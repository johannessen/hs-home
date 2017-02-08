/* 
 * $Id: Aufgabe2_2.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt2;

import de.thaw.ps1.Utils;



/**
 * Blatt 2, Aufgabe 2: switch-Anweisung
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt2.pdf">2. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe2_2 {
	
	
	/**
	 * Die Kardinal-Zahlen von null (0) bis zw&ouml;lf (12).
	 */
	public static final String[] CARDINAL_NUMBER = {"null", "eins", "zwei", "drei", "vier", "f\uu00fcnf", "sechs", "sieben", "acht", "neun", "zehn", "elf", "zw\u00f6lf"};
	
	
	/**
	 * Wandelt eine Zahl in einen Text um. Zahlen, die einem Index des Arrays
	 * <var>stringRepresentation</var> entsprechen, werden in den Inhalt des
	 * Array-Elements mit dem Index <var>number</var> gewandelt, falls dieses
	 * Array-Element ungleich <code>null</code> ist. In allen anderen
	 * F&auml;llen wird die Zahl in einen aus Ziffern bestehenden Text
	 * umgewandelt und <var>suffix</var> angef&uuml;gt.
	 * <p>Beispielsweise kann man diese Methode mit
	 * {@link #CARDINAL_NUMBER CARDINAL_NUMBER} f&uuml;r
	 * <var>stringRepresentation</var> aufrufen, um die Zahl <var>number</var>
	 * in einen Text umzuwandeln, der im Bereich von null (<code>0</code>) bis
	 * zw&ouml;lf (<code>12</code>) ausgeschrieben und ansonsten eine Zahl in
	 * Ziffern ist. Dieses Format entspricht den Empfehlungen des Dudens.
	 * @param number Die umzuwandelnde Zahl.
	 * @param stringRepresentation der Array, der entweder die
	 * Text-Repr&auml;sentationen der Indizes enth&auml;lt (oder stattdessen
	 * <code>null</code>, falls das betreffende Array-Element keine
	 * "ausgeschriebene" textuelle Repr&auml;sentation hat).
	 * @param suffix Der an eine nicht-textuelle Repr&auml;sentation von
	 * <var>number</var> anzuf&uuml;gende String. Falls <code>null</code>, wird
	 * kein String angef&uuml;gt.
	 * @return Eine Textrepr&auml;sentation der Zahl <var>number</var>.
	 */
	public static String numberToString (int number, String[] stringRepresentation, String suffix) {
		if (number >= 0 && number < stringRepresentation.length && stringRepresentation[number] != null) {
			return stringRepresentation[number];
		}
		else if (suffix != null) {
			return Integer.toString(number)+suffix;
		}
		else {
			return Integer.toString(number);
		}
	}
	
	
	/**
	 * Wandelt eine Zahl in einen Text um. Zahlen zwischen null (<code>0</code>)
	 * und zw&ouml;lf (<code>12</code>) werden in die entsprechende
	 * ausgeschriebene (kardinale) Zahl gewandelt; andere Zahlen werden in einen
	 * aus Ziffern bestehenden Text umgewandelt.
	 * @param number Die umzuwandelnde Zahl.
	 * @return Eine Textrepr&auml;sentation der Zahl <var>number</var>.
	 */
	public static String numberToString (int number) {
		return Aufgabe2_2.numberToString(number, Aufgabe2_2.CARDINAL_NUMBER, null);
	}
	
	
	/**
	 * Liest eine ganze Zahl ein und gibt sie textuell aus, falls sie zwischen
	 * zwei (<code>2</code>) und acht (<code>8</code>) liegt.
	 */
	public static void main (String[] args) {
		int number;
		
		number = Utils.inputInteger("Gib eine Zahl ein: ");
		
		switch (number) {
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				System.out.println(Aufgabe2_2.numberToString(number));
				break;
			
			default:
				System.out.println("Zahl nicht bekannt");
		}
	}
	
}
