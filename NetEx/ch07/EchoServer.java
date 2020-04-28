package com.ch07;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(3333);
			while (true) {
				System.out.println("�����s�u��");
				Socket socket = server.accept();
				//��ѫ���]�p��Echo�B�z�����
				EchoThread echo = new EchoThread(socket);
				echo.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("���A�����f�o�Ϳ��~");
		}
	}
}
