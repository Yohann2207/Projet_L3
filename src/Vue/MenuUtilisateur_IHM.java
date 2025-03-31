package Vue;

import Controleur.Controleur;
import Personne.Utilisateur;
import Ressource.Ressource;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuUtilisateur_IHM {

    private JFrame fenetre;
    private Controleur controleur;
    private Utilisateur utilisateur;

    public MenuUtilisateur_IHM(Controleur controleur, Utilisateur utilisateur) {
        this.controleur = controleur;
        this.utilisateur = utilisateur;

        fenetre = new JFrame("Menu Utilisateur - " + utilisateur.getNom());
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        JLabel titre = new JLabel("Bienvenue " + utilisateur.getNom() + " !");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        fenetre.add(titre, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton btnAfficherRessources = new JButton("Afficher les ressources disponibles");
        JButton btnEmprunter = new JButton("Emprunter une ressource");
        JButton btnRendre = new JButton("Rendre une ressource");
        JButton btnAfficherDette = new JButton("Afficher la dette");
        JButton btnPayerDette = new JButton("Payer une dette");
        JButton btnAfficherEmpruntsActifs = new JButton("Afficher les emprunts en cours");
        JButton btnAfficherHistorique = new JButton("Afficher l'historique des emprunts");
        JButton btnDeconnexion = new JButton("Se déconnecter");

        panelBoutons.add(btnAfficherRessources);
        panelBoutons.add(btnEmprunter);
        panelBoutons.add(btnRendre);
        panelBoutons.add(btnAfficherDette);
        panelBoutons.add(btnPayerDette);
        panelBoutons.add(btnAfficherEmpruntsActifs);
        panelBoutons.add(btnAfficherHistorique);
        panelBoutons.add(btnDeconnexion);

        fenetre.add(panelBoutons, BorderLayout.CENTER);
        
        btnEmprunter.addActionListener(e -> RadioButtonExample());
        btnDeconnexion.addActionListener(e -> deconnexion());

        fenetre.setVisible(true);
        
    }
    
    public void RadioButtonExample() {
        // Création d'une nouvelle fenêtre dédiée
        JFrame ressourceFrame = new JFrame("Sélectionner une ressource");
        ressourceFrame.setSize(500, 400);
        ressourceFrame.setLocationRelativeTo(null);
        ressourceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ressourceFrame.setLayout(new BorderLayout());

        // Récupération de la liste des ressources
        ArrayList<Ressource> ressources = controleur.getRessources();
        JPanel panelRadio = new JPanel();
        panelRadio.setLayout(new BoxLayout(panelRadio, BoxLayout.Y_AXIS));

        ButtonGroup group = new ButtonGroup();
        ArrayList<JRadioButton> boutons = new ArrayList<>();

        for (Ressource r : ressources) {
            JRadioButton bouton = new JRadioButton(r.toString());
            bouton.setActionCommand(String.valueOf(r.getId()));
            group.add(bouton);
            panelRadio.add(bouton);
            boutons.add(bouton);
        }

        // Ajout au frame
        ressourceFrame.add(new JScrollPane(panelRadio), BorderLayout.CENTER);

        ressourceFrame.setVisible(true);
    }

        
    public void deconnexion() {
        fenetre.dispose();
        controleur.deconnecter();
        new Authentification_IHM(controleur);
    }
}    
