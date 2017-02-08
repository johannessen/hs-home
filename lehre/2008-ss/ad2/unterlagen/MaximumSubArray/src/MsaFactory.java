/* $Id: MsaFactory.java,v 1.1 2008-06-18 00:29:55 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public class MsaFactory {
	
	
	public MsaFactory () {
	}
	
	
	public MsaFactory (Object caller) {
	}
	
	
	private MaximumSubArraySolver createSolver (MaximumSubArraySolver solver) {
		if (solver == null) {
			throw new NullPointerException();
		}
		return solver;
	}
	
	
	public MaximumSubArraySolver createSolver (Class solverClass) throws IllegalAccessException, InstantiationException {
		return this.createSolver((MaximumSubArraySolver)solverClass.newInstance());
	}
	
	
	public MaximumSubArraySolver createSolver (String solverClassName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
		return this.createSolver(Class.forName(solverClassName));
	}
	
	
	private MaximumSubArraySolver createSolverSafe (String solverClassName) {
		try {
			return this.createSolver(Class.forName(solverClassName));
		}
		catch (Exception exception) {
			return null;
		}
	}
	
	
	MaximumSubArraySolver[] createDefaultSolvers () {
		MaximumSubArraySolver[] defaultSolvers = new MaximumSubArraySolver[4];
		defaultSolvers[0] = this.createSolverSafe("Naiv");
		defaultSolvers[1] = this.createSolverSafe("Halbnaiv");
		defaultSolvers[2] = this.createSolverSafe("Scanline");
		defaultSolvers[3] = this.createSolverSafe("DivideAndConquer");
		return defaultSolvers;
	}
	
}



