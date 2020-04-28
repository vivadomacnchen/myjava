package com.ch13;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SimpleHttp {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://j.snpy.org/net/index.html");
			URLConnection conn = url.openConnection();
//			InputStream in = conn.getInputStream();
			Reader in = new InputStreamReader(conn.getInputStream(),"UTF-8");
			int data = in.read();
			while(data!=-1){
				System.out.print((char)data);
				data = in.read();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
