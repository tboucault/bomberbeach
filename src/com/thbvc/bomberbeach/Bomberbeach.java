package com.thbvc.bomberbeach;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Panel;
import javax.swing.JPanel;

public class Bomberbeach{
	
	// ********************************************************************
	// *** Déclaration variables globales                               ***
	// ********************************************************************
	
	private JFrame frmBomberbeach;
	ImageIcon icon_mur = new ImageIcon(this.getClass().getResource("/mur.jpg"));
	ImageIcon icon_boite = new ImageIcon(this.getClass().getResource("/boite.jpg"));
	ImageIcon icon_brique = new ImageIcon(this.getClass().getResource("/brique.jpg"));
	ImageIcon icon_pop = new ImageIcon(this.getClass().getResource("/pop.jpg"));
	ImageIcon icon_player = new ImageIcon(this.getClass().getResource("/player.png"));
	ImageIcon icon_boost = new ImageIcon(this.getClass().getResource("/speed.png"));
	String FILE_PATH_lvl1 = this.getClass().getResource("/level1.txt").getPath();
	private static JLabel[] sprites_m = new JLabel[200];
	private static JLabel[] sprites_p = new JLabel[200];
	private static JLabel[] sprites_b = new JLabel[200];
	private static JLabel[] sprites_j = new JLabel[200];
	private static JLabel[] sprites_bo = new JLabel[200];
	boolean enable=true;
	private static int player1_x,player2_x,player1_y,player2_y;
	private String joueur;
	private static String ip;
	private static int port;
	private static boolean start_game = false;
	private static JTextPane tchatarea = new JTextPane();
    private static Panel panel_nogame = new Panel();
	private static JTextField tchatfield = new JTextField();
	private static JLabel lbl_level = new JLabel();
    private static JLabel lbl_info = new JLabel();
	static Server s = new Server(tchatarea,tchatfield);
	static Client c = new Client(tchatarea,tchatfield);
	private JButton btnCreate = new JButton("Créer une partie");
	private JButton btnJoin = new JButton("Rejoindre une partie");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bomberbeach jframe = new Bomberbeach();
					jframe.frmBomberbeach.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Bomberbeach() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// ********************************************************************
		// *** Création de l'interface                                      ***
		// ********************************************************************
		frmBomberbeach = new JFrame();
		frmBomberbeach.setResizable(false);
		//frmBomberbeach.setUndecorated(true); //on masque la barre de titre
		frmBomberbeach.getContentPane().setBackground(new Color(46, 139, 87)); //fond couleur verte
		frmBomberbeach.getContentPane().setLayout(null);
		frmBomberbeach.setBounds(100, 100, 992, 564);
        
        panel_nogame.setBackground(new Color(46, 139, 87));
        panel_nogame.setBounds(0, 0, 708, 512);
        frmBomberbeach.getContentPane().add(panel_nogame);
        panel_nogame.setLayout(null);
        lbl_info.setBounds(231, 239, 273, 16);
        lbl_info.setText("En attente d'un concurrent...");
        panel_nogame.add(lbl_info);
       panel_nogame.setVisible(true);
		lbl_level.setBounds(741, 24, 61, 16);
		lbl_level.setVisible(true);
		frmBomberbeach.getContentPane().add(lbl_level);
		
		// *** client/serveur ***
		portField.setBounds(851, 62, 130, 26);
		portField.setColumns(10);
		ipField.setBounds(851, 24, 130, 26);
		ipField.setColumns(10);
		// **********************
		
        // *** chat ***
        final JScrollPane scrollPane = new JScrollPane(tchatarea);
		scrollPane.setBounds(714, 119, 267, 342);
		frmBomberbeach.getContentPane().add(scrollPane);
		tchatarea.setEditable(false);
		tchatarea.setSize(200, 200);
        tchatarea.setBackground(new Color(211, 211, 211));
        tchatarea.addKeyListener(new KeyAction());
		
