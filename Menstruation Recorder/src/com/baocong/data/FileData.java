package com.baocong.data;

public class FileData {

	public static final String MENSTRUATION_DATA = ".men";
	public static final String GRAPH_DATA = ".graph";
	public static final String NOTE_DATA = ".note";

	private static String mUrl;
	private static String nUrl;
	private static String gUrl;

	public static String getmUrl() {
		return mUrl;
	}

	public static void setmUrl(String mUrl) {
		FileData.mUrl = mUrl;
	}

	public static String getgUrl() {
		return gUrl;
	}

	public static void setgUrl(String gUrl) {
		FileData.gUrl = gUrl;
	}

	public static String getnUrl() {
		return nUrl;
	}

	public static void setnUrl(String nUrl) {
		FileData.nUrl = nUrl;
	}

}
