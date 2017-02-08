/* $Id: MsaRacerDelegate.java,v 1.2 2007-12-12 05:49:18 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public interface MsaRacerDelegate {
	
	public void addImplementation (MaximumSubArraySolver solver) ;
	
	public void removeImplementation () ;
	
	public void startRace (int arrayLength) ;
	
	public void stopRace () ;
	
}
