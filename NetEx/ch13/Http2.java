package com.ch13;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Http2 {
  public static void main(String[] args)
      throws IOException {
    URL url = 
      new URL("ftp://j.snpy.org");
    HttpURLConnection uc = 
        (HttpURLConnection) url.openConnection();
    System.out.println("要求的方式:"
        + uc.getRequestMethod());
    System.out.println("回應碼:" + uc.getResponseCode());
    System.out.println("回應訊息:"
        + uc.getResponseMessage());
  }
}
