package jeu;

import cartes.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZoneDeJeu {
    private List<Limite> pileLimites;
    private List<Bataille> pileBataille;
    private List<Borne> pileBornes;
	private Set<Botte> pileBottes;

    public ZoneDeJeu() {
        this.pileLimites = new ArrayList<>();
        this.pileBataille = new ArrayList<>();
        this.pileBornes = new ArrayList<>();
		this.pileBottes = new HashSet<>();
    }

    // Méthodes pour gérer les piles et les bornes
    public void ajouterLimite(Limite carte) {
        pileLimites.add(carte);
    }

    public void ajouterBataille(Bataille carte) {
        pileBataille.add(carte);
    }

    public void ajouterBorne(Borne carte) {
    	pileBornes.add(carte);
    }
    
    public void ajouterBote(Botte carte) {
    	pileBottes.add(carte);
    }

    public List<Limite> getPileLimites() {
        return pileLimites;
    }

    public List<Bataille> getPileBataille() {
        return pileBataille;
    }

    public List<Borne> getBornes() {
        return pileBornes;
    }
    
    public Set<Botte> getBottes() {
    	return pileBottes;
    }
    
    public int donnerLimitationVitesse() {
		int limite = 200;
		if (pileLimites.isEmpty() 
				|| pileLimites.get(0) instanceof FinLimite
			    || pileBottes.contains(new Botte(Type.FEU)))
			return limite;
		else
			return 50;
	}
    
    public int donnerKmParcourus() {
        int totalKm = 0;
        for (Borne borne : pileBornes) {
            totalKm += borne.getKm();
        }
        return totalKm;
    }
    
    public void deposer(Carte carte) {
    	
        if (carte instanceof Borne) {
        	pileBornes.add((Borne) carte);
        } else if (carte instanceof Limite) {
            pileLimites.add((Limite) carte);
        } else if (carte instanceof Bataille) {
        	pileBataille.add((Bataille) carte);
        } else if (carte instanceof Botte){
        	pileBottes.add((Botte) carte);
        } else {
            throw new IllegalArgumentException("Type de carte non existant");
        }
    }
   
	public boolean estPrioritaire() {
		return pileBottes.contains(Cartes.PRIORITAIRE);
	}
    
    public boolean peutAvancer() {
		if (pileBataille.isEmpty() && estPrioritaire()) return true;
		
		if (!pileBataille.isEmpty()) {
			
			Bataille first = pileBataille.get(0);
			if (first.equals(Cartes.FEU_VERT)) return true;
			if (first instanceof Parade && estPrioritaire()) return true;
			if (first.equals(new Attaque(Type.FEU)) && estPrioritaire()) return true;
			
			if (first instanceof Attaque && !first.equals(new Attaque(Type.FEU))) {
				Type firstType = first.getType();
				Botte botte = new Botte(firstType);
				if (pileBottes.contains(botte) && estPrioritaire()) return true;
			}
		}
		return false;
	}
    
	private boolean estDepotFeuVertAutorise() {
		if (estPrioritaire()) return false;
		
		if (pileBataille.isEmpty()) return true;
		
		Bataille first = pileBataille.get(0);
		if (first.equals(Cartes.FEU_ROUGE)) return true;
		else if (first instanceof Parade && !first.equals(Cartes.FEU_VERT)) return true;
		else if (first instanceof Attaque) {
			Type firstType = first.getType();
			if (pileBottes.contains(new Botte(firstType))) return true;
		}
		
		return false;
	}
    
    
//	private boolean estDepotFeuVertAutorise() {
//		return (pileBataille.isEmpty() 
//				|| pileBataille.get(0).equals(Cartes.FEU_ROUGE)
//				|| pileBataille.get(0) instanceof Parade && !pileBataille.get(0).equals(Cartes.FEU_VERT));
//	}
	
	private boolean estDepotBorneAutorise(Borne borne) {
		return peutAvancer()
				&& borne.getKm() <= donnerLimitationVitesse()
				&& donnerKmParcourus() + borne.getKm() <= 1000;		
	}
	
	private boolean estDepotLimiteAutorise(Limite limite) {
		if (pileBottes.contains(Cartes.PRIORITAIRE) 
				|| estPrioritaire()) return false;
		else if (limite instanceof DebutLimite && (pileLimites.isEmpty() || pileLimites.get(0) instanceof FinLimite)) return true;
		else if (limite instanceof FinLimite && pileLimites.get(0) instanceof DebutLimite) return true;
		return false;
	}

	private boolean estDepotBatailleAutorise(Bataille bataille) {
		if (bataille instanceof Attaque && peutAvancer()) {
			return (pileBataille.isEmpty() || !pileBataille.get(0).equals(bataille));
		}
		else if (bataille instanceof Parade) {
			if (bataille.equals(Cartes.FEU_VERT)) {
				return estDepotFeuVertAutorise();
			} else if (!pileBataille.isEmpty() &&
						pileBataille.get(0) instanceof Attaque &&
						pileBataille.get(0).getClass().equals(bataille.getClass())) 
				return true;
		}
		Type type = bataille.getType();
		if (pileBottes.contains(new Botte(type))) return false;
		return false;
	}

	public boolean estDepotAutorise(Carte carte) {
		if (carte instanceof Borne borne) return estDepotBorneAutorise(borne);
		if (carte instanceof Limite limite) return estDepotLimiteAutorise(limite);
		if (carte instanceof Bataille bataille) return estDepotBatailleAutorise(bataille);
		if (carte instanceof Botte && !pileBottes.contains(carte)) return true;
		return false;	
	}
}
