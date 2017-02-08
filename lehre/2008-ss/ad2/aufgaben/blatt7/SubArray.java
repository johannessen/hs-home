/* $Id: SubArray.java,v 1.3 2008/06/18 00:41:36 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Datentyp, der einen bestimmten (definierbaren) Teil eines Arrays
 * darstellt. Dieser Teil wird Sub-Array genannt.
 * <p>
 * Ein Sub-Array ist eindeutig definiert durch drei Angaben:<ol>
 * <li>der Gesamt-Array, von dem der Sub-Array einen Teil darstellt,
 * <li>die Stelle im Gesamt-Array, an welcher der Sub-Array beginnt, und
 * <li>die Anzahl der Elemente (also die Laenge) des Sub-Arrays.</ol>
 * <p>
 * In dieser Klasse wird diese Eindeutigkeit umgesetzt durch interne
 * Speicherung<ol>
 * <li>einer Referenz zum Gesamt-Array,
 * <li>des Indizes desjenigen Elements des Gesamt-Arrays, mit dem der
 * Sub-Array beginnt, und
 * <li>der Laenge des Sub-Arrays.</ol>
 * <p>
 * Alternativ zu Start-Stelle und Laenge des Sub-Arrays waeren natuerlich
 * auch Start-Stelle und End-Stelle ausreichend, um den Sub-Array eindeutig
 * zu definieren. Warum Start und Laenge besser sind als Beginn und Ende,
 * beschrieb E. W. Dijkstra im Jahre 1982 recht anschaulich.
 * [vgl. <a href="http://www.cs.utexas.edu/users/EWD/ewd08xx/EWD831.PDF">EWD831</a>]
 * Diese Klasse stellt der Bequemlichkeit halber Methoden zur Verfuegung,
 * die direkt Beginn- und End-Stelle zurueckliefern, so dass eine
 * entsprechende Umrechnung nur beim Setzen der End-Stelle notwendig ist.
 * Diese Umrechnung ist weiter unten erklaert.
 * <p>
 * Bemerkenswert ist, dass die Summe der Elemente des Sub-Arrays diesen
 * nicht definiert. Vielmehr ist die Summe eine <em>Eigenschaft</em> des
 * Sub-Arrays. Immer, wenn man Start und Laenge des Sub-Arrays kennt, ist
 * das Berechnen der Summe trivial. Aus einer Summe allein kann man aber
 * selbst bei bekanntem Gesamt-Array oft nicht eindeutig auf das
 * zugehoerige Sub-Array schliessen.
 * <p>
 * Um das Arbeiten mit der Summe zu erleichtern, verfuegt jede
 * Instanz dieser Klasse ueber einen Cache, der die aktuelle Summe
 * zwischenspeichert, wann immer sie bekannt ist. Dieser Cache bleibt
 * bis zur naechsten Aenderung der Definition des Sub-Arrays gueltig.
 * Die Objektmethoden <code>setSum(int)</code> und <code>getSum()</code>
 * erlauben den direkten Zugruff auf den Cache. Falls beim Aufruf von
 * <code>getSum()</code> der Cache gerade veraltet ist, wird die Summe
 * automatisch mit einer Schleife neu berechnet und der Cache aktualisiert.
 * <p>
 * <br>
 * Beim Arbeiten mit dieser Klassse wird man in der Regel zunaechst mit
 * einem Konstruktor und dem <code>new</code>-Operator eine neue Instanz,
 * also ein neues Objekt vom Typ SubArray, erstellen. Auf diesem Objekt
 * wird man dann je nach Bedarf Objektmethoden aufrufen (das sind die
 * <em>ohne</em> <code>static</code>),
 * z. B. <code>variablenname.print();</code>.
 * <p>
 * Eine interessante Anwendung dieser Klasse besteht darin, den
 * Rueckgabe-Typ einer Methode als <code>SubArray</code> zu definieren.
 * Dadurch wird es moeglich, statt etwa einer einzigen Zahl mehrere
 * Zahlen zuruckzugeben -- naemlich genau diejenigen Zahlen, durch
 * die ein Sub-Array definiert wird.
 * <p>
 * Abgesehen von den Objektmethoden stehen auch einige Klassenmethoden zum
 * Aufruf zur Verfuegung:
 * Die Methoden <code>findLeftEdgeMaximum(int[],int,int)</code> und
 * <code>findRightEdgeMaximum(int[],int,int)</code> ermitteln innerhalb des
 * angegebenen Array-Teils das linke bzw. rechte Randmaximum und geben das
 * Ergebnis als Objekt vom Typ <code>SubArray</code> zurueck. Das kann
 * nuetzlich sein bei Divide-and-Conquer--Algorithmen mit Sub-Arrays.
 * <p>
 * <br>
 * <b>Beispiel.</b> Die folgende Klasse ermittelt die rechte Haelfte
 * eines an der Kommandozeile eingegebenen Arrays und zeigt ihre Lage
 * im Gesamt-Array an:<p>
 * <pre><code>
 * public class RechteHaelfteFinder {
 *     
 *     public SubArray findeRechteHaelfte (int[] array) {
 *          // rechte Haelfte ermitteln:
 *         int mitte = array.length / 2;
 *         int startRechteHaelfte = mitte;
 *         int laengeRechteHaelfte = array.length - mitte;
 *          // SubArray-Objekt konstruieren und zurueckgeben:
 *         SubArray ergebnis = new SubArray(array);
 *         ergebnis.setStart(startRechteHaelfte);
 *         ergebnis.setLength(laengeRechteHaelfte);
 *         return ergebnis;
 *     }
 *     
 *     public static void main (String[] args) {
 *         int[] eingabeArray = SubArray.parseStringArray(args);
 *          // weil die Methode findeRechteHaelfte(int[]) eine
 *          // Objektmethode ist (nicht static!), muessen wir
 *          // erst ein neues Objekt konstruieren:
 *         RechteHaelfteFinder finder = new RechteHaelfteFinder();
 *         SubArray rechteHaelfte;
 *         rechteHaelfte = finder.findeRechteHaelfte(eingabeArray);
 *         rechteHaelfte.print();
 *     }
 * }
 * </code></pre>
 * <p>Beim Aufruf von der Kommandozeile mit<br><kbd>
 * java RechteHaelfteFinder 1 2 3 4 5 6
 * </kbd><br>wird folgendes Ergebnis ausgegeben:<br><samp>
 * 1 2 3 [4 5 6]
 * </samp>
 * <p>
 * <br>
 * <b>Hinweis.</b> Soll ein Sub-Array <code>subArray</code> anhand der
 * Indizes des ersten und letzten Elements (<code>beginIndex</code>
 * respektive <code>endIndex</code>) definiert werden, koennen folgende
 * Objektmethoden-Aufrufe dazu verwendet werden:<p>
 * <pre><code>
 * subArray.setStart(beginIndex);
 * subArray.setLength(endIndex - beginIndex + 1);
 * </code></pre>
 * <p>(Die <code>+ 1</code> sind dabei notwendig, denn offensichtlich hat
 * ein Sub-Array, dessen erstes und letztes Element identisch sind
 * (<code>endIndex == beginIndex</code>), immer die Laenge eins. Bei einer
 * einfachen Differenz <code>endIndex - beginIndex</code> wuerde sich dann
 * jedoch faelschlich null fuer die Laenge ergeben. Folglich muss diese
 * Differenz um eins erhoeht werden.)
 * <p>
 * <br>
 * <b>Warnung.</b> Diese Klasse arbeitet unmittelbar auf dem durch
 * <code>array</code> referenzierten Array. Das Verhalten dieser Klasse
 * bei externer Veraenderung dieses Arrays ist nicht definiert. Um daraus
 * resultierende Probleme zu umgehen, kann der Array beim Instanziieren
 * dieser Klasse dupliziert werden:<p>
 * <pre><code>
 * int[] array = new int[] {1, 2, 3};
 * SubArray subArray = new SubArray(array.clone());
 * </code></pre>
 * <p>
 * <br>
 * <b>Anmerkung.</b> Die ueberschreibbaren Methoden dieser Klasse werden
 * nicht von anderen Methoden oder Konstruktoren dieser Klasse aufgerufen.
 * Das bedeutet, dass diese Klasse durch Vererbung erweitert werden kann.
 * <br>
 * 
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.3 $
 */
