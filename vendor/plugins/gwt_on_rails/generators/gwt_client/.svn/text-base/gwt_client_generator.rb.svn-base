require File.join(File.dirname(__FILE__), '..', '..', 'lib', 'gwt_on_rails')

class GwtClientGenerator < Rails::Generator::NamedBase
  attr_accessor :gwt_home
  attr_accessor :gwt_entry_point
  attr_accessor :gwt_module
  attr_accessor :gwt_client_path
  attr_accessor :gwt_package
  attr_accessor :gwt_entry_point_package
  attr_accessor :gwt_client

  # TODO remove unix-y deps in gwt shell, compile, and rake
  
  def initialize(*runtime_args)
    super(*runtime_args)
    @gwt_client = GwtOnRails::Client.new name
    @gwt_home = args[0]
    @gwt_package = gwt_client.package
    @gwt_entry_point_package = gwt_client.entry_point_package
    @gwt_entry_point = gwt_client.entry_point
    @gwt_module = gwt_client.module
    @gwt_client_path = gwt_client.path
  end
  
  def gwt_dev_platform
    if RUBY_PLATFORM =~ /mswin|mingw|bccwin|wince/
      'win'
    elsif RUBY_PLATFORM =~ /darwin/
      'mac'
    else
      'linux' # TODO map out possibilities and allow user to override
    end
  end
  
  def manifest
    record do |m|
      m.class_collisions class_name
      
      # create GWT directory structure
      m.directory File.join('app', 'gwt', file_name, 'lib')
      m.directory File.join('app', 'gwt', file_name, 'src', gwt_package, 'client', 'resource')
      
      # generate GWT starter project and module
      m.template "app/gwt/shell_template.rb",
                 "app/gwt/#{file_name}/#{class_name}-shell",
                 :chmod => 0755
      m.template "app/gwt/compile_template.rb",
                 "app/gwt/#{file_name}/#{class_name}-compile",
                 :chmod => 0755
      m.template "app/gwt/src/config_template.rb",
                 "app/gwt/#{file_name}/src/#{gwt_package}/#{class_name}.gwt.xml"
      m.template "app/gwt/src/client/entry_point_template.rb",
                 "app/gwt/#{file_name}/src/#{gwt_package}/client/#{class_name}.java"
                 
      # add GWT-REST
      m.file "app/gwt/lib/gwt-rest.jar",
             "app/gwt/#{file_name}/lib/gwt-rest.jar"

      # add GWTx
      m.file "app/gwt/lib/gwtx.jar",
             "app/gwt/#{file_name}/lib/gwtx.jar"
                
      # build controller, helper and template for GWT module container
      m.template "app/controllers/controller_template.rb",
                 "app/controllers/#{file_name}_controller.rb"
      m.template "app/helpers/helper_template.rb",
                 "app/helpers/#{file_name}_helper.rb"
      m.directory File.join('app', 'views', file_name)
      
      # create GWT bootstrap page
      m.template "app/views/view_template.rb",
                 "app/views/#{file_name}/index.html.erb"
                 
      # create folder for compiled GWT JavaScript
      m.directory File.join('public', 'gwt', gwt_module)
      
      # create Rake tasks to management development
      m.template "lib/tasks/rake_template.rb",
                 "lib/tasks/#{file_name}_tasks.rake"
                 
      # mention helpful plugins, etc.
      m.readme "POST_GENERATION_REMINDER"
    end
  end
end
