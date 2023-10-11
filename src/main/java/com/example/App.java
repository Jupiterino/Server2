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
public class App 
{
    public static void main( String[] args )
    {
        
        try{
            System.out.println("1 SERVER partito in esecuzione...");
            
            int Ncasuale = (int)(Math.random() * 101);
            System.out.println(Ncasuale);
            ServerSocket server = new ServerSocket(3000);
            Socket client = server.accept();

            System.out.println("un client si è collegato");
            //server.close();
            int Nutente = 0;
            int cont=0;
            BufferedReader inDalClient = new BufferedReader(new InputStreamReader (client.getInputStream()));//tubo nel quale ascolto
            DataOutputStream outVersoClient = new DataOutputStream(client.getOutputStream());//tubo nel quale parlo
            do{
            cont++;
            String stringaRicevuta = inDalClient.readLine(); //rimango in attesa nell ingresso
            System.out.println(stringaRicevuta);
            Nutente = Integer.parseInt(stringaRicevuta);

            //System.out.println(Nutente+" e "+Ncasuale);
            if(Nutente > Ncasuale){   
                outVersoClient.writeBytes("2\n");
            }else if(Nutente < Ncasuale){
                outVersoClient.writeBytes("1\n");
            }else{
                outVersoClient.writeBytes("3\n");
                outVersoClient.writeBytes( Integer.toString(cont)+'\n');
            }
            
            }while(Ncasuale != Nutente); 
            client.close();
            server.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza dal server !");
            System.exit(1);
        }
    }
}