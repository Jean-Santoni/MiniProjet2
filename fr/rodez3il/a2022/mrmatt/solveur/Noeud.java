package fr.rodez3il.a2022.mrmatt.solveur;

import fr.rodez3il.a2022.mrmatt.solveur.structures.DictionnaireChaine;
import fr.rodez3il.a2022.mrmatt.solveur.structures.ListeChainee;
import fr.rodez3il.a2022.mrmatt.solveur.structures.ListeTableau;
import fr.rodez3il.a2022.mrmatt.sources.Commande;
import fr.rodez3il.a2022.mrmatt.sources.Niveau;

import java.util.ArrayList;
import java.util.Objects;

public class Noeud {
    private DictionnaireChaine<String,Noeud> dicoConfigurations;
    private Niveau niveauActuel;
    private ListeTableau<Noeud> fils;
    private String commandes;
   private String solutions ;


    private boolean visite;

    public Noeud(DictionnaireChaine <String,Noeud> dicoConfigurations, Niveau niveauActuel , String commandes) {
        this.dicoConfigurations = dicoConfigurations;
        this.niveauActuel = niveauActuel;
        this.fils = new ListeTableau<>();
        this.commandes = commandes;
        this.visite = false;
    }

    public boolean estVisite() {
        return dicoConfigurations.contient(niveauActuel.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Noeud noeud = (Noeud) o;
        return Objects.equals(niveauActuel, noeud.niveauActuel);
    }

    public ListeTableau<Noeud> getFils() {
        return fils;
    }

    public String calculerFils() {
        String suiteCommande = "";
        String valeurChaine = "";
        if (!estVisite()) {
            dicoConfigurations.inserer(niveauActuel.valeurChaine(), this);


            Commande [] direction = Commande.values();


            for (int i = 0; i < 4; i++) {
                if (niveauActuel.deplacementPossible(direction[i]) && niveauActuel.enCours()) {
                    suiteCommande= this.commandes+" "+direction[i];
                    Niveau fil = niveauActuel.copier();
                    fil.deplacer(direction[i]);
                    fil.calcule();
                    boolean gagner = fil.estGagnant();


                    if(gagner) {
                        solutions = suiteCommande;
                        System.out.println(solutions);
                    }else{
                        Noeud filsNoeud;
                        valeurChaine = fil.valeurChaine();
                        if (dicoConfigurations.contient(valeurChaine)){
                            filsNoeud = dicoConfigurations.valeur(valeurChaine);
                        } else {
                            filsNoeud= new Noeud(this.dicoConfigurations,fil,suiteCommande);
                            this.dicoConfigurations.inserer(valeurChaine,filsNoeud);
                        }
                        this.fils.ajouter(filsNoeud);
                    }
                }
            }

        }
        return solutions;
    }
}