package com.ch12;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SimpleFile {
  public static void main(String[] args)
      throws IOException {
    // File file = new File("c:\\net\\d1\\d2\\d3");
    // System.out.println("�ؿ��إ߬O�_���\?"+file.mkdirs());

    File file = new File("c:\\net\\file1.txt");
    System.out.println("�ɮ׬O�_�s�b?" + file.exists());
    if (!file.exists()) {
      System.out.println("�s���ɮ׬O�_���\?"
          + file.createNewFile());
    }
    System.out.println("�O�_�O�ؿ�:" + file.isDirectory());
    System.out.println("�O�_�O�ɮ�:" + file.isFile());
    System.out.println("�ɮצW��:" + file.getName());
    System.out.println("�ɮ׸��|:" + file.getPath());
    System.out.println("�W�h�ؿ�:" + file.getParent());
    System.out.println("�ثe�ؿ�:"
        + file.getCanonicalPath());
    System.out.println("�ק�ɶ�:"
        + new Date(file.lastModified()));
    System.out.println("�ɮפj�p:" + file.length());

  }
}
