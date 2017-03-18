package com.thbvc.bomberbeach;

import java.io.Serializable;

public class Player_leave implements Serializable{
	
	public int joueur;

	public Player_leave(int joueur){
		this.joueur = joueur;
	}
	
	public int check(){
		int val_retour = joueur;
		System.out.println(val_retour);
		return val_retour;
		
	}
}
