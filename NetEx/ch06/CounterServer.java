package com.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CounterServer {
  public static int count = 0;

  public void startCount() {
    while (true) {
      try {
        //參考https://openhome.cc/Gossip/JavaGossip-V2/ServerSocket.htm
        ServerSocket ss = new ServerSocket(23);//Socket類別主要在處理客戶端的Socket連線，如果要實作一個伺服器，可以使用ServerSocket類別，它包括了伺服器傾聽與客戶端連線的方法
        //以port 23可以使用teraterm的telent連線到
        Socket socket = ss.accept();//當要傾聽連線或關閉連線時，可以使用accept()與close()方法：
        count++;
        System.out.println("第" + count + 
            "個客戶連線成功");
        OutputStream rawOut = 
          socket.getOutputStream();
        PrintWriter out = new PrintWriter(rawOut);
        out.println("您是第" + count + "個客戶端");
        out.flush();
        out.close();
        socket.close();
        ss.close();
      } catch (IOException e) {
        System.out.println("輸出入錯誤");
      }
    }
  }

  public static void main(String[] args) {
    CounterServer cserver = new CounterServer();
    cserver.startCount();
  }
}
