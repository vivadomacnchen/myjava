package com.ch12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FtpHandler1 extends Thread {
  BufferedReader in = null;
  PrintWriter out = null;
  boolean logon = false;
  Map<String, Integer> map;
  public static final int USER = 1;
  public static final int PASS = 2;
  public static final int PWD = 3;
  public static final int LIST = 4;
  public static final int TYPE = 5;
  public static final int PASV = 6;
  String directory = "/";
  Socket dataSocket = null;

  public FtpHandler1(Socket s) {
    try {
      in = new BufferedReader(new InputStreamReader(
          s.getInputStream()));
      out = new PrintWriter(s.getOutputStream());
    } catch (IOException e) {
      System.out.println("資料串流輸出入錯誤");
    }
    map = new HashMap<String, Integer>();
    map.put("USER", USER);
    map.put("PASS", PASS);
    map.put("PWD", PWD);
    map.put("LIST", LIST);
    map.put("TYPE", TYPE);
    map.put("PASV", PASV);
  }

  public void run() {
    reply("220", "Welcome to My FTP Server");
    String line = "";
    try {
      while ((line = in.readLine()) != null) {
        StringTokenizer stk = new StringTokenizer(
            line);
        String cmd = stk.nextToken();
        System.out.println(line);
        int cmdInt = map.get(cmd) == null ? -1 : map
            .get(cmd);
        switch (cmdInt) {
        case USER:
          reply("331", "Please specify the password.");
          break;
        case PASS:
          reply("230", "Login successful.");
          break;
        case PWD:
          reply("257", directory);
          break;
        case TYPE:
          String mode = stk.nextToken();
          if (mode.equals("I"))
            reply("200", "Switching to Binary mode.");
          if (mode.equals("A"))
            reply("200", "Switching to ASCII mode.");
          break;
        case LIST:

          break;
        case PASV:

          break;
        default:
          reply("500", "command not understood.");
        }
      }
    } catch (IOException e) {
      System.out.println("輸出入錯誤");
    }
  }

  public void reply(String code, String msg) {
    out.println(code + " " + msg);
    out.flush();
    System.out.println("  回應:" + code);
  }
}
