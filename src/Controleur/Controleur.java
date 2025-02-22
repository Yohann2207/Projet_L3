package Controleur;

import Personne.Employe;
import Personne.Utilisateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import Transaction.Emprunt;
import Ressource.Ordinateur;
import Vue.IHM;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controleur {
    
    private IHM affichage;
    private Utilisateur utilisateur;
    private Employe employe;
    private ArrayList<Ressource> ressources;

    public Controleur() {
        this.affichage = new IHM();
        this.utilisateur = new Utilisateur("Alice", LocalDate.of(2000, 3, 22), "aliceU", "pass456", LocalDate.now());
        this.employe = new Employe("Martin", LocalDate.of(1985, 6, 15), "martinE", "pass123", 2500, 500, "Admin");
        this.ressources = new ArrayList<>();
        
     // Liste des ressources
        Ressource ordinateur = new Ordinateur("Dell", 10, 8, 16, 1080, true, 1000, 15, true, true, true);
        Tablette_graphique tablette = new Tablette_graphique("Wacom", 8, 4, 8, 720, true, 500, 8, "Photoshop", false);
        Ressource telephone = new Telephone("Samsung", 12, 8, 16, 1080, true, 700, 5, 123456789);
        ressources.add(ordinateur);
        ressources.add(tablette);
        ressources.add(telephone);

        // Test rendu retard
        LocalDate dateEmprunt = LocalDate.now().minusDays(10); // Emprunt il y a 20 jours
        Emprunt empruntRetard = new Emprunt(utilisateur, telephone, dateEmprunt);
        utilisateur.getEmpruntsActifs().add(empruntRetard); // Ajouter l'emprunt manuellement pour tester un rendu avec retard
    }

    // Demarrer l'application
    public void demarrer() {
        boolean running = true;
        while (running) {
            affichage.afficherMenuPrincipal();
            int role = affichage.lireEntreeEntier();

            switch (role) {
                case 1: // Action de l'utilisateur
                    gererUtilisateur();
                    break;
                case 2: // Action de l'employe
                    gererEmploye();
                    break;
                case 3: // Fin du programme
                    running = false;
                    break;
                default:
                    affichage.erreur();
            }
        }
    }

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
            case 8: // Retour au menu
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

                	    String marque = (String) infosBase[0];
                	    int autonomie = (int) infosBase[1];
                	    int nbCoeurs = (int) infosBase[2];
                	    int memoire = (int) infosBase[3];
                	    int resolution = (int) infosBase[4];
                	    double prix = (double) infosBase[5];
                	    int dureeMax = (int) infosBase[6];
                	    
                	    Ressource ressource = null;

                	    switch (type) {
                	        case 1: // Ordinateur
                	        	Object[] optionsOrdinateur = affichage.saisirOptionsOrdinateur();
                	            boolean portUSB = (boolean) optionsOrdinateur[0];
                	            boolean portUSBC = (boolean) optionsOrdinateur[1];
                	            boolean portHDMI = (boolean) optionsOrdinateur[2];

                	            ressource = employe.AjouterOrdinateur(marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, portUSB, portUSBC, portHDMI);
                	            break;

                	        case 2: // Tablette Graphique
                	        	Object[] optionsTablette = affichage.saisirOptionsTablette();
                	            String logiciel = (String) optionsTablette[0];
                	            boolean estAccessoire = (boolean) optionsTablette[1];

                	            ressource = employe.AjouterTablette(marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, logiciel, estAccessoire);
                	            break;

                	        case 3: // Téléphone
                	        	int numero = affichage.saisirOptionsTelephone();

                	            ressource = employe.AjouterTelephone(marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax, numero);
                	            break;

                	        default:
                	            affichage.erreur();
                	            return;
                	    }

                	    if (ressource != null) {
                	        ressources.add(ressource);
                	    } 
                	    else {
                	        affichage.afficherMessage("Echec de l'ajout.");
                	    }
                    break;
                case 2: // Supprimer une ressource
                	affichage.afficherRessources(ressources);
                    affichage.afficherMessage("ID de la ressource a supprimer : ");
                    int indexS = affichage.lireEntreeEntier();
                    if (employe.supprimerRessource(ressources, indexS)) {
                    } 
                    else {
                        affichage.erreur();
                    }
                    break;
                case 3: // Changer l'état d'une ressource
                	affichage.afficherRessources(ressources);
                    affichage.afficherMessage("ID de la ressource a modifier : ");
                    int indexE = affichage.lireEntreeEntier();
                    affichage.afficherMessage("Nouvel etat : ");
                    String nouvelEtat = affichage.lireEntreeTexte();
                    if (employe.changerEtatRessource(ressources, indexE, nouvelEtat)) {
                    } 
                    else {
                        affichage.erreur();
                    }
                    break;
                case 4: // Afficher toutes les ressources 
                    affichage.afficherRessources(ressources);
                    break;
                case 5: // Retour au menu
                    employeMenu = false;
                    break;
                default: // Choix invalide
                    affichage.erreur();
            }
        }
    }

}
