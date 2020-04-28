package com.ch10;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextAppender extends Thread {
	JTextArea ta;
	public TextAppender(JTextArea ta){
		this.ta = ta;
	}
	public void run(){
		for (int i=0; i<50; i++){
			String s = String.valueOf((char)(i+65));
			ta.append(s);
			ta.setCaretPosition(ta.getText().length());
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
