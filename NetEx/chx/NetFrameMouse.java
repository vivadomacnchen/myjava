package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class NetFrameMouse extends JFrame {
	JTextField tf = new JTextField(10);
	JButton b = new JButton("連 線");
	public NetFrameMouse(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
		//註冊傾聽者物件
		b.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.out.println("放開");
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("按下");
			}
		});
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				System.out.println("打出字元:"+c);
			}
		});
		
		Panel p = new Panel();
		p.add(tf);
		p.add(b);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new NetFrameMouse();
	}
}
