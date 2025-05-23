package Vue;

import Ressource.Ressource;
import Transaction.Emprunt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Personne.Employe;
import Personne.Utilisateur;

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
        System.out.println("8. Se deconnecter");
        System.out.print("Choisissez une action : ");
    }

    // Affichage du menu employe
    public void afficherMenuEmploye() {
    	System.out.println("\n--- MENU EMPLOYE ---");
    	System.out.println("1. Ajouter une ressource");
    	System.out.println("2. Supprimer une ressource");
    	System.out.println("3. Changer l'etat d'une ressource");
    	System.out.println("4. Afficher les ressources");
    	System.out.println("5. Ajouter un utilisateur");
    	System.out.println("6. Supprimer un utilisateur");
    	System.out.println("7. Ajouter un employe");
    	System.out.println("8. Supprimer un employe");
    	System.out.println("9. Afficher les utilisateurs");
    	System.out.println("10. Afficher les employes");
    	System.out.println("11. Se deconnecter");
    	System.out.println("Choisissez une action : ");
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
                System.out.println("Erreur : Veuillez entrer un nombre decimal valide !");
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
    
    public LocalDate lireDate() {
        LocalDate date = null;
        boolean saisieCorrecte = false;

        while (!saisieCorrecte) {
            String dateStr = lireEntreeTexte();

            try {
                date = LocalDate.parse(dateStr);
                saisieCorrecte = true;  
            } catch (Exception e) {
            	System.out.println("Format invalide ! Veuillez entrer la date au format AAAA-MM-JJ.\n");
            }
        }

        return date;
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
    
    // Affichage des informations à saisir pour une ressource
    public Object[] saisirInformationsRessource() {
    	System.out.println("Nom : ");
        String nom = lireEntreeTexte();
    	System.out.println("Marque : ");
        String marque = lireEntreeTexte();
        System.out.println("Autonomie (h) : ");
        int autonomie = lireEntreeEntier();
        System.out.println("Nombre de coeurs : ");
        int nbCoeurs = lireEntreeEntier();
        System.out.println("Memoire (Go) : ");
        int memoire = lireEntreeEntier();
        System.out.println("Resolution ecran (px) : ");
        int resolution = lireEntreeEntier();
        System.out.println("Prix (euros) : ");
        double prix = lireEntreeDouble();
        System.out.println("Duree max d'emprunt (jours) : ");
        int dureeMax = lireEntreeEntier();

        return new Object[]{nom, marque, autonomie, nbCoeurs, memoire, resolution, prix, dureeMax};
    }
    
    // Affichage des informations à saisir pour un ordinateur
    public Object[] saisirOptionsOrdinateur() {
        System.out.println("Port USB (true/false) : ");
        boolean portUSB = lireEntreeBoolean();
        System.out.println("Port USB-C (true/false) : ");
        boolean portUSBC = lireEntreeBoolean();
        System.out.println("Port HDMI (true/false) : ");
        boolean portHDMI = lireEntreeBoolean();

        return new Object[]{portUSB, portUSBC, portHDMI};
    }

    // Affichage des informations à saisir pour une tablette
    public Object[] saisirOptionsTablette() {
        System.out.println("Logiciel fourni : ");
        String logiciel = lireEntreeTexte();
        System.out.println("Avec un accessoire ? (true/false) : ");
        boolean estAccessoire = lireEntreeBoolean();

        return new Object[]{logiciel, estAccessoire};
    }

    // Affichage des informations à saisir pour un téléphone
    public int saisirOptionsTelephone() {
        System.out.println("Numero de telephone : ");
        return lireEntreeEntier();
    }
    
    /*
    public Utilisateur saisirNouvelUtilisateur() {
    	System.out.println("Nom : ");
        String nom = lireEntreeTexte();
        System.out.println("Date de naissance (YYYY-MM-DD) : ");
        LocalDate dateNaissance = lireDate();
        System.out.println("Login : ");
        String login = lireEntreeTexte();
        System.out.println("Mot de passe : ");
        String mdp = lireEntreeTexte();
        LocalDate dateInscription = LocalDate.now();

        return new Utilisateur(nom, dateNaissance, login, mdp, dateInscription);
    }
    
    public Employe saisirNouvelEmploye() {
    	System.out.println("Nom : ");
        String nom = lireEntreeTexte();
        System.out.println("Date de naissance (YYYY-MM-DD) : ");
        LocalDate dateNaissance = lireDate();
        System.out.println("Login : ");
        String login = lireEntreeTexte();
        System.out.println("Mot de passe : ");
        String mdp = lireEntreeTexte();
        System.out.println("Salaire : ");
        double salaire = lireEntreeDouble();
        double prime = 0;
        System.out.println("Poste : ");
        String poste = lireEntreeTexte();

        return new Employe(nom, dateNaissance, login, mdp, salaire, prime, poste);
    }
    */
    
    public void afficherListeUtilisateurs(ArrayList<Utilisateur> utilisateurs) {
        if (utilisateurs.isEmpty()) {
        	System.out.println("Aucun utilisateur enregistre.");
        } else {
        	System.out.println("\n--- LISTE DES UTILISATEURS ---");
            for (Utilisateur u : utilisateurs) {
            	System.out.println(u.toString());
            }
        }
    }
    
    public void afficherListeEmployes(ArrayList<Employe> employes) {
        if (employes.isEmpty()) {
        	System.out.println("Aucun employe enregistre.");
        } else {
        	System.out.println("\n--- LISTE DES EMPLOYES ---");
            for (Employe e : employes) {
            	System.out.println(e.toString());
            }
        }
    }
    
    // Message d'erreur
    public void erreur() {
    	System.out.println("Choix invalide.");
    }

}
