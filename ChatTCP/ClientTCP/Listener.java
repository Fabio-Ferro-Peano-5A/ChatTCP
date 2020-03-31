/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Ferro Fabio
 */
 
public class Listener implements Runnable{
    private Socket client;

    // Costruttore
    public Listener(Socket client) {
        this.client = client;
        System.out.println("Server connesso con: " + client);
    }
    
    @Override
    public void run() {
        
        // Connessione con il socket
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }catch(IOException e){
            System.out.println("Errore!");
            System.exit(-1);
        }
        
        // Testo ricevuto
        String messaggioServer = "";
        try{
            while((messaggioServer = in.readLine()) != null){
                System.out.println(messaggioServer);
                // Se il messaggio ricevuto dal server contiene "Arrivederci.", chiudi il client
                if(messaggioServer.contains("Arrivederci.")){
                    client.close();
                    System.exit(0);
                    break;
        }
    }
    
}catch(IOException e){
    try{
        System.out.println("Il server ha terminato la connessione");
        client.close();
        System.exit(-1);
    }catch(IOException exc){
        System.out.println("Errore nella chiusura del socket");
        System.exit(-1);
    }
   }
  }
}