/*
 * Dateiname: ProgressBarSynchron.java
 *
 * Programmiersprachen 2, Hochschule Karlsruhe
 * ProgressBarSynchron: synchrone L�sung zu Kapitel 3, Aufgabe 2
 * erstellt von: Arne Johannessen, 2006-11-13
 * zuletzt ge�ndert von: 
 *
 */



// verwendete Pakete importieren
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// diese Klasse (ProgressBarSynchron) ist ein JFrame
public class ProgressBarSynchron extends JFrame {
	
	
	
	// Deklaration von Instanzvariablen
	Container contentPane = super.getContentPane();
//	ButtonListener buttonListener = new ButtonListener();
	
	// Deklaration der Steuerelemente als Instanzvariablen
//	JButton startKnopf;
	JProgressBar fortschrittsAnzeige;
	
	
	
	// Konstruktor
	public ProgressBarSynchron () {
		super();
		
		// Fenster initialisieren, erster Teil
		super.setTitle("JFrame-ProgressBar synchron");
		super.setSize(350, 250);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.setLayout(new FlowLayout());
		
		// neue Instanzen der Steuerelemente erstellen
//		startKnopf = new JButton("Start");
		fortschrittsAnzeige = new JProgressBar();
		
		// Fortschritts-Anzeige initialisieren
		// ...
		
		// Steuerelemente zum Fensterinhalt hinzuf�gen
//		contentPane.add(startKnopf);
		contentPane.add(fortschrittsAnzeige);
		
		// Steuerelemente f�r Ereignis-Behandlung registrieren
//		startKnopf.addActionListener(buttonListener);
		
	}
	
	
	
/*	// Ereignis-Behandlung ist hier mit innerer Klasse gel�st
	class ButtonListener implements ActionListener {
		
		// Java ruft die actionPerformed-Methode auf, wenn die angeklickten
		// Kn�pfe zuvor mit addActionListener registriert worden sind
		public void actionPerformed (ActionEvent ereignis) {
			
			// pr�fen, bei welchem Steuerelement das Ereignis stattgefunden hat
			if (ereignis.getSource() == startKnopf) {
				zeigeFortschritt();
			}
			
		}
	}
*/	
	
	
	// Fortschritts-Anzeige einmal von links nach rechts laufen lassen
	public void zeigeFortschritt () {
		// ...
	}
	
	
	
	// main-Methode; wird ausgef�hrt, wenn man diese Klasse als Programm startet
	public static void main (String[] args) {
		
		// neue Fenster-Instanz erstellen
		ProgressBarSynchron hauptfenster = new ProgressBarSynchron();  // Konstruktor-Aufruf!
		
		// Fenster initialisieren, zweiter Teil
		hauptfenster.setLocation(250, 350);
		hauptfenster.setVisible(true);
		
		// Fortschrittsanzeige synchron laufen lassen
		hauptfenster.zeigeFortschritt();
	}
	
}
