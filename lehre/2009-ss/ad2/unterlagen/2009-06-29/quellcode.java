/* 
 * $Id: quellcode.java 2009-06-29 $
 * Arne Johannessen
 */


import java.util.*;


class Aufg {

	static public void main (String[] args) {
		
		LinkedList L = new LinkedList();
		
		while (1 == 1) {
			System.out.println("1. Fuege einen neuen Namen hinzu");
			System.out.println("2. Loesche einen Namen");
			System.out.println("3. Suche ein Mitglied");
			System.out.println("4. Programm beenden");
			String eingabe = Utils.inputString();
			int eingabeAlsZahl = Integer.parseInt(eingabe);
			if (eingabeAlsZahl == 4) {
				return;
			}
			
			if (eingabeAlsZahl == 1) {
				System.out.print("Bitte den hinzuzufuegenden Namen eingeben: ");
				String name = Utils.inputString();
				L.add(name);
			}
			
			
		}
		
	}

}
