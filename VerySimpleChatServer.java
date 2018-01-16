/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author daniel
 */
public class VerySimpleChatServer {
    
    //ArrayList contendo todos os outputs dos clientes
    ArrayList clientOutputStreams;
    
    /**
     * Classe que lida com todas as solicitações client-side
     */
    public class CLientHandler implements Runnable{
    
        BufferedReader reader;
        Socket sock;
        
        /**
         * Construtor da classe ClientHandler
         * @param clientSocket Socket com o endereco de Ip do cliente
         */
        public CLientHandler(Socket clientSocket){
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        /**
         * Método que inicia a thread
         */
        public void run(){
            String message;
            try {
                while((message = reader.readLine()) != null){
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }
    
    public void go(){
        clientOutputStreams = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(5000);
            while(true){
                Socket clientSocket = serverSock.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);
                Thread t = new Thread(new CLientHandler(clientSocket));
                t.start();
                System.out.println("got a connection");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
