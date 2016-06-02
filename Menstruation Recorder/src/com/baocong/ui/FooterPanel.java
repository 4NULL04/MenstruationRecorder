package com.baocong.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.baocong.data.DateAttr;
import com.baocong.data.FileData;
import com.baocong.data.FontAttr;
import com.baocong.data.LogData;

public class FooterPanel extends JPanel {
	private DateAttr date = new DateAttr();

	public FooterPanel() {
		setLayout(new BorderLayout(0, 0));

		String info = String.valueOf(date.getDay()) + ", " + String.valueOf(date.getMonth()) + ", "
				+ String.valueOf(date.getYear());
		JLabel lblTime = new JLabel(info);
		lblTime.setFont(FontAttr.menuFont);
		add(lblTime, BorderLayout.EAST);

		JLabel lblPath = new JLabel();
		lblPath.setFont(FontAttr.menuFont);
		add(lblPath, BorderLayout.WEST);

		new Thread(new Runnable() {

			private String temp = "";

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						LogData.recordLog("error when refresh");
					}
					if (!temp.equals(FileData.getmUrl())) {
						lblPath.setText("Path - " + FileData.getmUrl());
						temp = FileData.getmUrl();
					}
				}
			}
		}).start();

	}

}
