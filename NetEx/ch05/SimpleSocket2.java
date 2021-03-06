package com.ch05;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleSocket2 {
  public static void main(String[] args)
      throws UnknownHostException, IOException {
    Socket socket = new Socket("www.snpy.org", 80);
    System.out.println("本地出發的位址:"
        + socket.getLocalAddress());
    System.out.println("本地出發的埠號:"
        + socket.getLocalPort());
    System.out.println("遠端主機的位址:"
        + socket.getInetAddress());
    System.out.println("遠端主機的埠號:" +
        socket.getPort());
  }
}
