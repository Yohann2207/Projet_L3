package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Statement;
import Personne.Employe;
import Personne.Utilisateur;
import Ressource.Ordinateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;

public class BDD {
	
    public static void changer_etat_res(String nv_etat, int id_res) {
        //Requete SQL pour accès à la res (d'où le paramètre id)
        //Requete SQL pour changer l'état vers nv_etat
    	String sql = "UPDATE Ressource SET etat_res = ? WHERE id_res = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv_etat);
            pstmt.setInt(2, id_res);

            pstmt.executeUpdate();
            
        } catch (SQLException e) {
          
        }
    }
    
    public static int ajouter_res(String nom, String marque, boolean libre, double prix, int dureeMax, String etat, String type) {
        String sql = "INSERT INTO Ressource (nom_res, marque, libre, prix, duree_max, etat_res, type_res) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, nom);
            pstmt.setString(2, marque);
            pstmt.setBoolean(3, libre);
            pstmt.setDouble(4, prix);
            pstmt.setInt(5, dureeMax);
            pstmt.setString(6, etat);
            pstmt.setString(7, type);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
            	ResultSet rs = pstmt.getGeneratedKeys();	//Récupère le ou les identifiants auto-générés par l'insertion.
                if (rs.next()) {	//On vérifie s’il y a au moins une clé générée à récupérer
                    int idRes = rs.getInt(1);	//Récupère la valeur de la première colonne
                    return idRes;
                }
            }

        } catch (SQLException e) {
        	
        }
        
        return -1;
    }

    public static void supprimer_res(int id) {
        //Requete SQL pour supprimer la ressource via son id
    	String sql = "DELETE FROM Ressource WHERE id_res = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        
        } catch (SQLException e) {
        	
        }
    }

    public static void ajouter_uti(String nom, LocalDate date_naissance, String login, String mdp) {
        String sql = "INSERT INTO utilisateur (nom_uti, date_naissance_uti, login_uti, mdp_uti) VALUES (?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            String mdpHash = Hashage.hashermdp(mdp);

            pstmt.setString(1, nom);
            pstmt.setDate(2, java.sql.Date.valueOf(date_naissance));  // conversion LocalDate -> java.sql.Date
            pstmt.setString(3, login);
            pstmt.setString(4, mdpHash);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        	
        }
    }

    public static void supprimer_uti(String login) {
        //Requete SQL pour supprimer l'utilisateur via son login
        String sql = "DELETE FROM utilisateur WHERE login_uti = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        	
        }
    }
    
    public static void ajouter_employe(String nom, LocalDate date_naissance, String login, String mdp, double salaire, String poste) {
        String sql = "INSERT INTO Employe (nom_employe, date_naissance_employe, login_employe, mdp_employe, salaire, poste) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	
            String mdpHash = Hashage.hashermdp(mdp);
        	
            pstmt.setString(1, nom);
            pstmt.setDate(2, java.sql.Date.valueOf(date_naissance));  // conversion LocalDate -> java.sql.Date
            pstmt.setString(3, login);
            pstmt.setString(4, mdpHash);
            pstmt.setDouble(5, salaire);
            pstmt.setString(6, poste);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        	
        }
    }
    
    public static void supprimer_employe(String login) {
        String sql = "DELETE FROM Employe WHERE login_employe = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        	
        }
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
    
    public static ArrayList<Utilisateur> recupererTousLesUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        
        String sql = "SELECT * FROM utilisateur";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom_uti");
                LocalDate date = rs.getDate("date_naissance_uti").toLocalDate();
                String login = rs.getString("login_uti");
                String mdp = rs.getString("mdp_uti");
                LocalDate dateCreation = rs.getDate("date_creation").toLocalDate();
                double dette = rs.getDouble("dette");
                boolean premium = rs.getBoolean("premium");

                Utilisateur utilisateur = new Utilisateur(nom, date, login, mdp, dateCreation);
                utilisateur.setDette(dette);
                utilisateur.setIs_premium(premium);
                utilisateurs.add(utilisateur);
            }

        } catch (SQLException e) {
        	
        }

        return utilisateurs;
    }
    
    public static ArrayList<Employe> recupererTousLesEmployes() {
        ArrayList<Employe> employes = new ArrayList<>();
        
        String sql = "SELECT * FROM employe";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nom = rs.getString("nom_employe");
                LocalDate date = rs.getDate("date_naissance_employe").toLocalDate();
                String login = rs.getString("login_employe");
                String mdpHash = rs.getString("mdp_employe");
                double salaire = rs.getDouble("salaire");
                String poste = rs.getString("poste");
                double prime = 0;

                Employe employe = new Employe(nom, date, login, mdpHash, salaire, prime, poste);

                employes.add(employe);
            }

        } catch (SQLException e) {
        	
        }

        return employes;
    }
    
    public static ArrayList<Ressource> recupererToutesLesRessources() {
        ArrayList<Ressource> ressources = new ArrayList<>();

        String sql = "SELECT * FROM Ressource";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	int id = rs.getInt("id_res");
                String nom = rs.getString("nom_res");
                String marque = rs.getString("marque");
                boolean libre = rs.getBoolean("libre");
                double prix = rs.getDouble("prix");
                int dureeMax = rs.getInt("duree_max");
                String type = rs.getString("type_res"); // Indique si c'est un ordinateur, tablette, etc.

                Ressource ressource = null;

                switch (type) {
                    case "Ordinateur":
                        boolean portUSB = true;
                        boolean portUSBC = true;
                        boolean portHDMI = true;

                        ressource = new Ordinateur(id, nom, marque, 10, 8, 16, 1080, libre, prix, dureeMax, portUSB, portUSBC, portHDMI);
                        break;

                    case "Tablette_graphique":
                        String logiciel = "Non defini";
                        boolean estAccessoire = false;

                        ressource = new Tablette_graphique(id, nom, marque,  8, 4, 8, 720, libre, prix, dureeMax, logiciel, estAccessoire);
                        break;
                        
                    case "Telephone":
                        int numero = 0000000000;

                        ressource = new Telephone(id, nom, marque, 12, 8, 16, 1080, libre, prix, dureeMax, numero);
                        break;

                    default:
                        System.out.println("Type de ressource inconnu : " + type);
                        break;
                }

                if (ressource != null) {
                    ressources.add(ressource);
                }
            }

        } catch (SQLException e) {
        	
        }

        return ressources;
    }
}