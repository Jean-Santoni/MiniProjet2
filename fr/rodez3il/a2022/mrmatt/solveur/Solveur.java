package fr.rodez3il.a2022.mrmatt.solveur;

import fr.rodez3il.a2022.mrmatt.solveur.structures.DictionnaireChaine;
import fr.rodez3il.a2022.mrmatt.solveur.structures.ListeChainee;
import fr.rodez3il.a2022.mrmatt.solveur.structures.ListeTableau;
import fr.rodez3il.a2022.mrmatt.sources.Niveau;

public class Solveur {




	public static String trouverSolution(Niveau niveau)  {
		DictionnaireChaine<String,Noeud> dic = new DictionnaireChaine<>();
		ListeTableau<Noeud> traites = new ListeTableau<Noeud>();
		ListeTableau<Noeud> aTraiter = new ListeTableau<Noeud>();
		Noeud noeudInitial = new Noeud(dic, niveau, "");
		aTraiter.ajouter(noeudInitial);
		String solution=null;

		while (!aTraiter.estVide() && solution==null) {
			Noeud noeudActuel = aTraiter.enlever(0);
			traites.ajouter(noeudActuel);

			solution = noeudActuel.calculerFils();
			if (solution != null) {
				return solution;
			}else{
				ListeTableau<Noeud>tabFils = noeudInitial.getFils();
				for (int i = 0 ; i< tabFils.taille();i++) {
					Noeud fils = tabFils.element(i);
					if (!traites.contient(fils) && !aTraiter.contient(fils)&&!fils.estVisite()) {
						aTraiter.ajouter(fils);
					}
				}
			}


		}

		return solution;
	}
	
	public static void main(String[] args) {
		String solution = trouverSolution(new Niveau("niveaux/1rocher.txt"));
		if (solution == null) {
			System.out.println("Pas de solution.");
		} else {
			System.out.println("Une solution est : " + solution);
		}
	}

}
