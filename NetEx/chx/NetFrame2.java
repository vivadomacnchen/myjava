package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListDataListener;
public class NetFrame2 extends JFrame {
	JTextField tf = new JTextField(10);
	JButton b = new JButton("�s �u");
	InnerListener listener = new InnerListener();
	public NetFrame2(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
		//���U��ť�̪���
		b.addActionListener(listener);
		Panel p = new Panel();
		p.add(tf);
		p.add(b);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	class InnerListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("Inner:Action�ƥ�o��");
		}
	}
	
	public static void main(String[] args) {
		new NetFrame2();
	}
}
