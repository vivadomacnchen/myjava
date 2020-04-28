package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import javax.swing.JFrame;
public class EditorFrameSwing extends JFrame {
	Button bOpen = new Button("Open");
	Button bSave = new Button("Save");
	Button bExit = new Button("Exit");
	TextArea ta = new TextArea();
	public EditorFrameSwing(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,250);
		Panel p = new Panel();
		p.add(bOpen);
		p.add(bSave);
		p.add(bExit);
		c.add(p, BorderLayout.NORTH);
		c.add(ta, BorderLayout.CENTER);
		setVisible(true);
	}
	public static void main(String[] args) {
		new EditorFrameSwing();
	}
}
