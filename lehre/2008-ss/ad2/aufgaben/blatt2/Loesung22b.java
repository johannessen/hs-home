/* $Id: Loesung22b.java,v 1.2 2008/04/30 02:15:25 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 2-2b.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung22b {
	
	
	
	/**
	 * Erstellt eine lineare Liste mit den drei Elementen 42, 8 und 15
	 * in dieser Reihenfolge.
	 * <p>
	 * <em>Anmerkung.</em> Die Aufgabenstellung erfordert nicht, dass
	 * diese Methode irgend etwas mit der erzeugten Liste macht. Eine
	 * Methode, die keine Ausgabe erzeugt und auch nicht wie diese die
	 * erzeugte Liste als Rueckgabewert liefert, waere daher auch eine
	 * korrekte Loesung. (Eine solche Loesung haette die
	 * Methodensignatur <code>void start ()</code> und enthielte keine
	 * <code>return</code>-Anweisung.)
	 * 
	 * @return die erstellte Liste
	 */
	MutableLinearList start () {
		
		MutableLinearList list = new MutableLinearList();
		MutableLinearList item2 = new MutableLinearList();
		MutableLinearList item3 = new MutableLinearList();
		
		list.head = 42;
		list.tail = item2;
		
		item2.head = 8;
		item2.tail = item3;
		
		item3.head = 15;
		item3.tail = null;
		
		
/*		// alternative Implementierung:
		MutableLinearList list = new MutableLinearList();
		list.head = 15;
		list.addInFront(8);
		list.addInFront(42);
*/		
		
		return list;
	}
	
}
