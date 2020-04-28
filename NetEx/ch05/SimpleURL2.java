package com.ch05;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class SimpleURL2 {
  public static void main(String[] args)
      throws IOException {
    URL url = new URL("http", "j.snpy.org",
        "/net/index.html");
    URLConnection conn = url.openConnection();
    conn.connect();
    String type = conn.getContentType();
    System.out.println("����榡�G" + type);
    String encoding = type.substring(type
        .lastIndexOf("=") + 1);
    System.out.println("����s�X�G" + encoding);
    // �N��J��Ƭy�ରReader, �ë��w�ӷ��s�X�榡
    InputStreamReader in = new InputStreamReader(
        conn.getInputStream(), encoding);
    int data = in.read();
    while (data != -1) {
      System.out.print((char) data);
      data = in.read();
    }
    in.close();
  }
}
