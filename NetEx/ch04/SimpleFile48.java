package com.ch04;

import java.io.File;

public class SimpleFile48 {
  public static void main(String[] args) {
    File f = new File("C:\\Windows\\");
    System.out.println("�O�_�s�b? " + f.exists());
    System.out.println("�O�_���ؿ�? " + f.isDirectory());
    System.out.println("�O�_���@���ɮ�? " + f.isFile());
    System.out.println("�O�_�i�HŪ��? " + f.canRead());
    System.out.println("�W�h�ؿ���? " + f.getParent());
  }
}
