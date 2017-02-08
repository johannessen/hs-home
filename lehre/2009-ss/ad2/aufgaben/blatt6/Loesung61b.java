/* 
 * $Id: Loesung61b.java 2009-05-27 $
 * Arne Johannessen
 */


class ListNode {
	
	ListNode nextElement;
	
	int data;
	
	
	/**
	 * Methode als Vorschlag zur Loesung der Teilaufgabe 6-1 (b).
	 * Fuegt einen Knoten nach diesem in einer Liste ein.
	 */
	void insertAfter (ListNode element) {
		element.nextElement = this.nextElement;
		this.nextElement = element;
	}
	
}



class List {
	ListNode firstElement;
}



class Loesung61b {
	
	/**
	 * main-Methode als Treiber fuer den Aufruf von der Kommandozeile.
	 * Erzeugt eine Liste und ruft testweise obige Methode auf.
	 */
	public static void main (String[] args) {
		
		// Liste und Knoten erzeugen
		List list = new List();
		ListNode node0 = new ListNode();
		node0.data = 3;
		ListNode node1 = new ListNode();
		node1.data = 5;
		ListNode node2 = new ListNode();
		node2.data = 7;
		ListNode node3 = new ListNode();
		node3.data = 9;
		ListNode node4 = new ListNode();
		node4.data = 11;
		ListNode node5 = new ListNode();
		node5.data = 13;
		
		// Knoten aneinander anhaengen
		list.firstElement = node0;
		node0.nextElement = node1;
		node1.nextElement = node2;
		node2.nextElement = node3;
		node3.nextElement = node4;
		node4.nextElement = node5;
		node5.nextElement = null;  // hat keinen Nachfolger
		
		System.out.print("Vorher:  ");
		printList(list);
		
		ListNode newNode = new ListNode();
		newNode.data = 8;
		node2.insertAfter(newNode);
		
		System.out.print("Nachher: ");
		printList(list);
	}
	
	
	static void printList (List list) {
		// nur zur Vereinfachung des Testens, nicht Teil der Aufgabenstellung
		// waere an fuer sich einfacher als Objektmethode in 'List'
		ListNode node = list.firstElement;
		while (node != null) {
			System.out.print(node.data);
			System.out.print(" ");
			node = node.nextElement;
		}
		System.out.println();
	}
}
