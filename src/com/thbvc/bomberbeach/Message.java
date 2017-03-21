package com.thbvc.bomberbeach;
import java.io.Serializable; 
import java.awt.Graphics;
import java.awt.FontMetrics;

public class Message implements Serializable
{

/* Constructeur ***************************************************************/
	
	public Message(String chateur, String mes) {
	
		this.chateur = chateur;
		this.mes = mes;	
	}
	

/* Methodes *******************************************************************/
	
	public String traitement(){
		String val_retour = chateur + " : "+ mes;
		System.out.println(val_retour);
		return val_retour;
	}
	
	
/* Variables ******************************************************************/

	private String chateur;
	private String mes;		
}