package threadPool;

import java.util.List;
import java.util.ArrayList;

public class Pool {

	private Object moniteur;
	private List<Travailleur> lesTravailleurs = new ArrayList<Travailleur>();
	private Integer numeroTravailleur = Runtime.getRuntime().availableProcessors();
	private PileCirculaire pile = new PileCirculaire();
	
	public Object recupereMonitor() {
		return moniteur;
	}
	public void setMonitor(Object moniteur) {
		this.moniteur = moniteur;
	}

	public Integer recupererNumeroTravailleur() {
		return numeroTravailleur;
	}
	public void modifierNumeroTravailleur(Integer numeroTravailleur) {
		this.numeroTravailleur = numeroTravailleur;
	}

	public List<Travailleur> recupererLesTravailleurs() {
		return lesTravailleurs;
	}
	public void modifierLesTravailleurs(List<Travailleur> lesTravailleurs) {
		this.lesTravailleurs = lesTravailleurs;
	}

	public PileCirculaire recupererPile() {
		return pile;
	}
	public void setStack(PileCirculaire pile) {
		this.pile = pile;
	}
	
	public Pool()
	{
		init();
	}
	
	private void init()
	{
		moniteur = new Object();
		for(int i = 0; i < numeroTravailleur; i++) {
			lesTravailleurs.add(new Travailleur(this, i));
			//lance le Thread
			lesTravailleurs.get(i).start();
		}
	}
	
	public Runnable prochaineTache() {
		synchronized (moniteur) {
			
			try {
				if(pile.recupererTaille() == 0) {
					moniteur.wait();
				}
			} catch (InterruptedException e) {
				System.out.println(String.format("{0}", e));
				return null;
			}
			
			return this.pile.remove().recupererTache();
		}
	}
	
	public void ajouterTache(Runnable tache) {
		synchronized (moniteur) {
			this.pile.add(new Noeud(tache));
			moniteur.notify();
		}
	}
	
	
	
}
