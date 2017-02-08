/*
 * Dateiname: ProgressBar.java
 *
 * Programmiersprachen 2, Hochschule Karlsruhe
 * ProgressBar: Vorlage zu Kapitel 3, Aufgabe 2
 * erstellt von: Arne Johannessen, 2006-11-13
 * zuletzt geändert von: 
 *
 */



// verwendete Pakete importieren
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// diese Klasse (ProgressBar) ist ein JFrame
public class ProgressBar extends JFrame {
	
	
	
	// Deklaration von Instanzvariablen
	Container contentPane = super.getContentPane();
	ButtonListener buttonListener = new ButtonListener();
	
	// Deklaration der Steuerelemente als Instanzvariablen
	JButton startKnopf;
	JProgressBar fortschrittsAnzeige;
	
	
	
	// Konstruktor
	public ProgressBar () {
		super();
		
		// Fenster initialisieren, erster Teil
		super.setTitle("JFrame-ProgressBar");
		super.setSize(350, 250);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.setLayout(new FlowLayout());
		
		// neue Instanzen der Steuerelemente erstellen
		startKnopf = new JButton("Start");
		fortschrittsAnzeige = new JProgressBar();
		
		// Fortschritts-Anzeige initialisieren
		// ...
		
		// Steuerelemente zum Fensterinhalt hinzufügen
		contentPane.add(startKnopf);
		contentPane.add(fortschrittsAnzeige);
		
		// Steuerelemente für Ereignis-Behandlung registrieren
		startKnopf.addActionListener(buttonListener);
		
	}
	
	
	
	// Ereignis-Behandlung ist hier mit innerer Klasse gelöst
	class ButtonListener implements ActionListener {
		
		// Java ruft die actionPerformed-Methode auf, wenn die angeklickten
		// Knöpfe zuvor mit addActionListener registriert worden sind
		public void actionPerformed (ActionEvent ereignis) {
			
			// prüfen, bei welchem Steuerelement das Ereignis stattgefunden hat
			if (ereignis.getSource() == startKnopf) {
				zeigeFortschritt();
			}
			
		}
	}
	
	
	
	// Fortschritts-Anzeige einmal von links nach rechts laufen lassen
	public void zeigeFortschritt () {
		// ...
	}
	
	
	
	// main-Methode; wird ausgeführt, wenn man diese Klasse als Programm startet
	public static void main (String[] args) {
		
		// neue Fenster-Instanz erstellen
		ProgressBar hauptfenster = new ProgressBar();  // Konstruktor-Aufruf!
		
		// Fenster initialisieren, zweiter Teil
		hauptfenster.setLocation(250, 350);
		hauptfenster.setVisible(true);
	}
	
}
