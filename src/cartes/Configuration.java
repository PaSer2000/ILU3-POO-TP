package cartes;

public class Configuration {
    private String nomCarte;
    private int nombreExemplaires;

    public Configuration(String nomCarte, int nombreExemplaires) {
        this.nomCarte = nomCarte;
        this.nombreExemplaires = nombreExemplaires;
    }

    public String getCarte() {
        return nomCarte;
    }

    public int getNbExemplaires() {
        return nombreExemplaires;
    }

    @Override
    public String toString() {
        return nombreExemplaires + " " + nomCarte;
    }
}
