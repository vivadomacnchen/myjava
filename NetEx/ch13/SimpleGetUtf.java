package com.ch13;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SimpleGetUtf {
  public static void main(String[] args)
      throws IOException {
    String name = "王小明";
    String utf8 = URLEncoder.encode(name, "UTF-8");
    String params = "id=" + utf8;
    System.out.println("網址參數(UTF-8): " + params);
    URL url = new URL(
        "http://j.snpy.org/net/get.php?" + params);
    URLConnection conn = url.openConnection();
    Reader in = new InputStreamReader(
        conn.getInputStream(), "UTF-8");
    int data = in.read();
    while (data != -1) {
      System.out.print((char) data);
      data = in.read();
    }
  }
}
