/* $Id: Loesung32.java,v 1.2 2008/06/04 19:51:14 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.Iterator;
import java.util.LinkedList;


/**
 * Loesungsvorschlag fuer Aufgabe 3-2: Sequentielle Suche (LinkedList).
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung32 {
	
	
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
		int index = 0;
		for (Iterator i = list.iterator(); i.hasNext(); ) {
			Object item = i.next();
			if (item.equals(key)) {
				// gefunden!
				return index;
			}
			index++;
		}
		// wenn wir hierhin kommen, war die Suche erfolglos
		
		return -1;
	}
	
}
