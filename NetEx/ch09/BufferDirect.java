package com.ch09;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferDirect {
	public static void main(String[] args) {
		//間接緩衝區
		ByteBuffer buf = ByteBuffer.allocate(20);
		System.out.println("是否是直接緩衝區?"+buf.isDirect());
		CharBuffer cbuf = CharBuffer.allocate(20);
		System.out.println("是否是直接緩衝區?"+cbuf.isDirect());
		//直接緩衝區
		ByteBuffer dbuf = ByteBuffer.allocateDirect(20);
		System.out.println("是否是直接緩衝區?"+dbuf.isDirect());
	}
}
