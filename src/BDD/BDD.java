package BDD;

import Personne.Utilisateur;
import Ressource.Ressource;

public class BDD {
	
    public static void changer_etat_res(String nv_etat, int id_res) {
        //Requete SQL pour accès à la res (d'où le paramètre id)
        //Requete SQL pour changer l'état vers nv_etat
        System.out.println("Le changement de l'état de la ressource à été effectué vers : " + nv_etat);

    }
    public static void ajouter_res() {
        //Requete SQL pour ajouter la ressource (paramètres variables selon notre future BDD) 
        System.out.println("La ressource a été ajouté averc succès");
    }

    public static void supprimer_res(int id) {
        //Requete SQL pour supprimer la ressource via son id
        System.out.println("La ressource a été supprimé avec succès");

    }

    public static void ajouter_uti() {
        //Requete SQL pour ajouter l'utilisateur (paramètres variables selon notre future BDD) 
        System.out.println("L'utilisateur a été ajouté averc succès");
    }

    public static void supprimer_uti(String login) {
        //Requete SQL pour supprimer l'utilisateur via son login
        System.out.println("L'utilisateur a été supprimé avec succès");

    }

    public static void ajouter_paiement(String login) {
        //Requete SQL pour obtenir l'utilisateur via son login 
        //Requete sql pour ajouter un paiement 
        System.out.println("Paiement effectué");
    }

    public static void afficher_historique_paiements(String login) {
        System.out.println("Voici vos paiements : ");
        //Requête SQL pour récupérer l'utilisateur via son login
        //Requete SQL pour afficher l'historique des paiements 

    }

    public static void ajouter_emprunt() {
        //Requete SQL pour ajouter un emprunt (paramètres variables selon notre future BDD) 
        System.out.println("L'emprunt a été ajouté averc succès");

    }

}
