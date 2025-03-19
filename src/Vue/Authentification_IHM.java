package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification_IHM {

    public static void main(String[] args) {
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

        // Lien "Pas encore inscrit ?"
        JLabel linkLabel = new JLabel("<html><u> Pas encore inscrit ? </u></html>");
        linkLabel.setForeground(Color.BLUE);

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

        gbc.gridy = 3;
        panelPrincipal.add(linkLabel, gbc);

        // Action au clic sur "Pas encore inscrit ?"
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // Nettoyage du panel
                panelPrincipal.removeAll();

                // Changement du titre
                titre.setText("Inscription");

                // Ajout du champ Nom
                JLabel labelNom = new JLabel("AAAAAAAAA :");
                JTextField inputNom = new JTextField(15);

                // Mise à jour du bouton
                btnValider.setText("S'inscrire");

                // Placement des nouveaux composants
                gbc.gridx = 0; gbc.gridy = 0;
                panelPrincipal.add(labelNom, gbc);
                gbc.gridx = 1;
                panelPrincipal.add(inputNom, gbc);

                gbc.gridx = 0; gbc.gridy = 1;
                panelPrincipal.add(labelUser, gbc);
                gbc.gridx = 1;
                panelPrincipal.add(inputUser, gbc);

                gbc.gridx = 0; gbc.gridy = 2;
                panelPrincipal.add(labelPwd, gbc);
                gbc.gridx = 1;
                panelPrincipal.add(inputPwd, gbc);

                gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
                panelPrincipal.add(btnValider, gbc);

                // Rafraîchir l'affichage
                panelPrincipal.revalidate();
                panelPrincipal.repaint();
            }
        });

        // Ajout des panels à la fenêtre
        fenetre.add(panelTitre, BorderLayout.NORTH);
        fenetre.add(panelPrincipal, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        fenetre.setVisible(true);

        //cd /Users/manonmars/git/Projet_L3 
        //javac -d bin src/Vue/TEST_IHM_VRAC.java
        //java -cp bin Vue.TEST_IHM_VRAC
    }
    
}


