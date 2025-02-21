package Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import Ressource.Ordinateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import BDD.BDD;

public class Employe extends Personne{
	
	private double salaire; //a voir rajouter cu 
	private double prime; //a voir rajouter cu 
	private String role; // exemple admin 
	
	public Employe(String nom, LocalDate date_naissance, String login, String mdp, double salaire, double prime, String role) {
		super(nom, date_naissance, login, mdp);
		this.salaire=salaire;
		this.prime=prime;
		this.role=role;
	}
	
	// Ajouter un ordinateur
	public Ressource AjouterOrdinateur(String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, boolean portUSB, boolean portUSBC, boolean portHDMI) {
		Ressource ordinateur = new Ordinateur(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, portUSB, portUSBC, portHDMI);
		BDD.ajouter_res();
		return ordinateur;
	}

	// Ajouter une tablette
	public Ressource AjouterTablette(String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, String logiciel, boolean estAccessoire) {
		Ressource tablette = new Tablette_graphique(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, logiciel, estAccessoire);
		BDD.ajouter_res();
		return tablette;
	}

	// Ajouter un telephone
	public Ressource AjouterTelephone(String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, int numero) {
		Ressource telephone = new Telephone(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, numero);
		BDD.ajouter_res();
		return telephone;
	}
	
    // Supprimer une ressource
    public boolean supprimerRessource(ArrayList<Ressource> ressources, int index) {
        if (index >= 0 && index < ressources.size()) {
            ressources.remove(index);
            BDD.supprimer_res(index);
            return true;
        }
        return false;
    }

    // Changer l'état d'une ressource
    public boolean changerEtatRessource(ArrayList<Ressource> ressources, int index, String nouvelEtat) {
        if (index >= 0 && index < ressources.size()) {
            ressources.get(index).setEtat(nouvelEtat);
            BDD.changer_etat_res(nouvelEtat, index);
            return true;
        }
        return false;
    }
    
}
