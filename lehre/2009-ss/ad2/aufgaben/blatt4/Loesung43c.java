/* 
 * $Id: Loesung43c.java 2009-05-18 $
 * Arne Johannessen
 */


class Loesung43c {
	
	boolean nurBinaer = false;
	
	int[] array;
	
	Loesung31 binaereSucheRekursion;
	
	Loesung43a binaereSucheIteration;
	
	Loesung41 interpolationsSuche;
	
	
	Loesung43c (int length) {
		System.out.print("Erzeuge sortierten Array mit "+length+" Elementen...");
		array = RandomisedArrayFactory.createRandomArray(length, 1, (int)Math.min((long)length / 5L, (long)Integer.MAX_VALUE - 1));
		java.util.Arrays.sort(array);
		System.out.println(" fertig.");
		
		binaereSucheRekursion = new Loesung31(array);
		binaereSucheIteration = new Loesung43a(array);
		interpolationsSuche = new Loesung41(array);
	}
	
	
	Loesung43c () {
		this(500);
	}
	
	
	long messeSequentiellRekursion (int versuche, Integer schluessel) {
		if (nurBinaer) {
			return -1;
		}
		try {
			int vorgegebenerSchluessel = 0;
			if (schluessel != null) {
				vorgegebenerSchluessel = schluessel.intValue();
			}
			long startzeit = System.currentTimeMillis();
			
			for (int i = 0; i < versuche; i++) {
				int zufaelligerSchluessel = zufaelligerExistenterSchluessel();
				if (schluessel != null) {
					Loesung12.sequentialSearch(array, vorgegebenerSchluessel, 0);
				}
				else {
					Loesung12.sequentialSearch(array, zufaelligerSchluessel, 0);
				}
			}
			
			long endzeit = System.currentTimeMillis();
			return endzeit - startzeit;
		}
		catch (StackOverflowError error) {  // don't try this at home
			return -1;
		}
	}
	
	
	long messeSequentiellIteration (int versuche, Integer schluessel) {
		if (nurBinaer) {
			return -1;
		}
		int vorgegebenerSchluessel = 0;
		if (schluessel != null) {
			vorgegebenerSchluessel = schluessel.intValue();
		}
		long startzeit = System.currentTimeMillis();
		
		for (int i = 0; i < versuche; i++) {
			int zufaelligerSchluessel = zufaelligerExistenterSchluessel();
			if (schluessel != null) {
				Loesung11.sequentialSearch(array, vorgegebenerSchluessel);
			}
			else {
				Loesung11.sequentialSearch(array, zufaelligerSchluessel);
			}
		}
		
		long endzeit = System.currentTimeMillis();
		return endzeit - startzeit;
	}
	
	
	long messeBinaerRekursion (int versuche, Integer schluessel) {
		try {
			int vorgegebenerSchluessel = 0;
			if (schluessel != null) {
				vorgegebenerSchluessel = schluessel.intValue();
			}
			long startzeit = System.currentTimeMillis();
			
			for (int i = 0; i < versuche; i++) {
				int zufaelligerSchluessel = zufaelligerExistenterSchluessel();
				if (schluessel != null) {
					binaereSucheRekursion.finde(vorgegebenerSchluessel);
				}
				else {
					binaereSucheRekursion.finde(zufaelligerSchluessel);
				}
			}
			
			long endzeit = System.currentTimeMillis();
			return endzeit - startzeit;
		}
		catch (StackOverflowError error) {  // don't try this at home
			return -1;
		}
	}
	
	
	long messeBinaerIteration (int versuche, Integer schluessel) {
		int vorgegebenerSchluessel = 0;
		if (schluessel != null) {
			vorgegebenerSchluessel = schluessel.intValue();
		}
		long startzeit = System.currentTimeMillis();
		
		for (int i = 0; i < versuche; i++) {
			int zufaelligerSchluessel = zufaelligerExistenterSchluessel();
			if (schluessel != null) {
				binaereSucheIteration.finde(vorgegebenerSchluessel);
			}
			else {
				binaereSucheIteration.finde(zufaelligerSchluessel);
			}
		}
		
		long endzeit = System.currentTimeMillis();
		return endzeit - startzeit;
	}
	
	
	long messeInterpolation (int versuche, Integer schluessel) {
		if (nurBinaer) {
			return -1;
		}
		try {
			int vorgegebenerSchluessel = 0;
			if (schluessel != null) {
				vorgegebenerSchluessel = schluessel.intValue();
			}
			long startzeit = System.currentTimeMillis();
			
			for (int i = 0; i < versuche; i++) {
				int zufaelligerSchluessel = zufaelligerExistenterSchluessel();
				if (schluessel != null) {
					interpolationsSuche.findeInterpoliert(vorgegebenerSchluessel);
				}
				else {
					interpolationsSuche.findeInterpoliert(zufaelligerSchluessel);
				}
			}
			
			long endzeit = System.currentTimeMillis();
			return endzeit - startzeit;
		}
		catch (StackOverflowError error) {  // don't try this at home
			return -1;
		}
	}
	
	
	int zufaelligerExistenterSchluessel () {
		return array[(int)(Math.random() * (double)array.length)];
	}
	
	
	Vergleich vergleicheBestCase (int versuche) {
		Integer sequentiellBestCaseSchluessel = new Integer(array[0]);  // sequentielle Suche: Best Case <=> Wert genau am Anfang
		Integer binaerBestCaseSchluessel = new Integer(array[(array.length - 1) / 2]);  // binaere Suche: Best Case <=> Wert genau in der Mitte
//		Integer interpolationBestCaseSchluessel = null;  // Interpolationssuche: Best Case hier nicht messbar
		Integer interpolationBestCaseSchluessel = new Integer(array[0] + (array[array.length - 1] - array[0]) / (array.length - 1));  // Interpolationssuche: Best Case <=> Wert an interpolierter Startposition (hier Mitte)
		
		Vergleich bestCaseVergleich = new Vergleich();
		System.out.print("Vergleiche Best-Case-Performance...");
		bestCaseVergleich.sequentiellRekursion = messeSequentiellRekursion(versuche, sequentiellBestCaseSchluessel);
		bestCaseVergleich.sequentiellIteration = messeSequentiellIteration(versuche, sequentiellBestCaseSchluessel);
		bestCaseVergleich.binaerRekursion = messeBinaerRekursion(versuche, binaerBestCaseSchluessel);
		bestCaseVergleich.binaerIteration = messeBinaerIteration(versuche, binaerBestCaseSchluessel);
		bestCaseVergleich.interpolation = messeInterpolation(versuche, interpolationBestCaseSchluessel);
		System.out.println(" fertig.");
		return bestCaseVergleich;
	}
	
	
	Vergleich vergleicheAverageCase (int versuche) {
		Integer sequentiellAverageCaseSchluessel = null;  // sequentielle Suche: Average Case <=> jeweils zufaellige Auswahl
		Integer binaerAverageCaseSchluessel = null;  // binaere Suche: Average Case <=> jeweils zufaellige Auswahl
		Integer interpolationAverageCaseSchluessel = null;  // Interpolationssuche: Average Case <=> jeweils zufaellige Auswahl
		
		Vergleich averageCaseVergleich = new Vergleich();
		System.out.print("Vergleiche Average-Case-Performance...");
		averageCaseVergleich.sequentiellRekursion = messeSequentiellRekursion(versuche, sequentiellAverageCaseSchluessel);
		averageCaseVergleich.sequentiellIteration = messeSequentiellIteration(versuche, sequentiellAverageCaseSchluessel);
		averageCaseVergleich.binaerRekursion = messeBinaerRekursion(versuche, binaerAverageCaseSchluessel);
		averageCaseVergleich.binaerIteration = messeBinaerIteration(versuche, binaerAverageCaseSchluessel);
		averageCaseVergleich.interpolation = messeInterpolation(versuche, interpolationAverageCaseSchluessel);
		System.out.println(" fertig.");
		return averageCaseVergleich;
	}
	
	
	Vergleich vergleicheWorstCase (int versuche) {
		Integer sequentiellWorstCaseSchluessel = new Integer(Integer.MIN_VALUE);  // sequentielle Suche: Worst Case <=> garantiert nicht enthalten
		Integer binaerWorstCaseSchluessel = new Integer(Integer.MIN_VALUE);  // binaere Suche: Worst Case <=> garantiert nicht enthalten
		Integer interpolationWorstCaseSchluessel = null;  // Interpolationssuche: Worst Case hier nicht messbar
		
		Vergleich worstCaseVergleich = new Vergleich();
		System.out.print("Vergleiche Worst-Case-Performance...");
		worstCaseVergleich.sequentiellRekursion = messeSequentiellRekursion(versuche, sequentiellWorstCaseSchluessel);
		worstCaseVergleich.sequentiellIteration = messeSequentiellIteration(versuche, sequentiellWorstCaseSchluessel);
		worstCaseVergleich.binaerRekursion = messeBinaerRekursion(versuche, binaerWorstCaseSchluessel);
		worstCaseVergleich.binaerIteration = messeBinaerIteration(versuche, binaerWorstCaseSchluessel);
		worstCaseVergleich.interpolation = -1;
		System.out.println(" fertig.");
		return worstCaseVergleich;
	}
	
	
	class Vergleich {
		long sequentiellRekursion;
		long sequentiellIteration;
		long binaerRekursion;
		long binaerIteration;
		long interpolation;
	}
	
	
	/** main-Methode zum Testen von der Kommandozeile aus. */
	public static void main (String[] args) {
		Loesung43c loesung43c;
		if (args.length >= 1) {
			loesung43c = new Loesung43c(Integer.parseInt(args[0]));
		}
		else {
			loesung43c = new Loesung43c();
		}
		int versuche = 500000;
		if (args.length >= 2 && args[args.length - 1].equalsIgnoreCase("-b")) {
			loesung43c.nurBinaer = true;
		}
		else if (args.length >= 2) {
			versuche = Integer.parseInt(args[1]);
		}
		
		Vergleich bestCase = loesung43c.vergleicheBestCase(versuche);
		Vergleich averageCase = loesung43c.vergleicheAverageCase(versuche);
		Vergleich worstCase = loesung43c.vergleicheWorstCase(versuche);
		
		System.out.println("Ergebnis der Suche f\u00fcr "+versuche+" Versuche [ms]:");
		System.out.println();
		System.out.println("                         \tBest\tAvg.\tWorst");
		System.out.println("sequentiell, rekursiv:   \t"
				+bestCase.sequentiellRekursion+"\t"
				+averageCase.sequentiellRekursion+"\t"
				+worstCase.sequentiellRekursion);
		System.out.println("sequentiell, iterativ:   \t"
				+bestCase.sequentiellIteration+"\t"
				+averageCase.sequentiellIteration+"\t"
				+worstCase.sequentiellIteration);
		System.out.println("bin\u00e4r, rekursiv:         \t"
				+bestCase.binaerRekursion+"\t"
				+averageCase.binaerRekursion+"\t"
				+worstCase.binaerRekursion);
		System.out.println("bin\u00e4r, iterativ:         \t"
				+bestCase.binaerIteration+"\t"
				+averageCase.binaerIteration+"\t"
				+worstCase.binaerIteration);
		System.out.println("Interpolation, rekursiv: \t"
				+bestCase.interpolation+"\t"
				+averageCase.interpolation+"\t"
				+"--");
		System.out.println();
	}
	
}
