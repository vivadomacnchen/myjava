package com.ch07;

public class Horse extends Thread{
	//�мgThread��krun()
	public void run(){
		//��1�]��5000
		int h = 0;
		for (int i=0; i<5000; i++){
			h++;
			System.out.println(getName()+":"+h);
		}
	}
}

