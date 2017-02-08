/*
 * $Id: Bildsignierer.java,v 1.1 2007-10-19 18:31:15 arne Exp $
 * Kodierung: iso-8859-1 (ISO Latin 1)
 *
 * Autor: Prof. Dr. Bernhard Buerg, 2006
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * Aenderungen:
 * - Arne Johannessen (Bloecke verbessert, show->setVisible), 2006-11-19
 */


/*
 * Bildsignierer.java - Bilder laden und bearbeiten 
 *
 */


import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.event.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;

public class Bildsignierer extends JFrame {
	static Bildsignierer fenster;
	String dateiname;  // Name der Bilddatei
	BufferedImage bild;  // Referenz auf das aktuelle Bild
	BildLeinwand leinwand;  // JPanel zum Anzeigen des Bildes
	
	JMenuBar menuleiste = new JMenuBar();
	
	JMenu menuDatei = new JMenu();
	JMenuItem menuDateiOeffnen = new JMenuItem();
	JMenuItem menuDateiSpeichern = new JMenuItem();
	JMenuItem menuDateiBeenden = new JMenuItem();
	
	JMenu menuBearbeiten = new JMenu();
	JMenuItem menuBearbeitenSignieren = new JMenuItem();
	
	// Innere Klasse zum Überschreiben von paintComponent()
	class BildLeinwand extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(bild != null) {
				g.drawImage(bild, 0, 0, bild.getWidth(), bild.getHeight(), this);
			}
		} // paintComponent
		
		public Dimension getPreferredSize() {
			if (bild != null) {
				return new Dimension(bild.getWidth(), bild.getHeight());
			}
			else {
				return fenster.getSize();
			}
		}
	}
	
	Bildsignierer() {
		super();
		
		// Hauptfenster einrichtensetTitle("Bildsignierprogramm");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		// Menüleiste
		JMenuBar menueleiste = new JMenuBar();
		menuDatei.setText("Datei");
		menuDateiOeffnen.setText("Öffnen");
		menuDateiOeffnen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bildOeffnen();
					}
				}); 
		menuDateiSpeichern.setText("Speichern");
		menuDateiSpeichern.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bildSpeichern();
					}
				});
		menuDateiBeenden.setText("Beenden");
		menuDateiBeenden.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		menuDatei.add(menuDateiOeffnen);
		menuDatei.add(menuDateiSpeichern);
		menuDatei.addSeparator();
		menuDatei.add(menuDateiBeenden);
		menueleiste.add(menuDatei);
		
		menuBearbeiten.setText("Bearbeiten");
		menuBearbeitenSignieren.setText("Signieren"); 
		menuBearbeitenSignieren.addActionListener(
				new ActionListener() { 
					public void actionPerformed(ActionEvent e) {
						bildSignieren(); 
					}
				}); 
		
		menuBearbeiten.add(menuBearbeitenSignieren);
		menueleiste.add(menuBearbeiten);
		
		setJMenuBar(menueleiste);
		
		// Eine Leinwand anlegen (von JPanel abgeleitet)
		leinwand = new BildLeinwand();
		getContentPane().add(leinwand, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
	
	
	// Ereignisbehandlungsmethoden für Komponenten
	protected void bildOeffnen() {
		class PngFilter extends FileFilter {
			public String getDescription() {
				return "Portable Network Graphic (*.png)";
			} 
			public boolean accept(File f) {
				if (f == null) {
					return false;
				}
				if (f.isDirectory()) {
					return true;
				}
				return f.getName().toLowerCase().endsWith(".png");
			}
		}
		
		JFileChooser oeffnenDialog = new JFileChooser();
		oeffnenDialog.setCurrentDirectory(new File("."));
		oeffnenDialog.setFileFilter(new PngFilter());
		oeffnenDialog.setAcceptAllFileFilterUsed(false);
		
		if (JFileChooser.APPROVE_OPTION == oeffnenDialog.showOpenDialog(this)) {
			this.dateiname = oeffnenDialog.getSelectedFile().getPath();
			try {
				bild = ImageIO.read(new File(dateiname));
				leinwand.repaint();
				pack();
			}
			catch(IOException e) {
				dateiname = null;
				bild = null;
			}
		}
	}
	
	protected void bildSpeichern() {
		if (bild != null) {
			try { 
				ImageIO.write(bild, "png", new File("Signiert.png"));
			}
			catch(IOException e) {
		 	}
		}
	}
	
	protected void bildSignieren() {
		if (bild != null) {
			Graphics2D g = bild.createGraphics();
			try {
				BufferedImage signatur = ImageIO.read(new File("signatur.png"));
				int x = bild.getWidth() -signatur.getWidth();
				int y = bild.getHeight() - signatur.getHeight();
				g.drawImage(signatur, x, y, signatur.getWidth(), signatur.getHeight(), this);
				leinwand.repaint();
			}
			catch(IOException e) {
			}
		}
	}
	
	public static void main(String args[]) {
		Bildsignierer fenster = new Bildsignierer();
		fenster.setSize(500, 500);
		fenster.setLocation(200, 300);
		fenster.setVisible(true);
	} // main 
	
} // class Bildsignierer
