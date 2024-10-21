package jeu;

import cartes.Carte;
import cartes.JeuDeCartes;
import utils.GestionCartes;
import java.util.Arrays;
import java.util.List;

public class Jeu {
    private Sabot<Carte> sabot;

	public Jeu() {
        // Récupère le tableau de cartes depuis JeuDeCartes
        JeuDeCartes jeuDeCartes = new JeuDeCartes();
        Carte[] tableauDeCartes = jeuDeCartes.donnerCartes();
        
        // Transformation en liste et mélange
        List<Carte> listeCartes = Arrays.asList(tableauDeCartes);
        listeCartes = GestionCartes.melanger(listeCartes);
        
        // Transformation en tableau
        Carte[] cartesMelangees = listeCartes.toArray(new Carte[0]);
        
        // Création du sabot avec le tableau de cartes mélangées
        this.sabot = new Sabot<>(cartesMelangees);
    }

    public Sabot<Carte> getSabot() {
        return sabot;
    }
}

