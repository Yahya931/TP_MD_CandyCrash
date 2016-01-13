package factory;



import view.CandyLabel;

public class CandyLabelFactory {

	private CandyLabelFactory instance;
	
	protected CandyLabel createCandyLabel() {
		return new CandyLabel(null);
	}

	
	public CandyLabelFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public CandyLabel getCandyLabel(){
		return createCandyLabel();
	}
	
	public CandyLabelFactory getInstance(){return instance;}
	
}
