package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet4 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr[] = InetAddress.getAllByName("core2");
		for (int i=0; i<addr.length; i++){
			System.out.println(addr[i]);
		}
	}
}
