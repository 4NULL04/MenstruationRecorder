package com.baocong.ui;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import com.baocong.data.FileData;
import com.baocong.data.FontAttr;
import com.baocong.data.LocalStorage;
import com.baocong.data.LogData;

public class MenuPanel extends JMenuBar implements ActionListener {

	public MenuPanel() {
		// Menu
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(FontAttr.menuFont);
		mnMenu.setMnemonic('M');
		add(mnMenu);

		// Sub new
		JMenu mnNew = new JMenu("New");
		mnNew.setFont(FontAttr.menuFont);
		mnNew.setMnemonic('N');
		mnMenu.add(mnNew);

		JMenuItem mntmNewM = new JMenuItem("New Menstruation Data");
		mntmNewM.setFont(FontAttr.menuFont);
		mntmNewM.setMnemonic('M');
		mntmNewM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewM.setActionCommand("newM");
		mntmNewM.addActionListener(this);
		mnNew.add(mntmNewM);

		JMenuItem mntmNewN = new JMenuItem("New Note Data");
		mntmNewN.setFont(FontAttr.menuFont);
		mntmNewN.setMnemonic('N');
		mntmNewN.setActionCommand("newN");
		mntmNewN.addActionListener(this);
		mnNew.add(mntmNewN);

		// Sub open
		JMenu mnOpen = new JMenu("Open");
		mnOpen.setFont(FontAttr.menuFont);
		mnOpen.setMnemonic('O');
		mnMenu.add(mnOpen);

		JMenuItem mntmOpenM = new JMenuItem("Open Menstruation Data");
		mntmOpenM.setFont(FontAttr.menuFont);
		mntmOpenM.setMnemonic('M');
		mntmOpenM.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmOpenM.setActionCommand("openM");
		mntmOpenM.addActionListener(this);
		mnOpen.add(mntmOpenM);

		JMenuItem mntmOpenN = new JMenuItem("Open Note Data");
		mntmOpenN.setFont(FontAttr.menuFont);
		mntmOpenN.setMnemonic('N');
		mntmOpenN.setActionCommand("openN");
		mntmOpenN.addActionListener(this);
		mnOpen.add(mntmOpenN);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setFont(FontAttr.menuFont);
		mntmSave.setMnemonic('S');
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setActionCommand("save");
		mntmSave.addActionListener(this);
		mnMenu.add(mntmSave);

		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.setFont(FontAttr.menuFont);
		mntmSaveAs.setMnemonic('A');
		mntmSaveAs.setActionCommand("saveas");
		mntmSaveAs.addActionListener(this);
		mnMenu.add(mntmSaveAs);

		JSeparator separator_1 = new JSeparator();
		mnMenu.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setFont(FontAttr.menuFont);
		mntmExit.setMnemonic('E');
		mntmExit.setActionCommand("exit");
		mntmExit.addActionListener(this);
		mnMenu.add(mntmExit);

		// Option
		JMenu mnOption = new JMenu("Option");
		mnOption.setFont(FontAttr.menuFont);
		mnOption.setMnemonic('O');
		add(mnOption);

		JMenuItem mntmImport = new JMenuItem("Import Graph");
		mntmImport.setFont(FontAttr.menuFont);
		mntmImport.setMnemonic('I');
		mntmImport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmImport.setActionCommand("import");
		mntmImport.addActionListener(this);
		mnOption.add(mntmImport);

		JMenuItem mntmOutput = new JMenuItem("Output Graph");
		mntmOutput.setFont(FontAttr.menuFont);
		mntmOutput.setMnemonic('D');
		mntmOutput.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmOutput.setActionCommand("output");
		mntmOutput.addActionListener(this);
		mnOption.add(mntmOutput);

		// Help
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(FontAttr.menuFont);
		mnHelp.setMnemonic('H');
		add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setFont(FontAttr.menuFont);
		mntmHelp.setMnemonic('H');
		mntmHelp.setActionCommand("help");
		mntmHelp.addActionListener(this);
		mnHelp.add(mntmHelp);

		JSeparator separator_2 = new JSeparator();
		mnHelp.add(separator_2);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setFont(FontAttr.menuFont);
		mntmAbout.setMnemonic('A');
		mntmAbout.setActionCommand("about");
		mntmAbout.addActionListener(this);
		mnHelp.add(mntmAbout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		// Menu
		case "newM":
		case "newN":
			newAction(e.getActionCommand());
			break;
		case "openM":
		case "openN":
			openAction(e.getActionCommand());
			break;
		case "save":
			saveAction();
			break;
		case "saveas":
			saveAsAction();
			break;
		case "exit":
			System.exit(0);
			break;

		// Option
		case "import":
			importAction();
			break;
		case "output":
			outputAction();
			break;

		// Help
		case "help":
			helpAction();
			break;
		case "about":
			aboutAction();
			break;

		default:
			break;
		}
	}

