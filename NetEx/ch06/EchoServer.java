/*
package com.ch06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
  public void echo() {
    try {
      ServerSocket ss = new ServerSocket(23);
      Socket socket = ss.accept();
      System.out.println("\nClient Get\n");
      // �s�u��,���o��X�J��y
      OutputStream rawOut = socket.getOutputStream();
      System.out.println("\nClient 1\n");
      //InputStream rawIn = socket.getInputStream();
      System.out.println("\nClient 2\n");
      PrintWriter out = new PrintWriter(rawOut);
      System.out.println("\nClient 3\n");
      //BufferedReader in = new BufferedReader(new InputStreamReader(rawIn));
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
      System.out.println("\nClient 4\n");
      // ���ݫȤ�ݰe�Ӧr��
      String data2 = in.readLine();
      System.out.println("\nClient 5\n");
      System.out.println("Got " + data2+"\n");
      //System.out.println("Got ");
      System.out.println("\nClient 6\n");
      // �N�ǨӪ���Ʀ^�e���Ȥ��
      out.println(data2);
      System.out.println("\nClient 7\n");
      out.flush();
      System.out.println("\nClient 8\n");
      //System.out.println("Send " + data);
      System.out.println(data2);
      System.out.println("TEst data"+data2);
      System.out.println("\nClient 9\n");
      // �����s�u�귽
      in.close();
      System.out.println("\nClient 10\n");
      out.close();
      System.out.println("\nClient 11\n");
      socket.close();
      System.out.println("\nClient 12\n");
      ss.close();
      System.out.println("\nClient 13\n");
    } catch (IOException e) {
      System.out.println("��X�J���~");
    }
  }

  public static void main(String[] args) {
    EchoServer eserver = new EchoServer();
    eserver.echo();
  }
}
//
package onlyfun.caterpillar;
*/
//Test OK below seems first some word in the first beginning cause error
import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) { 
        final int port = 23; 
        ServerSocket serverSkt; 
        Socket skt; 
        BufferedReader sktReader; 
        String message;      
        PrintStream sktStream; 
        
        try { 
            serverSkt = new ServerSocket(port); 
            try { 
                while(true) { 
                    System.out.printf("�s���� %d �����s�u��......%n", port); 
                    skt = serverSkt.accept(); 
                    System.out.printf("�P %s �إ߳s�u%n", 
                            skt.getInetAddress().toString()); 

                    sktReader = new BufferedReader(new 
                            InputStreamReader(skt.getInputStream())); 

                    while((message = sktReader.readLine()) != null) { 
                        if(message.equals("/bye")) { 
                            System.out.println("Bye!"); 
                            skt.close(); 
                            break; 
                        } 

                        System.out.printf("Client: %s%n", message); 
                        sktStream = new PrintStream(skt.getOutputStream()); 
                        sktStream.printf("echo: %s%n", message); 
                    } 
                } 
            } 
            catch(IOException e) { 
                System.out.println(e.toString()); 
            } 
        } 
        catch(IOException e) { 
            System.out.println(e.toString()); 
        } 
    }  
}
//
