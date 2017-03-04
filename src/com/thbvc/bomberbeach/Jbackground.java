package com.thbvc.bomberbeach;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;

public class Jbackground extends JLabel {
	
	protected void paintComponent(Graphics g){
		Image img = new ImageIcon(this.getClass().getResource("/Fond2.jpg")).getImage();
		try{
			Graphics2D g2d = (Graphics2D)g;
			double x = img.getWidth(null);
			double y = img.getHeight(null);
			g2d.scale(getWidth() / x, getHeight()/ y);
			g2d.drawImage(img, 0, 0, this);
			
		}catch(Exception e){
			System.out.println("Erreur de chargement du background");
		}
	}

}
