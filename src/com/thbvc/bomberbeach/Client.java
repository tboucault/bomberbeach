package com.thbvc.bomberbeach;

import java.awt.Color;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Client implements Communicateur {

/************************************************ Declaration des variables : */

	static Bomberbeach b;
	static Map m;

	private String IPServeur;
	private int port;
	private Connexion cnx;
	private int idClient;	

/********************************************** Declaration du constructeur : */


	public Client(String IPServeur, int port,Bomberbeach b){
     	 this.b = b;
		
    	 
		this.IPServeur = IPServeur;
		this.port = port;
		creationClient();
	}	
	

/************************************************* Declaration des methodes : */


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

		String[][] mamap = new String[22][16];
		String maposition ;
		String dead;
		String destroy;
		String boost;
		String monmessage;
		int leaver;
		
		try	{
				if (Map.class.isInstance(O)) { //on reçoit un objet de type map
					
					System.out.println("Map recue : " + O);
					Map map = (Map) O; //appel classe map
					mamap=map.ReadFile(1);// on récupère la matrice map retourné par la classe Map
					b.create_map(mamap,1);// on l'envoi à bomberbeach qui traitera la matrice pour afficher la map
				} else if (Joueur.class.isInstance(O)) { //on reçoit un objet de type joueur
					
					System.out.println("Pos Joueur recue : " + O);
					Joueur joueur = (Joueur) O; //appel classe joueur
					maposition = joueur.setPosition();
					b.receive_pos_player(maposition);// on l'envoi à bomberbeach qui traitera la matrice pour afficher la map
				}else if (PlayerDead.class.isInstance(O)) {
					
					System.out.println("Explosion: " + O);
					PlayerDead p = (PlayerDead) O;
					dead = p.check();
					destroy = p.destroy();
					b.check_dead(dead);
					b.check_destroy(destroy);
				}else if (Boost.class.isInstance(O)) {
					Boost bo = (Boost) O;
					System.out.println("Boost recu : " + O);
					boost = bo.check();
					b.receive_boost_player(boost);// on l'envoi à bomberbeach qui traitera la matrice pour afficher la map

				}else if (Player_leave.class.isInstance(O)) {
					Player_leave pl = (Player_leave) O;
					System.out.println("Un joueur s'est déconnecté : " + O);
					leaver = pl.check();
					b.player_leave(leaver);// on l'envoi à bomberbeach qui traitera la matrice pour afficher la map

				}else if (Message.class.isInstance(O)) { //on reçoit un objet de type message
					System.out.println("Message reçu : " + O);
					Message message = (Message) O; //appel classe message
					monmessage = message.traitement();
					b.receive_message(monmessage);// on l'envoi à bomberbeach
				}else {
					
					idClient = Integer.parseInt((String) O);
					
					System.out.println("Le serveur vous attribue l'identifiant " + idClient + ".");
					b.getbtnJoin().setEnabled(false);
					b.getipField().setEnabled(false);
					b.getportField().setEnabled(false);
					b.joueur=Integer.toString(idClient);
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
    
}
