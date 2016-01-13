package view;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import controler.PartieController;

public class CandyNiveauFrame extends Frame {

	private static final long serialVersionUID = -2569161072883103326L;
	private CandyNiveauGrid grid;
	private PartieController controller;
	
	public CandyNiveauFrame(String titre,PartieController controler) {
		super(titre);
		this.controller = controler;
		grid = new CandyNiveauGrid(this,controller);
		add(grid);
		setSize(700, 450);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent event) {
	                System.exit(0);
	            }
	        });
		setLocationRelativeTo(null);
	}
	
	public CandyNiveauGrid getGrid() {
		return grid;
	}

	public void setGrid(CandyNiveauGrid grid) {
		this.grid = grid;
	}


}
