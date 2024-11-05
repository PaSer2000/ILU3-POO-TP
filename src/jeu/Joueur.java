package jeu;

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
    
    public Carte prendreCarte(Sabot sabot) {//<Carte>
        if (!sabot.estVide()) {
            Carte carte = sabot.piocher();
            donner(carte);
            return carte;
        } else {
            return null;
        }
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
