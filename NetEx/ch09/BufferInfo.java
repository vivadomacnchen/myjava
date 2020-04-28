package com.ch09;

import java.nio.ByteBuffer;

public class BufferInfo {
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println("�e�q:"+buf.capacity());
		System.out.println("����:"+buf.limit());
		buf.put("abcxyz".getBytes());
		buf.mark();
		buf.put("GH".getBytes());
		buf.reset();
		System.out.println("��m:"+buf.position());
		
	}
}
