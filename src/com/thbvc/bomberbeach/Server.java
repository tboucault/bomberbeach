package com.thbvc.bomberbeach;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

public class Server implements Communicateur, Runnable {

	
	
/************************************************ D�claration des variables : */

	private int port;
	private int nbCli;
	private ServerSocket serv;
	private Connexion cnx;
	public LinkedList lCli;
	private LinkedList lMessages;
	

/********************************************** D�claration du constructeur : */


	public Server(int port, int nbCli) {
		
		this.port = port;
		this.nbCli = nbCli;
		lMessages = new LinkedList();
		creationServeur();
	}	
	
	
/************************************************* D�claration des methodes : */

	
	public void creationServeur() {
	
		try {
	
			serv = new ServerSocket(port, nbCli);

		} catch (Exception e) {
			
			System.out.println("Erreur dans la creation du serveur : " + e);	
		}
		
		Thread t = new Thread(this);
		t.start();				
	}
	
		
	public void run()
	{
		lCli = new LinkedList();
		
		try {

			while (true) {
			
				System.out.println("En attente d'un client ...");
				
				Socket cli = serv.accept();
				
				System.out.println("Client accepte ...");
				
				cnx = new Connexion(cli, this);
				lCli.add(cnx);
				System.out.println("Client connecte ...\n");
				
				// On renvoie l'identifiant du client {Utile lorsqu'il d�sire
				// Changer de serveur et donc se retirer de celui-ci}.
				
				cnx.Envoie((Object) (new Integer (lCli.size()).toString())); //ajout du client à la liste des clients
				
				if(lCli.size()>=2){//il y a au moins 2 joueurs donc on peut lancer la partie
					for (int i = 0; i < lCli.size(); i ++) {
						((Connexion)lCli.get(i)).Envoie((Object) (new Map(1)));
					}
				}
			}
			
		} catch(Exception e) {
		
			System.out.println("Erreur lors de la connexion d'un client : " + e);	
			
		}
	}

    public void envoyer_position(int pos_x, int pos_y, int joueur){
    	//TODO probleme: la liste est vide... impossible d'envoyer a tt les joueurs...
		System.out.println("lol: "+lCli.size());
    	for (int i = 1; i <= lCli.size(); i ++) {
    		((Connexion)lCli.get(i)).Envoie((Object)  (new Joueur(joueur,pos_x,pos_y))); //envoie de la position d'un joueur aux autres
    		System.out.println("test2");    	
    	}
    }

	
	public synchronized void traiteMessage(Object O) {
		
		try	{
	
				// Si l'objet peut-�tre assign� � un message.

				if (Class.forName("Message").isAssignableFrom(O.getClass())) {
					
					lMessages.addLast(O);
					
					for (int i = 0; i < lCli.size(); i ++) {
					
						((Connexion)lCli.get(i)).Envoie(O);	
					}
				
				}else {
				
					// Sinon, c'est un String, nous enlevons alors le client de
					// La liste.
					
					int rang = Integer.parseInt((String) O);
					
					lCli.remove(rang);
					
					System.out.println("Le client " + rang + " s'est deconnecte ...");
					
					// Nous r�-indexons ensuite la liste des client en leur
					// Envoyant un nouvel identifiant.
					
					for (int i = 0; i < lCli.size(); i ++) {
					
						((Connexion)lCli.get(i)).Envoie((Object) (new Integer (i).toString()));	
					}
					
					System.out.println("Re-indexation des client ...");					 			
				}
						
		} catch (Exception e) {
		
			System.out.println("Objet recu non identifie !");	
		}
	}
	
	/*JTextPane chat;
	JTextField chatfield;

	 static Bomberbeach b;
	 static Map m;
	 private static int skt_port = 5000;
	 static int level = 1;
     private ServerSocket serveurSocket  ;
     private Socket clientSocket ;
     private BufferedReader in;
     private PrintWriter out;
     
     public Server(JTextPane chat,JTextField chatfield, Bomberbeach b){
    	 this.b = b;
    	 this.m = new Map(b.getPanel_nogame(),b.getTchatfield(),b.getLbl_info(),b.getLbl_level(),b.isStart_game());
     	 this.chat = chat;
    	 this.chatfield = chatfield;
      }

     public void envoyer(String message){
         SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
            	 if(message.charAt(0)!='*' && message.charAt(0)!='@'){ //ce n'est pas un message système donc on l'envoi dans le tchat
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
       serveurSocket = new ServerSocket(port);
       clientSocket = serveurSocket.accept();
       System.out.println("Serveur démarré\n###################");
       m.setlevel(level);// chargement de la map
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
     }*/

}
