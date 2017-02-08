/* $Id: DivideAndConquer.java,v 1.1 2007-12-10 06:53:49 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 4-2. Divide-and-Conquer--Loesung fuer
 * das Maximum-Sub-Array--Problem mit O(n*log(n))-Komplexitaet.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt4/">Aufgabenblatt 4</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class DivideAndConquer extends AbstractMaximumSubArraySolver {
	
	
	private int maxState = 1000;  // erzeugt halbwegs brauchbare default-darstellung
	private int state = 0;
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array im Divide-and-Conquer--Verfahren.
	 * 
	 * @see #findMaximumSubArray(SubArray)
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 * <code>SubArray</code>, deren Gesamt-Array identisch mit dem
	 * dieser Methode uebergebenen <code>array</code> ist und deren
	 * Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 * des Maximum-Sub-Array--Problems darstellt.
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	public SubArray findMaximumSubArray (int[] array) {
		
		super.initSleepTime();
		
		// state initialisieren
		int i = array.length;
		this.maxState = 0;
		while (i > 0) {
			i /= 2;
			this.maxState += i;
		}
		this.state = 0;
		
		SubArray maximum = this.findMaximumSubArray(new SubArray(array));
		
		super.reportSleepTime();
		
		return maximum;
	}
	
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Sub-Array im Divide-and-Conquer--Verfahren.
	 * <p>
	 * Der Sub-Array wird  in zwei etwa gleich grosse Teile
	 * gesplittet, fuer die jeweils das Problem rekursiv geloest wird.
	 * Zusaetzlich wird der Maximum-Sub-Array ermittelt, innerhalb dessen
	 * Grenzen sich die Trennstelle befindet. Derjenige dieser drei
	 * Sub-Arrays, dessen Summe der Elemente am hoechsten ist, wird
	 * zurueckgeliefert.
	 * <p>
	 * Dieser Divide-and-Conquer--Algorithmus laesst sich als
	 * easy-split, hard-join charakterisieren.
	 * 
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 * <code>SubArray</code>, deren Gesamt-Array identisch mit dem des
	 * dieser Methode uebergebenen <code>array</code> ist und deren
	 * Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 * des Maximum-Sub-Array--Problems darstellt.
	 * @throws NullPointerException falls <code>array == null</code>
	 */
	protected SubArray findMaximumSubArray (SubArray array) {
		
		// trivialer Fall
		if (array.getLength() <= 1) {
			SubArray maximum = (SubArray)array.clone();
			if (maximum.getSum() < 0) {
				maximum.setLength(0);
			}
			return maximum;
		}
		
		super.reportState(this.state++, this.maxState);
		
		// Split (Divide)
		int leftIndex = array.getBeginIndex();
		int rightIndex = array.getEndIndex();
		int centerIndex = (leftIndex + rightIndex) / 2;
		SubArray leftPart = new SubArray(array, leftIndex, centerIndex);
		SubArray rightPart = new SubArray(array, centerIndex + 1, rightIndex);
		
		// Rekursion (Conquer)
		SubArray leftMaximum = this.findMaximumSubArray(leftPart);
		SubArray rightMaximum = this.findMaximumSubArray(rightPart);
		
		// Join (Merge)
		leftPart.findRightEdgeMaximum();
		rightPart.findLeftEdgeMaximum();
		SubArray centerMaximum = new SubArray(array, leftPart.getBeginIndex(), rightPart.getEndIndex());
		centerMaximum.setSum(leftPart.getSum() + rightPart.getSum());
		
		// groesstes der drei Maxima ermitteln (noch Join)
		SubArray maximum = centerMaximum;
		if (leftMaximum.getSum() > maximum.getSum()) {
			maximum = leftMaximum;
		}
		if (rightMaximum.getSum() > maximum.getSum()) {
			maximum = rightMaximum;
		}
		
		// Ergebnis zurueckgeben
		return maximum;
	}
	
}
