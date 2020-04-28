package com.ch10;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class UserInputThread extends Thread {
	SocketChannel channel;

	public UserInputThread(SocketChannel channel) {
		this.channel = channel;
	}

	public void run() {
		ByteBuffer outBuf = ByteBuffer.allocate(1024);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				outBuf.clear();
				String line = in.readLine();
				line = line + "\n\r";
				outBuf.put(line.getBytes());
				outBuf.flip();
				channel.write(outBuf);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
