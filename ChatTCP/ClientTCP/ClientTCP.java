/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.xml.bind.Marshaller.Listener;

/**
 *
 * @author Ferro Fabio
 */
 
public class ClientTCP {

    /**
     * @param args the command line arguments
     */
     
    public static void main(String[] args) {
        // TODO code application logic here
        String host = "127.0.0.1";
        int porta = 1234;
        try {
            InetAddress indirizzo = InetAddress.getByName(host); // Prendo l'indirizzo IP inserito in linea di comando
            Socket socketClient = new Socket(indirizzo, porta); // Creazione socket
            System.out.println("Usasa Ctrl-C per terminare e INVIO per spedire il messaggio.\n"); // Informazioni
            
            // Invio il socket per la connessione al thread
            try {
                Listener l = new Listener(socketClient);
                Thread tr = new Thread((Runnable) l);
                tr.start();
            }catch(Exception e){
                System.out.println("Connessione fallita con: ");
            }
            
            PrintWriter out = new PrintWriter(socketClient.getOutputStream(), true); // connessione al socket
            
            // Connessione al BufferedReader per inserire il testo
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String inputUtente;
            
            // Leggo il testo inserito da inviare al server
            System.out.println(">");
            while((inputUtente = in.readLine()) != null){
                // Scrivo il messaggio da inserire nel socket 
                out.println(inputUtente);
                System.out.println("Messaggio inviato a: " + inputUtente);
                System.out.println(">");
            }
            
            // Chiudo il socket
            socketClient.close();
            System.out.println("Fine connessione");
        }catch(IOException e){
            System.out.println("Il server ha terminato la connessione: ");
            e.printStackTrace();
        }
    }
    
}