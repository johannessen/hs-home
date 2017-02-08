/*
 * $Id: MenuDemo2.java $
 * Kodierung: iso-8859-1 (ISO Latin 1)
 *
 * Autor: Prof. Dr. Bernhard Buerg, 2006
 * Hochschule Karlsruhe - Technik und Wirtschaft
 *
 * Aenderungen:
 * - Arne Johannessen (Bloecke verbessert, show->setVisible), 2006-11-19
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.datatransfer.*;


public class MenuDemo2 extends JFrame implements ActionListener, ItemListener {
	
	
	// Hier Variablen für Komponenten deklarieren
	// auf die im Programm laufend zugegriffen werden muss
	private JCheckBoxMenuItem menuDateiCISymbolleiste;
	private ButtonGroup menuDateiModus;
	private JToolBar symbolLeiste;
	private JTextArea textAnzeige;
	private Clipboard zal;
	
	
	public MenuDemo2() {
		super();
		
		// Hauptfenster einrichten
		setTitle("Clipboard-Demo");
		
		// die Zwischenablage besorgen
		zal = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		// Hier Komponenten einfügen
		// eine JTextArea in der Mitte
		textAnzeige = new JTextArea();
		getContentPane().add(textAnzeige, BorderLayout.CENTER);
		
		// Menüleiste
		JMenuBar menuLeiste = erzeugeMenueLeiste();    
		setJMenuBar(menuLeiste);
		
		// Symbolleiste
		symbolLeiste = erzeugeSymbolLeiste();
		getContentPane().add(symbolLeiste, BorderLayout.NORTH);
		
		// Lauscher für Fenster-schließen registrieren
		addWindowListener(new WindowLauscher());
		
		// Lauscher für Mausereignisse im Fenster (-> Kontextmenü)
		KontextMenuLauscher kml = new KontextMenuLauscher(this);
		textAnzeige.addMouseListener(kml);  // für Mausklicks
				//innerhalb Textbereich
	} // Konstruktor
	
	
	// Diese Methode stellt die Menüleiste zusammen
	private JMenuBar erzeugeMenueLeiste() {
		JMenuBar menueLeiste = new JMenuBar();
		JMenu menuInfo = new JMenu("Programm-Info");
		
		// Menü Datei
		JMenu menuDatei = new JMenu("Datei");
		JMenuItem menuDateiOeffnen = new JMenuItem("Datei öffnen", new ImageIcon("Open.gif"));
		menuDateiOeffnen.addActionListener(this);
		JMenuItem menuDateiSpeichern = new JMenuItem("Datei speichern", new ImageIcon("Save.gif"));
		menuDateiSpeichern.addActionListener(this);
		menuDateiModus = new ButtonGroup();
		JRadioButtonMenuItem  menuDateiRBNormal = new JRadioButtonMenuItem("Normal", true);
		JRadioButtonMenuItem  menuDateiRBProfi = new JRadioButtonMenuItem("Profi", false);
		JRadioButtonMenuItem  menuDateiRBGuru = new JRadioButtonMenuItem("Guru", false);
		menuDateiModus.add(menuDateiRBNormal);
		menuDateiModus.add(menuDateiRBProfi);
		menuDateiModus.add(menuDateiRBGuru);
		menuDateiCISymbolleiste = new JCheckBoxMenuItem("Symbolleiste");
		menuDateiCISymbolleiste.setSelected(true);
		menuDateiCISymbolleiste.addItemListener(this);
		
		// Untermenü Datei/Extras
		JMenu menuDateiExtras = new JMenu("Extras");
		JMenuItem menuDateiExtrasPfade = new JMenuItem("Pfade", 'P');
		menuDateiExtrasPfade.addActionListener(this);
		JMenuItem menuDateiExtrasFarben = new JMenuItem("Farben", 'F');
		menuDateiExtrasFarben.addActionListener(this);
		JMenuItem menuDateiExtrasSprache = new JMenuItem("Sprache", 'S');
		menuDateiExtrasSprache.addActionListener(this);
		
		// die Accelerators für die Einträge Pfade, Farben,
		// Sprache erzeugen
		KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
		menuDateiExtrasPfade.setAccelerator(ks);
		ks = KeyStroke.getKeyStroke('F', Event.CTRL_MASK);
		menuDateiExtrasFarben.setAccelerator(ks);
		ks = KeyStroke.getKeyStroke('S', Event.SHIFT_MASK);
		menuDateiExtrasSprache.setAccelerator(ks);
		
		JMenuItem menuDateiBeenden = new JMenuItem("Programmbeenden");
		menuDateiBeenden.addActionListener(this);
		
		// das Menü Datei zusammenstellen
		menuDatei.add(menuDateiOeffnen);
		menuDatei.add(menuDateiSpeichern);
		menuDatei.addSeparator();
		menuDatei.add(menuDateiRBNormal);
		menuDatei.add(menuDateiRBProfi);
		menuDatei.add(menuDateiRBGuru);
		menuDatei.addSeparator();
		menuDatei.add(menuDateiCISymbolleiste);
		menuDatei.add(menuDateiExtras);
		menuDatei.addSeparator();
		menuDatei.add(menuDateiBeenden);
		
		// das Untermenü Extras zusammenstellen
		menuDateiExtras.add(menuDateiExtrasPfade);
		menuDateiExtras.add(menuDateiExtrasFarben);
		menuDateiExtras.add(menuDateiExtrasSprache);
		
		// Menüleiste zusammenstellen
		menueLeiste.add(menuInfo); 
		menueLeiste.add(menuDatei);
		
		return menueLeiste;
	}
	
	
	// Diese Methode erzeugt die Symbolleiste
	private JToolBar erzeugeSymbolLeiste() {
		JToolBar symbolLeiste = new JToolBar();
		JButton btnOeffnen = new JButton(new ImageIcon("open.gif"));
		btnOeffnen.setActionCommand("Datei öffnen");
		btnOeffnen.setToolTipText("Öffnet eine Datei");
		symbolLeiste.add(btnOeffnen);
		btnOeffnen.addActionListener(this);
		JButton btnSpeichern = new JButton(new ImageIcon("save.gif"));
		btnSpeichern.setActionCommand("Datei speichern");
		btnSpeichern.setToolTipText("Speichert die aktuelle Datei");
		symbolLeiste.add(btnSpeichern);
		btnSpeichern.addActionListener(this);
		return symbolLeiste;
	}
	
	
	// Hier Ereignisbehandlungsmethoden für Komponenten
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Programm beenden")) {
			System.exit(0);
		}
		if(cmd.equals("Kopieren")) {
			String auswahl = textAnzeige.getSelectedText();
			if(auswahl != null) {
				StringSelection text = new StringSelection(auswahl);
				zal.setContents(text, null);
			}
			return;
		}
		if(cmd.equals("Einfügen")) {
			Transferable inhalt = zal.getContents(this);
			if(inhalt == null) {
				return;
			}
			DataFlavor stringTyp = DataFlavor.stringFlavor;
			if(inhalt.isDataFlavorSupported(stringTyp)) {
				try {
					String text = (String)
					inhalt.getTransferData(stringTyp);
					textAnzeige.replaceSelection(text);
				}
				catch(Exception ex) {
					System.out.println(ex);
				}
			} // if
		} // if
	} // actionPerformed
	
	
	public void itemStateChanged(ItemEvent e) {
		Object item = e.getItemSelectable();
		if(item == menuDateiCISymbolleiste) {
			if(e.getStateChange() == ItemEvent.DESELECTED) {
				getContentPane().remove(symbolLeiste);
			}
			else {
				getContentPane().add(symbolLeiste, BorderLayout.NORTH);
			}
			repaint();
		}
	} // itemStateChanged
	
	
	// innere Klassen:
	
	
	// Fenster und Anwendung schließen
	protected final class WindowLauscher extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	}
	
	
	// Kontextmenü-Handling
	protected final class KontextMenuLauscher extends MouseAdapter {
		
		MenuDemo2 fenster;
		KontextMenuLauscher(MenuDemo2 f) {
			fenster = f;
		}
		
		public void mouseReleased(MouseEvent e) {
			if(e.isPopupTrigger() == true) {
				// Kontextmenü erzeugen 
				erzeugeKontextMenue(e);
			}
		}
		
		public void mousePressed(MouseEvent e) {
			if(e.isPopupTrigger() == true) {
				// Kontextmenü erzeugen
				erzeugeKontextMenue(e); 
			}
		}
		
		// erzeugt ein Kontext-Menü und zeigt es an
		private void erzeugeKontextMenue(MouseEvent e) {
			JPopupMenu popup = new JPopupMenu();
			JMenuItem popupKopieren = new JMenuItem("Kopieren");
			popupKopieren.addActionListener(fenster);
			JMenuItem popupEinfuegen = new JMenuItem("Einfügen");
			popupEinfuegen.addActionListener(fenster); // Einfuegen
			// deaktivieren, falls die Zwischenablage keine
			// brauchbaren Daten enthält
			Transferable zalInhalt = zal.getContents(this);
			if(zalInhalt == null) {
				popupEinfuegen.setEnabled(false);
			}
			else {
				// es gibt Daten in der Zwischenablage;prüfen,ob sie
				// brauchbar sind, d.h. Zeichenketten
				DataFlavor stringTyp = DataFlavor.stringFlavor;
				if(zalInhalt.isDataFlavorSupported(stringTyp) == false){
					popupEinfuegen.setEnabled(false);
					System.out.println("disabled");
				}
			}
			
			JMenuItem popupEnde = new JMenuItem("Programm beenden");
			popupEnde.addActionListener(fenster);
			popup.add(popupKopieren);
			popup.add(popupEinfuegen);
			popup.addSeparator();
			popup.add(popupEnde);
			
			// anzeigen an aktueller Mausposition
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}
	
	
	public static void main(String args[]) {
		// Fenster erzeugen und anzeigen
		MenuDemo2 hauptfenster = new MenuDemo2();
		hauptfenster.setSize(400, 350);  // oder pack()
		hauptfenster.setLocation(200, 300);
		hauptfenster.setVisible(true);
	}
	
} 