package com.thbvc.bomberbeach;

import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Map {

	Panel panel_nogame;
	JTextField tchatfield;
	JLabel lbl_info;
	JLabel lbl_lvl;
	boolean start_game;
	static Bomberbeach b = new Bomberbeach();

     public Map(Panel panel_nogame,JTextField tchatfield,JLabel lbl_info,JLabel lbl_lvl,boolean start_game){
    	 this.panel_nogame = panel_nogame;
     	 this.lbl_info = lbl_info;
     	 this.lbl_lvl = lbl_lvl;
    	 this.tchatfield = tchatfield;
    	 this.start_game = start_game;
      }
     

 	// ********************************************************************
 	// *** Lancement du lvl après connexion entre client et serveur     ***
 	// ********************************************************************
	public void setlevel(int level){
		panel_nogame.setVisible(false); //on masque le panel vide pour afficher le jeu
		start_game=true;
		tchatfield.enable();
		lbl_info.setVisible(false);
		lbl_lvl.setVisible(true); //affichage du label lvl

		switch (level)
		{
		case 1:
			System.out.println("Lancement level 1");
			b.ReadFile(1);
			lbl_lvl.setText("Level : " + level);
			System.out.println("lvl 1 chargé");
			break;        
		default:
			/*Action*/;             
		}
	}
	
}
