package com.ch05;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet4 {
	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr[] = InetAddress.getAllByName("www.google.com.tw");//���������W�i��֦�����@�Ӫ�IP��}�A�i�H�ϥ�getAllByName()��k���^�Ҧ������}��T�A�o�|�Ǧ^InetAddress����}�C�A�i�H�ϥΰj��N�o�Ǫ���@�@���X
		for (int i=0; i<addr.length; i++){
			System.out.println(addr[i]);
		}
	}
}
