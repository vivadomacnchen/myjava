package com.ch07;

public class PriorityTester {
  public static void main(String[] args) {
    Thread thr = new Thread();
    // �L�Xthr�ثe���u���v��
    System.out.println("thr�ثe���u���v��:"
        + thr.getPriority());
    // �ܧ󬰳̰��u���v
    thr.setPriority(Thread.MAX_PRIORITY);
    System.out.println("thr�ثe���u���v��:"
        + thr.getPriority());
    // �ܧ��u���v�Ȭ�8
    thr.setPriority(8);
    System.out.println("thr�ثe���u���v��:"
        + thr.getPriority());
  }
}
