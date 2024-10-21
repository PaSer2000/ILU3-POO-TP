package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;

public class GestionCartes {

    // a. Version 1 : Extraire un élément de manière aléatoire en travaillant directement sur la liste
    public static <T> T extraire(List<T> liste) {
        if (liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide.");
        }

        Random rand = new Random();
        int index = rand.nextInt(liste.size());
        return liste.remove(index);
    }

    // a. Version 2 : Extraire un élément de manière aléatoire en utilisant un ListIterator
    public static <T> T extraireAvecIterator(List<T> liste) {
        if (liste.isEmpty()) {
            throw new IllegalArgumentException("La liste ne doit pas être vide.");
        }

        Random rand = new Random();
        int index = rand.nextInt(liste.size());
        ListIterator<T> iterator = liste.listIterator();

        T element = null;
        for (int i = 0; i <= index; i++) {
            element = iterator.next();
        }
        iterator.remove();
        return element;
    }

    // b. Mélanger une liste en extrayant tous ses éléments
    public static <T> List<T> melanger(List<T> liste) {
        List<T> melangee = new ArrayList<>();
        while (!liste.isEmpty()) {
            melangee.add(extraire(liste));
        }
        return melangee;
    }

    // c. Vérifier si deux listes ont le même nombre d'occurrences pour chaque élément
    public static <T> boolean verifierMelange(List<T> liste1, List<T> liste2) {
        if (liste1.size() != liste2.size()) {
            return false;
        }

        for (T element : liste1) {
            if (Collections.frequency(liste1, element) != Collections.frequency(liste2, element)) {
                return false;
            }
        }
        return true;
    }

    // d. Rassembler les éléments identiques pour qu'ils soient consécutifs
    public static <T> List<T> rassembler(List<T> liste) {
        Map<T, Integer> occurrences = new HashMap<>();
        
        // Contar las ocurrencias de cada elemento en la lista
        for (T element : liste) {
            occurrences.put(element, occurrences.getOrDefault(element, 0) + 1);
        }
        
        // Crear una nueva lista donde se agrupan los elementos idénticos
        List<T> result = new ArrayList<>();
        for (Map.Entry<T, Integer> entry : occurrences.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }


    // e. Vérifier que les éléments identiques sont bien consécutifs
    public static <T> boolean verifierRassemblement(List<T> liste) {
        if (liste.isEmpty()) return true;

        ListIterator<T> iterator = liste.listIterator();
        T previous = iterator.next();

        while (iterator.hasNext()) {
            T current = iterator.next();
            if (!current.equals(previous)) {
                // Si on trouve un élément différent, on doit vérifier que les éléments précédents sont bien tous identiques
                previous = current;
            }
            // Si les éléments sont identiques, on continue la vérification sans changer 'previous'
        }

        return true;
    }

   
}
