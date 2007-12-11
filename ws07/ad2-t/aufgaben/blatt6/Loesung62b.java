/* $Id: Loesung62b.java,v 1.1 2007-12-11 20:08:59 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 6-2b.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung62b {
	
	
	
	/**
	 * Erstellt eine lineare Liste mit den drei Elementen 42, 8 und 15
	 * in dieser Reihenfolge.
	 * <p>
	 * <em>Anmerkung.</em> Die Aufgabenstellung erfordert nicht, dass
	 * diese Methode irgend etwas mit der erzeugten Liste macht. Eine
	 * Methode, die keine Ausgabe erzeugt und auch nicht wie diese die
	 * erzeugte Liste als Rueckgabewert liefert, waere daher auch eine
	 * korrekte Loesung. (Eine solche Loesung haette die
	 * Methodensignatur <code>static void start ()</code> und
	 * enthielte keine <code>return</code>-Anweisung.)
	 * 
	 * @return die erstellte Liste
	 */
	public static MutableLinearList start () {
		
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
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		MutableLinearList.println(start());
	}
	
}
