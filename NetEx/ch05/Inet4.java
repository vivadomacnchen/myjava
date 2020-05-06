package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet4 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr[] = InetAddress.getAllByName("www.google.com.tw");//有的網站上可能擁有不止一個的IP位址，可以使用getAllByName()方法取回所有的網址資訊，這會傳回InetAddress物件陣列，可以使用迴圈將這些物件一一取出
		for (int i=0; i<addr.length; i++){
			System.out.println(addr[i]);
		}
	}
}
