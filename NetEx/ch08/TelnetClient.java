package com.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TelnetClient {
  String host;
  int port;
  InputStream in;
  OutputStream out;
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;
  public static final int TERMINAL_TYPE = 24;
  public static final int SB = 250;
  public static final int SE = 240;

  public TelnetClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void connect() throws UnknownHostException,
      IOException {
    Socket socket = new Socket(host, port);
    in = socket.getInputStream();
    out = socket.getOutputStream();
    processLoop();
  }

  public void processLoop() throws IOException {
    while (true) {
      int data = in.read();
      if (data == IAC) {
        int tone = in.read();
        int option = in.read();
        if (tone == SB)
          negotiation(tone, option);
        if (tone == DO && option == TERMINAL_TYPE)
          allowOption(tone, option);
        else
          denyOption(tone, option);
      } else {
        System.out.print(getBig5Char(data, in));
      }
    }
  }

  public void denyOption(int tone, int option)
      throws IOException {
    if (tone == DO) {
      System.out.println(IAC + "," + tone + ","
          + option);
      out.write(IAC);
      out.write(WONT);
      out.write(option);
      out.flush();
      System.out.println(" SENT:" + IAC + "," + WONT
          + "," + option);
    } else if (tone == WILL) {
      System.out.println(IAC + "," + tone + ","
          + option);
      out.write(IAC);
      out.write(DONT);
      out.write(option);
      out.flush();
      System.out.println(" SENT:" + IAC + "," + DONT
          + "," + option);
    }
  }

  public void allowOption(int tone, int option)
      throws IOException {
    if (tone == DO) {
      System.out.println(IAC + "," + tone + ","
          + option);
      out.write(IAC);
      out.write(WILL);
      out.write(option);
      out.flush();
      System.out.println(" SENT:" + IAC + "," + WILL
          + "," + option);
    } else if (tone == WILL) {
      System.out.println(IAC + "," + tone + ","
          + option);
      out.write(IAC);
      out.write(DO);
      out.write(option);
      out.flush();
      System.out.println(" SENT:" + IAC + "," + DO
          + "," + option);
    }
  }

  public void negotiation(int tone, int option)
      throws IOException {
    switch (option) {
    case TERMINAL_TYPE:
      in.read();
      in.read();
      in.read();
      // 以SB方式送出"A N S I"
      out.write(IAC);
      out.write(SB);
      out.write(TERMINAL_TYPE);
      out.write(0);
      out.write("ANSI".getBytes());
      out.write(IAC);
      out.write(SE);
      out.flush();
    }
  }

  public char getBig5Char(int data, InputStream in)
      throws IOException {
    char c = (char) data;
    if (data > 127) {
      byte[] big5 = new byte[2];
      big5[0] = (byte) data;
      big5[1] = (byte) in.read();
      c = new String(big5, "BIG5").charAt(0);
    }
    return c;
  }

  public static void main(String[] args)
      throws UnknownHostException, IOException {
    TelnetClient client = new TelnetClient(
        "ptt.cc", 23);
    client.connect();
  }
}
