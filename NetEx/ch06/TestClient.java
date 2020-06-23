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
    // �ǳƱ����T���ʥ]��socket�Ppacket
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
    �����O��ܥΨӵo�e�M������ƹq���ʥ]��Socket�C

��ƹq��Socket�O�]�뻼�A�Ȫ��o�e�α����I�C�C�Ӧb��ƹq��Socket�W�o�e�α������M�󳣬O��W�s�}�M���Ѫ��C�q�@�x�����o�e��t�@�x�������h�ӥ]�i���ܤ��P�����ѡA�]�i������P�����Ǩ�F�C

�b DatagramSocket �W�`�O�ҥ� UDP �s���o�e�C���F�����s���]�A���ӱN DatagramSocket �j�w��q�t�Ŧ�}�C�b�Y�ǹ�@���A�N DatagramSocket �j�w��@�ӧ�[���骺��}�ɼs���]�]�i�H�Q�����C 
    
    */
    DatagramPacket rpacket = 
      new DatagramPacket(data, 20);
    // �ǳƶǰe�ʥ]
    DatagramPacket p = new DatagramPacket(msg,
        msg.length, server, 9970);
    rsocket.send(p);
    System.out.println("�w�e�X�d�߫ʥ]");
    // �ǳƱ���Server�ǨӪ��ʥ]
    rsocket.receive(rpacket);
    System.out.println("������A�����^���ʥ]:"
        + new String(data));
    /* 
    �@�BDatagramPacket��:

�p�G��DatagramSocket��@�إߪ���f�X�Y,����DatagramPacket�N�O�ǰe�M������ƪ����˽c�C
�غc�禡:�@�ӥΨӱ������,�@�ӥΨӵo�e���
public DatagramPacket(byte[] buf,int length) //�������
�c�y DatagramPacket �Ψӱ������׬� ilength ���]�C
public DatagramPacket(byte[] buf,int length,InetAddress address,int port)
�c�y��Ƴ���]�Ψӧ���׬� ilength ���]�ǰe����w�J�D�����w���𸹡C
getAddress()
��^�����ζǰe����Ƴ��媺������ IP �a�}�C
getData()
��^��������Ʃζǰe�X����ơC
getLength()
��^�ǰe�X���α����쪺��ƪ����סC
getPort()
��^�����ζǰe�Ӹ�Ƴ��媺���ݥD���𸹡C

�G�BDatagramSocket��

������ܥΨӵo�e�M������Ƴ��]���M���r�C ��Ƴ��M���r�O�]�뻼�A�Ȫ��ǰe�α����I�C
DatagramSocket(int port) �إ߸�Ƴ��M���r�ñN��ô���쥻�a�D���W�����w��C
DatagramSocket(int port, InetAddress laddr) �إ߸�Ƴ��M���r,�N��ô������w�����a�a�}�C

receive(DatagramPacket p)
�q���M���r������Ƴ��]�C
void send(DatagramPacket p)
�q���M���r�ǰe��Ƴ��]�C
bind(SocketAddress addr)
�N�� DatagramSocket ô����S�w���a�}�M��C
void close()
��������Ƴ��M���r�C
void connect(InetAddress address, int port)
�N�M���r�s�u�즹�M���r�����ݦa�}�C
void connect(SocketAddress addr)
�N���M���r�s�u�컷�ݮM���r�a�}(IP �a�} + ��)�C
void disconnect()
�_�}�M���r���s�u�C
getInetAddress()
��^���M���r�s�u���a�}�C
InetAddress getLocalAddress()
����M���rô�������a�a�}�C

�T�BInetAddress��
InetAddress�Ω��ܭp���IP�a�}���@����,�Ӧb��`���Τ���IP�a�}��"192.168.0.1",
"WWW.it315.org"���r��榡��ܪ��C
getByName��k
getHostAddress��k 
    */
  }
}
