package com.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CounterServer {
  public static int count = 0;

  public void startCount() {
    while (true) {
      try {
        ServerSocket ss = new ServerSocket(8884);
        Socket socket = ss.accept();
        count++;
        System.out.println("��" + count + 
            "�ӫȤ�s�u���\");
        OutputStream rawOut = 
          socket.getOutputStream();
        PrintWriter out = new PrintWriter(rawOut);
        out.println("�z�O��" + count + "�ӫȤ��");
        out.flush();
        out.close();
        socket.close();
        ss.close();
      } catch (IOException e) {
        System.out.println("��X�J���~");
      }
    }
  }

  public static void main(String[] args) {
    CounterServer cserver = new CounterServer();
    cserver.startCount();
  }
}
