package com.thbvc.bomberbeach;
//permet de créer des connexion entre les clients et le serveur.
//ce communicateur joue le role de "routeur"

public interface Communicateur {
	
	public void traiteMessage(Object inMessage) throws java.io.IOException;
}