package Vue;

import javax.swing.*;

import Controleur.Controleur;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification_IHM {

    private Controleur controleur;

    public Authentification_IHM(Controleur controleur) {
        this.controleur = controleur;

        // Création de la fenêtre principale
        JFrame fenetre = new JFrame();
        fenetre.setTitle("Association de prêts informatiques");
        fenetre.setSize(400, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Panel pour le titre
        JPanel panelTitre = new JPanel();
        JLabel titre = new JLabel("Authentification");
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitre.add(titre);

        // Champs pour authentification
        JLabel labelUser = new JLabel("Nom d'utilisateur :");
        JTextField inputUser = new JTextField(15);
        JLabel labelPwd = new JLabel("Mot de passe :");
        JPasswordField inputPwd = new JPasswordField(15);
        JButton btnValider = new JButton("Se connecter");

        // Action pour se connecter
        btnValider.addActionListener(e -> {
            String login = inputUser.getText();
            String mdp = new String(inputPwd.getPassword());

            boolean success = controleur.authentifier(login, mdp);

            if (success) {
                fenetre.dispose();
                controleur.lancerMenu(); 
            } else {
                JOptionPane.showMessageDialog(fenetre, "Login ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Placement des composants par défaut
        gbc.gridx = 0; gbc.gridy = 0;
        panelPrincipal.add(labelUser, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(inputUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelPrincipal.add(labelPwd, gbc);
        gbc.gridx = 1;
        panelPrincipal.add(inputPwd, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelPrincipal.add(btnValider, gbc);

        // Ajout des panels à la fenêtre
        fenetre.add(panelTitre, BorderLayout.NORTH);
        fenetre.add(panelPrincipal, BorderLayout.CENTER);

        fenetre.setVisible(true);
    }
}
