package Personne;

import java.time.LocalDate;
import java.util.ArrayList;
import BDD.Hashage;

public class Personne {
	
	private String nom;
	private LocalDate date_naissance;
	private String login;
	private String mdp;
	
	public Personne(String nom, LocalDate date_naissance, String login, String mdp) {
		this.nom=nom;
		this.date_naissance=date_naissance;
		this.login=login;
		this.mdp=mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String toString() {
		return "Personne [nom=" + nom + ", date_naissance=" + date_naissance + ", login=" + login + ", mdp=" + mdp
				+ "]";
	}
	
	public static Personne authentifier(String login, String motDePasse, ArrayList<Utilisateur> utilisateurs, ArrayList<Employe> employes) {
		
	    String mdpHash = Hashage.hashermdp(motDePasse);
		
        for (Utilisateur u : utilisateurs) {
            if (u.getLogin().equals(login) && u.getMdp().equals(mdpHash)) {
                return u;
            }
        }

        for (Employe e : employes) {
            if (e.getLogin().equals(login) && e.getMdp().equals(mdpHash)) {
                return e;
            }
        }

        return null;
    }

}
