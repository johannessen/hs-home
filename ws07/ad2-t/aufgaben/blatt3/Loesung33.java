/* $Id: Loesung33.java,v 1.2 2007-10-28 22:50:20 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Loesungsvorschlag fuer Aufgabe 3-3.
 * 
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt3/">Aufgabenblatt 3</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.2 $
 */
public class Loesung33 {
	
	
	
	/**
	 * Berechnet rekursiv, wie oft auf einer Party zur Begruessung
	 * angestossen wurde. Jeder neu ankommende Gast stoesst mit jedem
	 * bereits anwesenden an. Die Gastgeber stossen nicht mit an.
	 * 
	 * @param gaeste die Anzahl der anwesenden Gaeste
	 * @return wie oft angestossen wurde
	 */
	public static int hilfUwe (int gaeste) {
		if (gaeste <= 1) {
			return 0;  // mit sich selbst stoesst man nicht an
		}
		return (gaeste - 1) + hilfUwe(gaeste - 1);
	}
	
	
	
	/** Treiber fuer Aufruf von der Kommandozeilenschnittstelle. */
	public static void main (String[] args) {
		int gaeste = Integer.parseInt(args[0]);
		System.out.print("Bei "+gaeste+" Gaesten wurde insgesamt ");
		System.out.println(hilfUwe(gaeste)+" Mal angestossen.");
	}
	
}
