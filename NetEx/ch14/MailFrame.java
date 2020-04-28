package com.ch14;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MailFrame {
  private JFrame frame;
  private JTable table;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MailFrame window = new MailFrame();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public MailFrame() {
    initialize();
  }

  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 450, 300);
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE);
    JPanel leftPanel = new JPanel();
    frame.getContentPane().add(leftPanel,
        BorderLayout.WEST);
    JButton button = new JButton("讀取信件");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Vector rows = new Vector();
        Mailer mailer = new Mailer("smtp",
            "msa.hinet.net");
        Message[] messages = mailer.read(
            "snpy.org", "eric", "mypassword");
        for (Message msg : messages) {
          Vector data = new Vector();
          try {
            data.add(msg.getFrom()[0]);
            data.add(msg.getSubject());
            data.add(msg.getContent());
            rows.add(data);
          } catch (MessagingException e1) {
            e1.printStackTrace();
          } catch (IOException e1) {
            e1.printStackTrace();
          }
        }
        Vector columnNames = new Vector();
        columnNames.add("寄件人");
        columnNames.add("標題");
        columnNames.add("內容");
        table.setModel(new DefaultTableModel(rows,
            columnNames));
      }
    });
    leftPanel.add(button);
    JPanel centerPanel = new JPanel();
    frame.getContentPane().add(centerPanel,
        BorderLayout.CENTER);
    centerPanel.setLayout(new BorderLayout(0, 0));
    table = new JTable();
    centerPanel.add(table);
  }
}
