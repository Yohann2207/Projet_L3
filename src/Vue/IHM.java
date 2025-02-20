package Vue;

import Ressource.Ressource;
import Transaction.Emprunt;

import java.util.ArrayList;
import java.util.Scanner;

public class IHM {
    
    private final Scanner scanner;

    public IHM() {
        this.scanner = new Scanner(System.in);
    }

    // Affichage du menu principal
    public void afficherMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Utilisateur");
        System.out.println("2. Employe");
        System.out.println("3. Quitter");
        System.out.print("Choisissez votre role : ");
    }

    // Affichage du menu utilisateur
    public void afficherMenuUtilisateur() {
        System.out.println("\n--- MENU UTILISATEUR ---");
        System.out.println("1. Afficher les ressources disponibles");
        System.out.println("2. Emprunter une ressource");
        System.out.println("3. Rendre une ressource");
        System.out.println("4. Afficher la dette");
        System.out.println("5. Effectuer un paiement");
        System.out.println("6. Voir les emprunts en cours");
        System.out.println("7. Voir l historique des emprunts");
        System.out.println("8. Retour au menu principal");
        System.out.print("Choisissez une action : ");
    }

    // Affichage du menu employe
    public void afficherMenuEmploye() {
        System.out.println("\n--- MENU EMPLOYE ---");
        System.out.println("1. Ajouter une ressource");
        System.out.println("2. Supprimer une ressource");
        System.out.println("3. Changer l etat d une ressource");
        System.out.println("4. Afficher les ressources");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une action : ");
    }

    // Afficher les ressources disponibles
    public void afficherRessources(ArrayList<Ressource> ressources) {
        if (ressources.isEmpty()) {
            System.out.println("Aucune ressource disponible.");
        } else {
            System.out.println("\n--- Liste des ressources ---");
            for (int i = 0; i < ressources.size(); i++) {
                System.out.println(i + " - " + ressources.get(i));
            }
        }
    }

    // Afficher un message simple
    public void afficherMessage(String message) {
        System.out.println(message);
    }

    // Lire un entier saisi par l utilisateur
    public int lireEntreeEntier() {
        int choix = scanner.nextInt();
        scanner.nextLine();
        return choix;
    }

    // Lire une chaine de caracteres saisie par l utilisateur
    public String lireEntreeTexte() {
        return scanner.nextLine();
    }
    
    // --- Affichage des dettes et paiements ---
    public void afficherDette(double dette) {
        System.out.println("Votre dette actuelle est de " + dette + " euros.");
    }
    
    // --- Affichage des emprunts ---
    public void afficherEmpruntsActifs(ArrayList<Emprunt> emprunts) {
        if (emprunts.isEmpty()) {
            System.out.println("Vous n'avez aucune ressource empruntee !");
        } else {
            System.out.println("\n--- Emprunts en cours ---");
            for (int i = 0; i < emprunts.size(); i++) {
                System.out.println(i + " - " + emprunts.get(i).getRessource());
            }
        }
    }
    
    public void afficherHistoriqueEmprunts(ArrayList<Emprunt> historique) {
        if (historique.isEmpty()) {
            System.out.println("Aucun emprunt termine.");
        } else {
            System.out.println("\n--- Historique des emprunts ---");
            for (Emprunt emp : historique) {
                System.out.println(emp);
            }
        }
    }
}
