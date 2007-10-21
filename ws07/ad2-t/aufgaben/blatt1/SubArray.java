/* $Id: SubArray.java,v 1.2 2007-10-21 01:08:04 arne Exp $
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
	private int subArrayStart = 0;
	
	
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
		this.subArrayLength = array.length;
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
	 * Es werden keinerlei Ueberpruefungen auf sinnvolle Werte
	 * durchgefuehrt.
	 * @param subArrayStart auf den Gesamt-Array bezogener Array-Index,
	 * an dem der Sub-Array beginnen soll
	 */
	public void setStart (int subArrayStart) {
		this.subArrayStart = subArrayStart;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert den Index des Beginns des Sub-Arrays.
	 * <p>Der zurueckgegebene Index ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * @return auf den Gesamt-Array bezogener Array-Index, an dem der
	 * Sub-Array beginnt
	 */
	public int getStart () {
		if (this.subArrayLength < 0) {
			return 0;
		}
		return Math.min(this.array.length - 1, Math.max(0, this.subArrayStart));
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt die Laenge des Sub-Arrays. Es werden
	 * keinerlei Ueberpruefungen auf sinnvolle Werte durchgefuehrt.
	 * @param subArrayLength Laenge des Sub-Arrays
	 */
	public void setLength (int subArrayLength) {
		this.subArrayLength = subArrayLength;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert die Laenge des Sub-Arrays.
	 * <p>Die zurueckgegebene Laenge ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * @return Laenge des Sub-Arrays
	 */
	public int getLength () {
		if (this.subArrayLength < 0) {
			return this.array.length;
		}
		int length = this.subArrayLength;
		if (this.subArrayStart < 0) {
			length += this.subArrayStart;
		}
		return Math.min(this.array.length - this.subArrayStart, Math.max(0, length));
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
	 * Errechnet das linke Rand-Maximum dieses Sub-Arrays. Dies ist
	 * genau das Sub-Array, dessen erstes Element mit dem ersten
	 * Element dieses Sub-Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * @return das linke Rand-Maximum als neues SubArray-Objekt
	 */
	public SubArray findLeftEdgeMaximum () {
		
		// Schleife vorbereiten
		SubArray result = new SubArray(this.array, 0, 0);
		int maximumSum = 0;
		int beginIndex = this.getStart();
		int endIndex = beginIndex + this.getLength() - 1;
		
		// Sub-Array von links nach rechts durchlaufen und Maximum bestimmen
		for (int sum = 0, index = beginIndex; index <= endIndex; index++) {
			sum += this.array[index];
			if (sum > maximumSum) {
				maximumSum = sum;
				result.setStart(beginIndex);
				result.setLength(index - beginIndex + 1);
			}
		}
		
		return result;
	}
	
	
	
	/**
	 * Errechnet das rechte Rand-Maximum dieses Sub-Arrays. Dies ist
	 * genau das Sub-Array, dessen letzte Element mit dem letzten
	 * Element dieses Sub-Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * @return das rechte Rand-Maximum als neues SubArray-Objekt
	 */
	public SubArray findRightEdgeMaximum () {
		
		// Schleife vorbereiten
		SubArray result = new SubArray(this.array, 0, 0);
		int maximumSum = 0;
		int endIndex = this.getStart();
		int beginIndex = endIndex + this.getLength() - 1;
		
		// Sub-Array von rechts nach links durchlaufen und Maximum bestimmen
		for (int sum = 0, index = beginIndex; index >= endIndex; index--) {
			sum += this.array[index];
			if (sum > maximumSum) {
				maximumSum = sum;
				result.setStart(index);
				result.setLength(beginIndex - index + 1);
			}
		}
		return result;
	}
	
	
	
	/**
	 * Addiert alle Elemente des Sub-Arrays.
	 * @return die Summe aller Elemente des Sub-Arrays
	 */
	public int sum () {
		int sum = 0;
		int endIndex = this.getStart() + this.getLength();
		for (int index = this.getStart(); index <= endIndex; index++) {
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
		
		// triviale Faelle behandeln
		if (this.array == null) {
			return "null";
		}
		if (this.array.length == 0) {
			return "";
		}
		
		// Schleife vorbereiten
		StringBuffer buffer = new StringBuffer(this.array.length * 4);
		int beginIndex = this.getStart();
		int endIndex = this.getStart() + this.getLength();
		boolean isSubArrayEmpty = (beginIndex == endIndex);
		
		// Gesamt-Array durchlaufen und Werte mitsamt Sub-Array-Grenzen ausgeben
		int index = 0;
		while (true) {
			if (index == beginIndex && ! isSubArrayEmpty) {
				buffer.append('[');
			}
			buffer.append(this.array[index]);
			index++;
			if (index == endIndex && ! isSubArrayEmpty) {
				buffer.append(']');
			}
			if (index >= this.array.length) {
				break;
			}
			buffer.append(' ');
		}
		if (isSubArrayEmpty) {
			buffer.append(" []");
		}
		
		// Wert des Ausgabepuffers zurueckgeben
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
			return false;  // Objekt einer anderen Klasse
		}
		SubArray other = (SubArray)obj;
		if (this.array != other.array) {
			return false;  // Gesamt-Array nicht identisch
		}
		if (this.getLength() != other.getLength()) {
			return false;  // Laengen der Sub-Arrays verschieden
		}
		// Sub-Arrays muessen entweder gleichen Bereich haben oder beide leer sein
		return (this.getLength() == 0 || this.getStart() == other.getStart());
	}
	
}
