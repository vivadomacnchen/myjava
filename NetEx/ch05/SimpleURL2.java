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
    System.out.println("內文格式：" + type);
    String encoding = type.substring(type
        .lastIndexOf("=") + 1);
    System.out.println("內文編碼：" + encoding); 
    // 將輸入資料流轉為Reader, 並指定來源編碼格式
    //InputStreamReader in = new InputStreamReader(
    //    conn.getInputStream(), encoding);
    //int data = in.read();
    /*
    while (data != -1) {
      System.out.print((char) data);
      data = in.read();
    }
    */
    //in.close();
    //
    //URL url2 = new URL("http", "j.snpy.org",
    //    "/net/index.html");
    //URL url2 = new URL("https://www.youtube.com:80/watch?v=4ASb2iXctWY");
    URL url2 = new URL("http://www06.eyny.com/index.php");
    URLConnection conn2 = url2.openConnection();//無法用在https的網址上。會出現Exception in thread "main" javax.net.ssl.SSLException: Unsupported or unrecognized SSL message
    conn2.connect();
    String type2 = conn2.getContentType();
    System.out.println("CCW內文格式：" + type2);
    String encoding2 = type2.substring(type2
        .lastIndexOf("=") + 1);
    System.out.println("CCW內文編碼：" + encoding2); 
    // 將輸入資料流轉為Reader, 並指定來源編碼格式
    InputStreamReader in2 = new InputStreamReader(
        conn2.getInputStream(), encoding);
    int data2 = in2.read();
    while (data2 != -1) {
      System.out.print((char) data2);
      data2 = in2.read();
    }
    in2.close();
    //
  }
}
