/* 
 * $Id: Aufgabe3_2.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt3;

import de.thaw.ps1.Utils;
import de.thaw.ps1.blatt2.Aufgabe2_2;



/**
 * Blatt 3, Aufgabe 2: F&uuml;llen eines Arrays.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt3.pdf">3. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe3_2 {
	
	
	/**
	 * Die weiblichen Ordnungszahlen von "erste" bis "zw&ouml;lfte".
	 */
	public static final String[] FEMININE_ORDINAL_NUMBER = {null, "erste", "zweite", "dritte", "vierte", "f\uu00fcnfte", "sechste", "siebte", "achte", "neunte", "zehnte", "elfte", "zw\u00f6lfte"};
	
	
	/**
	 * Liest f&uuml;r jeden der Komponenten des &uuml;bergebenen Vektors einen
	 * Wert ein und speichert ihn im &uuml;bergebenen Vektor <var>vector</var>.
	 * @param vector Der Vektor (als Array), in den Werte eingelesen werden
	 * sollen. Dieser Array wird ver&auml;ndert.
	 * @return Den mit den eingelesenen Daten gef&uuml;llten Vektor.
	 */
	protected static double[] inputVector (double[] vector) {
		String prompt;
		
		for (int index = 0; index < vector.length; index++) {
			prompt = "Bitte gib die "+Aufgabe2_2.numberToString(index + 1, Aufgabe3_2.FEMININE_ORDINAL_NUMBER, ".")+" Zahl ein: ";
			vector[index] = (double)Utils.inputFloat(prompt);
		}
		return vector;
	}
	
	
	/**
	 * Liest f&uuml;nf Zahlen ein, ignoriert aber die Eingaben.
	 */
	public static void main (String[] args) {
		final int length = 5;  // as per excersice instruction
		
		System.out.println("Es werden nun "+Aufgabe2_2.numberToString(length)+" Vektor-Komponenten abgefragt.");
		Aufgabe3_2.inputVector(new double[length]);
	}
	
}
