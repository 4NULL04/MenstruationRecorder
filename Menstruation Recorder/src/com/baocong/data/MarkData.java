package com.baocong.data;

import javax.swing.JOptionPane;

public class MarkData {

	private static DateAttr lastMark = null;

	private MarkData() {
	}

	public static DateAttr getMark() {
		return lastMark;
	}

	public static void setMark(DateAttr date) {
		lastMark = date;
	}

	public static void mark(DateAttr date) {
		if (lastMark == null)
			lastMark = date;
		else if (DateAttr.order(lastMark, date) < 0) {
			int choice = JOptionPane.showConfirmDialog(null, "You have marked a date before, link these days?",
					"Confirm", JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				new MenstruationData(lastMark, DateAttr.difference(lastMark, date));
				lastMark = null;
			}
		}
	}

	public static void demark() {
		lastMark = null;
	}

	public static boolean isMarked(DateAttr date) {
		if (lastMark != null)
			return lastMark.equal(date);
		else
			return false;
	}

}
