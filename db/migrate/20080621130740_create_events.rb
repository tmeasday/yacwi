class CreateEvents < ActiveRecord::Migration
  def self.up
    create_table :events do |t|
      t.integer :calendar_id
      t.string :summary
      t.string :location
      t.text :notes
      t.datetime :start
      t.datetime :end
      t.string :uid

      t.timestamps
    end
  end

  def self.down
    drop_table :events
  end
end
