package com.ch04;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionHandling410 {
	public static void main(String[] args) {
		try {
			FileReader br = new FileReader("data.txt");
		} 
		catch (FileNotFoundException e) {
			System.out.println("�ɮפ��s�b,���ˬd�ɮצW��.");
		}
	}
}
