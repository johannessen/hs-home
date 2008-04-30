/* $Id: Loesung23.java,v 1.1 2008-04-30 01:13:45 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 2-3.
 * <p>
 * Ein Stapel, implementiert durch Vererbung mit einer <code>LinkedList</code>.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ss08/ad2-t/aufgaben/blatt2/">Aufgabenblatt 2</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.1 $
 */
public class Loesung23 extends java.util.LinkedList {
	
	
	
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
