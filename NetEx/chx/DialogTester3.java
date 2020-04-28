package com.chx;

import javax.swing.JOptionPane;

public class DialogTester3 {
	public static void main(String[] args) {
		String text = JOptionPane.showInputDialog("請輸入帳號:");
		System.out.println("使用者輸入:"+text);
		text = JOptionPane.showInputDialog("請輸入帳號:", "jack");
	}
}
