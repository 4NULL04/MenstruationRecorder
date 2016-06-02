package com.baocong.data;

import java.util.Calendar;

public class DateAttr {

	private int year;
	private int month;
	private int day;

	private Calendar date = Calendar.getInstance();

	public DateAttr() {
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH) + 1;
		day = date.get(Calendar.DATE);
	}

	public DateAttr(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return year + "#" + month + "#" + day;
	}

	public boolean equal(DateAttr date) {
		return (year == date.year && month == date.month && day == date.day);
	}

	public DateAttr prevDate() {
		int year = this.year;
		int month = this.month;
		int day = this.day - 1;
		if (day < 1) {
			if (--month < 1) {
				--year;
				month = 12;
			}
			day = CalendarAttr.dayOfMonth(month, year);
		}
		return new DateAttr(year, month, day);
	}

	public DateAttr nextDate() {
		int year = this.year;
		int month = this.month;
		int day = this.day + 1;
		if (day > CalendarAttr.dayOfMonth(month, year)) {
			if (++month > 12) {
				++year;
				month = 1;
			}
			day = 1;
		}
		return new DateAttr(year, month, day);
	}

	public static int difference(DateAttr earlier, DateAttr later) {
		int diff = 0;
		while (!earlier.equal(later)) {
			earlier = earlier.nextDate();
			diff++;
		}
		return diff;
	}

	public static int order(DateAttr first, DateAttr second) {
		if (first.year > second.year)
			return 1;
		else if (first.year < second.year)
			return -1;
		else if (first.month > second.month)
			return 1;
		else if (first.month < second.month)
			return -1;
		else if (first.day > second.day)
			return 1;
		else if (first.day < second.day)
			return -1;
		else
			return 0;
	}

}
