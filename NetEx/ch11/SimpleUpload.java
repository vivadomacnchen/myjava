package com.ch11;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;

public class SimpleUpload {
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient ftp = new FTPClient();
		//登入
		ftp.connect("localhost", 1234);
		ftp.login("tom", "net123");
		// 準備本地檔案輸入資料流
		FileInputStream in = new FileInputStream("00_index.txt");
		// 上傳梢案
		boolean success = ftp.storeFile("/00_index.txt", in);
		in.close();
		ftp.disconnect();
	}
}
