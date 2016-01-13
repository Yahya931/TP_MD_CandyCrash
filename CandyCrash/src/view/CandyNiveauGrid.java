package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Niveau;
import model.Partie;
import controler.PartieController;

public class CandyNiveauGrid extends JPanel implements MouseListener,MouseMotionListener, Runnable{

	private static final long serialVersionUID = 1L;
	private Partie partie; 
    private BufferedImage buffer, icon;
    private PartieController controller;
    private CandyNiveauFrame frame;
	public CandyNiveauGrid(CandyNiveauFrame frames,PartieController controler) {
		   frame = frames;
		   controller = controler;
		   partie  = new Partie(new Niveau());
		   partie.initialiser();
		   partie.fill(); 
		   addMouseListener(this);
	       addMouseMotionListener(this);
	       new Thread(this).start();	
	       }
	
	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Dimension getPreferredSize() {
	        return new Dimension(120 * 5 + 40, 120 * 5 + 40);
	    }
	@Override
	public void paint(Graphics g) {

		BufferedImage buffer = null;
		try {
			buffer =  ImageIO.read(getClass().getResourceAsStream("/resources/background2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Graphics2D	g2 = (Graphics2D) buffer.getGraphics();
		for(int i = 0; i < controller.getAllNiv().size(); i++) {
        	try {
        			icon = ImageIO.read(getClass().getResourceAsStream("/resources/"+
        		String.valueOf(controller.getAllNiv().get(i).isEtat())+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
        		Graphics2D g3 = (Graphics2D) icon.getGraphics();
        		
        		g3.setColor(Color.WHITE);
        		g3.setFont(new Font("Serif",Font.BOLD | Font.ITALIC,80));
        		g3.drawString(String.valueOf(i+1), 120, 80);
        		
        	g2.drawImage(icon, 120 * (i%5) + 40, 120 * (i/5) + 40, 100, 100 ,null);
        }
		g.drawImage(buffer, 0, 0, null);
	}
	
    public void update(Graphics g) {
    	
        paint(g);
    }
    
    
	public PartieController getController() {
		return controller;
	}

	
	public void setController(PartieController controller) {
		this.controller = controller;
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
        int selectedX = e.getX() / 120;
        int selectedY = e.getY() / 120;
        int selectedniv = selectedX+selectedX*selectedY;
        System.out.println(selectedX+"/"+selectedY);
        if(controller.getAllNiv().get(selectedniv).isEtat()){
        	controller.setNiv(controller.getAllNiv().get(selectedX+selectedX*selectedY));
        	try {
				controller.setFrame(new CandyFrame("Niveau "+selectedniv,controller));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	frame.dispose();
        }
        else{

		  	javax.swing.JOptionPane.showMessageDialog(null
		  			,"Niveau bloquer (¡_¡) !","Erreur",JOptionPane.INFORMATION_MESSAGE); 
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run() {
		
		 while(true) {
	            // un pas de simulation toutes les 100ms
	            try { 
	            	Thread.currentThread();
					Thread.sleep(100);
	            	
	            	
	    		} catch(InterruptedException e) { }

	            // s'il n'y a pas de case vide, chercher des alignements
	            if(!partie.fill()) {
	                partie.removeAlignments();
	                
	            }
	            repaint();
	        }	
		  
	}
	
}
