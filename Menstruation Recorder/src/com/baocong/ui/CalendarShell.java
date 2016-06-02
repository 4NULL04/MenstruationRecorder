package com.baocong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.baocong.data.CalendarAttr;
import com.baocong.data.DateAttr;
import com.baocong.data.FontAttr;
import com.baocong.data.LogData;
import com.baocong.data.MarkData;
import com.baocong.data.MenstruationData;

public class CalendarShell extends JPanel implements ActionListener {

	protected final String[] weekName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	protected final CalendarAttr calendar;
	protected final DateAttr dateAttr;
	protected int year;
	protected int month;
	protected int day;

	private JPanel panelCenter;
	private JButton btnPrev, btnNext;
	private JLabel lblMessage;
	protected JLabel[] lblDays = new JLabel[49];

	public CalendarShell() {
		dateAttr = new DateAttr();
		year = dateAttr.getYear();
		month = dateAttr.getMonth();
		day = dateAttr.getDay();

		setLayout(new BorderLayout(0, 0));

		panelCenter = new JPanel();
		panelCenter.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCenter.setBackground(Color.WHITE);
		add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(7, 7, 10, 10));
		for (int i = 0; i < 7; i++) {
			lblDays[i] = new JLabel(weekName[i], JLabel.CENTER);
			panelCenter.add(lblDays[i]);
		}
		lblDays[0].setForeground(Color.PINK);
		lblDays[6].setForeground(Color.PINK);
		calendar = new CalendarAttr(year, month);
		for (int i = 7; i < 49; i++) {
			lblDays[i] = new JLabel("", JLabel.CENTER);
			panelCenter.add(lblDays[i]);
		}
		setCalendarDays(calendar.getCalendar());

		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.setLayout(new GridLayout(1, 3, 0, 0));

		btnPrev = new JButton("Previous");
		btnPrev.setFont(FontAttr.menuFont);
		btnPrev.setActionCommand("prev");
		btnPrev.addActionListener(this);
		panelNorth.add(btnPrev);

		lblMessage = new JLabel(String.valueOf(year) + ", " + String.valueOf(month));
		lblMessage.setFont(FontAttr.menuFont);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorth.add(lblMessage);

		btnNext = new JButton("Next");
		btnNext.setFont(FontAttr.menuFont);
		btnNext.setActionCommand("next");
		btnNext.addActionListener(this);
		panelNorth.add(btnNext);

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					LogData.recordLog("error when refresh");
				}
				setCalendarDays(calendar.getCalendar());
			}

		}).start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setCalendarDays(calendar.getCalendar());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "prev":
			month -= 1;
			if (month < 1) {
				year -= 1;
				month = 12;
			}
			calendar.setYear(year);
			calendar.setMonth(month);
			setCalendarDays(calendar.getCalendar());
			break;
		case "next":
			month += 1;
			if (month > 12) {
				year += 1;
				month = 1;
			}
			calendar.setYear(year);
			calendar.setMonth(month);
			setCalendarDays(calendar.getCalendar());
			break;
		default:
			break;
		}
		lblMessage.setText(String.valueOf(year) + ", " + String.valueOf(month));
	}

	protected void setCalendarDays(String[] days) {
		for (int i = 7; i < 49; i++) {
			lblDays[i].setForeground(Color.BLACK);
			lblDays[i].setBackground(Color.WHITE);
			lblDays[i].setText(days[i - 7]);
			if (lblDays[i].getText() != null) {
				// current day set as red font
				if (lblDays[i].getText().equals(String.valueOf(day)) && month == dateAttr.getMonth()
						&& year == dateAttr.getYear()) {
					lblDays[i].setForeground(Color.RED);
				}
				// if has menstruation, set background as pink
				if (MenstruationData
						.isMenstruationByDate(new DateAttr(year, month, Integer.valueOf(lblDays[i].getText())))) {
					lblDays[i].setOpaque(true);
					lblDays[i].setBackground(Color.PINK);
				}
				// if was marked, set background as yellow
				if (MarkData.isMarked(new DateAttr(year, month, Integer.valueOf(lblDays[i].getText())))) {
					lblDays[i].setBackground(Color.YELLOW);
				}
			}
		}
	}

}
