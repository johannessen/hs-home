/* 
 * $Id: NegativeArgumentException.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Arne Johannessen on 2005-10-20.
 */

package de.thaw.ps1.blatt9;



/**
 * Wird geworfen, um den Aufruf einer Methode mit einem negativen Argument
 * in F&auml;llen anzuzeigen, in denen keine negativen Argumente erlaubt sind.
 * @see de.thaw.ps1.blatt9.Aufgabe9_4#calculateHypotenuse(int,int)
 */
public class NegativeArgumentException extends IllegalArgumentException {
	
	/**
	 * Erstellt eine <code>NegativeArgumentException</code> ohne
	 * Detailnachricht.
	 */
	public NegativeArgumentException () {
		super();
	}
	
	
	/**
	 * Erstellt eine <code>NegativeArgumentException</code> mit Detailnachricht.
	 * @param message Detailnachricht
	 */
	public NegativeArgumentException (String message) {
		super(message);
	}
	
	
	public void getInfo () {
		System.out.println("Die Zahl darf nicht negativ sein.");
	}
	
}
