/* 
 * $Id: Menge.java 2009-05-03 $
 * Arne Johannessen
 */


/**
 * Objekte dieser Klasse stellen mathematische Mengen dar.
 */
class Menge {
	
	
	/**
	 * Referenz auf den Array, in dem die Elemente der Menge gespeichert
	 * sind.
	 */
	int[] menge;
	
	
	/**
	 * Konstruktor, der beim Erzeugen eines neuen Objekts dieses als leere
	 * Menge initialisiert.
	 */
	Menge () {
		menge = new int[] {};
	}
	
	
	/**
	 * Konstruktor, der beim Erzeugen eines neuen Objekts dieses mit genau
	 * dem uebergebenen Array initialisiert. Veraenderungen oder Schutzkopien
	 * werden nicht gemacht, der Aufrufer muss alle Vorbedingungen
	 * selbst sicherstellen.
	 */
	Menge (int[] menge) {
		this.menge = menge;
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {-9, -2, 0, 1, 2, 5, 9, 12, 42, 815};
		Menge meineMenge = new Menge(array);
		
		// Aufruf weiterer Methoden des Objekts meineMenge z. B. so:
		// int fundstellenIndex = meineMenge.finde(9);
	}
	
}
