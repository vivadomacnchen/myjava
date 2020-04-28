package com.ch06;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestClient {
  public static void main(String[] args)
      throws IOException {
    int port = 9971;
    InetAddress server = 
      InetAddress.getByName("192.168.1.10");
    // �ǳƱ����T���ʥ]��socket�Ppacket
    byte[] data = new byte[20];
    byte[] msg = "AYT".getBytes();
    DatagramSocket rsocket = 
      new DatagramSocket(port);
    DatagramPacket rpacket = 
      new DatagramPacket(data, 20);
    // �ǳƶǰe�ʥ]
    DatagramPacket p = new DatagramPacket(msg,
        msg.length, server, 9970);
    rsocket.send(p);
    System.out.println("�w�e�X�d�߫ʥ]");
    // �ǳƱ���Server�ǨӪ��ʥ]
    rsocket.receive(rpacket);
    System.out.println("������A�����^���ʥ]:"
        + new String(data));
  }
}
