/* $Id: Loesung34.java,v 1.1 2008-05-19 04:44:47 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 3-4.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung34 {
	
	
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
	int find (int[] array, int key) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == key) {
				// gefunden!
				return i;
			}
		}
		// wenn wir hierhin kommen, war die Suche erfolglos
		
		return -1;
	}
	
}
