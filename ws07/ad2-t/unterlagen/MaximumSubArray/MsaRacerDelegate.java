/* $Id: MsaRacerDelegate.java,v 1.1 2007-12-10 06:53:49 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public interface MsaRacerDelegate {
	
	public void addImplementation (MaximumSubArraySolver solver) ;
	
	public void removeImplementation () ;
	
	public void startRace () ;
	
	public void stopRace () ;
	
}
