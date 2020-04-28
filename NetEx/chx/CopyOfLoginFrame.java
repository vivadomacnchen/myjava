package com.chx;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class CopyOfLoginFrame extends JFrame{
	JButton b1 = new JButton("功能1");
	JButton b2 = new JButton("功能2");
	JTextField tf = new JTextField(10);
	Label lb = new Label("標籤文字", Label.RIGHT);
	Button b = new Button("按鈕文字");
	TextField t = new TextField("預設文字", 10);
	
	public CopyOfLoginFrame(){
	  setSize(370,150);
		setLayout(new FlowLayout());
		t.setEchoChar('#');
		lb.setBackground(Color.yellow);
		lb.setPreferredSize(new Dimension( 80,20));
//		add(lb);
		add(t);
		add(b1);
		add(b2);
		add(tf);
		setVisible(true);
	}
	public static void main(String[] args) {
		new CopyOfLoginFrame();
	}
}
