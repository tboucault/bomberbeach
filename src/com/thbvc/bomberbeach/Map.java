package com.thbvc.bomberbeach;

import java.awt.Panel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Map implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String FILE_PATH_lvl1 = "/level1.txt";
	private int level;
	private String[][] map = new String[22][16];
	
	public Map(int level){
		this.setLevel(level);
		setMap();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	private void setMap(){
		//TODO Open map file
		//TODO Read File and set Map to String value...
		ReadFile(level);
		
	}

	public String[][] ReadFile(int level) {
		BufferedReader reader = null;
		int rows = 0;
		int column = 0;
		try {
			if(level==1){ // on charge le path du fichier lv1
				reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(FILE_PATH_lvl1)));				
			}
			//TODO: pour les autres levels

			String line = reader.readLine();
			while(line != null){
				for(column=0;column<line.length();column++){
					map[column][rows] = ""+line.charAt(column); //création de la matrice map
				}
				line = reader.readLine();
				rows++;
			}
			System.out.println("Fichier lv1 lu, matrice créée");
			//show_map_debug(); //appel de la fonction pour l'afficher en debug
			//TODO envoi de la matrice map a bomberbeach pour afficher.
			return map;
		} catch (IOException e) {
			e.printStackTrace();
			return map;
			//TODO pas renvoyer map mais message erreur
		}
	}


	public void show_map_debug(){
		for(int column=0;column<map[0].length;column++){

			for(int row=0;row<map.length;row++){
				System.out.print(map[row][column]);
			}
			System.out.println("");
		}
	}
	

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

    /* public Map(Panel panel_nogame,JTextField tchatfield,JLabel lbl_info,JLabel lbl_lvl,boolean start_game){
    	 this.panel_nogame = panel_nogame;
     	 this.lbl_info = lbl_info;
     	 this.lbl_lvl = lbl_lvl;
    	 this.tchatfield = tchatfield;
    	 this.start_game = start_game;
      }*/
     

 	// ********************************************************************
 	// *** Lancement du lvl après connexion entre client et serveur     ***
 	// ********************************************************************
/*	public void setlevel(int level){
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
			/*Action*;             
		}
	}*/
	
}
