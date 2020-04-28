package com.chx;
import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
public class AWTFrameBorder extends Frame{
	Button b1 = new Button("中間");
	Button b2 = new Button("北邊");
	Button b3 = new Button("南邊");
	Button b4 = new Button("西邊");
	Button b5 = new Button("東邊");
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
