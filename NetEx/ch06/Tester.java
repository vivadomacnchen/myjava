package com.ch06;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Tester {
	public static void main(String[] args) throws IOException {
//		ServerSocket ss = new ServerSocket(80,5, InetAddress.getByName("192.168.1.10"));
//		ServerSocket ss = new ServerSocket(8888,5, InetAddress.getByName("192.168.1.10"));
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("�}�l��ť...");
		Socket socket = ss.accept();
		System.out.println("�w���Ȥ�ݳs�u...");
		
//		System.out.println("�j�wIP:"+ss.getInetAddress().getHostAddress());
//		System.out.println("�e��port:"+ss.getLocalPort());
		
	}
}
