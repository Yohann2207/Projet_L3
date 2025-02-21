package Salle;

import java.util.ArrayList;

import Personne.Utilisateur;

public class Coworking extends Salle{
	
	private final int NB_POSTES=10; 

	public Coworking(int id, ArrayList<Utilisateur> occupants, boolean is_libre,  boolean is_reservable) {
		super(id, occupants, is_libre, is_reservable);
	}

}
