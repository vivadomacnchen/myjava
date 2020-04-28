package com.ch10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TelnetClientNio extends Thread {
  private String host;
  private int port;
  SocketChannel channel;
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;

  public TelnetClientNio(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void connect() {
    try {
      InetSocketAddress addr = new InetSocketAddress(
          "localhost", 23);
      channel = SocketChannel.open(addr);
      start();
      ByteBuffer buf = ByteBuffer.allocate(1024);
      while (true) {
        buf.clear();
        channel.read(buf);
        System.out.println("(緩衝區內的有效資料個數:"
            + buf.position() + ")");
        if (buf.position() == 0)
          break;
        buf.flip();
        while (buf.hasRemaining()) {
          byte b = buf.get();
          int data = b & 0xFF; //將byte轉為整數
          if (data == IAC) {
            handleCommand(buf);
          } else {
            System.out.print((char) data);
          }
        }
      }
      channel.close();
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void handleCommand(ByteBuffer buf)
      throws IOException {
    ByteBuffer outBuf = ByteBuffer.allocate(1024);
    int tone = buf.get() & 0xFF;
    int option = buf.get() & 0xFF;
    outBuf.clear();
    if (tone == DO) {
      outBuf.put((byte) IAC);
      outBuf.put((byte) WONT);
      outBuf.put((byte) option);
      outBuf.flip();
      channel.write(outBuf);
    } else if (tone == WILL) {
      outBuf.put((byte) IAC);
      outBuf.put((byte) DONT);
      outBuf.put((byte) option);
      outBuf.flip();
      channel.write(outBuf);
    }
  }

  public void run() {
    ByteBuffer outBuf = ByteBuffer.allocate(1024);
    BufferedReader in = new BufferedReader(
        new InputStreamReader(System.in));
    while (true) {
      try {
        outBuf.clear();
        String line = in.readLine();
        line = line + "\n\r";
        outBuf.put(line.getBytes());
        outBuf.flip();
        channel.write(outBuf);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    TelnetClientNio client = new TelnetClientNio(
        "localhost", 23);
    client.connect();
  }

}
