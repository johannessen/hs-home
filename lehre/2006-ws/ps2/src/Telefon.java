/*
 * $Id: Telefon.java $
 * Kodierung: iso-8859-1 (ISO Latin 1)
 *
 * Autor: Prof. Dr. Bernhard Buerg, 2006
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * Aenderungen:
 * - Arne Johannessen (show->setVisible), 2006-10-30
 */


import java.awt.*;
import java.awt.event.*;

public class Telefon extends Frame
{
	// Hier Variablen f�r Komponenten deklarieren
	// Ereignisempf�nger
	ButtonListener butLis = new ButtonListener();
	// Bezeichnungsfeld f�r Anzeige der Telefonnummer
	Label nlabel = new Label();
	// Grundfl�che f�r Tastatur
	Panel tpanel = new Panel();
	// Grundfl�che f�r Telefonnummer
	Panel npanel = new Panel();
	// Zeichenfolge f�r eingegebene Telefonnummer
	String telnum;
	
	
	public Telefon()
	{
		super();
		
		// Hauptfenster einrichten
		setTitle("Telefon-Tastatur");
		setBackground(Color.LIGHT_GRAY);
		
		// Layout f�r Container festlegen
		setLayout(new BorderLayout());
		
		// Tasten f�r Telefontastatur:
		Button taste1 = new Button("1");
		Button taste2 = new Button("2");
		Button taste3 = new Button("3");
		Button taste4 = new Button("4");
		Button taste5 = new Button("5");
		Button taste6 = new Button("6");
		Button taste7 = new Button("7");
		Button taste8 = new Button("8");
		Button taste9 = new Button("9");
		Button taste0 = new Button("0");
		Button tstern = new Button("*");
		Button traute = new Button("#");
		Button tclear = new Button("C");
		
		// Telefontasten in Panel f�r Tastatur einf�gen:
		tpanel.setLayout(new GridLayout(5, 0, 5, 5));
		tpanel.add(taste1);
		tpanel.add(taste2);
		tpanel.add(taste3);
		tpanel.add(taste4);
		tpanel.add(taste5);
		tpanel.add(taste6);
		tpanel.add(taste7);
		tpanel.add(taste8);
		tpanel.add(taste9);
		tpanel.add(tstern);
		tpanel.add(taste0);
		tpanel.add(traute);
		tpanel.add(tclear);
		
		// Ereignisempf�nger registrieren
		taste1.addActionListener(butLis);
		taste2.addActionListener(butLis);
		taste3.addActionListener(butLis);
		taste4.addActionListener(butLis);
		taste5.addActionListener(butLis);
		taste6.addActionListener(butLis);
		taste7.addActionListener(butLis);
		taste8.addActionListener(butLis);
		taste9.addActionListener(butLis);
		taste0.addActionListener(butLis);
		tclear.addActionListener(butLis);
		tstern.addActionListener(butLis);
		traute.addActionListener(butLis);
		
		// Bezeichnungsfeld f�r Telefonnummer
		npanel.setLayout(new GridLayout());
		
		// Anf�nglich leeres Bezeichnungsfeld anzeigen
		telnum = "";
		nlabel.setText(telnum);
		
		// Ausrichtung f�r Bezeichnungsfeld festlegen
		nlabel.setAlignment(Label.LEFT);
		
		// Bezeichnungsfeld in Panel einf�gen
		npanel.add(nlabel);
		
		// Grundfl�chen in Frame hinzuf�gen
		add(tpanel, BorderLayout.CENTER);
		add(npanel, BorderLayout.SOUTH);
		
		// Fenster schlie�en
		addWindowListener(new WindowLauscher());
	}
	
	
	// Hier Ereignisbehandlungsmethoden f�r Komponenten
	// Schaltfl�chenklicks auswerten
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String s;
			
			// Befehlszeichenfolge von Taste �bernehmen
			s = e.getActionCommand();
			
			// Zu bisheriger Telefonnummer hinzuf�gen
			telnum = telnum + e.getActionCommand();
			
			// Falls "C" gedr�ckt, Nummer l�schen
			if (s == "C") telnum="";
			nlabel.setText(telnum);
		}
	}
	
	
	// Fenster und Anwendung schlie�en
	protected static final class WindowLauscher extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	
	
	public static void main(String args[])
	{
		// Fenster erzeugen und anzeigen
		Telefon hauptfenster = new Telefon();
		hauptfenster.setSize(180,200);
		hauptfenster.setLocation(200,300);
		hauptfenster.setVisible(true);
	}
}

