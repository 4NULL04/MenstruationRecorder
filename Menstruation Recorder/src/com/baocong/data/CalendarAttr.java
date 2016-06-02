package com.baocong.data;

import java.util.Calendar;

public class CalendarAttr {

	private int year;
	private int month;

	public CalendarAttr() {
		setYear(1990);
		setMonth(1);
	}

	public CalendarAttr(int year, int month) {
		this.year = year;
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String[] getCalendar() {
		String[] cal = new String[42];

		Calendar date = Calendar.getInstance();
		date.set(year, month - 1, 1);

		int week = date.get(Calendar.DAY_OF_WEEK) - 1;
		int day = dayOfMonth(month, year);

		for (int i = week, n = 1; i < week + day; i++, n++)
			cal[i] = String.valueOf(n);

		return cal;
	}

	public static int dayOfMonth(int month, int year) {
		int day = 0;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		case 2:
			if (isLeapYear(year))
				day = 29;
			else
				day = 28;
			break;
		default:
			break;
		}
		return day;
	}

	public static boolean isLeapYear(int year) {
		return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
	}

}
