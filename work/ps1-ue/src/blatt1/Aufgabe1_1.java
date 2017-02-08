/* 
 * $Id: Aufgabe1_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-10-20.
 */

package de.thaw.ps1.blatt1;

import de.thaw.ps1.Utils;



/**
 * Blatt 1, Aufgabe 1: Gimme names.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt1.pdf">1. &Uuml;bungsblatt PS1</a>
 */
class Aufgabe1_1 {
	// no re-usable code inside, thus not "public"
	
	/**
	 * Fordert zur Eingabe des Vornamens und anschliessend zur Eingabe des
	 * Nachnamens auf; daraufhin werden die eingegebenen Namen in einer Zeile
	 * ausgegeben.
	 */
	public static void main (String[] args) {
		String givenName = null;
		String familyName = null;
		String fullName = null;
		
		givenName = Utils.inputString("Gib Deinen Vornamen ein: ");
		familyName = Utils.inputString("Gib Deinen Nachnamen ein: ");
		fullName = givenName+((givenName.length() > 0 && familyName.length() > 0) ? " " : "")+familyName;
		if (fullName.length() > 0) {
			System.out.println("Du hast den Namen \u201e"+fullName+"\u201c eingegeben.");
		}
		else {
			System.out.println("Du hast keinen Namen eigegeben.");
		}
	}
	
}
