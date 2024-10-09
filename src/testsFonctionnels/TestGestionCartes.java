package testsFonctionnels;

import cartes.JeuDeCartes;
import cartes.Carte;
import utils.GestionCartes;
import java.util.*;

public class TestGestionCartes {
    public static void main(String[] args) {
        JeuDeCartes jeu = new JeuDeCartes();
        List<Carte> listeCarteNonMelangee = new LinkedList<>();
        for (Carte carte : jeu.donnerCartes()) {
            listeCarteNonMelangee.add(carte);
        }

        // Test de mélange
        List<Carte> listeCartes = new ArrayList<>(listeCarteNonMelangee);
        System.out.println(listeCartes);
        listeCartes = GestionCartes.melanger(listeCartes);
        System.out.println(listeCartes);
        System.out.println("liste mélangée sans erreur ? " + 
            GestionCartes.verifierMelange(listeCarteNonMelangee, listeCartes));

        // Test de rassemblement
        listeCartes = GestionCartes.rassembler(listeCartes);
        System.out.println(listeCartes);
        System.out.println("liste rassemblée sans erreur ? " + 
            GestionCartes.verifierRassemblement(listeCartes));
    }
}
