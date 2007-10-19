/* $Id: MaximumSubArraySolver.java,v 1.1 2007-10-19 20:45:13 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Schnittstelle fuer die Loesung des Maximum-Sub-Array--Problems.
 * @see SubArray
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version 0.1
 */
public interface MaximumSubArraySolver {
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array.
	 * <p>Die Loesung ist genau diejenige Teilfolge des Gesamt-Arrays,
	 * die von allen moeglichen Teilfolgen die hoechste Summe ihrer
	 * Elemente aufweist. Der Gesamt-Array kann sowohl positive als
	 * auch negative Zahlen beinhalten; die Implementierung muss dies
	 * beruecksichtigen. Existieren nur negative Elemente, ist die
	 * Teilfolge mit der hoechsten Summe die leere Teilfolge mit dem
	 * Wert null (<code>0</code>).
	 * <p>Das Verhalten dieser Methode fuer <code>array == null</code>
	 * ist nicht definiert.
	 * @param array der fuer die Bestimmung der Problemloesung
	 * heranzuziehende Gesamt-Array
	 * @return eine neu erstellte Instanz der Klasse
	 * <code>SubArray</code>, deren Gesamt-Array identisch mit dem
	 * dieser Methode uebergebenen <code>array</code> ist und deren
	 * Sub-Array--Definition derart ist, dass sie eine korrekte Loesung
	 * des Maximum-Sub-Array--Problems darstellt.
	 */
	public SubArray findMaximumSubArray (int[] array) ;
	
}
