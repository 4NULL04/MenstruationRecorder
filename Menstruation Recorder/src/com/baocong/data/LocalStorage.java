package com.baocong.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LocalStorage {

	public static void readBasicdata() {
		File file = new File("data/data.mr");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			String[] str = line.split("#");
			FileData.setmUrl(str[0]);
			FileData.setnUrl(str[1]);
			int year = Integer.parseInt(str[2]);
			int month = Integer.parseInt(str[3]);
			int day = Integer.parseInt(str[4]);
			MarkData.setMark(new DateAttr(year, month, day));
			br.close();
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			MarkData.setMark(null);
		} catch (Exception e) {
			LogData.recordLog("error when read path data");
		}
	}

	public static List<MenstruationData> readMenstruation() {
		List<MenstruationData> list = new LinkedList<>();
		File file = new File(FileData.getmUrl());
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] str = line.split("#");
				int year = Integer.parseInt(str[0]);
				int month = Integer.parseInt(str[1]);
				int day = Integer.parseInt(str[2]);
				int last = Integer.parseInt(str[3]);
				list.add(new MenstruationData(new DateAttr(year, month, day), last));
			}
			br.close();
		} catch (Exception e) {
			LogData.recordLog("error when read menstruation data");
		}
		return list;
	}

	public static Map<DateAttr, String> readNote() {
		Map<DateAttr, String> map = new HashMap<>();
		File file = new File(FileData.getnUrl());
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] str = line.split("#");
				int year = Integer.parseInt(str[0]);
				int month = Integer.parseInt(str[1]);
				int day = Integer.parseInt(str[2]);
				String note = str[3];
				map.put(new DateAttr(year, month, day), note);
			}
			br.close();
		} catch (Exception e) {
			LogData.recordLog("error when read note data");
		}
		return map;
	}

	public static void writeMenstruation() {
		File file = new File(FileData.getmUrl());
		if (file.exists())
			file.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (MenstruationData m : MenstruationData.getmList()) {
				bw.write(m.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			LogData.recordLog("error when record menstruation data");
		}
	}

	public static void writeNote() {
		File file = new File(FileData.getnUrl());
		if (file.exists())
			file.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			for (DateAttr d : NoteData.getNotes().keySet()) {
				bw.write(d.toString() + "#" + NoteData.getNotes().get(d));
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (Exception e) {
			LogData.recordLog("error when record note data");
		}
	}

	public static void writeLog() {
		File file = new File("data/log.txt");
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write(LogData.getLog().replace("\n", "\r\n"));
			bw.flush();
			bw.close();
		} catch (Exception e) {
			LogData.recordLog("error when record log data");
		}
	}

	public static void writeBasicData() {
		File file = new File("data/data.mr");
		if (file.exists())
			file.delete();
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
			if (MarkData.getMark() != null)
				bw.write(FileData.getmUrl() + "#" + FileData.getnUrl() + "#" + MarkData.getMark().toString());
			else
				bw.write(FileData.getmUrl() + "#" + FileData.getnUrl() + "###");
			bw.flush();
			bw.close();
		} catch (Exception e) {
			LogData.recordLog("error when record url data");
		}
	}

	public static void readData() {
		readBasicdata();
		MenstruationData.setmList(readMenstruation());
		NoteData.setNotes(readNote());
		LogData.recordLog("read data successfully");
	}

	public static void saveData() {
		writeMenstruation();
		writeNote();
		writeBasicData();
		LogData.recordLog("record data successfully");
		writeLog();
	}

}
