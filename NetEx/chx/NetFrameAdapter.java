package com.chx;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
public class NetFrameAdapter extends JFrame {
	JTextField tf = new JTextField(10);
	JButton b = new JButton("�s �u");
	public NetFrameAdapter(){
		Container c = getContentPane();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,150);
		//���U��ť�̪���
		b.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				System.out.println("��}");
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("���U");
			}
			public void mouseExited(MouseEvent e) {
				System.out.println("���}");
			}
			public void mouseEntered(MouseEvent e) {
				System.out.println("�i�J");
			}
			public void mouseClicked(MouseEvent e) {
				System.out.println("�w��");
			}
		});
		tf.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				System.out.println("���X�r��:"+c);
			}
			public void keyReleased(KeyEvent evt) {
				System.out.println("����w��}");
			}
			public void keyPressed(KeyEvent evt) {
				System.out.println("����U����");
			}
		});
		
		Panel p = new Panel();
		p.add(tf);
		p.add(b);
		c.add(p, BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		new NetFrameAdapter();
	}
}
