package Ressource;

public class Tablette_graphique extends Ressource{
	
	private String logiciel;
	private boolean est_accessoire;
	
	public Tablette_graphique(int id, String nom, String marque, int autonomie, int nb_coeurs, int memoire, int resolution_ecran, boolean libre, double prix, int duree_max, String logiciel, boolean est_accessoire) {
		super(id, nom, marque, autonomie, nb_coeurs, memoire, resolution_ecran, libre, prix, duree_max);
		this.logiciel = logiciel;
		this.est_accessoire = est_accessoire;
	}

}
