package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Personne.Utilisateur;
import Ressource.Ressource;
import Transaction.Emprunt;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test_Utilisateur {

    private Utilisateur utilisateur;
    private Ressource ressource;

    @BeforeEach
    void setUp() {
        utilisateur = new Utilisateur(1, "Dupont", LocalDate.of(1990, 1, 1), "Dupont", "mdp", LocalDate.now());
        ressource = new Ressource(1, "Ordinateur", "Description", 0, 0, 0, 0, true, 10.0, 30);
    }

    @Test
    void testGettersEtSetters() {
        assertEquals(LocalDate.now(), utilisateur.getDate_inscription());
        assertEquals(0, utilisateur.getDette());
        assertFalse(utilisateur.getIs_premium());

        utilisateur.setDette(100);
        assertEquals(100, utilisateur.getDette());

        utilisateur.setIs_premium(true);
        assertTrue(utilisateur.getIs_premium());
    }

    // Les 3 tests suivants ne peuvent pas marcher car on travaille en BDD et pas en local 
    @Test
    void testCreerNouvelEmprunt() {

    	int empruntId = utilisateur.creerNouvelEmprunt(utilisateur, ressource);
        assertTrue(empruntId >= 0);

        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(utilisateur);
        
        ArrayList<Ressource> ressources = new ArrayList<>();
        ressources.add(ressource);
        
        ArrayList<Emprunt> emprunts = utilisateur.getMesEmprunts(utilisateurs, ressources);
        assertNotNull(emprunts);
        assertEquals(1, emprunts.size());
        assertEquals(empruntId, emprunts.get(0).getId());
    }
    
   
    
    @Test
    void testGetMesEmprunts() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        ArrayList<Ressource> ressources = new ArrayList<>();
        utilisateurs.add(utilisateur);
        ressources.add(ressource);

        ArrayList<Emprunt> empruntsInitiaux = utilisateur.getMesEmprunts(utilisateurs, ressources);
        assertEquals(0, empruntsInitiaux.size()); 

        int empruntId = utilisateur.creerNouvelEmprunt(utilisateur, ressource);
        assertTrue(empruntId >= 0);

        ArrayList<Emprunt> empruntsApresCreation = utilisateur.getMesEmprunts(utilisateurs, ressources);
        assertNotNull(empruntsApresCreation);
        assertEquals(1, empruntsApresCreation.size());
    
    }
    @Test
    void testPayer() {
        utilisateur.setDette(100);
        utilisateur.payer(50);
        assertEquals(50, utilisateur.getDette());

        utilisateur.payer(100);
        assertEquals(0, utilisateur.getDette());

        // Test du cas où l'utilisateur essaie de payer plus que sa dette
        utilisateur.setDette(50);
        utilisateur.payer(100);
        assertEquals(0, utilisateur.getDette());
    }

    
    @Test
    void testGetId() {
        assertEquals(1, utilisateur.getId());
    }

    @Test
    void testGetNom() {
        assertEquals("Dupont", utilisateur.getNom());
    }

    @Test
    void testGetDateNaissance() {
        assertEquals(LocalDate.of(1990, 1, 1), utilisateur.getDate_naissance());
    }

    @Test
    void testGetLogin() {
        assertEquals("Dupont", utilisateur.getLogin());
    }

    @Test
    void testGetMotDePasse() {
        assertEquals("mdp", utilisateur.getMdp());
    }

    @Test
    void testPayerAvecDetteNulle() {
        utilisateur.payer(50);
        assertEquals(0, utilisateur.getDette());
    }

 

    @Test
    void testCreerNouvelEmpruntAvecRessourceIndisponible() {
        Ressource ressourceIndisponible = new Ressource(2, "Telephone", "Description", 0, 0, 0, 0, false, 5.0, 15);
        assertEquals(-1, utilisateur.creerNouvelEmprunt(utilisateur, ressourceIndisponible));
    }
}