public class SubArray implements Comparable {
	
	
	/** Der Gesamt-Array. */
	private final int[] array;
	
	
	/** Index des Beginns des Sub-Arrays im Gesamt-Array. */
	private int subArrayStart;
	
	
	/** Laenge des Sub-Arrays im Gesamt-Array. */
	private int subArrayLength;
	
	
	/** Cache der Summe der Elemente des Sub-Arrays. */
	private Integer subArraySum = null;
	
	
	
	
	
	/**
	 * Konstruktor; erstellt ein neues SubArray-Objekt fuer den
	 * ganzen uebergebenen Array. Der Sub-Array umfasst also den
	 * kompletten Gesamt-Array. Der Aufruf dieses Konstruktors ist
	 * damit genau gleichbedeutend mit  dem folgenden
	 * Code-Abschnitt:<p>
	 * <pre><code>
	 * SubArray subArray = new SubArray(array);
	 * subArray.setStart(0);
	 * subArray.setLength(array.length);
	 * </code></pre>
	 * <p>
	 * Der Gesamt-Array kann in dieser Instanz nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit 
	 * einem anderen Array zu erstellen.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = {1, 2, 3};
	 * SubArray subArray = new SubArray(array);
	 * System.out.println(subArray);  // [1 2 3]
	 * </code></pre>
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see #setStart(int)
	 * @see #setLength(int)
	 */
	public SubArray (final int[] array) {
		this.array = array;
		this.subArrayStart = 0;
		this.subArrayLength = array.length;
	}
	
	
	
