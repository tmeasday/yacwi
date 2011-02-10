require 'fileutils'
namespace :<%= file_name %> do
  desc "compile java to javascript"
  task :compile => :environment do
    sh "<%= gwt_client_path %>/<%= class_name %>-compile"
  end
  
  desc "run <%= gwt_package %> module in hosted mode"
  task :hosted => :environment do
    sh "<%= gwt_client_path %>/<%= class_name %>-shell"
  end
  
  desc "clean compiled javascript"
  task :clean => :environment do
    rm_rf("<%= RAILS_ROOT %>/public/gwt/<%= gwt_module %>")
  end
end