/* $Id: MsaTester.java,v 1.1 2008-06-04 17:49:25 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */



/**
 * Diese Klasse kann ueberpruefen, ob ein oder mehrere
 * Loesungsalgorithmen des Maximum--Sub-Array--Problems
 * korrekt arbeiten.
 */
public class MsaTester {
	
	
	MaximumSubArraySolver solver = null;
	
	
	MsaTester (MaximumSubArraySolver solver) {
		if (solver == null) {
			throw new NullPointerException();
		}
		this.solver = solver;
	}
	
	
	MsaTester (Class solverClass) throws IllegalAccessException, InstantiationException {
		this((MaximumSubArraySolver)solverClass.newInstance());
	}
	
	
	MsaTester (String solverClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		this(Class.forName(solverClassName));
	}
	
	
	public static void test (MaximumSubArraySolver solver) {
		new MsaTester(solver).testSolver();
	}
	
	
	public static void test (Class solverClass) throws IllegalAccessException, InstantiationException {
		new MsaTester(solverClass).testSolver();
	}
	
	
	public static void test (String solverClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		new MsaTester(solverClassName).testSolver();
	}
	
	
	public static void main (String[] args) throws Throwable {
		int index = 0;
		while (index < args.length) {
			MsaTester.test(args[index++]);
		}
		if (index == 0) {
			System.err.println("Benutzung:\njava MsaTester <Klassenname> [<Klassenname> [...] ]");
		}
	}
	
	
	static void test_Loesungsvorschlaege () throws Throwable {
		String[] args = {"Loesung13", "Loesung21", "Loesung22", "Loesung42"};
		MsaTester.main(args);
	}
	
	
	void assertEquals (Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null || obj1 != null && obj2 != null && obj1.equals(obj2)) {
			return;
		}
		throw new AssertionError("\n"+this.solver.getClass().getName()+": <"+String.valueOf(obj1)+"> , <"+String.valueOf(obj2)+">");
	}
	
	
	void testSolver () {
		String solverClassName = this.solver.getClass().getName();
		try {
			this.test();
			System.out.println("Die Klasse \""+solverClassName+"\" ist ein korrekter MaximumSubArraySolver.");
		}
		catch (AssertionError error) {
			String methodName = error.getStackTrace()[1].getMethodName();
			if (methodName.startsWith("test")) {
				String testName = methodName.substring(4, methodName.length());
				System.out.println("Die Klasse \""+solverClassName+"\" ist kein korrekter MaximumSubArraySolver:");
				System.out.println("Der Test \""+testName+"\" ist fehlgeschlagen.\n");
			}
			throw error;
		}
	}
	
	
	
	
	
	void test () {
		testEinElementMaximum();
		testMaximumLinkerRand();
		testMaximumRechterRand();
		testMaximumMitte();
		testGanzerArrayMaximum();
		
		testNegativerArray();
		testZweiElementeArray();
		testEinElementArray();
		testLeererArray();
	}
	
	
	void testEinElementMaximum () {
		int[] array = {2, -4, 8, -1};
		SubArray korrekteLoesung = new SubArray(array, 2, 1);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testMaximumLinkerRand () {
		int[] array = {2, -1, 8, -16, 4};
		SubArray korrekteLoesung = new SubArray(array, 0, 3);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testMaximumRechterRand () {
		int[] array = {1, 8, 0, -4, -32, 2, 16};
		SubArray korrekteLoesung = new SubArray(array, 5, 2);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testMaximumMitte () {
		int[] array = {16, 8, -32, 4, 64, -2, 0, 1};
		SubArray korrekteLoesung = new SubArray(array, 3, 2);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testGanzerArrayMaximum () {
		int[] array = {8, -1, 0, -2, -4, 8};
		SubArray korrekteLoesung = new SubArray(array, 0, 6);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testNegativerArray () {
		int[] array = {-2, -1, -4};
		SubArray korrekteLoesung = new SubArray(array, 0, 0);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testZweiElementeArray () {
		int[] array = {1, -2};
		SubArray korrekteLoesung = new SubArray(array, 0, 1);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testEinElementArray () {
		int[] array = {1};
		SubArray korrekteLoesung = new SubArray(array, 0, 1);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
	
	void testLeererArray () {
		int[] array = new int[0];
		SubArray korrekteLoesung = new SubArray(array, 0, 0);
		assertEquals(korrekteLoesung, solver.findMaximumSubArray(array));
	}
	
}



