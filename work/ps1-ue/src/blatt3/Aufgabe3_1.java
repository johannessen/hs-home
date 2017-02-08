/* 
 * $Id: Aufgabe3_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-11-07.
 */

package de.thaw.ps1.blatt3;

import de.thaw.ps1.Utils;



/**
 * Blatt 3, Aufgabe 1: Vektoraddition.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt3.pdf">3. &Uuml;bungsblatt PS1</a>
 */
public class Aufgabe3_1 {
	
	
	/**
	 * Die Untergrenze f&uuml;r einen zuf&auml;lligen Vektorkomponenten.
	 */
	// 2 so that multiplication shows a change
	public static final int VECTOR_MIN_RANDOM = 2;
	
	
	/**
	 * Die Obergrenze f&uuml;r einen zuf&auml;lligen Vektorkomponenten.
	 */
	// 49 so that an addition of two components doesn't procude a three-digit number
	public static final int VECTOR_MAX_RANDOM = 49;
	
	
	/**
	 * Gibt einen zuf&auml;lligen Vektor der Dimension <var>dimension</var>
	 * zur&uuml;ck. Die einzelnen Komponenten liegen im Intervall
	 * [<var>minRandom</var>, <var>maxRandom</var>].
	 * @param dimension Die Dimension, die der zur&uuml;ckzugebene Vektor haben
	 * soll.
	 * @param minRandom Untergrenze der einzelnen (zuf&auml;lligen) Komponenten.
	 * @param maxRandom Obergrenze der einzelnen (zuf&auml;lligen) Komponenten.
	 * @return Ein zuf&auml;lliger Vektor der Dimension <var>dimension</var>
	 * als Array.
	 */
	public static int[] randomVector (int dimension, int minRandom, int maxRandom) {
		int[] vector = new int[dimension];
		int range;
		
		range = maxRandom - minRandom + 1;
		for (int index = 0; index < dimension; index++) {
			vector[index] = (int)Math.floor(Math.random() * range + minRandom);
		}
		return vector;
	}
	
	
	/**
	 * Gibt einen zuf&auml;lligen Vektor der Dimension <var>dimension</var>
	 * zur&uuml;ck. Die einzelnen Komponenten liegen im Intervall
	 * [{@link #VECTOR_MIN_RANDOM VECTOR_MIN_RANDOM}, {@link #VECTOR_MAX_RANDOM
	 * VECTOR_MAX_RANDOM}].
	 * @param dimension Die Dimension, die der zur&uuml;ckzugebene Vektor haben
	 * soll.
	 * @return Ein zuf&auml;lliger Vektor der Dimension <var>dimension</var>
	 * als Array.
	 */
	public static int[] randomVector (int dimension) {
		return Aufgabe3_1.randomVector(dimension, VECTOR_MIN_RANDOM, VECTOR_MAX_RANDOM);
	}
	
	
	/**
	 * Addiert zwei Vektoren, die als Arrays &uuml;bergeben werden. Beide
	 * Vektoren m&uuml;ssen dieselbe Dimension haben.
	 * @param vector1 Der erste Summand.
	 * @param vector2 Der zweite Summand.
	 * @return Einen neuen Vektor (als Array), der die Summe der beiden
	 * &uuml;bergebenen Vektoren enth&auml;lt.
	 * @throws IllegalArgumentException falls die Dimension der beiden Vektoren
	 * (also die L&auml;nge der beiden Arrays) nicht gleich ist.
	 */
	public static int[] vectorSum (int[] vector1, int[] vector2) {
		int[] sum;
		
		if (vector1.length != vector2.length) {
			throw new IllegalArgumentException();
		}
		
		sum = new int[vector1.length];
		for (int index = 0; index < vector1.length; index++) {
			sum[index] = vector1[index] + vector2[index];
		}
		return sum;
	}
	
	
	/**
	 * Gibt die Inhalte des &uuml;bergebenen Vektors an <code>System.out</code>
	 * aus. Die Ausgabe erfolgt in horizontaler Form.
	 * @param vector Der auszugebene Vektor als Array.
	 */
	public static void printVector (int[] vector) {
		System.out.print("(");
		if (vector.length > 0) {
			System.out.print(vector[0]);
		}
		for (int index = 1; index < vector.length; index++) {
			System.out.print(" "+vector[index]);
		}
		System.out.println(")");
	}
	
	
	/**
	 * Gibt die Summe zweier zuf&auml;lliger f&uuml;nfdimensionaler Vektoren
	 * aus.
	 */
	public static void main (String[] args) {
		final int length = 5;  // as per excersice instruction
		
		int[] vector1, vector2;
		
		vector1 = Aufgabe3_1.randomVector(length);
		vector2 = Aufgabe3_1.randomVector(length);
		Aufgabe3_1.printVector(Aufgabe3_1.vectorSum(vector1, vector2));
	}
	
}
