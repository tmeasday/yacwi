require 'fileutils'
namespace :view do
  desc "compile java to javascript"
  task :compile => :environment do
    sh "app/gwt/view/View-compile"
  end
  
  desc "run view module in hosted mode"
  task :hosted => :environment do
    sh "app/gwt/view/View-shell"
  end
  
  desc "clean compiled javascript"
  task :clean => :environment do
    rm_rf("/Users/tom/Projects/yacwi/public/gwt/view.View")
  end
end