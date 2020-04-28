package com.ch10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EchoHandler extends Thread {
	SocketChannel channel;
	public static final int IAC = 255;
	public static final int WILL = 251;
	public static final int WONT = 252;
	public static final int DO = 253;
	public static final int DONT = 254;
	public EchoHandler(SocketChannel channel) {
		this.channel = channel;
	}

	public void run() {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		try {
			buff.clear();
			channel.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				byte b = buff.get();
				int data = b & 0xFF;
				System.out.print(data + ",");
			}
//			while (true) {
//				buff.clear();
//				channel.read(buff);
//				buff.flip();
//				while (buff.hasRemaining()) {
//					byte b = buff.get();
//					int data = b & 0xFF;
//					System.out.print(data + ",");
//				}
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String text) {
		ByteBuffer buff = ByteBuffer.allocate(1024);
		buff.put(text.getBytes());
		buff.flip();
		try {
			channel.write(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleCommand(ByteBuffer buf) throws IOException {
		ByteBuffer outBuf = ByteBuffer.allocate(1024);
		int tone = buf.get() & 0xFF;
		int option = buf.get() & 0xFF;
		outBuf.clear();
		if (tone == DO) {
			outBuf.put((byte) IAC);
			outBuf.put((byte) WONT);
			outBuf.put((byte) option);
			outBuf.flip();
			channel.write(outBuf);
		} else if (tone == WILL) {
			outBuf.put((byte) IAC);
			outBuf.put((byte) DONT);
			outBuf.put((byte) option);
			outBuf.flip();
			channel.write(outBuf);
		}
	}
}
