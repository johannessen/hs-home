/* $Id: MsaRacerController.java,v 1.2 2007-12-12 05:49:18 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 * 
 * Encoding: UTF-8
 */


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class MsaRacerController extends JPanel implements ChangeListener {
	
	private MsaRacerDelegate delegate = null;
	
	private AbstractButton addButton = null;
	
	private MsaFactory factory = null;
	
	private JLabel arrayInfo = null;
	
	private JSlider arraySlider = null;
	
	
	public MsaRacerController (MsaRacerDelegate delegate) {
		super(new BorderLayout());
		
		if (delegate == null) {
			throw new NullPointerException();
		}
		this.delegate = delegate;
		
		this.factory = new MsaFactory(this);
		
		JPanel arrayPanel = new JPanel();
		arrayPanel.setLayout(new BoxLayout(arrayPanel, BoxLayout.PAGE_AXIS));
		this.arrayInfo = new JLabel("Array mit 2500 Zahlen");
		this.arraySlider = new JSlider(6214608, 12431214, 7824046);
		this.arraySlider.addChangeListener(this);
		arrayPanel.add(this.arrayInfo);
		arrayPanel.add(this.arraySlider);
		
		JPanel listPanel = new JPanel(new FlowLayout());
		listPanel.add(new JButton(new AddAction()));
		listPanel.add(new JButton(new RemoveAction()));
		
		JPanel startStopPanel = new JPanel(new FlowLayout());
		startStopPanel.add(new JButton(new StartAction()));
		startStopPanel.add(new JButton(new StopAction()));
		
		super.add(arrayPanel, BorderLayout.PAGE_START);
		super.add(listPanel, BorderLayout.LINE_START);
		super.add(new JPanel(), BorderLayout.CENTER);
		super.add(startStopPanel, BorderLayout.LINE_END);
		
		this.setupDefaultSolvers();
	}
	
	
	private void setupDefaultSolvers () {
		MaximumSubArraySolver[] solvers = this.factory.createDefaultSolvers();
		for (int index = 0; index < solvers.length; index++) {
			if (solvers[index] != null) {
				SwingUtilities.invokeLater(new AddRunnable(solvers[index]));
			}
		}
	}
	
	
	private int arrayLength () {
		return (int)Math.round(Math.exp((double)this.arraySlider.getValue() / 1000000d)) - 500;
	}
	
	
	public void stateChanged (ChangeEvent event) {
		if (event.getSource() == this.arraySlider) {
			this.arrayInfo.setText("Array mit "+this.arrayLength()+" Zahlen");
		}
	}
	
	
	private abstract class AbstractRacerAction extends AbstractAction implements Runnable {
		
		AbstractRacerAction (String name) {
			super(name);
		}
		
		public void actionPerformed (ActionEvent event) {
			new Thread(this).start();
		}
		
	}
	
	
	private class AddAction extends AbstractRacerAction {
		
		AddAction () {
			super("+");
		}
		
		public void actionPerformed (ActionEvent event) {
			new Thread(this).start();
		}
		
		public void run () {
			// some lengthy operations; take these out of the event thred just to be safe
			String className = JOptionPane.showInputDialog(MsaRacerController.this, "Bitte den qualifizierten Namen der hinzuzufügenden Klasse angeben:");
			MaximumSubArraySolver solver;
			try {
				solver = MsaRacerController.this.factory.createSolver(className);
			}
			catch (NullPointerException exception) {
				return;  // user cancelled
			}
			catch (ClassNotFoundException exception) {
				JOptionPane.showMessageDialog(MsaRacerController.this, "Die Klasse „"+className+"“ konnte im CLASSPATH nicht gefunden werden.\nBitte überprüfen Sie den Klassennamen und stellen Sie sicher, dass es sich tatsächlich um einen qualifizierten Namen handelt.\n(ClassNotFoundException)");
				return;
			}
			catch (IllegalAccessException exception) {
				JOptionPane.showMessageDialog(MsaRacerController.this, "Die Klasse „"+className+"“ konnte nicht instanziiert werden.\n(IllegalAccessException)");
				return;
			}
			catch (InstantiationException exception) {
				JOptionPane.showMessageDialog(MsaRacerController.this, "Die Klasse „"+className+"“ konnte nicht instanziiert werden, da sie abstrakt ist.\nBitte laden Sie statt dessen eine konkrete Implementierung.\n(InstantiationException)");
				return;
			}
			catch (ClassCastException exception) {
				JOptionPane.showMessageDialog(MsaRacerController.this, "Die Klasse „"+className+"“ ist keine Lösung des Maximum–Sub-Array–Problems.\n(ClassCastException)");
				return;
			}
			catch (Exception exception) {
				throw new Error(exception);
			}
			try {
				MsaTester.test(solver);
			}
			catch (AssertionError error) {
				String[] options = new String[] {"Hinzufügen", "Abbrechen"};
				boolean userCanceled = JOptionPane.showOptionDialog(MsaRacerController.this, "Die Klasse „"+className+"“ ist augenscheinlich keine korrekte\nLösung des Maximum–Sub-Array–Problems. Dies kann zu unvorhersehbarem Verhalten führen.\nSofern Sie nicht genau wissen, was Sie tun, sollten Sie diese Klasse nicht hinzufügen.", "Warnung", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]) != 0;
				if (userCanceled) {
					return;
				}
			}
			// gui manipulations: return control to the event thread
			SwingUtilities.invokeLater(new AddRunnable(solver));
		}
		
	}
	
	
	private class AddRunnable implements Runnable {
		
		private MaximumSubArraySolver solver;
		
		AddRunnable (MaximumSubArraySolver solver) {
			this.solver = solver;
		}
		
		public void run () {
			MsaRacerController.this.delegate.addImplementation(solver);
		}
		
	}
	
	
	private class RemoveAction extends AbstractAction {
		
		RemoveAction () {
			super("-");
			super.setEnabled(false);
		}
		
		public void actionPerformed (ActionEvent event) {
			MsaRacerController.this.delegate.removeImplementation();
		}
		
	}
	
	
	private class StartAction extends AbstractRacerAction {
		
		StartAction () {
			super("Start");
		}
		
		public void run () {
			MsaRacerController.this.delegate.startRace(MsaRacerController.this.arrayLength());
		}
		
	}
	
	
	private class StopAction extends AbstractRacerAction {
		
		StopAction () {
			super("Stop");
		}
		
		public void run () {
			MsaRacerController.this.delegate.stopRace();
		}
		
	}
	
}
