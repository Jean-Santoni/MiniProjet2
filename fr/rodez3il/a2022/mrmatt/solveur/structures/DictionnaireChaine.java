package fr.rodez3il.a2022.mrmatt.solveur.structures;

public class DictionnaireChaine<C, V> implements Dictionnaire<C, V> {
    private class Entree<C, V> {
        private C cle;
        private V valeur;
        private Entree<C, V> suivant;

        public Entree(C cle, V valeur) {
            this.cle = cle;
            this.valeur = valeur;
        }
    }

    private Entree<C, V> tete;

    public DictionnaireChaine() {
        tete = null;
    }

    public void inserer(C cle, V valeur) {
        Entree<C, V> nouvelleEntree = new Entree<>(cle, valeur);

        if (tete == null) {
            tete = nouvelleEntree;
        } else {
            Entree<C, V> entreeCourante = tete;
            while (entreeCourante.suivant != null && !entreeCourante.cle.equals(cle)) {
                entreeCourante = entreeCourante.suivant;
            }

            if (entreeCourante.cle.equals(cle)) {
                entreeCourante.valeur = valeur;
            } else {
                entreeCourante.suivant = nouvelleEntree;
            }
        }
    }

    public boolean contient(C cle) {
        Entree<C, V> entreeCourante = tete;
        while (entreeCourante != null && !entreeCourante.cle.equals(cle)) {
            entreeCourante = entreeCourante.suivant;
        }

        return entreeCourante != null;
    }

    public V valeur(C cle) {
        Entree<C, V> entreeCourante = tete;
        while (entreeCourante != null && !entreeCourante.cle.equals(cle)) {
            entreeCourante = entreeCourante.suivant;
        }

        if (entreeCourante != null) {
            return entreeCourante.valeur;
        } else {
            throw new IllegalArgumentException("La clé n'existe pas dans le dictionnaire.");
        }
    }
}