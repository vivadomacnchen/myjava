package com.ch09;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class BufferDirect {
	public static void main(String[] args) {
		//�����w�İ�
		ByteBuffer buf = ByteBuffer.allocate(20);
		System.out.println("�O�_�O�����w�İ�?"+buf.isDirect());
		CharBuffer cbuf = CharBuffer.allocate(20);
		System.out.println("�O�_�O�����w�İ�?"+cbuf.isDirect());
		//�����w�İ�
		ByteBuffer dbuf = ByteBuffer.allocateDirect(20);
		System.out.println("�O�_�O�����w�İ�?"+dbuf.isDirect());
	}
}
