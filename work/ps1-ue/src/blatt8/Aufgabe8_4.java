/* 
 * $Id: Aufgabe8_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-08.
 */

package de.thaw.ps1.blatt8;

import de.thaw.ps1.blatt3.Aufgabe3_3;



/**
 * Blatt 8, Aufgabe 4: Kommandozeilenparameter.
 * <p><strong>Hinweis:</strong> Diese L&ouml;sung ist <em>nicht</em> korrekt.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt8.pdf">8. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class Aufgabe8_4 {
	
	
	/**
	 * Berechnet den Mittelwert der &uuml;bergebenen Zahlen. Dazu werden die
	 * Strings im &uuml;bergebenen Array zun&auml;chst mittels
	 * {@link Double#parseDouble(String) Double.parseDouble(String)} in Zahlen
	 * umgewandelt.
	 * @param args enth&auml;lt String-Repr&auml;sentationen der Zahlen, deren
	 * Durchschnitt berechnet werden soll
	 * @throws NumberFormatException falls einer der &uuml;bergebenen Strings
	 * nicht in eine Zahl umgewandelt werden kann
	 * @see Aufgabe3_3#mean(double[])
	 */
	public static void main (String[] args) {
		double[] numbers = new double[args.length];
		
		for (int index = args.length - 1; index >= 0; index--) {
			numbers[index] = Double.parseDouble(args[index]);
		}
		
		try {
			System.out.println("Mittelwert: "+Aufgabe3_3.mean(numbers));
		}
		catch (IllegalArgumentException exception) {
			// silently ignore
		}
	}
	
}
