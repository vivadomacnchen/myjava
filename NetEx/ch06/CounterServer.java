package com.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CounterServer {
  public static int count = 0;

  public void startCount() {
    while (true) {
      try {
        //�Ѧ�https://openhome.cc/Gossip/JavaGossip-V2/ServerSocket.htm
        ServerSocket ss = new ServerSocket(23);//Socket���O�D�n�b�B�z�Ȥ�ݪ�Socket�s�u�A�p�G�n��@�@�Ӧ��A���A�i�H�ϥ�ServerSocket���O�A���]�A�F���A����ť�P�Ȥ�ݳs�u����k
        //�Hport 23�i�H�ϥ�teraterm��telent�s�u��
        Socket socket = ss.accept();//��n��ť�s�u�������s�u�ɡA�i�H�ϥ�accept()�Pclose()��k�G
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
