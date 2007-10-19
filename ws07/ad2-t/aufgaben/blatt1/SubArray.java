/* $Id: SubArray.java,v 1.1 2007-10-19 20:45:13 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Datentyp, der einen bestimmten (definierbaren) Teil eines Arrays
 * darstellt.
 * <p>Ferner sind auch einige Hilfs-Klassenmethoden enthalten, die bei
 * der Implementierung von Loesungen des Maximum-Sub-Array--Problems
 * nuetzlich sein koennten.
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 */
public class SubArray {
	
	
	/** Der Gesamt-Array. */
	private int[] array = null;
	
	
	/** Index des Beginns des Sub-Arrays im Gesamt-Array. */
	private int subArrayStart = -1;
	
	
	/** Laenge des Sub-Arrays im Gesamt-Array. */
	private int subArrayLength = 0;
	
	
	/**
	 * Standard-Begrenzung der Werte in einem mit zufaelligen Werten
	 * erstellten Array. Ein Wert von <code>10</code> liefert
	 * Zufallszahlen aus dem Intervall [-10, 10].
	 * @see #createRandomArray(int,int)
	 */
	protected static final int DEFAULT_LIMITS = 99;
	
	
	/**
	 * Standard-Laenge eines mit zufaelligen Werten erstellten Arrays.
	 * @see #createRandomArray(int,int)
	 */
	protected static final int DEFAULT_ARRAY_LENGTH = 12;
	
	
	
	
	
	/**
	 * Konstruktor; erstellt eine neue Instanz dieser Klasse mit dem
	 * uebergebenen Array als Gesamt-Array.
	 * <p>Der Gesamt-Array kann in dieser Klasse nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit
	 * einem anderen Array zu erstellen.
	 * @param array der Gesamt-Array fuer die neue Instanz
	 */
	public SubArray (int[] array) {
		this.array = array;
	}
	
	
	
	/**
	 * Konstruktor; erstellt eine neue Instanz dieser Klasse mit dem
	 * uebergebenen Array als Gesamt-Array.
	 * <p>Der Gesamt-Array kann in dieser Klasse nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit
	 * einem anderen Array zu erstellen.
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @param subArrayStart der Index des Beginns des Sub-Arrays
	 * @param subArrayLength die Laenge des Sub-Arrays
	 */
	public SubArray (int[] array, int subArrayStart, int subArrayLength) {
		this.array = array;
		this.subArrayStart = subArrayStart;
		this.subArrayLength = subArrayLength;
	}
	
	
	
	
	
