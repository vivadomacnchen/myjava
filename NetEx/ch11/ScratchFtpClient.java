package com.ch11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScratchFtpClient {
  String host;
  int port;
  BufferedReader in;
  PrintWriter out;

  public ScratchFtpClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void connect() throws UnknownHostException,
      IOException {
    Socket socket = new Socket(host, port);
    in = new BufferedReader(new InputStreamReader(
        socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream());
  }

  public void getWelcome() {
    try {
      String line;
      do {
        line = in.readLine();
        System.out.println(line);
      } while (line.charAt(3) != ' ');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void login() {
    sendCommand("USER anonymous");
    sendCommand("PASS aa@com.tw");
  }

  public String sendCommand(String cmd) {
    String reply = null;
    // 傳送指令
    out.println(cmd);
    out.flush();
    // 取得伺服器的回應
    String line = null;
    try {
      do {
        line = in.readLine();
        System.out.println(line);
      } while (line.charAt(3) != ' ');
    } catch (IOException e) {
      e.printStackTrace();
    }
    reply = line;
    return reply;
  }

  public void pasvList() throws UnknownHostException,
      IOException {
    // 得到如 "140,117,11,7,29,5"
    String reply = sendCommand("PASV");
    String info = reply.substring(
        reply.indexOf("(") + 1, reply.indexOf(")"));
    String tks[] = info.split(",");
    String ip = tks[0] + "." + tks[1] + "." + tks[2]
        + "." + tks[3];
    int p1 = Integer.parseInt(tks[4]);
    int p2 = Integer.parseInt(tks[5]);
    int port = p1 * 256 + p2;
    Socket dataSocket = new Socket(ip, port);
    sendCommand("LIST");
    InputStream dataIn = dataSocket.getInputStream();
    int data = dataIn.read();
    while (data != -1) {
      System.out.print((char) data);
      data = dataIn.read();
    }
    dataIn.close();
    dataSocket.close();
  }

  public static void main(String[] args) {
    ScratchFtpClient client = new ScratchFtpClient(
        "ftp.nsysu.edu.tw", 21);
    try {
      client.connect();
      client.getWelcome();
      client.login();
      client.pasvList();
    } catch (UnknownHostException e) {
      System.out.println("主機解析錯誤");
    } catch (IOException e) {
      System.out.println("資料輸出入錯誤");
    }
  }
}
