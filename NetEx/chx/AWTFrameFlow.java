package com.chx;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

public class AWTFrameFlow{
	
	public static void main(String[] args) {
		Button b1 = new Button("���s1");
		Button b2 = new Button("�r���h�����s2");
		Button b3 = new Button("�r�ܦh�ܦh�ܦh�����s3");
		Frame fm = new Frame();
		fm.setSize(280, 150);
		fm.setLayout(new FlowLayout());
		fm.add(b1);
		fm.add(b2);
		fm.add(b3);
		fm.setVisible(true);
	}

}
