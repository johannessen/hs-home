/* $Id: StringListFactory.java,v 1.2 2008-05-21 01:32:15 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;


/**
 * Diese Klasse bietet zwei Methoden zum Erzeugen von
 * <code>LinkedList</code>s mit vorgefertigten Strings an:
 * <ul>
 * <li><code>getColourList()</code> liefert eine Liste mit neun Farbnamen
 * <li><code>createStringList(int)</code> erzeugt eine Liste mit der
 * gewuenschten Anzahl zufaelliger Strings
 * </ul><p>
 * Zusaetzlich besteht mit <code>pickRandomString(LinkedList)</code> die
 * Moeglichkeit, aus einer gegebenen Liste ein zufaelliges Element
 * auswaehlen zu lassen.
 * <p>
 * <em>Hinweis.</em> Gegenueber der vorhergehenden Revision 1.1 wurde diese
 * Klasse stark umgebaut: Alle Objektmethoden wurden zu Klassenmethoden,
 * einige wenig gebrauchte Methoden wurden entfernt und andere auf
 * <code>private</code> gesetzt. Revision 1.1 dieser Klasse ist beim Autor
 * auf Anfrage erhaeltlich.
 * 
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class StringListFactory {
	
	
	// an array containing some common colour names as Strings
	private static final String[] COLOURS = new String[] {"red", "green", "blue", "cyan", "magenta", "yellow", "black", "grey", "white"};
	
	
	// the random number generator that we use
	private static final Random random = new Random();
	
	
	// this class is not to be instantiated
	private StringListFactory () {
	}
	
	
	/**
	 * Liefert eine Liste mit neun Farbnamen. Bei jedem Aufruf werden
	 * die gleichen Farbnamen in der gleichen Reihenfolge verwendet, um
	 * eine neue Liste zu erzeugen. Die verwendeten Namen lauten:
	 * <ul>
	 * <li>red
	 * <li>green
	 * <li>blue
	 * <li>cyan
	 * <li>magenta
	 * <li>yellow
	 * <li>black
	 * <li>grey
	 * <li>white
	 * </ul>
	 * (in dieser Reihenfolge)
	 * 
	 * @return eine <code>LinkedList</code> mit neun Farbnamen, jeder
	 *  ein internierter <code>String</code>
	 * @see String#intern()
	 */
//	@SuppressWarnings("unchecked")  // uncomment this line in Java 1.5
	public static LinkedList getColourList () {
		return new LinkedList(Arrays.asList(COLOURS));
	}
	
	
	
	/**
	 * Erzeugt eine Liste der angegebenen Laenge mit zufaelligen
	 * Strings. Die Strings bestehen alle aus der gleichen Anzahl
	 * Kleinbuchstaben; die Anzahl wird automatisch so kurz wie
	 * moeglich gewaehlt, jedoch in Abhaengigkeit der Listenlaenge so
	 * gesteuert, dass vor allem kurze Listen nur wenige Duplikate
	 * haben.
	 * <p>
	 * Alle Strings sind interniert, so dass ein Vergleich sowohl mit
	 * <code>==</code> als auch mit <code>equals(Object)</code>
	 * moeglich ist.
	 * 
	 * @param size die gewuenschte Laenge der Liste
	 * @return eine <code>LinkedList</code> mit zufaelligen,
	 *  internierten Strings
	 * @throws IllegalArgumentException falls <code>size &lt; 0</code>
	 * @see String#intern()
	 */
	public static LinkedList createStringList (final int size) {
		return createStringList(size, (int)(Math.log(size) / Math.log(26)) + 2);  // adding 2 gives us a small extra margin to avoid duplicates
	}
	
	
	
	// creates a LinkedList with a specified number of Strings, each with a specified length
//	@SuppressWarnings("unchecked")  // uncomment this line in Java 1.5
	private static LinkedList createStringList (final int size, final int stringLength) {
		if (size < 0) {
			throw new IllegalArgumentException("Listengroesse kann nicht negativ sein");
		}
		final LinkedList list = new LinkedList();
		for (int i = 0; i < size; i++) {
			list.add(randomString(stringLength));
		}
		return list;
	}
	
	
	
	// create an interned random string with specified length
	private static String randomString (final int length) {
		final StringBuffer buffer = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			buffer.append('a' + (char)random.nextInt(26));
		}
		return buffer.toString().intern();
	}
	
	
	
	/**
	 * Waehlt aus einer Liste ein zufaelliges Element aus.
	 * 
	 * @param list Die Liste, aus der ein Element zu waehlen ist.
	 * @return ein zufaellig ausgewaehltes Element aus
	 *  <code>list</code>
	 * @throws NullPointerException falls <code>list == null</code>
	 */
	public static String pickRandomString (final List list) {
		return (String)list.get(random.nextInt(list.size()));
	}
	
}
