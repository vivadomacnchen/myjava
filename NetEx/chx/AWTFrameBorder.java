package com.chx;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
public class AWTFrameBorder extends Frame{
	Button b1 = new Button("����");
	Button b2 = new Button("�_��");
	Button b3 = new Button("�n��");
	Button b4 = new Button("����");
	Button b5 = new Button("�F��");
	public AWTFrameBorder(){
		setSize(300,200);
		add("Center", b1);
		add("North", b2);
		add("South", b3);
		add("West", b4);
		add("East", b5);
		setVisible(true);
	}
	public static void main(String[] args) {
		new AWTFrameBorder();
	}
}
