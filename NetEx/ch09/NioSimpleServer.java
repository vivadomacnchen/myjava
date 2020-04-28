package com.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioSimpleServer {
  public static void main(String[] args)
      throws IOException {
    ServerSocketChannel serverChannel = ServerSocketChannel
        .open();
    ServerSocket ss = serverChannel.socket();
    ss.bind(new InetSocketAddress(9950));
    serverChannel.configureBlocking(false);
    Selector selector = Selector.open();
    serverChannel.register(selector,
        SelectionKey.OP_ACCEPT);
    while (true) {
      selector.select();
      Set keys = selector.selectedKeys();
      Iterator it = keys.iterator();
      while (it.hasNext()) {
        SelectionKey key = (SelectionKey) it.next();
        it.remove();
        if (key.isAcceptable()) {
          System.out.println("client connected");
          ServerSocketChannel server = (ServerSocketChannel) key
              .channel();
          SocketChannel client = server.accept();
          client.configureBlocking(false);
          SelectionKey clientKey = client.register(
              selector, SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
          SocketChannel client = (SocketChannel) key
              .channel();
          ByteBuffer buff = ByteBuffer.allocate(10);
          buff.put("ABC".getBytes());
          buff.flip();
          client.write(buff);
          client.close();
        }
      }
    }
  }
}
