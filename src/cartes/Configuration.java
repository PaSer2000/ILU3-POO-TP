package cartes;

public class Configuration {
    private int nombreExemplaires;
    private Carte carte;

    public Configuration(Carte carte, int nombreExemplaires) {
        this.carte = carte;
        this.nombreExemplaires = nombreExemplaires;
    }

    public Carte getType() {
        return carte;
    }

    public int getNbExemplaires() {
        return nombreExemplaires;
    }

    @Override
    public String toString() {
        return nombreExemplaires + " " + carte;
    }
}
