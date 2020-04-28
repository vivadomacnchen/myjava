package com.ch6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class SimpleFileRead {
	public static void main(String[] args) {
		FileInputStream fis;
//		FileReader fis;
		try {
			Charset charset = Charset.forName("UTF-8"); 
			CharsetDecoder decoder = charset.newDecoder();
			CharsetEncoder encoder = charset.newEncoder();
			Charset cs = Charset.forName("MS950");
			fis = new FileInputStream("test.txt");
//			fis = new FileReader("test.txt");
			FileChannel chan = fis.getChannel();
//			ByteBuffer buf = decoder.decode(ByteBuffer.allocate(50));
			ByteBuffer buf = ByteBuffer.allocate(50);
			chan.read(buf);
			buf.rewind();
			CharBuffer chbuf = cs.decode(buf); 
			chbuf.rewind();
			System.out.println(chbuf.capacity());
			for (int i=0;i<chbuf.length(); i++)
				System.out.println(chbuf.get());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("檔案讀取錯誤發生");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("輸出入錯誤發生");
		}
	}
}
