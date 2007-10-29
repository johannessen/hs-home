/* $Id: MsaTimer.java,v 1.1 2007-10-29 02:21:11 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public class MsaTimer {
	
	
	MaximumSubArraySolver solver = null;
	
	
	MsaTimer (MaximumSubArraySolver solver) {
		if (solver == null) {
			throw new NullPointerException();
		}
		this.solver = solver;
	}
	
	
	MsaTimer (Class solverClass) throws IllegalAccessException, InstantiationException {
		this((MaximumSubArraySolver)solverClass.newInstance());
	}
	
	
	MsaTimer (String solverClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		this(Class.forName(solverClassName));
	}
	
	
	public static void test (MaximumSubArraySolver solver, int length) {
		new MsaTimer(solver).testSolver(length);
	}
	
	
	public static void test (Class solverClass, int length) throws IllegalAccessException, InstantiationException {
		new MsaTimer(solverClass).testSolver(length);
	}
	
	
	public static void test (String solverClassName, int length) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		new MsaTimer(solverClassName).testSolver(length);
	}
	
	
	public static void main (String[] args) throws Throwable {
		try {
			MsaTimer.test(args[0], Integer.parseInt(args[1]));
		}
		catch (ArrayIndexOutOfBoundsException exception) {
			System.err.println("Benutzung:\njava MsaTimer <Klassenname> <Arraylaenge>");
		}
		catch (NumberFormatException exception) {
			System.err.println("Benutzung:\njava MsaTimer <Klassenname> <Arraylaenge>");
		}
	}
	
	
	void testSolver (int length) {
		int[] array = SubArray.createRandomArray(length, 1000);
		long startTime = System.currentTimeMillis();
		SubArray maximum = this.solver.findMaximumSubArray(array);
		long endTime = System.currentTimeMillis();
		maximum.print();
		System.out.println("Summe: ["+maximum.getSum()+"]");
		System.err.println("\n"+(endTime - startTime)+" ms\n");
	}
	
}
