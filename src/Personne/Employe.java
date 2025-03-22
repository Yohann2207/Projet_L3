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
        // Supprimer dans la liste mémoire
        boolean ressourceTrouvee = ressources.removeIf(r -> r.getId() == index);

        if (ressourceTrouvee) {
            boolean deleted = BDD.supprimer_res(index);

            if (deleted) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // Changer l'état d'une ressource
    public boolean changerEtatRessource(ArrayList<Ressource> ressources, int index, String nouvelEtat) {
    	// Recherche de la ressource
        Ressource ressourceAModifier = ressources.stream().filter(r -> r.getId() == index).findFirst().orElse(null);

        if (ressourceAModifier != null) {
            ressourceAModifier.setEtat(nouvelEtat);
            BDD.changer_etat_res(nouvelEtat, index);
            return true;
        }
        return false; 
    }
    
    public boolean ajouterUtilisateur(ArrayList<Utilisateur> utilisateurs, Utilisateur utilisateur) {
        // Vérifie si le login existe déjà dans la liste des utilisateurs
        boolean existeDejaU = utilisateurs.stream().anyMatch(u -> u.getLogin().equalsIgnoreCase(utilisateur.getLogin()));

        if (existeDejaU) {
            return false; 
        }
        
        utilisateurs.add(utilisateur);
        BDD.ajouter_uti(utilisateur.getNom(), utilisateur.getLogin(), utilisateur.getMdp());

        return true;
    }
    
    public boolean supprimerUtilisateur(ArrayList<Utilisateur> utilisateurs, String login) {
        // Supprimer dans la liste mémoire
        boolean removed = utilisateurs.removeIf(user -> user.getLogin().equals(login));

        if (removed) {
            BDD.supprimer_uti(login);
            return true;
        }

        return false;
    }
    
    public boolean ajouterEmploye(ArrayList<Employe> employes, Employe nouvelEmploye) {
        // Vérifier si le login existe déjà
        boolean existeDejaE = employes.stream().anyMatch(emp -> emp.getLogin().equalsIgnoreCase(nouvelEmploye.getLogin()));

        if (existeDejaE) {
            return false;  
        }

        employes.add(nouvelEmploye);
        BDD.ajouterEmploye(nouvelEmploye.getNom(), nouvelEmploye.getLogin(), nouvelEmploye.getMdp(), nouvelEmploye.getSalaire(), nouvelEmploye.getRole());

        return true;
    }
    
    public boolean supprimerEmploye(ArrayList<Employe> employes, String login) {
        // Supprimer de la liste mémoire
        boolean removed = employes.removeIf(emp -> emp.getLogin().equalsIgnoreCase(login));

        if (removed) {
            BDD.supprimerEmploye(login);
            return true;
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
