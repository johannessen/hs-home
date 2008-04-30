/* $Id: MutableLinearList.java,v 1.2 2008-04-30 01:45:52 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Eine einfache lineare Liste mit Hilfsmethoden.
 * 
 * @see Loesung22a
 */
class MutableLinearList extends LinearList {
	
	
	
	void addInFront (int value) {
		MutableLinearList firstItem = this;
		
		MutableLinearList secondItem = new MutableLinearList();
		secondItem.head = firstItem.head;
		secondItem.tail = firstItem.tail;
		
		firstItem.head = value;
		firstItem.tail = secondItem;
	}
	
	
	void deleteFromFront () {
		head = tail.head;
		tail = tail.tail;
	}
	
	
	int firstElementValue () {
		return head;
	}
	
	
	int length () {
		MutableLinearList tail = MutableLinearList.cast(this.tail);  // Typanpassung
		
		if (tail == null) {
			return 1;
		}
		return 1 + tail.length();
	}
	
	
	void println () {
		MutableLinearList tail = MutableLinearList.cast(this.tail);  // Typanpassung
		
		System.out.print(head+" ");
		if (tail == null) {
			System.out.println();  // Zeilenumbruch erzeugen
			return;
		}
		tail.println();
	}
	
	
	
	
	
	
	/** 
	 * Typumwandlung von <code>LinearList</code> zu
	 * <code>MutableLinearList</code>. Diese Methode liefert die
	 * uebergebene lineare Liste als <code>MutableLinearList</code>
	 * zurueck.
	 * <p>
	 * Diese Methode ist fuer rekursive Algorithmen in dieser Klasse
	 * notwendig, weil die eigentliche Referenz auf die Restliste
	 * (bzw. auf das jeweils naechste Element) geerbt wird und den
	 * Typ <code>LinearList</code> hat. Weil die Klasse
	 * <code>LinearList</code> keine Methoden anbietet, ist es nicht
	 * ohne Weiteres moeglich, Methoden rekursiv aufzurufen.
	 * Beispielsweise wuerde <code>this.tail.print();</code> einen
	 * Compiler-Fehler hervorrufen, weil <code>tail</code>
	 * (in <code>LinearList</code>) als Objektvariable vom Typ
	 * <code>LinearList</code> deklariert ist, in der Klasse
	 * <code>LinearList</code> aber keine
	 * Methode <code>print()</code> enthalten ist.
	 * <p>
	 * Handelt es sich bei <code>list</code> um ein Objekt des Typs
	 * <code>MutableLinearList</code>, genuegt an fuer sich ein
	 * simpler Type-Cast. Andernfalls muss ein neues Objekt des Typs
	 * <code>MutableLinearList</code> erstellt und die Felder manuell
	 * aus <code>list</code> kopiert werden.
	 * 
	 * @param list die in eine <code>MutableLinearList</code> zu
	 * wandelnde lineare Liste
	 * @return ein Objekt des Typs <code>MutableLinearList</code>,
	 * das dem in <code>list</code> uebergebenen Objekt entspricht.
	 * <code>null</code>, falls <code>list == null</code>.
	 */
	private static MutableLinearList cast (LinearList list) {
		if (list == null) {
			return null;
		}
		
		if (list instanceof MutableLinearList) {
			return (MutableLinearList)list;
		}
		else {
			MutableLinearList mutableList = new MutableLinearList();
			mutableList.head = list.head;
			mutableList.tail = list.tail;
			return mutableList;
		}
	}
	
	
	/** 
	 * Gibt alle Elemente einer Liste aus. Die Liste muss den Typ
	 * <code>MutableLinearList</code> haben.
	 * 
	 * @param list die auszugebende lineare Liste
	 */
	static void println (MutableLinearList list) {
		if (list != null) {
			list.println();
		}
	}
	
	
	
	/** 
	 * Gibt alle Elemente einer Liste aus. Der Typ der Liste muss das
	 * Interface <code>Collection</code> implementieren; dies ist
	 * beispielsweise fuer die Klasse <code>LinkedList</code> der Fall.
	 * 
	 * @param list die auszugebende lineare Liste
	 */
	public static void println (java.util.Collection list) {
		// zum Durchlaufen von Datenstrukturen aus java.util.* wird aus Performancegruenden immer ein Iterator verwendet
		java.util.Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
		}
		System.out.println();
	}
	
}
