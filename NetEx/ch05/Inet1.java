package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet1 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getByName("java.sun.com");//�H���}���oIP��m
		System.out.println(addr);
		InetAddress local = InetAddress.getLocalHost();//���o�D���ݦW�ٻPIP��m
		System.out.println(local);
	}
}
