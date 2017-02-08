/*
 * $Id: DrawFkt1.java $
 *
 * Copyright (c) Prof. Dr. Bernhard Buerg
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * entnommen aus dem Skript der Vorlesung "Programmiersprachen I"
 * Kapitel 8, Folie 25 ff.
 * geaendert von Arne Johannessen:
 * -- fixed "beenden" bug in actionPerformed(ActionEvent)
 * -- removed "deprecated" warning for Window#show()
 * -- improved white space and blocks
 * 
 * Kodierung: iso-8859-1 (ISO Latin 1)
 */


import java.awt.*;
import java.awt.event.*;


public class DrawFkt1 extends Frame implements ActionListener {
	
	
	MenuItem parabel, sinus, gerade, neu, quit;
	float skalierung;
	float x_start = -4;
	float x_ende = -x_start;
	
	int x_koord_start;
	int x_koord_end;
	int y_koord_start;
	int y_koord_end;
	int x_koord_offset;  // Verschiebung des Ursprungs in x-Richt.
	int y_koord_offset;  // Verschiebung des Ursprungs in y-Richt.
	
	Graphics g;
	int auswahl;
	final int SINUS = 0;
	final int PARABEL = 1;
	final int GERADE = 2;
	
	
	DrawFkt1 (String title) {
		super(title);
		genMenu();
		this.setSize(400, 400);  
		this.setVisible(true);
	}  // Konstruktor
	
	
	private void genMenu () {
		MenuBar menubar = new MenuBar();
		this.setMenuBar(menubar);
		
		// ZweiPulldown Menus für die MenuBar erzeugen
		Menu file = new Menu("Datei");
		Menu edit = new Menu("Auswählen");  // Diese Menus der MenuBar hinzufügen
		menubar.add(file);
		menubar.add(edit);
		
		// file die MenuItems Neu und Beenden hinzufügen
		neu = new MenuItem("Neu");
		file.add(neu);
		quit = new MenuItem("Beenden");
		file.add(quit);
		// edit die MenuItems Parabel, Sinus und Gerade hinzufügen
		parabel = new MenuItem("Parabel");
		edit.add(parabel);
		sinus = new MenuItem("Sinus");
		edit.add(sinus);
		gerade = new MenuItem("Gerade");
		edit.add(gerade);
		// Allen MenuItems den ActionListener hinzufügen
		neu.addActionListener(this);
		quit.addActionListener(this);
		parabel.addActionListener(this);
		sinus.addActionListener(this);
		gerade.addActionListener(this);
	}
	
	
	public void actionPerformed (ActionEvent e) {
		String str = e.getActionCommand();
		g = this.getGraphics();
		if (str == "Beenden") {
			System.exit(0);
		}
		else if (str == "Sinus") {
			auswahl = SINUS;
			zeichne_graph(Color.green, g);
		}
		else if (str == "Parabel") {
			auswahl = PARABEL;
			zeichne_graph(Color.blue, g);
		}
		else if (str == "Gerade") {
			auswahl = GERADE;
			zeichne_graph(Color.red, g);
		}
		else {
			koordinatenkreuz(g);
		}
	}  // actionPerformed
	
	
	// Koordinatenkreuz zeichnen
	public void koordinatenkreuz (Graphics g) {
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.pink);
		g.drawLine(x_koord_start, y_koord_offset, x_koord_end, y_koord_offset);
		g.drawLine(x_koord_offset, y_koord_start, x_koord_offset, y_koord_end);
	}  // koordinatenkreuz
	
	
	// Den jeweiligen Graphen zeichnen
	public void zeichne_graph (Color c, Graphics g) {
		// Erster Funktionswert berechnen und Wertepaar zwischensp.
		int x_koord_old = x_koord_start;
		int y_koord_old;
		int y_koord;
		float x;
		float y;
		
		// Transformation von x-Bildkoordinatenpunkt zu realem x Wert
		x = (x_koord_start - x_koord_offset) / skalierung;
		y = (float)berechne(x);  // berechnen des Fkt-Wertes
		// Transformation von realem y Wert in Bildkoordianten y Wert
		y_koord_old = (int)(y * skalierung);
		g.setColor(c);
		for (int x_koord = x_koord_start + 1; x_koord < x_koord_end; x_koord++) {
			x = (x_koord-x_koord_offset) / skalierung;
			y = (float) berechne(x);  // berechnen des Fkt-Wertes
			y_koord = -(int)(y * skalierung) + y_koord_offset;
			g.drawLine(x_koord_old, y_koord_old, x_koord, y_koord);
			x_koord_old = x_koord;
			y_koord_old = y_koord;
		}  // for
	}  // zeichne_graph
	
	
	public void paint (Graphics g) {  // Ermittl. der Zeichenfläche und Abbildung der Koordinaten
		g = this.getGraphics();
		int width = this.getWidth();
		int height = this.getHeight();
		System.out.println(0+"/"+0+"   "+width+","+height);
		x_koord_offset = width/2;
		x_koord_start = 0;
		x_koord_end = width;
		y_koord_start = 50;
		y_koord_offset = (height + y_koord_start) / 2;
		y_koord_end = height;
		skalierung = width / (x_ende - x_start);
		System.out.println("Skalierung:         "+skalierung);
		System.out.println("Zeicheninterval:    ["+x_start+","+x_ende+"]");
		System.out.println("Koordinatenbereich: ["+x_koord_start+","+x_koord_end+"]");
		System.out.println("Koordinatenbereich: ["+y_koord_start+","+y_koord_end+"]");
		// Zeichne Koordinatenkreuz
		koordinatenkreuz(g);
	}  // paint
	
	
	double berechne (double x) {
		if (auswahl == SINUS) {
			return Math.sin(x);
		}
		else if (auswahl == PARABEL) {
			return x*x;
		}
		else {
			return x;
		}
	}  // berechne
	
	
	public static void main (String[] args) {
		DrawFkt1 drawFkt1 = new DrawFkt1("Beispielmenu");
	} // main
	
	
} // class
