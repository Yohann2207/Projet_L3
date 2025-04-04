package Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import Ressource.Ordinateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import BDD.BDD;

public class Employe extends Personne{
	
	private double salaire; 
	private double prime; 
	private String role; // exemple admin 
	
	public Employe(int id, String nom, LocalDate date_naissance, String login, String mdp, double salaire, String role) {
		super(id, nom, date_naissance, login, mdp);
		this.salaire=salaire;
		this.prime=0;
		this.role=role;
	}
	
	// Ajouter un ordinateur
	public Ressource AjouterOrdinateur(ArrayList<Ressource> ressources, int id, String nom,String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, boolean portUSB, boolean portUSBC, boolean portHDMI) {
		Ressource ordinateur = new Ordinateur(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, portUSB, portUSBC, portHDMI);
        ressources = BDD.recupererToutesLesRessources();
		return ordinateur;
	}

	// Ajouter une tablette
	public Ressource AjouterTablette(ArrayList<Ressource> ressources, int id, String nom, String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, String logiciel, boolean estAccessoire) {
		Ressource tablette = new Tablette_graphique(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, logiciel, estAccessoire);
        ressources = BDD.recupererToutesLesRessources();
		return tablette;
	}

	// Ajouter un telephone
	public Ressource AjouterTelephone(ArrayList<Ressource> ressources, int id, String nom, String marque, int autonomie, int nbCoeurs, int memoire, int resolution, double prix, int dureeMax, int numero) {
		Ressource telephone = new Telephone(id, nom, marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, numero);
        ressources = BDD.recupererToutesLesRessources();
		return telephone;
	}
	
    // Supprimer une ressource
	public boolean supprimerRessource(ArrayList<Ressource> ressources, int index) {
	    for (Ressource r : ressources) {
	        if (r.getId() == index) {
	            BDD.supprimer_res(index);
	            ressources = BDD.recupererToutesLesRessources();
	            return true;
	        }
	    }
	    return false;
	}

    // Changer l'état d'une ressource
	public boolean changerEtatRessource(ArrayList<Ressource> ressources, int index, String nouvelEtat) {
	    for (Ressource r : ressources) {
	        if (r.getId() == index) {
	            BDD.changer_etat_res(nouvelEtat, index);
	            ressources = BDD.recupererToutesLesRessources();
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
	    utilisateurs = BDD.recupererTousLesUtilisateurs();
	    return true;
	}
    
	// Supprimer un utilisateur
	public boolean supprimerUtilisateur(ArrayList<Utilisateur> utilisateurs, String login) {
	    for (Utilisateur u : utilisateurs) {
	        if (u.getLogin().equalsIgnoreCase(login)) {
	            BDD.supprimer_uti(login);
	    	    utilisateurs = BDD.recupererTousLesUtilisateurs();
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
	    employes = BDD.recupererTousLesEmployes();
	    return true;
	}
    
	// Supprimer un employé
	public boolean supprimerEmploye(ArrayList<Employe> employes, String login) {
	    for (Employe e : employes) {
	        if (e.getLogin().equalsIgnoreCase(login)) {
	            BDD.supprimer_employe(login);
	            employes = BDD.recupererTousLesEmployes();
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
		return "Id : " + getId() + " | Nom : " + getNom() + " | Login : " + getLogin() + " | Poste : " + getRole() + " | Salaire : " + getSalaire() + " euros";	
	}
	
	public static ArrayList<Employe> recupererTous() {
	    return BDD.recupererTousLesEmployes();
	}
}
