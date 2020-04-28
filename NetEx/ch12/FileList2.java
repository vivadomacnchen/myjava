package com.ch12;

import java.io.File;

public class FileList2 {
  public static void main(String[] args) {
    File f = new File("c:\\net");
    if (f.isDirectory()) {
      File[] files = f.listFiles();
      for (int i = 0; i < files.length; i++) {
        System.out.print(files[i].getName());
        if (files[i].isDirectory())
          System.out.println("\t<DIR>");
        else
          System.out
              .println("\t" + files[i].length());
      }
    }
  }
}
