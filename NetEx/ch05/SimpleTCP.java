package com.ch05;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleTCP {
	public static void main(String[] args) {
		try {
			Socket ptt = new Socket("ptt.cc",23);
			InputStream in = ptt.getInputStream();
			OutputStream out = ptt.getOutputStream();
		} catch (UnknownHostException e) {
			System.out.println("�D���s�u����");
		} catch (IOException e) {
			System.out.println("�ǿ饢��");
		}
	}
}
