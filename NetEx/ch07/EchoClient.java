package com.ch07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 3333);
			BufferedReader in = new BufferedReader(new 
					InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(
					new OutputStreamWriter(s.getOutputStream()));
			//�e�X"Hello"�r��
			out.println("Hello");
			out.flush();
			System.out.println("�w�e�XHello");
			//���y5��,�i�̻ݨD�[���ɶ�
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Ū�����A��EchoServer�e�Ӫ��^���r��,�æL�X
			String rec = in.readLine();
			System.out.println("���A���Ǩ�:"+rec);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
