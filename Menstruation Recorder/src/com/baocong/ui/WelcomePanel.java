package com.baocong.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.baocong.main.AppInfo;

public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 568777379666428040L;

	public WelcomePanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));

		JLabel lblCenter = new JLabel("Welcome!");
		lblCenter.setHorizontalAlignment(SwingConstants.CENTER);
		lblCenter.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 90));
		add(lblCenter, BorderLayout.CENTER);

		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(Color.WHITE);
		add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblFirst = new JLabel(
				"This software is designed for personal using, do not use it in commercial purpose.");
		lblFirst.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblFirst.setHorizontalAlignment(SwingConstants.CENTER);
		panelFooter.add(lblFirst);

		JLabel lblSecond = new JLabel(AppInfo.COPYRIGHT);
		lblSecond.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSecond.setHorizontalAlignment(SwingConstants.CENTER);
		panelFooter.add(lblSecond);
	}

}
