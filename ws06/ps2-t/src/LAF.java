/*
 * $Id: LAF.java,v 1.1 2007-10-19 18:31:16 arne Exp $
 * Kodierung: iso-8859-1 (ISO Latin 1)
 *
 * Autor: Prof. Dr. Bernhard Buerg, 2006
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * Aenderungen:
 * - Arne Johannessen (Bloecke verbessert, show->setVisible), 2006-10-30
 */


/*
 * LAF.java -Programm zum Umschalten des Look&Feels und zur
 * Demonstration des BoxLayout-Managers
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LAF extends JFrame {
	
	Box rbox;
	JPanel lbox;
	JButton bmotif, bwindows, bmetal;
	JCheckBox chk1, chk2;
	ButtonListener butLis = new ButtonListener();
	Container contentPane = getContentPane();
	
	
	public LAF() {
		super();
		
		// Hauptfenster einrichten
		setTitle("Look and Feel");
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setLayout(new GridLayout(0,2));
		bmotif = new JButton("Motif");
		bwindows = new JButton("Windows");
		bmetal = new JButton("Metal");
		bmotif.addActionListener(butLis);
		bwindows.addActionListener(butLis);
		bmetal.addActionListener(butLis);
		
		// Rechte Grundfläche mit Schaltflächen zum
		// Umschalten des Look&Feels
		// Box-Container mit vertikaler Ausrichtung
		rbox = Box.createVerticalBox();
		rbox.add(Box.createGlue());
		rbox.add(bmotif);
		rbox.add(bwindows);
		rbox.add(bmetal);
		rbox.add(Box.createGlue());
		
		// Linke Grundfläche zeigt lediglich zwei
		// Kontrollkästchen zur Demonstration des
		// Erscheinungsbildes
		// BoxLayout-Manager für linke Grundfläche festlegen
		lbox = new JPanel();
		lbox.setLayout(new BoxLayout(lbox, BoxLayout.Y_AXIS));
		chk1 = new JCheckBox("Unterstrichen");
		chk2 = new JCheckBox("Kursiv");
		lbox.add(Box.createVerticalStrut(30));
		lbox.add(chk1);
		lbox.add(chk2);
		contentPane.add(lbox);
		contentPane.add(rbox);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} // Konstruktor LAF()
	
	
	// Ereignisbehandlung für Schaltflächen zum
	// Umschalten des Look&Feels
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getSource()==bmotif) {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				}
				if (e.getSource()==bwindows) {
					UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				}
				if (e.getSource()==bmetal) {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				}
			}
			catch(Exception ex) {
			}
			
			// Das neue Look&Feel allen Komponenten mitteilen
			SwingUtilities.updateComponentTreeUI(contentPane);
			
		} // actionPerformed()
	} // class ButtonListener()
	
	
	public static void main(String args[]) {
		// Fenster erzeugen und anzeigen
		LAF hauptfenster = new LAF();
		hauptfenster.setSize(300,200);
		hauptfenster.setLocation(200,300);
		hauptfenster.setVisible(true);
	} // main
	
} // class LAF()
