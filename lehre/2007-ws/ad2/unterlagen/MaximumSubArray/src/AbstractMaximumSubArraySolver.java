/* $Id: AbstractMaximumSubArraySolver.java,v 1.1 2007-12-10 06:53:49 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 */


public abstract class AbstractMaximumSubArraySolver implements MaximumSubArraySolver {
	
	private MsaRacerImplementation delegate = null;
	
	private long nextUiUpdate = 0;
	
	private static final int UI_UPDATE_INTERVAL = 50;
	
//	private static final int THREAD_SLEEP_TIME = 10;
	
	private long sleepingTime = 0;
	
	
	public void setDelegate (MsaRacerImplementation delegate) {
		this.delegate = delegate;
	}
	
	
	protected void reportState (int state, int stateMax) {
		if (this.delegate == null) {
			return;
		}
		long time = System.currentTimeMillis();
		if (time >= nextUiUpdate) {
			this.delegate.displayProgressLater(state, stateMax, null);
			this.nextUiUpdate = time + UI_UPDATE_INTERVAL;
		}
/*		try {
			Thread.sleep(THREAD_SLEEP_TIME);
		}
		catch (InterruptedException exception) {
			throw new UserCanceledException();
		}
*/		Thread.yield();
		if (Thread.interrupted()) {
			throw new UserCanceledException();
		}
		this.sleepingTime += System.currentTimeMillis() - time;
	}
	
	
	protected void initSleepTime () {
		this.sleepingTime = 0;
	}
	
	
	protected void reportSleepTime () {
		if (this.delegate == null) {
			return;
		}
		this.delegate.setSleepTime((this.sleepingTime > 0) ? this.sleepingTime : 0);
	}
	
}
