package com.ch10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TelnetTester0 {
  public static void main(String[] args)
      throws IOException {
    InetSocketAddress addr = new InetSocketAddress(
        "localhost", 23);
    SocketChannel chann = SocketChannel.open(addr);
    ByteBuffer buf = ByteBuffer.allocate(1024);

    chann.read(buf);
    System.out.println("緩衝區內的有效資料個數:"+buf.position());
    buf.flip();
    while (buf.hasRemaining()) {
      byte b = buf.get();
      int data = b & 0xFF;
      System.out.print(data + ",");
    }
  }
}
