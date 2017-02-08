/* $Id: Loesung14b.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 1-4b: Tabellen Abteilungen und Mitarbeiter.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt1/#aufgabe1-4b">Aufgabenblatt 1, Aufgabe 1-4 (b)</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung14b {
	
	
	
	
	class Karte {
		
		int lfdNummer;
		
		String titel;
		
		Object modul;
		
		int breite;
		
		int hoehe;
		
		String zustand;
		
		java.util.Date ausgabe;
		
		String sparte;
		
		double nettoPreis;
		
	}
	
	
	
	
	
	class Abteilung {
		
		String name;
		
		int budget;
		
	}
	
	
	
	
	
	class Mitarbeiter {
		
		String name;
		
		String vorname;
		
		int stellenNummer;
		
		java.util.Date geburtsdatum;
		
		
		Mitarbeiter vorgesetzter;
		
		Abteilung[] abteilungen;
		
	}
		
	
	
}
