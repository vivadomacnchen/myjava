package com.ch06;

import java.io.*;
import java.net.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class TimeServer {
  public TimeServer() 
  {
  }

  public void report() 
  {
    try { 
            ServerSocket ss = new ServerSocket(23);
            Socket socket;
            //Socket socket = ss.accept();
            while(true) 
            {
                String message; 
                System.out.printf("�s���� �����s�u��......%n"); 
                socket = ss.accept(); 
                System.out.printf("�P %s �إ߳s�u%n", socket.getInetAddress().toString()); 
                BufferedReader sktReader;
                sktReader = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                PrintStream sktStream;
                Date date;
                while((message = sktReader.readLine()) != null) 
                {
                    if(message.equals("/bye")) 
                    {
                        System.out.println("Bye!"); 
                        socket.close();
                        ss.close(); 
                        out.close();
                        break; 
                    } 
                    date = Calendar.getInstance().getTime();
                    out.println(date);
                    out.flush();
                    System.out.printf("Client: %s%n", message); 
                    sktStream = new PrintStream(socket.getOutputStream()); 
                    sktStream.printf("echo: %s%n", message); 
                } 
            } 
        } 
        catch (IOException e) 
        {
          System.out.println("��X�J���~");
        }
  }

  public static void main(String[] args) {
    TimeServer tserver = new TimeServer();
    tserver.report();
  }
}
