package com.ch06;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestClient {
  public static void main(String[] args)
      throws IOException {
    int port = 9971;
    InetAddress server = 
      InetAddress.getByName("192.168.1.10");
    // 準備接收訊息封包的socket與packet
    byte[] data = new byte[20];
    byte[] msg = "AYT".getBytes();
    DatagramSocket rsocket = 
      new DatagramSocket(port);
    //DatagramSocket
    //This class represents a socket for sending and receiving datagram packets.
    //A datagram socket is the sending or receiving point for a packet delivery service. Each packet sent or received on a datagram socket is individually addressed and routed. Multiple packets sent from one machine to another may be routed differently, and may arrive in any order.
    //
    //Where possible, a newly constructed DatagramSocket has the SO_BROADCAST socket option enabled so as to allow the transmission of broadcast datagrams. In order to receive broadcast packets a DatagramSocket should be bound to the wildcard address. In some implementations, broadcast packets may also be received when a DatagramSocket is bound to a more specific address.
    //
    /* 
    此類別表示用來發送和接收資料電報封包的Socket。

資料電報Socket是包投遞服務的發送或接收點。每個在資料電報Socket上發送或接收的套件都是單獨編址和路由的。從一台機器發送到另一台機器的多個包可能選擇不同的路由，也可能按不同的順序到達。

在 DatagramSocket 上總是啟用 UDP 廣播發送。為了接收廣播包，應該將 DatagramSocket 綁定到通配符位址。在某些實作中，將 DatagramSocket 綁定到一個更加具體的位址時廣播包也可以被接收。 
    
    */
    DatagramPacket rpacket = 
      new DatagramPacket(data, 20);
    // 準備傳送封包
    DatagramPacket p = new DatagramPacket(msg,
        msg.length, server, 9970);
    rsocket.send(p);
    System.out.println("已送出查詢封包");
    // 準備接收Server傳來的封包
    rsocket.receive(rpacket);
    System.out.println("收到伺服器的回應封包:"
        + new String(data));
    /* 
    一、DatagramPacket類:

如果把DatagramSocket比作建立的港口碼頭,那麼DatagramPacket就是傳送和接收資料的集裝箱。
建構函式:一個用來接收資料,一個用來發送資料
public DatagramPacket(byte[] buf,int length) //接收資料
構造 DatagramPacket 用來接收長度為 ilength 的包。
public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
構造資料報文包用來把長度為 ilength 的包傳送到指定宿主的指定的埠號。
getAddress()
返回接收或傳送此資料報文的機器的 IP 地址。
getData()
返回接收的資料或傳送出的資料。
getLength()
返回傳送出的或接收到的資料的長度。
getPort()
返回接收或傳送該資料報文的遠端主機埠號。

二、DatagramSocket類

此類表示用來發送和接收資料報包的套接字。 資料報套接字是包投遞服務的傳送或接收點。
DatagramSocket(int port) 建立資料報套接字並將其繫結到本地主機上的指定埠。
DatagramSocket(int port, InetAddress laddr) 建立資料報套接字,將其繫結到指定的本地地址。

receive(DatagramPacket p)
從此套接字接收資料報包。
void send(DatagramPacket p)
從此套接字傳送資料報包。
bind(SocketAddress addr)
將此 DatagramSocket 繫結到特定的地址和埠。
void close()
關閉此資料報套接字。
void connect(InetAddress address, int port)
將套接字連線到此套接字的遠端地址。
void connect(SocketAddress addr)
將此套接字連線到遠端套接字地址(IP 地址 + 埠號)。
void disconnect()
斷開套接字的連線。
getInetAddress()
返回此套接字連線的地址。
InetAddress getLocalAddress()
獲取套接字繫結的本地地址。

三、InetAddress類
InetAddress用於表示計算機IP地址的一個類,而在日常應用中的IP地址用"192.168.0.1",
"WWW.it315.org"等字串格式表示的。
getByName方法
getHostAddress方法 
    */
  }
}
