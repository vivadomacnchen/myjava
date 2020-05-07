package com.ch05;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleURL {
  public static void main(String[] args)
      throws IOException {
    URL url = new URL("http", "snpy.org",
        "/java/index.html");
    System.out.println("url的協定:" + 
        url.getProtocol());
    System.out.println("url的主機:" + url.getHost());
    System.out.println("url的埠號:" + url.getPort());//The getPort() function is a part of URL class. The function getPort() returns the port of a specified URL. The function returns the port number or -1 if the port is not set
    System.out.println("url的目錄:" + url.getPath());//其中getFile()會包括從主機名稱後至檔案名稱的字串，包括/，而getRef()則是取回參考點名稱，中文俗稱網頁中的「書籤」，下面這個程式示範這幾個方法的作用：
    System.out.println("url的檔案:" + url.getFile());
    //
    URL url2 = new URL("https://www.youtube.com:80/watch?v=4ASb2iXctWY");
    //  URL url2 = new URL("https:// www.geeksforgeeks.org:80"); 
    System.out.println("url的協定:" + 
        url2.getProtocol());
    System.out.println("url2的主機:" + url2.getHost());
    System.out.println("url2的埠號:" + url2.getPort());
    System.out.println("url2的目錄:" + url2.getPath());
    System.out.println("url2的檔案:" + url2.getFile());
    //
    //參考https://openhome.cc/Gossip/JavaGossip-V2/URL.htm
  }
}
