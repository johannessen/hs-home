/* $Id: Loesung22a.java,v 1.2 2008/04/30 02:15:25 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 2-2a.
 * <p>
 * Eine einfache lineare Liste mit Hilfsmethoden. Die Methoden in dieser
 * Klasse entsprechen genau denen der Original-Klasse
 * <code>MutableLinearList</code> aus der Aufgabenstellung. Die einzige
 * Aenderung sind die hinzugefuegten Javadoc- und Inline-Kommentare.
 * <p>
 * Zur Loesung dieser Aufgabe gehoert eigentlich vor allem, dass man sich
 * anschaulich klar macht, was da passiert. Das geht am besten mit Papier
 * und Bleistift -- oder durch Besuch des Tutoriums. Bei offenen Fragen
 * bitte E-Mail an mich!
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
class Loesung22a extends MutableLinearList {
	
	
	
	/**
	 * Fuegt einen neuen Wert als erstes Element in die Liste ein; die
	 * bestehende Liste wird zur Restliste (Rumpf).
	 * 
	 * @param value der einzufuegende Wert
	 */
	void addInFront (int value) {
		/* Bei dieser Methode ist das Was (oben im
		 * Javadoc-Kommentar) viel einfacher beschrieben als das
		 * Warum. Das Problem ist, dass das erste Element der Liste
		 * bei dieser Implementierung nicht durch ein anderes
		 * ersetzt werden kann.
		 * Die Loesung ist, eine exakte Kopie dieses ersten
		 * Elements zu erstellen, diese Kopie als zweites Element
		 * einzufuegen und dann dieses erste Element so zu
		 * behandeln, als waere es ein neu eingefuegtes.
		 */
		
		// bei dieser Implementierung stellt dasjenige Objekt, auf
		// dem diese Objektmethode aufgerufen wurde, immer das
		// erste Element der Liste dar
		MutableLinearList firstItem = this;
		
		// exakte Kopie (quasi einen Klon)
		// des ersten Listenelements erzeugen
		MutableLinearList secondItem = new MutableLinearList();
		secondItem.head = firstItem.head;
		secondItem.tail = firstItem.tail;
		
		// in erstes Listenelement neuen Wert eintragen und
		// Referenz zu Restliste so setzen, dass sie auf die Kopie
		// des bisher ersten Elements zeigt
		firstItem.head = value;
		firstItem.tail = secondItem;
	}
	
	
	
	/**
	 * Loescht das erste Element dieser Liste.
	 * 
	 * @throws NullPointerException falls das letzte Element der Liste
	 * geloescht wird
	 */
	void deleteFromFront () {
		/* Die Liste besteht aus _diesem_ Listenelement und dem
		 * Rest, der Rest heisst "tail".
		 * Ahnlich wie bei addInFront(int) oben kann _dieses_
		 * Listenelement (this) nicht weggeschmissen werden; statt
		 * dessen wird das erste Element zu einer exakten Kopie des
		 * zweiten gemacht. Dies hat den Effekt, dass das bisherige
		 * zweite Element keine auf es verweisende Referenz hat und
		 * es daher zum Entfernen durch Garbage Collection
		 * freigegeben ist. Uebrig bleibt eine Kopie des bisherigen
		 * zweiten und die Restliste -- also genau das, was wir
		 * wollten.
		 */
		
		// erstes Listenelement zu exakter Kopie des zweiten machen
		head = tail.head;
		tail = tail.tail;
	}
	
	
	
	/**
	 * Gibt den Wert (Kopf) des ersten Listenelemnts zurueck.
	 * 
	 * @return den Wert dieses Listenelements
	 */
	int firstElementValue () {
		/* Die Liste besteht aus _diesem_ Listenelement und dem
		 * Rest.
		 * Der Wert dieses Elements ist in der Objektvariablen
		 * "head" gespeichert, also geben wir die zurueck.
		 * ("tail" ist die Referenz auf die Restliste.)
		 */
		
		return head;
	}
	
	
	
	/**
	 * Berechnet die Laenge der Liste rekursiv.
	 * 
	 * @return die Anzahl der Elemente der Restliste, plus <code>1</code>
	 */
	int length () {
		/* Die Liste besteht aus _diesem_ Listenelement und dem
		 * Rest.
		 * Dieses Element ist genau ein Element. Also lassen wir
		 * rekursiv die Laenge des Rests berechnen, addieren 1
		 * fuer dieses Element, und geben diese Summe als Ergebnis
		 * zurueck.
		 */
		
		// fuer die Rekursion muss diese Methode auf tail aufrufbar
		// sein; diese Anweisung ersetzt tail transparent durch
		// eine Referenz des richtigen Typs
		MutableLinearList tail = MutableLinearList.cast(this.tail);  // Typanpassung
		
		// Rekursions-Abbruchbedingung:
		// ist die Restliste leer, bin ich das einzige Element
		if (tail == null) {
			return 1;
		}
		
		// Rekursionsfall:
		// die Gesamte Liste besteht aus mir und der Restliste
		return 1 + tail.length();
	}
	
	
	
	/**
	 * Gibt die gesamte Liste in einer Zeile aus.
	 */
	void println () {
		/* Die Liste besteht aus _diesem_ Listenelement und dem
		 * Rest.
		 * Wir geben also erst mal dieses Element aus und dann die
		 * Restliste (falls sie nicht leer ist). Am Ende der Liste
		 * wird ein Zeilenumbruch ausgegeben, der Optik halber.
		 */
		
		// fuer die Rekursion muss diese Methode auf tail aufrufbar
		// sein; diese Anweisung ersetzt tail transparent durch
		// eine Referenz des richtigen Typs
		MutableLinearList tail = MutableLinearList.cast(this.tail);  // Typanpassung
		
		System.out.print(head+" ");
		if (tail == null) {
			System.out.println();  // Zeilenumbruch erzeugen
			return;
		}
		tail.println();
	}
	
}
