package BDD;

import java.sql.Connection;


public class Test_Connexion_BDD {

    public static void main(String[] args) {
        Connection conn = Connexion_BDD.getConnection(); // Correction ici
        if (conn != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec de la connexion.");
        }
    }
}
