/* 
 * $Id: Math.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-12-08.
 */

package de.thaw.ps1.blatt8;



/**
 * Die Klasse <code>&#8230;.blatt8.Math</code> enth&auml;lt Methoden zur
 * Berechnung der Fakult&auml;t.
 * @author Arne Johannessen
 */
public class Math {
	
	
	/**
	 * Gibt die Fakult&auml;t der Zahl <code>number</code> zur&uuml;ck.
	 * <p>Diese Methode ist nicht gegen Bin&auml;r&uuml;berlauf gesch&uuml;tzt
	 * und liefert nur f&uuml;r Werte, die nicht gr&ouml;&szlig;er als
	 * <code>12</code> sind, definierte Ergebnisse.
	 * {@link de.thaw.ps1.blatt2.Aufgabe2_3#faculty(BigInteger) Aufgabe2_3#faculty(BigInteger)}
	 * ist eine Implementierung einer Fakult&auml;tsberechnung, die f&uuml;r
	 * Zahlen mit beliebiger Gr&ouml;&szlig;e korrekt funktioniert.
	 * @param number Eine String-Repr&auml;sentation der Zahl, deren
	 * Fakult&auml;t berechnet werden soll.
	 * @return Die berechnete Fakult&auml;t.
	 * @see de.thaw.ps1.blatt2.Aufgabe2_3#faculty(BigInteger)
	 * @see de.thaw.ps1.blatt1.Aufgabe1_4#faculty(long)
	 */
	public static int faculty (int number) {
		int faculty;
		
		faculty = 1;
		while (number > 0) {
			faculty *= number--;
		}
		return faculty;
	}
	
	
	/**
	 * Treiber. Berechnet die Fakult&auml;t der Zahl, die als erstes Element
	 * im Array <code>args</code> &uuml;begeben wird. F&uuml;r die Berechnung
	 * wird die Methode {@link #faculty(int) faculty(int)} benutzt.
	 * @param args ein Array, der als erstes Element eine
	 * String-Repr&auml;sentation der Zahl enth&auml;lt, deren Fakult&auml;t
	 * berechnet werden soll
	 * @throws ArrayIndexOutOfBoundsException falls der Array kein Element
	 * enth&auml;lt (zum Beispiel nachdem diese Klasse von einem Terminal aus
	 * ohne zus&auml;tzliche Parameter gestartet wurde)
	 * @see #faculty(int)
	 * @see de.thaw.ps1.blatt2.Aufgabe2_3#main(String[])
	 */
	public static void main (String[] args) {
		int faculty;
		int number;
		
		number = Integer.parseInt(args[0]);
		faculty = Math.faculty(number);
		System.out.println(faculty);
	}
	
}
