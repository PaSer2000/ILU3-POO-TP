package jeu;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Jeu {
    @SuppressWarnings("unused")
	private Sabot<Carte> sabot;
	private Set<Joueur> joueurs;

	public Jeu() {
        // Récupère le tableau de cartes depuis JeuDeCartes
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tabDeCartes = jeuDeCartes.donnerCartes();
        
        // Transformation en liste et mélange
        List<Carte> listeCartes = new ArrayList<>();
        Collections.addAll(listeCartes, tabDeCartes);
        listeCartes = GestionCartes.melanger(listeCartes);
        
        // Transformation en tableau
        Carte[] cartesMelangees = (Carte[]) listeCartes.toArray();
        
        // Création du sabot avec le tableau de cartes mélangées
        this.sabot = new Sabot<>(cartesMelangees);
    }
	
	public void inscrire(Joueur... joueur) {
		Collections.addAll(this.joueurs, joueur);
	}
	
	public void distribuerCartes() {
		for (Joueur joueur : joueurs) {
			for (int i = 0; i <= 6; i++) {
				joueur.donner(sabot.piocher());
			}
		}
	}

}

