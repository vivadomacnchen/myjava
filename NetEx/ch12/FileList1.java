package com.ch12;

import java.io.File;

public class FileList1 {
  public static void main(String[] args) {
    File f = new File("c:\\net");
    if (f.isDirectory()) {
      String[] files = f.list();
      for (int i = 0; i < files.length; i++) {
        System.out.println(files[i]);
      }
    }
  }
}
