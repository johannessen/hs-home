/* $Id: MsaRacerDelegate.java,v 1.1 2008-06-18 00:29:55 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public interface MsaRacerDelegate {
	
	public void addImplementation (MaximumSubArraySolver solver) ;
	
	public void removeImplementation () ;
	
	public void startRace (int arrayLength) ;
	
	public void stopRace () ;
	
}
