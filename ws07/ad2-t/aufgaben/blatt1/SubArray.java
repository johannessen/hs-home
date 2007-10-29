/* $Id: SubArray.java,v 1.7 2007-10-29 02:23:25 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Datentyp, der einen bestimmten (definierbaren) Teil eines Arrays
 * darstellt.
 * <p>
 * Ferner sind auch einige Hilfs-Klassenmethoden enthalten, die bei
 * der Implementierung von Loesungen des Maximum-Sub-Array--Problems
 * nuetzlich sein koennten.
 * 
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.7 $
 */
public class SubArray implements Cloneable {
	
	
	/** Der Gesamt-Array. */
	private int[] array = null;
	
	
	/** Index des Beginns des Sub-Arrays im Gesamt-Array. */
	private int subArrayStart = 0;
	
	
	/** Laenge des Sub-Arrays im Gesamt-Array. */
	private int subArrayLength = 0;
	
	
	/** Cache der Summe der Elemente des Sub-Arrays. */
	private Integer subArraySum = null;
	
	
	/**
	 * Standard-Begrenzung der Werte in einem mit zufaelligen Werten
	 * erstellten Array. Ein Wert von <code>10</code> liefert
	 * Zufallszahlen aus dem Intervall [-10, 10].
	 * 
	 * @see #createRandomArray(int,int)
	 */
	protected static final int DEFAULT_LIMITS = 99;
	
	
	/**
	 * Standard-Laenge eines mit zufaelligen Werten erstellten Arrays.
	 * 
	 * @see #createRandomArray(int,int)
	 */
	protected static final int DEFAULT_ARRAY_LENGTH = 12;
	
	
	
	
	
	/**
	 * Konstruktor; erstellt eine neue Instanz dieser Klasse mit dem
	 * uebergebenen Array als Gesamt-Array.
	 * <p>
	 * Der Gesamt-Array kann in dieser Klasse nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit
	 * einem anderen Array zu erstellen.
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public SubArray (int[] array) {
		this.array = array;
		this.subArrayStart = 0;
		this.subArrayLength = array.length;
	}
	
	
	
	/**
	 * Konstruktor; erstellt eine neue Instanz dieser Klasse mit dem
	 * uebergebenen Array als Gesamt-Array.
	 * <p>
	 * Der Gesamt-Array kann in dieser Klasse nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit
	 * einem anderen Array zu erstellen.
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @param subArrayStart der Index des Beginns des Sub-Arrays
	 * @param subArrayLength die Laenge des Sub-Arrays
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public SubArray (int[] array, int subArrayStart, int subArrayLength) {
		if (array == null) {
			throw new NullPointerException();
		}
		this.array = array;
		this.subArrayStart = subArrayStart;
		this.subArrayLength = subArrayLength;
	}
	
	
	
	/**
	 * Konstruktor; erstellt eine Kopie der uebergebenen
	 * SubArray-Instanz mit veraenderten Sub-Array--Grenzen.
	 * <p>
	 * Im Gegensatz zu allen anderen Konstruktoren und Methoden
	 * werden hier Indizes auf den Gesamt-Array als Parameter
	 * gefordert. Die Indizes zaehlen dabei einschliesslich, so
	 * dass im Falle <code>beginIndex == endIndex</code> der neue
	 * Sub-Array die Laenge 1 hat.
	 * <p>
	 * Beispiel. In diesem Code-Schnipsel wird <code>array2</code>
	 * als exakte Kopie von <code>array1</code> erstellt:
	 * <pre><code>
	 * SubArray array1 = ...;  // beliebiger Sub-Array
	 * int endIndex = array1.getStart() + array1.getLength() - 1;
	 * SubArray array2 = new SubArray(array1, array1.getStart(), endIndex);
	 * array2.equals(array1);  // true
	 * </code></pre><p>
	 * Der Gesamt-Array kann in dieser Klasse nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit
	 * einem anderen Array zu erstellen.
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @param beginIndex der Index des ersten Elements im neuen
	 * Sub-Array
	 * @param endIndex der Index des letzten Elements im neuen
	 * Sub-Array
	 * @throws NullObjectException falls <code>array == null</code>
	 */
	public SubArray (SubArray array, int beginIndex, int endIndex) {
		this.array = array.array;
		this.subArrayStart = beginIndex;
		this.subArrayLength = endIndex - beginIndex + 1;
	}
	
	
	
	
	
