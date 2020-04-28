package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ListDataListener;
public class SwingFrame extends JFrame {
	JTextArea ta = new JTextArea(3, 10);
	ImageIcon icon = new ImageIcon("play.png");
	JButton b = new JButton("¼½©ñ", icon);
	public SwingFrame(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
//		Vector<String> items = new Vector<String>();
//		items.add("POP");
//		items.add("Classic");
//		items.add("Rock");
		String[] items = {"POP", "Classic", "Rock"};
		DefaultComboBoxModel model = new DefaultComboBoxModel(items);
		JComboBox cb = new JComboBox(model);
		Panel p = new Panel();
		p.add(b);
//		p.add(ta);
		p.add(cb);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new SwingFrame();
	}
}
