package com.ch07;

public class Wizard2 extends Thread {
	public void run() {
		synchronized (this) {
			System.out.println("THUNDER!!");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("END");
		}
	}
	
	public static void main(String[] args) {
		Wizard2 wizard = new Wizard2();
		Thread thr1 = new Thread(wizard);
		thr1.start();
		Thread thr2 = new Thread(wizard);
		thr2.start();
	}
}
