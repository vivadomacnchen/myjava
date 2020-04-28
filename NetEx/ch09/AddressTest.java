package com.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class AddressTest {

	public static void main(String[] args) throws IOException {
		InetSocketAddress addr = new InetSocketAddress("ptt.cc", 23);
		SocketChannel chann = SocketChannel.open(addr);
		
	}

}
