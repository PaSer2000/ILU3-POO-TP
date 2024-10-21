package jeu;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class Sabot<T> implements Iterable<T> {
    private T[] cartes;
    private int nbCartes;
    private int modCount; // Compteur pour gérer la modification concurrente

    // Constructeur sabot avec capacité maximale init
    @SuppressWarnings("unchecked")
    public Sabot(T[] cartes) {
        this.cartes = (T[]) new Object[cartes.length];
        this.nbCartes = cartes.length;
        this.modCount = 0;

        // Mélanger les cartes
        List<T> listeCartes = Arrays.asList(cartes);
        this.cartes = listeCartes.toArray(this.cartes);
    }

    public boolean estVide() {
        return nbCartes == 0;
    }

    public void ajouterCarte(T carte) throws IllegalStateException {
        if (nbCartes >= cartes.length) {
            throw new IllegalStateException("Capacité maximale atteinte. Impossible d'ajouter une carte.");
        }
        cartes[nbCartes] = carte;
        nbCartes++;
        modCount++;
    }

    public T piocher() throws NoSuchElementException {
        if (estVide()) {
            throw new NoSuchElementException("Le sabot est vide, impossible de piocher.");
        }

        Iterator<T> it = iterator();
        T cartePiochee = it.next(); // Récupérer la première carte via l'itérateur
        it.remove(); // Supprimer la carte piochée
        return cartePiochee;
    }

    // Rendre classe itérable
    @Override
    public Iterator<T> iterator() {
        return new SabotIterator();
    }

    // Classe interne SabotIterator qui gère l'itération et les exceptions
    private class SabotIterator implements Iterator<T> {
        private int cursor = 0; // Pointeur sur la position actuelle
        private int expectedModCount = modCount; // Pour gérer la modification concurrente
        private boolean canRemove = false; // Pour indiquer si remove() peut être appelé

        @Override
        public boolean hasNext() {
            return cursor < nbCartes;
        }

        @Override
        public T next() throws ConcurrentModificationException, NoSuchElementException {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("Le sabot a été modifié pendant l'itération.");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("Il n'y a plus de cartes dans le sabot.");
            }

            canRemove = true; // Permet de retirer la carte après l'appel de next()
            return cartes[cursor++];
        }

        @Override
        public void remove() throws IllegalStateException {
            if (!canRemove) {
                throw new IllegalStateException("Impossible de retirer une carte sans appel préalable à next().");
            }
            if (cursor <= 0 || cursor > nbCartes) {
                throw new IllegalStateException("Aucune carte à retirer.");
            }

            // Décalage des cartes restantes
            System.arraycopy(cartes, cursor, cartes, cursor - 1, nbCartes - cursor);
            cartes[--nbCartes] = null; // Réduire la taille et nettoyer la dernière position
            cursor--; // Réajuster le curseur après le retrait
            modCount++; // Incrémenter pour indiquer une modification
            expectedModCount = modCount; // Mettre à jour l'itérateur avec le nouveau modCount
            canRemove = false; // Reset pour empêcher de retirer plusieurs fois sans next()
        }
    }
}
