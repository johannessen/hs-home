/* 
 * $Id: Aufgabe10_1.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2006-01-17.
 */

package de.thaw.ps1.blatt10;




/**
 * Eine komplexe Zahl mitsamt Real- und Imagin&auml;rteil.
 * @see <a href="http://www.christophweser.de/java/aufgaben/WS2005/Blatt10.pdf">10. &Uuml;bungsblatt PS1</a>
 * @author Arne Johannessen
 */
public class KomplexeZahl {
	
	
	
	
	// Blatt 10, Aufgabe 1: Ein "Kontainer" f&uuml;r komplexe Zahlen.
	
	
	/** Realteil */
	private double real;
	
	
	/** Imagin&auml;rteil */
	private double imaginary;
	
	
	/**
	 * Erstellt eine komplexe Zahl anhand von Realteil und Imagin&auml;rteil.
	 * @param real Realteil
	 * @param imaginary Imagin&auml;rteil
	 */
	public KomplexeZahl (double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	
	/**
	 * Zugriffsmethode; setzt den Realteil in {@link #real real}.
	 * @param real Der neue Realteil
	 */
	public void setReal (double real) {
		this.real = real;
	}
	
	
	/**
	 * Zugriffsmethode; setzt den Imagin&auml;rteil in {@link #imaginary
	 * imaginary}.
	 * @param imaginary Der neue Imagin&auml;rteil
	 */
	public void setImaginary (double imaginary) {
		this.imaginary = imaginary;
	}
	
	
	/**
	 * Zugriffsmethode f&uuml;r den Realteil in {@link #real real}.
	 * @return Den Realteil.
	 */
	public double real () {
		return this.real;
	}
	
	
	/**
	 * Zugriffsmethode f&uuml;r den Imagin&auml;rteil in {@link #imaginary
	 * imaginary}.
	 * @return Den Imagin&auml;rteil.
	 */
	public double imaginary () {
		return this.imaginary;
	}
	
	
	/**
	 * Gibt eine String-Repr&auml;sentation dieser komplexen Zahl zur&uum;ck.
	 * @return Einen String der Form <code>"a + b*i"</code>, wobei <code>a</code>
	 * und <code>b</code> jeweils durch den Real- und den Imagin&auml;rteil
	 * ersetzt werden.
	 */
	public String toString () {
		return this.real+" + "+this.imaginary+"*i";
	}
	
	
	
	
	// Blatt 10, Aufgaben 2: Komplexe Arithmetik; und 3: Achtung bei der Division.
	
	
	/**
	 * Addiert die &uuml;bergebe komplexe Zahl zu dieser. Die Addition
	 * komplexer Zahlen ist kommutativ.
	 * @param summand die zu addierende Zahl
	 * @return die Summe (also <code>this</code>)
	 */
	public KomplexeZahl doAddition (KomplexeZahl summand) {
		this.real += summand.real;
		this.imaginary += summand.imaginary;
		return this;
	}
	
	
	/**
	 * Teilt diese Zahl durch die &uuml;bergebene. Die Division komplexer Zahlen
	 * ist <em>nicht</em> kommutativ, weswegen <code>a.divideBy(b)</code> ein
	 * anderes Ergebnis hat als <code>b.divideBy(a)</code>. In dieser Methode
	 * ist <em>diese</em> Zahl (<code>this</code>) der Divident und divisor ist
	 * der Divisor.
	 * @param divisor Der Divisor f&uuml;r diese Division.
	 * @return das Ergebnis der Division (also <code>this</code>)
	 * @see <a href="http://de.wikipedia.org/wiki/Komplexe_Zahlen#Division">Komplexe
	 * Zahlen (Wikipedia)</a>
	 * @throws ArithmeticException falls der Divisor gleich null ist.
	 */
	public KomplexeZahl divideBy (KomplexeZahl divisor) {
		double denominator;
		
		denominator = divisor.real * divisor.real + divisor.imaginary * divisor.imaginary;
		this.real = (this.real * divisor.real + this.imaginary * divisor.imaginary) / denominator;
		this.imaginary = (this.imaginary * divisor.real + this.real * divisor.imaginary) / denominator;
		
		return this;
	}
	
	
	/**
	 * F&uuml;hrt (laut Aufgabenstellung) <q
	 * cite="http://www.christophweser.de/java/aufgaben/WS2005/Blatt10.pdf">mit
	 * der aufgerufenden [sic!] eine Division [durch]</q>. Da die Semantik der
	 * Division nicht definiert wird, wird hier willk&uuml;rlich definiert, dass
	 * diese Zahl der Divident und die &uuml;bergebene Zahl der Divisor ist.
	 * <p>Ist diese Zahl ungleich null, die &uuml;bergebene Zahl aber gleich
	 * null, ist offensichtlich, dass diese Semantik falsch ist. In diesem Fall
	 * wird diese Zahl als Divisor und die &uuml;bergebene Zahl als Divident
	 * angenommen; in diesem Fall ist also das Ergebnis null (<code>0</code>).
	 * <p>Ist sowohl diese Zahl als auch die &uuml;bergebene Zahl gleich null,
	 * kann eine Divison, gleich welcher Richtung, nicht durchgeführt werden.
	 * In diesem Fall wird eine Ausnahme geworfen.
	 * @return das Ergebnis der Division (also <code>this</code>)
	 * @throws ArithmeticException falls der Divisor gleich null ist.
	 */
	KomplexeZahl doDivision (KomplexeZahl number) {
		KomplexeZahl divident;
		
		if (number.real == 0 && number.imaginary == 0) {
			divident = new KomplexeZahl(number.real, number.imaginary);
			divident.divideBy(this);
			this.real = divident.real;
			this.imaginary = divident.imaginary;
		}
		
		return this;
	}
	
}
