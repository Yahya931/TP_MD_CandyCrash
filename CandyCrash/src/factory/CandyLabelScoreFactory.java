package factory;

import view.CandyLabel;
import view.CandyLabelScore;

public class CandyLabelScoreFactory extends CandyLabelFactory{

	public CandyLabelScoreFactory() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected CandyLabel createCandyLabel() {
		// TODO Auto-generated method stub
		return new CandyLabelScore(null);
	}
}
