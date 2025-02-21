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
        System.out.println("7. Voir l'historique des emprunts");
        System.out.println("8. Retour au menu principal");
        System.out.print("Choisissez une action : ");
    }

    // Affichage du menu employe
    public void afficherMenuEmploye() {
        System.out.println("\n--- MENU EMPLOYE ---");
        System.out.println("1. Ajouter une ressource");
        System.out.println("2. Supprimer une ressource");
        System.out.println("3. Changer l'etat d une ressource");
        System.out.println("4. Afficher les ressources");
        System.out.println("5. Retour au menu principal");
        System.out.print("Choisissez une action : ");
    }
    
 // Menu lors de l'ajout d'une ressource
    public void affichermenuAjoutRes() {
    	System.out.println("\n--- AJOUT D'UNE RESSOURCE ---");
        System.out.println("Choisissez le type de ressource :");
        System.out.println("1. Ordinateur");
        System.out.println("2. Tablette Graphique");
        System.out.println("3. Telephone");
        System.out.print("Votre choix : ");
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

    // Lire un entier saisi par l'utilisateur
    public int lireEntreeEntier() {
    	while (true) {
            try {
                int valeur = Integer.parseInt(scanner.nextLine().trim());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier valide !");
            }
        }
    }
    
    // Lire un double saisi par l'utilisateur
    public double lireEntreeDouble() {
    	while (true) {
            try {
                double valeur = Double.parseDouble(scanner.nextLine().trim());
                return valeur;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Veuillez entrer un nombre décimal valide !");
            }
        }
    }
    
    // Lire un boolean
    public boolean lireEntreeBoolean() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")){
                return true;
            } else if (input.equals("false")){
                return false;
            } else {
                System.out.println("Veuillez entrer 'true' ou 'false' !");
            }
        }
    }

    // Lire une chaine de caracteres saisie par l'utilisateur
    public String lireEntreeTexte() {
    	String choix = scanner.nextLine();
        return choix;
    }
    
    // Affichage des dettes et paiements
    public void afficherDette(double dette) {
        System.out.println("Votre dette actuelle est de " + dette + " euros.");
    }
    
    // Affichage des emprunts en cours
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
    
    // Affichage des emprunts
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
    
    // Message d'erreur
    public void erreur() {
    	System.out.println("Choix invalide.");
    }

}
