package com.chx;

import javax.swing.JOptionPane;

public class DialogTester2 {
  public static void main(String[] args) {
    int n = JOptionPane.showConfirmDialog(null,
        "確認訊息");
    System.out.println(n);
    switch (n) {
    case JOptionPane.YES_OPTION:
      System.out.println("使用者按下 YES");
      break;
    case JOptionPane.NO_OPTION:
      System.out.println("使用者按下 NO");
      break;
    case JOptionPane.CANCEL_OPTION:
      System.out.println("使用者按下 CANCEL");
      break;
    }
  }
}
