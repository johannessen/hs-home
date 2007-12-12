/* $Id: MsaRacerImplementationsList.java,v 1.2 2007-12-12 05:49:18 arne Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 * 
 * Encoding: UTF-8
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class MsaRacerImplementationsList extends JScrollPane implements MsaRacerDelegate {
	
//	private Collection implementations = null;
	
	private JPanel list;
	
	
	public MsaRacerImplementationsList () {
		super();
		
//		this.implementations = new LinkedList();
		this.list = new JPanel();
		this.list.setLayout(new BoxLayout(this.list, BoxLayout.PAGE_AXIS));
		JPanel listSpacer = new JPanel(null);
		JPanel listContainer = new JPanel(new BorderLayout());
		listContainer.add(this.list, BorderLayout.PAGE_START);
		listContainer.add(listSpacer, BorderLayout.CENTER);
		
		super.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		super.setViewportView(listContainer);
	}
	
	
	public void addImplementation (MaximumSubArraySolver solver) {
		this.stopRace();
		MsaRacerImplementation solverImplementation = new MsaRacerImplementation(solver);
		this.list.add(solverImplementation);
		((Window)this.getTopLevelAncestor()).pack();
//		this.implementations.add(solverImplementation);
	}
	
	
	public void removeImplementation () {
		throw new UnsupportedOperationException();
	}
	
	
	public void startRace (int arrayLength) {
		this.stopRace();
		int[] array = SubArray.createRandomArray((arrayLength >= 0) ? arrayLength : 2500);
		Component[] components = this.list.getComponents();
		MsaRacerImplementation implementation = null;
//		for (Iterator iterator = this.implementations.iterator(); iterator.hasNext(); implementation = (MsaRacerImplementation)iterator.next()) {
		for (int index = 0; index < components.length; index++) {
			implementation = (MsaRacerImplementation)components[index];
			implementation.start(array);
		}
	}
	
	
	public void stopRace () {
		Component[] components = this.list.getComponents();
		MsaRacerImplementation implementation = null;
//		for (Iterator iterator = this.implementations.iterator(); iterator.hasNext(); implementation = (MsaRacerImplementation)iterator.next()) {
		for (int index = 0; index < components.length; index++) {
			implementation = (MsaRacerImplementation)components[index];
			implementation.stop();
		}
		Thread.yield();  // try to give everyone time to actually stop
	}
	
}
