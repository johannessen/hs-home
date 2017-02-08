/* 
 * $Id: Loesung41.java 2009-05-23 $
 * Arne Johannessen
 */


class Loesung41 extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 4-1.
	 * Interpolationssuche nach einer Zahl in der Menge. Die Suche ist auf den
	 * Bereich von 'links' bis 'rechts' beschraenkt (jeweils einschliesslich).
	 * 
	 * - Vorbedingung fuer Korrektheit:
	 *   menge ist aufsteigend sortiert
	 * - Vorbedingung fuer Effizienz:
	 *   menge hat gleichmaessige Werteverteilung (vgl. Telefonbuch)
	 */
	int findeInterpoliert (int zahl, int links, int rechts) {
		
		// falls kein Array-Teil mehr uebrig ist, schlaegt die Suche fehl
		if (links > rechts) {
			return -1;
		}
		
		// Interpolations-Formel fuer 'm' (interpolierterIndex) anwenden:
		int interpolierterIndex = links;
		int werteBereich = menge[rechts] - menge[links];
		if (werteBereich != 0) {  // Division durch 0 vermeiden
			int indexBereich = rechts - links;
			interpolierterIndex += (zahl - menge[links]) * indexBereich / werteBereich;
			interpolierterIndex = Math.min(rechts, Math.max(links, interpolierterIndex));  // Indizes ausserhalb des Arrays vermeiden
		}
		
		// rekursive binaere Suche
		if (zahl < menge[interpolierterIndex]) {
			return findeInterpoliert(zahl, links, interpolierterIndex - 1);
		}
		else if (zahl > menge[interpolierterIndex]) {
			return findeInterpoliert(zahl, interpolierterIndex + 1, rechts);
		}
		
		// an diesem Punkt ist die Suche erfolgreich
		// (zahl == menge[interpolierterIndex])
		return interpolierterIndex;
	}
	
	
	/**
	 * Interpolationssuche nach einer Zahl in der Menge.
	 */
	int findeInterpoliert (int zahl) {
		return findeInterpoliert(zahl, 0, menge.length - 1);  // Anfangswerte fuer 'links' und 'rechts'
	}
	
	
	Loesung41 (int[] menge) {
		super(menge);  // Konstruktor erben
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Loesung41 meineMenge = new Loesung41(array);
		
		System.out.println( meineMenge.findeInterpoliert(2) );  // 4
		System.out.println( meineMenge.findeInterpoliert(5) );  // 5
		System.out.println( meineMenge.findeInterpoliert(-9) );  // 0
		System.out.println( meineMenge.findeInterpoliert(0) );  // 2
		System.out.println( meineMenge.findeInterpoliert(-1000) );  // -1
		System.out.println( meineMenge.findeInterpoliert(1000) );  // -1
	}
	
}
