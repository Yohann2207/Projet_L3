package Personne;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import Ressource.Ressource;
import Transaction.Emprunt;
import Transaction.Paiement;
import BDD.BDD;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
	
	
	
	public int creerNouvelEmprunt(Utilisateur utilisateur, Ressource ressource) {
	    String etat = "En cours";
	    String com = "Aucun commentaire";
	    LocalDate dateEmp = LocalDate.now();
	    LocalDate dateRendu = dateEmp.plusDays(ressource.getDuree_max());
	    int idRes = ressource.getId();
	    int idUti = utilisateur.getId();

	    return BDD.ajouter_emprunt(etat, com, dateRendu, idRes, idUti);
	}
	
	
	
	public boolean rendreEmprunt(Emprunt emprunt) {
	    try {
	        // Marquer l'emprunt comme rendu dans la base de données
	        int empruntId = emprunt.getId(); 
	        BDD.marquerEmpruntRendu(empruntId); 
	        
	        // Marquer la ressource comme libre
	        emprunt.getRessource().setLibre(true);
	        return true; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Retourne false en cas d'erreur
	    }
	}
	
	
    public void payer(double montant) {
        if (montant > dette) {
            dette = 0;
        } else {
            dette -= montant;
        }
    }

    public ArrayList<Emprunt> getMesEmprunts(ArrayList<Utilisateur> utilisateurs, ArrayList<Ressource> ressources) {
        return BDD.recupererEmpruntsParUtilisateur(this.getId(), utilisateurs, ressources);
    }
	
	public ArrayList<Emprunt> getHistoriqueEmprunts() {
	    return historiqueEmprunts;
	}
	
	public void ajouterHistorique(Emprunt emp) {
	    historiqueEmprunts.add(emp);
	}

	@Override
	public String toString() {
	    return "Id : " + getId() + " | Nom : " + getNom() + " | Login : " + getLogin() + " | Dette : " + getDette() + " euros" + " | Premium : " + (getIs_premium() ? "Oui" : "Non");
	}
	
	public static ArrayList<Utilisateur> recupererTous() {
	    return BDD.recupererTousLesUtilisateurs();
	}
	
	public ArrayList<Emprunt> recupererEmpruntsEnCours(ArrayList<Utilisateur> utilisateurs, ArrayList<Ressource> ressources) {
	    return BDD.recupererEmpruntsEnCoursParUtilisateur(this.getId(), utilisateurs, ressources);
	}
	
	public double recupererDette() {
	    return BDD.recuperer_dette(this.getId());
	}

	public void payerDette() {
	    BDD.payer_dette(this.getId());
	}

}
