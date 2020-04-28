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
    // 建立file物件
    File file = new File("C:/net/file1.txt");
    // 取得輸出資料流fos
    FileOutputStream fos = new FileOutputStream(file);
    // 取得檔案通道
    FileChannel channel = fos.getChannel();
    try {
      // 嘗試取得鎖定物件
      FileLock lock = channel.tryLock();
      System.out.println("取得鎖定物件,成功鎖定檔案:"
          + file.getName());
      // 釋放鎖定物件
      lock.release();
      System.out.println("鎖定物件己釋放:" + file.getName());
    } catch (IOException e) {
      System.out.println("無法取得鎖定物件");
      e.printStackTrace();
    }
  }
}

class Tester extends Thread {
  public void run() {
    // 建立file物件
    File file = new File("C:/net/file1.txt");
    // 取得輸出資料流fos
    try {
      FileOutputStream fos = new FileOutputStream(
          file);
      // 取得檔案通道
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
