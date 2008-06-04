/* $Id: Loesung81.java,v 1.1 2008-06-04 14:30:13 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 8-1. Divide-and-Conquer--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n*log(n))-Komplexitaet.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt8/">Aufgabenblatt 8</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
class Loesung81 {
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Divide-and-Conquer--Verfahren.
	 * 
	 * @see #findMaximumSum(int[],int,int)
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @return die maximale Teilsumme von <code>array</code>
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	int findMaximumSum (int[] array) {
		return findMaximumSum(array, 0, array.length - 1);
	}
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Sub-Array im Divide-and-Conquer--Verfahren.
	 * <p>
	 * Der Sub-Array wird  in zwei etwa gleich grosse Teile
	 * gesplittet, fuer die jeweils das Problem rekursiv geloest wird.
	 * Zusaetzlich wird diejenige maximale Teilsumme ermittelt,
	 * welche die Trennstelle ueberbrueckt. Diejenige dieser drei
	 * Teilsummen, die am hoechsten ist, wird zurueckgeliefert.
	 * <p>
	 * Dieser Divide-and-Conquer--Algorithmus laesst sich als
	 * easy-split, hard-join charakterisieren.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 *  heranzuziehende Gesamt-Array
	 * @param leftIndex die linke Begrenzung des Sub-Arrays, fuer den
	 *  die maximale Teilsumme ermittelt werden soll
	 * @param rightIndex die rechte Begrenzung des Sub-Arrays, fuer den
	 *  die maximale Teilsumme ermittelt werden soll
	 * @return die maximale Teilsumme im durch die Parameter
	 *  definierten Sub-Array
	 * @throws NullPointerException falls <code>array == null</code>
	 * @throws ArrayIndexOutOfBoundsException falls die beiden Indizes
	 *  keine gueltigen und sinnvollen Array-Indizes sind
	 */
	int findMaximumSum (int[] array, int leftIndex, int rightIndex) {
		
		// trivialer Fall
		if (leftIndex == rightIndex) {
			if (array[leftIndex] < 0) {
				return 0;
			}
			return array[leftIndex];
		}
		
		// Split (Divide)
		int centerIndex = (leftIndex + rightIndex) / 2;
		
		// Rekursion (Conquer)
		int leftMaximumSum = findMaximumSum(array, leftIndex, centerIndex);
		int rightMaximumSum = findMaximumSum(array, centerIndex + 1, rightIndex);
		
		// Join (Merge)
		int leftPartRightEdgeMaximumSum = SubArray.findRightEdgeMaximum(array, leftIndex, centerIndex).getSum();
		int rightPartLeftEdgeMaximumSum = SubArray.findLeftEdgeMaximum(array, centerIndex + 1, rightIndex).getSum();
		int centerMaximumSum = leftPartRightEdgeMaximumSum + rightPartLeftEdgeMaximumSum;
		
		// groesstes der drei Maxima ermitteln (noch Join)
		int maximumSum = centerMaximumSum;
		if (leftMaximumSum > maximumSum) {
			maximumSum = leftMaximumSum;
		}
		if (rightMaximumSum > maximumSum) {
			maximumSum = rightMaximumSum;
		}
		
		// Ergebnis zurueckgeben
		return maximumSum;
	}
	
}
