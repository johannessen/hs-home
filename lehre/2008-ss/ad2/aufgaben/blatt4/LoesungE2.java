/* $Id: LoesungE2.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe E-2: Rekursion.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt4/">Aufgabenblatt 4</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class LoesungE2 {
	
	
	/**
	 * Durchsucht einen Array sequentiell. Zurueckgeliefert wird der
	 * Index der ersten Fundstelle.
	 * 
	 * @param array das zu durchsuchende Array
	 * @param key den zu suchenden Wert
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 * den Wert <code>key</code> hat
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int find (int[] array, int key, int index) {
		
		// zuerst die Abbruch-Bedingung der Rekursion
		if (index >= array.length) {
			// die Array-Grenze wurde ueberlaufen
			// => der gesamte Array wurde durchlaufen, aber erfolglos
			return -1;
		}
		
		if (array[index] == key) {
			// gefunden!
			return index;
		}
		else {
			// dieses Array-Element (an der Stelle 'index') ist nicht das gesuchte
			// => dieses Element ignorieren und beim naechsten Element weitersuchen
			return find(array, key, index + 1);
		}
	}
	
}
