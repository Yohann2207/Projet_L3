package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    private JFrame fenetre;
    private JTextField inputUser;
    private JPasswordField inputPwd;
    private JButton btnValider;

    public Test() {
        // Création de la fenêtre principale
        fenetre = new JFrame("Association de prêts informatiques");
        fenetre.setSize(400, 300);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Titre
        JLabel titre = new JLabel("Authentification");
        titre.setFont(new Font("Arial", Font.BOLD, 18));

        // Champs utilisateur
        JLabel labelUser = new JLabel("Nom d'utilisateur :");
        inputUser = new JTextField(15);

        JLabel labelPwd = new JLabel("Mot de passe :");
        inputPwd = new JPasswordField(15);

        btnValider = new JButton("Se connecter");

        // Ajout des composants
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

        // Ajout du titre et du formulaire
        fenetre.add(titre, BorderLayout.NORTH);
        fenetre.add(panelPrincipal, BorderLayout.CENTER);

        // Action sur le bouton valider
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = inputUser.getText();
                String mdp = new String(inputPwd.getPassword());

                // ➡️ Ici tu peux appeler ton Controleur ou la méthode d'authentification
                boolean success = authentifier(login, mdp);

                if (success) {
                    JOptionPane.showMessageDialog(fenetre, "Connexion réussie ! Bienvenue " + login + " !");
                    fenetre.dispose();  // Ferme la fenêtre après connexion
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Login ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fenetre.setVisible(true);
    }

    // ➡️ Exemple de méthode d'authentification
    // Remplace ça par l'appel réel au Controleur + Model
    private boolean authentifier(String login, String mdp) {
        // 👉 Logique temporaire pour tester :
        return login.equals("admin") && mdp.equals("admin");
        // ➡️ Remplacer par :
        // return Controleur.authentifier(login, mdp);
    }

    // ➡️ Main de test indépendant
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Authentification_IHM());
    }
}
