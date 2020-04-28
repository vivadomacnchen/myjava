package com.chx;

import javax.swing.JOptionPane;

public class DialogTester {
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "這是訊息");
		JOptionPane.showMessageDialog(null, "這是訊息", "標題文字", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "這是訊息", "標題文字", JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null, "這是訊息", "標題文字", JOptionPane.PLAIN_MESSAGE);
		JOptionPane.showMessageDialog(null, "這是訊息", "標題文字", JOptionPane.WARNING_MESSAGE);
		JOptionPane.showMessageDialog(null, "這是訊息", "標題文字", JOptionPane.QUESTION_MESSAGE);
	}
}
