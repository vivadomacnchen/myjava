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
    System.out.println("url����:" + url.getPort());//The getPort() function is a part of URL class. The function getPort() returns the port of a specified URL. The function returns the port number or -1 if the port is not set
    System.out.println("url���ؿ�:" + url.getPath());//�䤤getFile()�|�]�A�q�D���W�٫���ɮצW�٪��r��A�]�A/�A��getRef()�h�O���^�Ѧ��I�W�١A����U�ٺ��������u���ҡv�A�U���o�ӵ{���ܽd�o�X�Ӥ�k���@�ΡG
    System.out.println("url���ɮ�:" + url.getFile());
    //
    URL url2 = new URL("https://www.youtube.com:80/watch?v=4ASb2iXctWY");
    //  URL url2 = new URL("https:// www.geeksforgeeks.org:80"); 
    System.out.println("url����w:" + 
        url2.getProtocol());
    System.out.println("url2���D��:" + url2.getHost());
    System.out.println("url2����:" + url2.getPort());
    System.out.println("url2���ؿ�:" + url2.getPath());
    System.out.println("url2���ɮ�:" + url2.getFile());
    //
    //�Ѧ�https://openhome.cc/Gossip/JavaGossip-V2/URL.htm
  }
}
