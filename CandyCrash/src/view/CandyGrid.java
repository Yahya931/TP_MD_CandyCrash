package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import controler.PartieController;
import model.*;
public class CandyGrid extends Panel implements Runnable, MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = -7833194219510322237L;
	private Partie partie; 
    private BufferedImage buffer, icon;
    private int selectedX = -1, selectedY = -1; 
    private int swappedX = -1, swappedY = -1;
    private PartieController controller;
    private CandyFrame myframe;
    
	public CandyGrid(CandyFrame frm,PartieController controller) {
		   this.controller = controller;
		   this.myframe = frm;
		   partie  = new Partie(controller.getNiv());
		   partie.initialiser();
		   partie.fill();
		   partie.addObserver(frm.getLabel1());
		   partie.addObserver(frm.getLabel2());
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
	        return new Dimension(45 * 8 + 1, 45 * 8 + 1);
	    }
	
    public void paint(Graphics g2) {
    		if(buffer == null)
			buffer = new BufferedImage(400,400,BufferedImage.BITMASK);
    		Graphics2D	g = (Graphics2D) buffer.getGraphics();

        // fond
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());

        // afficher la grille vide
        g.setColor(Color.BLACK);
        for(int i = 0; i < 9; i++) {
            g.drawLine(45 * i, 0, 45 * i, 8 * 45 + 1); 
            g.drawLine(0, 45 * i, 8 * 45 + 1, 45 * i); 
        }

        // afficher la premire case sŽlectionnŽe
        if(selectedX != -1 && selectedY != -1) {
            g.setColor(Color.ORANGE);
            g.fillRect(selectedX * 45 + 1, selectedY * 45 + 1, 44, 44);
        }

        // afficher la deuxime case sŽlectionnŽe
        if(swappedX != -1 && swappedY != -1) {
            g.setColor(Color.YELLOW);
            g.fillRect(swappedX * 45 + 1, swappedY * 45 + 1, 44, 44);
        }

        // afficher le contenu de la grille
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
            	try {
					icon = ImageIO.read(getClass().getResourceAsStream("/resources/"+String.valueOf(partie.getGrid()[i][j].getColor())+".png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	switch(partie.getGrid()[i][j].getColor()){
            	case 0: g.setColor(Color.red);  g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null); break;
            	case 1: g.setColor(Color.ORANGE); g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null); break;
            	case 2: g.setColor(Color.YELLOW); g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null);   break;
            	case 3: g.setColor(Color.GREEN); g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null);  break;
            	case 4: g.setColor(Color.BLUE); g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null);  break;
            	case 5: g.setColor(Color.PINK); g.drawImage(icon, 45 * i + 3, 45 * j + 3, 40, 44 ,null); break;
            	default :g.setColor(Color.BLACK);  break;
            	}
            }
        }

        // copier l'image ˆ l'Žcran
        g2.drawImage(buffer, 0, 0, null);
    }
    
    public void update(Graphics g) {
    	
        paint(g);
    }
    
    boolean isValidSwap(int x1, int y1, int x2, int y2) {
        // il faut que les cases soient dans la grille
        if(x1 == -1 || x2 == -1 || y1 == -1 || y2 == -1) return false;
        // que les cases soient ˆ c™tŽ l'une de l'autre
        if(Math.abs(x2 - x1) + Math.abs(y2 - y1) != 1) return false;
        // et que les couleurs soient diffŽrentes
        if(partie.getGrid()[x1][y1].getColor() == partie.getGrid()[x2][y2].getColor()) return false;

        // alors on effectue l'Žchange
        partie.swap(x1, y1, x2, y2);

        // et on vŽrifie que a crŽŽ un nouvel alignement
        boolean newAlignment = false;
        for(int i = 0; i < 3; i++) {
            newAlignment |= partie.horizontalAligned(x1 - i, y1);
            newAlignment |= partie.horizontalAligned(x2 - i, y2);
            newAlignment |= partie.verticalAligned(x1, y1 - i);
            newAlignment |= partie.verticalAligned(x2, y2 - i);
        }

        // puis on annule l'Žchange
        partie.swap(x1, y1, x2, y2);

        return newAlignment;
    }
    
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		   // on bouge la souris : rŽcupŽrer les coordonnŽes de la deuxime case
        if(selectedX != -1 && selectedY != -1) {
            swappedX = e.getX() / 45;
            swappedY = e.getY() / 45;
            // si l'Žchange n'est pas valide, on cache la deuxime case
            if(!isValidSwap(selectedX, selectedY, swappedX, swappedY)) {
                swappedX = swappedY = -1;
            }
        }
        repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		  // on appuie sur le bouton de la souris : rŽcupŽrer les coordonnŽes de la premire case
        selectedX = e.getX() / 45;
        selectedY = e.getY() / 45;
        repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		 Bombom[][] grid = partie.getGrid();
		if(selectedX != -1 && selectedY != -1 && swappedX != -1 && swappedY != -1) {
            partie.swap(selectedX, selectedY, swappedX, swappedY);
        }
        selectedX = selectedY = swappedX = swappedY = -1;
        repaint();
        if(partie.isModfied(grid))
		{partie.setMouvement(partie.getMouvement()-1);}

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
		
		long x=-100000000;
		 while(true) {
	            // un pas de simulation toutes les 100ms
	            try { 
	            	Thread.currentThread();
					Thread.sleep(100);
	            	x++;
	            	if(x%10==0){
	            	partie.setMouvement(partie.getMouvement()-1);
	            	}
	            	
	    		} catch(InterruptedException e) { }

	            // s'il n'y a pas de case vide, chercher des alignements
	            if(!partie.fill()) {
	                partie.removeAlignments();
	                
	            }
	            if(partie.getMouvement()<1) break;
	            repaint();
	        }	
		  if(partie.getScore()>=partie.getNiveau().getObjectif()){
			  javax.swing.JOptionPane.showMessageDialog(null
					  ,"VOUS AVEZ GAGNER LA PARTIE (^_^) !","Partie Terminer",JOptionPane.INFORMATION_MESSAGE); 
			  
			  int index = partie.getNiveau().getNum();
			  Niveau niv = controller.getAllNiv().get(index);
			  niv.setEtat(true);
			  controller.getAllNiv().set(index,niv);
			   
		  }
			  
		  else {
			  	javax.swing.JOptionPane.showMessageDialog(null
			  			,"VOUS AVEZ PERDU (¡_¡) !","Partie Terminer",JOptionPane.INFORMATION_MESSAGE); 
		  		}
		  CandyNiveauFrame frame = new CandyNiveauFrame("Choisir un niveau pour jouer",controller);
		  frame.setVisible(true);
		  myframe.dispose();
	}
	
}
