package Controleur;

import Personne.Employe;
import Personne.Personne;
import Personne.Utilisateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import Transaction.Emprunt;
import Ressource.Ordinateur;
import Vue.IHM;
import Vue.MenuEmploye_IHM;
import Vue.MenuUtilisateur_IHM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BDD.BDD;

public class Controleur {
    
    private Utilisateur utilisateur;
    private Employe employe;
    private ArrayList<Ressource> ressources;
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Employe> employes;
    private ArrayList<Emprunt> emprunts;
    private ArrayList<Emprunt> mesEmprunts;

    public Controleur() {        
        this.utilisateurs = BDD.recupererTousLesUtilisateurs();
        this.employes = BDD.recupererTousLesEmployes();
        this.ressources = BDD.recupererToutesLesRessources();
        this.emprunts = BDD.recupererTousLesEmprunts(utilisateurs, ressources);
    }
    
    // Demarrer l'application avec l'IHM
    public boolean authentifier(String login, String mdp) {
        Personne personne = Personne.authentifier(login, mdp, utilisateurs, employes);

        if (personne == null) {
            return false;
        }

        if (personne instanceof Utilisateur) {
            utilisateur = (Utilisateur) personne;
        } else if (personne instanceof Employe) {
            employe = (Employe) personne;
        }

        return true;
    }
    
    // Lancer un menu (IHM)
    public void lancerMenu() {
        if (utilisateur != null) {
            new MenuUtilisateur_IHM(this, utilisateur);
        } else if (employe != null) {
            new MenuEmploye_IHM(this, employe);
        }
    }

    
    public boolean ajouterNouvelEmprunt(int idRessource) {
        Ressource ressource = null;

        // Recherche de la ressource à partir de son ID
        for (Ressource r : ressources) {
            if (r.getId() == idRessource) {
                ressource = r;
                break;
            }
        }

        if (ressource != null && utilisateur != null) {
            int idEmprunt = utilisateur.creerNouvelEmprunt(utilisateur, ressource);

            if (idEmprunt != -1) {
            	BDD.recupererTousLesEmprunts(utilisateurs, ressources);
                return true;
            }
        }

        return false;
    }
    
    public boolean rendreEmprunt(Emprunt emprunt) {
        boolean result = utilisateur.rendreEmprunt(emprunt);
        if (result) {
            ressources = BDD.recupererToutesLesRessources();
        }
        return result;
    }
    
    
    public ArrayList<Emprunt> recup_emp_en_cours() {
        return BDD.recupererEmpruntsEnCoursParUtilisateur(utilisateur.getId(), utilisateurs, ressources);
    }
    
    public double recup_dette() {
    	double dette= BDD.recuperer_dette(this.utilisateur.getId());
    	return dette;
    }
    
    public void pay_dette() {
    	BDD.payer_dette(this.utilisateur.getId());
    }
    
    public boolean ajouterRessource(String nom, String marque, double prix, int dureeMax, String typeRes) {
        int id = BDD.ajouter_res(nom, marque, true, prix, dureeMax, "Neuf", typeRes);

        if (id == -1) {
            return false;
        }

        Ressource ressource = null;
        switch (typeRes.toLowerCase()) {
            case "ordinateur":
                ressource = employe.AjouterOrdinateur(ressources, id, nom, marque, 0, 0, 0, 0, prix, dureeMax, false, false, false);
                break;
            case "tablette_graphique":
                ressource = employe.AjouterTablette(ressources, id, nom, marque, 0, 0, 0, 0, prix, dureeMax, "", false);
                break;
            case "telephone":
                ressource = employe.AjouterTelephone(ressources, id, nom, marque, 0, 0, 0, 0, prix, dureeMax, 0);
                break;
            default:
                return false;
        }

        if (ressource != null) {
            return true;
        }
        return false;
    }

    public boolean supprimerRessource(int idRessource) {
        boolean removed = employe.supprimerRessource(ressources, idRessource);
        return removed;
    }

    public boolean changerEtatRessource(int idRessource, String nouvelEtat) {
        return employe.changerEtatRessource(ressources, idRessource, nouvelEtat);
    }

    
    public String afficherRessources() {
    	ressources = BDD.recupererToutesLesRessources();
    	String ressourcesList = "";

        if (ressources.isEmpty()) {
            ressourcesList = "Aucune ressource trouvée !";
        } else {
            for (Ressource r : ressources) {
                ressourcesList = ressourcesList + r.toString() + "\n";
            }
        }
        return ressourcesList;
    }
    
