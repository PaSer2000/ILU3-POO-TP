package jeu;

import cartes.Carte;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainJoueur implements Iterable<Carte> {
    private List<Carte> cartesEnMain;

    public MainJoueur() {
        this.cartesEnMain = new ArrayList<>();
    }

    // Méthode pour ajouter une carte
    public void prendre(Carte carte) {
        cartesEnMain.add(carte);
        assert(cartesEnMain.contains(carte));
    }

    // Méthode pour jouer une carte (la retirer de la main)
    public void jouer(Carte carte) {
        assert (cartesEnMain.contains(carte));
        cartesEnMain.remove(carte);
    }

    // Méthode toString pour afficher la main du joueur
    @Override
    public String toString() {
        return cartesEnMain.toString();
    }

    // Itérateur pour permettre l'itération sur les cartes
    @Override
    public Iterator<Carte> iterator() {
        return cartesEnMain.iterator();
    }
}
