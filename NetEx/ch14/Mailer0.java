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
        "�Ĥ@�ʴ��իH","���հT�����e(1)");
    mailer.send("tom@abc.com", "eric@snpy.org", 
        "�ĤG�ʴ��իH","���հT�����e(2)");
  }
}
