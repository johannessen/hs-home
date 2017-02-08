/* Dateiname: Baum.java
 * Programmiersprachen I, Hochschule Karlsruhe
 * Beispiel 2 zum Tutorium, Handzettel 1
 * Autor: Arne Johannessen
 * erstellt anhand einer Vorlage von Prof. Dr. B. Buerg
 * geschrieben am 2006-04-21
 */

/**
 * Zeichnet einen aufrecht stehenden dreieckigen Baum, der
 * aus 'o's besteht. Der Baum hat eine Hoehe von 10 Zeilen.
 */
public class Baum
{
	
	public static void main (String args [])
	{
		
		// Hoehe des Baums: 10 Zeilen (vorgegeben)
		int hoehe = 10;
		
		// den Baum Zeile fuer Zeile zeichnen
		for (int zeile = hoehe; zeile > 0; zeile--)
		{
			
			// Einrueckung fuer Baum erzeugen
			for (int randSpalte = 1; randSpalte < zeile; randSpalte++)
			{
				System.out.print(' ');
			}
			
			// eigentlichen Baum erzeugen
			int zeilenBreite = 2 * (hoehe - zeile) + 1;
			for (int baumSpalte = 0; baumSpalte < zeilenBreite; baumSpalte++)
			{
				System.out.print('o');
			}
			
			System.out.println();
		}
		
	}
	
}
