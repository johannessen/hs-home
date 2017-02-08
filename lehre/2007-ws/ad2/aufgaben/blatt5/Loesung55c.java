/* $Id: Loesung55c.java,v 1.3 2007/12/11 20:06:25 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.GregorianCalendar;



/**
 * Loesungsvorschlag fuer Aufgabe 5-5c.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt5/#aufgabe5-5">Aufgabenblatt 5, Aufgabe 5-5</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class Loesung55c {
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
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
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	public class Abteilung {
		
		protected String name;
		
		protected int budget;
		
	}
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	public class Mitarbeiter {
		
		protected String name;
		
		protected String vorname;
		
		protected int stellenNummer;
		
		protected java.util.Date geburtsdatum;
		
		
		protected Mitarbeiter vorgesetzter;
		
		protected Abteilung[] abteilungen;
		
	}
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	public class Start {
		
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
			karte1.inhalt = "Panorama";  // ist ja egal
			karte1.nettoPreis = 152.99;
			
			karte2.lfdNummer = 2006141;
			karte2.titel = "Nguma, Gabun Offshore";
			karte2.breite = 1023;
			karte2.hoehe = 795;
			karte2.zustand = "nicht mehr im Vertrieb";
			karte2.ausgabe = new GregorianCalendar(2007, 2, 13).getTime();
			karte2.inhalt = "Touristik";
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
