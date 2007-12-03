/* $Id: Loesung56.java,v 1.1 2007-12-03 03:25:16 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public class Loesung56 {
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
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
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
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
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
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
	
	
	
	
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	public class DegreesMinutes {
		
		protected double degrees = Double.NaN;
		
		protected double minutes = Double.NaN;
		
	}
	
	
}