	// Menu
	private void newAction(String cmd) {
		JFileChooser fileChooser = new JFileChooser("data/");
		fileChooser.setDialogTitle("New");
		fileChooser.setFileFilter(new Filter());
		fileChooser.showDialog(getParent(), "New");
		fileChooser.setVisible(true);

		LocalStorage.saveData();
		if (cmd.equals("newM"))
			FileData.setmUrl(fileChooser.getSelectedFile().getAbsolutePath() + FileData.MENSTRUATION_DATA);
		else if (cmd.equals("newN"))
			FileData.setnUrl(fileChooser.getSelectedFile().getAbsolutePath() + FileData.NOTE_DATA);
		else
			LogData.recordLog("error when creating new files");
		LocalStorage.writeBasicData();
		LocalStorage.readData();
	}

	private void openAction(String cmd) {
		JFileChooser fileChooser = new JFileChooser("data/");
		fileChooser.setDialogTitle("Open");
		fileChooser.setFileFilter(new Filter());
		fileChooser.showOpenDialog(getParent());
		fileChooser.setVisible(true);

		if (cmd.equals("openM"))
			FileData.setmUrl(fileChooser.getSelectedFile().getAbsolutePath());
		else if (cmd.equals("openN"))
			FileData.setnUrl(fileChooser.getSelectedFile().getAbsolutePath());
		else
			LogData.recordLog("error when opening files");

		LocalStorage.saveData();
		LocalStorage.readData();
	}

	private void saveAction() {
		LocalStorage.saveData();
	}

	private void saveAsAction() {
		JFileChooser fileChooser = new JFileChooser("data/");
		fileChooser.setDialogTitle("Save As");
		fileChooser.setFileFilter(new Filter());
		fileChooser.showSaveDialog(getParent());
		fileChooser.setVisible(true);

		FileData.setmUrl(fileChooser.getSelectedFile().getAbsolutePath() + FileData.MENSTRUATION_DATA);
		FileData.setnUrl(fileChooser.getSelectedFile().getAbsolutePath() + FileData.NOTE_DATA);
		LocalStorage.saveData();
	}

	// Option
	private void importAction() {
	}

	private void outputAction() {
	}

	// Help
	private void helpAction() {
		Desktop desktop = Desktop.getDesktop();
		try {
			desktop.open(new File("help/MRHelp.chm"));
		} catch (IOException e) {
			LogData.recordLog("error when open help manual");
		}
	}

	private void aboutAction() {
		String message = "Menstruation Recorder\n\n" + "Version: 1.1.12 beta\n\n"
				+ "(C) Copyright Menstruation Recorder contributors and others 2016. All rights reserved.\n"
				+ "This software was designed for personal using, do not use it in commercial purpose.\n"
				+ "All the data stored in local storage were private. Peeking without permission was illegal.\n\n";
		JOptionPane.showMessageDialog(getParent(), message, "About", JOptionPane.PLAIN_MESSAGE);
	}

	private class Filter extends FileFilter {

		@Override
		public String getDescription() {
			return "*.men;*.note";
		}

		@Override
		public boolean accept(File f) {
			return f.getName().endsWith(FileData.MENSTRUATION_DATA) || f.getName().endsWith(FileData.NOTE_DATA);
		}

	}

}
