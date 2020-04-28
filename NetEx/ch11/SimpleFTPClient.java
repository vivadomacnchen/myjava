package com.ch11;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class SimpleFTPClient {
  public static void main(String[] args) throws IOException {
    FTPClient ftp = new FTPClient();
    // 登入
    ftp.connect("ftp.isu.edu.tw", 21);
    ftp.login("anonymous", "aa@com.tw");
    // 切換目錄至/Linux
    ftp.changeWorkingDirectory("/Linux");
    System.out.println("目前目錄:" + ftp.printWorkingDirectory());
    // 列出檔案清單
    FTPFile[] files = ftp.listFiles();
    for (FTPFile f : files){
    	System.out.println(f.getName());
    }
    // 下載檔案
    String remoteFile = "/Linux/00_index.txt";
    String downloadFile = "00_index.txt";
    // 準備下載檔案的輸出資料流
    BufferedOutputStream output = new BufferedOutputStream(
    		new FileOutputStream(downloadFile));
    // 執行下載工作
    boolean success = ftp.retrieveFile(remoteFile, output);
    output.close();
    System.out.println("成功下載?"+success);
    // 關閉ftp連線
    ftp.disconnect();
  }
}
