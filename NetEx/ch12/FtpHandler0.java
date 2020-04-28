package com.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FtpHandler0 extends Thread {
  BufferedReader in = null;
  PrintWriter out = null;
  boolean logon = false;

  public FtpHandler0(Socket s) {
    try {
      in = new BufferedReader(new InputStreamReader(
          s.getInputStream()));
      out = new PrintWriter(s.getOutputStream());
    } catch (IOException e) {
      System.out.println("資料串流輸出入錯誤");
    }
  }

  public void run() {

  }

  public void reply(String code, String msg) {
    out.println(code + " " + msg);
    out.flush();
    System.out.println("  回應:" + code);
  }
}
