package com.thbvc.bomberbeach;

import java.io.Serializable;

public class Boost implements Serializable{
	
	public String joueur;
	public int id;
	public int position_joueur_x;
	public int position_joueur_y;
	public String type;

	public Boost(String joueur, String type, int id, int pos_x, int pos_y){
		this.joueur = joueur;
		this.id = id;
		this.type = type;
		this.position_joueur_x = pos_x;
		this.position_joueur_y = pos_y;
	}
	
	public String check(){
		String val_retour = joueur + ","+ type + ","+ String.valueOf(id) + ","+ String.valueOf(position_joueur_x) + ","+ String.valueOf(position_joueur_y);
		System.out.println(val_retour);
		return val_retour;
		
	}
}
