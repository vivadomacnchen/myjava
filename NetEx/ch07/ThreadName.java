package com.ch07;

public class ThreadName {
	public static void main(String[] args) {
		Thread thr = Thread.currentThread();
		System.out.println("�ثe������W��:"+thr.getName());
		thr.setName("DEMO");
		System.out.println("���᪺�W��:"+thr.getName());
	}
}