		tchatfield = new JTextField();
		tchatfield.disable();
		tchatfield.setBackground(new Color(211, 211, 211));
		tchatfield.setBounds(712, 466, 271, 30);
		frmBomberbeach.getContentPane().add(tchatfield);
		tchatfield.setColumns(10);
		
		frmBomberbeach.getContentPane().add(ipField);
		
		frmBomberbeach.getContentPane().add(portField);
		tchatfield.addKeyListener(new KeyAction());
		// *************


        // *** menu ***
		JMenuBar menuBar = new JMenuBar();
		frmBomberbeach.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmPrfrences = new JMenuItem("Préférences");
		mnMenu.add(mntmPrfrences);
	
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnMenu.add(mntmQuitter);

	    menuBar.add(Box.createHorizontalGlue());
		menuBar.add(btnCreate);
		btnCreate.addActionListener(actionClick);
		btnCreate.addKeyListener(new KeyAction());
		
		menuBar.add(btnJoin);
		btnJoin.addActionListener(actionClick);
		btnJoin.addKeyListener(new KeyAction());
		
		
		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		// *************

		//Jbackground desktopPane = new Jbackground();
		//frmBomberbeach.getContentPane().add(desktopPane, BorderLayout.CENTER);
		

		// ********************************************************************
		// *** Création texture lvl 1                                       ***
		// ********************************************************************
		// spawns + joueurs
		player1_x =1*32;
		player1_y =1*32;
		player2_x =20*32;
		player2_y =14*32;
		// ********************************************************************
	}
	

	// ********************************************************************
	// *** Event lors d'appui touche clavier                            ***
	// ********************************************************************
	class KeyAction implements KeyListener{
	   //la méthode keyPressed est appelée lorsque l'on presse une touche  
	        public void keyPressed(KeyEvent e){
	               if (e.getKeyCode()== KeyEvent.VK_ENTER) { // si appui sur touche entrer
	            		if(tchatfield.getText().length()>0){
		            		if(joueur.equals("1")){
			            		if(tchatfield.getText().length()>0){
				   					s.envoyer(tchatfield.getText());
				   					tchatarea.setCaretPosition(tchatarea.getDocument().getLength());
									tchatfield.setText(""); //on vide le champ
			            		}
			   				}
			   				else{
			   					c.envoyer(tchatfield.getText());
			   					tchatarea.setCaretPosition(tchatarea.getDocument().getLength());
								tchatfield.setText(""); //on vide le champ
			   				}
	            		}
	               }
	               else if (e.getKeyCode()== KeyEvent.VK_DOWN) {
	            		if(joueur.equals("1")){
		            	  if(traitement_position("bas")==1){ //je ne vais pas rencontrer un obstacle
			            	   System.out.println("<down key> : x="+player1_x+" y="+player1_y);
			            	   player1_y=player1_y+32; // on bouge le personnage d'une case vers le bas
			            	   sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
			   					s.envoyer("*|"+player1_x+"|"+player1_y); //envoi de la position du joueur 1 au joueur 2
		            	   }
		            	  }
	            		else if(joueur.equals("2")){
		            	  if(traitement_position("bas")==1){ //je ne vais pas rencontrer un obstacle
			            	   System.out.println("<down key> : x="+player2_x+" y="+player2_y);
			            	   player2_y=player2_y+32; // on bouge le personnage d'une case vers le bas
			            	   sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
			   					c.envoyer("*|"+player2_x+"|"+player2_y); //envoi de la position du joueur 2 au joueur 1
		            	   }
		            	  }
	               }
	               else if (e.getKeyCode()== KeyEvent.VK_UP) {
	            		if(joueur.equals("1")){
	 	            	   if(traitement_position("haut")==1){ //je ne vais pas rencontrer un obstacle
		            		   System.out.println("<up key> : x="+player1_x+" y="+player1_y);
			            	   player1_y=player1_y-32; // on bouge le personnage d'une case vers le haut
			            	   sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
			   					s.envoyer("*|"+player1_x+"|"+player1_y);
		            	   }
			            	  }
		            		else if(joueur.equals("2")){
		 	            	   if(traitement_position("haut")==1){ //je ne vais pas rencontrer un obstacle
			            		   System.out.println("<up key> : x="+player2_x+" y="+player2_y);
				            	   player2_y=player2_y-32; // on bouge le personnage d'une case vers le haut
				            	   sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
				   					c.envoyer("*|"+player2_x+"|"+player2_y);
			            	   }
			            	  }
	               }
	               else if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
	            		if(joueur.equals("1")){
	 	            	   if(traitement_position("droite")==1){ //je ne vais pas rencontrer un obstacle
			            	   System.out.println("<right key> : x="+player1_x+" y="+player1_y);
			            	   player1_x=player1_x+32; // on bouge le personnage d'une case vers la droite
			            	   sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
			   					s.envoyer("*|"+player1_x+"|"+player1_y);
		            	   }
				            	  }
			            		else if(joueur.equals("2")){
			 	            	   if(traitement_position("droite")==1){ //je ne vais pas rencontrer un obstacle
					            	   System.out.println("<right key> : x="+player2_x+" y="+player2_y);
					            	   player2_x=player2_x+32; // on bouge le personnage d'une case vers la droite
					            	   sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
					   					c.envoyer("*|"+player2_x+"|"+player2_y);
				            	   }
				            	  }
	               }
	               else if (e.getKeyCode()== KeyEvent.VK_LEFT) {
	            		if(joueur.equals("1")){
	 	            	   if(traitement_position("gauche")==1){ //je ne vais pas rencontrer un obstacle
			            	   System.out.println("<left key> : x="+player1_x+" y="+player1_y);
			            	   player1_x=player1_x-32; // on bouge le personnage d'une case vers la gauche
			            	   sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
			   					s.envoyer("*|"+player1_x+"|"+player1_y);
		            	   }
					            	  }
				            		else if(joueur.equals("2")){
				 	            	   if(traitement_position("gauche")==1){ //je ne vais pas rencontrer un obstacle
						            	   System.out.println("<left key> : x="+player2_x+" y="+player2_y);
						            	   player2_x=player2_x-32; // on bouge le personnage d'une case vers la gauche
						            	   sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						   					c.envoyer("*|"+player2_x+"|"+player2_y);
					            	   }
					            	  }
	               }
	        }
	 
	        /*les deux méthodes suivantes doivent être également écrites pour pouvoir réaliser l'interface KeyListener*/               
	        public void keyReleased(KeyEvent e){}
	        public void keyTyped(KeyEvent e){}
	}  
	// ********************************************************************

	// ********************************************************************
	// *** Event lors d'appui clic souris                               ***
	// ********************************************************************
	ActionListener actionClick = new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			if (e.getSource().equals(btnCreate) && enable==true){ //choix créer partie (joueur 1)
				if(ipField.getText().equals("") && portField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez saisir une ip et un port valide","Bomberbeach",JOptionPane.PLAIN_MESSAGE);				
				}
				else{
					String ip=ipField.getText();
					int port=Integer.parseInt(portField.getText());
					ipField.disable();
					portField.disable();
					btnCreate.setForeground(Color.GREEN);
					btnJoin.setEnabled(false);
					joueur = "1";
					//attente du serveur
					Thread t = new Thread(new Runnable() {
						
						@Override
						public void run() {
							s.start(ip,port); //lancement serveur
						}
					});
					t.start();
					ReadFile();
					tchatarea.setText("Bienvenue joueur "+joueur+"\nAttente d'un concurent\n");
					enable=false; //on désactive le reclic sur le bouton	
				}
			}
			if (e.getSource().equals(btnJoin) && enable==true){ //choix rejoindre partie (joueur 2)
				if(ipField.getText().equals("") && portField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez saisir une ip et un port valide","Bomberbeach",JOptionPane.PLAIN_MESSAGE);
				}
				else{
					ip=ipField.getText();
					port=Integer.parseInt(portField.getText());
					joueur = "2";
					//attente du client
					Thread t2 = new Thread(new Runnable() {
						
						@Override
						public void run() {
							c.start(ip,port); //lancement client
						}
					});
					t2.start();
					ReadFile();
					if (start_game==true){
						ipField.disable();
						portField.disable();
						btnJoin.setForeground(Color.GREEN);
						btnCreate.setEnabled(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "Aucun serveur disponible à cette adresse et port","Bomberbeach",JOptionPane.PLAIN_MESSAGE);	
						t2.interrupt();
						ip=ipField.getText();
						port=Integer.parseInt(portField.getText());
						joueur = "2";
						t2.start();
						ReadFile();
					}
					tchatarea.setText("Bienvenue joueur "+joueur+"\nAttente d'un concurent\n");
					enable=false; //on désactive le reclic sur le bouton
				}
			}
		}
	};
	private final JTextField ipField = new JTextField();
	private final JTextField portField = new JTextField();
	
	public void player_leave(String joueur){
        System.out.println("joueur "+ joueur +" a quitté le jeux");
	    panel_nogame.setVisible(true); //un joueur est parti on stop le jeux
	    lbl_info.setVisible(true);
	    lbl_info.setText("Votre concurrent est parti");
		JOptionPane.showMessageDialog(null, "Votre concurrent est parti","Bomberbeach | Fin du jeu",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}
	
	public int createlevel(int level){
		switch (level)
		{
		  case 1:
			  	// ********************************************************************
				// *** Création textures lvl 1                                      ***
				// ********************************************************************
				for(int i=0;i<3;i++){ //bord droit (*17)
					//sprites_1[i].setVisible(true);
				}
				// ********************************************************************
			System.out.println("lvl 1 chargé");
		    break; 
		  case 2:
			  /* chargement sprites lv2 */
		    break;          
		  default:
		    /*Action*/;             
		}
		return level;
	}

	
	public void receive_pos_player(int player,int x,int y){
		if(player==1){ //on recoit la position du joueur 1 il faut la modifier (on est le joueur 2)
			sprites_j[1].setBounds(x, y, 32, 32);
		}
		else if(player==2){ //on recoit la position du joueur 2 il faut la modifier (on est le joueur 1)
			sprites_j[20].setBounds(x, y, 32, 32);
			
		}
	}
	
  	// ********************************************************************
	// *** Fonction de traitement position personnage pour eviter       ***
	// *** un déplacement sur un obstacle                               ***
	// ********************************************************************
	public int traitement_position(String mvmt){
 	   int myposx_j1 = sprites_j[1].getX();
 	   int myposy_j1 = sprites_j[1].getY();
 	   int myposx_j2 = sprites_j[20].getX();
 	   int myposy_j2 = sprites_j[20].getY();

 	   switch (mvmt) {
		case "bas":
			if(joueur=="1"){
				if(myposy_j1==sprites_p[2].getY()-32 && myposx_j1==sprites_p[2].getX()){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
			else if(joueur=="2"){
				if(myposy_j2==sprites_p[2].getY()-32 && myposx_j2==sprites_p[2].getX()){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
		case "haut":
			if(joueur=="1"){
				if(myposy_j1==sprites_p[2].getY()+32 && myposx_j1==sprites_p[2].getX()){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
			else if(joueur=="2"){
				if(myposy_j2==sprites_p[2].getY()+32 && myposx_j2==sprites_p[2].getX()){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
		case "droite":
			if(joueur=="1"){
				if(myposy_j1==sprites_p[2].getY() && myposx_j1==sprites_p[2].getX()-32){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
			else if(joueur=="2"){
				if(myposy_j2==sprites_p[2].getY() && myposx_j2==sprites_p[2].getX()-32){
					   return 0;
				   }
				   else{
						return 1;
			   	   }			
			}
		case "gauche":
			if(joueur=="1"){
				if(myposy_j1==sprites_p[2].getY() && myposx_j1==sprites_p[2].getX()+32){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}
			else if(joueur=="2"){
				if(myposy_j2==sprites_p[2].getY() && myposx_j2==sprites_p[2].getX()+32){
					   return 0;
				   }
				   else{
						return 1;
			   	   }				
			}	
		default:
			return -1;
		}
 	  
	}
	// ********************************************************************
	
	public void setlevel(int level){
	    panel_nogame.setVisible(false); //on masque le panel vide pour afficher le jeu
	    start_game=true;
	    tchatfield.enable();
	    lbl_info.setVisible(false);
		lbl_level.setVisible(true); //affichage du label lvl
		
		switch (level)
		{
		  case 1:
			System.out.println("Lancement level 1");
			lbl_level.setText("Level : " + level);
			createlevel(level);
		    break;        
		  default:
		    /*Action*/;             
		}
	}
	
	public void ReadFile() {
		File file = new File(FILE_PATH_lvl1);
		FileInputStream fis = null;
        BufferedReader reader = null;
        int rows = 0;
        String[] charactere = new String[22];

		try {
			fis = new FileInputStream(file);
            reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            while(line != null){
            	for(int i=0;i<22;i++){//pour chaque caractère par ligne
    	            charactere[i] = line.substring(i, i+1);//on récupère caractère par caractère
    	            if(charactere[i].equals("#")){//c'est un mur
    	    			sprites_m[i] = new JLabel(icon_mur); //import de l'image du mur
    	    			sprites_m[i].setBounds(-42+i*32, rows*32, 117, 32); //position
    	    			frmBomberbeach.getContentPane().add(sprites_m[i]); //affichage sur la fenêtre
    	            }
    	            else if(charactere[i].equals("@")){//c'est un pilier
    	    			sprites_p[i] = new JLabel(icon_brique); //import de l'image du mur
    	    			sprites_p[i].setBounds(i*32, rows*32, 32, 32); //position
    	    			frmBomberbeach.getContentPane().add(sprites_p[i]); //affichage sur la fenêtre
    	    			//System.out.println("pilier i= "+i);
    	            	
    	            }
    	            else if(charactere[i].equals("*")){//c'est une boite
    	    			sprites_b[i] = new JLabel(icon_boite); //import de l'image du mur
    	    			sprites_b[i].setBounds(i*32, rows*32, 32, 32); //position
    	    			frmBomberbeach.getContentPane().add(sprites_b[i]); //affichage sur la fenêtre    	            	
    	            }
    	            else if(charactere[i].equals("p")){//c'est un joueur
    	    			sprites_j[i] = new JLabel(icon_player); //import de l'image du mur
    	    			sprites_j[i+1] = new JLabel(icon_pop);
    	    			sprites_j[i].setBounds(i*32, rows*32, 32, 32); //position
    	    			sprites_j[i+1].setBounds(i*32, rows*32, 32, 32); //position
    	    			frmBomberbeach.getContentPane().add(sprites_j[i]); //affichage sur la fenêtre
    	    			frmBomberbeach.getContentPane().add(sprites_j[i+1]); //affichage sur la fenêtre
    	    			System.out.println("lol: "+i);
    	            }
    	            else if(charactere[i].equals("b")){//c'est un boost
    	    			sprites_bo[i] = new JLabel(icon_boost); //import de l'image du mur
    	    			sprites_bo[i].setBounds(i*32, rows*32, 32, 32); //position
    	    			frmBomberbeach.getContentPane().add(sprites_bo[i]); //affichage sur la fenêtre    	            	
    	            }
            	}
                line = reader.readLine();
                rows++;
            }
			System.out.println("fichier lv1 lu");
            	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
}
}
