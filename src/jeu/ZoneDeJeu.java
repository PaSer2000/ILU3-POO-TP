package jeu;

import cartes.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ZoneDeJeu {
    private List<Limite> pileLimites;
    private List<Bataille> pileBataille;
    private List<Borne> bornes;

    public ZoneDeJeu() {
        this.pileLimites = new ArrayList<>();
        this.pileBataille = new ArrayList<>();
        this.bornes = new ArrayList<>();
    }

    // Méthodes pour gérer les piles et les bornes
    public void ajouterLimite(Limite carte) {
        pileLimites.add(carte);
    }

    public void ajouterBataille(Bataille carte) {
        pileBataille.add(carte);
    }

    public void ajouterBorne(Borne carte) {
        bornes.add(carte);
    }

    public List<Limite> getPileLimites() {
        return pileLimites;
    }

    public List<Bataille> getPileBataille() {
        return pileBataille;
    }

    public List<Borne> getBornes() {
        return bornes;
    }
    
    public int donnerLimitationVitesse() {
		int limite = 200;
		if (pileLimites.isEmpty() 
				|| pileLimites.get(0) instanceof FinLimite) 
			return limite;
		else
			return 50;
	}
    
    public int donnerKmParcourus() {
        int totalKm = 0;
        for (Borne borne : bornes) {
            // On suppose que la carte a une méthode getValeur() qui retourne la valeur en km
            totalKm += borne.getKm();
        }
        return totalKm;
    }
    
    public void deposer(Carte carte) {
        if (carte instanceof Borne) {
            bornes.add((Borne) carte);
        } else if (carte instanceof Limite) {
            pileLimites.add((Limite) carte);
        } else if (carte instanceof Attaque 
        		|| carte instanceof Parade 
        		|| carte instanceof Botte) {
            pileBataille.add((Bataille) carte);
        } else {
            throw new IllegalArgumentException("Type de carte non existant");
        }
    }
    
    public boolean peutAvancer() {
		return (!pileBataille.isEmpty() && pileBataille.get(0).equals(Cartes.FEU_VERT));
    }
	
	private boolean estDepotFeuVertAutorise() {
		return (pileBataille.isEmpty() 
				|| pileBataille.get(0).equals(Cartes.FEU_ROUGE)
				|| pileBataille.get(0) instanceof Parade && !pileBataille.get(0).equals(Cartes.FEU_VERT));
	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
				&& borne.getKm() <= donnerLimitationVitesse()
				&& donnerKmParcourus() + borne.getKm() <= 1000;		
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (limite instanceof DebutLimite 
				&& (pileLimites.isEmpty() 
						|| pileLimites.get(0) instanceof FinLimite)) return true;
		else if (limite instanceof FinLimite 
				&& pileLimites.get(0) instanceof DebutLimite) return true;
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque 
				&& peutAvancer()) return true;
		else if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) return estDepotFeuVertAutorise();
			else if (!pileBataille.isEmpty()
					&& pileBataille.get(0) instanceof Attaque
					&& pileBataille.get(0).getClass().equals(bataille.getClass())) return true;
		}
		return false;
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
		if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
		if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
		return false;	
	}
}
