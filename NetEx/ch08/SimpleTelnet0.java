package com.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleTelnet0 {
  public static final int IAC = 255;
  public static final int WILL = 251;
  public static final int WONT = 252;
  public static final int DO = 253;
  public static final int DONT = 254;

  public static void main(String[] args)
      throws UnknownHostException, IOException {
    Socket s = new Socket("localhost", 23);
    InputStream in = s.getInputStream();
    OutputStream out = s.getOutputStream();
    int data = in.read();
    for (int i = 0; i < 40; i++) {
      if (data == IAC) {
        int tone = in.read();
        if (tone == DO) {
          int option = in.read();
          System.out.println(IAC + "," + tone + ","
              + option);
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
      } else {
        System.out.print((char) data);
      }
      data = in.read();
    }
  }
}
