/* $Id: Loesung31.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.LinkedList;


/**
 * Loesungsvorschlag fuer Aufgabe 3-1: Naiver Ansatz zur sequentiellen Suche.
 * <p>
 * In der Praxis ist der Algorithmus dieser Klasse Schwachsinn;
 * siehe Aufgabe 3-3.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung31 {
	
	
	/**
	 * Durchsucht eine lineare Liste sequentiell. Zurueckgeliefert wird
	 * der Index der ersten Fundstelle.
	 * 
	 * @param list die zu durchsuchende Liste
	 * @param key den zu suchenden Wert
	 * @return den Index desjenigen Elements in <code>array</code>, das
	 * den Wert <code>key</code> hat
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int find (LinkedList list, String key) {
		for (int i = 0; i < list.size(); i++) {
			Object item = list.get(i);
			if (item.equals(key)) {
				// gefunden!
				return i;
			}
		}
		// wenn wir hierhin kommen, war die Suche erfolglos
		
		return -1;
	}
	
}
