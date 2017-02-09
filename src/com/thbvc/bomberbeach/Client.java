package com.thbvc.bomberbeach;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class Client {
	JTextPane chat;
	JTextField chatfield;

	  static Bomberbeach b = new Bomberbeach();
	  static int level = 1;
	  private static int skt_port = 5000;
	  private static String skt_ip = "127.0.0.1";
      private Socket clientSocket;
      private BufferedReader in;
      private PrintWriter out;

      public Client(JTextPane chat,JTextField chatfield){
     	 this.chat = chat;
    	 this.chatfield = chatfield;
      }
   
      public void envoyer(String message){
          SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
            	 if(message.charAt(0)!='*'){ //ce n'est pas un message système donc on l'envoi dans le tchat
            		 chat.setText(chat.getText() + "\nJoueur 2: "+message);
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
          System.out.println("posX 1to2: "+x); //debug
          System.out.println("posY 1to2: "+y); //debug
  		  b.receive_pos_player(1,Integer.parseInt(x),Integer.parseInt(y));    
      }
      
      public void start(String ip, int port){
      try {
         /*
         * les informations du serveur ( port et adresse IP ou nom d'hote
         * 127.0.0.1 est l'adresse local de la machine
         */
         clientSocket = new Socket(ip,port);
         System.out.println("Client démarré\n###################");
         b.setlevel(level);
         System.out.println("Joueur 2 rejoint la partie");
   
         //flux pour envoyer
         out = new PrintWriter(clientSocket.getOutputStream());
         //flux pour recevoir
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
   
        Thread recevoir = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
               try {
                 msg = in.readLine();
                 while(msg!=null){
                    System.out.println("Serveur : "+msg);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                    	 if(msg.charAt(0)=='*'){//message système: on recoit la position du joueur 1 et la traite
                    		 //todo: on lit le message et envoi la pos x et y
                    		 traitement(msg);                    		 
                      	 }
                      	 else //message texte, on affiche le message du joueur 1 dans notre tchat
                      		 chat.setText(chat.getText() +"\nJoueur 1: "+ msg);
                        }
                    });
                    //traitement(msg,tableau);  
                    msg = in.readLine();
                 }
                 System.out.println("Serveur déconecté");
                 b.player_leave("1");
                 out.close();
                 clientSocket.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });
        recevoir.start();
        
   
      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}