	/**
	 * Zugriffsmethode; liefert den bei der Instanziierung gesetzten
	 * Gesamt-Array.
	 * 
	 * @return den Gesamt-Array
	 */
	public int[] getFullArray () {
		return this.array;
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt den Index des Beginns des Sub-Arrays.
	 * Es werden keinerlei Ueberpruefungen auf sinnvolle Werte
	 * durchgefuehrt.
	 * <p>
	 * Beim Aufruf dieser Methode wird der interne Cache der
	 * Sub-Array--Summe als veraltet markiert. Ein anschliessender
	 * Aufruf von <code>getSum()</code> fuehrt zu einer Neuberechnung
	 * der Sub-Array--Summe.
	 * 
	 * @param subArrayStart auf den Gesamt-Array bezogener Array-Index,
	 * an dem der Sub-Array beginnen soll
	 * @see #getSum()
	 */
	public void setStart (int subArrayStart) {
		this.subArrayStart = subArrayStart;
		this.subArraySum = null;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert den Index des Beginns des Sub-Arrays.
	 * <p>
	 * Der zurueckgegebene Index ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * 
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
	 * <p>
	 * Beim Aufruf dieser Methode wird der interne Cache der
	 * Sub-Array--Summe als veraltet markiert. Ein anschliessender
	 * Aufruf von <code>getSum()</code> fuehrt zu einer Neuberechnung
	 * der Sub-Array--Summe.
	 * 
	 * @see #getSum()
	 * @param subArrayLength Laenge des Sub-Arrays
	 */
	public void setLength (int subArrayLength) {
		this.subArrayLength = subArrayLength;
		this.subArraySum = null;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert die Laenge des Sub-Arrays.
	 * <p>
	 * Die zurueckgegebene Laenge ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * 
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
	 * Zugriffsmethode; setzt den Cache der Summe des Sub-Arrays.
	 * Es werden keinerlei Ueberpruefungen auf sinnvolle Werte
	 * durchgefuehrt.
	 * <p>
	 * Diese Methode kann bei bereits bekannter Summe aufgerufen
	 * werden, um den internen Cache der Summe aufzufrischen. Beim
	 * naechsten Aufruf von <code>getSum()</code> wird direkt der
	 * dieser Methode uebergebene Wert zurueckgeliefert, eine
	 * Neuberechnung der Sub-Array--Summe findet dann nicht statt.
	 * 
	 * @param subArraySum Summe des Sub-Arrays
	 * @see #getSum()
	 */
	public void setSum (int subArraySum) {
		this.subArraySum = new Integer(subArraySum);
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert die Sub-Array--Summe.
	 * <p>
	 * Um Rechenzeit zu sparen, wird nach Moeglichkeit der
	 * interne Cache der Summe verwendet. Ist der Cache jedoch
	 * nicht mehr frisch, muss die Summe neu berechnet werden. Dies
	 * geschieht mittels einer Iteration mit O(n)-Komplexitaet.
	 * <p>
	 * Diese Methoden frischen den Cache auf:<ul>
	 * <li><code>setSum(int)</code>
	 * <li><code>getSum()</code>
	 * <li><code>findLeftEdgeMaximum()</code>
	 * <li><code>findRightEdgeMaximum()</code>
	 * </ul><p>
	 * Diese Methoden markieren den Cache als veraltet:<ul>
	 * <li><code>setStart(int)</code>
	 * <li><code>setLength(int)</code>
	 * </ul>
	 * 
	 * @return die Summe aller Elemente des Sub-Arrays
	 * @see #setSum(int)
	 */
	public int getSum () {
		if (this.subArraySum == null) {
			int sum = 0;
			int endIndex = this.getEndIndex();
			for (int index = this.getBeginIndex(); index <= endIndex; index++) {
				sum += this.array[index];
			}
			this.subArraySum = new Integer(sum);
		}
		return this.subArraySum.intValue();
	}
	
	
	
	
	
	/**
	 * Liefert den auf den Gesamt-Array bezogenen Index des
	 * ersten Elements des Sub-Arrays.
	 * <p>
	 * Dies entspricht genau:<br><code>
	 * this.getStart()
	 * </code><p>
	 * Diese Methode ist nur ein Alias fuer die Methode
	 * <code>getStart()</code>. Sie stellt das Gegenstueck zu
	 * <code>getEndIndex()</code> dar.
	 * 
	 * @return Index des ersten Elements im Sub-Array
	 * @see #getStart()
	 */
	public int getBeginIndex () {
		return this.getStart();
	}
	
	
	
	/**
	 * Liefert den auf den Gesamt-Array bezogenen Index des
	 * letzten Elements des Sub-Arrays.
	 * <p>
	 * Dies entspricht genau:<br><code>
	 * this.getStart() + this.getLength() - 1
	 * </code>
	 * 
	 * @return Index des letzten Elements im Sub-Array
	 */
	public int getEndIndex () {
		return this.getStart() + this.getLength() - 1;
	}
	
	
	
	/**
	 * Errechnet das linke Randmaximum dieses Sub-Arrays. Dies ist
	 * genau das Sub-Array, dessen erstes Element mit dem ersten
	 * Element dieses Sub-Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * 
	 * @see #setSum(int)
	 */
	public void findLeftEdgeMaximum () {
		
		// Sub-Array--Grenzen normalisieren
		int beginIndex = this.getBeginIndex();
		int endIndex = this.getEndIndex();
		this.subArrayStart = beginIndex;
		this.subArrayLength = 0;
		
		// Sub-Array von links nach rechts durchlaufen und Maximum bestimmen
		int maximumSum = 0;
		int sum = 0;
		for (int index = beginIndex; index <= endIndex; index++) {
			sum += this.array[index];
			if (sum > maximumSum) {
				maximumSum = sum;
				this.subArrayLength = index - beginIndex + 1;
			}
		}
		
		// Summen-Cache auffrischen
		this.subArraySum = new Integer(maximumSum);
	}
	
	
	
	/**
	 * Errechnet das rechte Randmaximum dieses Sub-Arrays. Dies ist
	 * genau das Sub-Array, dessen letzte Element mit dem letzten
	 * Element dieses Sub-Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * 
	 * @see #setSum(int)
	 */
	public void findRightEdgeMaximum () {
		
		// Sub-Array--Grenzen normalisieren
		int beginIndex = this.getBeginIndex();
		int endIndex = this.getEndIndex();
		this.subArrayStart = endIndex + 1;
		this.subArrayLength = 0;
		
		// Sub-Array von links nach rechts durchlaufen und Maximum bestimmen
		int maximumSum = 0;
		int sum = 0;
		for (int index = endIndex; index >= beginIndex; index--) {
			sum += this.array[index];
			if (sum > maximumSum) {
				maximumSum = sum;
				this.subArrayStart = index;
			}
		}
		this.subArrayLength = endIndex - this.subArrayStart + 1;
		
		// Summen-Cache auffrischen
		this.subArraySum = new Integer(maximumSum);
	}
	
	
	
	/**
	 * Errechnet das linke Randmaximum eines Arrays-Teils. Das ist
	 * genau das Sub-Array, dessen erstes Element in <code>array</code>
	 * den Index <code>beginIndex</code> hat und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * 
	 * @param array der Array, in dem sich der Array-Teil befindet,
	 * von dem das Randmaximum zu ermitteln ist
	 * @param beginIndex der Index des ersten Elements des Array-Teils
	 * @param endIndex der Index des letzten Elements des Array-Teils
	 * @return das linke Randmaximum als neues SubArray-Objekt
	 * @see #setSum(int)
	 */
	public static SubArray findLeftEdgeMaximum (int[] array, int beginIndex, int endIndex) {
		SubArray subArray = new SubArray(array, beginIndex, endIndex - beginIndex + 1);
		subArray.findLeftEdgeMaximum();
		return subArray;
	}
	
	
	
	/**
	 * Errechnet das rechte Randmaximum eines Arrays-Teils. Das ist
	 * genau das Sub-Array, dessen letztes Element in <code>array</code>
	 * den Index <code>endIndex</code> hat und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * 
	 * @param array der Array, in dem sich der Array-Teil befindet,
	 * von dem das Randmaximum zu ermitteln ist
	 * @param beginIndex der Index des ersten Elements des Array-Teils
	 * @param endIndex der Index des letzten Elements des Array-Teils
	 * @return das rechte Randmaximum als neues SubArray-Objekt
	 * @see #setSum(int)
	 */
	public static SubArray findRightEdgeMaximum (int[] array, int beginIndex, int endIndex) {
		SubArray subArray = new SubArray(array, beginIndex, endIndex - beginIndex + 1);
		subArray.findRightEdgeMaximum();
		return subArray;
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
	 * 
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
		StringBuffer buffer = new StringBuffer(this.array.length * 4);  // 4 passt haeufig
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
	 * Kopiert den Sub-Array in einen neu angelegten Integer-Array.
	 * 
	 * @return ein Array, dessen Inhalt diesem Sub-Array in seinen
	 * derzeitigen Grenzen entspricht.
	 */
	public int[] toArray () {
		int[] array = new int[this.getLength()];
		System.arraycopy(this.array, this.getStart(), array, 0, array.length);
		return array;
	}
	
	
	
	/**
	 * Erstellt eine exakte Bitkopie dieses SubArray-Objekts. Dabei
	 * wird jedoch der Gesamt-Array nicht geklont, sondern nur als
	 * Referenz kopiert.
	 * 
	 * @return einen Klon dieses Objekts
	 * @see Object#clone()
	 * @see Cloneable
	 */
	public Object clone () {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException exception) {
			throw (Error)new InternalError().initCause(exception);
		}
	}
	
	
	
	/**
	 * Prueft, ob ein anderes Objekt gleich diesem ist. Das ist der
	 * Fall genau dann, wenn die beiden Gesamt-Arrays gleich sind
	 * und die Definitionen der Sub-Arrays uebereinstimmen.
	 * 
	 * @param obj das auf Gleichheit zu pruefende Objekt
	 * @return <code>true</code>, falls <code>obj</code> gleich diesem
	 * Objekt ist
	 * @throws NullObjectException falls <code>obj == null</code>
	 */
	public boolean equals (Object obj) {
		if (! (obj instanceof SubArray)) {
			return false;  // Objekt eines anderen Typs
		}
		SubArray other = (SubArray)obj;
		if (! java.util.Arrays.equals(this.array, other.array)) {
			return false;  // Gesamt-Array nicht gleich
		}
		if (this.getLength() != other.getLength()) {
			return false;  // Laengen der Sub-Arrays verschieden
		}
		// Sub-Arrays muessen entweder gleichen Bereich haben oder beide leer sein
		return (this.getLength() == 0 || this.getStart() == other.getStart());
	}
	
	
	
	/**
	 * Liefert den Hash-Code fuer diesen Sub-Array.
	 * 
	 * @return den Hash-Code-Wert dieses Sub-Arrays
	 * @see Object#hashCode()
	 * @see java.util.Arrays#hashCode(int[])
	 */
	public int hashCode () {
		// bitte nicht versuchen, dies zu verstehen!
		int hashCode = 1;
		for (int index = 0; index < this.array.length; index++) {
			hashCode = 0x1f * hashCode + this.array[index];
		}
		hashCode *= 0x3c1;
		hashCode += 0x1f * this.getLength();
		if (this.getLength() != 0) {
			hashCode += this.getStart();
		}
		return hashCode;
	}
	
	
	
	
	
	/**
	 * Erstellt einen mit Zufallszahlen gefuellten Array mit
	 * definierter Laenge. Der Bereich, aus dem die Zufallszahlen
	 * stammen sollen, wird uebergeben. Die Bereichsgrenzen selbst
	 * koennen ebenfalls als Zufallszahlen auftreten.
	 * 
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
	 * 
	 * @param arrayLength die Laenge des Arrays
	 * @param limits der maximale Abstand, den die Zufallszahlen von der
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
	 * 
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
	 * 
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
	 * 
	 * @param args ein Array, dessen Elemente allesamt Ganzzahlen
	 * beschreiben
	 * @throws NumberFormatException falls eines der Array-Elemente
	 * sich nicht in eine Ganzzahl wandeln laesst
	 * @throws NullObjectException falls <code>args == null</code>
	 */
	public static int[] parseStringArray (String[] args) {
		int[] array = new int[args.length];
		for (int index = 0; index < args.length; index++) {
			array[index] = Integer.parseInt(args[index]);
		}
		return array;
	}
	
}
