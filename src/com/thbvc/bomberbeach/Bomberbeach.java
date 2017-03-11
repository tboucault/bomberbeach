package com.thbvc.bomberbeach;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class Bomberbeach{

	// ********************************************************************
	// *** Déclaration variables globales                               ***
	// ********************************************************************

	private JFrame frmBomberbeach;
	ImageIcon icon_mur;
	ImageIcon icon_boite;
	ImageIcon icon_brique;
	ImageIcon icon_bombe;
	ImageIcon icon_fire;
	ImageIcon icon_fire_x;
	ImageIcon icon_fire_y;
	ImageIcon icon_fire_d;
	ImageIcon icon_fire_g;
	ImageIcon icon_fire_b;
	ImageIcon icon_fire_h;
	ImageIcon icon_portal;
	ImageIcon icon_player;
	ImageIcon icon_player2;
	ImageIcon icon_boost;
	String FILE_PATH_lvl1 = "/level1.txt";
	private static JLabel[] sprites_m = new JLabel[200];
	private static JLabel[] sprites_p = new JLabel[200];
	private static JLabel[] sprites_b = new JLabel[200];
	private static JLabel[] sprites_j = new JLabel[200];
	private static JLabel[] sprites_bo = new JLabel[200];
	private static JLabel[] sprites_bom = new JLabel[2];
	private static JLabel[] sprites_fire = new JLabel[30];
	boolean enable=true;
	private static int player1_x,player2_x,player1_y,player2_y;
	String joueur;
	Boolean[] cansetbombe = new Boolean[2];
	private static String ip;
	private static int port;
	private static boolean start_game = false;
	public static Panel panel_nogame = new Panel();
	public static JLabel lbl_info = new JLabel();
	public static JLabel lbl_player = new JLabel();
	static Server s;
	static Client c;
	static Bomberbeach b;
	private static JButton btnJoin = new JButton("Rejoindre une partie");
	private final static JTextField ipField = new JTextField();
	private final static JTextField portField = new JTextField();

	private static int myposx_j1 ;
	private static int myposy_j1 ;
	private static int myposx_j2 ;
	private static int myposy_j2 ;
	
	private Client moi;
	
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
		this.s = new Server(port, 0);
		this. b = this;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// *** Import des fichiers externes necessaires à la création des textures ***
		try{
			InputStream mur_is = new BufferedInputStream(this.getClass().getResourceAsStream("/mur.jpg"));
			InputStream boite_is = new BufferedInputStream(this.getClass().getResourceAsStream("/boite.jpg"));
			InputStream brique_is = new BufferedInputStream(this.getClass().getResourceAsStream("/brique.jpg"));
			InputStream portal_is = new BufferedInputStream(this.getClass().getResourceAsStream("/portal.jpg"));
			InputStream player_is = new BufferedInputStream(this.getClass().getResourceAsStream("/player.png"));
			InputStream player2_is = new BufferedInputStream(this.getClass().getResourceAsStream("/player2.png"));
			InputStream boost_is = new BufferedInputStream(this.getClass().getResourceAsStream("/speed.png"));
			InputStream bombe_is = new BufferedInputStream(this.getClass().getResourceAsStream("/bombe.png"));
			InputStream fire_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire.png"));
			InputStream fire_x_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_x.png"));
			InputStream fire_y_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_y.png"));
			InputStream fire_d_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_end_d.png"));
			InputStream fire_g_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_end_g.png"));
			InputStream fire_b_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_end_b.png"));
			InputStream fire_h_is = new BufferedInputStream(this.getClass().getResourceAsStream("/fire_end_h.png"));
			Image mur_image = ImageIO.read(mur_is);
			Image boite_image = ImageIO.read(boite_is);
			Image bombe_image = ImageIO.read(bombe_is);
			Image fire_image = ImageIO.read(fire_is);
			Image fire_x_image = ImageIO.read(fire_x_is);
			Image fire_y_image = ImageIO.read(fire_y_is);
			Image fire_d_image = ImageIO.read(fire_d_is);
			Image fire_g_image = ImageIO.read(fire_g_is);
			Image fire_b_image = ImageIO.read(fire_b_is);
			Image fire_h_image = ImageIO.read(fire_h_is);
			Image brique_image = ImageIO.read(brique_is);
			Image portal_image = ImageIO.read(portal_is);
			Image player_image = ImageIO.read(player_is);
			Image player2_image = ImageIO.read(player2_is);
			Image boost_image = ImageIO.read(boost_is);
			icon_mur = new ImageIcon(mur_image);
			icon_boite = new ImageIcon(boite_image);
			icon_bombe = new ImageIcon(bombe_image);
			icon_brique = new ImageIcon(brique_image);
			icon_portal = new ImageIcon(portal_image);
			icon_player = new ImageIcon(player_image);
			icon_player2 = new ImageIcon(player2_image);
			icon_fire = new ImageIcon(fire_image);
			icon_fire_x = new ImageIcon(fire_x_image);
			icon_fire_y = new ImageIcon(fire_y_image);
			icon_fire_d = new ImageIcon(fire_d_image);
			icon_fire_g = new ImageIcon(fire_g_image);
			icon_fire_h = new ImageIcon(fire_h_image);
			icon_fire_b = new ImageIcon(fire_b_image);
			icon_boost = new ImageIcon(boost_image);
			
		}
		catch(Exception e){
			System.out.println("Erreur lors du chargement d'un fichier de texture");
		}
		//

		// ********************************************************************
		// *** Création de l'interface                                      ***
		// ********************************************************************
		frmBomberbeach = new JFrame();
		frmBomberbeach.setResizable(false);
		//frmBomberbeach.setUndecorated(true); //on masque la barre de titre
		frmBomberbeach.getContentPane().setBackground(new Color(46, 139, 87)); //fond couleur verte
		frmBomberbeach.getContentPane().setLayout(null);
		frmBomberbeach.setBounds(100, 100, 703, 564);

		getPanel_nogame().setBackground(new Color(46, 139, 87));
		getPanel_nogame().setBounds(0, 0, 708, 512);
		frmBomberbeach.getContentPane().add(getPanel_nogame());
		getPanel_nogame().setLayout(null);
		getLbl_info().setBounds(231, 239, 273, 16);
		getLbl_info().setText("En attente d'un concurrent...");
		getPanel_nogame().add(getLbl_info());
		getPanel_nogame().setVisible(true);

		// *** client/serveur ***
		getportField().setBounds(851, 62, 130, 26);
		getportField().setColumns(10);
		getportField().setText("5000");
		getipField().setBounds(851, 24, 130, 26);
		getipField().setColumns(10);
		getipField().setText("127.0.0.1");
		// **********************
		frmBomberbeach.addKeyListener(new KeyAction());


		// *** menu ***
		JMenuBar menuBar = new JMenuBar();
		frmBomberbeach.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmPrfrences = new JMenuItem("Preferences");
		mnMenu.add(mntmPrfrences);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnMenu.add(mntmQuitter);
		
		JLabel label = new JLabel("           ");
		JLabel label_1 = new JLabel("           ");
		JLabel label_2 = new JLabel("           ");
		
		menuBar.add(label);
		menuBar.add(ipField);
		menuBar.add(portField);
		menuBar.add(label_1);
		menuBar.add(getLbl_player());
		menuBar.add(label_2);
		

		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(getbtnJoin());
		btnJoin.addActionListener(actionClick);
		btnJoin.addKeyListener(new KeyAction());


		mntmQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO envoyer aux autres joueurs que le joueur a quitté la partie
				System.exit(0);	
			}
		});
		// *************

		player1_x =1*32;
		player1_y =1*32;
		player2_x =20*32;
		player2_y =14*32;
		Arrays.fill(cansetbombe, Boolean.TRUE);
	}


	// ********************************************************************
	// *** Event lors d'appui touche clavier                            ***
	// ********************************************************************
	class KeyAction implements KeyListener{
		//la méthode keyPressed est appelée lorsque l'on presse une touche  
		public void keyPressed(KeyEvent e){
			myposx_j1 = sprites_j[1].getX();
			myposy_j1 = sprites_j[1].getY();
			myposx_j2 = sprites_j[20].getX();
			myposy_j2 = sprites_j[20].getY();
			
			if(joueur ==null) return;

			if (e.getKeyCode()== KeyEvent.VK_DOWN) {
				if(joueur.equals("1")){
					if(can_walk(1,myposx_j1,myposy_j1,"bas")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("bas");
						System.out.println("<down key> : x="+player1_x+" y="+player1_y);
						player1_y=player1_y+32; // on bouge le personnage d'une case vers le bas
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"bas",player1_x,player1_y)));
					}
				}
				else if(joueur.equals("2")){
					if(can_walk(2,myposx_j2,myposy_j2,"bas")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("bas");
						System.out.println("<down key> : x="+player2_x+" y="+player2_y);
						player2_y=player2_y+32; // on bouge le personnage d'une case vers le bas
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"bas",player2_x,player2_y)));
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_UP) {
				if(joueur.equals("1")){
					if(can_walk(1,myposx_j1,myposy_j1,"haut")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("haut");
						System.out.println("<up key> : x="+player1_x+" y="+player1_y);
						player1_y=player1_y-32; // on bouge le personnage d'une case vers le haut
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"haut",player1_x,player1_y)));
					}
				}
				else if(joueur.equals("2")){
					if(can_walk(2,myposx_j2,myposy_j2,"haut")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("haut");
						System.out.println("<up key> : x="+player2_x+" y="+player2_y);
						player2_y=player2_y-32; // on bouge le personnage d'une case vers le haut
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"haut",player2_x,player2_y)));
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
				if(joueur.equals("1")){
					if(can_walk(1,myposx_j1,myposy_j1,"droite")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("droite");
						System.out.println("<right key> : x="+player1_x+" y="+player1_y);
						player1_x=player1_x+32; // on bouge le personnage d'une case vers la droite
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"droite",player1_x,player1_y)));
					}
				}
				else if(joueur.equals("2")){
					if(can_walk(2,myposx_j2,myposy_j2,"droite")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("droite");
						System.out.println("<right key> : x="+player2_x+" y="+player2_y);
						player2_x=player2_x+32; // on bouge le personnage d'une case vers la droite
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"droite",player2_x,player2_y)));
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_LEFT) {
				if(joueur.equals("1")){
					if(can_walk(1,myposx_j1,myposy_j1,"gauche")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("gauche");
						System.out.println("<left key> : x="+player1_x+" y="+player1_y);
						player1_x=player1_x-32; // on bouge le personnage d'une case vers la gauche
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"gauche",player1_x,player1_y)));
					}
				}
				else if(joueur.equals("2")){
					if(can_walk(2,myposx_j2,myposy_j2,"gauche")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("gauche");
						System.out.println("<left key> : x="+player2_x+" y="+player2_y);
						player2_x=player2_x-32; // on bouge le personnage d'une case vers la gauche
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"gauche",player2_x,player2_y)));
					}
				}
			}

			else if (e.getKeyCode()== KeyEvent.VK_SPACE) {
				if(joueur.equals("1")){
					if(cansetbombe[0]==true){
						cansetbombe[0]=false; // le joueur 1 ne peut pas poser d'autres bombe avant que celle-ci explose
						System.out.println("Joueur 1 a posé une bombe en "+player1_x+","+player1_y);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"bombe",player1_x,player1_y)));
					}
					else{
						//on ne peut pas poser une autre bombe
					}
				}
				else if(joueur.equals("2")){
					if(cansetbombe[1]==true){
						cansetbombe[1]=false; // le joueur 2 ne peut pas poser d'autres bombe avant que celle-ci explose
						System.out.println("Joueur 2 a posé une bombe en "+player2_x+","+player2_y);
						moi.getCnx().Envoie((Object) (new Joueur(Integer.parseInt(joueur),"bombe",player2_x,player2_y)));
					}
					else{
						//on ne peut pas poser une autre bombe
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

			if (e.getSource().equals(btnJoin) && enable==true){ //choix rejoindre partie
				if(ipField.getText().equals("") && portField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez saisir une ip et un port valide","Bomberbeach",JOptionPane.PLAIN_MESSAGE);
				}
				else{
					ip=ipField.getText();
					port=Integer.parseInt(portField.getText());
					moi = new Client(ip,port,b);  // lancement d'une instance Client
					/*
					if (isStart_game()==true){
						//ipField.disable();
						//portField.disable();
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
					}*/
				}
			}
		}
	};

	public void player_leave(String joueur){
		System.out.println("joueur "+ joueur +" a quitté le jeux");
		getPanel_nogame().setVisible(true); //un joueur est parti on stop le jeux
		getLbl_info().setVisible(true);
		getLbl_info().setText("Votre concurrent est parti");
		JOptionPane.showMessageDialog(null, "Votre concurrent est parti","Bomberbeach | Fin du jeu",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}

 	// ************************************************************************
 	// *** Traitement gestion position des joueurs                          ***
 	// ************************************************************************

	public void receive_pos_player(String maposition){
        String[] parts = maposition.split("\\,");
        String joueur = parts[0];
        String mvmt = parts[1];
        String x = parts[2];
        String y = parts[3];
        System.out.println("joueur: "+"mouvement: "+mvmt +joueur+" x: "+x+" y: "+y); //debug
        
        if(joueur.equals("1")){
        	if(mvmt.equals("bas")){
				sprites_j[1].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("haut")){
				sprites_j[1].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("gauche")){
				sprites_j[1].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("droite")){
				sprites_j[1].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("bombe")){
				sprites_bom[0] = new JLabel(icon_bombe); //import de l'image d'une bombe
				sprites_bom[0].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32); //position
				frmBomberbeach.getContentPane().add(sprites_bom[0]); //affichage sur la fenêtre
				bombe(0,Integer.parseInt(x), Integer.parseInt(y));
        	}
        	player1_x = sprites_j[1].getX();
        	player1_y = sprites_j[1].getY();
        }else if(joueur.equals("2")){
        	if(mvmt.equals("bas")){
				sprites_j[20].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("haut")){
				sprites_j[20].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("gauche")){
				sprites_j[20].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}
        	else if(mvmt.equals("droite")){
				sprites_j[20].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32);
        	}  
        	else if(mvmt.equals("bombe")){
				sprites_bom[1] = new JLabel(icon_bombe); //import de l'image d'une bombe
				sprites_bom[1].setBounds(Integer.parseInt(x), Integer.parseInt(y), 32, 32); //position
				frmBomberbeach.getContentPane().add(sprites_bom[1]); //affichage sur la fenêtre
				bombe(1,Integer.parseInt(x), Integer.parseInt(y));
        	}    
        	player2_x = sprites_j[20].getX();
        	player2_y = sprites_j[20].getY();
        	
        }
	}

	public boolean can_walk(int joueur,int x,int y,String mvmt){
		int myposx_j1 = sprites_j[1].getX();
		int myposy_j1 = sprites_j[1].getY();
		int myposx_j2 = sprites_j[20].getX();
		int myposy_j2 = sprites_j[20].getY();
		
		
		return true;
	}
 	// ************************************************************************

	
 	// ************************************************************************
 	// *** Traitement interraction lors de la pose d'une bombe et explosion ***
 	// ************************************************************************
	
	public void bombe(int id, int x, int y){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  if(id==0){ //joueur 1
				  cansetbombe[id]=true; //le joueur peut désormais reposer une bombe
				  sprites_bom[id].hide();
				  explosion(x,y);
			  }else if(id==1){ // joueur 2
				  cansetbombe[id]=true; //le joueur peut désormais reposer une bombe
				  sprites_bom[id].hide();
				  explosion(x,y);
			  }
		  }
		}, 2000); //2secondes
	}
	
	public void explosion(int x,int y){
		Timer timer2 = new Timer();

		sprites_fire[0] = new JLabel(icon_fire);
		sprites_fire[1] = new JLabel(icon_fire_x);
		sprites_fire[2] = new JLabel(icon_fire_x);
		sprites_fire[3] = new JLabel(icon_fire_y);
		sprites_fire[4] = new JLabel(icon_fire_y);
		sprites_fire[5] = new JLabel(icon_fire_h);
		sprites_fire[6] = new JLabel(icon_fire_b);
		sprites_fire[7] = new JLabel(icon_fire_g);
		sprites_fire[8] = new JLabel(icon_fire_d);
		sprites_fire[0].setBounds(x, y, 32, 32); //position centrale
		sprites_fire[1].setBounds(x+32, y, 32, 32); //position x
		sprites_fire[2].setBounds(x-32, y, 32, 32); //position x
		sprites_fire[3].setBounds(x, y+32, 32, 32); //position y
		sprites_fire[4].setBounds(x, y-32, 32, 32); //position y
		sprites_fire[5].setBounds(x, y-64, 32, 32); //position h
		sprites_fire[6].setBounds(x, y+64, 32, 32); //position b
		sprites_fire[7].setBounds(x-64, y, 32, 32); //position g
		sprites_fire[8].setBounds(x+64, y, 32, 32); //position d
		  for(int i=0;i<9;i++){
				frmBomberbeach.getContentPane().add(sprites_fire[i]); //affichage sur la fenêtre
		  }
		  
		  moi.getCnx().Envoie((Object) (new PlayerDead(x,y,player1_x,player1_y,player2_x,player2_y)));
		
		frmBomberbeach.getContentPane().repaint();
		
		timer2.schedule(new TimerTask() {
		  @Override
		  public void run() {
			  for(int i=0;i<9;i++){
				  sprites_fire[i].hide();
			  }

				frmBomberbeach.getContentPane().repaint();
		  }
		}, 1000); //1seconde 
	}
	
	public void check_dead(String string){
        String[] parts = string.split("\\,");
        int x = Integer.parseInt(parts[0]);;
        int y = Integer.parseInt(parts[1]);;
        int player1_x = Integer.parseInt(parts[2]);;
        int player1_y = Integer.parseInt(parts[3]);;
        int player2_x = Integer.parseInt(parts[4]);;
        int player2_y = Integer.parseInt(parts[5]);;
			
			//TODO si objet= on le détruit
		if( (player1_x == sprites_fire[0].getX()) && (player1_y == sprites_fire[0].getY()) ||
			(player1_x == sprites_fire[1].getX()) && (player1_y == sprites_fire[1].getY()) ||
		    (player1_x == sprites_fire[2].getX()) && (player1_y == sprites_fire[2].getY()) ||
			(player1_x == sprites_fire[3].getX()) && (player1_y == sprites_fire[3].getY()) ||
			(player1_x == sprites_fire[4].getX()) && (player1_y == sprites_fire[4].getY()) ||
			(player1_x == sprites_fire[5].getX()) && (player1_y == sprites_fire[5].getY()) ||
			(player1_x == sprites_fire[6].getX()) && (player1_y == sprites_fire[6].getY()) ||
			(player1_x == sprites_fire[7].getX()) && (player1_y == sprites_fire[7].getY()) ||
			(player1_x == sprites_fire[8].getX()) && (player1_y == sprites_fire[8].getY()) ){
			sprites_j[1].hide(); //le joueur est mort
			
			if(joueur.equals("1")){
				if(JOptionPane.showConfirmDialog(null, "Vous êtes mort! :( Voulez-vous refaire une partie?","Bomberbeach",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					//TODO newParty();
				}
				else
					System.exit(0);
			}else if(joueur.equals("2")){
				if(JOptionPane.showConfirmDialog(null, "Vous avez gagné !:) Voulez-vous refaire une partie?","Bomberbeach",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					//newParty();
				}
				else
					System.exit(0);				
			}
			
		}else if( (player2_x == sprites_fire[0].getX()) && (player2_y == sprites_fire[0].getY()) ||
				(player2_x == sprites_fire[1].getX()) && (player2_y == sprites_fire[1].getY()) ||
			    (player2_x == sprites_fire[2].getX()) && (player2_y == sprites_fire[2].getY()) ||
				(player2_x == sprites_fire[3].getX()) && (player2_y == sprites_fire[3].getY()) ||
				(player2_x == sprites_fire[4].getX()) && (player2_y == sprites_fire[4].getY()) ||
				(player2_x == sprites_fire[5].getX()) && (player2_y == sprites_fire[5].getY()) ||
				(player2_x == sprites_fire[6].getX()) && (player2_y == sprites_fire[6].getY()) ||
				(player2_x == sprites_fire[7].getX()) && (player2_y == sprites_fire[7].getY()) ||
				(player2_x == sprites_fire[8].getX()) && (player2_y == sprites_fire[8].getY()) ){
			sprites_j[20].hide(); //le joueur est mort
			if(joueur.equals("1")){
				if(JOptionPane.showConfirmDialog(null, "Vous avez gagné !:) Voulez-vous refaire une partie?","Bomberbeach",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					//newParty();
				}
				else
					System.exit(0);	
			}else if(joueur.equals("2")){			
				if(JOptionPane.showConfirmDialog(null, "Vous êtes mort! :( Voulez-vous refaire une partie?","Bomberbeach",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					//newParty();
				}
				else
					System.exit(0);
			}
		}

	}
 	// ************************************************************************
	

	
 	// ************************************************************************
 	// *** Traitement interraction lors d'un powerup activé                 ***
 	// ************************************************************************
	public void receive_boost_player(int player,int id){
		if(player==1){
			sprites_bo[id].hide();
		}
		else if(player==2){
			sprites_bo[id].hide();

		}
	}

	public void traitement_powerup(String mvmt){
		int myposx_j1 = sprites_j[1].getX();
		int myposy_j1 = sprites_j[1].getY();
		int myposx_j2 = sprites_j[20].getX();
		int myposy_j2 = sprites_j[20].getY();

		switch (mvmt) {
		case "bas":
			if(joueur=="1"){
				if((myposx_j1==sprites_bo[10].getX() && myposy_j1==sprites_bo[10].getY()-32) ){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
					//s.envoyer("@|boost|10");
				}	
				else if(myposx_j1==sprites_bo[16].getX() && myposy_j1==sprites_bo[16].getY()-32){
					System.out.println("BOOOOOOOOST");
					sprites_bo[16].hide();
					//s.envoyer("@|boost|16");					
				}
				else if(myposx_j1==sprites_bo[4].getX() && myposy_j1==sprites_bo[4].getY()-32){
					System.out.println("BOOOOOOOOST");
					sprites_bo[4].hide();
					//s.envoyer("@|boost|4");
				}
			}	
			else{
				if(myposx_j2==sprites_bo[10].getX() && myposy_j2==sprites_bo[10].getY()-32){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}		
				
			}
		case "haut":
			if(joueur=="1"){
				if(myposx_j1==sprites_bo[10].getX() && myposy_j1==sprites_bo[10].getY()+32){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}				
			}		
			else{
				if(myposx_j2==sprites_bo[10].getX() && myposy_j2==sprites_bo[10].getY()+32){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}		
				
			}
		case "droite":
			if(joueur=="1"){
				if(myposx_j1==sprites_bo[10].getX()-32 && myposy_j1==sprites_bo[10].getY()){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}				
			}	
			else{
				if(myposx_j2==sprites_bo[10].getX()-32 && myposy_j2==sprites_bo[10].getY()){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}		
				
			}
		case "gauche":
			if(joueur=="1"){
				if(myposx_j1==sprites_bo[10].getX()+32 && myposy_j1==sprites_bo[10].getY()){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}				
			}		
			else{
				if(myposx_j2==sprites_bo[10].getX()+32 && myposy_j2==sprites_bo[10].getY()){
					System.out.println("BOOOOOOOOST");
					sprites_bo[10].hide();
				}		
				
			}
		default:
			break;
		}
		
		
	}
 	// ************************************************************************	
	
 	// ********************************************************************
 	// *** Traitement et affichage de la carte de jeu                   ***
 	// ********************************************************************
	public void create_map(String[][] mamap,int level){
		for(int column=0;column<mamap[0].length;column++){

			for(int row=0;row<mamap.length;row++){
				if(mamap[row][column].equals("#")){//c'est un mur
					sprites_m[row] = new JLabel(icon_mur); //import de l'image du mur
					sprites_m[row].setBounds(-42+row*32, column*32, 117, 32); //position
					frmBomberbeach.getContentPane().add(sprites_m[row]); //affichage sur la fenêtre
				}
				else if(mamap[row][column].equals("@")){//c'est un pilier
					sprites_p[row] = new JLabel(icon_brique);
					sprites_p[row].setBounds(row*32, column*32, 32, 32);
					frmBomberbeach.getContentPane().add(sprites_p[row]);
				}
				else if(mamap[row][column].equals("*")){//c'est une boite
					sprites_b[row] = new JLabel(icon_boite);
					sprites_b[row].setBounds(row*32, column*32, 32, 32);
					frmBomberbeach.getContentPane().add(sprites_b[row]);            	
				}
				else if(mamap[row][column].equals("1")){//c'est un joueur
					sprites_j[row] = new JLabel(icon_player);
					sprites_j[row].setBounds(row*32, column*32, 32, 32);
					frmBomberbeach.getContentPane().add(sprites_j[row]);
				}
				else if(mamap[row][column].equals("2")){//c'est un joueur
					sprites_j[row] = new JLabel(icon_player2);
					sprites_j[row].setBounds(row*32, column*32, 32, 32);
					frmBomberbeach.getContentPane().add(sprites_j[row]);
				}
				else if(mamap[row][column].equals("b")){//c'est un boost
					sprites_bo[row] = new JLabel(icon_boost);
					sprites_bo[row].setBounds(row*32, column*32, 32, 32);
					frmBomberbeach.getContentPane().add(sprites_bo[row]);
				}
			}
		}
		setlevel(level); //appel methode pour afficher l'interface correspondant au lvl
	}
	// ********************************************************************

	
 	// ************************************************************************************
 	// *** Lancement du lvl après connexion entre client et serveur                     ***
	// *** Permettra une evolution du jeux pour choisir entre plusieurs map prédéfinies ***
 	// ************************************************************************************
	public void setlevel(int level){
		panel_nogame.setVisible(false); //on masque le panel vide pour afficher le jeu
		start_game=true;
		lbl_info.setVisible(false);

		switch (level)
		{
		case 1:
			System.out.println("Lancement level 1");
			System.out.println("lvl 1 chargé");
			break;        
		default:
			/*Action*/;             
		}
	}
	// ************************************************************************************
	
	
	public static Panel getPanel_nogame() {
		return panel_nogame;
	}

	public static void setPanel_nogame(Panel panel_nogame) {
		Bomberbeach.panel_nogame = panel_nogame;
	}

	public static JLabel getLbl_info() {
		return lbl_info;
	}
	
	public static JButton getbtnJoin() {
		return btnJoin;
	}

	public static JTextField getipField() {
		return ipField;
	}

	public static JTextField getportField() {
		return portField;
	}

	public static void setLbl_info(JLabel lbl_info) {
		Bomberbeach.lbl_info = lbl_info;
	}

	public static JLabel getLbl_player() {
		return lbl_player;
	}


	public static boolean isStart_game() {
		return start_game;
	}

	public static void setStart_game(boolean start_game) {
		Bomberbeach.start_game = start_game;
	}
}
