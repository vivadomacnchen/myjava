package com.chx;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
public class AWTFrameGrid extends Frame{
	Button b1 = new Button("1");
	Button b2 = new Button("2");
	Button b3 = new Button("3");
	Button b4 = new Button("4");
	Button b5 = new Button("5");
	Button b6 = new Button("6");
	public AWTFrameGrid(){
		setSize(300,200);
		GridLayout grid = new GridLayout(2,3);
		setLayout(grid);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		setVisible(true);
	}
	public static void main(String[] args) {
		new AWTFrameGrid();
	}
}
