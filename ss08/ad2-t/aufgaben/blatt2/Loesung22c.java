/* $Id: Loesung22c.java,v 1.2 2008-04-30 02:15:19 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 2-2c.
 * <p>
 * Eine einfache lineare Liste mit Hilfsmethoden. Diese Klasse erweitert
 * die Klasse <code>java.util.LinkedList</code> durch Vererbung. Sie
 * enthaelt alle Methoden, die auch die Klasse
 * <code>MutableLinearList</code> enthaelt, setzt diese aber auf die
 * Klasse <code>LinkedList</code> um. Im Prinzip ist dies ein informeller
 * Klassen-Adapter.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung62c extends java.util.LinkedList {
	
	
	
	/**
	 * Fuegt einen neuen Wert als erstes Element in die Liste ein; die
	 * bestehende Liste wird zur Restliste (Rumpf).
	 * 
	 * @param value der einzufuegende Wert
	 */
	void addInFront (int value) {
		// LinkedList versteht nur Objekte:
		// wir machen daher aus einer Zahl ein Objekt
		Object valueAsObject = new Integer(value);
		
		super.addFirst(valueAsObject);
	}
	
	
	
	/**
	 * Loescht das erste Element dieser Liste.
	 * 
	 * @throws NullPointerException falls das letzte Element der Liste
	 * geloescht wird
	 */
	void deleteFromFront () {
		super.removeFirst();
	}
	
	
	
	/**
	 * Gibt den Wert (Kopf) des ersten Listenelemnts zurueck.
	 * 
	 * @return den Wert dieses Listenelements
	 */
	int firstElementValue () {
		Object valueAsObject = super.getFirst();
		
		// LinkedList versteht nur Objekte:
		// wir machen daher aus einem Objekt wieder eine Zahl
		Integer value = (Integer)valueAsObject;
		return value.intValue();
	}
	
	
	
	/**
	 * Berechnet die Laenge der Liste rekursiv.
	 * 
	 * @return die Anzahl der Elemente der Restliste, plus <code>1</code>
	 */
	int length () {
		return super.size();
	}
	
	
	
	/**
	 * Gibt die gesamte Liste in einer Zeile aus.
	 */
	void println () {
		Loesung62c.println(this);  // siehe unten
	}
	
	
	
	/**
	 * Gibt eine Liste komplett in einer Zeile aus.
	 * 
	 * @param list die auszugebende Liste
	 */
	public static void println (java.util.Collection list) {
		// zur Ausgabe einer LinkedList gibt es keine fertige
		// Methode; es muss also mit einer Schleife die gesamte
		// Liste durchlaufen und dabei ausgegeben werden
		
		// zum Durchlaufen von Datenstrukturen aus java.util.* wird
		// aus Performancegruenden immer ein Iterator verwendet
		for (java.util.Iterator i = list.iterator(); i.hasNext(); ) {  // die ganze Liste durchlaufen
			Object item = i.next();  // das jeweils naechste Element aus der Liste holen
			System.out.print(item+" ");
		}
		System.out.println();
	}
	
	
	
	/**
	 * Gibt eine Liste komplett in einer Zeile aus.
	 * 
	 * @param list die auszugebende Liste
	 * @see MutableLinearList#println(MutableLinearList)
	 */
	static void println (MutableLinearList list) {
		MutableLinearList.println(list);
	}
	
}
