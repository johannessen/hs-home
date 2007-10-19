//
//  Interpol.java
//  Interpol
//
//  Created on 2005-06-25, last modified on 2006-11-13 by Arne Johannessen.
//  Copyright (c) 2005/2006 THAWsoftware.
//
//  Benutzung, Vervielf�ltigung und Ver�nderung im Rahmen der Veranstaltungen
//  Topographie und Programmiersprachen 2 an der Hochschule Karlsruhe ist
//  gestattet, sofern dieser Urheberrechtshinweis intakt bleibt. Alle anderen
//  Rechte, insbesondere das der Ver�ffentlichung, sind vorbehalten.
//


public class Interpol {
	
	
	private final double AEQUIDISTANZ = 2.0;
	
	private final double ZAEHLKURVENDISTANZ = 10.0;
	
	
	/**
	 * @param koteAnfang den Wert der H�henkote am Anfang der Messstrecke
	 * @param koteEnde den Wert der H�henkote am Ende der Messstrecke
	 * @param distanz die L�nge der Messstrecke
	 */
	public void gibAusInterpolierteHoehen (double koteAnfang, double koteEnde, double distanz) {
		
		// schleifenz�hlrichtung vorbereiten
		double richtung = (koteAnfang < koteEnde ? 1.0 : -1.0);  // vorzeichen der messrichtung
		double aequidistanz = AEQUIDISTANZ * richtung;  // �quidistanz mit vorzeichen versehen
		
		// schleifenvariablen deklarieren
		double naechsteHoehe;  // h�henzahl der n�chsten anzugebenden isohypse
		double naechsteDistanz;  // strecke vom anfangspunkt bis zur n�chsten isohypse
		
		// durch alle hoehenlinienwerte iterieren
		for (naechsteHoehe = Math.floor(koteAnfang / AEQUIDISTANZ) * AEQUIDISTANZ;
				richtung * naechsteHoehe <= richtung * koteEnde;
				naechsteHoehe += aequidistanz) {
			
			// interpolieren
			naechsteDistanz = distanz * (naechsteHoehe - koteAnfang) / (koteEnde - koteAnfang);
			if (naechsteDistanz < 0) {
				continue;  // nur positive Werte ausgeben
			}
			
			// z�hlkurven besonders markieren
			if (naechsteHoehe % ZAEHLKURVENDISTANZ == 0) {
				gibAus("--");
			}
			else {
				gibAus("  ");
			}
			
			// ergebnis ausgeben
			gibAus(" "+naechsteHoehe+" @ "+(Math.round(naechsteDistanz * 100.0) / 100.0)+"\n");
		}
	}
	
	
	// Extra-Methode, um Erweiterbarkeit zu verbessern
	protected void gibAus (String zeile) {
		System.out.print(zeile);
	}
	
	
	/**
	 * @param args Es wird ein Array mit genau drei Elementen erwartet. Das erste
	 * Element soll den Wert der H�henkote am Anfang, das zweite am Ende der
	 * Messstrecke beinhalten. Das dritte Element soll die L�nge der Messstrecke
	 * enthalten.
	 */
    public static void main (String[] args) {
		
		// parameterzahl pr�fen
        if (args.length < 3) {
			throw new IllegalArgumentException("zu wenige Parameter");
		}
        if (args.length > 3) {
			throw new IllegalArgumentException("zu viele Parameter");
		}
		
		// wenn wir hierhin kommen, stimmt die parameterzahl
		Interpol interpol = new Interpol();
		interpol.gibAusInterpolierteHoehen(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}
	
}
