package Salle;

import Personne.Utilisateur;
import java.util.ArrayList;

public class Salle {
	
	private static int id;
	private ArrayList<Utilisateur> occupants; 
	private boolean is_libre; 
	private boolean is_reservable; 

	public Salle(int id, ArrayList<Utilisateur> occupants, boolean is_libre,  boolean is_reservable) {
		this.id = id;
		this.occupants = new ArrayList<>(occupants);
		this.is_libre = is_libre;
		this.is_reservable = is_reservable;
	}

}
