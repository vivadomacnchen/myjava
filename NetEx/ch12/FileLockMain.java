package com.ch12;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class FileLockMain {
  public static void main(String[] args)
      throws IOException {
    Tester t = new Tester();
    t.start();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // �إ�file����
    File file = new File("C:/net/file1.txt");
    // ���o��X��Ƭyfos
    FileOutputStream fos = new FileOutputStream(file);
    // ���o�ɮ׳q�D
    FileChannel channel = fos.getChannel();
    try {
      // ���ը��o��w����
      FileLock lock = channel.tryLock();
      System.out.println("���o��w����,���\��w�ɮ�:"
          + file.getName());
      // ������w����
      lock.release();
      System.out.println("��w����v����:" + file.getName());
    } catch (IOException e) {
      System.out.println("�L�k���o��w����");
      e.printStackTrace();
    }
  }
}

class Tester extends Thread {
  public void run() {
    // �إ�file����
    File file = new File("C:/net/file1.txt");
    // ���o��X��Ƭyfos
    try {
      FileOutputStream fos = new FileOutputStream(
          file);
      // ���o�ɮ׳q�D
      FileChannel channel = fos.getChannel();
      channel.tryLock();
      sleep(3000);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
