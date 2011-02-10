class CreateCalendars < ActiveRecord::Migration
  def self.up
    create_table :calendars do |t|
      t.integer :user_id
      t.string :name
      t.string :uid

      t.timestamps
    end
  end

  def self.down
    drop_table :calendars
  end
end
