package Ressource;

public class Ressource {

    private static int id;
    private String marque;
    private int autonomie;
    private int nb_coeurs;
    private int memoire;
    private int resolution_ecran;
    private boolean libre;
    private double prix;
    private int duree_max;
    private String etat; 

    public Ressource(String marque, int autonomie, int nb_coeurs, int memoire, int resolution_ecran, boolean libre, double prix, int duree_max) {
        this.marque = marque;
        this.autonomie = autonomie;
        this.nb_coeurs = nb_coeurs;
        this.memoire = memoire;
        this.resolution_ecran = resolution_ecran;
        this.libre = libre;
        this.prix = prix;
        this.duree_max = duree_max;
        this.etat="neuf";
    }

    public int getDuree_max() {
        return duree_max;
    }
    
    public void setLibre(boolean liberte) {
    	this.libre=liberte;
    	
    }
    
    public void setEtat(String etat) {
    	this.etat=etat;
    	
    }
    
	@Override
	public String toString() {
		return "Ressource [marque=" + marque + ", autonomie=" + autonomie + ", nb_coeurs=" + nb_coeurs + ", memoire="
				+ memoire + ", resolution_ecran=" + resolution_ecran + ", prix=" + prix + ", etat=" + etat + "]";
	}

	public boolean isLibre() {
		return libre;
	}

	public String getEtat() {
		return etat;
	}

}