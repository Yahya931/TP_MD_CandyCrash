package view;


import java.io.IOException;

import controler.PartieController;

public class Candy {

	public static void main(String args[]) throws IOException{
		PartieController controller = new PartieController();
		
		CandyNiveauFrame frame = new CandyNiveauFrame("Choisissez le niveau pour jouer",controller);
		frame.setVisible(true);
		
	}
}
