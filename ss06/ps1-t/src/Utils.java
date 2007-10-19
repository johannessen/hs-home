/* 
 * $Id: Utils.java,v 1.1 2007-10-19 18:31:35 arne Exp $ 
 * 
 * Encoding: us-ascii
 * 
 * This file is part of the lecture "Programmiersprachen I"
 * Fakultaet fuer Geomatik / Faculty of Geomatics
 * Hochschule Karlsruhe - Technik und Wirtschaft, University of Applied Sciences
 * 
 * The Copyright status of this file is UNKNOWN.
 * It may have been created and/or modified by the following individuals:
 * -- Wolfgang Suess on or before 2002-02-01
 * -- Udo Gentner on or before 2005-11-07
 * -- Christoph Weser on or before 2005-11-07
 * -- Bernhard Buerg on or before 2006-03-29
 * 
 * Further modified by Arne Johannessen on 2006-04-04. This modified version may
 * only be used within the lecture "Programmiersprachen I" at the University of
 * Applied Sciences at Karlsruhe in the summer term 2006.
 * joar0011 AT hs-karlsruhe DOT de
 * 
 * Weitere Aenderungen von Arne Johannessen am 2006-04-04. Diese geaenderte
 * Fassung darf nur fuer der Vorlesung "Programmiersprachen I" an der Hochschule
 * Karlsruhe - Technik und Wirtschaft im Sommersemester 2006 verwendet werden.
 * joar0011 BEI hs-karlsruhe PUNKT de
 */


import java.io.IOException;


public class Utils {
	
	
	public static String inputString (String message) {
		byte buffer[] = new byte[80];
		int num_char = 0;
		String input = "";
		System.out.print(message);
		char c;
		int crlf = System.getProperty("line.separator").length();
		try {
			num_char = System.in.read(buffer, 0, 80);
		}
		catch (IOException e) {
			System.err.println("IO Fehler");
		}
		input = new String(buffer, 0, num_char-crlf);
		return input;
	}


	public static char inputChar (String message) {
		byte buffer[] = new byte[80];
		int num_char = 0;
		char input = 'n';
		System.out.print(message);
		char c;
		int crlf = System.getProperty("line.separator").length();
		try {
			num_char = System.in.read(buffer, 0, 80);
		}
		catch (IOException e) {
			System.err.println("IO Fehler");
		}
		if (num_char > 0)
			input = (char)buffer[0];
		return input;
	}
	
	
	public static int inputInteger (String msg) {
		int i = 0;
		boolean ok = false;
		String int_str = "NaN";
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
	
	
	public static float inputFloat (String msg) {
		float f = 0;
		boolean ok = false;
		String int_str = "NaN";
		while (! ok) {
			ok = true;
			try {
				int_str = inputString(msg);
				f =  Float.parseFloat(int_str);
			} 
			catch (NumberFormatException e) {
				System.err.println("'"+int_str+"' ist keine korrekte Gleitkommazahl");
				ok = false;
			}
		}
		return f;
	}
	
	
	public static double inputDouble (String msg) {
		double f = 0;
		boolean ok = false;
		String int_str = "NaN";
		while (! ok) {
			ok = true;
			try {
				int_str = inputString(msg);
				f =  Double.parseDouble(int_str);
			} 
			catch (NumberFormatException e) {
				System.err.println("'"+int_str+"' ist keine korrekte Gleitkommazahl");
				ok = false;
			}
		}
		return f;
	}
	
	
	public static void main (String argv[]) throws IOException { 
		int count = 0;
		int ch;
		char c = inputChar("Zeichen:");
		String str = inputString("Text:");
		int z = inputInteger("Zahl:");
		float f = inputFloat("Gleitkommazahl:");
		
		System.out.println("Zeichen: "+c+" Text: "+str+"; Zahl: "+z+" Gleitkommazahl: "+f);
	}
	
}
