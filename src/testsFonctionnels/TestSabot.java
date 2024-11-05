package testsFonctionnels;

import jeu.Sabot;
import java.util.NoSuchElementException;

import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.Type;
import cartes.JeuDeCartes;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class TestSabot {

    @SuppressWarnings({ })
	public static void main(String[] args) {
        // a. Créer un sabot avec des cartes et utiliser la méthode piocher
        System.out.println("=== Test avec piocher ===");
        
        JeuDeCartes jeu = new JeuDeCartes();
        Carte[] cartesInitiales = jeu.donnerCartes();

		Sabot<Carte> sabot = new Sabot<>(cartesInitiales);

        try {
            while (!sabot.estVide()) {
                Carte carte = sabot.piocher();
                System.out.println("Je pioche " + carte);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Le sabot est vide.");
        }

        // b. Utiliser un itérateur et remove pour obtenir le même résultat
        System.out.println("\n=== Test avec l'itérateur et remove ===");
        sabot.ajouterCarte(new Borne(25));
        sabot.ajouterCarte(new Borne(50));
        sabot.ajouterCarte(new Borne(75));

        try {
            Iterator<Carte> it = sabot.iterator();
            while (it.hasNext()) {
            	Carte carte = (Carte) it.next();
                System.out.println("Je pioche " + carte.toString());
                it.remove(); // Supprimer la carte après l'avoir piochée
            }
        } catch (IllegalStateException e) {
            System.err.println("Erreur lors de la suppression de la carte.");
        } catch (ConcurrentModificationException e) {
            System.err.println("Modification concurrente détectée.");
        }

        // c. Insérer des appels à piocher et une exception avec ajout de carte
        System.out.println("\n=== Test avec exception sur piocher et ajout de carte ===");
        sabot.ajouterCarte(new Borne(25));
        sabot.ajouterCarte(new Borne(50));
        sabot.ajouterCarte(new Borne(50));


        try {
            sabot.piocher(); // Pioche une carte pour éviter le débordement
            var it = sabot.iterator();
            while (it.hasNext()) {
            	Carte carte = (Carte) it.next();
                System.out.println("Je pioche " + carte.toString());
                sabot.ajouterCarte(new Botte(Type.ACCIDENT)); // Dépassement de capacité
                sabot.piocher();
            }
        } catch (IllegalStateException e) {
            System.err.println("Exception levée : " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Le sabot est vide.");
        } catch (ConcurrentModificationException e) {
            System.err.println("Modification concurrente détectée.");
        }
    }

}
