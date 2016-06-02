package com.baocong.data;

import java.util.HashMap;
import java.util.Map;

public class NoteData {

	private DateAttr date;
	private String note;

	private static Map<DateAttr, String> notes = new HashMap<>();

	public NoteData(DateAttr date, String note) {
		this.date = date;
		this.note = note;
		notes.put(date, note);
		LogData.recordLog("note data was saved successfully");
	}

	public DateAttr getDate() {
		return date;
	}

	public void setDate(DateAttr date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static String getNoteByDate(DateAttr date) {
		return notes.get(date);
	}

	public static void setNotes(Map<DateAttr, String> notes) {
		NoteData.notes = notes;
	}

	public static Map<DateAttr, String> getNotes() {
		return notes;
	}

	public static String showNotes() {
		String note = "NOTES";
		for (DateAttr date : notes.keySet()) {
			note += "\n\n===========================\n\n";
			note += date.getYear() + ", " + date.getMonth() + ", " + date.getDay();
			note += "\n\n";
			note += notes.get(date);
		}
		return note;
	}

	public static void clear() {
		notes.clear();
	}

}
