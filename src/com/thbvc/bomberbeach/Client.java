package com.thbvc.bomberbeach;

import java.net.Socket;


public class Client implements Communicateur {

/************************************************ D�claration des variables : */


	private String IPServeur;
	private int port;
	private Connexion cnx;
	private int idClient;	

/********************************************** D�claration du constructeur : */


	public Client(String IPServeur, int port) {
		
		this.IPServeur = IPServeur;
		this.port = port;
		creationClient();
	}	
	

/************************************************* D�claration des m�thodes : */


	public void creationClient() {
		
		
		// Creation de la socket {serveur}.
		try {
			
			Socket s = new Socket(IPServeur, port);
			
			// Creation de la connection {Serveur, moi}.
			cnx = new Connexion(s, this);
			
			System.out.println("Vous etes connecte au serveur ...");
			
		} catch (Exception e) {
			
			System.out.println("Erreur lors de la creation du client : " + e);
		}
	}
	
	
	public void traiteMessage(Object O)	{	
		
		try	{
				if (O instanceof Message) {
					
					System.out.println("Message recu : " + O);
				
				} else {
					
					idClient = Integer.parseInt((String) O);
					
					System.out.println("Le serveur vous attribue l'identifiant " + idClient + ".");
				}		
			
		} catch (Exception e) {
		
			System.out.println("Objet recu non identifie !");
		}	
	}
	
	
	public Connexion getCnx() {
		
		return cnx;
	}
	
	public int getIdClient() {
		
		return idClient;
	}
    

	/*
	JTextPane chat;
	JTextField chatfield;

	  static Bomberbeach b;
	  static Map m;
	  static int level = 1;
	  private static int skt_port = 5000;
	  private static String skt_ip = "127.0.0.1";
      private Socket clientSocket;
      private BufferedReader in;
      private PrintWriter out;

      public Client(JTextPane chat,JTextField chatfield, Bomberbeach b){
     	 this.b = b;
      	 this.chat = chat;
     	 this.chatfield = chatfield;
       }
   
      public void envoyer(String message){
          SwingUtilities.invokeLater(new Runnable() {
              @Override
              public void run() {
            	 if(message.charAt(0)!='*' && message.charAt(0)!='@'){ //ce n'est pas un message système donc on l'envoi dans le tchat
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

      public void traitement_boost(String msg){
  		  //String line = in.readLine();
          //System.out.println(line); //debug
          String[] parts = msg.split("\\|");
          String type = parts[1];
          String id = parts[2];
          System.out.println(type+" 1to2: "+ id); //debug
  		  b.receive_boost_player(1,Integer.parseInt(id));    
      }
      
      public void start(String ip, int port){
      try {
         /*
         * les informations du serveur ( port et adresse IP ou nom d'hote
         * 127.0.0.1 est l'adresse local de la machine
         *
         clientSocket = new Socket(ip,port);
         System.out.println("Client démarré\n###################");
         m.setlevel(level);
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
                    		 //on lit le message et envoi la pos x et y
                    		 traitement(msg);                    		 
                      	 }
                    	 else if(msg.charAt(0)=='@'){//message système: on recoit l'info qu'un joueur à utilisé un boost
                    		 traitement_boost(msg);
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
  }*/
}
