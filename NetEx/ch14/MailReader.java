package com.ch14;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class MailReader {
  public static void main(String[] args)
      throws MessagingException {
    Properties props = System.getProperties();
    Session session = 
        Session.getDefaultInstance(props);
    Store store = session.getStore("pop3");
    store.connect("snpy.org", "eric", "mypassword");
    Folder inbox = store.getFolder("INBOX");
    inbox.open(Folder.READ_ONLY);
    Message[] messages = inbox.getMessages();
    for (Message msg : messages) {
      try {
        msg.writeTo(System.out);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("¿é¥X¤J¿ù»~");
      }
    }
  }
}
