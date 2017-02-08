/* 
 * $Id: Aufgabe3_4.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-08.
 */

package de.thaw.ps1.blatt3;

import de.thaw.ps1.Utils;



/**
 * Blatt 3, Aufgabe 4: Ein mehrdimensionales Array.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt3.pdf">3. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe3_4 {
	
	
	/**
	 * Gibt die Summe zweier zuf&auml;lliger f&uuml;nfdimensionaler Vektoren
	 * aus.
	 * @see Aufgabe3_1#main(String[])
	 */
	public static void main (String[] args) {
		final int length = 5;  // as per excersice instruction
		final int vectorCount = 3;  // as per excersice instruction
		
		int[][] vectors = new int[vectorCount][length];
		
		vectors[0] = Aufgabe3_1.randomVector(length);
		vectors[1] = Aufgabe3_1.randomVector(length);
		vectors[2] = Aufgabe3_1.vectorSum(vectors[0], vectors[1]);
		Aufgabe3_1.printVector(vectors[2]);
	}
	
}
