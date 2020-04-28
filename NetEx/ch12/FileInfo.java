package com.ch12;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileInfo {
  public static void main(String[] args)
      throws IOException {
    File f = new File("c:\\net\\file1.txt");
    System.out.println("是否是目錄:" + f.isDirectory());
    System.out.println("是否是檔案:" + f.isFile());
    System.out.println("檔案名稱:" + f.getName());
    System.out.println("檔案路徑:" + f.getPath());
    System.out.println("上層目錄:" + f.getParent());
    System.out.println("目前目錄:"
        + f.getCanonicalPath());
    System.out.println("修改時間:"
        + new Date(f.lastModified()));
    System.out.println("檔案大小:" + f.length());
  }
}
