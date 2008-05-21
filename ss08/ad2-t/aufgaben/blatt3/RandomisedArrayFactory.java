/* $Id: RandomisedArrayFactory.java,v 1.2 2008-05-21 01:40:51 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class RandomisedArrayFactory {
	
	
	// non-instantiable
	private RandomisedArrayFactory () {
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Der Bereich, aus dem die Zufallszahlen
	 * stammen sollen, wird uebergeben. Die Bereichsgrenzen selbst
	 * koennen ebenfalls als Zufallszahlen auftreten.
	 * <p>
	 * <b>Beispiel.</b> Der folgende Code erstellt einen mit
	 * Zufallszahlen zwischen -10 und +15 gefüllten Integer-Array
	 * <code>array</code> mit 5 Elementen:<p>
	 * <pre><code>
	 * int[] array = RandomisedArrayFactory.createRandomArray(5, -10, 15);
	 * </code></pre>
	 * 
	 * @param arrayLength die Laenge des Arrays
	 * @param lowerLimit die kleinstmoegliche Zufallszahl
	 * @param upperLimit die groesstmoegliche Zufallszahl
	 */
	public static int[] createRandomArray (final int arrayLength, final int lowerLimit, final int upperLimit) {
		final int[] array = new int[arrayLength];
		final double range = upperLimit - lowerLimit + 1;
		for (int index = 0; index < array.length; index++) {
			array[index] = (int)(Math.random() * range) + lowerLimit;
		}
		return array;
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Der Bereich, aus dem die Zufallszahlen
	 * stammen sollen, wird uebergeben. Die Bereichsgrenzen selbst
	 * koennen ebenfalls als Zufallszahlen auftreten.
	 * <p>
	 * Dies entspricht genau:<br><code>
	 * int[] array = RandomisedArrayFactory.createRandomArray(arrayLength, -limits, limits);
	 * </code><p>
	 * 
	 * @param arrayLength die Laenge des Arrays
	 * @param limits der maximale Abstand, den die Zufallszahlen von
	 *  der Zahl null (<code>0</code>) haben sollen
	 * @see #createRandomArray(int, int, int)
	 */
	public static int[] createRandomArray (final int arrayLength, int limits) {
		limits = Math.abs(limits);
		return RandomisedArrayFactory.createRandomArray(arrayLength, -limits, limits);
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Fuer den Bereich, aus dem die Zufallszahlen
	 * stammen sollen, werden die Standardgrenzen [-99, 99] verwendet.
	 * <p>
	 * Dies entspricht genau:<br><code>
	 * int[] array = RandomisedArrayFactory.createRandomArray(arrayLength, 99);
	 * </code><p>
	 * 
	 * @param arrayLength die Laenge des Arrays
	 * @see #createRandomArray(int, int, int)
	 * @see #createRandomArray(int, int)
	 */
	public static int[] createRandomArray (final int arrayLength) {
		return RandomisedArrayFactory.createRandomArray(arrayLength, 99);
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit einer
	 * Standard-Laenge von 12 Elementen. Fuer den Bereich, aus dem
	 * die Zufallszahlen stammen sollen, werden die Standardgrenzen
	 * [-99, 99] verwendet.
	 * <p>
	 * Dies entspricht genau:<br><code>
	 * int[] array = RandomisedArrayFactory.createRandomArray(12);
	 * </code><p>
	 * 
	 * @see #createRandomArray(int, int, int)
	 * @see #createRandomArray(int)
	 */
	public static int[] createRandomArray () {
		return RandomisedArrayFactory.createRandomArray(12);
	}
	
	
	
	public static String toString (final int[] array) {
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i]);
			buffer.append(' ');
		}
		return buffer.deleteCharAt(buffer.length() - 1);
	}
	
}
