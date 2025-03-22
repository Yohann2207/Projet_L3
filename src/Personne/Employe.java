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
	public Ressource AjouterOrdinateur(int id, String nom,String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, boolean portUSB, boolean portUSBC, boolean portHDMI) {
		Ressource ordinateur = new Ordinateur(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, portUSB, portUSBC, portHDMI);
		return ordinateur;
	}

	// Ajouter une tablette
	public Ressource AjouterTablette(int id, String nom, String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, String logiciel, boolean estAccessoire) {
		Ressource tablette = new Tablette_graphique(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, logiciel, estAccessoire);
		return tablette;
	}

	// Ajouter un telephone
	public Ressource AjouterTelephone(int id, String nom, String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, int numero) {
		Ressource telephone = new Telephone(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, numero);
		return telephone;
	}
	
    // Supprimer une ressource
	public boolean supprimerRessource(ArrayList<Ressource> ressources, int index) {
	    for (Ressource r : ressources) {
	        if (r.getId() == index) {
	            ressources.remove(r);
	            BDD.supprimer_res(index);
	            return true;
	        }
	    }
	    return false;
	}

    // Changer l'état d'une ressource
	public boolean changerEtatRessource(ArrayList<Ressource> ressources, int index, String nouvelEtat) {
	    for (Ressource r : ressources) {
	        if (r.getId() == index) {
	            r.setEtat(nouvelEtat);
	            BDD.changer_etat_res(nouvelEtat, index);
	            return true;
	        }
	    }
	    return false;
	}
    
	// Ajouter un utilisateur
	public boolean ajouterUtilisateur(ArrayList<Utilisateur> utilisateurs, Utilisateur utilisateur) {
	    for (Utilisateur u : utilisateurs) {
	        if (u.getLogin().equalsIgnoreCase(utilisateur.getLogin())) {
	            return false;
	        }
	    }
	    utilisateurs.add(utilisateur);
	    BDD.ajouter_uti(utilisateur.getNom(), utilisateur.getDate_naissance(), utilisateur.getLogin(), utilisateur.getMdp());
	    return true;
	}
    
	// Supprimer un utilisateur
	public boolean supprimerUtilisateur(ArrayList<Utilisateur> utilisateurs, String login) {
	    for (Utilisateur u : utilisateurs) {
	        if (u.getLogin().equalsIgnoreCase(login)) {
	            utilisateurs.remove(u);
	            BDD.supprimer_uti(login);
	            return true;
	        }
	    }
	    return false; 
	}
    
	// Ajouter un employé
	public boolean ajouterEmploye(ArrayList<Employe> employes, Employe nouvelEmploye) {
	    for (Employe e : employes) {
	        if (e.getLogin().equalsIgnoreCase(nouvelEmploye.getLogin())) {
	            return false;
	        }
	    }
	    employes.add(nouvelEmploye);
	    BDD.ajouter_employe(nouvelEmploye.getNom(), nouvelEmploye.getDate_naissance(), nouvelEmploye.getLogin(), nouvelEmploye.getMdp(), nouvelEmploye.getSalaire(), nouvelEmploye.getRole());
	    return true;
	}
    
	// Supprimer un employé
	public boolean supprimerEmploye(ArrayList<Employe> employes, String login) {
	    for (Employe e : employes) {
	        if (e.getLogin().equalsIgnoreCase(login)) {
	            employes.remove(e);
	            BDD.supprimer_employe(login);
	            return true;
	        }
	    }
	    return false;
	}
    
    public double getSalaire() {
		return salaire;
	}

	public double getPrime() {
		return prime;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Nom : " + getNom() + " | Login : " + getLogin() + " | Poste : " + getRole() + " | Salaire : " + getSalaire() + " euros";	
	}
}
