package com.thbvc.bomberbeach;
public class Application_Serveur
{
	public static void main (String [] args)
	{
		new Server(5000, 2);	//lancement d'un nouveau serveur au port 5000 acceptant 2clients
	}	
}