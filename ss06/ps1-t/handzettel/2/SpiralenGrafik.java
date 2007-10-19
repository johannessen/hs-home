/* $Id: SpiralenGrafik.java,v 1.1 2007-10-19 18:31:25 arne Exp $
 * 
 * by Arne Johannessen
 * Programmiersprachen I, Hochschule Karlsruhe
 */



import java.awt.*;
import java.awt.event.*; 



public class SpiralenGrafik extends Component { 
	
	
	
	private double[] spiralRadien = null;
	
	private double massstab;
	
	private int winkel;
	
	private final int winkelSchritt;  // derzeit nur 90 unterstuetzt
	
	
	
	public SpiralenGrafik (double[] spiralRadien, double massstab, int winkel) {
		super();
		if (spiralRadien == null) {
			throw new NullPointerException();
		}
		this.spiralRadien = spiralRadien;
		this.massstab = massstab;
		this.winkel = winkel;
		this.winkelSchritt = 90;  // derzeit keine anderen Winkel unterstuetzt
	}
	
	
	
	private void zeichneKreisbogen (Graphics graphics, Point position, double radius) {
		
		/* Vektor von position zum "Urspung" (lt. AWT) des Kreisbogens:
		 * winkel |  ursprungDX |  ursprungDY
		 * -------+-------------+-------------
		 *     0  | -radius * 2 |  -radius
		 *    90  |   -radius   |     0
		 *   180  |      0      |  -radius
		 *   270  |   -radius   | -radius * 2
		 */
		double ursprungDX = -radius;
		double ursprungDY = -radius;
		switch (winkel) {
			case 0:
				ursprungDX *= 2;
				break;
			case 90:
				ursprungDY = 0;
				break;
			case 180:
				ursprungDX = 0;
				break;
			case 270:
				ursprungDY *= 2;
				break;
			
			default:
				throw new RuntimeException();
		}
		
		// Bogen zeichnen
		int diameter = (int)(2 * radius);
		graphics.drawArc((int)(position.getX() + ursprungDX), (int)(position.getY() + ursprungDY), diameter, diameter, this.winkel, this.winkelSchritt);
	}
	
	
	
	private void findeNaechstenStartpunkt (Point position, double radius) {
		
		/* Vektor von position (Anfang) zum Ende des Kreisbogens:
		 * winkel |  endeDX |  endeDY
		 * -------+---------+---------
		 *     0  | -radius | -radius
		 *    90  | -radius | +radius
		 *   180  | +radius | +radius
		 *   270  | +radius | -radius
		 */
		double endeDX = radius;
		double endeDY = radius;
		if (this.winkel < 180) {
			endeDX = -radius;
		}
		if (this.winkel < 45 || this.winkel > 215) {
			endeDY = -radius;
		}
		
		// Position verschieben
		position.translate((int)Math.round(endeDX), (int)Math.round(endeDY));
		
		// Richtung drehen
		this.winkel += this.winkelSchritt;
		if (this.winkel >= 360) {
			this.winkel -= 360;  // Vollkreis (Grad)
		}
	}
	
	
	
	public void paint (Graphics graphics) {
		super.paint(graphics);
		
		// Mittelpunkt als Zeichnungsursprung finden
		Dimension componentSize = super.getSize();
		Point position = new Point((int)(componentSize.getWidth() / 2), (int)(componentSize.getHeight() / 2));
		
		// Spirale zeichnen
		double radius;
		for (int index = 0; index < spiralRadien.length; index++) {
			radius = massstab * spiralRadien[index];
			this.zeichneKreisbogen(graphics, position, radius);
			this.findeNaechstenStartpunkt(position, radius);
		}
	}
	
	
	
	public void zeigeInStandardFenster () {
		
		// Fenster vorbereiten
		Frame frame = new Frame();
		frame.setSize(400, 400);  // sollte auf keinem Bildschirm Probleme bereiten
		frame.addWindowListener(new WindowAdapter() {
					public void windowClosing (WindowEvent event) {
						System.exit(0);
					}
				});
		
		// Spirale hinzufuegen
		frame.add(this);
		
		// Fenster anzeigen
		frame.show();
	}
	
	
	
	public static void zeichneFolge (int[] folge) {
		
		// Spirale von int- in double-Array kopieren
		double[] radien = new double[folge.length];
		for (int index = folge.length - 1; index >= 0; index--) {
			radien[index] = folge[index];
		}
		
		// Spirale anzeigen
		(new SpiralenGrafik(radien, 3d, 0)).zeigeInStandardFenster();
	}
	
	
	
	public static void zeichneFolge (double[] folge) {
		
		// Spirale anzeigen
		(new SpiralenGrafik(folge, 3d, 0)).zeigeInStandardFenster();
	}
	
	
	
	public static void main (String[] args) {
		
		// archimedische Spirale erstellen
		double[] archimedischeSpirale = new double[38];
		for (int index = archimedischeSpirale.length - 1; index >= 0; index--) {
			archimedischeSpirale[index] = index;
		}
		SpiralenGrafik grafik = new SpiralenGrafik(archimedischeSpirale, 5d, 0);
		
		// Spirale anzeigen
		grafik.zeigeInStandardFenster();
	}
	
}
