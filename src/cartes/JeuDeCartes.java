package cartes;

public class JeuDeCartes {
	private Configuration[] configurations;

    public void affichageJeuDeCartes() {
        System.out.println("JEU :");
        for (Configuration config : configurations) {
            System.out.println(config.toString());
        }
    }
}
