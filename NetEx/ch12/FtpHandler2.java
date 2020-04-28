package com.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FtpHandler2 {
	BufferedReader in = null;
	PrintWriter out = null;
	String directory = "/";
	public FtpHandler2(Socket s){
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream());
		} catch (IOException e) {
			System.out.println("資料串流輸出入錯誤");
		}
	}
	public void run(){
		
	}
	public void reply(String code, String msg){
		out.println(code+" "+msg);
		out.flush();
		System.out.println("回應:"+code+" "+msg);
	}
}
