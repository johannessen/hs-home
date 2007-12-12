/* $Id: Loesung71.java,v 1.1 2007-12-12 00:37:46 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 7-1.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt7/">Aufgabenblatt 7</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung71 {
	
	
	/**
	 * Durchsucht einen Array sequentiell. Zurueckgeliefert wird der
	 * Index der ersten Fundstelle.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 * den Wert <code>key</code> hat
	 * @throws KeyNotFoundException falls der Array den gesuchten Wert
	 * nicht enthaelt
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public static int find (int[] array, int key) {
		for (int index = 0; index < array.length; index++) {
			if (array[index] == key) {
				// gefunden!
				return index;
			}
		}
		// wenn wir hierhin kommen, war die Suche erfolglos
		
		throw new KeyNotFoundException();
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		// bei der sequentiellen Suche darf der Array unsortiert sein
		int[] array = new int[] {7, 4, 6, 9, 1, -1, 2, 3, 8, 0, 6, 5};
		
		int valueToBeFound = Integer.parseInt(args[0]);
		try {
			int index = find(array, valueToBeFound);
			System.out.println("Die Zahl "+valueToBeFound+" ist im Array an der Stelle "+index+".");
		}
		catch (KeyNotFoundException exception) {
			System.out.println("Die Zahl "+valueToBeFound+" ist nicht im Array enthalten.");
		}
	}
	
}
