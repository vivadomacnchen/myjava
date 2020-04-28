package com.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSelector {

	public static void main(String[] args) throws IOException {
		SocketAddress addr = new InetSocketAddress("ptt.cc",23);
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(addr);
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ);
		while (selector.select(500) > 0) {
			Set readyKeys = selector.selectedKeys(); 
			Iterator readyItor = readyKeys.iterator(); 
			while (readyItor.hasNext())
			{
				SelectionKey key = (SelectionKey)readyItor.next();
				readyItor.remove();//�ȥ��O�o�o�@��
				SocketChannel keyChannel = (SocketChannel)key.channel();
				if (key.isConnectable()) {
					//�b�o�سB�zOP_CONNECT�ƥ�
//					System.out.println("connected");
				} else if (key.isReadable()) {
					//�b�o�سB�zOP_READ�ƥ�
					System.out.println("read...");
				}
			}
		}
	}
}
