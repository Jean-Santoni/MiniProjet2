package fr.rodez3il.a2022.mrmatt.solveur.structures;

import java.util.Arrays;

public class ListeChainee<T> implements Liste<T> {
    private class Maillon {
        private T donnee;
        private Maillon suivant;

        public Maillon(T donnee, Maillon suivant) {
            this.donnee = donnee;
            this.suivant = suivant;
        }
    }

    private Maillon tete = null;
    private int taille = 0;

    @Override
    public void ajouter(T element) {
        Maillon nouveau = new Maillon(element, null);
        if (tete == null) {
            tete = nouveau;
        } else {
            Maillon dernier = tete;
            while (dernier.suivant != null) {
                dernier = dernier.suivant;
            }
            dernier.suivant = nouveau;
        }
        taille++;
    }

    @Override
    public boolean estVide() {
        return tete == null;
    }

    @Override
    public int taille() {
        return taille;
    }

    @Override
    public T enlever(int i) {
        if (i < 0 || i >= taille) {
            throw new IndexOutOfBoundsException();
        }
        Maillon precedent = null;
        Maillon courant = tete;
        for (int j = 0; j < i; j++) {
            precedent = courant;
            courant = courant.suivant;
        }
        T resultat = courant.donnee;
        if (precedent == null) {
            tete = courant.suivant;
        } else {
            precedent.suivant = courant.suivant;
        }
        taille--;
        return resultat;
    }

    @Override
    public T element(int i) {
        if (i < 0 || i >= taille) {
            throw new IndexOutOfBoundsException();
        }
        Maillon courant = tete;
        for (int j = 0; j < i; j++) {
            courant = courant.suivant;
        }
        return courant.donnee;
    }

    @Override
    public boolean contient(T e) {
        Maillon courant = tete;
        while (courant != null) {
            if (e == null) {
                if (courant.donnee == null) {
                    return true;
                }
            } else if (e.equals(courant.donnee)) {
                return true;
            }
            courant = courant.suivant;
        }
        return false;
    }
}