	/**
	 * Konstruktor; erstellt ein neues SubArray-Objekt mit bestimmten
	 * Grenzen. Die Grenzen werden zusammen mit dem Gesamt-Array, auf
	 * den sie sich beziehen, als Parameter uebergeben. Der Aufruf
	 * dieses Konstruktors ist also genau gleichbedeutend mit dem
	 * folgenden Code-Abschnitt:<p>
	 * <pre><code>
	 * SubArray subArray = new SubArray(array);
	 * subArray.setStart(subArrayStart);
	 * subArray.setLength(subArrayLength);
	 * </code></pre>
	 * <p>
	 * Der Gesamt-Array kann in dieser Instanz nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit 
	 * einem anderen Array zu erstellen.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = {1, 2, 3};
	 * SubArray subArray = new SubArray(array, 1, 1);
	 * System.out.println(subArray);  // 1 [2] 3
	 * </code></pre>
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @param subArrayStart der Index des Beginns des Sub-Arrays
	 * @param subArrayLength die Laenge des Sub-Arrays
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see #setStart(int)
	 * @see #setLength(int)
	 */
	public SubArray (final int[] array, final int subArrayStart, final int subArrayLength) {
		if (array == null) {
			throw new NullPointerException();
		}
		this.array = array;
		this.subArrayStart = subArrayStart;
		this.subArrayLength = subArrayLength;
	}
	
	
	
