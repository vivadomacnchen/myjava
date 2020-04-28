package com.ch13;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.*;

public class HttpClientGet {
  public static void main(String[] args)
      throws ClientProtocolException, IOException {
    HttpClient httpclient = new DefaultHttpClient();
    String params = "id=119&func=ac929";
    HttpGet httpget = new HttpGet(
        "http://j.snpy.org/net/get.php?"+params);
    // ����HTTP�ШD,�s�u�ܺ��},�ño����A�����^��
    HttpResponse response = 
      httpclient.execute(httpget);
    System.out.println(response.getStatusLine());
  }
}
