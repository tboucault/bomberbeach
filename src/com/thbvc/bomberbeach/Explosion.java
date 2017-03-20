package com.thbvc.bomberbeach;

import java.util.Arrays;

public class Explosion {


	Boolean[] fire = new Boolean[9];
	private String[][] mymap;
	private int p_x;
	private int p_y;
	private int x;
	private int y;

	public Explosion(String[][] map,int p_x,int p_y,int x,int y){
		this.mymap=map;
		this.p_x=p_x;
		this.p_y=p_y;
		this.x=x;
		this.y=y;
	}
	
	public Boolean[] explosion(){
		Arrays.fill(fire, Boolean.TRUE);
		

        if(p_x==1){//je suis aux frontières gauche de la map
            if(p_y==1){//je suis aussi aux frontières nord de la map
        		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            }else if(p_y==14){//je suis aussi aux frontières sud de la map
            	//TODO
            	if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}

        		if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-

        			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        				fire[4]=false;
        				fire[5]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[5]=false;
        			}
        		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            	
            }else{
        		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-

        			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        				fire[4]=false;
        				fire[5]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[5]=false;
        			}
        		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            }

    				
        }
        if(p_x==20){//je suis aux frontières droite de la map
        	if(p_y==1){//je suis aussi aux frontières nord de la map
        		if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
        	}else if(p_y==14){//je suis aussi aux frontières sud de la map
            		if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
            			fire[1]=false;
            			fire[8]=false;
            		}
            		
            		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

            			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
            				fire[2]=false;
            				fire[7]=false;
            			}else{//on cache seulement les flammes en x-2
            				fire[7]=false;
            			}
            		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
            			fire[2]=false;
            			fire[7]=false;
            		}
            		
            		if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
            			fire[3]=false;
            			fire[6]=false;
            		}
            		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-
            			
    	    			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
    	    				fire[4]=false;
    	    				fire[5]=false;
    	    			}else{//on cache seulement les flammes en y+2
    	    				fire[5]=false;
    	    			}
    	    		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
    	    			fire[4]=false;
    	    			fire[5]=false;
    	    		}
            	}else{
        		if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-

        			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        				fire[4]=false;
        				fire[5]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[5]=false;
        			}
        		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
        	}
    				
        }
        if(p_y==1){//je suis aux frontières nord de la map

            if(p_x==1){//je suis aussi aux frontières gauche de la map

        		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            }else if(p_x==20){//je suis aux frontières droite de la map
            	//TODO
        		if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            }else{
        		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
            }
    				
        }
        if(p_y==14){//je suis aux frontières sud de la map

            if(p_x==1){//je suis aussi aux frontières gauche de la map

	    		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+
	
	    			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
	    				fire[1]=false;
	    				fire[8]=false;
	    			}else{//on cache seulement les flammes en x+2
	    				fire[8]=false;
	    			}
	    		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
	    			fire[1]=false;
	    			fire[8]=false;
	    		}
	    		
	    		if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
	    			fire[2]=false;
	    			fire[7]=false;
	    		}
	    		
	    		if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
	    			fire[3]=false;
	    			fire[6]=false;
	    		}
	
	    		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-
	
	    			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    				fire[4]=false;
	    				fire[5]=false;
	    			}else{//on cache seulement les flammes en y+2
	    				fire[5]=false;
	    			}
	    		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    			fire[4]=false;
	    			fire[5]=false;
	    		}
            }else if(p_x==20){

        		if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}
        		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-
        			
	    			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    				fire[4]=false;
	    				fire[5]=false;
	    			}else{//on cache seulement les flammes en y+2
	    				fire[5]=false;
	    			}
	    		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    			fire[4]=false;
	    			fire[5]=false;
	    		}
            }else{
	    		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+
	
	    			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
	    				fire[1]=false;
	    				fire[8]=false;
	    			}else{//on cache seulement les flammes en x+2
	    				fire[8]=false;
	    			}
	    		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
	    			fire[1]=false;
	    			fire[8]=false;
	    		}
	    		
	    		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-
	
	    			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
	    				fire[2]=false;
	    				fire[7]=false;
	    			}else{//on cache seulement les flammes en x-2
	    				fire[7]=false;
	    			}
	    		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
	    			fire[2]=false;
	    			fire[7]=false;
	    		}
	    		
	    		if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
	    			fire[3]=false;
	    			fire[6]=false;
	    		}
	
	    		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-
	
	    			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    				fire[4]=false;
	    				fire[5]=false;
	    			}else{//on cache seulement les flammes en y+2
	    				fire[5]=false;
	    			}
	    		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
	    			fire[4]=false;
	    			fire[5]=false;
	    		}
            }
    				
        }else if(p_x!=1 && p_x!=20 && p_y!=1 && p_y!=14){
        		if(mymap[(x/32)+2][y/32].equals("#") || mymap[(x/32)+2][y/32].equals("@")){// x+

        			if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        				fire[1]=false;
        				fire[8]=false;
        			}else{//on cache seulement les flammes en x+2
        				fire[8]=false;
        			}
        		}else if(mymap[(x/32)+1][y/32].equals("#") || mymap[(x/32)+1][y/32].equals("@")){//on cache les flammes en x+2 et x+1
        			fire[1]=false;
        			fire[8]=false;
        		}
        		
        		if(mymap[(x/32)-2][y/32].equals("#") || mymap[(x/32)-2][y/32].equals("@")){// x-

        			if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        				fire[2]=false;
        				fire[7]=false;
        			}else{//on cache seulement les flammes en x-2
        				fire[7]=false;
        			}
        		}else if(mymap[(x/32)-1][y/32].equals("#") || mymap[(x/32)-1][y/32].equals("@")){//on cache les flammes en x-2 et x-1
        			fire[2]=false;
        			fire[7]=false;
        		}
        		
        		if(mymap[x/32][(y/32)+2].equals("#") || mymap[x/32][(y/32)+2].equals("@")){// y+

        			if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        				fire[3]=false;
        				fire[6]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[6]=false;
        			}
        		}else if(mymap[x/32][(y/32)+1].equals("#") || mymap[x/32][(y/32)+1].equals("@")){//on cache les flammes en y+2 et y+1
        			fire[3]=false;
        			fire[6]=false;
        		}

        		if(mymap[x/32][(y/32)-2].equals("#") || mymap[x/32][(y/32)-2].equals("@")){// y-

        			if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        				fire[4]=false;
        				fire[5]=false;
        			}else{//on cache seulement les flammes en y+2
        				fire[5]=false;
        			}
        		}else if(mymap[x/32][(y/32)-1].equals("#") || mymap[x/32][(y/32)-1].equals("@")){//on cache les flammes en y-2 et y-1
        			fire[4]=false;
        			fire[5]=false;
        		}
        }
		
		return fire;
	}

}
