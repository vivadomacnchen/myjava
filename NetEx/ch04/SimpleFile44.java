package com.ch04;

import java.io.FileWriter;
import java.io.IOException;

public class SimpleFile44 {
  public static void main(String[] args)
      throws IOException {
    FileWriter out = new FileWriter("data.txt");
    out.write("�o�O������");
    out.flush();
    out.close();
  }
}
