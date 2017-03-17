package com.thbvc.bomberbeach;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.SwingUtilities;

public class Server implements Communicateur, Runnable {

	
	
/************************************************ D�claration des variables : */

	static Bomberbeach b;
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

    public void envoyer_position(int pos_x, String mvmt, int pos_y, int joueur){
    	//TODO probleme: la liste est vide... impossible d'envoyer a tt les joueurs...
		System.out.println("lol: "+lCli.size());
    	for (int i = 1; i <= lCli.size(); i ++) {
    		((Connexion)lCli.get(i)).Envoie((Object)  (new Joueur(joueur,mvmt,pos_x,pos_y))); //envoie de la position d'un joueur aux autres
    		System.out.println("test2");    	
    	}
    }

	
	public synchronized void traiteMessage(Object O) {
		String maposition;
		try	{
				if (Joueur.class.isInstance(O)) { //on reçoit un objet de type joueur
					Joueur j = (Joueur) O;
					System.out.println("(server)Pos Joueur recue : " + O);

			    	for (int i = 0; i <= lCli.size(); i ++) {
			    		((Connexion)lCli.get(i)).Envoie((Object)j); //envoie de la position d'un joueur aux autres
			    	}
				}else if (PlayerDead.class.isInstance(O)) {
					PlayerDead p = (PlayerDead) O;
					System.out.println("Explosion : " + O);

			    	for (int i = 0; i <= lCli.size(); i ++) {
			    		((Connexion)lCli.get(i)).Envoie((Object)p); //envoie aux autres qu'il y a explosion  	
			    	}
				}else if (Boost.class.isInstance(O)) {
					Boost b = (Boost) O;
					System.out.println("Boost recu : " + O);

			    	for (int i = 0; i <= lCli.size(); i ++) {
			    		((Connexion)lCli.get(i)).Envoie((Object)b); //envoie aux autres qu'un joueur a pris un boost 	
			    	}
				}else {
				
					// Sinon, c'est un String, nous enlevons alors le client de
					// La liste.
					
					int rang = Integer.parseInt((String) O);
					//lCli.remove(rang);
					
					System.out.println("Le client " + rang + " s'est deconnecte ...");
					
					for (int i = 0; i < (lCli.size())+1; i ++) {
						((Connexion)lCli.get(i)).Envoie((Object)  (new Player_leave(rang)));
						//envoie aux autres que le joueur est parti, fin du jeux
					}
					
					// Nous r�-indexons ensuite la liste des client en leur
					// Envoyant un nouvel identifiant.
					
					/*for (int i = 0; i < (lCli.size())+1; i ++) {
						((Connexion)lCli.get(i)).Envoie((Object) (new Integer (i).toString()));	
					}
					
					System.out.println("Re-indexation des client ...");	*/				 			
				}
						
		} catch (Exception e) {
		
			System.out.println("Objet recu non identifie !");	
		}
	}

}
