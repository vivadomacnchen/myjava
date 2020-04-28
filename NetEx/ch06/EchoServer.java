package com.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public void echo() {
    try {
      ServerSocket ss = new ServerSocket(8885);
      Socket socket = ss.accept();
      // �s�u��,���o��X�J��y
      OutputStream rawOut = socket.getOutputStream();
      InputStream rawIn = socket.getInputStream();
      PrintWriter out = new PrintWriter(rawOut);
      BufferedReader in = new BufferedReader(
          new InputStreamReader(rawIn));
      // ���ݫȤ�ݰe�Ӧr��
      String data = in.readLine();
      System.out.println("����:" + data);
      // �N�ǨӪ���Ʀ^�e���Ȥ��
      out.println(data);
      out.flush();
      System.out.println("�e�X:" + data);
      // �����s�u�귽
      in.close();
      out.close();
      socket.close();
      ss.close();
    } catch (IOException e) {
      System.out.println("��X�J���~");
    }
  }

  public static void main(String[] args) {
    EchoServer eserver = new EchoServer();
    eserver.echo();
  }
}
