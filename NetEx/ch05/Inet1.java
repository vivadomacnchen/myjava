package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet1 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("java.sun.com");
		System.out.println(addr);
		InetAddress local = InetAddress.getLocalHost();
		System.out.println(local);
	}
}