    public void synchroniserRessources() {
        ArrayList<Ressource> ressources = BDD.recupererToutesLesRessources();
        this.ressources = ressources; 
    }
    
    public String afficherRessourcesLibres() {
    	ressources = BDD.recupererResLibre();
    	String ressourcesList = "";

        if (ressources.isEmpty()) {
            ressourcesList = "Aucune ressource trouvée !";
        } else {
            for (Ressource r : ressources) {
                ressourcesList = ressourcesList + r.toString() + "\n";
            }
        }
        return ressourcesList;
    }
    
    public ArrayList<Ressource> afficherRessourcesAL() {
        ArrayList<Ressource> ressourcesLibres = BDD.recupererResLibre();
        return ressourcesLibres;
    }
    
    public ArrayList<Employe> getEmployes() {
        return this.employes;
    }

    public ArrayList<Ressource> getRessources() {
        return this.ressources;
    }
    
    public boolean ajouterUtilisateur(String nom, String dateNaissanceStr, String login, String mdp) {
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);

        int id = BDD.ajouter_uti(nom, dateNaissance, login, mdp);
        if (id != -1) {
            Utilisateur utilisateur = new Utilisateur(id, nom, dateNaissance, login, mdp, LocalDate.now());
            return employe.ajouterUtilisateur(utilisateurs, utilisateur);
        }
        return false;
    }

    public boolean supprimerUtilisateur(String login) {
        return employe.supprimerUtilisateur(utilisateurs, login);
    }
    
    public boolean ajouterEmploye(String nom, String dateNaissanceStr, String login, String mdp, double salaire, String poste) {
    	LocalDate dateNaissance = LocalDate.parse(dateNaissanceStr);

        int id = BDD.ajouter_employe(nom, dateNaissance, login, mdp, salaire, poste);
        if (id != -1) {
            Employe employeaj = new Employe(id, nom, dateNaissance, login, mdp, salaire, poste);
            return employe.ajouterEmploye(employes, employeaj);
        }
        return false;
    }

    public boolean supprimerEmploye(String login) {
        return employe.supprimerEmploye(employes, login);
    }
    
    public String afficherListeUtilisateurs() {
    	utilisateurs = BDD.recupererTousLesUtilisateurs();
    	String utilisateursList = "";

        if (utilisateurs.isEmpty()) {
            utilisateursList = "Aucun utilisateur trouvé !";
        } else {
            for (Utilisateur u : utilisateurs) {
            	utilisateursList = utilisateursList + u.toString() + "\n";
            }
        }
        return utilisateursList;
    }

    public String afficherListeEmployes() {
    	employes = BDD.recupererTousLesEmployes();
    	String employesList = "";
	
    	if (employes.isEmpty()) {
    		employesList = "Aucun employé trouvé !";
    	} else {
    		for (Employe e : employes) {
    			employesList = employesList + e.toString() + "\n";
	        }
	    }
	    return employesList;
	}
    
    public String afficherListeEmprunts() {
        emprunts = BDD.recupererTousLesEmprunts(utilisateurs, ressources); 
        String empruntsList = "";

        if (emprunts.isEmpty()) {
            empruntsList = "Aucun emprunt trouvé !";
        } else {
            for (Emprunt e : emprunts) {
                empruntsList += e.toString() + "\n";
            }
        }

        return empruntsList;
    }
    
    public ArrayList<Utilisateur> getUtilisateurs() {
        return this.utilisateurs;
    }
    
    public String afficherEmpruntsUtilisateur() {
        if (utilisateur != null) {
            mesEmprunts = utilisateur.getMesEmprunts(utilisateurs, ressources);
            if (mesEmprunts.isEmpty()) {
                return "Aucun emprunt trouvé.";
            } else {
                String result = "";
                for (Emprunt e : mesEmprunts) {
                    result += e.toString() + "\n";
                }
                return result;
            }
        }
        return "Aucun utilisateur connecté.";
    }
    
   
    
    public void deconnecter() {
        this.utilisateur = null;
        this.employe = null;
    }

}
