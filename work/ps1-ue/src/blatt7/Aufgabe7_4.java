/* 
 * $Id: Aufgabe7_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-04.
 */

package de.thaw.ps1.blatt7;

import de.thaw.ps1.NegativeArgumentException;



/**
 * Blatt 7, Aufgabe 4: Rechteck revolutions.
 * <p>Nachtrag 2005-12-08:<br> <strong>Hinweis:</strong> Diese L&ouml;sung ist
 * <em>nicht</em> korrekt.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt7.pdf">7. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe7_4 {
	
	/** Treiber. */
	public static void main (String[] args) {
		Rechteck rechteck;
		
		rechteck = new Rechteck(12.5F, 4.23F);  // completely arbitrary
		System.out.println(rechteck.getVolume());
		rechteck.setLaenge(5.96F);  // completely arbitrary
		System.out.println(rechteck.getVolume());
	}
	
}




/**
 * Ein Rechteck mit beliebigen Kantenl&auml;ngen.
 * @deprecated Die Verwendung dieser Klasse wird missbilligt, das sie nicht
 * korrekt funktioniert und und in Zukunft durch eine korrekte Version ersetzt
 * werden k&ouml;nnte.
 * @version 1
 */
class Rechteck extends de.thaw.ps1.blatt5.Rechteck {
	
	
	/** Die L&auml;nge des Rechtecks. */
	private float laenge;
	
	
	/** Die Breite des Rechtecks. */
	private float breite;
	
	
	/**
	 * Erstellt ein neues Rechteck mit den gegebenen Ma&szlig;en.
	 * @param laenge die L&auml;nge des Rechtecks
	 * @param breite die Breite des Rechtecks
	 * @throws NegativeArgumentException falls <code>laenge</code> oder
	 * <code>breite</code> negativ sind
	 */
	Rechteck (float laenge, float breite) throws NegativeArgumentException {
		if (laenge < 0.0F || breite < 0.0F) {
			throw new NegativeArgumentException();
		}
		this.laenge = laenge;
		this.breite = breite;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return die L&auml;nge dieses Rechtecks
	 */
	float getLaenge () {
		return this.laenge;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @return die Breite dieses Rechtecks
	 */
	float getBreite () {
		return this.breite;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @deprecated Die Verwendung dieser Methode wird missbilligt, das sie nicht
	 * korrekt funktioniert und und in Zukunft durch eine korrekte Version
	 * ersetzt werden k&ouml;nnte.
	 * @param neueLaenge die L&auml;nge, auf die das Rechteck ver&auml;ndert
	 * werden soll
	 * @throws NegativeArgumentException falls <code>neueLaenge</code> negativ ist
	 */
	void setLaenge (float neueLaenge) throws NegativeArgumentException {
		if (neueLaenge < 0) {
			throw new NegativeArgumentException();
		}
		this.laenge = neueLaenge;
	}
	
	
	/**
	 * Zugriffsmethode.
	 * @deprecated Die Verwendung dieser Methode wird missbilligt, das sie nicht
	 * korrekt funktioniert und und in Zukunft durch eine korrekte Version
	 * ersetzt werden k&ouml;nnte.
	 * @param neueBreite die Breite, auf die das Rechteck ver&auml;ndert
	 * werden soll
	 * @throws NegativeArgumentException falls <code>neueBreite</code> negativ ist
	 */
	void setBreite (float neueBreite) throws NegativeArgumentException {
		if (neueBreite < 0) {
			throw new NegativeArgumentException();
		}
		this.breite = neueBreite;
	}
	
	
	/**
	 * Berechnet den Fl&auml;cheninhalt dieses Rechtecks.
	 * @return die Fl&auml;che des Rechtecks
	 */
	float getVolume () {
		// :KLUDGE: area() would be a better name than getVolume()
		return this.laenge * this.breite;
	}
	
}
