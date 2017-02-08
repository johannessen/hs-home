//
//  Interpol.java
//  Interpol
//
//  Created by Arne Johannessen on 2005-06-25.
//  Copyright (c) 2005 THAWsoftware. All rights reserved.
//

public class Interpol {
	
	final public static double KEINE_ZAEHLKURVEN = 0.0;
	
	final public static char MARKER_ZAEHLKURVE = '-';
	
	
	private double aequidistanz;  // [metres]
	
	private double zaehlkurvendistanz;  // [metres]
	
	
	public Interpol (double aequidistanz, double zaehlkurvendistanz) {
		this.aequidistanz = aequidistanz;
		this.zaehlkurvendistanz = zaehlkurvendistanz;
	}
	
	
	public void printInterpolierteHoehen (double anfang, double ende, double distanz) {
		boolean aufwaerts = anfang < ende;
		double naechsteAequidistanz = (Math.floor(anfang / this.aequidistanz) + (aufwaerts ? 1.0 : 0.0)) * this.aequidistanz;
		double richtung = (aufwaerts ? 1.0 : -1.0);
		double distanzVerhaeltnis = distanz / (ende - anfang);
		char zaehlkurve;
		double naechsteDistanz;
		while (richtung * naechsteAequidistanz <= richtung * ende) {
			zaehlkurve = ((naechsteAequidistanz % this.zaehlkurvendistanz == 0) ? MARKER_ZAEHLKURVE : ' ');  // :BUG: division by zero possible
			naechsteDistanz = distanzVerhaeltnis * (naechsteAequidistanz - anfang);
			System.out.println(""+zaehlkurve+naechsteAequidistanz+zaehlkurve+" @ "+Math.round(naechsteDistanz * 100.0) / 100.0);
			naechsteAequidistanz += this.aequidistanz * richtung;
		}
	}
	
	
    public static void main (String args[]) {
        if (args.length < 4 || args.length > 5) {
			throw new IllegalArgumentException("Wrong argument count!");
		}
		else {
			double zaehlkurvendistanz = Interpol.KEINE_ZAEHLKURVEN;
			if (args.length == 5) {
				zaehlkurvendistanz = Double.parseDouble(args[1]);
			}
			Interpol interpol = new Interpol(Double.parseDouble(args[0]), zaehlkurvendistanz);
			interpol.printInterpolierteHoehen(Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
		}
    }
	
}
