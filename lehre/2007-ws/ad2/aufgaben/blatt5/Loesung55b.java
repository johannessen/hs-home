/* $Id: Loesung55b.java,v 1.3 2008/01/11 02:32:04 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 5-5b.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt5/#aufgabe5-5">Aufgabenblatt 5, Aufgabe 5-5</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung55b {
	
	
	
	
	public class Karte {
		
		protected int lfdNummer;
		
		protected String titel;
		
		protected Object modul;
		
		protected int breite;
		
		protected int hoehe;
		
		protected String zustand;
		
		protected java.util.Date ausgabe;
		
		protected String inhalt;
		
		protected double nettoPreis;
		
	}
	
	
	
	
	
	public class Abteilung {
		
		protected String name;
		
		protected int budget;
		
	}
	
	
	
	
	
	public class Mitarbeiter {
		
		protected String name;
		
		protected String vorname;
		
		protected int stellenNummer;
		
		protected java.util.Date geburtsdatum;
		
		
		protected Mitarbeiter vorgesetzter;
		
		protected Abteilung[] abteilungen;
		
	}
		
	
	
}
