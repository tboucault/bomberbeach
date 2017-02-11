package com.thbvc.bomberbeach;

import java.awt.EventQueue;
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

public class Bomberbeach{

	// ********************************************************************
	// *** Déclaration variables globales                               ***
	// ********************************************************************

	private JFrame frmBomberbeach;
	ImageIcon icon_mur;
	ImageIcon icon_boite;
	ImageIcon icon_brique;
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
	static Server s;
	static Client c;
	static Map m = new Map(getPanel_nogame(),getTchatfield(),getLbl_info(),getLbl_level(),isStart_game());
	static Joueur j = new Joueur();
	private JButton btnCreate = new JButton("Creer une partie");
	private JButton btnJoin = new JButton("Rejoindre une partie");
	


	private static int myposx_j1 ;
	private static int myposy_j1 ;
	private static int myposx_j2 ;
	private static int myposy_j2 ;
	
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
		//this.s = new Server(tchatarea,getTchatfield(), this);
		//this.c = new Client(tchatarea,getTchatfield(), this);
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
			Image mur_image = ImageIO.read(mur_is);
			Image boite_image = ImageIO.read(boite_is);
			Image brique_image = ImageIO.read(brique_is);
			Image portal_image = ImageIO.read(portal_is);
			Image player_image = ImageIO.read(player_is);
			Image player2_image = ImageIO.read(player2_is);
			Image boost_image = ImageIO.read(boost_is);
			icon_mur = new ImageIcon(mur_image);
			icon_boite = new ImageIcon(boite_image);
			icon_brique = new ImageIcon(brique_image);
			icon_portal = new ImageIcon(portal_image);
			icon_player = new ImageIcon(player_image);
			icon_player2 = new ImageIcon(player2_image);
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
		frmBomberbeach.setBounds(100, 100, 992, 564);

		getPanel_nogame().setBackground(new Color(46, 139, 87));
		getPanel_nogame().setBounds(0, 0, 708, 512);
		frmBomberbeach.getContentPane().add(getPanel_nogame());
		getPanel_nogame().setLayout(null);
		getLbl_info().setBounds(231, 239, 273, 16);
		getLbl_info().setText("En attente d'un concurrent...");
		getPanel_nogame().add(getLbl_info());
		getPanel_nogame().setVisible(true);
		getLbl_level().setBounds(741, 24, 61, 16);
		getLbl_level().setVisible(true);
		frmBomberbeach.getContentPane().add(getLbl_level());

		// *** client/serveur ***
		portField.setBounds(851, 62, 130, 26);
		portField.setColumns(10);
		portField.setText("5000");
		ipField.setBounds(851, 24, 130, 26);
		ipField.setColumns(10);
		ipField.setText("127.0.0.1");
		// **********************
		frmBomberbeach.addKeyListener(new KeyAction());

		// *** chat ***
		final JScrollPane scrollPane = new JScrollPane(tchatarea);
		scrollPane.setBounds(714, 119, 267, 342);
		frmBomberbeach.getContentPane().add(scrollPane);
		tchatarea.setEditable(false);
		tchatarea.setSize(200, 200);
		tchatarea.setBackground(new Color(211, 211, 211));
		tchatarea.addKeyListener(new KeyAction());

		setTchatfield(new JTextField());
		getTchatfield().disable();
		getTchatfield().setBackground(new Color(211, 211, 211));
		getTchatfield().setBounds(712, 466, 271, 30);
		frmBomberbeach.getContentPane().add(getTchatfield());
		getTchatfield().setColumns(10);

		frmBomberbeach.getContentPane().add(ipField);

		frmBomberbeach.getContentPane().add(portField);
		getTchatfield().addKeyListener(new KeyAction());
		// *************


		// *** menu ***
		JMenuBar menuBar = new JMenuBar();
		frmBomberbeach.setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmPrfrences = new JMenuItem("Preferences");
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
			myposx_j1 = sprites_j[1].getX();
			myposy_j1 = sprites_j[1].getY();
			myposx_j2 = sprites_j[20].getX();
			myposy_j2 = sprites_j[20].getY();
			
			if(joueur ==null) return;
			if(e.getSource() instanceof JTextField){
				if(e.getSource().equals(getTchatfield())){//si click dans le tchatfield on ne redispatch pas et passe a la suite

				}
				else{//redispatch le keyevent si click dans le tchatarea
					Bomberbeach.this.frmBomberbeach.getContentPane().requestFocus();
				}
			}

