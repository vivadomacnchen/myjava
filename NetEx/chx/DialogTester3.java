package com.chx;

import javax.swing.JOptionPane;

public class DialogTester3 {
	public static void main(String[] args) {
		String text = JOptionPane.showInputDialog("�п�J�b��:");
		System.out.println("�ϥΪ̿�J:"+text);
		text = JOptionPane.showInputDialog("�п�J�b��:", "jack");
	}
}
