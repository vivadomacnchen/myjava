package com.chx;

import javax.swing.JOptionPane;

public class DialogTester2 {
  public static void main(String[] args) {
    int n = JOptionPane.showConfirmDialog(null,
        "�T�{�T��");
    System.out.println(n);
    switch (n) {
    case JOptionPane.YES_OPTION:
      System.out.println("�ϥΪ̫��U YES");
      break;
    case JOptionPane.NO_OPTION:
      System.out.println("�ϥΪ̫��U NO");
      break;
    case JOptionPane.CANCEL_OPTION:
      System.out.println("�ϥΪ̫��U CANCEL");
      break;
    }
  }
}
