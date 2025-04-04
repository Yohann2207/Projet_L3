package Ressource;

import java.util.ArrayList;

import BDD.BDD;

public class Ressource {

    private int id;
    private String nom;
    private String marque;
    private int autonomie;
    private int nb_coeurs;
    private int memoire;
    private int resolution_ecran;
    private boolean libre;
    private double prix;
    private int duree_max;
    private String etat; 

    public Ressource(int id, String nom, String marque, int autonomie, int nb_coeurs, int memoire, int resolution_ecran, boolean libre, double prix, int duree_max) {
    	this.id = id;
    	this.nom = nom;
        this.marque = marque;
        this.autonomie = autonomie;
        this.nb_coeurs = nb_coeurs;
        this.memoire = memoire;
        this.resolution_ecran = resolution_ecran;
        this.libre = libre;
        this.prix = prix;
        this.duree_max = duree_max;
        this.etat="Neuf";
    }

    public Ressource(int id_res, String nom_res, String marque2, boolean libre2, double prix2, int duree_max2, String etat_res, String type_res) {
    	this.id = id_res;
    	this.nom = nom_res;
    	this.marque = marque2;
    	this.libre = libre2;
    	this.prix = prix2;
    	this.duree_max = duree_max2;
    	this.etat = etat_res != null ? etat_res : "Neuf"; 
    }

	public int getDuree_max() {
        return duree_max;
    }
    
    public void setLibre(boolean i) {
    	this.libre=i;
    	
    }
    
    public void setEtat(String etat) {
    	this.etat=etat;
    	
    }
    
	public String toString() {
		return getClass().getSimpleName() + " [id=" + id
		+ ", nom=" + nom
		+ ", marque=" + marque
        + ", autonomie=" + autonomie + "h"
        + ", nb_coeurs=" + nb_coeurs
        + ", memoire=" + memoire + "Go"
        + ", resolution_ecran=" + resolution_ecran + "px"
        + ", prix=" + prix + "euros"
        + ", etat=" + etat + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public boolean isLibre() {
		return libre;
	}

	public String getEtat() {
		return etat;
	}

	public String getMarque() {
		return marque;
	}

	public static ArrayList<Ressource> recupererToutesS() {
	    return BDD.recupererToutesLesRessources();
	}
	
	public ArrayList<Ressource> recupererToutes() {
	    return BDD.recupererToutesLesRessources(); // ici OK car c’est dans le modèle
	}
	
	public static int ajouterEnBDD(String nom, String marque, boolean libre, double prix, int dureeMax, String etat, String type) {
	    return BDD.ajouter_res(nom, marque, libre, prix, dureeMax, etat, type);
	}

}