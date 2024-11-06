package jeu;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import cartes.Carte;

public class Joueur {
    private String nom;
    private ZoneDeJeu zoneDeJeu;
    private MainJoueur mainJoueur;

    public Joueur(String nom) {
        this.nom = nom;
        this.zoneDeJeu = new ZoneDeJeu();
        this.mainJoueur = new MainJoueur();
    }

    public String getNom() {
        return nom;
    }

    public ZoneDeJeu getZoneDeJeu() {
        return zoneDeJeu;
    }

    public MainJoueur getMainJoueur() {
        return mainJoueur;
    }

    public void donner(Carte carte) {
        mainJoueur.prendre(carte);
    }
    
    public void deposer(Carte carte) {
        zoneDeJeu.deposer(carte);
    }
    
    @SuppressWarnings("rawtypes")
	public Carte prendreCarte(Sabot sabot) {//<Carte>
        if (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            donner(carte);
            return carte;
        } else {
            return null;
        }
    }
    
	public Set<Coup> coupsPossibles(Set<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		for (Joueur participant : participants) {
			for (Carte carte : mainJoueur) {
				Coup coup = new Coup(carte, this, participant);
				if (coup.estValide()) coupsPossibles.add(coup);
			}
		}
		return coupsPossibles;
	}
  
	public Set<Coup> coupsDefausse(){
		Set<Coup> coupsDefausse = new HashSet<>();
		for (Carte carte : mainJoueur) {
			Coup coup = new Coup(carte, this, null);
			if (coup.estValide()) coupsDefausse.add(coup);
		}
		return coupsDefausse;
	}
	
	public void retirerDeLaMain(Carte carte) {
		mainJoueur.jouer(carte);
	}
	
	//FONCTION AUXILIAIRE POUR CHOISIR UN COUP ALEATOIRE
	private Coup randomCoupChoisi(Set<Coup> coups) {
		Iterator<Coup> iter = coups.iterator();
		Random random = new Random();
		Coup next = null;
		int n = random.nextInt(coups.size());
		for (int i = 0; i < n; i++) {
			next = iter.next();
			if (i == n) {
				break;
			}
		}
		
		return next;
	}
	
	public Coup choisirCoup(Set<Joueur> participants) {
		Set<Coup> coupsPossibles = coupsPossibles(participants);
		
		if (coupsPossibles.isEmpty())
			return randomCoupChoisi(coupsDefausse());
		else 
			return randomCoupChoisi(coupsPossibles);
	}
	
    // Redéfinir equals pour comparer les joueurs par nom
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Joueur) {
        	Joueur autreJoueur = (Joueur) obj;
        	return nom.equals(autreJoueur.nom);
        }
        return false;
    }

    // Redéfinir toString pour retourner le nom du joueur
    @Override
    public String toString() {
        return nom;
    }
}
