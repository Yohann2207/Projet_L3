package Transaction;

import java.util.Date;

import Personne.Employe;
import Personne.Utilisateur;
import Salle.Coworking;

public class Reservation {
	
	private Employe employe;
	private Utilisateur utilisateur;
	private Date date_reservation;
	private Coworking salle; 
	
	public Reservation() {
		this.employe=employe;
		this.utilisateur=utilisateur;
		this.date_reservation=date_reservation;
		this.salle=salle; 
	}
	
	

}
