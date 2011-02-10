package view.client.resource;

import com.kaboomerang.gwt.rest.client.resource.Resource;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.HashMap;
import java.beans.PropertyChangeListener; 
import java.beans.PropertyChangeSupport;

/*
 * Generated Base Class. Do not edit.
 *
 * Place custom code in Event.java
 *
 * Each migration will overwrite this file.
 *
 */

public class EventBase implements Resource {
    protected PropertyChangeSupport changes = new PropertyChangeSupport(this);
    protected DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    protected Date updatedAt;
    protected Date createdAt;
    protected String uid;
    protected Date end;
    protected String notes;
    protected int id;
    protected Date start;
    protected String summary;
    protected int calendarId;
    protected String location;
    
    public EventBase() {}
    
    public void populateFromRepresentation(String representation) {
        JSONObject json = (JSONObject) JSONParser.parse(representation);
        Iterator i;
        
				json = json.get("event").isObject();
        
        for (i = json.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            
            if ("updated_at".equals(key)) {
               JSONString js = json.get(key).isString();
               if (js != null) {
                   this.updatedAt = dateTimeFormat.parse(js.stringValue());
               }
            } else if ("created_at".equals(key)) {
               JSONString js = json.get(key).isString();
               if (js != null) {
                   this.createdAt = dateTimeFormat.parse(js.stringValue());
               }
            } else if ("uid".equals(key)) {
                JSONString js = json.get(key).isString();
                if (js != null) {
                    this.uid = js.stringValue();
                } else {
                    this.uid = "";
                }
            } else if ("end".equals(key)) {
               JSONString js = json.get(key).isString();
               if (js != null) {
                   this.end = dateTimeFormat.parse(js.stringValue());
               }
            } else if ("notes".equals(key)) {
                JSONString js = json.get(key).isString();
                if (js != null) {
                    this.notes = js.stringValue();
                } else {
                    this.notes = "";
                }
            } else if ("id".equals(key)) {
                this.id = new Double(json.get(key).isNumber().getValue()).intValue();
            } else if ("start".equals(key)) {
               JSONString js = json.get(key).isString();
               if (js != null) {
                   this.start = dateTimeFormat.parse(js.stringValue());
               }
            } else if ("summary".equals(key)) {
                JSONString js = json.get(key).isString();
                if (js != null) {
                    this.summary = js.stringValue();
                } else {
                    this.summary = "";
                }
            } else if ("calendar_id".equals(key)) {
                this.calendarId = new Double(json.get(key).isNumber().getValue()).intValue();
            } else if ("location".equals(key)) {
                JSONString js = json.get(key).isString();
                if (js != null) {
                    this.location = js.stringValue();
                } else {
                    this.location = "";
                }
            }            
        }
    }
    
    public String toRepresentation() {
        return "{\"event\": {" +
            "\"updated_at\": " +
            new JSONString(dateTimeFormat.format(updatedAt)).toString() +
            ", " +
            "\"created_at\": " +
            new JSONString(dateTimeFormat.format(createdAt)).toString() +
            ", " +
            "\"uid\": " +
            new JSONString(uid).toString() +
            ", " +
            "\"end\": " +
            new JSONString(dateTimeFormat.format(end)).toString() +
            ", " +
            "\"notes\": " +
            new JSONString(notes).toString() +
            ", " +
            "\"id\": " +
            id +
            ", " +
            "\"start\": " +
            new JSONString(dateTimeFormat.format(start)).toString() +
            ", " +
            "\"summary\": " +
            new JSONString(summary).toString() +
            ", " +
            "\"calendar_id\": " +
            calendarId +
            ", " +
            "\"location\": " +
            new JSONString(location).toString() +
            "}}";
    }
    
    // Accessors

    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        Date old = this.updatedAt;
        this.updatedAt = updatedAt;
        changes.firePropertyChange("updatedAt", old, updatedAt);
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        Date old = this.createdAt;
        this.createdAt = createdAt;
        changes.firePropertyChange("createdAt", old, createdAt);
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        String old = this.uid;
        this.uid = uid;
        changes.firePropertyChange("uid", old, uid);
    }
    
    public Date getEnd() {
        return end;
    }
    
    public void setEnd(Date end) {
        Date old = this.end;
        this.end = end;
        changes.firePropertyChange("end", old, end);
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        String old = this.notes;
        this.notes = notes;
        changes.firePropertyChange("notes", old, notes);
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        int old = this.id;
        this.id = id;
        changes.firePropertyChange("id", old, id);
    }
    
    public Date getStart() {
        return start;
    }
    
    public void setStart(Date start) {
        Date old = this.start;
        this.start = start;
        changes.firePropertyChange("start", old, start);
    }
    
    public String getSummary() {
        return summary;
    }
    
    public void setSummary(String summary) {
        String old = this.summary;
        this.summary = summary;
        changes.firePropertyChange("summary", old, summary);
    }
    
    public int getCalendarId() {
        return calendarId;
    }
    
    public void setCalendarId(int calendarId) {
        int old = this.calendarId;
        this.calendarId = calendarId;
        changes.firePropertyChange("calendarId", old, calendarId);
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        String old = this.location;
        this.location = location;
        changes.firePropertyChange("location", old, location);
    }
    
    // Property Change Support
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
        changes.addPropertyChangeListener(propertyName, l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener l) {
        changes.removePropertyChangeListener(propertyName, l);
    }
}
