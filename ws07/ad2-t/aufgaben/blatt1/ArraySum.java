/* $Id: ArraySum.java,v 1.2 2007-10-27 20:00:33 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Klasse fuer Aufgabe 1-2.
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt1/">Aufgabenblatt 1</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class ArraySum {
	
	
	/** Das als Grundlage dieser Aufgabe dienende Array. */
	public static final int[] ARRAY =
			{97, 79, 66, -77, -42, -23, 57, -93, -84, -6, 62, 75};
	
	
	
	/**
	 * Gibt alle Elemente eines Arrays aus.
	 * @param array das auszugebende Array
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public static void printArray (int[] array) {
		for (int index = 0; index < array.length; index++) {
			System.out.print(array[index]+" ");
		}
		System.out.println();
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int[] array = ARRAY;
		printArray(array);
		System.out.println("Anzahl der Elemente: "+array.length);
	}
	
}
