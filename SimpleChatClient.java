/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author daniel
 */
public class SimpleChatClient {
    
    JTextArea incoming;
    JTextField outgoing;
    BufferedReader reader;
    PrintWriter writer;
    Socket sock;
    
    public void go(){
        JFrame frame = new JFrame("Ridiculamente simples cliente de chat");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15, 50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(new SendButtonActionListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setupNetWorking();
        
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }

    private void setupNetWorking() {
        try {
            sock = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(
                    sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Conexão estabelecida");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class IncomingReader implements Runnable {

        public IncomingReader() {
        }

        @Override
        public void run() {
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("lida " + message);
                    incoming.append(message + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class SendButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent ev){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            } catch(Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    
    public static void main(String[] args) {
        new SimpleChatClient().go();
    }
    
}
