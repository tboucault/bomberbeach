package com.thbvc.bomberbeach;

public class Canwalk {


	public int boost_speed;
	private String[][] mymap;
	private int mapos_x;
	private int mapos_y;

	public Canwalk(int boostspeed,String[][] map,int x,int y){
		this.boost_speed=boostspeed;
		this.mymap=map;
		this.mapos_x=x;
		this.mapos_y=y;
		
	}
	
	public int can_walk(String mvmt){

		switch(mvmt){
			case "bas":
				if (boost_speed==1){
					if(mymap[mapos_x][mapos_y+1].equals("#") || mymap[mapos_x][mapos_y+1].equals("@") || mymap[mapos_x][mapos_y+1].equals("*")){
						return 0;
					}else{
						if(mymap[mapos_x][mapos_y+2].equals("#") || mymap[mapos_x][mapos_y+2].equals("@") || mymap[mapos_x][mapos_y+2].equals("*")){
							return 1;
						}else{
							return 2;
						}
					}
				}else{
					if(mymap[mapos_x][mapos_y+1].equals("#") || mymap[mapos_x][mapos_y+1].equals("@") || mymap[mapos_x][mapos_y+1].equals("*")){
						return 0;
					}else{
						return 1;
					}
				}
			case "haut":
				if (boost_speed==1){
					if(mymap[mapos_x][mapos_y-1].equals("#") || mymap[mapos_x][mapos_y-1].equals("@") || mymap[mapos_x][mapos_y-1].equals("*")){
						return 0;
					}else{
						if(mymap[mapos_x][mapos_y-2].equals("#") || mymap[mapos_x][mapos_y-2].equals("@") || mymap[mapos_x][mapos_y-2].equals("*")){
							return 1;
						}else{
							return 2;
						}
					}
				}else{
					if(mymap[mapos_x][mapos_y-1].equals("#") || mymap[mapos_x][mapos_y-1].equals("@") || mymap[mapos_x][mapos_y-1].equals("*")){
						return 0;
					}else{
						return 1;
					}
				}
			case "gauche":
				if (boost_speed==1){
					if(mymap[mapos_x-1][mapos_y].equals("#") || mymap[mapos_x-1][mapos_y].equals("@") || mymap[mapos_x-1][mapos_y].equals("*")){
						return 0;
					}else{
						if(mymap[mapos_x-2][mapos_y].equals("#") || mymap[mapos_x-2][mapos_y].equals("@") || mymap[mapos_x-2][mapos_y].equals("*")){
							return 1;
						}else{
							return 2;
						}
					}
				}else{
					if(mymap[mapos_x-1][mapos_y].equals("#") || mymap[mapos_x-1][mapos_y].equals("@") || mymap[mapos_x-1][mapos_y].equals("*")){
						return 0;
					}else{
						return 1;
					}
				}
			case "droite":
				if (boost_speed==1){
					if(mymap[mapos_x+1][mapos_y].equals("#") || mymap[mapos_x+1][mapos_y].equals("@") || mymap[mapos_x+1][mapos_y].equals("*")){
						return 0;
					}else{
						if(mymap[mapos_x+2][mapos_y].equals("#") || mymap[mapos_x+2][mapos_y].equals("@") || mymap[mapos_x+2][mapos_y].equals("*")){
							return 1;
						}else{
							return 2;
						}
					}
				}else{
					if(mymap[mapos_x+1][mapos_y].equals("#") || mymap[mapos_x+1][mapos_y].equals("@") || mymap[mapos_x+1][mapos_y].equals("*")){
						return 0;
					}else{
						return 1;
					}
				}
			default:
				return 0;
		}	
	}
	
	
}
