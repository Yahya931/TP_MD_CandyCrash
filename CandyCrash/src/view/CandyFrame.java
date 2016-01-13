package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controler.PartieController;
import factory.CandyLabelFactory;
import factory.CandyLabelScoreFactory;
import factory.CandyLabelTimeFactory;


public class CandyFrame extends Frame{

	private static final long serialVersionUID = 1L;

	private CandyGrid grid;
	private CandyLabel label2;
	private CandyLabelScore label1;
	private JLabel label3;
	private JPanel p;
	private PartieController controller;
	
	
	public CandyFrame(String titre,PartieController control) throws IOException{
		super(titre);
		CandyLabelFactory factory1 =new CandyLabelScoreFactory();
		controller = control;
		setLayout(new BorderLayout());
		p= new JPanel();
		p.setBackground(new Color(255,255,255,0));
		
		label1=(CandyLabelScore) factory1.getCandyLabel();
		
		factory1 = new CandyLabelTimeFactory();
		label2= factory1.getCandyLabel();
		
		label1.setText("\n Score :\n 0.");
		label2.setText("\n Mouvements restants :\n "+controller.getNiv().getTime());
		label3 = new JLabel("\n Objectif :\n"+controller.getNiv().getObjectif());
		
		label1.setBackground(new Color(255,255,255,0));
		label2.setBackground(new Color(255,255,255,0));
		label3.setBackground(new Color(255,255,255,0));
		label3.setForeground(new Color(46,43,226));
		label3.setFont(new Font("Garamond",Font.BOLD,18));
		
		grid = new CandyGrid(this,controller);
		
		p.setLayout(new GridLayout(1,3));
		add(grid);

		p.add(label1);
		p.add(label2);
		p.add(label3);
		
		add(p);
		setLayout(new FlowLayout());
		setSize(700, 450);
		setBackground(Color.white);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent event) {
	                System.exit(0);
	            }
	        });
		setLocationRelativeTo(null);
		grid.getPartie().setScore(0);
		grid.getPartie().setMouvement(controller.getNiv().getTime());
	}
	
	@Override
	public void paint(Graphics g) {
		BufferedImage buffer = null;
		try {
			buffer = ImageIO.read(getClass().getResourceAsStream("/resources/back.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(buffer, 0, 0,this.getWidth(),this.getHeight(), null);
		super.paint(g);
	}


	public CandyLabel getLabel2() {
		return label2;
	}

	public void setLabel2(CandyLabel label2) {
		this.label2 = label2;
	}

	public CandyLabelScore getLabel1() {
		return label1;
	}

	public void setLabel1(CandyLabelScore label1) {
		this.label1 = label1;
	}

	
}
