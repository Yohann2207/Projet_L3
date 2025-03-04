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
        fenetre.setSize(400, 250);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Panel pour le titre
        JPanel panelTitre = new JPanel();
        JLabel titre = new JLabel("Authentification");
        titre.setFont(new Font("Arial", Font.BOLD, 18));
        panelTitre.add(titre);

        // Panel pour les champs d'entrée
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marges

        // Ajout du champ texte (Nom d'utilisateur)
        JLabel labelUser = new JLabel("Nom d'utilisateur :");
        JTextField inputUser = new JTextField();
        inputUser.setPreferredSize(new Dimension(150, 25)); // Réduction de la taille

        // Ajout du champ mot de passe
        JLabel labelPwd = new JLabel("Mot de passe :");
        JPasswordField inputPwd = new JPasswordField();
        inputPwd.setPreferredSize(new Dimension(150, 25)); // Réduction de la taille

        // Ajout du bouton de validation
        JButton btnValider = new JButton("Se connecter");

        // Ajout du lien "Mot de passe oublié ?"
        JLabel linkLabel = new JLabel("<html><u> Pas encore inscrit ? </u></html>"); // Texte souligné
        linkLabel.setForeground(Color.BLUE); // Couleur bleue pour simuler un lien

        // Ajouter un effet au survol et un événement au clic
        linkLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                linkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change le curseur en main
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(fenetre, "YES ! ");
                // Ici, on pourrait ouvrir un navigateur : Desktop.getDesktop().browse(new URI("URL"));
            }
        });

        // Placement des composants avec GridBagConstraints
        gbc.gridx = 0; gbc.gridy = 0;
        panelForm.add(labelUser, gbc);
        gbc.gridx = 1;
        panelForm.add(inputUser, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(labelPwd, gbc);
        gbc.gridx = 1;
        panelForm.add(inputPwd, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelForm.add(btnValider, gbc);

        gbc.gridy = 3; // Ajout du lien en dessous du bouton
        panelForm.add(linkLabel, gbc);

        // Ajout des panels à la fenêtre
        fenetre.add(panelTitre, BorderLayout.NORTH);
        fenetre.add(panelForm, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        fenetre.setVisible(true);
        
        //cd /Users/manonmars/git/Projet_L3 
        //javac -d bin src/Vue/TEST_IHM_VRAC.java
        //java -cp bin Vue.TEST_IHM_VRAC
    }
    
}


