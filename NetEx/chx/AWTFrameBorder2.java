package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
public class AWTFrameBorder2 extends Frame{
	Button b1 = new Button("中間");
	Button b2 = new Button("北邊");
	Button b3 = new Button("南邊");
	Button b4 = new Button("西邊");
	Button b5 = new Button("東邊");
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
