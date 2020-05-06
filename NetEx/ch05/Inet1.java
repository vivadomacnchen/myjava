package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet1 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("java.sun.com");//以網址取得IP位置
		System.out.println(addr);
		InetAddress local = InetAddress.getLocalHost();//取得主機端名稱與IP位置
		System.out.println(local);
	}
}
