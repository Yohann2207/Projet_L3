package Transaction;

import java.time.LocalDate;

import BDD.BDD;
import Personne.Utilisateur;


public class Paiement {
	
	private int id;
	private double montant;
	private LocalDate date;
	private String methode;
	private Utilisateur uti; 
	
	public Paiement(int id, double montant, String méthode, Utilisateur uti) {
		this.id =id;
		this.montant = montant;
		this.date = LocalDate.now();
		this.methode = méthode;
		this.uti=uti; 
	}
	
    public boolean payer() {
        // Vérifier si l'utilisateur a une dette ou si le montant est valide
        if (uti.getDette() <= 0 || montant <= 0) {
            return false;
        }

        double detteRestante = uti.getDette();
        double montantAPayer = Math.min(montant, detteRestante);

        //BDD.ajouterPaiement(id, montantAPayer, date, methode, uti);

        uti.setDette(detteRestante - montantAPayer);

        //BDD.majDetteUtilisateur(uti.getId(), uti.getDette());

        return true;
    }

    public double getMontant() {
        return montant;
    }

    public String getMethode() {
        return methode;
    }

    public LocalDate getDate() {
        return date;
    }

    public Utilisateur getUtilisateur() {
        return uti;
    }

}
