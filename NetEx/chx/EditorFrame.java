package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
public class EditorFrame extends Frame {
	Panel p = new Panel();
	Button bOpen = new Button("Open");
	Button bSave = new Button("Save");
	Button bExit = new Button("Exit");
	TextArea ta = new TextArea();
	public EditorFrame(){
		setSize(300,250);
		p.add(bOpen);
		p.add(bSave);
		p.add(bExit);
		add(p, BorderLayout.NORTH);
		add(ta, BorderLayout.CENTER);
		setVisible(true);
	}
	public static void main(String[] args) {
		new EditorFrame();
	}
}
