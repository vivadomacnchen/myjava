package com.ch04;

import java.io.Console;

public class Console415 {
	public static void main(String[] args) {
		Console cs = System.console();
		System.out.println("�п�J�b��:");
		String id = cs.readLine();
		System.out.println("�п�J�K�X:");
		String pw = new String(cs.readPassword());
		//��ܩҿ�J�����
		System.out.println("�b��:"+id);
		System.out.println("�K�X:"+pw);
	}
}
