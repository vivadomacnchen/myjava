package com.chx;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class NetFrame extends JFrame {
	JTextField tf = new JTextField(10);
	JButton b = new JButton("連 線");
	MyListener listener = new MyListener();
	public NetFrame(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
		//註冊傾聽者物件
		b.addActionListener(listener);
		Panel p = new Panel();
		p.add(tf);
		p.add(b);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new NetFrame();
	}
}
