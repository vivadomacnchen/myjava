package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
public class AWTFrameBorder2 extends Frame{
	Button b1 = new Button("����");
	Button b2 = new Button("�_��");
	Button b3 = new Button("�n��");
	Button b4 = new Button("����");
	Button b5 = new Button("�F��");
	public AWTFrameBorder2(){
		setSize(300,200);
		add(b1, BorderLayout.CENTER);
		add(b2, BorderLayout.NORTH);
		add(b3, BorderLayout.SOUTH);
		add(b4, BorderLayout.WEST);
		add(b5, BorderLayout.EAST);
		setVisible(true);
	}
	public static void main(String[] args) {
		new AWTFrameBorder2();
	}
}
