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
 * Place custom code in Calendar.java
 *
 * Each migration will overwrite this file.
 *
 */

public class CalendarBase implements Resource {
    protected PropertyChangeSupport changes = new PropertyChangeSupport(this);
    protected DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    protected String name;
    protected Date updatedAt;
    protected Date createdAt;
    protected String uid;
    protected int id;
    protected int userId;
    
    public CalendarBase() {}
    
    public void populateFromRepresentation(String representation) {
        JSONObject json = (JSONObject) JSONParser.parse(representation);
        Iterator i;
        
				json = json.get("calendar").isObject();
        
        for (i = json.keySet().iterator(); i.hasNext();) {
            String key = (String) i.next();
            
            if ("name".equals(key)) {
                JSONString js = json.get(key).isString();
                if (js != null) {
                    this.name = js.stringValue();
                } else {
                    this.name = "";
                }
            } else if ("updated_at".equals(key)) {
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
            } else if ("id".equals(key)) {
                this.id = new Double(json.get(key).isNumber().getValue()).intValue();
            } else if ("user_id".equals(key)) {
                this.userId = new Double(json.get(key).isNumber().getValue()).intValue();
            }            
        }
    }
    
    public String toRepresentation() {
        return "{\"calendar\": {" +
            "\"name\": " +
            new JSONString(name).toString() +
            ", " +
            "\"updated_at\": " +
            new JSONString(dateTimeFormat.format(updatedAt)).toString() +
            ", " +
            "\"created_at\": " +
            new JSONString(dateTimeFormat.format(createdAt)).toString() +
            ", " +
            "\"uid\": " +
            new JSONString(uid).toString() +
            ", " +
            "\"id\": " +
            id +
            ", " +
            "\"user_id\": " +
            userId +
            "}}";
    }
    
    // Accessors

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        String old = this.name;
        this.name = name;
        changes.firePropertyChange("name", old, name);
    }
    
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
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        int old = this.id;
        this.id = id;
        changes.firePropertyChange("id", old, id);
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        int old = this.userId;
        this.userId = userId;
        changes.firePropertyChange("userId", old, userId);
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
