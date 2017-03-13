package com.thbvc.bomberbeach;

import java.io.Serializable;

public class PlayerDead implements Serializable{
	
	public int x;
	public int y;
	public int player1_x;
	public int player1_y;
	public int player2_x;
	public int player2_y;

	public PlayerDead(int x, int y,int player1_x, int player1_y, int player2_x, int player2_y){
		this.x = x;
		this.y = y;
		this.player1_x = player1_x;
		this.player1_y = player1_y;
		this.player2_x = player2_x;
		this.player2_y = player2_y;
	}
	
	public String check(){
		
		String val_retour = String.valueOf(x) + ","+ String.valueOf(y) + ","+ String.valueOf(player1_x) + ","+ String.valueOf(player1_y) + ","+ String.valueOf(player2_x) + ","+ String.valueOf(player2_y);
		System.out.println(val_retour);
		return val_retour;
		
	}
	
	public String destroy(){
		
		String val_retour = String.valueOf(x) + ","+ String.valueOf(y);
		System.out.println(val_retour);
		return val_retour;
		
	}
}