			if (e.getKeyCode()== KeyEvent.VK_ENTER) { // si appui sur touche entrer
				if(getTchatfield().getText().length()>0){
					if(joueur.equals("1")){
						if(getTchatfield().getText().length()>0){
							//s.envoyer(getTchatfield().getText());
							tchatarea.setCaretPosition(tchatarea.getDocument().getLength());
							getTchatfield().setText(""); //on vide le champ
						}
					}
					else{
						//c.envoyer(getTchatfield().getText());
						tchatarea.setCaretPosition(tchatarea.getDocument().getLength());
						getTchatfield().setText(""); //on vide le champ
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_DOWN) {
				if(joueur.equals("1")){
					if(can_walk(1,myposx_j1,myposy_j1,"bas")){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("bas");
						System.out.println("<down key> : x="+player1_x+" y="+player1_y);
						player1_y=player1_y+32; // on bouge le personnage d'une case vers le bas
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						//s.envoyer("*|"+player1_x+"|"+player1_y); //envoi de la position du joueur 1 au joueur 2
					}
				}
				else if(joueur.equals("2")){
					if(traitement_position("bas")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("bas");
						System.out.println("<down key> : x="+player2_x+" y="+player2_y);
						player2_y=player2_y+32; // on bouge le personnage d'une case vers le bas
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						//c.envoyer("*|"+player2_x+"|"+player2_y); //envoi de la position du joueur 2 au joueur 1
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_UP) {
				if(joueur.equals("1")){
					if(traitement_position("haut")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("haut");
						System.out.println("<up key> : x="+player1_x+" y="+player1_y);
						player1_y=player1_y-32; // on bouge le personnage d'une case vers le haut
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						//s.envoyer("*|"+player1_x+"|"+player1_y);
					}
				}
				else if(joueur.equals("2")){
					if(traitement_position("haut")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("haut");
						System.out.println("<up key> : x="+player2_x+" y="+player2_y);
						player2_y=player2_y-32; // on bouge le personnage d'une case vers le haut
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						//c.envoyer("*|"+player2_x+"|"+player2_y);
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_RIGHT) {
				if(joueur.equals("1")){
					if(traitement_position("droite")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("droite");
						System.out.println("<right key> : x="+player1_x+" y="+player1_y);
						player1_x=player1_x+32; // on bouge le personnage d'une case vers la droite
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						//s.envoyer("*|"+player1_x+"|"+player1_y);
					}
				}
				else if(joueur.equals("2")){
					if(traitement_position("droite")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("droite");
						System.out.println("<right key> : x="+player2_x+" y="+player2_y);
						player2_x=player2_x+32; // on bouge le personnage d'une case vers la droite
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						//c.envoyer("*|"+player2_x+"|"+player2_y);
					}
				}
			}
			else if (e.getKeyCode()== KeyEvent.VK_LEFT) {
				if(joueur.equals("1")){
					if(traitement_position("gauche")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("gauche");
						System.out.println("<left key> : x="+player1_x+" y="+player1_y);
						player1_x=player1_x-32; // on bouge le personnage d'une case vers la gauche
						sprites_j[1].setBounds(player1_x, player1_y, 32, 32);
						//s.envoyer("*|"+player1_x+"|"+player1_y);
					}
				}
				else if(joueur.equals("2")){
					if(traitement_position("gauche")==1){ //je ne vais pas rencontrer un obstacle
						traitement_powerup("gauche");
						System.out.println("<left key> : x="+player2_x+" y="+player2_y);
						player2_x=player2_x-32; // on bouge le personnage d'une case vers la gauche
						sprites_j[20].setBounds(player2_x, player2_y, 32, 32);
						//c.envoyer("*|"+player2_x+"|"+player2_y);
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
					/*
					String ip=ipField.getText();
					int port=Integer.parseInt(portField.getText());
					//ipField.disable();
					//portField.disable();
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
					tchatarea.setText("Bienvenue joueur "+joueur+"\nAttente d'un concurent\n");
					enable=false; //on désactive le reclic sur le bouton	*/
				}
			}
			if (e.getSource().equals(btnJoin) && enable==true){ //choix rejoindre partie (joueur 2)
				if(ipField.getText().equals("") && portField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Veuillez saisir une ip et un port valide","Bomberbeach",JOptionPane.PLAIN_MESSAGE);
				}
				else{
					new Client("127.0.0.1", 1234);  // Client.
					/*
					ip=ipField.getText();
					port=Integer.parseInt(portField.getText());
					joueur = "2";
					//attente du client
					Thread t2 = new Thread(new Runnable() {

						@Override
						public void run() {
							//c.start(ip,port); //lancement client
						}
					});
					t2.start();
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
					}
					tchatarea.setText("Bienvenue joueur "+joueur+"\nAttente d'un concurent\n");
					enable=false; //on désactive le reclic sur le bouton*/
				}
			}
		}
	};
	private final JTextField ipField = new JTextField();
	private final JTextField portField = new JTextField();

	public void player_leave(String joueur){
		System.out.println("joueur "+ joueur +" a quitté le jeux");
		getPanel_nogame().setVisible(true); //un joueur est parti on stop le jeux
		getLbl_info().setVisible(true);
		getLbl_info().setText("Votre concurrent est parti");
		JOptionPane.showMessageDialog(null, "Votre concurrent est parti","Bomberbeach | Fin du jeu",JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
	}


	public void receive_pos_player(int player,int x,int y){
		if(player==1){ //on recoit la position du joueur 1 il faut la modifier (on est le joueur 2)
			sprites_j[1].setBounds(x, y, 32, 32);
		}
		else if(player==2){ //on recoit la position du joueur 2 il faut la modifier (on est le joueur 1)
			sprites_j[20].setBounds(x, y, 32, 32);

		}
	}
	

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
	
	public boolean can_walk(int joueur,int x,int y,String mvmt){
		int myposx_j1 = sprites_j[1].getX();
		int myposy_j1 = sprites_j[1].getY();
		int myposx_j2 = sprites_j[20].getX();
		int myposy_j2 = sprites_j[20].getY();
		
		
		return true;
	}
	
	// ********************************************************************
	// *** Fonction de traitement position personnage pour eviter       ***
	// *** un déplacement sur un obstacle                               ***
	// ********************************************************************
	public int traitement_position(String mvmt){

		switch (mvmt) {
		case "bas":
			/*if(joueur=="1"){
				if(can_walk(1,myposx_j1,myposy_j1,"bas")){
					return 1;
				}
				else
					return 0;
			}*/
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

 

	public void ReadFile(int level) {
		BufferedReader reader = null;
		int rows = 0;
		String[] charactere = new String[22];
		try {
			if(level==1){ // on charge le path du fichier lv1
				reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(FILE_PATH_lvl1)));				
			}
			//TODO: pour les autres levels

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

					}
					else if(charactere[i].equals("*")){//c'est une boite
						sprites_b[i] = new JLabel(icon_boite); //import de l'image du mur
						sprites_b[i].setBounds(i*32, rows*32, 32, 32); //position
						frmBomberbeach.getContentPane().add(sprites_b[i]); //affichage sur la fenêtre    	            	
					}
					else if(charactere[i].equals("1")){//c'est un joueur
						sprites_j[i] = new JLabel(icon_player); //import de l'image du mur
						sprites_j[i].setBounds(i*32, rows*32, 32, 32); //position
						frmBomberbeach.getContentPane().add(sprites_j[i]); //affichage sur la fenêtre
					}
					else if(charactere[i].equals("2")){//c'est un joueur
						sprites_j[i] = new JLabel(icon_player2); //import de l'image du mur
						sprites_j[i].setBounds(i*32, rows*32, 32, 32); //position
						frmBomberbeach.getContentPane().add(sprites_j[i]); //affichage sur la fenêtre
					}
					else if(charactere[i].equals("b")){//c'est un boost
						sprites_bo[i] = new JLabel(icon_boost); //import de l'image du mur
						sprites_bo[i].setBounds(i*32, rows*32, 32, 32); //position
						frmBomberbeach.getContentPane().add(sprites_bo[i]); //affichage sur la fenêtre    	
						//System.out.println("boost:"+i +": X="+sprites_bo[i].getX()+" Y="+sprites_bo[i].getY());
					}
				}
				line = reader.readLine();
				rows++;
			}
			System.out.println("fichier lv1 lu");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static Panel getPanel_nogame() {
		return panel_nogame;
	}

	public static void setPanel_nogame(Panel panel_nogame) {
		Bomberbeach.panel_nogame = panel_nogame;
	}

	public static JTextField getTchatfield() {
		return tchatfield;
	}

	public static void setTchatfield(JTextField tchatfield) {
		Bomberbeach.tchatfield = tchatfield;
	}

	public static JLabel getLbl_info() {
		return lbl_info;
	}

	public static void setLbl_info(JLabel lbl_info) {
		Bomberbeach.lbl_info = lbl_info;
	}

	public static JLabel getLbl_level() {
		return lbl_level;
	}

	public static void setLbl_level(JLabel lbl_level) {
		Bomberbeach.lbl_level = lbl_level;
	}

	public static boolean isStart_game() {
		return start_game;
	}

	public static void setStart_game(boolean start_game) {
		Bomberbeach.start_game = start_game;
	}
}
