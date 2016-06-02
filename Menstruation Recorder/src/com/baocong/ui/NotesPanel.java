package com.baocong.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.baocong.data.FontAttr;
import com.baocong.data.LogData;
import com.baocong.data.NoteData;

public class NotesPanel extends JPanel implements Runnable {

	private JTextArea textNotes;
	private String note = "";
	private int length;

	public NotesPanel() {
		setLayout(new BorderLayout(0, 0));

		textNotes = new JTextArea(NoteData.showNotes());
		textNotes.setFont(FontAttr.menuFont);

		JScrollPane scrollPane = new JScrollPane(textNotes);
		add(scrollPane, BorderLayout.CENTER);

		length = note.length();

		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				LogData.recordLog("error in NotesPanel thread");
			}
			if ((note = NoteData.showNotes()).length() != length) {
				textNotes.setText(note);
				length = note.length();
			}
		}
	}

}
