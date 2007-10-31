/* $Id: MaximumSubArraySolver.java,v 1.5 2007-10-31 00:29:47 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Schnittstelle fuer die Loesung des Maximum-Sub-Array--Problems.
 * <p>
 * Die folgende Klasse zeigt beispielhaft, wie der Rumpf einer Loesung
 * aussehen koennte:<p>
 * <pre><code>
 * public class MsaProblemLoeser implements MaximumSubArraySolver {
 *     
 *     public SubArray findMaximumSubArray (int[] array) {
 *         SubArray ergebnis;
 *         
 *          // Loesungs-Algorithmus hier
 *          // (siehe Manuskript)
 *         
 *         return ergebnis;
 *     }
 *     
 *     
 *     public static void main (String[] args) {
 *         
 *          // Vorbereitung des Eingabewerts
 *         int[] array = SubArray.createRandomArray();
 *         
 *          // Berechnung des Ergebnisses
 *         MsaProblemLoeser problemLoeser = new MsaProblemLoeser();
 *         SubArray maximumSubArray = problemLoeser.findMaximumSubArray(array);
 *         
 *          // Ausgabe des Ergebnisses
 *         System.out.println(maximumSubArray);
 *         System.out.println("Summe: ["+maximumSubArray.getSum()+"]");
 *     }
 * }
 * </code></pre>
 * 
 * @see SubArray
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.5 $
 */
public interface MaximumSubArraySolver {
	
	
	/**
	 * Loest das Maximum-Sub-Array--Problem fuer den uebergebenen
	 * Array.
	 * <p>
	 * Die Loesung ist genau diejenige Teilfolge des Gesamt-Arrays,
	 * die von allen moeglichen Teilfolgen die hoechste Summe ihrer
	 * Elemente aufweist. Der Gesamt-Array kann sowohl positive als
	 * auch negative Zahlen beinhalten; die Implementierung muss dies
	 * beruecksichtigen. Existieren nur negative Elemente, ist die
	 * Teilfolge mit der hoechsten Summe die leere Teilfolge mit der
	 * Summe null (<code>0</code>).
	 * <p>
	 * Das Verhalten dieser Methode fuer <code>array == null</code>
	 * ist nicht definiert.
	 * 
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
