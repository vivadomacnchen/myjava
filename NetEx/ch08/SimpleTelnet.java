package com.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleTelnet {
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;

  public static void main(String[] args)
      throws UnknownHostException, IOException {
    Socket s = new Socket("ptt.cc", 23);
    InputStream in = s.getInputStream();
    OutputStream out = s.getOutputStream();
    int data = in.read();
    for (int i = 0; i < 600; i++) {
      if (data == IAC) {
        int tone = in.read();
        if (tone == DO) {
          int option = in.read();
          System.out.println(IAC + "," + tone + ","
              + option);
          if (option == 24) {
            out.write(IAC);
            out.write(WILL);
            out.write(24);
            out.flush();
            continue;
          }
          out.write(IAC);
          out.write(WONT);
          out.write(option);
          out.flush();
          System.out.println(" SENT:" + IAC + ","
              + WONT + "," + option);
        }
        if (tone == WILL) {
          int option = in.read();
          System.out.println(IAC + "," + tone + ","
              + option);
          out.write(IAC);
          out.write(DONT);
          out.write(option);
          out.flush();
          System.out.println(" SENT:" + IAC + ","
              + DONT + "," + option);
        }
        if (tone == 250) {
          int option = in.read();
          if (option == 24) {
            // 將後面的三個值讀進來(1,255,240)
            in.read();
            in.read();
            in.read();
            // 以SB方式送出A N S I
            out.write(IAC);
            out.write(250);
            out.write(24);
            out.write(0);
            out.write("ANSI".getBytes());
            out.write(IAC);
            out.write(240);
            out.flush();
          }
        }
      } else {
        System.out.print(getBig5Char(data, in));
      }
      data = in.read();
    }
  }

  public static char getBig5Char(int data,
      InputStream in) throws IOException {
    char c = (char) data;
    if (data > 127) {
      byte[] big5 = new byte[2];
      big5[0] = (byte) data;
      big5[1] = (byte) in.read();
      c = new String(big5, "BIG5").charAt(0);
    }
    return c;
  }
}
