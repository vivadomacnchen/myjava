package com.ch04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SimpleFile45 {
  public static void main(String[] args)
      throws FileNotFoundException {
    PrintWriter out = new PrintWriter("data.txt");
    out.println("���ո�ƲĤ@��");
    out.println("���ո�ƲĤG��");
    out.println("���ո�ƲĤT��");
    out.println("���ո�Ʋĥ|��");
    out.println("���ո�ƲĤ���");
    out.flush();
    out.close();
  }
}
