package model;

public class Niveau {

	private int num;
	private int time;
	private int objectif;
	
	private boolean etat;
	
	public Niveau() {
		// TODO Auto-generated constructor stub
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getObjectif() {
		return objectif;
	}

	public void setObjectif(int objectif) {
		this.objectif = objectif;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Niveau(int num, int time, int objectif, boolean etat) {
		super();
		this.num = num;
		this.time = time;
		this.objectif = objectif;
		this.etat = etat;
	}
	
	
}
