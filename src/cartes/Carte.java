package cartes;

public abstract class Carte {
	 @Override
	 public boolean equals(Object obj) {
	    if (this == obj) return true; // Vérification de référence
	    if (obj == null || getClass() != obj.getClass()) return false; // Vérification de null et de type

	    Carte other = (Carte) obj; // Cast de l'objet

	    return this.toString().equals(other.toString()); // Comparaison des chaînes (ou des attributs spécifiques)
	 }
}
