package com.ch05;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SimpleSocket {
  public static void main(String[] args)
      throws IOException {
    Socket socket = new Socket();
    InetSocketAddress local = 
      new InetSocketAddress(2222);
    InetSocketAddress remote = 
      new InetSocketAddress("www.snpy.org", 23);
    socket.bind(local);
    System.out.println("�ѥ��a�X�o����:"
        + socket.getLocalPort());
    socket.connect(remote, 5);
  }
}
