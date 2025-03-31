package Vue;


import Controleur.Controleur;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Personne.Employe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MenuEmploye_IHM {

    private JFrame fenetre;
    private Controleur controleur;
    private Employe employe;
    private BufferedImage image;


    public MenuEmploye_IHM(Controleur controleur, Employe employe) {
        this.controleur = controleur;
        this.employe = employe; 

        /*fenetre = new JFrame("Menu Employé - " + employe.getNom());
        fenetre.setSize(400, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fenetre.setLayout(new BorderLayout());

        JLabel titre = new JLabel("Bienvenue " + employe.getNom() + " !");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        fenetre.add(titre, BorderLayout.NORTH);

        JPanel panelBoutons = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton btnAjouterRessource = new JButton("Ajouter une ressource");
        JButton btnSupprimerRessource = new JButton("Supprimer une ressource");
        JButton btnChangerEtatRessource = new JButton("Changer état d'une ressource");
        JButton btnAfficherRessources = new JButton("Afficher toutes les ressources");
        JButton btnAjouterUtilisateur = new JButton("Ajouter un utilisateur");
        JButton btnSupprimerUtilisateur = new JButton("Supprimer un utilisateur");
        JButton btnAjouterEmploye = new JButton("Ajouter un employé");
        JButton btnSupprimerEmploye = new JButton("Supprimer un employé");
        JButton btnAfficherUtilisateurs = new JButton("Afficher les utilisateurs");
        JButton btnAfficherEmployes = new JButton("Afficher les employés");
        JButton btnDeconnexion = new JButton("Se déconnecter");

        panelBoutons.add(btnAjouterRessource);
        panelBoutons.add(btnSupprimerRessource);
        panelBoutons.add(btnChangerEtatRessource);
        panelBoutons.add(btnAfficherRessources);
        panelBoutons.add(btnAjouterUtilisateur);
        panelBoutons.add(btnSupprimerUtilisateur);
        panelBoutons.add(btnAjouterEmploye);
        panelBoutons.add(btnSupprimerEmploye);
        panelBoutons.add(btnAfficherUtilisateurs);
        panelBoutons.add(btnAfficherEmployes);       
        panelBoutons.add(btnDeconnexion);

        fenetre.add(panelBoutons, BorderLayout.CENTER);

        btnAjouterRessource.addActionListener(e -> ajouterRessource());
        btnSupprimerRessource.addActionListener(e -> supprimerRessource());
        btnChangerEtatRessource.addActionListener(e -> changerEtatRessource());
        btnAfficherRessources.addActionListener(e -> afficherRessources());
        btnAjouterUtilisateur.addActionListener(e -> ajouterUtilisateur());
        btnSupprimerUtilisateur.addActionListener(e -> supprimerUtilisateur());
        btnAjouterEmploye.addActionListener(e -> ajouterEmploye());
        btnSupprimerEmploye.addActionListener(e -> supprimerEmploye());
        btnAfficherUtilisateurs.addActionListener(e -> afficherUtilisateurs());
        btnAfficherEmployes.addActionListener(e -> afficherEmployes());
        btnDeconnexion.addActionListener(e -> deconnexion());

        fenetre.setVisible(true);
    } */

          
    

        

        fenetre = new JFrame("Menu Employé - " + employe.getNom());
        fenetre.setSize(500, 500);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        try {
            image = ImageIO.read(new File("/Users/manonmars/Desktop/LOGO_PJ.jpg")); //chemin absolu 
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        // Création du JPanel avec la méthode paintComponent pour dessiner l'image de fond
        JPanel panneauFond = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
                }
            }
        };
        
        fenetre.setContentPane(panneauFond);
        
        // Création de la barre de menu
        JMenuBar menuBar = new JMenuBar();
        
        // Menu Gestion Utilisateur
        JMenu menuUtilisateur = new JMenu("Gestion Utilisateur");
        JMenuItem ajouterUtilisateur = new JMenuItem("Ajouter un utilisateur");
        JMenuItem supprimerUtilisateur = new JMenuItem("Supprimer un utilisateur");
        JMenuItem afficherUtilisateurs = new JMenuItem("Afficher les utilisateurs");
        menuUtilisateur.add(ajouterUtilisateur);
        menuUtilisateur.add(supprimerUtilisateur);
        menuUtilisateur.add(afficherUtilisateurs);
        
        // Menu Gestion Employé
        JMenu menuEmploye = new JMenu("Gestion Employé");
        JMenuItem ajouterEmploye = new JMenuItem("Ajouter un employé");
        JMenuItem supprimerEmploye = new JMenuItem("Supprimer un employé");
        JMenuItem afficherEmployes = new JMenuItem("Afficher les employés");
        menuEmploye.add(ajouterEmploye);
        menuEmploye.add(supprimerEmploye);
        menuEmploye.add(afficherEmployes);
        
        // Menu Gestion Ressource
        JMenu menuRessource = new JMenu("Gestion Ressource");
        JMenuItem ajouterRessource = new JMenuItem("Ajouter une ressource");
        JMenuItem supprimerRessource = new JMenuItem("Supprimer une ressource");
        JMenuItem changerEtatRessource = new JMenuItem("Changer état d'une ressource");
        JMenuItem afficherRessources = new JMenuItem("Afficher toutes les ressources");
        menuRessource.add(ajouterRessource);
        menuRessource.add(supprimerRessource);
        menuRessource.add(changerEtatRessource);
        menuRessource.add(afficherRessources);
        
        // Ajouter les menus à la barre de menu
        menuBar.add(menuUtilisateur);
        menuBar.add(menuEmploye);
        menuBar.add(menuRessource);
        
        // Définir la barre de menus
        fenetre.setJMenuBar(menuBar);
        
        // Action des menus
        ajouterUtilisateur.addActionListener(e -> ajouterUtilisateur());
        supprimerUtilisateur.addActionListener(e -> supprimerUtilisateur());
        afficherUtilisateurs.addActionListener(e -> afficherUtilisateurs());
        
        ajouterEmploye.addActionListener(e -> ajouterEmploye());
        supprimerEmploye.addActionListener(e -> supprimerEmploye());
        afficherEmployes.addActionListener(e -> afficherEmployes());
        
        ajouterRessource.addActionListener(e -> ajouterRessource());
        supprimerRessource.addActionListener(e -> supprimerRessource());
        changerEtatRessource.addActionListener(e -> changerEtatRessource());
        afficherRessources.addActionListener(e -> afficherRessources());

        
        
        // Afficher la fenêtre
        fenetre.setVisible(true);
    }

    /* public void ajouterRessource() {
        String nom = JOptionPane.showInputDialog(fenetre, "Nom de la ressource :");
        String marque = JOptionPane.showInputDialog(fenetre, "Marque :");
        String type = JOptionPane.showInputDialog(fenetre, "Type (Ordinateur / Telephone / Tablette_graphique) :");
        String prixStr = JOptionPane.showInputDialog(fenetre, "Prix :");
        String dureeMaxStr = JOptionPane.showInputDialog(fenetre, "Durée max :");

        try {
            double prix = Double.parseDouble(prixStr);
            int dureeMax = Integer.parseInt(dureeMaxStr);

            boolean success = controleur.ajouterRessource(nom, marque, prix, dureeMax, type);

            if (success) {
                JOptionPane.showMessageDialog(fenetre, "Ressource ajoutée !");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Erreur lors de l'ajout !");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre, "Valeurs numériques invalides !");
        }
    } */
    

    public void ajouterRessource() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        
        // Champs de saisie texte
        JTextField nomField = new JTextField(20);
        JTextField marqueField = new JTextField(20);
        JTextField prixField = new JTextField(20);
        JTextField dureeMaxField = new JTextField(20);
        
        // Liste déroulante pour le type
        String[] types = {"Ordinateur", "Telephone", "Tablette_graphique"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        
        // Ajouter les champs au panel
        panel.add(new JLabel("Nom de la ressource :"));
        panel.add(nomField);
        panel.add(new JLabel("Marque :"));
        panel.add(marqueField);
        panel.add(new JLabel("Type :"));
        panel.add(typeComboBox);  // Ajout de la liste déroulante
        panel.add(new JLabel("Prix :"));
        panel.add(prixField);
        panel.add(new JLabel("Durée max :"));
        panel.add(dureeMaxField);
        
        // Bouton de validation
        JButton validerButton = new JButton("Valider");
        
        // Action au clic du bouton
        validerButton.addActionListener(e -> {
            String nom = nomField.getText();
            String marque = marqueField.getText();
            String type = (String) typeComboBox.getSelectedItem();  // Récupérer le type sélectionné dans le JComboBox
            String prixStr = prixField.getText();
            String dureeMaxStr = dureeMaxField.getText();
            
            // Vérification que tous les champs sont remplis
            if (nom.isEmpty() || marque.isEmpty() || type == null || prixStr.isEmpty() || dureeMaxStr.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez remplir tous les champs !");
                return;
            }
            
            try {
                double prix = Double.parseDouble(prixStr);
                int dureeMax = Integer.parseInt(dureeMaxStr);

                boolean success = controleur.ajouterRessource(nom, marque, prix, dureeMax, type);

                if (success) {
                    JOptionPane.showMessageDialog(fenetre, "Ressource ajoutée !");
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Erreur lors de l'ajout !");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fenetre, "Valeurs numériques invalides !");
            }
        });
        
        // Ajouter le bouton de validation
        panel.add(validerButton);
        
        // Affichage de la fenêtre avec le panel
        int option = JOptionPane.showConfirmDialog(fenetre, panel, "Ajouter une ressource", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;  
        }
    }


    public void supprimerRessource() {
        String idStr = JOptionPane.showInputDialog(fenetre, "ID de la ressource à supprimer :");

        try {
            int id = Integer.parseInt(idStr);
            boolean success = controleur.supprimerRessource(id);

            if (success) {
                JOptionPane.showMessageDialog(fenetre, "Ressource supprimée !");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Ressource non trouvée !");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre, "ID invalide !");
        }
    }

    public void changerEtatRessource() {
        String idStr = JOptionPane.showInputDialog(fenetre, "ID de la ressource à modifier :");
        String nouvelEtat = JOptionPane.showInputDialog(fenetre, "Nouveau statut (Libre / En maintenance...) :");

        try {
            int id = Integer.parseInt(idStr);
            boolean success = controleur.changerEtatRessource(id, nouvelEtat);

            if (success) {
                JOptionPane.showMessageDialog(fenetre, "État modifié !");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Ressource non trouvée !");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre, "ID invalide !");
        }
    }

    public void afficherRessources() {
        String ressourcesAffichees = controleur.afficherRessources();
        JOptionPane.showMessageDialog(fenetre, ressourcesAffichees);
    }

    /* public void ajouterUtilisateur() {
        String nom = JOptionPane.showInputDialog(fenetre, "Nom de l'utilisateur :");
        String dateNaissanceStr = JOptionPane.showInputDialog(fenetre, "Date de naissance (YYYY-MM-DD) :");
        String login = JOptionPane.showInputDialog(fenetre, "Login :");


        char[] mdpArray;
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(fenetre, passwordField, "Mot de passe :", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) return;
        mdpArray = passwordField.getPassword();
       
        String mdp = new String(mdpArray);
        
        
        
        
        boolean success = controleur.ajouterUtilisateur(nom, dateNaissanceStr, login, mdp);

        if (success) {
            JOptionPane.showMessageDialog(fenetre, "Utilisateur ajouté !");
        } else {
            JOptionPane.showMessageDialog(fenetre, "Erreur : Login déjà existant !");
        }
        
    }
    */
    
    public void ajouterUtilisateur() {

    	JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        
        JTextField nomField = new JTextField(20);
        JTextField dateNaissanceField = new JTextField(20);
        JTextField loginField = new JTextField(20);
        JPasswordField mdpField = new JPasswordField(20);
        
        panel.add(new JLabel("Nom de l'utilisateur :"));
        panel.add(nomField);
        panel.add(new JLabel("Date de naissance (YYYY-MM-DD) :"));
        panel.add(dateNaissanceField);
        panel.add(new JLabel("Login :"));
        panel.add(loginField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(mdpField);
        
        JButton validerButton = new JButton("Valider");
        
        validerButton.addActionListener(e -> {
            String nom = nomField.getText();
            String dateNaissanceStr = dateNaissanceField.getText();
            String login = loginField.getText();
            char[] mdpArray = mdpField.getPassword();
            String mdp = new String(mdpArray);
            
            if (nom.isEmpty() || dateNaissanceStr.isEmpty() || login.isEmpty() || mdp.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez remplir tous les champs !");
                return;
            }
            
            boolean success = controleur.ajouterUtilisateur(nom, dateNaissanceStr, login, mdp);

            if (success) {
                JOptionPane.showMessageDialog(fenetre, "Utilisateur ajouté !");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Erreur : Login déjà existant !");
            }
        });
        
        panel.add(validerButton);
        
        int option = JOptionPane.showConfirmDialog(fenetre, panel, "Ajouter un utilisateur", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;          }
    }

    

    public void supprimerUtilisateur() {
        String login = JOptionPane.showInputDialog(fenetre, "Login de l'utilisateur à supprimer :");

        boolean success = controleur.supprimerUtilisateur(login);

        if (success) {
            JOptionPane.showMessageDialog(fenetre, "Utilisateur supprimé !");
        } else {
            JOptionPane.showMessageDialog(fenetre, "Utilisateur non trouvé !");
        }
    }

    /* public void ajouterEmploye() {
        String nom = JOptionPane.showInputDialog(fenetre, "Nom de l'employé :");
        String dateNaissanceStr = JOptionPane.showInputDialog(fenetre, "Date de naissance (YYYY-MM-DD) :");
        String login = JOptionPane.showInputDialog(fenetre, "Login :");
        
        
        char[] mdpArray;
        JPasswordField passwordField = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(fenetre, passwordField, "Mot de passe :", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) return;
        mdpArray = passwordField.getPassword();
       
        String mdp = new String(mdpArray);
        
        
        
        
        String salaireStr = JOptionPane.showInputDialog(fenetre, "Salaire :");
        String poste = JOptionPane.showInputDialog(fenetre, "Poste :");

        try {
            double salaire = Double.parseDouble(salaireStr);

            boolean success = controleur.ajouterEmploye(nom, dateNaissanceStr, login, mdp, salaire, poste);

            if (success) {
                JOptionPane.showMessageDialog(fenetre, "Employé ajouté !");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Erreur : Login déjà existant !");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(fenetre, "Salaire invalide !");
        }
    }
    */
    
    public void ajouterEmploye() {
        // Créer un JPanel pour contenir les champs de saisie
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        
        // Créer les champs de saisie
        JTextField nomField = new JTextField(20);
        JTextField dateNaissanceField = new JTextField(20);
        JTextField loginField = new JTextField(20);
        JPasswordField mdpField = new JPasswordField(20);
        JTextField salaireField = new JTextField(20);
        JTextField posteField = new JTextField(20);
        
        // Ajouter les composants au panel
        panel.add(new JLabel("Nom de l'employé :"));
        panel.add(nomField);
        panel.add(new JLabel("Date de naissance (YYYY-MM-DD) :"));
        panel.add(dateNaissanceField);
        panel.add(new JLabel("Login :"));
        panel.add(loginField);
        panel.add(new JLabel("Mot de passe :"));
        panel.add(mdpField);
        panel.add(new JLabel("Salaire :"));
        panel.add(salaireField);
        panel.add(new JLabel("Poste :"));
        panel.add(posteField);
        
        // Créer un bouton de validation
        JButton validerButton = new JButton("Valider");
        
        // Ajouter une action au bouton
        validerButton.addActionListener(e -> {
            String nom = nomField.getText();
            String dateNaissanceStr = dateNaissanceField.getText();
            String login = loginField.getText();
            char[] mdpArray = mdpField.getPassword();
            String mdp = new String(mdpArray);
            String salaireStr = salaireField.getText();
            String poste = posteField.getText();
            
            // Valider les informations saisies
            if (nom.isEmpty() || dateNaissanceStr.isEmpty() || login.isEmpty() || mdp.isEmpty() || salaireStr.isEmpty() || poste.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez remplir tous les champs !");
                return;
            }

            try {
                double salaire = Double.parseDouble(salaireStr);

                boolean success = controleur.ajouterEmploye(nom, dateNaissanceStr, login, mdp, salaire, poste);

                if (success) {
                    JOptionPane.showMessageDialog(fenetre, "Employé ajouté !");
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Erreur : Login déjà existant !");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fenetre, "Salaire invalide !");
            }
        });
        
        // Ajouter le bouton au panel
        panel.add(validerButton);
        
        // Afficher la fenêtre avec le panel
        int option = JOptionPane.showConfirmDialog(fenetre, panel, "Ajouter un employé", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;  // Si l'utilisateur annule, ne rien faire
        }
    }


    public void supprimerEmploye() {
    	
        String login = JOptionPane.showInputDialog(fenetre, "Login de l'employé à supprimer :");

        boolean success = controleur.supprimerEmploye(login);

        if (success) {
            JOptionPane.showMessageDialog(fenetre, "Employé supprimé !");
        } else {
            JOptionPane.showMessageDialog(fenetre, "Employé non trouvé !");
        }
    }
    
    public void afficherUtilisateurs() {
        String utilisateursList = controleur.getListeUtilisateurs();
        JOptionPane.showMessageDialog(fenetre, utilisateursList, "Liste des Utilisateurs", JOptionPane.INFORMATION_MESSAGE);
    }

    public void afficherEmployes() {
        String employesList = controleur.getListeEmployes();
        JOptionPane.showMessageDialog(fenetre, employesList, "Liste des Employés", JOptionPane.INFORMATION_MESSAGE);
    }
    
    

    public void deconnexion() {
        fenetre.dispose();
        controleur.deconnecter();
        new Authentification_IHM(controleur);
    }
}
