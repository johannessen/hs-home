/* $Id: MsaRacerImplementation.java,v 1.1 2007-12-10 06:53:48 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 * 
 * Encoding: UTF-8
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;


public class MsaRacerImplementation extends JPanel implements Runnable {
	
	private MaximumSubArraySolver solver = null;
	
	private JProgressBar progressBar = null;
	
	private JLabel timerLabel = null;
	
	private int[] array = null;
	
	private long startTime = -1L;
	
	private long sleepTime = 0L;
	
	private Thread thread = null;
	
	
	public MsaRacerImplementation (final MaximumSubArraySolver solver) {
		super();
		
		try {
			((AbstractMaximumSubArraySolver)solver).setDelegate(this);
		}
		catch (ClassCastException exception) {
			// not a problem, we'll simply continue w/o callback opportunity
		}
		
		this.solver = solver;
		this.progressBar = new JProgressBar(0, 1);
		this.timerLabel = new JLabel();
		
		this.add(new JLabel(solver.getClass().getName()));
		this.add(this.progressBar);
		this.add(this.timerLabel);
	}
	
	
	public boolean isSolverIdentical (final MaximumSubArraySolver solver) {
		return this.solver == solver;
	}
	
	
	public void start (final int[] array) {
		this.array = array;
		this.displayProgressNow(0, 1, "running…");
		this.thread = new Thread(this);
		this.thread.start();  // :BUG: bei synchron laufendem solver kommt keine rückmeldung und es werden erst alle threads gestartet, bevor der erste anfängt zu laufen
		// :TODO: ein semaphor, für jeden thread lock um 1 erhöht. P-operation am anfang von run, V-operation _entweder_ am ende von run _oder_ beim ersten gui-update-callback (muss aber in _beiden_ fällen ausführen, insgesamt genau einmal!)
	}
	
	
	public void run () {
		try {
			this.sleepTime = 0L;
			this.startTime = System.currentTimeMillis();
			SubArray maximum = this.solver.findMaximumSubArray(this.array);
			long endTime = System.currentTimeMillis();
			this.displayProgressLater(1, 1, (endTime - startTime - this.sleepTime)+" ms");
		}
		catch (UserCanceledException exception) {
			this.displayProgressLater(-1, -1, "stopped");
		}
	}
	
	
	public void stop () {
		if (this.thread != null && this.thread.isAlive()) {
			this.thread.interrupt();
		}
	}
	
	
	public void setSleepTime (long sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	
	public void displayProgressLater (final int value, final int maxValue, final String time) {
		SwingUtilities.invokeLater(new ProgressDisplayer(value, maxValue, time));
	}
	
	
	public boolean displayProgressNow (final int value, final int maxValue, final String time) {
		try {
			SwingUtilities.invokeAndWait(new ProgressDisplayer(value, maxValue, time));
		}
		catch (InterruptedException exception) {
			SwingUtilities.invokeLater(new ProgressDisplayer(value, maxValue, time));
			return false;
		}
		catch (InvocationTargetException exception) {
			throw new Error(exception);
		}
		return true;
	}
	
	
	private class ProgressDisplayer implements Runnable {
		
		private final int value;
		
		private final int maxValue;
		
		private final String time;
		
		ProgressDisplayer (final int value, final int maxValue, final String time) {
			this.value = value;
			this.maxValue = maxValue;
			this.time = time;
		}
		
		public void run () {
			if (this.maxValue >= 0) {
				progressBar.setMaximum(this.maxValue);
			}
			if (this.value >= 0) {
				progressBar.setValue(this.value);
			}
			if (time != null) {
				MsaRacerImplementation.this.timerLabel.setText(this.time);
			}
		}
		
	}
	
}
