package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.Partie;

public class CandyLabel extends JLabel implements Observer{

	private static final long serialVersionUID = 6480069450280613721L;

	public CandyLabel(String txt) {
		super(txt);
		setSize(100, 30);
		setBackground(new Color(255,255,255,0));
		setForeground(new Color(46,43,226));
		setFont(new Font("Garamond",Font.BOLD,18));

		
	}

	@Override
	public void update(Observable o, Object arg) {
		Partie p = (Partie)o;
		this.setText("Mouvement restant : "+p.getMouvement());
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}
