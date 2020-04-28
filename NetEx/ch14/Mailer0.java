package com.ch14;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;

public class Mailer0 {
  Session session ;
  
  public Mailer0(String prot, String server){
    Properties props = System.getProperties();
    props.put("mail.smtp.host", "msa.hinet.net");
    props.put("mail.transport.protocol", "smtp");
    session = Session.getDefaultInstance(props);
  }
  
  public static void main(String[] args) throws 
      MessagingException, IOException {
    Mailer mailer = 
        new Mailer("smtp", "msa.hinet.net");
    mailer.send("jack@test.com", "eric@snpy.org", 
        "第一封測試信","測試訊息內容(1)");
    mailer.send("tom@abc.com", "eric@snpy.org", 
        "第二封測試信","測試訊息內容(2)");
  }
}
