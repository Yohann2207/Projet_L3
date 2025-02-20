package Ressource;

public class Telephone extends Ressource{
	
	private int numero;

	public Telephone(String marque, int autonomie, int nb_coeurs, int memoire, int resolution_ecran, boolean libre, double prix, int duree_max, int numero) {
		super(marque, autonomie, nb_coeurs, memoire, resolution_ecran, libre, prix, duree_max);
		this.numero = numero;
	}

}
