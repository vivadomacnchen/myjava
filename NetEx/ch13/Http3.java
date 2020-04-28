package com.ch13;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http3 {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://j.snpy.org/net/redirect.php");
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		uc.setFollowRedirects(false);
		uc.setUseCaches(false);
		System.out.println("要求的方式:"+uc.getRequestMethod());
		System.out.println("回應碼:"+uc.getResponseCode());
		System.out.println("回應訊息:"+uc.getResponseMessage());
		Reader in = new InputStreamReader(uc.getInputStream(),"UTF-8");
		int data = in.read();
		while(data!=-1){
			System.out.print((char)data);
			data = in.read();
		}
		System.out.println("是否有重導過:"+uc.getFollowRedirects());
	}
}
