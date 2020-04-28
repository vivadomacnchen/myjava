package com.ch13;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SimplePostUtf {
	public static void main(String[] args) throws IOException {
		String name = "王小明";
		String utf8 = URLEncoder.encode(name, "UTF-8");
		String params = "id="+utf8;
		URL url = new URL("http://j.snpy.org/net/post.php");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(params);
		System.out.println("送出參數: "+params);
		out.flush();
		Reader in = new InputStreamReader(conn.getInputStream(),"UTF-8");
		int data = in.read();
		while(data!=-1){
			System.out.print((char)data);
			data = in.read();
		}
	}
}
