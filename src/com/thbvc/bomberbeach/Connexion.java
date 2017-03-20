package com.thbvc.bomberbeach;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.Runnable;

public class Connexion implements Runnable {

/********************************************** Déclaration du constructeur : */


	public Connexion(Socket client, Communicateur server) {
      
		try {
			
			this.server = server;   
			output = new ObjectOutputStream(client.getOutputStream());
			input = new ObjectInputStream(client.getInputStream());
		
		} catch(Exception e) {
			
			System.out.println("Erreur de connexion : " + e);	
		}
		
		Thread t = new Thread(this);
		t.start();		
	}	
	
	
/************************************************* Déclaration des méthodes : */


	public void run() {
		
		try {
		
			while(true) {
				
				Object recu = input.readObject();
				server.traiteMessage(recu);
			}
		} catch(Exception e) {
		
			System.out.println("Erreur de lecture : " + e);	
		}
	}

	
	public void Envoie(Object O) {
		
		try {
			output.writeObject(O);
		      
		} catch(Exception e) {
		    	
			System.out.println("Erreur d'ecriture : " + e);	
		}
	}
		

/************************************************ Déclaration des variables : */
	
	
	private Communicateur server;
	private ObjectOutputStream output;
	private ObjectInputStream input;
}