package com.ch09;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferTester {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		byte[] bb = "abcxyz".getBytes();
		buf.put(bb);
		ByteBuffer buf2 = ByteBuffer.allocate(10);
		buf2.put(buf);
		
//		buf.put(bb);
		
		
		
		show(buf2);
		//
		buf.flip();
		while(buf.hasRemaining()){
			System.out.print((char)buf.get());
		}
		System.out.println();
		show(buf);
	}
	public static void show(ByteBuffer buf){
		System.out.print("位置:"+buf.position()+"\t");
		System.out.print("限制:"+buf.limit()+"\t");
		System.out.println("容量:"+buf.capacity());
//		System.out.println("0123456789");
	}
}
