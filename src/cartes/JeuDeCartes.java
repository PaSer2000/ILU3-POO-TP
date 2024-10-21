package cartes;
import java.util.HashMap;
import java.util.Map;

public class JeuDeCartes {
    private final Configuration[] configurations = new Configuration[19];

    public JeuDeCartes() {
        configurations[0] = new Configuration(new Borne(25), 10);
        configurations[1] = new Configuration(new Borne(50), 10);
        configurations[2] = new Configuration(new Borne(75), 10);
        configurations[3] = new Configuration(new Borne(100), 12);
        configurations[4] = new Configuration(new Borne(200), 4);
        configurations[5] = new Configuration(new Parade(Type.FEU), 14);
        configurations[6] = new Configuration(new FinLimite(), 6);
        configurations[7] = new Configuration(new Parade(Type.ESSENCE), 6);
        configurations[8] = new Configuration(new Parade(Type.CREVAISON), 6);
        configurations[9] = new Configuration(new Parade(Type.ACCIDENT), 6);
        configurations[10] = new Configuration(new Attaque(Type.FEU), 5);
        configurations[11] = new Configuration(new DebutLimite(), 4);
        configurations[12] = new Configuration(new Attaque(Type.ESSENCE), 3);
        configurations[13] = new Configuration(new Attaque(Type.CREVAISON), 3);
        configurations[14] = new Configuration(new Attaque(Type.ACCIDENT), 3);
        configurations[15] = new Configuration(new Botte(Type.FEU), 1);
        configurations[16] = new Configuration(new Botte(Type.ESSENCE), 1);
        configurations[17] = new Configuration(new Botte(Type.CREVAISON), 1);
        configurations[18] = new Configuration(new Botte(Type.ACCIDENT), 1);
    }

    public Carte[] donnerCartes() {
        int totalCartes = 0;
        for (Configuration config : configurations) {
            totalCartes += config.getNbExemplaires();
        }

        Carte[] cartesTab = new Carte[totalCartes];
        int index = 0;
        for (Configuration config : configurations) {
            for (int i = 0; i < config.getNbExemplaires(); i++) {
            	cartesTab[index++] = config.getType();
            }
        }
        return cartesTab;
    }

    public void affichageJeuDeCartes() {
        System.out.println("JEU :");
        for (Configuration config : configurations) {
            System.out.println(config.getNbExemplaires() + " " + config.getType());
        }
    }
    
    public boolean checkCount() {
        Carte[] cartes = donnerCartes();
        Map<Carte, Integer> countMap = new HashMap<>();

        // Compter les occurrences de chaque carte dans le tableau donné
        for (Carte carte : cartes) {
            countMap.put(carte, countMap.getOrDefault(carte, 0) + 1);
        }

        // Vérifier que chaque carte dans la configuration a le bon nombre d'occurrences
        for (Configuration config : configurations) {
            Carte carte = config.getType();
            int expectedCount = config.getNbExemplaires();
            int actualCount = countMap.getOrDefault(carte, 0);

            if (actualCount != expectedCount) {
                System.out.println("Erreur : La carte " + carte + " a " + actualCount +
                                   " occurrences au lieu de " + expectedCount);
                return false;
            }
        }

        System.out.println("Toutes les cartes sont conformes à la configuration.");
        return true;
    }
}

