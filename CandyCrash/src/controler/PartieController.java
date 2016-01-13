package controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import view.CandyFrame;
import view.CandyNiveauFrame;
import model.Niveau;
import model.Partie;

public class PartieController implements Observer {

	private List<Niveau> allNiv;
	private Partie p;
	private CandyFrame frame;
	private CandyNiveauFrame niveaux;
	private Niveau niv;
	
	public PartieController() {
		allNiv= new ArrayList<Niveau>();
		niv = new Niveau();
		allNiv.add(new Niveau(1,25,500,true));
		allNiv.add(new Niveau(2,60,2000,false));
		allNiv.add(new Niveau(3,120,5000,false));
		allNiv.add(new Niveau(4,150,6000,false));
		allNiv.add(new Niveau(5,130,6000,false));
		niv=allNiv.get(0);
	}


	 
	@Override
	public void update(Observable o, Object arg) {
		frame.repaint();
	}

	public CandyNiveauFrame getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(CandyNiveauFrame niveaux) {
		this.niveaux = niveaux;
	}

	public List<Niveau> getAllNiv() {
		return allNiv;
	}

	public void setAllNiv(List<Niveau> allNiv) {
		this.allNiv = allNiv;
		
	}

	public Partie getP() {
		return p;
	}

	public void setP(Partie p) {
		this.p = p;
	}

	public CandyFrame getFrame() {
		return frame;
	}

	public void setFrame(CandyFrame frame) {
		this.frame = frame;
	}

	public Niveau getNiv() {
		return niv;
	}

	public void setNiv(Niveau niv) {
		this.niv = niv;
	}
	
	public Niveau getNextNiveau(){
		int index = allNiv.indexOf(niv);
		return allNiv.get(index+1);
	}
}
