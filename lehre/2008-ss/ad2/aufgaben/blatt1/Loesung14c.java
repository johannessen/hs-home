/* $Id: Loesung14c.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.GregorianCalendar;



/**
 * Loesungsvorschlag fuer Aufgabe 1-4c: Instanzen statischer Datenstrukturen.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt1/#aufgabe14c">Aufgabenblatt 1, Aufgabe 1-4 (c)</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung14c {
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
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
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	class Abteilung {
		
		String name;
		
		int budget;
		
	}
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	class Mitarbeiter {
		
		String name;
		
		String vorname;
		
		int stellenNummer;
		
		java.util.Date geburtsdatum;
		
		
		Mitarbeiter vorgesetzter;
		
		Abteilung[] abteilungen;
		
	}
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	class Start {
		
		public static void main (String[] args) {
			
			// erst die Karten:
			
			Karte karte1 = new Karte();
			Karte karte2 = new Karte();
			
			karte1.lfdNummer = 2007054;
			karte1.titel = "More Soer, Projected 4D Lines Block 62/3";
			karte1.breite = 687;
			karte1.hoehe = 581;
			karte1.zustand = "Vertrieb";
			karte1.ausgabe = new GregorianCalendar(2007, 8, 26).getTime();
			karte1.sparte = "Panorama";  // ist ja egal
			karte1.nettoPreis = 152.99;
			
			karte2.lfdNummer = 2006141;
			karte2.titel = "Nguma, Gabun Offshore";
			karte2.breite = 1023;
			karte2.hoehe = 795;
			karte2.zustand = "nicht mehr im Vertrieb";
			karte2.ausgabe = new GregorianCalendar(2007, 2, 13).getTime();
			karte2.sparte = "Touristik";
			karte2.nettoPreis = 220.00;
			
			
			
			// jetzt die Abteilungen und Mitarbeiter:
			
			Abteilung herstellung = new Abteilung();
			Abteilung vertrieb = new Abteilung();
			
			Mitarbeiter oberboss = new Mitarbeiter();
			Mitarbeiter herstellungsleiter = new Mitarbeiter();  // Abteilungsleiter herstellung
			Mitarbeiter vertriebsleiter = new Mitarbeiter();  // Abteilungsleiter vertrieb
			Mitarbeiter kartendesigner = new Mitarbeiter();  // Mitarbeiter in herstellung
			Mitarbeiter katalogdesigner = new Mitarbeiter();  // Mitarbeiter in beiden Abteilungen
			
			
			
			// Relationen setzen:
			
			oberboss.vorgesetzter = null;
			oberboss.abteilungen = new Abteilung[0];  // steht ueber allem
			
			herstellungsleiter.vorgesetzter = oberboss;
			herstellungsleiter.abteilungen = new Abteilung[] {herstellung};
			
			vertriebsleiter.vorgesetzter = oberboss;
			vertriebsleiter.abteilungen = new Abteilung[] {vertrieb};
			
			kartendesigner.vorgesetzter = herstellungsleiter;
			kartendesigner.abteilungen = new Abteilung[] {herstellung};
			
			katalogdesigner.vorgesetzter = vertriebsleiter;  // es kann nur einen Vorgesetzten geben
			katalogdesigner.abteilungen = new Abteilung[] {herstellung, vertrieb};
			
			
			
			// Stammdaten setzen:
			
			herstellung.name = "KDH";
			herstellung.budget = 80000;
			
			vertrieb.name = "Vertrieb";
			vertrieb.budget = 25000;
			
			oberboss.name = "Wups";
			oberboss.vorname = "Willi";
			oberboss.stellenNummer = 10001;
			oberboss.geburtsdatum = new GregorianCalendar(1968, 7, 25).getTime();
			
			herstellungsleiter.name = "Bockwurst";
			herstellungsleiter.vorname = "Barny";
			herstellungsleiter.stellenNummer = 10014;
			herstellungsleiter.geburtsdatum = new GregorianCalendar(1941, 4, 1).getTime();
			
			vertriebsleiter.name = "Life";
			vertriebsleiter.vorname = "Deep";
			vertriebsleiter.stellenNummer = 10042;
			vertriebsleiter.geburtsdatum = new GregorianCalendar(1942, 12, 30).getTime();
			
			kartendesigner.name = "Bumskopp";
			kartendesigner.vorname = "Dankward";
			kartendesigner.stellenNummer = 10321;
			kartendesigner.geburtsdatum = new GregorianCalendar(1954, 3, 21).getTime();
			
			katalogdesigner.name = "Bunker";
			katalogdesigner.vorname = "Archie";
			katalogdesigner.stellenNummer = 10101;
			katalogdesigner.geburtsdatum = new GregorianCalendar(1900, 8, 15).getTime();
			
		}
		
	}
		
	
	
}
