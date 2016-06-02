package com.baocong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import com.baocong.data.DateAttr;
import com.baocong.data.FontAttr;
import com.baocong.data.LogData;
import com.baocong.data.NoteData;

public class AsidePanel extends JPanel implements ActionListener {

	private JButton btnSave, btnReset;
	private JTextArea txtrNote;
	private JPanel panelCenter;
	private JPanel panelSouth;

	public AsidePanel() {
		setLayout(new BorderLayout(0, 0));
		panelCenter = new CalendarShell();
		add(panelCenter, BorderLayout.CENTER);

		panelSouth = new JPanel();
		panelSouth.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setLayout(new BorderLayout(0, 0));

		txtrNote = new JTextArea();
		txtrNote.setBackground(new Color(248, 248, 255));
		txtrNote.setRows(18);
		txtrNote.setText("Record what happened today");
		panelSouth.add(txtrNote, BorderLayout.CENTER);

		JPanel panelNote = new JPanel();
		panelSouth.add(panelNote, BorderLayout.SOUTH);
		panelNote.setLayout(new GridLayout(1, 2, 0, 0));

		btnSave = new JButton("Save");
		btnSave.setFont(FontAttr.menuFont);
		btnSave.setActionCommand("save");
		btnSave.addActionListener(this);
		panelNote.add(btnSave);

		btnReset = new JButton("Reset");
		btnReset.setFont(FontAttr.menuFont);
		btnReset.setActionCommand("reset");
		btnReset.addActionListener(this);
		panelNote.add(btnReset);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (Exception e) {
						LogData.recordLog("error when refresh");
					}
					if (MainFrame.isChanged) {
						panelCenter.repaint();
						MainFrame.isChanged = false;
					}
				}
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			new NoteData(new DateAttr(), txtrNote.getText());
			break;
		case "reset":
			txtrNote.setText("Record what happened today");
			break;
		default:
			break;
		}
	}

}
