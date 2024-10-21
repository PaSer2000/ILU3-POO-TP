package jeu;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jeu {
    @SuppressWarnings("unused")
	private Sabot<Carte> sabot;

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

}

