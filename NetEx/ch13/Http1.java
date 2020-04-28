package com.ch13;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Http1 {
  public static void main(String[] args) {
    try {
      URL url = new URL(
          "http://j.snpy.org/net/index.html");
      HttpURLConnection uc = (HttpURLConnection) url
          .openConnection();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
