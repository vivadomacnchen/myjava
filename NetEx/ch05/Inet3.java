package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet3 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println(addr);
	}
}
