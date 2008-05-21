/* $Id: LoesungE1.java,v 1.1 2008-05-21 01:40:05 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe E-1.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt4/">Aufgabenblatt 4</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class LoesungE1 {
	
	
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
		int i = 0;
		while (i < array.length) {
			if (array[i] == key) {
				// gefunden!
				return i;
			}
			
			i++;
		}
		
		// wenn wir hierhin kommen, war die Suche erfolglos
		return -1;
	}
	
}
