/* 
 * $Id: Aufgabe3_3.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-08.
 */

package de.thaw.ps1.blatt3;

import de.thaw.ps1.Utils;



/**
 * Blatt 3, Aufgabe 3: Der Klausurdurchschnittsrechner.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt3.pdf">3. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe3_3 {
	
	
	/**
	 * Errechnet das arithmetische Mittel aller Zahlen in der &uuml;bergebenen
	 * Zahlenmenge.
	 * @param set Der die Zahlenmenge enthaltende Array.
	 * @return den Mittelwert der Zahlenmenge.
	 * @throws IllegalArgumentException falls die Zahlenmenge leer ist.
	 */
	public static double mean (double[] set) {
		double mean;
		
		if (set.length == 0) {
			throw new IllegalArgumentException();
		}
		
		mean = 0.0;
		for (int index = 0; index < set.length; index++) {
			mean += set[index];
		}
		return (mean / set.length);
	}
	
	
	/**
	 * Fragt eine Zahl <var>n</var> vom Benutzer ab und liest anschlie&szlig;end
	 * genau <var>n</var> Flie&szlig;kommazahlen ein, errechnet deren
	 * arithmetisches Mittel und gibt dieses aus.
	 */
	public static void main (String[] args) {
		int markCount;
		double[] marks;
		double average;
		
		markCount = Utils.inputInteger("Gib die Anzahl der Noten f\u00fcr die Durchschnittsberechnung ein: ");
		if (markCount <= 0) {
			System.out.println("Die Notenanzahl muss positiv (also gr\u00f6\u00dfer als null) sein.");
			return;
		}
		marks = new double[markCount];
		System.out.println("Die Noten werden nun nacheinander als Zahl abgefragt.");
		average = Aufgabe3_3.mean(Aufgabe3_2.inputVector(marks));
		System.out.println("Der Durchschnitt dieser Noten ist "+(Math.round(average * 10.0) / 10.0)+".");
	}
	
}
