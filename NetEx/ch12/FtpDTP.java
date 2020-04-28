package com.ch12;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FtpDTP extends Thread {
	ServerSocket dataServer = null;
	public FtpDTP(ServerSocket dataServer){
		this.dataServer = dataServer;
	}
	public void run(){
		try {
			System.out.println("waiting for data connection...");
			Socket s = dataServer.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("socket connected");
	}
}
