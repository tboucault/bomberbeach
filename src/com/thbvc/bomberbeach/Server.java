package com.thbvc.bomberbeach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class Server {
	JTextPane chat;
	JTextField chatfield;

	 static Bomberbeach b = new Bomberbeach();
	 private static int skt_port = 5000;
	 static int level = 1;
     private ServerSocket serveurSocket  ;
     private Socket clientSocket ;
     private BufferedReader in;
     private PrintWriter out;
     
     public Server(JTextPane chat,JTextField chatfield){
     	 this.chat = chat;
    	 this.chatfield = chatfield;
      }

     public void envoyer(String message){
         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
            	 if(message.charAt(0)!='*'){ //ce n'est pas un message système donc on l'envoi dans le tchat
            		 chat.setText(chat.getText() + "\nJoueur 1: "+message);
            	 }
             }
         });
         out.println(message);
         out.flush();
     }


     public void traitement(String msg){
		//String line = in.readLine();
        //System.out.println(line); //debug
        String[] parts = msg.split("\\|");
        String x = parts[1];
        String y = parts[2];
        System.out.println("posX 2to1: "+x); //debug
        System.out.println("posY 2to1: "+y); //debug
		b.receive_pos_player(2,Integer.parseInt(x),Integer.parseInt(y));    
     }
     
     
     
     public void start(String ip, int port){
     try {
       serveurSocket = new ServerSocket(port);
       clientSocket = serveurSocket.accept();
       System.out.println("Serveur démarré\n###################");
       b.setlevel(level);
       System.out.println("Joueur 1 rejoint la partie");
       out = new PrintWriter(clientSocket.getOutputStream());
       in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
      
   
       Thread recevoir= new Thread(new Runnable() {
          String msg ;
          @Override
          public void run() {
             try {
                msg = in.readLine();
                //tant que le client est connecté
                while(msg!=null){
                   System.out.println("Client : "+msg);
                   // update the label IN THE EDT!
                   SwingUtilities.invokeLater(new Runnable() {
                       @Override
                       public void run() {
                    	 if(msg.charAt(0)=='*'){//message système: on recoit la position du joueur 2 et la traite
                    		 //todo: on lit le message et envoi la pos x et y
                    		 traitement(msg);
                      	 }
                      	 else //message texte, on affiche le message du joueur 2 dans notre tchat
                      		 chat.setText(chat.getText() +"\nJoueur 2: "+ msg);
                       }
                   });
                   //traitement(msg,tableau);  
                   msg = in.readLine();
                }
                //sortir de la boucle si le client a déconecté
                System.out.println("Client déconecté");
                b.player_leave("2");
                //fermer le flux et la session socket
                out.close();
                clientSocket.close();
                serveurSocket.close();
             } catch (IOException e) {
                  e.printStackTrace();
             }
         }
      });
      recevoir.start();
      }catch (IOException e) {
         e.printStackTrace();
      }
     }

}
