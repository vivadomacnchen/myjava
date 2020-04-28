package com.ch14;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
  Session session ;
  
  public Mailer(String prot, String server){
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "msa.hinet.net");
    props.put("mail.transport.protocol", "smtp");
    session = Session.getDefaultInstance(props);
  }

  public void send(String from, String to,
      String subject, String text) {
    Message msg = new MimeMessage(session);
    try {
      InternetAddress fromAddress = 
          new InternetAddress(from);
      InternetAddress toAddress = 
          new InternetAddress(to);
      msg.setFrom(fromAddress);
      msg.setRecipient(
          Message.RecipientType.TO,toAddress);
      msg.setSubject(subject);
      msg.setText(text);
      Transport.send(msg);
    } catch (AddressException e) {
      e.printStackTrace();
      System.out.println("�l���}���~");
    } catch (MessagingException e) {
      e.printStackTrace();
      System.out.println("�T�����~");
    }
  }
  
  public Message[] read(String host, String user, 
      String pw){
    Message[] messages = null;
    try {
      Store store = session.getStore("pop3");
      store.connect(host, user, pw);
      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_ONLY);
      messages = inbox.getMessages( );
    } catch (NoSuchProviderException e) {
      e.printStackTrace();
      System.out.println("Ū�H��w���~");
    } catch (MessagingException e) {
      e.printStackTrace();
      System.out.println("�T�����~");
    }
    return messages;
  }
  
  public static void main(String[] args) throws 
      MessagingException, IOException {
    Mailer mailer = new Mailer("smtp", "msa.hinet.net");
    Message[] messages = 
          mailer.read("snpy.org", "eric", "mypassword");
    for (Message msg : messages){
      System.out.println("�e���:"+msg.getFrom()[0]);
      System.out.println("���D:"+msg.getSubject());
      System.out.println("���e:"+msg.getContent());
    }
    
//    Message[] messages = mailer.read("j.snpy.org", "eric", "jjj123");
//    mailer.send("jack@test.com", "eric@snpy.org", "�Ĥ@�ʴ��իH","���հT�����e(1)");
//    mailer.send("tom@abc.com", "eric@snpy.org", "�ĤG�ʴ��իH","���հT�����e(2)");
  }
}
