package BDD;

import Personne.Utilisateur;
import Ressource.Ressource;

public class BDD {
	
    public static void changer_etat_res(String nv_etat, int id_res) {
        //Requete SQL pour accès à la res (d'où le paramètre id)
        //Requete SQL pour changer l'état vers nv_etat
        System.out.println("Le changement de l'etat de la ressource a ete effectue vers : " + nv_etat);

    }
    public static void ajouter_res() {
        //Requete SQL pour ajouter la ressource (paramètres variables selon notre future BDD) 
        System.out.println("La ressource a ete ajoute avec succes"); //commentaire piuyr test dre
    }

    public static void supprimer_res(int id) {
        //Requete SQL pour supprimer la ressource via son id
        System.out.println("La ressource a ete supprime avec succes");
    }

    public static void ajouter_uti() {
        //Requete SQL pour ajouter l'utilisateur (paramètres variables selon notre future BDD) 
        System.out.println("L'utilisateur a ete ajoute avec succes");
    }

    public static void supprimer_uti(String login) {
        //Requete SQL pour supprimer l'utilisateur via son login
        System.out.println("L'utilisateur a ete supprime avec succes");
    }

    public static void ajouter_paiement(String login) {
        //Requete SQL pour obtenir l'utilisateur via son login 
        //Requete sql pour ajouter un paiement 
        System.out.println("Paiement effectue");
    }

    public static void afficher_historique_paiements(String login) {
        System.out.println("Voici vos paiements : ");
        //Requête SQL pour récupérer l'utilisateur via son login
        //Requete SQL pour afficher l'historique des paiements 

    }

    public static void ajouter_emprunt() {
        //Requete SQL pour ajouter un emprunt (paramètres variables selon notre future BDD) 
        System.out.println("L'emprunt a ete ajoute avec succes");

    }

}
