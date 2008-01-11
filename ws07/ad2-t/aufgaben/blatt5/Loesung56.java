/* $Id: Loesung56.java,v 1.4 2008-01-11 02:32:04 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


/**
 * Loesungsvorschlag fuer Aufgabe 5-6.
 * <p>
 * Der "objektorientierte Ansatz" aus Teilaufgabe (a) besteht darin, statt
 * eines primitiven Datentyps wie etwa <code>int</code> eine Klasse als
 * Rueckgabetyp festzulegen. Objekte dieser Klasse enthalten dann mehrere
 * primitive Werte.
 * <p>
 * In diesem Loesungsvorschlag wird eine Klasse <code>DegreesMinutes</code>
 * definiert. Diese Klasse enthaelt zwei Objektvariablen, jeweils eine fuer
 * Grad- und Minutenzahl. Die entsprechenden Methoden erzeugen dann ein
 * neues Objekt dieser Klasse, setzen dessen Grad- und Minutenzahl passend
 * und geben dann dieses Objekt zurueck.
 * 
 * @see MaximumSubArraySolver
 * @see <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/ws07/ad2-t/aufgaben/blatt5/#zusatzaufgabe5-6">Aufgabenblatt 5, Zusatzaufgabe 5-6</A>
 * @author <A HREF="http://www.home.hs-karlsruhe.de/~joar0011/">Arne Johannessen</A>
 * @version $Revision: 1.4 $
 */
public class Loesung56 {
	
	
	
	
	public class Position {
		
		protected double y;
		
		protected double x;
		
		public Position (double y, double x) {
			this.y = y;
			this.x = x;
		}
		
		public double distanceFrom (Position other) {
			double dY = other.y - this.y;
			double dX = other.x - this.x;
			return Math.sqrt(dY*dY + dX*dX);  // Pythagoras
		}
		
	}
	
	
	
	
	
	public class GeographicPosition extends Position {
		
		public GeographicPosition (double lambda, double phi) {
			super(lambda, phi);
		}
		
		public GeographicPosition (double lambdaDegrees, double lambdaMinutes, double phiDegrees, double phiMinutes) {
			super(Double.NaN, Double.NaN);
			this.setLambda(lambdaDegrees, lambdaMinutes);
			this.setPhi(phiDegrees, phiMinutes);
		}
		
		public void setLambda (double degrees, double minutes) {
			super.y = degrees + minutes / 60.0d;
		}
		
		public void setPhi (double degrees, double minutes) {
			super.x = degrees + minutes / 60.0d;
		}
		
	}
	
	
	
	
	
	public class MyGeographicPosition extends GeographicPosition {
		
		public MyGeographicPosition (double lambda, double phi) {
			super(lambda, phi);
		}
		
		public MyGeographicPosition (
				double lambdaDegrees, double lambdaMinutes,
				double phiDegrees, double phiMinutes) {
			super(lambdaDegrees, lambdaMinutes, phiDegrees, phiMinutes);
		}
		
		public DegreesMinutes lambdaDegreesMinutes () {
			DegreesMinutes result = new DegreesMinutes();
			result.degrees = (int)super.y;
			result.minutes = (super.y - result.degrees) * 60.0d;
			return result;
		}
		
		public DegreesMinutes phiDegreesMinutes () {
			DegreesMinutes result = new DegreesMinutes();
			result.degrees = (int)super.x;
			result.minutes = (super.x - result.degrees) * 60.0d;
			return result;
		}
		
	}
	
	
	
	
	
	public class DegreesMinutes {
		
		protected double degrees = Double.NaN;
		
		protected double minutes = Double.NaN;
		
	}
	
	
}
