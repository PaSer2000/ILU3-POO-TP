package testsFonctionnels;

import cartes.*;

public class TestMethodeEquals {
    public static void main(String[] args) {
        // Création de deux objets de la carte Borne avec 25KM
        Carte borne1 = new Borne(25);
        Carte borne2 = new Borne(25);
        
        // Création de deux objets de la carte Feu Rouge
        Carte feuRouge1 = new Attaque(Type.FEU); // Carte Feu Rouge
        Carte feuRouge2 = new Attaque(Type.FEU); // Carte Feu Rouge

        // Création d'une carte Feu Vert
        Carte feuVert = new Parade(Type.FEU); // Carte Feu Vert

        // Tests
        System.out.println("Deux cartes de 25KM sont identiques ? " + borne1.equals(borne2));
        System.out.println("Deux cartes de feu rouge sont identiques ? " + feuRouge1.equals(feuRouge2));
        System.out.println("La carte feu rouge et la carte feu vert sont identiques ? " + feuRouge1.equals(feuVert));
    }
}
