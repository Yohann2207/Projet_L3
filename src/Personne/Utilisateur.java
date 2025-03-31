package Personne;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import Ressource.Ressource;
import Transaction.Emprunt;
import Transaction.Paiement;
import BDD.BDD;
import java.util.ArrayList;
import java.util.Arrays;


public class Utilisateur extends Personne{
	
	private LocalDate date_inscription;
	private double dette;
	private boolean is_premium;
	private boolean is_a_jour;
	private Paiement[] historique_paiements;
	private ArrayList<Emprunt> empruntsActifs = new ArrayList<>();
	private ArrayList<Emprunt> historiqueEmprunts = new ArrayList<>();
    private static final double PENALITE_PAR_JOUR = 5; // Pénalité de retard en euros par jour

	
	public Utilisateur(int id, String nom, LocalDate date_naissance, String login, String mdp, LocalDate date_inscription) {
		super(id, nom, date_naissance, login, mdp);
		this.date_inscription=date_inscription;
		this.dette=0;
		this.is_premium=false;
		this.is_a_jour=true;
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
	
	public void setIs_premium(boolean is_premium) {
		this.is_premium = is_premium;
	}
	
	
	public void non_a_jour() {
		is_a_jour=false;
	}
	
	// Emprunter une ressource
	public void emprunter(Ressource ressource) {
        if (ressource.isLibre()) {
            Emprunt nouvelEmprunt = new Emprunt(this, ressource, LocalDate.now());
            empruntsActifs.add(nouvelEmprunt);
            ressource.setLibre(false);
            BDD.ajouterEmprunt(ressource.getId(), this.getId());
        }
    }

	//Rendre une ressource
    public void rendre(int index) {
        if (index >= 0 && index < empruntsActifs.size()) {
            Emprunt emp = empruntsActifs.get(index);
            emp.setDate_rendu(LocalDate.now());
            emp.getRessource().setLibre(true);
            emp.setEtat("termine");
            ajouterHistorique(emp);
            long jours_retard = ChronoUnit.DAYS.between(emp.getDate_limite(), emp.getDate_rendu());
            if (jours_retard > 0) {
                dette += jours_retard * PENALITE_PAR_JOUR;
            }
            empruntsActifs.remove(index);
        }
    }

    //Effectuer un paiment
    public void payer(double montant) {
        if (montant > dette) {
            dette = 0;
        } else {
            dette -= montant;
        }
    }

	public ArrayList<Emprunt> getEmpruntsActifs() {
	    return empruntsActifs;
	}
	
	public ArrayList<Emprunt> getHistoriqueEmprunts() {
	    return historiqueEmprunts;
	}
	
	//Ajouter a l'historique des emprunts de l'utilisateur
	public void ajouterHistorique(Emprunt emp) {
	    historiqueEmprunts.add(emp);
	}

	@Override
	public String toString() {
	    return "Id : " + getId() + " | Nom : " + getNom() + " | Login : " + getLogin() + " | Dette : " + getDette() + " euros" + " | Premium : " + (getIs_premium() ? "Oui" : "Non");
	}

}
