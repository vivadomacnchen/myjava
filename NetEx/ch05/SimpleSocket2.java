package com.ch05;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocket2 {
  public static void main(String[] args)
      throws UnknownHostException, IOException {
    Socket socket = new Socket("www.snpy.org", 80);
    System.out.println("���a�X�o����}:"
        + socket.getLocalAddress());
    System.out.println("���a�X�o����:"
        + socket.getLocalPort());
    System.out.println("���ݥD������}:"
        + socket.getInetAddress());
    System.out.println("���ݥD������:" +
        socket.getPort());
  }
}
