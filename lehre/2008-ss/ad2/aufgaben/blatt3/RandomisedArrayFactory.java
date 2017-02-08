/* $Id: RandomisedArrayFactory.java,v 1.5 2008/06/04 19:05:53 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Diese Klasse bietet einige Klassenmethoden an, die anhand bestimmter
 * Kriterien Arrays mit Zahlen erstellen. <ul>
 * <li>
 * Die Methoden <code>createRandomArray(...)</code> erstellen einen mit
 * zufaelligen Elementen gefuellten Array des Typs <code>int[]</code>. Das
 * kann nutzlich sein, wenn man ohne grossen Aufwand schnell eine grosse
 * Anzahl von Testwerten braucht.
 * <li>
 * Die Methode <code>parseStringArray(String[])</code> erstellt einen Array
 * des Typs <code>int[]</code>, der mit genau den Zahlen gefuellt ist, die
 * sich in den einzelnen String-Elementen des Arrays befinden. Das kann
 * nuetzlich sein beim Versuch, auf der Kommandozeile einen Array an das
 * Programm zu uebergeben.
 * </ul>
 * 
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.5 $
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
		return buffer.deleteCharAt(buffer.length() - 1).toString();
	}
	
	
	
	/**
	 * Erstellt einen <code>Integer</code>-Array, der mit den
	 * umgewandelten Zahlen aus dem uebergebenen
	 * <code>String</code>-Array gefuellt ist. Beide Arrays haben die
	 * gleiche Laenge.
	 * <p>
	 * <b>Beispiel:</b><p> Die folgende Klasse erstellt beim
	 * Programmstart einen Array <code>array</code>, der mit genau den
	 * auf der Kommandozeile uebergebenen Werten gefuellt ist:<p>
	 * <pre><code>
	 * class Beispiel {
	 *     public static void main (String[] args) {
	 *         int[] array = SubArray.parseStringArray(args);
	 *         // mehr code hierhin
	 *     }
	 * }
	 * </code></pre>
	 * <p>Nun kann das Programm z. B. mit
	 * <code>java Beispiel 12 -56 47 8 -87</code>
	 * gestartet werden, um einen Array mit einer Laenge von fuenf
	 * Elementen mit genau diesen Zahlenwerten zu erstellen.
	 * 
	 * @param args ein Array, dessen Elemente allesamt Ganzzahlen
	 *  beschreiben
	 * @throws NumberFormatException falls eines der Array-Elemente
	 *  sich nicht in eine Ganzzahl wandeln laesst
	 * @throws NullPointerException falls <code>args == null</code>
	 */
	public static int[] parseStringArray (final String[] args) {
		final int[] array = new int[args.length];
		for (int index = 0; index < args.length; index++) {
			array[index] = Integer.parseInt(args[index]);
		}
		return array;
	}
	
}
