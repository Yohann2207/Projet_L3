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
    
    private final IHM affichage;
    private final Utilisateur utilisateur;
    private final Employe employe;
    private final ArrayList<Ressource> ressources;

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
        utilisateur.getEmpruntsActifs().add(empruntRetard); // Ajouter l'emprunt manuellement
    }

    // Demarrer l'application
    public void demarrer() {
        boolean running = true;
        while (running) {
            affichage.afficherMenuPrincipal();
            int role = affichage.lireEntreeEntier();

            switch (role) {
                case 1:
                    gererUtilisateur();
                    break;
                case 2:
                    gererEmploye();
                    break;
                case 3:
                    running = false;
                    affichage.afficherMessage("Fin du programme.");
                    break;
                default:
                    affichage.afficherMessage("Choix invalide.");
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
            case 1:
                affichage.afficherRessources(ressources);
                break;
            case 2:
                utilisateur.emprunter(ressources);
                break;
            case 3:
                utilisateur.rendre();
                break;
            case 4:
                utilisateur.afficherDette();
                break;
            case 5:
                utilisateur.payer();
                break;
            case 6:
                utilisateur.afficherEmpruntsActifs();
                break;
            case 7:
                utilisateur.afficherHistoriqueEmprunts();
                break;
            case 8:
                userMenu = false;
                break;
            default:
                affichage.afficherMessage("Choix invalide.");
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
                case 1:
                    employe.ajouterRessource(ressources);
                    break;
                case 2:
                    employe.supprimerRessource(ressources);
                    break;
                case 3:
                    employe.changerEtatRessource(ressources);
                    break;
                case 4:
                    affichage.afficherRessources(ressources);
                    break;
                case 5:
                    employeMenu = false;
                    break;
                default:
                    affichage.afficherMessage("Choix invalide.");
            }
        }
    }
}
