package com.baocong.data;

import java.util.ArrayList;
import java.util.List;

public class MenstruationData {

	private DateAttr date;
	private int lastTime;

	private static List<MenstruationData> mList = new ArrayList<>();

	public MenstruationData(DateAttr date, int lastTime) {
		this.date = date;
		this.lastTime = lastTime;
		mList.add(this);

		if (lastTime >= 1)
			new MenstruationData(date.nextDate(), --lastTime);

		LogData.recordLog("menstruation data was saved successfully");
	}

	public DateAttr getDate() {
		return date;
	}

	public void setDate(DateAttr date) {
		this.date = date;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public static List<MenstruationData> getmList() {
		return mList;
	}

	public static void setmList(List<MenstruationData> mList) {
		MenstruationData.mList = mList;
	}

	@Override
	public String toString() {
		return date.toString() + "#" + lastTime;
	}

	public static boolean isMenstruationByDate(DateAttr d) {
		for (MenstruationData m : mList) {
			if (m.date.equal(d) && m.lastTime >= 0)
				return true;
		}
		return false;
	}

	public static void clear() {
		mList.clear();
	}

}
