package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.event.ListDataListener;
public class SwingFrame2 extends JFrame {
	public SwingFrame2(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
//		Vector<String> items = new Vector<String>();
//		items.add("POP");
//		items.add("Classic");
//		items.add("Rock");
//		DefaultComboBoxModel model = new DefaultComboBoxModel(items);
//		JComboBox cb = new JComboBox(model);
		
//		String[] items = {"POP", "Classic", "Rock"};
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("POP");
		listModel.addElement("Classic");
		listModel.addElement("Rock");
		JList list = new JList(listModel);
		
		Panel p = new Panel();
		p.add(list);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new SwingFrame2();
	}
}
