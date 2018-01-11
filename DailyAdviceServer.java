/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.io.*;
import java.net.*;

/**
 *
 * @author daniel
 */
public class DailyAdviceServer {
    
    String[] adviceList = {"Morda pedaços menores",
        "Use o jeans apertado! Não, ele não faz você parecer mais gorda!",
        "Só vou dizer uma palavra: inapropriado",
        "Pelo menos hoje, seja honesto(a). Diga a seu chefe o que você realmente pensa",
        "Reconsidere esse corte de cabelo"
    };
    
    public void go(){
        try{
            ServerSocket serversSocket = new ServerSocket(4242);
            
            while(true){
                Socket socket =serversSocket.accept();
                
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ex){
        ex.printStackTrace();
        }
    }    
    
    private String getAdvice(){
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }
    
    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
