package com.ch05;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleURL {
  public static void main(String[] args)
      throws IOException {
    URL url = new URL("http", "snpy.org",
        "/java/index.html");
    System.out.println("url����w:" + 
        url.getProtocol());
    System.out.println("url���D��:" + url.getHost());
    System.out.println("url����:" + url.getPort());
    System.out.println("url���ؿ�:" + url.getPath());
    System.out.println("url���ɮ�:" + url.getFile());
  }
}
