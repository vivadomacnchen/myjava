package com.ch04;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ExceptionHandling411 {
  // ��main�w�qthrows�ҥ~
  public static void main(String[] args)
      throws FileNotFoundException {
    FileReader br = new FileReader("data.txt");
  }
}
