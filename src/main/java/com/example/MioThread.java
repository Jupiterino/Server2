package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class MioThread extends Thread {
    Socket s;

    public MioThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {

            System.out.println("un client si è collegato");
            // server.close();
            int Nutente = 0;
            int cont = 0;
            BufferedReader inDalClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream outVersoClient = new DataOutputStream(s.getOutputStream());
            int Ncasuale = (int) (Math.random() * 101);
            System.out.println(Ncasuale);
            do {
                cont++;
                String stringaRicevuta = inDalClient.readLine(); // rimango in attesa nell ingresso
                System.out.println(stringaRicevuta);
                Nutente = Integer.parseInt(stringaRicevuta);

                // System.out.println(Nutente+" e "+Ncasuale);
                if (Nutente > Ncasuale) {
                    outVersoClient.writeBytes("2\n");
                } else if (Nutente < Ncasuale) {
                    outVersoClient.writeBytes("1\n");
                } else {
                    outVersoClient.writeBytes("3\n");
                    outVersoClient.writeBytes(Integer.toString(cont) + '\n');
                }

            } while (Ncasuale != Nutente);
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza dal server !");
            System.exit(1);
        }
    }
}
