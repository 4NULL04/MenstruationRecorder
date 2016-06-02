package com.baocong.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import com.baocong.data.DateAttr;
import com.baocong.data.MarkData;
import com.baocong.data.MenstruationData;

public class CalendarPanel extends CalendarShell {

	public CalendarPanel() {
		for (int i = 0; i < 7; i++) {
			lblDays[i].setOpaque(true);
			lblDays[i].setBackground(Color.GRAY);
			lblDays[i].setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		}
		for (int i = 7; i < 49; i++) {
			lblDays[i].setOpaque(true);
			lblDays[i].setBackground(Color.WHITE);
			lblDays[i].setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lblDays[i].setBorder(BorderFactory.createEtchedBorder());
			if (lblDays[i].getText() != null) {
				lblDays[i].addMouseListener(new Action(i));
			}
		}
	}

	@Override
	protected void setCalendarDays(String[] days) {
		super.setCalendarDays(days);
		for (int i = 7; i < 49; i++) {
			int number = lblDays[i].getMouseListeners().length;
			while (number > 0) {
				lblDays[i].removeMouseListener(lblDays[i].getMouseListeners()[number - 1]);
				number--;
			}
			if (lblDays[i].getText() != null) {
				lblDays[i].addMouseListener(new Action(i));
			}
		}
	}

	private class Action extends MouseAdapter {

		private int i;

		public Action(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Color bg = lblDays[i].getBackground();
			if (e.getClickCount() == 2 && bg != Color.PINK) {
				String duration = JOptionPane.showInputDialog("Please enter the duration.");
				if (!duration.isEmpty()) {
					new MenstruationData(new DateAttr(year, month, Integer.valueOf(lblDays[i].getText())),
							Integer.valueOf(duration));
					repaint();
					MainFrame.isChanged = true;
				} else {
					JOptionPane.showMessageDialog(getParent(), "Nothing was entered!");
				}
			} else if ((e.getModifiers() & InputEvent.BUTTON1_MASK) != 0 && bg != Color.PINK) {
				lblDays[i].setBackground(Color.YELLOW);
				MarkData.mark(new DateAttr(year, month, Integer.valueOf(lblDays[i].getText())));
				repaint();
				MainFrame.isChanged = true;
			} else if ((e.getModifiers() & InputEvent.BUTTON3_MASK) != 0 && bg != Color.PINK) {
				lblDays[i].setBackground(Color.WHITE);
				MarkData.demark();
				repaint();
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

	}

}
