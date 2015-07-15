package threadPool;

public class Noeud {
	
	private Runnable tache;
	private Noeud suivant;
	
	public Noeud recupererSuivant() {
		return suivant;
	}

	public void modifierSuivant(Noeud suivant) {
		this.suivant = suivant;
	}
	
	public Runnable recupererTache() {
		return tache;
	}

	public void modifierJob(Runnable tache) {
		this.tache = tache;
	}
	
	
	public Noeud(Runnable tache) {
		this.tache = tache;
	}
	
	public Noeud(Runnable tache, Noeud suivant) {
		this.tache = tache;
		this.suivant = suivant;
	}

}
