package com.ch10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServerNio {
	int port = 9988;
	public EchoServerNio(int port){
		this.port = port;
	}
	
	public void accept(){
		try {
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			ServerSocket ss = serverChannel.socket();
			ss.bind(new InetSocketAddress(port));
			serverChannel.configureBlocking(false);
			Selector selector = Selector.open();
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("伺服器等待連線中("+ss.getLocalPort()+")");
			while(true){
				selector.select();
				Set keys = selector.selectedKeys();
				Iterator it = keys.iterator();
				while(it.hasNext()){
					SelectionKey key = (SelectionKey)it.next();
					it.remove();
					if (key.isAcceptable()){
						System.out.println("用戶連上線");
						ServerSocketChannel server = (ServerSocketChannel)key.channel();
						SocketChannel client = server.accept();
						client.configureBlocking(false);
						SelectionKey clientKey = client.register(selector, SelectionKey.OP_READ );
						ByteBuffer buff = ByteBuffer.allocate(100);
			            clientKey.attach(buff);
					}
					else if (key.isReadable()){
						System.out.print("用戶通道資料讀取中...");
						SocketChannel client = (SocketChannel)key.channel();
						ByteBuffer buff = (ByteBuffer) key.attachment();
						//讀取從用戶端傳來的資料,並印出
						int count = client.read(buff);
						System.out.println((char)buff.get(0));
						buff.flip();
						//送出剛收到的資料
						client.write(buff);
						buff.clear();
					}
				}
			}
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//測試EchoServerNio,並傾聽9988埠
		new EchoServerNio(9988).accept();
	}
}
