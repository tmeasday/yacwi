package view.client;

import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;

import view.client.resource.*;

import java.util.*;

public class CalendarSelectorView extends Tree {
	private HashMap userItems = new HashMap();
	private View view;
	
	
	public CalendarSelectorView (View view) {
		this.view = view;
	}
	
	public void addUser (view.client.resource.User user) {
		Integer id = new Integer(user.getId());
		
		// first check if it's already in the tree
		if (userItems.containsKey(id)) {
			TreeItem ti = (TreeItem) userItems.get(id);
			ti.setText(user.getName());
			ti.setVisible(true);
			ti.setState(true);
		} else {
			TreeItem ti = new TreeItem(user.getName());
			this.addItem(ti);
			ti.setState(true);
			userItems.put(id, ti);
		}
	}
	
	public void addCalendar (view.client.resource.Calendar cal) {
		TreeItem ti = null;
		Integer id = new Integer(cal.getUserId());
		
		// check if the user is in the tree
		if (userItems.containsKey(id)) {
			ti = (TreeItem) userItems.get(id);
		} else {
			ti = new TreeItem("");
			this.addItem(ti);
			ti.setState(true);
			userItems.put(id, ti);
			ti.setVisible(false);
		}			
					
		CheckBox cb = new CheckBox(cal.getName());
		cb.setName(cal.getId() + "");
		cb.setStyleName(View.hashIdToColor(cal.getId()));
		ti.addItem(cb);
		ti.setState(true);
		
		cb.addClickListener(new ClickListener() {
      public void onClick(Widget sender) {
				CheckBox cb = (CheckBox) sender;
       	view.calendarSubscribed((new Integer(cb.getName())).intValue(), 
																cb.isChecked());
      }
    });
	}
	
	public ArrayList getSelectedCalendars () {
		ArrayList calids = new ArrayList();
		for (int i = 0; i < getItemCount(); i++) {
			TreeItem userItem = this.getItem(i);
			for (int j = 0; j < userItem.getChildCount(); j++) {
				TreeItem calItem = userItem.getChild(j);
				CheckBox cb = (CheckBox) calItem.getWidget();
				if (cb.isChecked()) {
					calids.add(new Integer(cb.getName()));
				}
			}
		}
		
		return calids;
	}
}