package fr.rodez3il.a2022.mrmatt.solveur.structures;

import java.util.Arrays;

public class ListeTableau<T> implements Liste<T> {

    private T[] tableau;
    private int size;

    public ListeTableau() {

        this.size = 0;
        this.tableau = (T[]) new Object[5];
        // this.tab = new T[size];
    }

    /**
     * Ajoute un élément à la liste.
     *
     * @param element l'élément à ajouter
     */

    public void ajouter(T element) {
        /**
         * Si on a atteint la taille maximale,
         * refaire un tableau en doublant la taille initale
         */
        if (this.tableau.length == this.size) {
            this.tableau = Arrays.copyOf(tableau, tableau.length * 2);
        }
        this.tableau[size++] = element;
    }

    /**
     * Indique si la liste est vide.
     *
     * @return true ssi la liste est vide.
     */
    @Override
    public boolean estVide() {
        return this.tableau.length == 0;
    }

    /**
     * Indique la taille de la liste.
     *
     * @return La taille de la liste.
     */
    @Override
    public int taille() {
        return this.size;
    }

    /**
     * Enlève (et retourne) l'élément à la position i.
     *
     * @param i la position de l'élément
     * @return L'élément qui a été supprimé
     */
    @Override
    public T enlever(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        T tmp = this.tableau[i];
        for (int j = i; j < this.size - 1; j++) {
            tableau[j] = tableau[j + 1];
        }
        return tmp;
    }

    /**
     * Renvoie l'élément à la position i.
     *
     * @param i
     * @return
     */
    @Override
    public T element(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return tableau[i];
    }
    /**
     * Indique s'il existe un élément f dans la liste
     * tel que f.equals(e) est VRAI.
     *
     * @param e L'élément à comparer.
     * @return
     */
    @Override
    public boolean contient(T e) {
        for (int i = 0; i < this.size; i++) {
            if (tableau[i].equals(e)) {
                return true;
            }
        }
        return false;
    }
}