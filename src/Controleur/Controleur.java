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
import BDD.BDD;

public class Controleur {
    
    private IHM affichage;
    private Utilisateur utilisateur;
    private Employe employe;
    private ArrayList<Ressource> ressources;
    private ArrayList<Utilisateur> utilisateurs;
    private ArrayList<Employe> employes;
    private ArrayList<Emprunt> emprunts;

    public Controleur() {
        this.affichage = new IHM();
        
        this.utilisateurs = BDD.recupererTousLesUtilisateurs();
        this.employes = BDD.recupererTousLesEmployes();
        this.ressources = BDD.recupererToutesLesRessources();
        this.emprunts = BDD.recupererTousLesEmprunts(utilisateurs, ressources);
        
     // Liste des personnes
        //this.utilisateur = new Utilisateur("Alice", LocalDate.of(2000, 3, 22), "aliceY", "pass456", LocalDate.now());
        //this.employe = new Employe("Martin", LocalDate.of(1985, 6, 15), "martinE", "pass123", 2500, 500, "Admin");
        //utilisateurs.add(utilisateur);
        //employes.add(employe);
                
     // Liste des ressources
        //Ressource ordinateur = new Ordinateur("test", "Dell", 10, 8, 16, 1080, true, 1000, 15, true, true, true);
        //Ressource tablette = new Tablette_graphique("test", "Wacom", 8, 4, 8, 720, true, 500, 8, "Photoshop", false);
        //Ressource telephone = new Telephone("test", "Samsung", 12, 8, 16, 1080, true, 700, 5, 123456789);
        //ressources.add(ordinateur);
        //ressources.add(tablette);
        //ressources.add(telephone);

        // Test rendu retard
        //LocalDate dateEmprunt = LocalDate.now().minusDays(10); // Emprunt il y a 10 jours
        //Emprunt empruntRetard = new Emprunt(utilisateur, telephone, dateEmprunt);
        //utilisateur.getEmpruntsActifs().add(empruntRetard); // Ajouter l'emprunt manuellement pour tester un rendu avec retard
    }

/*
 // Demarrer l'application (console)
    public void demarrer() {
        affichage.afficherMessage("=== Bienvenue dans l'application ===");

        while (true) {

            Personne personne = null;

            while (personne == null) {
                affichage.afficherMessage("Login : ");
                String login = affichage.lireEntreeTexte();

                affichage.afficherMessage("Mot de passe : ");
                String mdp = affichage.lireEntreeTexte();
                
                personne = Personne.authentifier(login, mdp, utilisateurs, employes);

                if (personne == null) {
                    affichage.afficherMessage("Login ou mot de passe incorrect. Veuillez reessayer.\n");
                } else {
                    affichage.afficherMessage("Connexion reussie ! Bienvenue " + personne.getNom() + " !\n");
                }
            }

            if (personne instanceof Utilisateur) {
                this.utilisateur = (Utilisateur) personne;
                gererUtilisateur();
            } else if (personne instanceof Employe) {
                this.employe = (Employe) personne;  
                gererEmploye();
            }

            affichage.afficherMessage("Deconnexion en cours...\n");
            
            this.utilisateur = null;
            this.employe = null;
        }
    }
*/
    
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

/*
    // Menu Utilisateur
    private void gererUtilisateur() {
        boolean userMenu = true;
        while (userMenu) {
            affichage.afficherMenuUtilisateur();
            int choix = affichage.lireEntreeEntier();

            switch (choix) {
            case 1: // Afficher toutes les ressources
                affichage.afficherRessources(ressources);
                break;
            case 2: // Emprunter en affichant les ressources disponibles
            	 ArrayList<Ressource> ressourcesDisponibles = new ArrayList<>();
            	 for (Ressource r : ressources) {
                     if (r.isLibre()) {
                         ressourcesDisponibles.add(r);
                     }
                 }
                 affichage.afficherRessources(ressourcesDisponibles);
                 affichage.afficherMessage("Choisissez une ressource a emprunter : ");
                 int indexE = affichage.lireEntreeEntier();
                 if (indexE >= 0 && indexE < ressourcesDisponibles.size()) {
                     utilisateur.emprunter(ressourcesDisponibles.get(indexE));
                 } else {
                     affichage.erreur();
                 }
                break;
            case 3: // Rendre
            	affichage.afficherEmpruntsActifs(utilisateur.getEmpruntsActifs());
                affichage.afficherMessage("Choisissez une ressource a rendre : ");
                int indexR = affichage.lireEntreeEntier();
                utilisateur.rendre(indexR);
                affichage.afficherMessage("Ressource rendue avec succes !");
                affichage.afficherDette(utilisateur.getDette());
                break;
            case 4: // Afficher sa dette
                affichage.afficherDette(utilisateur.getDette());
                break;
            case 5: // Payer
            	if (utilisateur.getDette() > 0) {
            		affichage.afficherDette(utilisateur.getDette());
            		affichage.afficherMessage("Entrez le montant que vous souhaitez payer : ");
	                double montant = affichage.lireEntreeDouble();
	                if (montant > utilisateur.getDette()) {
		                utilisateur.payer(montant);
	                	affichage.afficherMessage("Vous avez paye " + montant + " euros, le reste vous sera rendu.");
	                } 
	                else {
		                utilisateur.payer(montant);
	                	affichage.afficherMessage("Paiement de " + montant + " euros effectue. Dette restante : " + utilisateur.getDette() + " euros.");
	                }            	
            	} 
            	else {
            		affichage.afficherMessage("Vous n'avez aucune dette a payer !");
                }
                break;
            case 6: // Afficher les emprunts en cours
                affichage.afficherEmpruntsActifs(utilisateur.getEmpruntsActifs());
                break;
            case 7: // Afficher la liste des emprunts effectuer
                affichage.afficherHistoriqueEmprunts(utilisateur.getHistoriqueEmprunts());
                break;
            case 8: // Se déconnecter
                userMenu = false;
                break;
            default: // Choix invalide
                affichage.erreur();
            }
        }
    }

    // Menu Employé
    private void gererEmploye() {
        boolean employeMenu = true;
        while (employeMenu) {
            affichage.afficherMenuEmploye();
            int choix = affichage.lireEntreeEntier();

            switch (choix) {
                case 1: // Ajouter une ressource
                	affichage.affichermenuAjoutRes();
            	    int type = affichage.lireEntreeEntier();

            	    Object[] infosBase = affichage.saisirInformationsRessource();

            	    String nom = (String) infosBase[0];
            	    String marque = (String) infosBase[1];
            	    int autonomie = (int) infosBase[2];
            	    int nbCoeurs = (int) infosBase[3];
            	    int memoire = (int) infosBase[4];
            	    int resolution = (int) infosBase[5];
            	    double prix = (double) infosBase[6];
            	    int dureeMax = (int) infosBase[7];
            	    
            	    Ressource ressource = null;
            	    String typeRes = "";
            	    
            	    int id = BDD.ajouter_res(nom, marque, true, prix, dureeMax, "Neuf", (type == 1) ? "Ordinateur" : (type == 2) ? "Tablette_graphique" : "Telephone");

            	        if (id == -1) {
            	            affichage.afficherMessage("Erreur lors de l'ajout de la ressource en BDD !");
            	            break;
            	        }

            	    switch (type) {
            	        case 1: // Ordinateur
            	        	Object[] optionsOrdinateur = affichage.saisirOptionsOrdinateur();
            	            boolean portUSB = (boolean) optionsOrdinateur[0];
            	            boolean portUSBC = (boolean) optionsOrdinateur[1];
            	            boolean portHDMI = (boolean) optionsOrdinateur[2];

            	            ressource = employe.AjouterOrdinateur(ressources, id, nom, marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, portUSB, portUSBC, portHDMI);
            	            typeRes = "Ordinateur";
            	            break;

            	        case 2: // Tablette Graphique
            	        	Object[] optionsTablette = affichage.saisirOptionsTablette();
            	            String logiciel = (String) optionsTablette[0];
            	            boolean estAccessoire = (boolean) optionsTablette[1];

            	            ressource = employe.AjouterTablette(ressources, id, nom, marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, logiciel, estAccessoire);
            	            typeRes = "Tablette_graphique";
            	            break;

            	        case 3: // Téléphone
            	        	int numero = affichage.saisirOptionsTelephone();

            	            ressource = employe.AjouterTelephone(ressources, id, nom, marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, numero);
            	            typeRes = "Telephone";
            	            break;

            	        default:
            	            affichage.erreur();
            	            return;
            	    }

            	    if (ressource != null) {
    	                ressources.add(ressource);
    	                affichage.afficherMessage("Ressource ajoutee avec succes !");
            	    } else {
            	        affichage.afficherMessage("Echec lors de la creation de la ressource !");
            	    }
                    break;
                    
                case 2: // Supprimer une ressource
                	affichage.afficherRessources(ressources);

                    affichage.afficherMessage("Entrez l'ID de la ressource a supprimer : ");
                    int idRessourceASupprimer = affichage.lireEntreeEntier();

                    boolean suppressionS_OK = employe.supprimerRessource(ressources, idRessourceASupprimer);

                    if (suppressionS_OK) {
                        affichage.afficherMessage("Ressource supprimee avec succes !");
                    } else {
                        affichage.afficherMessage("Aucune ressource trouvee avec cet ID ou echec de suppression !");
                    }
                    break;
                    
                case 3: // Changer l'état d'une ressource
                	 affichage.afficherRessources(ressources);

                     affichage.afficherMessage("Entrez l'ID de la ressource a modifier : ");
                     int idRessourceAModifier = affichage.lireEntreeEntier();

                     affichage.afficherMessage("Entrez le nouvel etat : ");
                     String nouvelEtat = affichage.lireEntreeTexte();
                     
                     boolean modificationOK = employe.changerEtatRessource(ressources, idRessourceAModifier, nouvelEtat);

                     if (modificationOK) {
                	    affichage.afficherMessage("Etat de la ressource modifie avec succes !");
                	} else {
                	    affichage.afficherMessage("Aucune ressource trouvee avec cet ID !");
                	}
                    break;
                    
                case 4: // Afficher toutes les ressources 
                    affichage.afficherRessources(ressources);
                    break;
                    
                case 5: // Ajouter un utilisateur
                	Utilisateur u = affichage.saisirNouvelUtilisateur();

                    boolean ajoutReussi = employe.ajouterUtilisateur(utilisateurs, u);

                    if (ajoutReussi) {
                        affichage.afficherMessage("Utilisateur ajoute avec succes !");
                    } else {
                        affichage.afficherMessage("Erreur : ce login existe deja. Impossible d'ajouter l'utilisateur !");
                    }
                    break;
                    
                case 6: // Supprimer un utilisateur
                    affichage.afficherListeUtilisateurs(utilisateurs);
                    
                    affichage.afficherMessage("Login de l'utilisateur a supprimer : ");
                    String loginToDeleteU = affichage.lireEntreeTexte();
                    
                    boolean suppressionU_OK = employe.supprimerUtilisateur(utilisateurs, loginToDeleteU);
                    
                    if (suppressionU_OK) {
                        affichage.afficherMessage("Utilisateur supprime avec succes !");
                    } else {
                        affichage.afficherMessage("Aucun utilisateur trouve avec ce login !");
                    }
                    break;
                    
                case 7: // Ajouter un employé
                	 Employe e = affichage.saisirNouvelEmploye();

                	 boolean ajoutOK = employe.ajouterEmploye(employes, e);

            	    if (ajoutOK) {
            	        affichage.afficherMessage("Employe ajoute avec succes !");
            	    } else {
            	        affichage.afficherMessage("Erreur : ce login existe deja. Impossible d'ajouter l'employe !");
            	    }
                    break;
                    
                case 8: // Supprimer un employé
                    affichage.afficherListeEmployes(employes);
                    
                    affichage.afficherMessage("Login de l'employe a supprimer : ");
                    String loginToDeleteE = affichage.lireEntreeTexte();
                    
                    boolean suppressionOK = employe.supprimerEmploye(employes, loginToDeleteE);

                    if (suppressionOK) {
                        affichage.afficherMessage("Employe supprime avec succes !");
                    } else {
                        affichage.afficherMessage("Aucun employe trouve avec ce login !");
                    }                    
                    break;
                    
                case 9: // Afficher les utilisateurs
                    affichage.afficherListeUtilisateurs(utilisateurs);
                    break;
                    
                case 10: // Afficher les employés
                    affichage.afficherListeEmployes(employes);
                    break;
                    
                case 11: // Se déconnecter
                    employeMenu = false;
                    break;
                    
                default: // Choix invalide
                    affichage.erreur();
            }
        }
    }
*/
    
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
    
    public ArrayList<Ressource> getRessourcesAL() {
        ArrayList<Ressource> ressourcesLibres = BDD.recupererResLibre();
        return ressourcesLibres;
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
            Employe employe = new Employe(id, nom, dateNaissance, login, mdp, salaire, poste);
            return employe.ajouterEmploye(employes, employe);
        }
        return false;
    }

    public boolean supprimerEmploye(String login) {
        return employe.supprimerEmploye(employes, login);
    }
    
    public String getListeUtilisateurs() {
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

    public String getListeEmployes() {
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
    
    public String getListeEmprunts() {
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

    
    public void deconnecter() {
        this.utilisateur = null;
        this.employe = null;
    }

}
