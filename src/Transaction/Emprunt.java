package Transaction;

import java.time.LocalDate;


import Personne.Utilisateur;
import Ressource.Ressource;

public class Emprunt {
	
	private Utilisateur utilisateur;
	private Ressource ressource;
	private LocalDate date_emprunt;
	private int duree_max;
	private LocalDate date_rendu=null;
	private String etat;
	private LocalDate date_limite=null;

	
	public Emprunt(Utilisateur utilisateur, Ressource ressource, LocalDate date) {
		this.utilisateur=utilisateur;
		this.ressource=ressource;
		this.date_emprunt= date;
		this.duree_max=0;
		this.etat="en cours";
	    setDuree();
	    setDate_limite();
	    //Test rendu retard
	    System.out.println("Date emprunt initialisee : " + this.date_emprunt);
	    System.out.println("Date limite calculee : " + this.date_limite);
	}
	

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
	
	public void terminerEmprunt() {
	    this.date_rendu = LocalDate.now(); // Enregistre la date de retour
	    this.ressource.setLibre(true); // Marque la ressource comme disponible
	    this.etat = "Terminé";
	}
	
	public Ressource getRessource() {
	    return this.ressource;
	}
	
	@Override
	public String toString() {
		return "Emprunt [utilisateur=" + utilisateur + ", ressource=" + ressource + ", date_emprunt=" + date_emprunt
				+ ", duree_max=" + duree_max + ", date_rendu=" + date_rendu + ", etat=" + etat + ", date_limite="
				+ date_limite + "]";
	}

	
}
