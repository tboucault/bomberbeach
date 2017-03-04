package com.thbvc.bomberbeach;

import java.io.Serializable;

public class Joueur implements Serializable{
	
	public int joueur;
	public int position_joueur_x;
	public int position_joueur_y;

	public Joueur(int joueur, int pos_x, int pos_y){
		this.joueur = joueur;
		this.position_joueur_x = pos_x;
		this.position_joueur_y = pos_y;
	}
	
	public String setPosition(){
		//TODO traiter de quel joueur la position provient (en retour niveau client)
		//TODO traiter le changement de position
		//TODO renvoi aux clients de la position pour rappeler dans bomberbeach le changement d'affichage
		
		String val_retour = String.valueOf(joueur) + ","+ String.valueOf(position_joueur_x) + "," + String.valueOf(position_joueur_y);
		System.out.println(val_retour);
		return val_retour;
		
	}
}