	/**
	 * Zugriffsmethode; liefert den bei der Instanziierung gesetzten
	 * Gesamt-Array.
	 * @return den Gesamt-Array
	 */
	public int[] getFullArray () {
		return this.array;
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt den Index des Beginns des Sub-Arrays.
	 * <p>Wuerde diese Aenderung zu einer ungueltigen Definition des
	 * Sub-Arrays fuehren (z. B. dadurch, dass der Sub-Array ausserhalb
	 * des Gesamt-Arrays zu liegen kaeme), wird
	 * <code>subArrayLength</code> so angepasst, dass sich wieder eine
	 * gueltige Definition ergibt.
	 * @param subArrayStart auf den Gesamt-Array bezogener Array-Index,
	 * an dem der Sub-Array beginnen soll
	 */
	public void setStart (int subArrayStart) {
		this.subArrayStart = subArrayStart;
		this.validateSubArray();
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert den Index des Beginns des Sub-Arrays.
	 * @return auf den Gesamt-Array bezogener Array-Index, an dem der
	 * Sub-Array beginnt
	 */
	public int getStart () {
		return this.subArrayStart;
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt die Laenge des Sub-Arrays.
	 * <p>Wuerde diese Aenderung zu einer ungueltigen Definition des
	 * Sub-Arrays fuehren (z. B. durch eine negative Laenge), wird
	 * die Laenge so angepasst, dass sich wieder eine gueltige
	 * Definition ergibt.
	 * @param subArrayLength Laenge des Sub-Arrays
	 */
	public void setLength (int subArrayLength) {
		this.subArrayLength = subArrayLength;
		this.validateSubArray();
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert die Laenge des Sub-Arrays.
	 * @return Laenge des Sub-Arrays
	 */
	public int getLength () {
		return this.subArrayLength;
	}
	
	
	
	/**
	 * Passt, falls noetig, <code>subArrayLength</code> so an, dass der
	 * Sub-Array gueltig definiert ist.
	 */
	private void validateSubArray () {
		if (this.subArrayStart + this.subArrayLength > this.array.length) {
			this.subArrayLength = this.array.length - this.subArrayStart;
		}
		if (this.subArrayStart < 0 || this.subArrayLength < 0) {
			this.subArrayLength = 0;
		}
	}
	
	
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Der Bereich, aus dem die Zufallszahlen
	 * stammen sollen, wird uebergeben. Die Bereichsgrenzen selbst
	 * koennen ebenfalls als Zufallszahlen auftreten.
	 * @param arrayLength die Laenge des Arrays
	 * @param lowerLimit die kleinstmoegliche Zufallszahl
	 * @param upperLimit die groesstmoegliche Zufallszahl
	 */
	public static int[] createRandomArray (int arrayLength, int lowerLimit, int upperLimit) {
		int[] array = new int[arrayLength];
		double range = upperLimit - lowerLimit + 1;
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
	 * @param arrayLength die Laenge des Arrays
	 * @param limit der maximale Abstand, den die Zufallszahlen von der
	 * Zahl null (<code>0</code>) haben sollen
	 */
	public static int[] createRandomArray (int arrayLength, int limits) {
		limits = Math.abs(limits);
		return SubArray.createRandomArray(arrayLength, -limits, limits);
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Fuer den Bereich, aus dem die Zufallszahlen
	 * stammen sollen, werden Standardgrenzen verwendet.
	 * @param arrayLength die Laenge des Arrays
	 * @see #DEFAULT_LIMITS
	 */
	public static int[] createRandomArray (int arrayLength) {
		return SubArray.createRandomArray(arrayLength, DEFAULT_LIMITS);
	}
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * Standard-Laenge. Fuer den Bereich, aus dem die Zufallszahlen
	 * stammen sollen, werden Standardgrenzen verwendet.
	 * @see #DEFAULT_LIMITS
	 * @see #DEFAULT_ARRAY_LENGTH
	 */
	public static int[] createRandomArray () {
		return SubArray.createRandomArray(DEFAULT_ARRAY_LENGTH);
	}
	
	
	
	/**
	 * Erstellt einen <code>Integer</code>-Array, der mit den
	 * umgewandelten Zahlen aus dem uebergebenen
	 * <code>String</code>-Array gefuellt ist. Beide Arrays haben die
	 * gleiche Laenge.
	 * @param args ein Array, dessen Elemente allesamt Ganzzahlen
	 * beschreiben
	 * @throws NumberFormatException falls eines der Array-Elemente
	 * sich nicht in eine Ganzzahl wandeln laesst
	 */
	public static int[] parseStringArray (String[] args) {
		int[] array = new int[args.length];
		for (int index = 0; index < args.length; index++) {
			array[index] = Integer.parseInt(args[index]);
		}
		return array;
	}
	
	
	
	/**
	 * Addiert alle Elemente des Sub-Arrays.
	 * @return die Summe aller Elemente des Sub-Arrays
	 */
	public int sum () {
		int sum = 0;
		for (int index = this.subArrayStart; index < this.subArrayStart + this.subArrayLength; index++) {
			sum += this.array[index];
		}
		return sum;
	}
	
	
	
	/**
	 * Gibt eine String-Repraesentation dieses Objekts auf dem
	 * Standard-Ausgabe-Stream aus.
	 */
	public void print () {
		System.out.println(this);
	}
	
	
	
	/**
	 * Liefert eine String-Repraesentation dieses Objekts. Sie zeigt
	 * den Gesamt-Array mit markierten Grenzen des Sub-Arrays,
	 * getrennt durch Leerzeichen.
	 * @return dieses Objekt als String
	 */
	public String toString () {
		if (this.array == null) {
			return "null";
		}
		if (this.array.length == 0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer(this.array.length * 4);
		int index = 0;
		while (true) {
			if (index == this.subArrayStart && this.subArrayLength > 0) {
				buffer.append('[');
			}
			buffer.append(this.array[index]);
			index++;
			if (index == this.subArrayStart + this.subArrayLength && this.subArrayLength > 0) {
				buffer.append(']');
			}
			if (index >= this.array.length) {
				break;
			}
			buffer.append(' ');
		}
		if (this.subArrayLength == 0) {
			buffer.append(" []");
		}
		return buffer.toString();
	}
	
	
	
	/**
	 * Prueft, ob ein anderes Objekt gleich diesem ist. Das ist der
	 * Fall genau dann, wenn die beiden Gesamt-Arrays identisch sind
	 * und die Definitionen der Sub-Arrays uebereinstimmen.
	 * @param obj das auf Gleichheit zu pruefende Objekt
	 * @return <code>true</code>, falls <code>obj</code> gleich diesem
	 * Objekt ist
	 */
	public boolean equals (Object obj) {
		if (! (obj instanceof SubArray)) {
			return false;
		}
		SubArray other = (SubArray)obj;
		return this.array == other.array
				&& (this.subArrayStart == other.subArrayStart
				|| this.subArrayLength == 0)
				&& this.subArrayLength == other.subArrayLength;
	}
	
}
