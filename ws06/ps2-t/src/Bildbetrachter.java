/*
 * $Id: Bildbetrachter.java,v 1.1 2007-10-19 18:31:16 arne Exp $
 * Kodierung: iso-8859-1 (ISO Latin 1)
 *
 * Autor: Prof. Dr. Bernhard Buerg, 2006
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * Aenderungen:
 * - Arne Johannessen (Bloecke verbessert, show->setVisible), 2006-11-19
 */


import java.awt.geom.*;
import java.awt.*;
import javax.swing.*; 
import javax.swing.filechooser.FileFilter; 
import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage; 
import javax.imageio.*; 

class Bildbetrachter extends Bildsignierer.BildLeinwand { 
	
	BufferedImage bild;
	
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(bild != null) {
			int r = Math.max(getWidth()/3, getHeight()/3);
			Point mitte = new Point(getWidth()/2, getHeight()/2);
			GeneralPath kreisClip = new GeneralPath();
			kreisClip.moveTo(mitte.x, mitte.y-r);
			kreisClip.quadTo(mitte.x+r,mitte.y-r,mitte.x+r,mitte.y);
			kreisClip.quadTo(mitte.x+r,mitte.y+r,mitte.x,mitte.y+r);
			kreisClip.quadTo(mitte.x-r,mitte.y+r,mitte.x-r,mitte.y);
			kreisClip.quadTo(mitte.x-r,mitte.y-r,mitte.x,mitte.y-r);
			g2.clip(kreisClip);
			g2.drawImage(bild, 0, 0, bild.getWidth(),bild.getHeight(), this);
		}
	}
	
	
	// ...
	
} 
