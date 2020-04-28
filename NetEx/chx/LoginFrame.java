package com.chx;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class LoginFrame extends JFrame{
	JButton b1 = new JButton("功能1");
	JButton b2 = new JButton("功能2");
	JTextField tf = new JTextField(10);
	public LoginFrame(){
		setSize(180,150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new FlowLayout());
		add(b1);
		add(b2);
		add(tf);
		setVisible(true);
	}
	public static void main(String[] args) {
		new LoginFrame();
	}
}
