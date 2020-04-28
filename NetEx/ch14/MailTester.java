package com.ch14;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTester {
  public static void main(String[] args)
      throws MessagingException {
    java.util.Properties props = 
      System.getProperties();
    props.put("mail.smtp.host", "msa.hinet.net");
    props.put("mail.transport.protocol", "smtp");
    javax.mail.Session session = javax.mail.Session
        .getDefaultInstance(props);
    InternetAddress from = 
      new InternetAddress("jack@test.com");
    //請更改為讀者收得到信的郵件位址
    InternetAddress to = 
      new InternetAddress("eric@snpy.org");
    Message msg = new MimeMessage(session);
    msg.setFrom(from);
    msg.setRecipient(Message.RecipientType.TO, to);
    msg.setSubject("mail for testing");
    msg.setText("測試訊息內容");
    Transport.send(msg);
    System.out.println("成功寄出");
  }
}
