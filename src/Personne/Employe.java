package Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Ressource.Ordinateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import Vue.IHM;

public class Employe extends Personne{
	
    private final IHM affichage;
	private double salaire; //a voir rajouter cu 
	private double prime; //a voir rajouter cu 
	private String role; // exemple admin 
	
	public Employe(String nom, LocalDate date_naissance, String login, String mdp, double salaire, double prime, String role) {
		super(nom, date_naissance, login, mdp);
		this.salaire=salaire;
		this.prime=prime;
		this.role=role;
        this.affichage = new IHM();
	}
	
	// Ajouter une ressource avec saisie utilisateur
    public void ajouterRessource(ArrayList<Ressource> ressources) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- AJOUT D'UNE RESSOURCE ---");
        System.out.println("Choisissez le type de ressource :");
        System.out.println("1. Ordinateur");
        System.out.println("2. Tablette Graphique");
        System.out.println("3. Téléphone");
        System.out.print("Votre choix : ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Marque : ");
        String marque = scanner.nextLine();
        System.out.print("Autonomie (h) : ");
        int autonomie = scanner.nextInt();
        System.out.print("Nombre de cœurs : ");
        int nbCoeurs = scanner.nextInt();
        System.out.print("Mémoire (Go) : ");
        int memoire = scanner.nextInt();
        System.out.print("Résolution écran (px) : ");
        int resolution = scanner.nextInt();
        System.out.print("Prix (€) : ");
        double prix = scanner.nextDouble();
        System.out.print("Durée max d'emprunt (jours) : ");
        int dureeMax = scanner.nextInt();
        scanner.nextLine(); 

        Ressource nouvelleRessource = null;

        switch (type) {
            case 1: // Ordinateur
                System.out.print("Port USB (true/false) : ");
                boolean portUSB = scanner.nextBoolean();
                System.out.print("Port USB-C (true/false) : ");
                boolean portUSBC = scanner.nextBoolean();
                System.out.print("Port HDMI (true/false) : ");
                boolean portHDMI = scanner.nextBoolean();
                scanner.nextLine(); 

                nouvelleRessource = new Ordinateur(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, portUSB, portUSBC, portHDMI);
                break;

            case 2: // Tablette Graphique
                System.out.print("Logiciel fourni : ");
                String logiciel = scanner.nextLine();
                System.out.print("Avec un accessoire ? (true/false) : ");
                boolean estAccessoire = scanner.nextBoolean();

                nouvelleRessource = new Tablette_graphique(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, logiciel, estAccessoire);
                break;

            case 3: // Téléphone
                System.out.print("Numéro de téléphone (ex: 123456789) : ");
                int numero = scanner.nextInt();

                nouvelleRessource = new Telephone(marque, autonomie, nbCoeurs, memoire, resolution, true, prix, dureeMax, numero);
                break;

            default:
                System.out.println("Type invalide.");
                return;
        }

        ressources.add(nouvelleRessource);
        System.out.println("Ressource ajoutée avec succès !");
    }

    // Supprimer une ressource par index avec vérification si la liste est vide
    public void supprimerRessource(ArrayList<Ressource> ressources) {
        if (ressources.isEmpty()) {
            System.out.println("Aucune ressource disponible à supprimer !");
            return;
        }

        affichage.afficherRessources(ressources);
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID de la ressource à supprimer : ");
        int index = scanner.nextInt();

        if (index >= 0 && index < ressources.size()) {
            ressources.remove(index);
            System.out.println("Ressource supprimée avec succès.");
        } else {
            System.out.println("Échec de la suppression : Index invalide.");
        }
    }

    // Modifier l'état d'une ressource avec vérification si la liste est vide
    public void changerEtatRessource(ArrayList<Ressource> ressources) {
        if (ressources.isEmpty()) {
            System.out.println("Aucune ressource disponible à modifier !");
            return;
        }

        affichage.afficherRessources(ressources);
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID de la ressource à modifier : ");
        int index = scanner.nextInt();
        scanner.nextLine(); 

        if (index >= 0 && index < ressources.size()) {
            Ressource ressource = ressources.get(index);
            System.out.print("Nouvel état de la ressource : ");
            String nouvelEtat = scanner.nextLine();
            System.out.println("Ancien état : " + ressource.getEtat());
            ressource.setEtat(nouvelEtat);
            System.out.println("État mis à jour avec succès !");
        } else {
            System.out.println("Échec de la mise à jour : Index invalide.");
        }
    }
}
