package com.ch06;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestServer {
  public static void main(String[] args)
      throws IOException {
    int port = 9970;
    // �ǳƱ����T���ʥ]��socket�Ppacket
    byte[] data = new byte[20];
    DatagramSocket rsocket = 
        new DatagramSocket(port);
    DatagramPacket rpacket = 
        new DatagramPacket(data, 20);
    // �ϥΰj��while, �����_��ť�~�ӫʥ]
    while (true) {
      System.out.println("�����ݬd�߫ʥ]...");
      rsocket.receive(rpacket);
      // �ˬd�ʥ]���e�O�_��"AYT"
      String rec = new String(data);
      if (rec.startsWith("AYT")) {
        // �ѫʥ]��k���o�߰ݺ�IP��}
        InetAddress client = rpacket.getAddress();
        System.out.println("����߰ݫʥ]:" + rec);
        System.out.println("�߰ݫʥ]�Ӧ�:" + client);
        // �ǳƶǰe�ʥ]
        String fine = "Everything is fine.";
        byte[] msg = fine.getBytes();
        DatagramPacket p = new DatagramPacket(msg,
            msg.length, client, 9971);
        rsocket.send(p);
        System.out.println("�w�e�X�^���ʥ]");
      }
    }
  }
}