package com.ch13;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class DisplayHeader {
  public static void main(String[] args) {
    try {
      URL url = new URL(
          "http://j.snpy.org/net/index.html");
      URLConnection conn = url.openConnection();
      System.out.println("Object's Class name =>"
          + url.getClass().getName());
      System.out.println("Content Type:"
          + conn.getContentType());
      System.out.println("Content Length:"
          + conn.getContentLength());
      System.out.println("Date:"
          + new Date(conn.getDate()));
      System.out.println("Expires:"
          + conn.getExpiration());
      System.out.println("標頭的第3項資料:"
          + conn.getHeaderField(3));
      System.out.println("最後修改時間:"
          + conn.getHeaderField("Last-Modified"));
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
