package model;

import java.util.Observable;
import java.util.Random;


public class Partie extends Observable{
		
	private boolean marked[][] ;
	private Niveau niveau;
	private int score;
	private int time=60;
	private Bombom[][] grid ;
		
	public Partie(Niveau niv) {
			niveau = niv;
			grid= new Bombom[8][8];
			marked= new boolean[8][8];
		}
	
	
		
	public int getMouvement() {
		return time;
	}

	public boolean isModfied( Bombom[][] g){
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(g[i][j].getColor()==grid[i][j].getColor()){ continue;}
				else return true;
			}
		}
		return false;
	}

	public void setMouvement(int t) {
		this.time = t;
		setChanged();
	    notifyObservers();
	}



	public boolean[][] getMarked() {
		return marked;
	}

	public void setMarked(boolean[][] marked) {
		this.marked = marked;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		setChanged();
	    notifyObservers();
	}

	public Bombom[][] getGrid() {
		return grid;
	}

	public void setGrid(Bombom[][] grid) {
		this.grid = grid;
	}

	public void initialiser(){
		Bombom bombom = new Bombom();
		for(int i=0;i<8;i++){
			for(int j=0;j<8 ; j++){
				bombom = new Bombom();
				bombom.setColor(-1);
				bombom.setPositionX(i);
				bombom.setPositionY(j);
				grid[i][j] = bombom;
			}
		}
	}

	public boolean fill() {
        Random rand = new Random();
        boolean modified = false;
        for(int i = 0; i < 8; i++) {
            for(int j = 7; j >= 0; j--) {
                if(grid[i][j].getColor() == -1) {
                    if(j == 0) grid[i][j].setColor(Math.abs(rand.nextInt()%6));
                    else {
                        grid[i][j].setColor(grid[i][j - 1].getColor());
                        grid[i][j - 1].setColor(-1);
                    }
                    modified = true;
                }
            }
        }
        return modified;
    }

    public boolean horizontalAligned(int i, int j) {
        if(i < 0 || j < 0 || i >= 6 || j >= 8) return false;
        if(grid[i][j].getColor() == grid[i + 1][j].getColor() && grid[i][j].getColor() == grid[i + 2][j].getColor()) 
        	return true;
        return false;
    }

    public boolean verticalAligned(int i, int j) {
           if(i < 0 || j < 0 || i >= 8 || j >= 6) return false;
           if(grid[i][j].getColor() == grid[i][j + 1].getColor() && grid[i][j].getColor() == grid[i][j + 2].getColor()) 
           	return true;
           return false;
       }
    
    public boolean removeAlignments() {
        // passe 1 : marquer tous les alignements
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(grid[i][j].getColor() != -1 && horizontalAligned(i, j)) {
                    marked[i][j] = marked[i + 1][j] = marked[i + 2][j] = true;
                }
                if(grid[i][j].getColor() != -1 && verticalAligned(i, j)) {
                    marked[i][j] = marked[i][j + 1] = marked[i][j + 2] = true;
                }
            }
        }
     // passe 2 : supprimer les cases marquŽes
        boolean modified = false;
        int som=0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(marked[i][j]) {
                    grid[i][j].setColor(-1);
                    marked[i][j] = false;
                    modified = true;
                    som++;
                }
            }
            setScore(score+som*som); 
         
          
        }

        return modified;
    }

    
    public Niveau getNiveau() {
		return niveau;
	}



	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}



	public void swap(int x1, int y1, int x2, int y2) {
        int tmp = grid[x1][y1].getColor();
        grid[x1][y1].setColor(grid[x2][y2].getColor());
        grid[x2][y2].setColor(tmp);
    }
    
}
