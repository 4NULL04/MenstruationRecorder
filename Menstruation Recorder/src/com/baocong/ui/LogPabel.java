package com.baocong.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.baocong.data.FontAttr;
import com.baocong.data.LogData;

public class LogPabel extends JPanel implements Runnable {

	private JTextArea txtrLog;
	private String log = "";
	private int length;

	public LogPabel() {
		setLayout(new BorderLayout(0, 0));

		txtrLog = new JTextArea(log);
		txtrLog.setFont(FontAttr.menuFont);

		JScrollPane scrollPane = new JScrollPane(txtrLog);
		add(scrollPane, BorderLayout.CENTER);

		length = log.length();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				LogData.recordLog("error in LogPanel thread");
			}
			if ((log = LogData.getLog()).length() != length) {
				txtrLog.setText(log);
				length = log.length();
			}
		}
	}

}
