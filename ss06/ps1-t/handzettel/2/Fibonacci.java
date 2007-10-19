/* Dateiname: Fibonacci.java
 * Programmiersprachen I, Hochschule Karlsruhe
 * Beispiel 3 zum Tutorium
 * Autor: Arne Johannessen
 * geschrieben am 2006-05-02
 */



/**
 * Berechnet die ersten 20 Fibonacci-Zahlen, gibt sie auf dem Terminal aus und
 * zeichnet die Fibonaccispirale.
 * 
 * Definiton von Fibonacci-Zahlen:
 * Die n-te Fibonacci-Zahl f(n) ist die Summe aus den beiden ("vorhergehenden")
 * Fibonacci-Zahlen an den Stellen n - 1 und n - 2. f(0) sei 0, f(1) sei 1.
 */
public class Fibonacci
{
	
	
	
	/** Hauptprogramm */
	public static void main (String[] args)
	{
		int[] fibonacci;
		
		fibonacci = Fibonacci.berechneFolge(20);
		Fibonacci.gibFolgeAus(fibonacci);
		SpiralenGrafik.zeichneFolge(fibonacci);
	}
	
	
	
	/** berechne die Zahlen der Fibonacci-Folge */
	public static int[] berechneFolge (int anzahl)
	{
		int[] f = new int[anzahl];
		
		// Startwerte (lt. Definition)
		f[0] = 0;
		f[1] = 1;
		
		// die Folge ist definiert ab Stelle 2:
		for (int n = 2; n < anzahl; n++)
		{
			// diese Fibonacci-Zahl berechnen
			f[n] = f[n - 2] + f[n - 1];
		}
		
		// Array mit fertig berechnter Folge zurueck geben
		return f;
	}
	
	
	
	/** gib Zahlen der Fibonacci-Folge im Terminal aus */
	public static void gibFolgeAus (int[] folge)
	{
		for (int index = 0; index < folge.length; index++)
		{
			System.out.print(folge[index]+" ");
		}
		System.out.println();
	}
	
}