	/**
	 * Konstruktor; erstellt eine Kopie der uebergebenen
	 * SubArray-Instanz mit veraenderten Sub-Array--Grenzen. Der
	 * Aufruf dieses Konstruktors ist also genau gleichbedeutend
	 * mit dem folgenden Code-Abschnitt:<p>
	 * <pre><code>
	 * SubArray subArray = new SubArray(array.getFullArray());
	 * subArray.setStart(beginIndex);
	 * subArray.setLength(endIndex - BeginIndex + 1);
	 * </code></pre>
	 * <p>
	 * Im Gegensatz zu den meisten anderen Konstruktoren und Methoden
	 * werden hier Indizes auf den Gesamt-Array als Parameter
	 * gefordert. Die Indizes zaehlen dabei einschliesslich, so dass im
	 * Falle <code>beginIndex == endIndex</code> der neue Sub-Array die
	 * Laenge 1 hat.
	 * <p>
	 * Der Gesamt-Array kann in dieser Instanz nicht veraendert
	 * werden; noetigenfalls ist eine neue Instanz dieser Klasse mit 
	 * einem anderen Array zu erstellen.
	 * <p>
	 * <b>Beispiel.</b> In diesem Code-Schnipsel wird
	 * <code>array2</code> als exakte Kopie von <code>array1</code>
	 * erstellt:<p>
	 * <pre><code>
	 * SubArray original = ...;  // beliebiger Sub-Array
	 * int beginIndex = original.getStart();
	 * int endIndex = original.getStart() + original.getLength() - 1;
	 * SubArray kopie = new SubArray(original, beginIndex, endIndex);
	 * System.out.println(kopie.equals(original));  // true
	 * </code></pre><p>
	 * 
	 * @param array der Gesamt-Array fuer die neue Instanz
	 * @param beginIndex der Index des ersten Elements im neuen
	 *  Sub-Array
	 * @param endIndex der Index des letzten Elements im neuen
	 *  Sub-Array
	 * @throws NullPointerException falls <code>array == null</code>
	 * @see #setStart(int)
	 * @see #setLength(int)
	 */
	public SubArray (final SubArray array, final int beginIndex, final int endIndex) {
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
	int[] getFullArray () {
		return this.array;
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt den Index des Beginns des Sub-Arrays.
	 * Es werden keinerlei Ueberpruefungen auf sinnvolle Werte
	 * durchgefuehrt.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, 2, 3};
	 * SubArray subArray = new SubArray(array, 1, 1);
	 * System.out.println(subArray);  // 1 [2] 3
	 * subArray.setStart(0);
	 * System.out.println(subArray);  // [1] 2 3
	 * </code></pre>
	 * <p>
	 * Beim Aufruf dieser Methode wird der interne Cache der
	 * Sub-Array--Summe als veraltet markiert. Ein anschliessender
	 * Aufruf von <code>getSum()</code> fuehrt zu einer Neuberechnung
	 * der Sub-Array--Summe.
	 * 
	 * @param subArrayStart auf den Gesamt-Array bezogener Array-Index,
	 *  an dem der Sub-Array beginnen soll
	 * @see #getSum()
	 */
	public void setStart (final int subArrayStart) {
		this.subArrayStart = subArrayStart;
		this.subArraySum = null;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert den Index des Beginns des Sub-Arrays.
	 * <p>
	 * Der zurueckgegebene Index ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * Wenn<br>
	 * <code>System.out.println(subArray)</code>
	 * die Ausgabe "<code>1 2 3 [4 5] 6</code>" erzeugt, hat
	 * <code>subArray.getStart()</code> genau den Wert
	 * <code>3</code>.
	 * 
	 * @return auf den Gesamt-Array bezogener Array-Index, an dem der
	 *  Sub-Array beginnt
	 * @see #getLength()
	 */
	public int getStart () {
		return this.getStart1();
	}
	
	private int getStart1 () {
		if (this.subArrayLength < 0) {
			return 0;
		}
		return Math.min(this.array.length - 1, Math.max(0, this.subArrayStart));
	}
	
	
	
	/**
	 * Zugriffsmethode; setzt die Laenge des Sub-Arrays. Es werden
	 * keinerlei Ueberpruefungen auf sinnvolle Werte durchgefuehrt.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, 2, 3};
	 * SubArray subArray = new SubArray(array, 1, 1);
	 * System.out.println(subArray);  // 1 [2] 3
	 * subArray.setLength(2);
	 * System.out.println(subArray);  // 1 [2 3]
	 * </code></pre>
	 * <p>
	 * Beim Aufruf dieser Methode wird der interne Cache der
	 * Sub-Array--Summe als veraltet markiert. Ein anschliessender
	 * Aufruf von <code>getSum()</code> fuehrt zu einer Neuberechnung
	 * der Sub-Array--Summe.
	 * 
	 * @param subArrayLength Laenge des Sub-Arrays
	 * @see #getSum()
	 */
	public void setLength (final int subArrayLength) {
		this.subArrayLength = subArrayLength;
		this.subArraySum = null;
	}
	
	
	
	/**
	 * Zugriffsmethode; liefert die Laenge des Sub-Arrays.
	 * <p>
	 * Die zurueckgegebene Laenge ist so angepasst, dass sich
	 * immer eine gueltige Definition des Sub-Arrays ergibt.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * Wenn<br>
	 * <code>System.out.println(subArray)</code>
	 * die Ausgabe "<code>1 2 3 [4 5] 6</code>" erzeugt, hat
	 * <code>subArray.getLength()</code> genau den Wert
	 * <code>2</code>.
	 * 
	 * @return Laenge des Sub-Arrays
	 * @see #getStart()
	 */
	public int getLength () {
		return this.getLength1();
	}
	
	private int getLength1 () {
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
	 * Setzt den Cache der Summe des Sub-Arrays. Es werden
	 * keinerlei Ueberpruefungen auf sinnvolle Werte durchgefuehrt.
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
	public void setSum (final int subArraySum) {
		this.subArraySum = new Integer(subArraySum);
	}
	
	
	
	/**
	 * Liefert die Summe aller Elemente des Sub-Arrays in
	 * seinen derzeitigen Grenzen.
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
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, 2, 3};
	 * SubArray subArray = new SubArray(array, 1, 2);
	 * System.out.println(subArray);  // 1 [2 3]
	 * System.out.println(subArray.getSum());  // 5
	 * </code></pre>
	 * 
	 * @return die Sub-Array--Summe
	 * @see #setSum(int)
	 */
	public int getSum () {
		return this.getSum1();
	}
	
	private int getSum1 () {
		if (this.subArraySum == null) {
			int sum = 0;
			final int endIndex = this.getEndIndex1();
			for (int index = this.getBeginIndex1(); index <= endIndex; index++) {
				sum += this.array[index];
			}
			this.subArraySum = new Integer(sum);
		}
		return this.subArraySum.intValue();
	}
	
	
	
	
	
	/**
	 * Liefert den auf den Gesamt-Array bezogenen Index des
	 * ersten Elements des Sub-Arrays.
	 * <p><code>
	 * subArray.getBeginIndex()
	 * </code><br>entspricht genau:<br><code>
	 * subArray.getStart()
	 * </code>
	 * <p>
	 * Diese Methode ist nur ein Alias fuer die Methode
	 * <code>getStart()</code>. Sie stellt das Gegenstueck zu
	 * <code>getEndIndex()</code> dar.
	 * 
	 * @return Index des ersten Elements im Sub-Array
	 * @see #getStart()
	 */
	public int getBeginIndex () {
		return this.getStart1();
	}
	
	private int getBeginIndex1 () {
		return this.getStart1();
	}
	
	
	
	/**
	 * Liefert den auf den Gesamt-Array bezogenen Index des
	 * letzten Elements des Sub-Arrays.
	 * <p><code>
	 * subArray.getEndIndex()
	 * </code><br>entspricht genau:<br><code>
	 * subArray.getStart() + subArray.getLength() - 1
	 * </code>
	 * 
	 * @return Index des letzten Elements im Sub-Array
	 */
	public int getEndIndex () {
		return this.getEndIndex1();
	}
	
	private int getEndIndex1 () {
		return this.getStart1() + this.getLength1() - 1;
	}
	
	
	
	/**
	 * Errechnet das linke Randmaximum dieses Sub-Arrays. Dies ist
	 * genau das Sub-Array, dessen erstes Element mit dem ersten
	 * Element dieses Sub-Arrays identisch ist und dessen Summe der
	 * Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, -4, 2};
	 * SubArray subArray = new SubArray(array);
	 * System.out.println(subArray);  // [1 -4 2]
	 * subArray.findLeftEdgeMaximum();
	 * System.out.println(subArray);  // [1] -4 2
	 * </code></pre>
	 * 
	 * @see #setSum(int)
	 */
	public void findLeftEdgeMaximum () {
		
		// Sub-Array--Grenzen normalisieren
		final int beginIndex = this.getBeginIndex1();
		final int endIndex = this.getEndIndex1();
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
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, -4, 2};
	 * SubArray subArray = new SubArray(array);
	 * System.out.println(subArray);  // [1 -4 2]
	 * subArray.findRightEdgeMaximum();
	 * System.out.println(subArray);  // 1 -4 [2]
	 * </code></pre>
	 * 
	 * @see #setSum(int)
	 */
	public void findRightEdgeMaximum () {
		
		// Sub-Array--Grenzen normalisieren
		final int beginIndex = this.getBeginIndex1();
		final int endIndex = this.getEndIndex1();
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
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, -4, 2};
	 * SubArray subArray = SubArray.findLeftEdgeMaximum(array, 0, 2);
	 * System.out.println(subArray);  // [1] -4 2
	 * </code></pre>
	 * 
	 * @param array der Array, in dem sich der Array-Teil befindet,
	 *  von dem das Randmaximum zu ermitteln ist
	 * @param beginIndex der Index des ersten Elements des Array-Teils
	 * @param endIndex der Index des letzten Elements des Array-Teils
	 * @return das linke Randmaximum als neues SubArray-Objekt
	 * @see #findLeftEdgeMaximum()
	 * @see #setSum(int)
	 */
	public static SubArray findLeftEdgeMaximum (final int[] array, final int beginIndex, final int endIndex) {
		final SubArray subArray = new SubArray(array, beginIndex, endIndex - beginIndex + 1);
		subArray.findLeftEdgeMaximum();
		return subArray;
	}
	
	
	
	/**
	 * Errechnet das rechte Randmaximum eines Arrays-Teils. Das ist
	 * genau das Sub-Array, dessen letztes Element in
	 * <code>array</code> den Index <code>endIndex</code> hat und
	 * dessen Summe der Elemente so gross wie moeglich ist.
	 * <p>
	 * Das zurueckgegebene Objekt hat einen erfrischten Cache der
	 * Sub-Array--Summe.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, -4, 2};
	 * SubArray subArray = SubArray.findRightEdgeMaximum(array, 0, 2);
	 * System.out.println(subArray);  // 1 -4 [2]
	 * </code></pre>
	 * 
	 * @param array der Array, in dem sich der Array-Teil befindet,
	 *  von dem das Randmaximum zu ermitteln ist
	 * @param beginIndex der Index des ersten Elements des Array-Teils
	 * @param endIndex der Index des letzten Elements des Array-Teils
	 * @return das rechte Randmaximum als neues SubArray-Objekt
	 * @see #findRightEdgeMaximum()
	 * @see #setSum(int)
	 */
	public static SubArray findRightEdgeMaximum (final int[] array, final int beginIndex, final int endIndex) {
		final SubArray subArray = new SubArray(array, beginIndex, endIndex - beginIndex + 1);
		subArray.findRightEdgeMaximum();
		return subArray;
	}
	
	
	
	/**
	 * Gibt eine String-Repraesentation dieses Objekts auf dem
	 * Standard-Ausgabe-Stream aus.
	 * <p>
	 * <b>Beispiel:</b><p>
	 * <pre><code>
	 * int[] array = new int[] {1, 2, 3};
	 * SubArray subArray = new SubArray(array);
	 * subArray.print();  // [1 2 3]
	 * </code></pre>
	 * <p>
	 * Im vorstehenden Beispiel koennte man anstelle von
	 * <code>subArray.print();</code> genau so gut auch
	 * <code>System.out.println(subArray);</code> schreiben. Diese
	 * beiden Schreibweisen sind genau gleichbedeutend.
	 * 
	 * @see #toString()
	 * @see java.io.PrintStream#println(Object)
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
		final StringBuffer buffer = new StringBuffer(this.array.length * 4);  // 4 passt haeufig
		final int beginIndex = this.getStart1();
		final int endIndex = this.getStart1() + this.getLength1();
		final boolean isSubArrayEmpty = (beginIndex == endIndex);
		
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
	 *  derzeitigen Grenzen entspricht.
	 */
	public int[] toArray () {
		int[] array = new int[this.getLength1()];
		System.arraycopy(this.array, this.getStart1(), array, 0, array.length);
		return array;
	}
	
	
	
	/**
	 * Erstellt eine exakte Bitkopie dieses SubArray-Objekts,
	 * einschliesslich des Inhalts des Gesamt-Arrays.
	 * 
	 * @return ein Klon dieses Objekts vom Typ <code>SubArray</code>
	 * @throws CloneNotSupportedException falls die
	 *  <i>runtime class</i> dieses Objekts nicht
	 *  <code>Cloneable</code> implementiert
	 * @see Object#clone()
	 * @see Cloneable
	 */
	protected Object clone () throws CloneNotSupportedException {
//		SubArray clone = (SubArray)super.clone();  // :BUG: causes CloneNotSupported to trip because this class doesn't implement Cloneable
		SubArray clone = new SubArray(new int[this.array.length], this.subArrayStart, this.subArrayLength);
		System.arraycopy(this.array, 0, clone.array, 0, this.array.length);
		return clone;
	}
	
	
	
	/**
	 * Vergleicht dieses Objekt mit dem angegebenen Objekt. Ist dieses
	 * Objekt kleiner, gleich, oder groesser als das angegebene Objekt,
	 * wird entsprechend eine negative Zahl, null, oder eine positive
	 * Zahl zurueckgegeben.
	 * <p>
	 * Hinweis: Diese Klasse hat eine natuerliche Ordnung, die nicht
	 * mit <i>equals</i> uebereinstimmt.
	 * 
	 * @param o das zu vergleichende Objekt
	 * @return die Differenz aus der Summe dieses Sub-Arrays und der
	 *  Summe des Sub-Arrays <code>o</code>
	 * @throws NullPointerException falls <code>o == null</code>
	 * @throws ClassCastException falls <code>o</code> kein Sub-Array
	 *  ist
	 * @throws java.util.ConcurrentModificationException falls die
	 *  beiden Sub-Arrays ungleiche Gesamt-Arrays haben
	 */
	public int compareTo (final Object o) {
		final SubArray other = (SubArray)o;
		if (! java.util.Arrays.equals(this.array, other.array)) {
			throw new java.util.ConcurrentModificationException("comparing subarrays of unequal arrays");
		}
		return this.getSum1() - other.getSum1();
	}
	
	
	
	/**
	 * Prueft, ob ein anderes Objekt gleich diesem ist. Das ist der
	 * Fall genau dann, wenn die beiden Gesamt-Arrays gleich sind
	 * und die Definitionen der Sub-Arrays uebereinstimmen.
	 * 
	 * @param obj das auf Gleichheit zu pruefende Objekt
	 * @return <code>true</code>, falls <code>obj</code> gleich diesem
	 *  Objekt ist
	 */
	public boolean equals (final Object obj) {
		if (this == obj) {
			return true;  // Objekte identisch
		}
		if (! (obj instanceof SubArray)) {
			return false;  // Objekt eines anderen Typs (oder null)
		}
		final SubArray other = (SubArray)obj;
		if (! java.util.Arrays.equals(this.array, other.array)) {
			return false;  // Gesamt-Array nicht gleich
		}
		final int length = this.getLength1();
		if (length != other.getLength1()) {
			return false;  // Laengen der Sub-Arrays verschieden
		}
		// Sub-Arrays muessen entweder gleichen Bereich haben oder beide leer sein
		return (length == 0 || this.getStart1() == other.getStart1());
	}
	
	
	
	/**
	 * Liefert den Hash-Code fuer diesen Sub-Array.
	 * 
	 * @return den Hash-Code-Wert dieses Sub-Arrays
	 * @see Object#hashCode()
	 */
	public int hashCode () {
		// bitte nicht versuchen, dies zu verstehen!
		int hashCode = 1;
		for (int index = 0; index < this.array.length; index++) {
			hashCode = 0x1f * hashCode + this.array[index];
		}
		hashCode *= 0x3c1;
		final int length = this.getLength1();
		hashCode += 0x1f * length;
		if (length != 0) {
			hashCode += this.getStart1();
		}
		return hashCode;
	}
	
}
