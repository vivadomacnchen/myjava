package com.ch09;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class SimpleNio {
	public static void main(String[] args) throws IOException {
		SocketAddress addr = new InetSocketAddress("ptt.cc",23);
		SocketChannel channel = SocketChannel.open(addr);
		ByteBuffer buf = ByteBuffer.allocate(100);
		WritableByteChannel out = Channels.newChannel(System.out);
		channel.configureBlocking(false);
//		while(channel.read(buf)!=-1){
//			buf.flip();
//			out.write(buf);
//			buf.clear();
//		}
		while(true){
			int n = channel.read(buf);
//			System.out.println("***"+n);
			if (n>0){
				buf.flip();
				out.write(buf);
				buf.clear();
			}else if (n==-1){
				break;
			}
			
		}
	}
}
