package com.ch09;

import java.nio.ByteBuffer;

public abstract class BufferReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.put("abcxyz".getBytes());
		System.out.println("容量:"+buf.capacity());
		System.out.println("限制:"+buf.limit());
		System.out.println("位置:"+buf.position());
		buf.flip();
		System.out.println("容量:"+buf.capacity());
		System.out.println("限制:"+buf.limit());
		System.out.println("位置:"+buf.position());
		while(buf.hasRemaining()){
			System.out.print((char)buf.get());
		}
//		byte[] bb = new byte[buf.limit()];
//		buf.get(bb);
//		System.out.println(new String(bb));
		
	}

}
