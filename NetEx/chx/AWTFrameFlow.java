package com.chx;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

public class AWTFrameFlow{
	
	public static void main(String[] args) {
		Button b1 = new Button("按鈕1");
		Button b2 = new Button("字較多的按鈕2");
		Button b3 = new Button("字很多很多很多的按鈕3");
		Frame fm = new Frame();
		fm.setSize(280, 150);
		fm.setLayout(new FlowLayout());
		fm.add(b1);
		fm.add(b2);
		fm.add(b3);
		fm.setVisible(true);
	}

}
