/* $Id: MsaRacer.java,v 1.1 2008-06-18 00:29:55 aj3 Exp $
 * by Arne Johannessen
 * Faculty of Geomatics, Hochschule Karlsruhe - Technik und Wirtschaft
 * 
 * Encoding: UTF-8
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MsaRacer extends JFrame {
	
	private MsaRacerImplementationsList list = null;
	
	private MsaRacerController controller = null;
	
	
	public MsaRacer () {
		super("MsaRacer");
		
		super.getContentPane().setLayout(new BorderLayout());
		super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		this.list = new MsaRacerImplementationsList();
		this.controller = new MsaRacerController(this.list);
		
		super.getContentPane().add(new JLabel("Verfügbare Lösungen des Maximum–Sub-Array–Problems:"), BorderLayout.PAGE_START);
		super.getContentPane().add(this.list, BorderLayout.CENTER);
		super.getContentPane().add(this.controller, BorderLayout.PAGE_END);
		
		super.pack();
		super.setVisible(true);
	}
	
	
	public static void start () {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				new MsaRacer();
			}
		});
	}
	
	
	public static void main (String[] args) {
		MsaRacer.start();
	}
	
}
