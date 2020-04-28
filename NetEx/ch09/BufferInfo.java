package com.ch09;

import java.nio.ByteBuffer;

public class BufferInfo {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println("容量:"+buf.capacity());
		System.out.println("限制:"+buf.limit());
		buf.put("abcxyz".getBytes());
		buf.mark();
		buf.put("GH".getBytes());
		buf.reset();
		System.out.println("位置:"+buf.position());
		
	}
}
