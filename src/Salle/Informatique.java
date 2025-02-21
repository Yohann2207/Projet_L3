package Salle;

import Ressource.Ordinateur;
import java.util.ArrayList;
import Personne.Utilisateur;

public class Informatique extends Salle{
	
	private ArrayList<Ordinateur> postes; 
	
	public Informatique(int id, ArrayList<Utilisateur> occupants, boolean is_libre,  boolean is_reservable, ArrayList<Ordinateur> postes) {
		super(id, occupants, is_libre, is_reservable);
		this.postes = new ArrayList<>(postes);
	}

}
