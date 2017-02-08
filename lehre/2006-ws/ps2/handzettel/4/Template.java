/*
 * Dateiname: Template.java
 *
 * Programmiersprachen 2, Hochschule Karlsruhe
 * Template: strukturierte Vorlage für eigene Programmierung
 * erstellt von: Arne Johannessen, 2006-11-13
 * zuletzt geändert von: 
 *
 */



// verwendete Pakete importieren
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// diese Klasse (Template) ist ein JFrame
public class Template extends JFrame {
	
	
	
	// Deklaration von Instanzvariablen
	Container contentPane = super.getContentPane();
	ButtonListener buttonListener = new ButtonListener();
	
	// Deklaration der Steuerelemente als Instanzvariablen
	JButton meinKnopf;
	// ...
	
	
	
	// Konstruktor
	public Template () {
		super();
		
		// Fenster initialisieren, erster Teil
		super.setTitle("JFrame-Template");
		super.setSize(350, 250);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// neue Instanzen der Steuerelemente erstellen
		meinKnopf = new JButton("Mein Knopf");
		// ...
		
		// Steuerelemente zum Fensterinhalt hinzufügen
		contentPane.add(meinKnopf);
		// ...
		
		// Steuerelemente für Ereignis-Behandlung registrieren
		meinKnopf.addActionListener(buttonListener);
		// ...
		
	}
	
	
	
	// Ereignis-Behandlung ist hier mit innerer Klasse gelöst
	class ButtonListener implements ActionListener {
		
		// Java ruft die actionPerformed-Methode auf, wenn die angeklickten
		// Knöpfe zuvor mit addActionListener registriert worden sind
		public void actionPerformed (ActionEvent ereignis) {
			
			// prüfen, bei welchem Steuerelement das Ereignis stattgefunden hat
			if (ereignis.getSource() == meinKnopf) {
				System.out.println("'meinKnopf' wurde gedrückt!");
			}
			// ...
			
		}
	}
	
	
	
	// main-Methode; wird ausgeführt, wenn man diese Klasse als Programm startet
	public static void main (String[] args) {
		
		// neue Fenster-Instanz erstellen
		Template hauptfenster = new Template();  // Konstruktor-Aufruf!
		
		// Fenster initialisieren, zweiter Teil
		hauptfenster.setLocation(250, 350);
		hauptfenster.setVisible(true);
	}
	
}
