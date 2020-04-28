package com.ch14;

import javax.mail.MessagingException;

public class MailTester0 {
  public static void main(String[] args)
      throws MessagingException {
    java.util.Properties props = 
      System.getProperties();
    javax.mail.Session session = javax.mail.Session
        .getDefaultInstance(props);
  }
}
