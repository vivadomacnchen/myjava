package com.ch10;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TelnetTester {
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;
  public static final int TERMINAL_TYPE = 24;
  public static final int SB = 250;
  public static final int SE = 240;

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
      if (data == IAC) {
        System.out.println();
        System.out.print(data + ",");
        handleCommand(chann, buf);
      } else {
        System.out.print((char) data);
      }
    }
  }

  public static void handleCommand(SocketChannel chann,
      ByteBuffer buf) throws IOException {
    ByteBuffer outBuf = ByteBuffer.allocate(1024);
    int tone = buf.get() & 0xFF;
    int option = buf.get() & 0xFF;
    System.out.println(tone + "," + option);
    outBuf.clear();
    if (tone == DO) {
      outBuf.put((byte) IAC);
      outBuf.put((byte) WONT);
      outBuf.put((byte) option);
      outBuf.flip();
      chann.write(outBuf);
      System.out.println(" SENT:" + IAC + "," + WONT
          + "," + option);
    } else if (tone == WILL) {
      outBuf.put((byte) IAC);
      outBuf.put((byte) DONT);
      outBuf.put((byte) option);
      outBuf.flip();
      chann.write(outBuf);
      System.out.println(" SENT:" + IAC + "," + DONT
          + "," + option);
    }
  }
}
