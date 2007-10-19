/*
 * Dateiname: ProgressBarThread.java
 *
 * Programmiersprachen 2, Hochschule Karlsruhe
 * ProgressBarThread: nebenl�ufige L�sung zu Kapitel 3, Aufgabe 2
 * erstellt von: Arne Johannessen, 2006-11-13
 * ge�ndert von: Arne Johannessen, 2006-11-16 (Fehler "MyProgressBarThread" Zeile 74 behoben)
 * zuletzt ge�ndert von: 
 *
 */



// verwendete Pakete importieren
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



// diese Klasse (ProgressBarThread) ist ein JFrame und kann als Thread laufen
public class ProgressBarThread extends JFrame implements Runnable {
	
	
	
	// Deklaration von Instanzvariablen
	Container contentPane = super.getContentPane();
	ButtonListener buttonListener = new ButtonListener();
	
	// Deklaration der Steuerelemente als Instanzvariablen
	JButton startKnopf;
	JProgressBar fortschrittsAnzeige;
	
	
	
	// Konstruktor
	public ProgressBarThread () {
		super();
		
		// Fenster initialisieren, erster Teil
		super.setTitle("JFrame-ProgressBar nebenl�ufig");
		super.setSize(350, 250);
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.setLayout(new FlowLayout());
		
		// neue Instanzen der Steuerelemente erstellen
		startKnopf = new JButton("Start");
		fortschrittsAnzeige = new JProgressBar();
		
		// Fortschritts-Anzeige initialisieren
		// ...
		
		// Steuerelemente zum Fensterinhalt hinzuf�gen
		contentPane.add(startKnopf);
		contentPane.add(fortschrittsAnzeige);
		
		// Steuerelemente f�r Ereignis-Behandlung registrieren
		startKnopf.addActionListener(buttonListener);
		
	}
	
	
	
	// Ereignis-Behandlung ist hier mit innerer Klasse gel�st
	class ButtonListener implements ActionListener {
		
		// Java ruft die actionPerformed-Methode auf, wenn die angeklickten
		// Kn�pfe zuvor mit addActionListener registriert worden sind
		public void actionPerformed (ActionEvent ereignis) {
			
			// pr�fen, bei welchem Steuerelement das Ereignis stattgefunden hat
			if (ereignis.getSource() == startKnopf) {
				
				// Nebenlauf (Thread) starten
				Thread thread = new Thread(ProgressBarThread.this);
				thread.start();
			}
			
		}
	}
	
	
	
	// Implementation des Nebenlaufs (Threads) dieser Klasse
	public void run () {
		zeigeFortschritt();
	}
	
	
	
	// Fortschritts-Anzeige einmal von links nach rechts laufen lassen
	public void zeigeFortschritt () {
		// ...
	}
	
	
	
	// main-Methode; wird ausgef�hrt, wenn man diese Klasse als Programm startet
	public static void main (String[] args) {
		
		// neue Fenster-Instanz erstellen
		ProgressBarThread hauptfenster = new ProgressBarThread();  // Konstruktor-Aufruf!
		
		// Fenster initialisieren, zweiter Teil
		hauptfenster.setLocation(250, 350);
		hauptfenster.setVisible(true);
	}
	
}
