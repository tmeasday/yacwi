module GwtOnRails
  class Client
    attr_accessor :name
    attr_accessor :gwt_root
    attr_accessor :package
    attr_accessor :dir
    attr_accessor :path
    attr_accessor :resource_path
    attr_accessor :resource_package
    attr_accessor :module
    attr_accessor :entry_point_package
    attr_accessor :entry_point
    
    def initialize(name)
      @name = name.camelize
      @gwt_root = File.join('app', 'gwt')
      @package = @name.downcase
      @module = [@package, @name].join('.')
      @dir = @name.underscore
      @path = File.join(@gwt_root, @dir)
      @resource_path = File.join(@path, 'src', @package, 'client', 'resource')
      @entry_point_package = [@package, 'client'].join('.')
      @entry_point = [@entry_point_package, @name].join('.')
      @resource_package = [@entry_point_package, 'resource'].join('.')
    end
  end
end

module ActiveRecord
  class SchemaDumper
    include GwtOnRails
    alias_method :dump_gwt_copy, :dump
  
    def dump(stream)
      dump_gwt_copy(stream)
      create_models_from_tables
    end
    
    def create_models_from_tables
      @connection.tables.sort.each do |tbl|
        if tbl != 'schema_info'
          generate_gwt_model_if_defined(tbl)
        end
      end
    end
    
    def generate_gwt_model_if_defined(table)
      gwt_root = File.join(RAILS_ROOT, 'app', 'gwt')
      if File.exists?(gwt_root)
        Dir.foreach(gwt_root) do |dir|
          if (dir.first != '.')
            gwt_client = Client.new dir
            Dir.foreach(gwt_client.resource_path) do |stub|
              if (stub == table.camelize.singularize + '.java')
                create_model(table, gwt_client)
              end
            end
          end
        end
      end
    end

    def create_model(table, gwt_client)
      columns = @connection.columns(table)
      gwt_resource_package = gwt_client.resource_package
      gwt_resource_name = table.camelize.singularize

      if @connection.respond_to?(:pk_and_sequence_for)
        pk, pk_seq = @connection.pk_and_sequence_for(table)
      end
      pk ||= 'id'
      
      properties = {}
      
      if columns.detect { |c| c.name == pk }
        properties['id'] = 'int'
      end

      column_specs = columns.map do |column|
        next if column.name == pk
        properties[java_property_name(column)] = java_mapping(column.type)
      end
      
      template_file = open(File.join(File.dirname(__FILE__), '..', 'templates', 'resource_base_template.rb'))
      template = ERB.new(template_file.read, nil, '-')
      
      open(File.join(gwt_client.resource_path, table.camelize.singularize + 'Base.java'), 'w') { |f| f << template.result(binding) }
    end
    
    def java_property_name(column)
      s = column.name.inspect.to_s.gsub('"', '').camelize
      return s.sub(s.first, s.first.downcase)
    end
    
    def java_mapping(column_type)
      case column_type.to_s
        when 'string'
          'String'
        when 'text'
          'String'
        when 'integer'
          'int'
        when 'float'
          'double'
        when 'decimal'
          'double'
        when 'datetime'
          'Date'
        when 'timestamp'
          'Date'
        when 'time'
          'Date'
        when 'date'
          'DateOnly' # Parsing of a date-only format in GWT needs different treatment than other formats
        when 'binary'
          'String'
        when 'boolean'
          'boolean'
      end
    end
  end
end
