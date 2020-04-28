package com.ch10;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.TreeMap;

public class ClientHandler {
  private Map<String, String> users;
  SocketChannel channel;
  ByteBuffer buff = ByteBuffer.allocate(100);
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;
  public static final int STAGE_USER = 0;
  public static final int STAGE_PASSWORD = 1;
  public static final int STAGE_LOGON = 2;
  int stage = STAGE_USER;
  String userid = "";
  String pw = "";

  public ClientHandler(SelectionKey key) {
    channel = (SocketChannel) key.channel();
    initAccount();
  }

  public void handleRead(SelectionKey key) {
    try {
      int count = channel.read(buff);
      if (count > 0) {
        buff.flip();
        while (buff.position() < count) {
          int data = buff.get() & 0xFF;
          if (data == IAC) {
            handleCommand(buff);
          } else {
            switch (stage) {
            case STAGE_USER:
              if (data == 10) {
                send("\n\rpassword:");
                stage = STAGE_PASSWORD;
                userid = userid.substring(0,
                    userid.length() - 1);
                System.out.println("id=" + userid);
              } else {
                userid = userid + (char) data;
              }
              break;
            case STAGE_PASSWORD:
              if (data == 10) {
                pw = pw.substring(0, pw.length() - 1);
                System.out.println("pw=" + pw);
                if (users.get(userid).equals(pw)) {
                  send("\n\rLogin successful");
                  stage = STAGE_LOGON;
                } else {
                  send("\n\rLogin failed");
                  channel.close();
                }
              } else {
                pw = pw + (char) data;
              }
              break;
            }
          }
        }
        buff.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void welcomeMessage() {
    send("Welcome\r\nlogin:");
    stage = STAGE_USER;
  }

  public void send(String text) {
    ByteBuffer buff = ByteBuffer.allocate(1024);
    buff.put(text.getBytes());
    buff.flip();
    try {
      channel.write(buff);
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

  public void initAccount() {
    users = new TreeMap<String, String>();
    users.put("tom", "uu123");
    users.put("jack", "yy123");
  }
}
