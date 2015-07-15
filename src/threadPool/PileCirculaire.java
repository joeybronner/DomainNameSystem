package threadPool;

public class PileCirculaire {

	private Noeud dernier;

	private Integer taille;

	public PileCirculaire() {
		dernier = null;
		taille = 0;
	}
	
	public Noeud RecupererDernier() {
		return dernier;
	}

	public void modifierDernier(Noeud dernier) {
		this.dernier = dernier;
	}

	public Integer recupererTaille() {
		return taille;
	}

	public void modifierTaille(Integer taille) {
		this.taille = taille;
	}

	/**
	 * Ajoute un noeud comme premier noeud si taille ==0 ajouter noeud comme dernier et denier.suivant sinon
	 * Ajouter dernier.suivant comme noeud.suivant et dernier.suivant comme noeud
	 * 
	 * @param noeud
	 */
	public void add(Noeud noeud) {
		if (taille == 0) {
			dernier = noeud;
			dernier.modifierSuivant(noeud);
		} else {
			noeud.modifierSuivant(dernier.recupererSuivant());
			dernier.modifierSuivant(noeud);
		}
		taille++;
	}

	/**
	 * Supprimmer le dernier Noeud
	 * 
	 * @return noeud
	 */
	public Noeud remove() {
		Noeud valeurRetour = null;
		
		if(taille > 0){
			if (taille > 1) 
			{
				Noeud current = dernier.recupererSuivant();
				while (current.recupererSuivant() != dernier) 
				{
					current = current.recupererSuivant();
				}
				current.modifierSuivant(dernier.recupererSuivant());
				valeurRetour = dernier;
				dernier = current;
			} else if(taille == 1) 
			{
				valeurRetour = dernier;
				dernier = null;
			}
			taille--;
		}
		
		return valeurRetour;
	}

	/**
	 * Affiche l'état de la pile
	 */
	public void Affficher() {
		if(taille > 0) {
			System.out.println("Stack size : " + taille);
			Noeud courrant = dernier.recupererSuivant();
			for(int i = 0; i < taille; i++) {
				System.out.println("\t" + i + " :::::: " + courrant.toString());
				courrant = courrant.recupererSuivant();
			}
		} else {
			System.out.println("La pile est vide !");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		if(taille > 0) {
			builder.append("Stack size : " + taille + "\n");
			Noeud courrant = dernier.recupererSuivant();
			for(int i = 0; i < taille; i++) {
				builder.append("\t" + i + " : " + courrant.toString() + "\n");
				courrant = courrant.recupererSuivant();
			}
		} else {
			builder.append("Stack is empty !" + "\n");
		}
		
		return builder.toString();
	}

}
