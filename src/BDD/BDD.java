package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Statement;
import Personne.Employe;
import Personne.Personne;
import Personne.Utilisateur;
import Ressource.Ordinateur;
import Ressource.Ressource;
import Ressource.Tablette_graphique;
import Ressource.Telephone;
import Transaction.Emprunt;

public class BDD {
	
    public static void changer_etat_res(String nv_etat, int id_res) {
    	String sql = "UPDATE Ressource SET etat_res = ? WHERE id_res = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nv_etat);
            pstmt.setInt(2, id_res);

            pstmt.executeUpdate();
            
        } catch (SQLException e) {
          
        }
    }
    
    public static int ajouter_emprunt(String etat, String com, LocalDate dateRendu, int idRes, int idUti) {
        String sql = "INSERT INTO Emprunt (date_emp, etat_emp, com, date_rendu, id_res, id_uti) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            pstmt.setString(2, etat);
            pstmt.setString(3, com);
            pstmt.setDate(4, java.sql.Date.valueOf(dateRendu));
            pstmt.setInt(5, idRes);
            pstmt.setInt(6, idUti);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                changerDisponibiliteRessource(idRes, false);
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1); // Retourne l'ID de l'emprunt ajouté
                }
            }

        } catch (SQLException e) {
        	
        }

        return -1;
    }
    
    
    public static void marquerEmpruntRendu(int empruntId) throws SQLException {
        Connection conn = null;
        PreparedStatement psEmprunt = null;
        PreparedStatement psRessource = null;

        try {
            conn = Connexion_BDD.getConnection();
            conn.setAutoCommit(false); 

            String queryEmprunt = "UPDATE emprunt SET date_rendu = CURDATE(), etat_emp = 'Rendu' WHERE id_emp = ?";
            psEmprunt = conn.prepareStatement(queryEmprunt);
            psEmprunt.setInt(1, empruntId);
            psEmprunt.executeUpdate();

            String queryResourceId = "SELECT id_res FROM emprunt WHERE id_emp = ?";
            PreparedStatement psResourceId = conn.prepareStatement(queryResourceId);
            psResourceId.setInt(1, empruntId);
            ResultSet rs = psResourceId.executeQuery();
            int resourceId = -1;
            if (rs.next()) {
                resourceId = rs.getInt("id_res");
            }
            rs.close();
            psResourceId.close();

            if (resourceId != -1) {
                String queryRessource = "UPDATE ressource SET libre = 1 WHERE id_res = ?";
                psRessource = conn.prepareStatement(queryRessource);
                psRessource.setInt(1, resourceId);
                psRessource.executeUpdate();
            }

            conn.commit(); //màj BDD
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e; 
        } finally {
            if (psEmprunt != null) {
                psEmprunt.close();
            }
            if (psRessource != null) {
                psRessource.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); 
                conn.close();
            }
        }
    }

    
    public static void changerDisponibiliteRessource(int idRes, boolean libre) {
        String sql = "UPDATE Ressource SET libre = ? WHERE id_res = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, libre);
            pstmt.setInt(2, idRes);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
    	String sql = "DELETE FROM Ressource WHERE id_res = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        
        } catch (SQLException e) {
        	
        }
    }
    
    
    public static int ajouter_uti(String nom, LocalDate date_naissance, String login, String mdp) {
        String sql = "INSERT INTO utilisateur (nom_uti, date_naissance_uti, login_uti, mdp_uti) VALUES (?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            String mdpHash = Hashage.hashermdp(mdp);

            pstmt.setString(1, nom);
            pstmt.setDate(2, java.sql.Date.valueOf(date_naissance));
            pstmt.setString(3, login);
            pstmt.setString(4, mdpHash);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
        	
        }

        return -1;
    }


    public static void supprimer_uti(String login) {
        String sql = "DELETE FROM utilisateur WHERE login_uti = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);

            pstmt.executeUpdate();

        } catch (SQLException e) {
        	
        }
    }
    
    
    public static int ajouter_employe(String nom, LocalDate date_naissance, String login, String mdp, double salaire, String poste) {
        String sql = "INSERT INTO Employe (nom_employe, date_naissance_employe, login_employe, mdp_employe, salaire, poste) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        	
            String mdpHash = Hashage.hashermdp(mdp);
        	
            pstmt.setString(1, nom);
            pstmt.setDate(2, java.sql.Date.valueOf(date_naissance));  // conversion LocalDate -> java.sql.Date
            pstmt.setString(3, login);
            pstmt.setString(4, mdpHash);
            pstmt.setDouble(5, salaire);
            pstmt.setString(6, poste);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
        	
        }
        return -1; 
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
    
    
    public static ArrayList<Utilisateur> recupererTousLesUtilisateurs() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        
        String sql = "SELECT * FROM utilisateur";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	int id = rs.getInt("id_uti");
                String nom = rs.getString("nom_uti");
                LocalDate date = rs.getDate("date_naissance_uti").toLocalDate();
                String login = rs.getString("login_uti");
                String mdp = rs.getString("mdp_uti");
                LocalDate dateCreation = rs.getDate("date_creation").toLocalDate();
                double dette = rs.getDouble("dette");
                boolean premium = rs.getBoolean("premium");

                Utilisateur utilisateur = new Utilisateur(id, nom, date, login, mdp, dateCreation);
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
            	int id = rs.getInt("id_employe");
                String nom = rs.getString("nom_employe");
                LocalDate date = rs.getDate("date_naissance_employe").toLocalDate();
                String login = rs.getString("login_employe");
                String mdpHash = rs.getString("mdp_employe");
                double salaire = rs.getDouble("salaire");
                String poste = rs.getString("poste");

                Employe employe = new Employe(id, nom, date, login, mdpHash, salaire, poste);

                employes.add(employe);
            }

        } catch (SQLException e) {
        	
        }

        return employes;
    }
    
    
    public static double recuperer_dette(int id) {
        double dette = 0.00;
         
        String sql = "SELECT dette FROM utilisateur WHERE id_uti = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                dette = rs.getDouble("dette");
            }

        } catch (SQLException e) {
        }

        return dette;
    }
    
    
    public static void payer_dette(int id) {
        
        String sql = "UPDATE utilisateur SET dette = 0 WHERE id_uti = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, id);
            pstmt.executeUpdate(); 
             
        } catch (SQLException e) {
        }
    }
     
    
    /*
    public static ArrayList<Ressource> recupererResLibre() {
        ArrayList<Ressource> res = new ArrayList<>();
        
        String sql = "SELECT * FROM ressource WHERE libre=1";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	int id_res = rs.getInt("id_res");
                String nom_res = rs.getString("nom_res");
                String marque = rs.getString("marque");
                boolean libre = rs.getBoolean("libre");
                double prix = rs.getDouble("prix");
                int duree_max = rs.getInt("duree_max");
                String etat_res = rs.getString("etat_res");
                String type_res = rs.getString("type_res");
                Ressource ressource = new Ressource(id_res, nom_res, marque, libre, prix, duree_max, etat_res, type_res);

                res.add(ressource);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return res;
    }
    */
    
    
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
                String etat = rs.getString("etat_res");
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
                	ressource.setEtat(etat);
                    ressources.add(ressource);
                }
            }

        } catch (SQLException e) {
        	
        }

        return ressources;
    }
    
    
    public static ArrayList<Ressource> recupererResLibre() {
        ArrayList<Ressource> ressources = new ArrayList<>();

        String sql = "SELECT * FROM Ressource  WHERE libre=1";

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
                String etat = rs.getString("etat_res");
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
                	ressource.setEtat(etat);
                    ressources.add(ressource);
                }
            }

        } catch (SQLException e) {
        	
        }

        return ressources;
    }


    public static ArrayList<Emprunt> recupererTousLesEmprunts(ArrayList<Utilisateur> utilisateurs, ArrayList<Ressource> ressources) {
        ArrayList<Emprunt> emprunts = new ArrayList<>();

        String sql = "SELECT id_emp, date_emp, etat_emp, date_rendu, id_res, id_uti FROM Emprunt";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int idEmp = rs.getInt("id_emp");
                int idRes = rs.getInt("id_res");
                int idUti = rs.getInt("id_uti");
                String etat = rs.getString("etat_emp");
                LocalDate dateEmp = rs.getDate("date_emp").toLocalDate();
                LocalDate dateRendu =  rs.getDate("date_rendu").toLocalDate();
                
                Utilisateur utilisateur = null;
                for (Utilisateur u : utilisateurs) {
                    if (u.getId() == idUti) {
                        utilisateur = u;
                        break;
                    }
                }

                Ressource ressource = null;
                for (Ressource r : ressources) {
                    if (r.getId() == idRes) {
                        ressource = r;
                        break;
                    }
                }

                if (utilisateur != null && ressource != null) {
                    Emprunt e = new Emprunt(idEmp, utilisateur, ressource, dateEmp, dateRendu);
                    e.setEtat(etat);
                    emprunts.add(e);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur récupération emprunts simplifiés : " + e.getMessage());
        }

        return emprunts;
    }
    
    
    public static ArrayList<Emprunt> recupererEmpruntsEnCoursParUtilisateur(int idUti, ArrayList<Utilisateur> utilisateurs, ArrayList<Ressource> ressources) {
        ArrayList<Emprunt> emprunts = new ArrayList<>();

        String sql = "SELECT id_emp, date_emp, etat_emp, date_rendu, id_res FROM Emprunt WHERE id_uti = ? AND etat_emp != 'rendu'";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUti);
            ResultSet rs = pstmt.executeQuery();

            Utilisateur utilisateur = null;
            for (Utilisateur u : utilisateurs) {
                if (u.getId() == idUti) {
                    utilisateur = u;
                    break;
                }
            }

            while (rs.next()) {
                int idEmp = rs.getInt("id_emp");
                int idRes = rs.getInt("id_res");
                String etat = rs.getString("etat_emp");
                LocalDate dateEmp = rs.getDate("date_emp").toLocalDate();
                LocalDate dateRendu = rs.getDate("date_rendu").toLocalDate();

                Ressource ressource = null;
                for (Ressource r : ressources) {
                    if (r.getId() == idRes) {
                        ressource = r;
                        break;
                    }
                }

                if (utilisateur != null && ressource != null) {
                    Emprunt emprunt = new Emprunt(idEmp, utilisateur, ressource, dateEmp, dateRendu);
                    emprunt.setEtat(etat);
                    emprunts.add(emprunt);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur récupération emprunts utilisateur : " + e.getMessage());
        }

        return emprunts;
    }


    public static ArrayList<Emprunt> recupererEmpruntsParUtilisateur(int idUti, ArrayList<Utilisateur> utilisateurs, ArrayList<Ressource> ressources) {
        ArrayList<Emprunt> emprunts = new ArrayList<>();

        String sql = "SELECT id_emp, date_emp, etat_emp, date_rendu, id_res FROM Emprunt WHERE id_uti = ?";

        try (Connection conn = Connexion_BDD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idUti);
            ResultSet rs = pstmt.executeQuery();

            Utilisateur utilisateur = null;
            for (Utilisateur u : utilisateurs) {
                if (u.getId() == idUti) {
                    utilisateur = u;
                    break;
                }
            }

            while (rs.next()) {
                int idEmp = rs.getInt("id_emp");
                int idRes = rs.getInt("id_res");
                String etat = rs.getString("etat_emp");
                LocalDate dateEmp = rs.getDate("date_emp").toLocalDate();
                LocalDate dateRendu = rs.getDate("date_rendu").toLocalDate();

                Ressource ressource = null;
                for (Ressource r : ressources) {
                    if (r.getId() == idRes) {
                        ressource = r;
                        break;
                    }
                }

                if (utilisateur != null && ressource != null) {
                    Emprunt emprunt = new Emprunt(idEmp, utilisateur, ressource, dateEmp, dateRendu);
                    emprunt.setEtat(etat);
                    emprunts.add(emprunt);
                }
            }

        } catch (SQLException e) {
            System.out.println("Erreur récupération emprunts utilisateur : " + e.getMessage());
        }

        return emprunts;
    }

}