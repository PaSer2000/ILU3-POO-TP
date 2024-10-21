package testsFonctionnels;

import jeu.Sabot;
import java.util.NoSuchElementException;

import cartes.Carte;

import java.util.ConcurrentModificationException;

public class TestSabot {

    public static void main(String[] args) {
        // a. Créer un sabot avec des cartes et utiliser la méthode piocher
        System.out.println("=== Test avec piocher ===");

		Sabot<Carte> sabot = new Sabot<>();
        sabot.ajouterCarte(new Carte("25KM"));
        sabot.ajouterCarte("50KM");
        sabot.ajouterCarte("75KM");

        try {
            while (!sabot.estVide()) {
                String carte = sabot.piocher();
                System.out.println("Je pioche " + carte);
            }
        } catch (NoSuchElementException e) {
            System.err.println("Le sabot est vide.");
        }

        // b. Utiliser un itérateur et remove pour obtenir le même résultat
        System.out.println("\n=== Test avec l'itérateur et remove ===");
        sabot.ajouterCarte("25KM");
        sabot.ajouterCarte("50KM");
        sabot.ajouterCarte("75KM");

        try {
            var it = sabot.iterator();
            while (it.hasNext()) {
            	Carte carte = it.next();
                System.out.println("Je pioche " + carte);
                it.remove(); // Supprimer la carte après l'avoir piochée
            }
        } catch (IllegalStateException e) {
            System.err.println("Erreur lors de la suppression de la carte.");
        } catch (ConcurrentModificationException e) {
            System.err.println("Modification concurrente détectée.");
        }

        // c. Insérer des appels à piocher et une exception avec ajout de carte
        System.out.println("\n=== Test avec exception sur piocher et ajout de carte ===");
        sabot.ajouterCarte("25KM");
        sabot.ajouterCarte("50KM");

        try {
            sabot.piocher(); // Pioche une carte pour éviter le débordement
            var it = sabot.iterator();
            while (it.hasNext()) {
            	Carte carte = it.next();
                System.out.println("Je pioche " + carte);
                sabot.ajouterCarte("As du Volant"); // Dépassement de capacité
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
