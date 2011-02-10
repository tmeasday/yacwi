package view.client;

import com.google.gwt.user.client.ui.Grid;

import java.util.Date;
import java.util.ArrayList;

import com.google.gwt.user.client.*;

import view.client.resource.Event;


public class CalendarView extends Grid {
	private int month;
	private int year;
	
	ArrayList cells;
	
	public CalendarView(int month, int year) {
		this.month = month;
		this.year  = year;
		
		resize (7, 7);
		setWidth("100%");
		setStyleName("calendarView");
		
		cells = new ArrayList();
		
		setText(0, 0, "Monday");
		setText(0, 1, "Tuesday");
		setText(0, 2, "Wednesday");
		setText(0, 3, "Thursday");
		setText(0, 4, "Friday");
		setText(0, 5, "Saturday");
		setText(0, 6, "Sunday");
		
/*		RowFormatter rformatter = new RowFormatter();
		rformatter.addStyleName(0, "dayRow");
		rformatter.setHeight(0, "10%");
		for (int i = 1; i < 7; i++) {
			rformatter.setHeight(i, 90/6 + "%");
		}
		setRowFormatter(rformatter);
		
		ColumnFormatter cformatter = new ColumnFormatter();
		for (int i = 0; i < 7; i++) {
			cformatter.setWidth(i, 100/7 + "%");
		}
		setColumnFormatter(cformatter);*/
		
		CellFormatter formatter = new CellFormatter();
		for (int row = 0; row < 7; row++) {
			for (int col = 0; col < 7; col++) {
				formatter.setWidth(row, col, 100/7 + "%");
				
				if (row == 0) {
					formatter.setHeight(row, col, "5%");
					formatter.addStyleName(row, col, "dayRow");
				} else {
					formatter.setHeight(row, col, 95/6 + "%");
				}
			}
		}
		setCellFormatter(formatter);
		
		format();
	}
	
	public void format() {
		this.clear();	
		cells.clear();
			
		// Dealing with Java shittyness
		int firstDay = new Date(this.year - 1900, this.month-1, 1).getDay();
		firstDay -= 1;
		if (firstDay == -1) { firstDay = 6; }
/*		Window.alert ("" + firstDay);*/
		
		int lastDate = daysInMonth(this.month);
/*		Window.alert ("" + lastDate);*/
		int row = 1;
		int col = firstDay;
		for (int i = 1; i <= lastDate; i++) {
			CalendarViewCell cell =  new CalendarViewCell(i);
			setWidget(row, col, cell);
			cells.add(cell);
			
			col += 1;			
			if (col % 7 == 0) {
				row += 1;
				col = 0;
			}
		}
	}
	
	public String getMonthName () {
		switch (this.month) {
			case 1: return "January";
			case 2: return "February";
			case 3: return "March";
			case 4: return "April";
			case 5: return "May";
			case 6: return "June";
			case 7: return "July";
			case 8: return "August";
			case 9: return "September";
			case 10: return "October";
			case 11: return "November";
			case 12: return "December";
		}
		return "Whoops!";
	}
	
	public int getYear () {
		return this.year;
	}
	
	public int getMonth () {
		return this.month;
	}
	
	public void nextMonth() {
		this.month += 1;
		if (this.month == 13) {
			this.month = 1;
			this.year += 1;
		}
		
		this.format();
	}
	
	
	public void prevMonth() {
		this.month -= 1;
		if (this.month == 0) {
			this.month = 12;
			this.year -= 1;
		}
		
		this.format();
	}
	
	public void addEvent(Event e) {
		Date start = e.getStart();
		if (start.getMonth()+1 != this.month ||
				start.getYear()+1900 != this.year) {
			return;
		}

		CalendarViewCell cell = (CalendarViewCell) cells.get(start.getDate() - 1);

		cell.addEvent(e);		
	}
	
	public void removeCalendar(int calendarId) {
		for (int i = 0; i < cells.size(); i++) {
			((CalendarViewCell) cells.get(i)).removeCalendar(calendarId);
		}
	}
		
	private int daysInMonth (int month) {
		switch (month) {
			case 1: case 3: case 5: case 7: case 8: case 10: case 12:
			return 31;
			case 2: return 28;
			default: return 30;
		}
	}
}
