package Transaction;

import java.time.LocalDate;
import Personne.Utilisateur;
import Ressource.Ressource;

public class Emprunt {
	
	private int id;
	private Utilisateur utilisateur;
	private Ressource ressource;
	private LocalDate date_emprunt;
	private int duree_max;
	private LocalDate date_rendu=null;
	private String etat;
	private LocalDate date_limite=null;

	
	public Emprunt(int id, Utilisateur utilisateur, Ressource ressource, LocalDate date_emprunt, LocalDate date_rendu) {
		this.utilisateur=utilisateur;
		this.ressource=ressource;
		this.date_emprunt=date_emprunt;
		this.date_rendu=date_rendu;
		this.duree_max=0;
		this.etat="en cours";
	    setDuree();
	    setDate_limite();
	}
	
	//Durée max de l'emprunt
	public void setDuree() {
		duree_max=ressource.getDuree_max();
		if (utilisateur.getIs_premium()) {
			duree_max=duree_max+15;
		}
	}
	
	public void setDate_rendu(LocalDate date) {
		this.date_rendu=date; 
	}
	
	public LocalDate getDate_rendu() {
		return date_rendu;
	}
	
	public LocalDate getDate_emprunt() {
		return date_emprunt;
	}
	
	public int getDuree_max() {
		return duree_max;
	}
	
	public LocalDate getDate_limite() {
		return date_limite;
	}
	
	public void setDate_limite() {
		this.date_limite=getDate_emprunt().plusDays(duree_max); 
	}
	
	public Ressource getRessource() {
	    return this.ressource;
	}
	
	public String toString() {
		 return "ID: " + id + " | Utilisateur: " + utilisateur.getNom() + " | Ressource: " + ressource.getNom() + " | Début: " + date_emprunt + " | Limite: " + date_limite;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}
	
}
