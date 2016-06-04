package com.baocong.main;

import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.UIManager;

import com.baocong.ui.MainFrame;

public class Application {

	public static final Point CENTER = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame frame = new MainFrame();
					frame.setBounds(CENTER.x - WIDTH / 2, CENTER.y - HEIGHT / 2, WIDTH, HEIGHT);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
