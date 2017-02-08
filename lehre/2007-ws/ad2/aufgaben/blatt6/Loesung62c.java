/* $Id: Loesung62c.java,v 1.1 2007/12/11 20:08:59 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 6-2c.
 * <p>
 * Eine einfache lineare Liste mit Hilfsmethoden. Diese Klasse erweitert
 * die Klasse <code>java.util.LinkedList</code> durch Vererbung. Sie
 * enthaelt alle Methoden, die auch die Klasse
 * <code>MutableLinearList</code> enthaelt, setzt diese aber auf die
 * Klasse <code>LinkedList</code> um. Im Prinzip ist dies ein informeller
 * Klassen-Adapter.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung62c extends java.util.LinkedList {
	
	
	
	/**
	 * Fuegt einen neuen Wert als erstes Element in die Liste ein; die
	 * bestehende Liste wird zur Restliste (Rumpf).
	 * 
	 * @param value der einzufuegende Wert
	 */
	public void addInFront (int value) {
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
	public void deleteFromFront () {
		super.removeFirst();
	}
	
	
	
	/**
	 * Gibt den Wert (Kopf) des ersten Listenelemnts zurueck.
	 * 
	 * @return den Wert dieses Listenelements
	 */
	public int firstElementValue () {
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
	public int length () {
		return super.size();
	}
	
	
	
	/**
	 * Gibt die gesamte Liste in einer Zeile aus.
	 */
	public void println () {
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
		Object item = null;
		for (java.util.Iterator iterator = list.iterator(); iterator.hasNext(); item = iterator.next()) {
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
	public static void println (MutableLinearList list) {
		MutableLinearList.println(list);
	}
	
}
