package com.ch07;

public class Racing {
	public static void main(String[] args) {
		int h1 = 0;
		//����Horse����ñҰʰ����
		Horse h2 = new Horse();
		h2.start();
		for (int i=0; i<5000; i++){
			h1++;
			System.out.println("H1:"+h1);
		}
	}
}


