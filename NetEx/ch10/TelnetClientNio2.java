package com.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TelnetClientNio2 extends Thread {
	private String host;
	private int port;
	SocketChannel channel;
	public static final int IAC = 255;
	public static final int WILL = 251;
	public static final int WONT = 252;
	public static final int DO = 253;
	public static final int DONT = 254;
	DataListener dataListener;
	
	public TelnetClientNio2(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() {
		try {
			InetSocketAddress addr = new InetSocketAddress("localhost", 23);
			channel = SocketChannel.open(addr);
			UserInputThread user = new UserInputThread(channel);
			user.start();
			ByteBuffer buf = ByteBuffer.allocate(1024);
			while (true) {
				buf.clear();
				channel.read(buf);
				System.out.println("(緩衝區內的有效資料個數:" + buf.position()+")");
				buf.flip();
				while (buf.hasRemaining()) {
					byte b = buf.get();
					int data = b & 0xFF;
					if (data == IAC) {
						handleCommand(buf);
					} else {
//						System.out.print((char) data);
						if (dataListener!=null){
							String s = String.valueOf((char)data);
							System.out.print(s);
							dataListener.dataRecievd(s);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleCommand(ByteBuffer buf)
			throws IOException {
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

	public void run() {
		connect();
	}
	
	public DataListener getDataListener() {
		return dataListener;
	}

	public void setDataListener(DataListener dataListener) {
		this.dataListener = dataListener;
	}

	public static void main(String[] args) {
		TelnetClientNio2 client = new TelnetClientNio2("localhost", 23);
		client.connect();
	}

	public void userInputed(char c) {
		ByteBuffer outBuf = ByteBuffer.allocate(1024);
		outBuf.put((byte)c);
		if (c == 10)
			outBuf.put((byte)13);
		outBuf.flip();
		try {
			channel.write(outBuf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
