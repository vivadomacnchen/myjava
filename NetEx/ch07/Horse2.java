package com.ch07;

public class Horse2 extends Thread {
	// �мgThread��krun()
	public void run() {
		try {
			sleep(2000);
			System.out.println("��F���I");
		} catch (InterruptedException e) {
			System.out.println("�Q���_�F");
		}
	}
}
