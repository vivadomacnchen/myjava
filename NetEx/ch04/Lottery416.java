package com.ch04;

import java.util.Scanner;

public class Lottery416 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("�п�J�m�W:");
		String name = scanner.next();
		System.out.println("�п�J���Ӽֳz�Ʀr(�H�ťլ����j):");
		int[] num = new int[5];
		for (int i=0; i<num.length; i++){
			num[i] = scanner.nextInt();
			System.out.println("�A��F..."+num[i]);
		}
	}
}
