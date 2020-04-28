package com.ch12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FtpHandler_RETR extends Thread {
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
  public static final int RETR = 7;
  public static final int STOR = 8;
  String directory = "/";
  Socket dataSocket = null;
  String serverIP = "192.168.1.15";

  public FtpHandler_RETR(Socket s) {
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
    map.put("RETR", RETR);
    map.put("STOR", STOR);
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
          reply("150",
              "Here comes the directory listing.");
          PrintWriter dataOut = new PrintWriter(
              dataSocket.getOutputStream());
          File currentDir = new File("C:\\net\\"
              + directory);
          File[] files = currentDir.listFiles();
          for (int i = 0; i < files.length; i++) {
            dataOut.println(files[i].getName());
          }
          dataOut.flush();
          reply("226", "Transfer complete.");
          dataOut.close();
          dataSocket.close();
          break;
        case PASV:
          ServerSocket dataServer = new ServerSocket(
              0);
          int dataPort = dataServer.getLocalPort();
          System.out.println(dataPort);
          int p1 = dataPort / 256;
          int p2 = dataPort % 256;
          reply("227", "Entering Passive Mode("
              + serverIP.replace('.', ',') + "," + p1
              + "," + p2 + ").");
          dataSocket = dataServer.accept();
          System.out.println("已建立資料通道");
          break;
        case RETR:
          String filename = stk.nextToken();
          File file = new File("C:/net" + filename);
          FileInputStream fileIn = new FileInputStream(
              file);
          PrintWriter out = new PrintWriter(
              dataSocket.getOutputStream());
          reply("150", "Open connection for "
              + filename);
          int n = fileIn.read();
          while (n != -1) {
            out.write(n);
            n = fileIn.read();
          }
          out.flush();
          reply("226", "Transfer complete.");
          out.close();
          fileIn.close();
          dataSocket.close();
          break;
        // case STOR:
        // String fname = stk.nextToken();
        // reply("150", "Ok to send data.");
        // FileWriter out = new FileWriter("C:/net/" + fname);
        // InputStreamReader in = new InputStreamReader(dataSocket
        // .getInputStream());
        // int n = in.read();
        // System.out.println("Data:" + n);
        // while (n != -1) {
        // System.out.print(n);
        // out.write(n);
        // n = in.read();
        // }
        // out.flush();
        // reply("226", "File receive OK.");
        // out.close();
        // in.close();
        // dataSocket.close();
        // break;
        case STOR:
          String fname = stk.nextToken();
          reply("150", "Ok to send data.");
          FileOutputStream dout = new FileOutputStream(
              "C:/net/" + fname);
          // 加鎖
          FileLock lock = dout.getChannel().tryLock();
          InputStreamReader din = new InputStreamReader(
              dataSocket.getInputStream());
          int dn = din.read();
          System.out.println("Data:" + dn);
          while (dn != -1) {
            dout.write(dn);
            dn = din.read();
          }
          dout.flush();
          reply("226", "File receive OK.");
          dout.close();
          // 解鎖
          lock.release();
          din.close();
          dataSocket.close();
          break;
        default:
          System.out.println("**" + cmdInt);
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

  public static void main(String[] args)
      throws IOException {
    ServerSocket ss = new ServerSocket(21);
    while (true) {
      Socket socket = ss.accept();
      System.out.println("客戶端連線:"
          + socket.getRemoteSocketAddress());
      FtpHandler_RETR server = new FtpHandler_RETR(
          socket);
      server.start();
    }
  }
}
