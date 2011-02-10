package view.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

import view.client.resource.Event;

public class CalendarViewCell extends VerticalPanel {
	private Label header;
	
	private ArrayList events;
	
	public CalendarViewCell (int day) {
		header = new Label((new Integer(day)).toString());
		header.setStyleName("calendarCellHeader");
		
		add(header);
		setStyleName("calendarCell");
		setHeight("100%");
		setWidth("100%");
		
		events = new ArrayList ();
	}
	
	public void addEvent (Event e) {
		events.add(e);
		redraw();
	}
	
	public void removeCalendar(int calendarId) {
		for (int i = events.size()-1; i >= 0; i--) {
			Event e = (Event) events.get(i);
			
			if (e.getCalendarId() == calendarId) {
				events.remove(i);
			}
		}
		redraw();
	}
	
	private void redraw() {
		for (int i = this.getWidgetCount() -1; i > 0; i--) {
			this.remove(i);
		}
		
		for (int i = 0; i < events.size(); i++) {
			Event e = (Event) events.get(i);
			
			Date time = e.getStart ();
			String label = "* ";
			label += time.getHours();
			label += ":" + (time.getMinutes() < 10 ? "0" : "") + time.getMinutes();
			label += " " + e.getSummary();

			Label l = new Label(label);
			l.setStyleName("eventLabel");
			l.addStyleName(View.hashIdToColor(e.getCalendarId()));
			add(l);			
		}
	}
}