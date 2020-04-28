package com.ch12;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class SimpleFile {
  public static void main(String[] args)
      throws IOException {
    // File file = new File("c:\\net\\d1\\d2\\d3");
    // System.out.println("目錄建立是否成功?"+file.mkdirs());

    File file = new File("c:\\net\\file1.txt");
    System.out.println("檔案是否存在?" + file.exists());
    if (!file.exists()) {
      System.out.println("新建檔案是否成功?"
          + file.createNewFile());
    }
    System.out.println("是否是目錄:" + file.isDirectory());
    System.out.println("是否是檔案:" + file.isFile());
    System.out.println("檔案名稱:" + file.getName());
    System.out.println("檔案路徑:" + file.getPath());
    System.out.println("上層目錄:" + file.getParent());
    System.out.println("目前目錄:"
        + file.getCanonicalPath());
    System.out.println("修改時間:"
        + new Date(file.lastModified()));
    System.out.println("檔案大小:" + file.length());

  }
}
