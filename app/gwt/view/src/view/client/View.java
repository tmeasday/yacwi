package view.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.http.client.*;
import com.google.gwt.core.client.*;


import com.kaboomerang.gwt.rest.client.resource.*;
import com.kaboomerang.gwt.rest.client.resource.impl.*;
import com.kaboomerang.gwt.rest.client.handler.*;

/*import java.util.Date;*/
import java.util.*;

import view.client.resource.*;



/*import view.client.*;*/

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class View implements EntryPoint, WindowResizeListener {
	private HorizontalPanel mainPanel;
	private VerticalPanel calPanel;
	private VerticalPanel leftPanel;
	private HorizontalPanel monthPanel;
	
	private Button leftButton;
	private Button rightButton;
	private Label monthLabel;
	private Image spinner;
	
	private CalendarSelectorView selector;
	private CalendarView calendar;
	
	private ArrayList users = new ArrayList();
	private ArrayList calendars = new ArrayList ();

	static public int N_COLORS = 9;
	
	static public String hashIdToColor(int id) {
		return "color" + (id % N_COLORS + 1);
	}
	
	
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
			mainPanel = new HorizontalPanel();
			calPanel = new VerticalPanel();
			monthPanel = new HorizontalPanel();
			leftPanel = new VerticalPanel();
			
			spinner = new Image ("gwt/view.View/images/spinner.gif");
			spinner.setVisible(false);
			
			leftPanel.add(spinner);
			leftPanel.setCellHeight(spinner, "32px");
						
			selector = new CalendarSelectorView(this);
			selector.setWidth("20%");
			leftPanel.add(selector);
			leftPanel.setWidth("20%");
			
			mainPanel.add(leftPanel);
			mainPanel.add(calPanel);
			
			mainPanel.setCellWidth(leftPanel, "20%");
			mainPanel.setCellWidth(calPanel, "80%");
			mainPanel.setWidth("100%");
/*			panel1.setHeight("100%");*/
			
			calPanel.add(monthPanel);
			
			calendar = new CalendarView(6, 2008);
			calPanel.add(calendar);
			calPanel.setWidth("100%");
/*			panel2.setHeight("100%");*/
			calPanel.setStyleName("rightPanel");

			monthPanel.setStyleName("monthPanel");
			
			leftButton = new Button("&lt;", new ClickListener () {
				public void onClick(Widget button) {
					calendar.prevMonth();
					setMonthLabel();
					getEvents();
				}
			});
			monthPanel.add(leftButton);
			
			monthLabel = new Label();
			setMonthLabel();
			monthLabel.setStyleName("monthLabel");
			monthPanel.add(monthLabel);
			
			rightButton = new Button("&gt;", new ClickListener () {
				public void onClick(Widget button) {
					calendar.nextMonth();
					setMonthLabel();
					getEvents();
				}
			});
			monthPanel.add(rightButton);

/*			getEvents();*/
			getUsers();			
			getCalendars();

			RootPanel.get().add(mainPanel);
			RootPanel.get().setStyleName("root");
/*			RootPanel.get().setHeight("100%");*/
					
			// Hook the window resize event, so that we can adjust the UI.
	    Window.addWindowResizeListener(this);
	
	    // Call the window resized handler to get the initial sizes setup. Doing
	    // this in a deferred command causes it to occur after all widgets' sizes
	    // have been computed by the browser.
	    DeferredCommand.addCommand(new Command() {
	      public void execute() {
	        onWindowResized(Window.getClientWidth(), Window.getClientHeight());
	      }
	    });

	    onWindowResized(Window.getClientWidth(), Window.getClientHeight());
	  }

	  public void onWindowResized(int width, int height) {
	    // Adjust the shortcut panel and detail area to take up the available room
	    // in the window.
			int pad = RootPanel.get().getOffsetHeight() - calendar.getOffsetHeight();
	    int h = height - pad -8;
	    if (h < 1) {
	      h = 1;
	    }

/*			Window.alert(h + "");*/
			calendar.setHeight(h + "px");
	  }
	
	private void setMonthLabel() {
		monthLabel.setText(calendar.getMonthName() + " " + calendar.getYear());
	}
	
	public void calendarSubscribed(int calendarId, boolean checked) {
		if (checked) {
			getEventsByCalendar(calendarId);
		} else {
			calendar.removeCalendar(calendarId);
		}
	}
	
	private void getEvents() {
		ArrayList calids = selector.getSelectedCalendars();
		for (int i = 0; i < calids.size(); i++) {
			getEventsByCalendar(((Integer) calids.get(i)).intValue());			
		}
	}
	
	private void getEventsByCalendar (int calendarId) {
		startLoading();
		ResourceCollection events = new RailsJsonResourceCollection(
			GWT.getHostPageBaseURL() + "events/by_calendar_date/" + calendarId +
						"/" + calendar.getYear() + "/" + calendar.getMonth(),
		    new ResourceFactory() {
		        public Resource create() {
		            return (Resource) GWT.create(view.client.resource.Event.class);
		        }
		    });

		events.findAll(
		    new CollectionResponseHandler() {
		        public void onSuccess(Collection resources) {
							ArrayList events = new ArrayList(resources);
							for (int i = 0; i < events.size (); i++) {
								/*
																Window.alert("Event summary is " + 
																	((view.client.resource.Event) events.get(i)).getSummary());*/
								calendar.addEvent((view.client.resource.Event) events.get(i));
							}
							endLoading();
		        }

		        public void onError(Request request, Throwable exception) {
							endLoading();
	            Window.alert("Something went wrong.");
		        }
		    });
	}
	
	private void getCalendars() {
		startLoading();
		ResourceCollection cal_rs = new RailsJsonResourceCollection(
			GWT.getHostPageBaseURL() + "calendars",
		    new ResourceFactory() {
		        public Resource create() {
		            return (Resource) GWT.create(view.client.resource.Calendar.class);
		        }
		    });

		cal_rs.findAll(
		    new CollectionResponseHandler() {
		        public void onSuccess(Collection resources) {
							calendars = new ArrayList(resources);
							for (int i = 0; i < calendars.size(); i++) {
								selector.addCalendar (
									(view.client.resource.Calendar) calendars.get(i));
							}
	
							endLoading();
		        }

		        public void onError(Request request, Throwable exception) {
							endLoading();
	            Window.alert("Something went wrong.");
		        }
		    });
	}
	
	
	private void getUsers() {
		startLoading();
		ResourceCollection usr_rs = new RailsJsonResourceCollection(
				GWT.getHostPageBaseURL() + "users",
		    new ResourceFactory() {
		        public Resource create() {
		            return (Resource) GWT.create(view.client.resource.User.class);
		        }
		    });

		usr_rs.findAll(
		    new CollectionResponseHandler() {
		        public void onSuccess(Collection resources) {
							users = new ArrayList(resources);
							for (int i = 0; i < users.size(); i++) {
								selector.addUser (
									(view.client.resource.User) users.get(i));
							}
							endLoading();
		        }

		        public void onError(Request request, Throwable exception) {
							endLoading();
	            Window.alert("Something went wrong.");
		        }
		    });
	}
	
	private void startLoading () {
		spinner.setVisible(true);
	}
	
	private void endLoading () {
		spinner.setVisible(false);
	}
}
