/* 
 * $Id: Liste.java 2009-05-27 $
 * Arne Johannessen
 */


// eine moeglichst einfache lineare Liste
// (eine bessere Loesung waere ListNode als innere Klasse)

class List {
	ListNode firstElement;
}

class ListNode {
	ListNode nextElement;
	int data;
}
