package com.ch10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TelnetServerNio {
  int port = 9980;

  public TelnetServerNio(int port) {
    this.port = port;
  }

  public void accept() {
    try {
      ServerSocketChannel serverChannel = ServerSocketChannel
          .open();
      ServerSocket ss = serverChannel.socket();
      ss.bind(new InetSocketAddress(port));
      serverChannel.configureBlocking(false);
      Selector selector = Selector.open();
      serverChannel.register(selector,
          SelectionKey.OP_ACCEPT);
      while (true) {
        selector.select();
        Set keys = selector.selectedKeys();
        Iterator it = keys.iterator();
        int count = 0;
        while (it.hasNext()) {
          SelectionKey key = (SelectionKey) it.next();
          it.remove();
          if (key.isAcceptable()) {
            System.out.println("�Τ�s�W�u");
            ServerSocketChannel server = (ServerSocketChannel) key
                .channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            SelectionKey clientKey = client.register(
                selector, SelectionKey.OP_READ);
            // �����@��ClientHandler����,�M���B�z�Τ�ݰe�F�����
            ClientHandler handler = new ClientHandler(
                clientKey);
            handler.welcomeMessage();
            // �NClientHandler������[��clientKey
            clientKey.attach(handler);
          } else if (key.isReadable()) {
            // ��key��Ȥ����X���[����,���૬��ClientHandler
            ClientHandler handler = (ClientHandler) key
                .attachment();
            // �B�z�Τ�ݰe�F�����
            handler.handleRead(key);
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
    new TelnetServerNio(9980).accept();
  }
}
