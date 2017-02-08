/* 
 * $Id: Loesung52.java 2009-05-27 $
 * Arne Johannessen
 */


class Loesung52 extends Menge {
	
	
	/**
	 * Methode zur Loesung der Aufgabe 5-2 (a).
	 * Sortiert die Menge (Bubble-Sort).
	 */
	void bubbleSort () {
		boolean tauschStattgefunden;
		do {
			tauschStattgefunden = false;
			for (int i = 0; i < menge.length - 1; i++) {
				if (menge[i] > menge[i + 1]) {
					vertausche(i, i + 1);
					tauschStattgefunden = true;
				}
			}
		} while (tauschStattgefunden);
	}
	
	
	/**
	 * Methode zur Loesung der Aufgabe 5-2 (b).
	 * Sortiert die Menge (Insertion-Sort).
	 */
	void insertSort () {
		for (int i = 1; i < menge.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (menge[j] < menge[j + 1]) {
					break;
				}
				vertausche(j, j + 1);
			}
		}
	}
	
	
	/**
	 * Methode zur Loesung der Aufgabe 5-2 (c).
	 * Sortiert die Menge (Selection-Sort).
	 */
	void selectSort () {
		for (int i = 0; i < menge.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < menge.length; j++) {
				if (menge[j] < menge[min]) {
					min = j;
				}
			}
			vertausche(min, i);
		}
	}
	
	
	/**
	 * Vertauscht die Werte an den Stellen i und j der Menge miteinander.
	 */
	void vertausche (int u, int v) {
		int w = menge[u];
		menge[u] = menge[v];
		menge[v] = w;
	}
	
	
	Loesung52 (int[] menge) {
		System.arraycopy(menge, 0, super.menge = new int[menge.length], 0, menge.length);  // bitte nicht versuchen, dies zu verstehen!
	}
	
	
	/**
	 * main-Methode zum Testen von der Kommandozeile aus.
	 */
	public static void main (String[] args) {
		int[] array = new int[] {9, 5, -2, 12, 815, 1, 42, -9, 2, 0};
		
		Loesung52 meineMenge1 = new Loesung52(array);
		meineMenge1.bubbleSort();
		Loesung05.printArray(meineMenge1.menge);
		
		Loesung52 meineMenge2 = new Loesung52(array);
		meineMenge2.insertSort();
		Loesung05.printArray(meineMenge2.menge);
		
		Loesung52 meineMenge3 = new Loesung52(array);
		meineMenge3.selectSort();
		Loesung05.printArray(meineMenge3.menge);
	}
	
}
