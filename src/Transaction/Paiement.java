package Transaction;

import java.time.LocalDate;

import Personne.Utilisateur;


public class Paiement {
	
	private LocalDate date;
	private Utilisateur uti; 
	
	public Paiement(LocalDate date, Utilisateur uti) {
		this.date=date;
		this.uti=uti; 
	}

}
