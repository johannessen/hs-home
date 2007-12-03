/* $Id: Loesung53b.java,v 1.1 2007-12-03 03:25:16 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public class Loesung53b {
	
	static  /* bitte nicht versuchen, dieses "static" zu verstehen */
	
	
	
	
	
	public class Position {
		
		protected double y;
		
		protected double x;
		
		public Position (double y, double x) {
			this.y = y;
			this.x = x;
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
	
}
