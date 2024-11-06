package jeu;

import cartes.*;

public class Coup {
	private Carte carte;
	private Joueur joueurCourant;
	private Joueur joueurCible;
	
	public Coup(Carte carte, Joueur joueurCourant, Joueur joueurCible) {
		this.carte = carte;
		this.joueurCourant = joueurCourant;
		this.joueurCible = joueurCible;
	}

	public Carte getCarte() {
		return carte;
	}

	public Joueur getJoueurCourant() {
		return joueurCourant;
	}

	public Joueur getJoueurCible() {
		return joueurCible;
	}
	
	public boolean estValide() {
		return (carte instanceof Attaque || carte instanceof DebutLimite);
	}

}
