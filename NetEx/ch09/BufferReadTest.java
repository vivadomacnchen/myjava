package com.ch09;

import java.nio.ByteBuffer;

public abstract class BufferReadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.put("abcxyz".getBytes());
		System.out.println("�e�q:"+buf.capacity());
		System.out.println("����:"+buf.limit());
		System.out.println("��m:"+buf.position());
		buf.flip();
		System.out.println("�e�q:"+buf.capacity());
		System.out.println("����:"+buf.limit());
		System.out.println("��m:"+buf.position());
		while(buf.hasRemaining()){
			System.out.print((char)buf.get());
		}
//		byte[] bb = new byte[buf.limit()];
//		buf.get(bb);
//		System.out.println(new String(bb));
		
	}

}
