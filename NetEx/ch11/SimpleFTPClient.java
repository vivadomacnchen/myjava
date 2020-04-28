package com.ch11;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class SimpleFTPClient {
  public static void main(String[] args) throws IOException {
    FTPClient ftp = new FTPClient();
    // �n�J
    ftp.connect("ftp.isu.edu.tw", 21);
    ftp.login("anonymous", "aa@com.tw");
    // �����ؿ���/Linux
    ftp.changeWorkingDirectory("/Linux");
    System.out.println("�ثe�ؿ�:" + ftp.printWorkingDirectory());
    // �C�X�ɮײM��
    FTPFile[] files = ftp.listFiles();
    for (FTPFile f : files){
    	System.out.println(f.getName());
    }
    // �U���ɮ�
    String remoteFile = "/Linux/00_index.txt";
    String downloadFile = "00_index.txt";
    // �ǳƤU���ɮת���X��Ƭy
    BufferedOutputStream output = new BufferedOutputStream(
    		new FileOutputStream(downloadFile));
    // ����U���u�@
    boolean success = ftp.retrieveFile(remoteFile, output);
    output.close();
    System.out.println("���\�U��?"+success);
    // ����ftp�s�u
    ftp.disconnect();
  }
}
