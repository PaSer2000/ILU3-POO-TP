package testsFonctionnels;

import cartes.Configuration;

public class TestJeuDeCartes {

	public static void main(String[] args) {
        // Tableau de configurations de cartes
        Configuration[] configurations = {
            new Configuration("25KM", 10),
            new Configuration("50KM", 10),
            new Configuration("75KM", 10),
            new Configuration("100KM", 12),
            new Configuration("200KM", 4),
            new Configuration("Feu Vert", 14),
            new Configuration("Fin Limite", 6),
            new Configuration("Bidon d'essence", 6),
            new Configuration("Roue de secours", 6),
            new Configuration("Réparation", 6),
            new Configuration("Feu Rouge", 5),
            new Configuration("Limite 50", 4),
            new Configuration("Panne d'essence", 3),
            new Configuration("Crevaison", 3),
            new Configuration("Accident", 3),
            new Configuration("Prioritaire", 1),
            new Configuration("Citerne", 1),
            new Configuration("Increvable", 1),
            new Configuration("As du volant", 1)
        };

        // Affichage des cartes et de leur nombre d'exemplaires
        System.out.println("JEU :");
        for (Configuration config : configurations) {
            System.out.println(config.toString());
        }
	}
}
