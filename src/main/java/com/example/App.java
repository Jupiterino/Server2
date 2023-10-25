package com.example; // SERVER

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        try {
            System.out.println("1 SERVER partito in esecuzione...");

            
            ServerSocket server = new ServerSocket(3000);
            do{
                Socket client = server.accept();
                MioThread m = new MioThread(client);
                m.start();
            }while(true);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza dal server !");
            System.exit(1);
        } 

            
    }
    
}