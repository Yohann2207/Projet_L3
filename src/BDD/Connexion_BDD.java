package BDD;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion_BDD {
    private static final String URL = "jdbc:mysql://localhost:3306/projet"; // Change ici
    private static final String USER = "root";   // Ton utilisateur
    private static final String PASSWORD = "";  // Ton mot de passe

    public static Connection getConnection() {
        try {
            // Chargement du driver (facultatif depuis Java 6+ mais utile)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
