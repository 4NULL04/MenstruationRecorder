package com.baocong.data;

import java.util.Calendar;

public class LogData {

	private static String log = "\nSuccessfully start up!\n\n";

	public static void recordLog(String content) {
		log += Calendar.getInstance().getTime();
		log += "\n\n\t";
		log += content;
		log += "\n\n";
	}

	public static String getLog() {
		return log;
	}

}
