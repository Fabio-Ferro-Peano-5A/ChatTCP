/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servertcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ferro Fabio
 */
 
public class ServerTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int porta = 1234; // Porta con cui si collegherà il server
        try{
            // Server in ascolto
            ServerSocket socketServer = new ServerSocket(porta);
            System.out.println("Server in esecuzione");

            while(true){
                SocketWorker worker;
                try {
                    Socket nuovoSocket = socketServer.accept();
                    worker = new SocketWorker(nuovoSocket);
                    Thread tr = new Thread(worker); // Genero un thread
                    tr.start(); // Eseguo il worker nel thread
                } catch (IOException e) {
                    System.out.println("Connessione con il client fallita: ");
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            System.out.println("Porta: " + porta + " non trovata!");
            System.exit(-1);
        }

        
    }
}