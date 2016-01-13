package view;

import java.util.Observable;

import model.Partie;

public class CandyLabelScore extends CandyLabel {

	private static final long serialVersionUID = 1L;

	public CandyLabelScore(String txt) {
		super(txt);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Partie p = (Partie)o;
		this.setText("Score : "+p.getScore());
		repaint();
	}

}
