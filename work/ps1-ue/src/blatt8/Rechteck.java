/* 
 * $Id: Rechteck.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Source: <http://www.christophweser.de/java/aufgaben/WS2005/Rechteck.java>.
 * Javadoc and package statement added by Arne Johannessen on 2005-12-13.
 */

package de.thaw.ps1.blatt8;



/**
 * Ein Rechteck mit Kantenl&auml;ngen, die auf ganze Zahlen beschr&auml;nkt
 * sind.
 * <p>Entgegen des entsprechenden Hinweises im &Uuml;bungsblatt 8 ist diese
 * Klasse <em>nicht</em> die Musterl&ouml;sung der Aufgabe 4 <q>Rechteck
 * revolutions</q> des &Uuml;bungsblatts 7. Bei dieser Implementierung der
 * Aufgabe <q>Rechteck revolutions</q> treten nennenswerte Rundungsfehler auf,
 * da intern ausschlie&szlig;lich Ganzzahlen (<code>int</code>) verwendet
 * werden. Im Folgenden ist das tats&auml;chliche (falsche) Verhalten der Klasse
 * (im Gegensatz zum beabsichtigten Verhalten lt. Aufgabenstellung Blatt 7)
 * dokumentiert.
 * <p>Ein Beispiel f&uuml;r das Fehlverhalten der Klasse lautet wie folgt:
 * <pre><code>Rechteck rechteck = new Rechteck(3, 3);
 *System.out.println(rechteck.getVolumen());  // gibt <q>9</q> aus
 *rechteck.setLaenge(2);
 *System.out.println(rechteck.getVolumen());  // gibt <q>8</q> aus
 * </code></pre>
 * Laut Aufgabenstellung in Blatt 7 sollte das Volumen des Rechtecks bei diesem
 * Codebeispiel nicht ver&auml;ndert werden (es sollte hier also zweimal
 * <q>9</q> ausgegeben werden).
 * <p>Die Klasse {@link de.thaw.ps1.blatt7.Aufgabe7_4 Aufgabe7_4} sollte
 * urspr&uuml;nglich die Aufgabe korrekt l&ouml;sen, ist aber (in der Version 1)
 * ebenfalls nicht korrekt.
 * @version 1
 * @author Dokumentation: Arne Johannessen; Java-Code: Christoph Weser, Udo Gentner
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt8.pdf">8. &Uuml;bungsblatt PS1</a>
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt7.pdf">7. &Uuml;bungsblatt PS1</a>
 * @see de.thaw.ps1.blatt7.Rechteck
 * @see de.thaw.ps1.blatt7.Aufgabe7_4
 * @see de.thaw.ps1.blatt5.Rechteck
 * 
 * @deprecated Die Verwendung dieser Klasse wird missbilligt, das sie nicht
 * korrekt funktioniert und und in Zukunft durch eine korrekte Version ersetzt
 * werden k&ouml;nnte.
 */
