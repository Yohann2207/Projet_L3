package Personne;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import Ressource.Ressource;
import Transaction.Emprunt;
import Transaction.Paiement;
import Vue.IHM;

import java.util.ArrayList;
import java.util.Scanner;


public class Utilisateur extends Personne{
	
    private final IHM affichage;
	private LocalDate date_inscription;
	private double dette;
	private boolean is_premium;
	private boolean is_a_jour;
	private Paiement[] historique_paiements;
	private ArrayList<Emprunt> empruntsActifs = new ArrayList<>();
	private ArrayList<Emprunt> historiqueEmprunts = new ArrayList<>();
    private static final double PENALITE_PAR_JOUR = 5.0; // Pénalité de retard en euros par jour

	
	public Utilisateur(String nom, LocalDate date_naissance, String login, String mdp, LocalDate date_inscription) {
		super(nom, date_naissance, login, mdp);
		this.date_inscription=date_inscription;
		this.dette=0;
		this.is_premium=false;
		this.is_a_jour=true;
		this.affichage = new IHM();
	}

	public LocalDate getDate_inscription() {
		return date_inscription;
	}

	public void setDate_inscription(LocalDate date_inscription) {
		this.date_inscription = date_inscription;
	}

	public double getDette() {
		return dette;
	}

	public void setDette(double dette) {
		this.dette = dette;
	}
	
	public boolean getIs_premium() {
		return is_premium; 
	}
	
	public void devenir_premium() {
		is_premium=true; 
	}
	
	
	public void non_a_jour() {
		is_a_jour=false;
	}
	
	/*
	public void emprunter(Ressource res) {
		LocalDate date = LocalDate.now();
		Emprunt emprunt = new Emprunt(this, res, date);
		
		emprunt.setDuree(); 
		
		res.setLibre(false);
		
	}
	
	public void rendre(Ressource res, Emprunt emp) {
		LocalDate date = LocalDate.now();
		emp.setDate_rendu(date);
		res.setLibre(true);
	    ajouterHistorique(emp); // Ajoute l'emprunt dans l'historique
		if (emp.getDate_rendu().isAfter(emp.getDate_limite())) {
			long jours_retard = ChronoUnit.DAYS.between(emp.getDate_limite(), emp.getDate_rendu());
			System.out.println("Jours de retard : " + jours_retard);
			dette=dette + jours_retard*5;
		}
		
	}
	
	public void payer(double montant) {
		 if (montant <= 0) {
		        System.out.println("Montant invalide !");
		        return;
		    }

		    if (montant > dette) {
		        System.out.println("Vous avez paye " + dette + " euros et le reste (" + (montant - dette) + " euros) vous sera rendu.");
		        dette = 0;
		    } else {
		        dette -= montant;
		        System.out.println("Paiement de " + montant + " euros effectue. Dette restante : " + dette + " euros.");
		    }
	}
	*/
	
	// Ajouter une ressource à la liste des emprunts
    public void emprunter(ArrayList<Ressource> ressources) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Ressource> ressourcesDisponibles = new ArrayList<>();

        for (Ressource r : ressources) {
            if (r.isLibre()) {
                ressourcesDisponibles.add(r);
            }
        }

        if (ressourcesDisponibles.isEmpty()) {
            System.out.println("Aucune ressource disponible !");
            return;
        }

        System.out.println("\nRessources disponibles :");
        for (int i = 0; i < ressourcesDisponibles.size(); i++) {
            System.out.println(i + " - " + ressourcesDisponibles.get(i));
        }

        System.out.print("Choisissez une ressource a emprunter : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < ressourcesDisponibles.size()) {
            Ressource ressourceChoisie = ressourcesDisponibles.get(index);
            Emprunt nouvelEmprunt = new Emprunt(this, ressourceChoisie, LocalDate.now());
            empruntsActifs.add(nouvelEmprunt);
            ressourceChoisie.setLibre(false);
            System.out.println("Vous avez emprunte : " + ressourceChoisie);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    // Rendre une ressource (ajout du test de dette en cas de retard)
    public void rendre() {
    	Scanner scanner = new Scanner(System.in);

        if (empruntsActifs.isEmpty()) {
            System.out.println("Vous n'avez aucune ressource empruntee !");
            return;
        }

        System.out.println("\nRessources empruntees :");
        for (int i = 0; i < empruntsActifs.size(); i++) {
            System.out.println(i + " - " + empruntsActifs.get(i).getRessource());
        }

        System.out.print("Choisissez une ressource à rendre : ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 0 && index < empruntsActifs.size()) {
            Emprunt emp = empruntsActifs.get(index);
            LocalDate date = LocalDate.now(); // Date de rendu
            emp.setDate_rendu(date);
            emp.getRessource().setLibre(true);
            ajouterHistorique(emp); // Ajout à l'historique

            // Vérifier s'il y a un retard
            if (emp.getDate_rendu().isAfter(emp.getDate_limite())) {
                long jours_retard = ChronoUnit.DAYS.between(emp.getDate_limite(), emp.getDate_rendu());
                System.out.println("Jours de retard : " + jours_retard);
                dette += jours_retard * PENALITE_PAR_JOUR; // Pénalité de 5€/jour de retard
            }

            empruntsActifs.remove(index);
            System.out.println("Ressource rendue avec succes !");
            System.out.println("Nouvelle dette apres retour : " + dette + " euros.");
        } else {
            System.out.println("Choix invalide.");
        }
    }

    // Effectuer un paiement
    public void payer() {
        Scanner scanner = new Scanner(System.in);

        if (dette > 0) {
            System.out.println("Votre dette actuelle est de " + dette + " euros.");
            System.out.print("Entrez le montant que vous souhaitez payer : ");
            double montant = scanner.nextDouble();
            scanner.nextLine();

            if (montant > dette) {
                System.out.println("Vous avez paye " + dette + " euros, le reste vous sera rendu.");
                dette = 0;
            } else {
                dette -= montant;
                System.out.println("Paiement de " + montant + " euros effectue. Dette restante : " + dette + " euros.");
            }
        } else {
            System.out.println("Vous n'avez aucune dette à payer !");
        }
    }
	
	public void ajouterEmprunt(Emprunt emprunt) {
	    empruntsActifs.add(emprunt);
	}

	public void rendreEmprunt(Emprunt emprunt) {
		 emprunt.getRessource().setLibre(true); // Rendre la ressource disponible
		 empruntsActifs.remove(emprunt);
	}

	public ArrayList<Emprunt> getEmpruntsActifs() {
	    return empruntsActifs;
	}
	
	public void ajouterHistorique(Emprunt emp) {
	    historiqueEmprunts.add(emp);
	}
	
	public void afficherHistorique() {
	    if (historiqueEmprunts.isEmpty()) {
	        System.out.println("Aucun emprunt termine.");
	    } else {
	        System.out.println("\n--- Historique des emprunts ---");
	        for (Emprunt emp : historiqueEmprunts) {
	            System.out.println(emp.toString());
	        }
	    }
	}
	
	public void afficherListeRessources(ArrayList<Ressource> ressources) {
        affichage.afficherRessources(ressources);
    }

    public void afficherDette() {
        affichage.afficherDette(dette);
    }
    
    public void afficherEmpruntsActifs() {
        affichage.afficherEmpruntsActifs(empruntsActifs);
    }

    public void afficherHistoriqueEmprunts() {
        affichage.afficherHistoriqueEmprunts(historiqueEmprunts);
    }
	
}
