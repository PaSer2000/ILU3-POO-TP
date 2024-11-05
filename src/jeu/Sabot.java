package jeu;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import cartes.Carte;

public class Sabot<T extends Carte> implements Iterable<T> {
    private Carte[] cartes;
    private int nbCartes = 0;
    private int nbModif = 0;

    public Sabot(Carte[] cartes) {
        this.cartes = cartes;
        this.nbCartes = cartes.length;
    }

	public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(Carte carte) throws IllegalStateException {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité maximale atteinte. Impossible d'ajouter une carte.");
        } else {
        	cartes[nbCartes] = carte;
        	nbCartes++;
        	nbModif++;
        }
    }

    public T piocher() throws NoSuchElementException {
    	
        if (estVide()) {
            throw new NoSuchElementException("Le sabot est vide, impossible de piocher.");
        }

        Iterator<T> it = iterator();
        T cartePiochee = it.next();
        it.remove();
		System.out.println(it.next());
    	nbModif++;
        return cartePiochee;
    }

    // Rendre classe itérable
    @Override
    public Iterator<T> iterator() {
        return new SabotIterator();
    }

    // Classe interne SabotIterator qui gère l'itération et les exceptions
    private class SabotIterator implements Iterator<T> {
        private int curseur = 0; // Pointeur sur la position actuelle
        private int nbModifAttendu = nbModif;
        private boolean canRemove = false; // Pour indiquer si remove() peut être appelé

        @Override
        public boolean hasNext() {
        	verificationConcurrence();
            return curseur < nbCartes;
        }

        @SuppressWarnings("unchecked")
		@Override
        public T next() throws ConcurrentModificationException, NoSuchElementException {
        	verificationConcurrence();
            if (!hasNext()) {
                throw new NoSuchElementException("Il n'y a plus de cartes dans le sabot.");
            }
       
            T carte = (T) cartes[curseur];
            curseur++;
            canRemove = true; // Permet de retirer la carte après l'appel de next()
            return carte;
        }

        @Override
        public void remove() throws IllegalStateException {
        	verificationConcurrence();
            if (!canRemove) {
                throw new IllegalStateException("Impossible de retirer une carte sans appel préalable à next().");
            }
            if (curseur <= 0 || curseur > nbCartes) {
                throw new IllegalStateException("Aucune carte à retirer.");
            }

            // Décalage des cartes restantes
            System.arraycopy(cartes, curseur, cartes, curseur - 1, nbCartes - curseur);
            cartes[--nbCartes] = null;
            curseur--; 
            nbModif++;
            nbModifAttendu++;
            canRemove = false;
        }
        
        
        public void verificationConcurrence() {
        	if (nbModif != nbModifAttendu) {
                throw new ConcurrentModificationException("Le sabot a été modifié pendant l'itération.");
            }
        }
    }

}