public class Rechteck {
	
	
	/** Die L&auml;nge des Rechtecks. */
    private int laenge;
	
	
	/** Die Breite des Rechtecks. */
	private int breite;
	
	
	/**
	 * Die <strong>Fl&auml;che</strong> des Rechtecks (im Gegensatz zum
	 * <em>Volumen</em>).
	 */
	private int volumen;
	
	
	/**
	 * Erstellt ein neues Rechteck. Die Instanzvariablen {@link #laenge laenge}
	 * und {@link #breite breite} werden entsprechend der Parameter gesetzt.
	 * @param laenge die L&auml;nge des neuen Rechtecks
	 * @param breite die Breite des neuen Rechtecks
	 */
	public Rechteck (int laenge, int breite) {
		this.laenge = laenge;
		this.breite = breite;
	}
	
	
	/**
	 * Zugriffsmethode. Liefert die L&auml;nge des Rechtecks.
	 * @return den Wert der Instantvariable {@link #laenge laenge}
	 */
	public int getLaenge () {
		return laenge;
	}
	
	
	/**
	 * Zugriffsmethode. Liefert die Breite des Rechtecks.
	 * @return den Wert der Instantvariable {@link #breite breite}
	 */
	public int getBreite () {
		return breite;
	}
	
	
	/**
	 * Stellt fest, ob dieses Rechteck quadratisch ist. Dazu werden die
	 * Instanzvariablen {@link #laenge laenge} und {@link #breite breite} auf
	 * Gleichheit verglichen.
	 * @return <code>true</code>, falls es sich um ein Quadrat handelt
	 */
	public boolean isSquare () {
		if (laenge == breite) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Berechnet die <strong>Fl&auml;che</strong> des Rechtecks. Zur Berechnung
	 * werden die Instanzvariablen {@link #laenge laenge} und {@link #breite
	 * breite} benutzt (und <em>nicht</em> die Instanzvariable {@link #volumen
	 * volumen}). Das Ergebnis wird nicht gespeichert, sondern <em>nur</em>
	 * zur&uuml;ckgegeben.
	 * @return das Produkt der L&auml;nge und der Breite des Rechtecks
	 */
	public int getVolumen () {
		return laenge * breite;
	}
	
	
	/**
	 * Berechnet und speichert die bisherige Fl&auml;che des Rechtecks, setzt
	 * die L&auml;nge auf den &uuml;bergebenen Wert und ver&auml;ndert die
	 * Breite.
	 * <p><strong>Hinweis:</strong> Diese Methode sollte laut Aufgabenstellung
	 * in Blatt 7 eigentlich anders funktionieren, als sie es tats&auml;chlich
	 * tut; sie ist insofern nicht korrekt. Das <em>hier</em> beschriebene
	 * Verhalten ist allerdings die <em>tats&auml;chliche</em>
	 * Funktionalit&auml;t dieser Methode so, wie sie derzeit implementiert ist.
	 * <p>Diese Methode hat die folgenden drei Auswirkungen auf
	 * Instanzvariablen: <ol>
	 * <li>{@link #volumen volumen} wird auf die <em>bisherige</em> (also
	 * innerhalb dieser Methode noch nicht ver&auml;nderte) Fl&auml;che des
	 * Rechtecks gesetzt.
	 * <li>{@link #laenge laenge} wird auf die in <code>neueLaenge</code>
	 * &uuml;bergebene L&auml;nge gesetzt.
	 * <li>{@link #breite breite} wird auf das Ergebnis der ganzzahligen
	 * Division <code>(int)({@link #volumen volumen} / {@link #laenge
	 * laenge})</code> gesetzt. Ist {@link #volumen volumen} ohne Rest durch
	 * {@link #laenge laenge} teilbar, funktioniert diese Methode korrekt;
	 * andernfalls treten Rundungsfehler auf.
	 * </ol><p>
	 * @param neueLaenge die L&auml;nge, die das Rechteck fortan haben soll
	 * 
	 * @deprecated Die Verwendung dieser Methode wird missbilligt, das sie einen
	 * Rundungsfehler hat und und in Zukunft durch eine korrekte Version ersetzt
	 * werden k&ouml;nnte.
	 */
	public void setLaenge (int neueLaenge) {
		volumen = getVolumen();
		laenge = neueLaenge;
		breite = (volumen / laenge);
	}
	
	
	/**
	 * Berechnet und speichert die bisherige Fl&auml;che des Rechtecks, setzt
	 * die Breiten auf den &uuml;bergebenen Wert und ver&auml;ndert die
	 * Breite.
	 * <p><strong>Hinweis:</strong> Diese Methode sollte laut Aufgabenstellung
	 * in Blatt 7 eigentlich anders funktionieren, als sie es tats&auml;chlich
	 * tut; sie ist insofern nicht korrekt. Das <em>hier</em> beschriebene
	 * Verhalten ist allerdings die <em>tats&auml;chliche</em>
	 * Funktionalit&auml;t dieser Methode so, wie sie derzeit implementiert ist.
	 * <p>Diese Methode hat die folgenden drei Auswirkungen auf
	 * Instanzvariablen: <ol>
	 * <li>{@link #volumen volumen} wird auf die <em>bisherige</em> (also
	 * innerhalb dieser Methode noch nicht ver&auml;nderte) Fl&auml;che des
	 * Rechtecks gesetzt.
	 * <li>{@link #breite breite} wird auf die in <code>neueBreite</code>
	 * &uuml;bergebene Breite gesetzt.
	 * <li>{@link #laenge laenge} wird auf das Ergebnis der ganzzahligen
	 * Division <code>(int)({@link #volumen volumen} / {@link #breite
	 * breite})</code> gesetzt. Ist {@link #volumen volumen} ohne Rest durch
	 * {@link #breite breite} teilbar, funktioniert diese Methode korrekt;
	 * andernfalls treten Rundungsfehler auf.
	 * </ol><p>
	 * @param neueBreite die Breite, die das Rechteck fortan haben soll
	 * 
	 * @deprecated Die Verwendung dieser Methode wird missbilligt, das sie einen
	 * Rundungsfehler hat und und in Zukunft durch eine korrekte Version ersetzt
	 * werden k&ouml;nnte.
	 */
	public void setBreite (int neueBreite) {
		volumen = getVolumen();
		breite = neueBreite;
		laenge = (volumen / breite);
	}
	
}
