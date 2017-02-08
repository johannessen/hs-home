/* 
 * $Id: Utils.java $ 
 * Programmiersprachen I
 * 
 * Encoding: us-ascii
 * 
 * Created by Wolfgang Suess on or before 2002-02-01.
 * 
 * Modified by Arne Johannessen on 2005-10-20 to be re-usable within the lecture
 * "Programmiersprachen I" read by Udo Gentner and Christoph Weser. This was
 * achieved by making the class public and adding a package identifier
 * (de.thaw.ps1 was arbitrarily chosen). More information is avaiable at
 * <http://www.christophweser.de/java/>.
 */

package de.thaw.ps1;  // :ADDED:

import java.io.IOException;

//class Utils {  // :REMOVED:
public class Utils {  // :ADDED:


    public static String inputString(String message) {
	
	byte buffer[] = new byte[80];
	int num_char=0;
	String input = "";
	System.out.print(message);
	char c;
	int crlf = System.getProperty("line.separator").length();
	try {
	    num_char = System.in.read(buffer,0,80);
	}
	catch (IOException e) {
	    System.err.println("IO Fehler");
	}
	input = new String(buffer,0, num_char-crlf);
	return input;
    }

    public static int inputInteger(String msg) {
	int i = 0;
	boolean ok = false;
	String int_str="NaN";
	while (! ok) {
	    ok = true;
	    try {
		int_str = inputString(msg);
		i =  Integer.parseInt(int_str);
	    } 
	    catch (NumberFormatException e) {
		System.err.println("'"+int_str+"' ist keine korrekte Ganzzahl");
		ok = false;
	    }
	}
	return i;
    }

    public static float inputFloat(String msg) {
	float f = 0;
	boolean ok = false;
	String int_str="NaN";
	while (! ok) {
	    ok = true;
	    try {
		int_str = inputString(msg);
		f =  Float.parseFloat(int_str);
	    } 
	    catch (NumberFormatException e) {
		System.err.println("'" + int_str+ "' ist keine korrekte Gleitkommazahl");
		ok = false;
	    }
	}
	return f;
    }

    


    public static void main(String argv[]) throws IOException { 
	int count=0;
	int ch;
	String str = inputString("Text:");
	int z = inputInteger("Zahl:");
	float f = inputFloat("Gleitkommazahl:");

	System.out.println(" Text: " + str + "; Zahl: " + z + " Gleitkommazahl: " + f);

    }

}
