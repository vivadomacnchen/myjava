package com.ch12;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileInfo {
  public static void main(String[] args)
      throws IOException {
    File f = new File("c:\\net\\file1.txt");
    System.out.println("�O�_�O�ؿ�:" + f.isDirectory());
    System.out.println("�O�_�O�ɮ�:" + f.isFile());
    System.out.println("�ɮצW��:" + f.getName());
    System.out.println("�ɮ׸��|:" + f.getPath());
    System.out.println("�W�h�ؿ�:" + f.getParent());
    System.out.println("�ثe�ؿ�:"
        + f.getCanonicalPath());
    System.out.println("�ק�ɶ�:"
        + new Date(f.lastModified()));
    System.out.println("�ɮפj�p:" + f.length());
  }
}
