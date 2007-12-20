/* $Id: Loesung73.java,v 1.2 2007-12-20 15:23:45 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 7-3.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt7/">Aufgabenblatt 7</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung73 {
	
	
	/**
	 * Durchsucht eine lineare Liste sequentiell. Zurueckgeliefert wird
	 * die erste Fundstelle.
	 * 
	 * @param list die zu durchsuchende Liste
	 * @param key den zu suchenden Wert
	 * @return dasjenige Listenelement, das als Kopf den Wert
	 * <code>key</code> hat
	 * @throws KeyNotFoundException falls die Liste leer ist oder den
	 * gesuchten Wert nicht enthaelt
	 */
	public static LinearList find (LinearList list, int key) {
		if (list == null) {
			throw new KeyNotFoundException();
		}
		if (list.head == key) {
			return list;
		}
		
		return find(list.tail, key);
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = new int[] {7, 4, 6, 9, 1, -1, 2, 3, 8, 0, 6, 5};
		
		// Array in Liste kopieren
		MutableLinearList list = new MutableLinearList();
		list.head = array[array.length - 1];
		for (int index = array.length - 2; index >= 0; index--) {
			list.addInFront(array[index]);
		}
		
		// Methodenaufruf und Ergebnisausgabe
		int valueToBeFound = 3;
		try {
			find(list, valueToBeFound);
			System.out.println("Die Zahl "+valueToBeFound+" ist in der Liste enthalten.");
		}
		catch (KeyNotFoundException exception) {
			System.out.println("Die Zahl "+valueToBeFound+" ist nicht in der Liste enthalten.");
		}
	}
	
}
