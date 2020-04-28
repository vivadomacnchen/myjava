package com.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioFtpClient {
  public static void main(String[] args)
      throws IOException {
    InetSocketAddress addr = new InetSocketAddress(
        "j.snpy.org", 21);
    SocketChannel chann = SocketChannel.open(addr);
    // �ǳƽw�İ�
    ByteBuffer buf = ByteBuffer.allocate(1024);
    // Ū�����
    chann.read(buf);
    System.out.println("�w�İϤ������ĸ�ƭӼ�:" + buf.position());
    // �N�w�İϪ���m�]�w��0,�ǳƤ@�@Ū�X�æC�L
    buf.flip();
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
    // �R�O�ǰe,�b���{��
    buf.clear();
    buf.put("USER anonymous\n".getBytes());
    buf.flip();
    chann.write(buf);
    buf.clear();
    // �A��Ū�����
    chann.read(buf);
    // �N�w�İϪ���m�]�w��0,�ǳƤ@�@Ū�X�æC�L
    buf.flip();
    while (buf.hasRemaining()) {
      System.out.print((char) buf.get());
    }
  }
}
