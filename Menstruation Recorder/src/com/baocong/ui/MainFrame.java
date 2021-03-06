package com.baocong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.baocong.data.FontAttr;
import com.baocong.data.LocalStorage;
import com.baocong.main.AppInfo;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	static boolean isChanged = false;

	public MainFrame() {
		setTitle("Menstruation Recorder " + AppInfo.APP_VERSION + " -- developed by " + AppInfo.AUTHOR_ABBR);
		setIconImage(Toolkit.getDefaultToolkit().getImage("res/icon.png"));
		setFont(FontAttr.menuFont);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new MenuPanel();
		menuBar.setFont(FontAttr.menuFont);
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel footer = new FooterPanel();
		contentPane.add(footer, BorderLayout.SOUTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOneTouchExpandable(true);
		contentPane.add(splitPane, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(FontAttr.menuFont);
		splitPane.setRightComponent(tabbedPane);

		JPanel welcome = new WelcomePanel();
		welcome.setBackground(Color.WHITE);
		tabbedPane.addTab(" Welcome ", null, welcome, null);

		JPanel calendar = new CalendarPanel();
		calendar.setBackground(Color.WHITE);
		tabbedPane.addTab(" Calendar ", null, calendar, null);

		JPanel graph = new GraphPanel();
		graph.setBackground(Color.WHITE);
		tabbedPane.addTab("   Graph   ", null, graph, null);

		JPanel notes = new NotesPanel();
		notes.setBackground(Color.WHITE);
		tabbedPane.addTab("   Notes   ", null, notes, null);

		JPanel log = new LogPabel();
		log.setBackground(Color.WHITE);
		tabbedPane.addTab("    Log    ", null, log, null);

		JPanel aside = new AsidePanel();
		splitPane.setLeftComponent(aside);

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				LocalStorage.readData();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				LocalStorage.saveData();
			}

		});
	}

}
