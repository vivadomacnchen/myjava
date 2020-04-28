package com.ch12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(21);
		while(true){
			Socket socket = ss.accept();
			System.out.println("«È¤áºÝ³s½u:"+socket.getRemoteSocketAddress());
			FtpHandler server = new FtpHandler(socket);
			server.start();
		}
	}
}
