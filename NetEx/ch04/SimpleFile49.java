package com.ch04;

import java.io.File;
import java.io.IOException;

public class SimpleFile49 {
  public static void main(String[] args)
      throws IOException {
    // Ū���w�s�b��data.txt��
    File f = new File("data.txt");
    System.out.println("�O�_�s�b? " + f.exists());
    System.out.println("�ɮת�����W��(�]�A�ؿ�)? "
        + f.getCanonicalPath());
    System.out.println("�O�_���@���ɮ�? " + f.isFile());
    System.out.println("�ɮת��j�p " + f.length());
    System.out.println("�ɮצW�� " + f.getName());
  }
}
