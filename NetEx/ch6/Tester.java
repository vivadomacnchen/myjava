package com.ch6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class Tester {
	public static void main(String[] args) {

		try {
			FileInputStream fis;
			fis = new FileInputStream("test.txt");
			System.out.println(fis.available());
			FileChannel chan = fis.getChannel();
			ByteBuffer buf = ByteBuffer.allocate(10);
			int n = chan.read(buf);
			System.out.println(n);
			info(buf);
			buf.rewind();
			info(buf);
			for (int i = 0; i < buf.capacity(); i++) {
				System.out.println((char)buf.get());
				info(buf);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("檔案讀取錯誤發生");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("輸出入錯誤發生");
		}
	}

	public static void info(ByteBuffer buf) {
		System.out.println("position:" + buf.position() + ",limit:" + buf.limit()
				+ ",capacity:" + buf.capacity());
	}
}
