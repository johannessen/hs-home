/* $Id: Loesung63.java,v 1.2 2007-12-12 02:58:01 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 6-3.
 * <p>
 * Ein Stapel, implementiert durch Vererbung mit einer <code>LinkedList</code>.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt6/">Aufgabenblatt 6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung63 extends java.util.LinkedList {
	
	
	
	/**
	 * Legt einen String oben auf den Stapel drauf.
	 * 
	 * @param value der aufzulegende String
	 */
	public void push (String value) {
		super.addFirst(value);
	}
	
	
	
	/**
	 * Nimmt den obersten String vom Stapel herunter.
	 * 
	 * @return der abgenommene String
	 */
	public String pop () {
		String value = (String)super.getFirst();
		super.removeFirst();
		return value;
	}
	
}
