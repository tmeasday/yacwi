class User < ActiveRecord::Base
  has_many :calendars
  
  def self.find_or_create(name)
    User.find_by_name(name) || User.create(:name => name)
  end
end