package com.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioFtpClient {
  public static void main(String[] args)
      throws IOException {
    InetSocketAddress addr = new InetSocketAddress(
        "j.snpy.org", 21);
    SocketChannel chann = SocketChannel.open(addr);
    // 準備緩衝區
    ByteBuffer buf = ByteBuffer.allocate(1024);
    // 讀取資料
    chann.read(buf);
    System.out.println("緩衝區內的有效資料個數:" + buf.position());
    // 將緩衝區的位置設定為0,準備一一讀出並列印
    buf.flip();
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
    // 命令傳送,帳號認證
    buf.clear();
    buf.put("USER anonymous\n".getBytes());
    buf.flip();
    chann.write(buf);
    buf.clear();
    // 再次讀取資料
    chann.read(buf);
    // 將緩衝區的位置設定為0,準備一一讀出並列印
    buf.flip();
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
  }
}
