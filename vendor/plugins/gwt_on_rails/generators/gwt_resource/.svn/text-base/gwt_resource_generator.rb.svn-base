require File.join(File.dirname(__FILE__), '..', '..', 'lib', 'gwt_on_rails')

class GwtResourceGenerator < Rails::Generator::NamedBase
  attr_accessor :gwt_resource_package
  attr_accessor :gwt_client
  
  def initialize(*runtime_args)
    super(*runtime_args)
    @gwt_client = GwtOnRails::Client.new args[0]
    @gwt_resource_package = gwt_client.resource_package
  end
  
  def manifest
    record do |m|
      # create the model package
      m.directory gwt_client.resource_path
      
      # create a stub class
      m.template "app/gwt/resource_stub_template.rb",
                 File.join(gwt_client.resource_path, class_name + '.java')
      
      # mention usage
      m.readme "POST_GENERATION_REMINDER"
    end
  end
end