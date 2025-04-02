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
        
          
        
        JPanel panneauFond = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                if (image != null) {
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
                }
            }
        };
        
        
        fenetre.setContentPane(panneauFond);
        JButton deco_boutton = new JButton("Se déconnecter");

        panneauFond.setLayout(new BorderLayout()); 
        panneauFond.add(deco_boutton, BorderLayout.SOUTH);

        

        JMenuBar menuBar = new JMenuBar();
        

        JMenu menuUtilisateur = new JMenu("Gestion Utilisateur");
        JMenuItem ajouterUtilisateur = new JMenuItem("Ajouter un utilisateur");
        JMenuItem supprimerUtilisateur = new JMenuItem("Supprimer un utilisateur");
        JMenuItem afficherUtilisateurs = new JMenuItem("Afficher les utilisateurs");
        menuUtilisateur.add(ajouterUtilisateur);
        menuUtilisateur.add(supprimerUtilisateur);
        menuUtilisateur.add(afficherUtilisateurs);
        

        JMenu menuEmploye = new JMenu("Gestion Employé");
        JMenuItem ajouterEmploye = new JMenuItem("Ajouter un employé");
        JMenuItem supprimerEmploye = new JMenuItem("Supprimer un employé");
        JMenuItem afficherEmployes = new JMenuItem("Afficher les employés");
        menuEmploye.add(ajouterEmploye);
        menuEmploye.add(supprimerEmploye);
        menuEmploye.add(afficherEmployes);
        

        JMenu menuRessource = new JMenu("Gestion Ressource");
        JMenuItem ajouterRessource = new JMenuItem("Ajouter une ressource");
        JMenuItem supprimerRessource = new JMenuItem("Supprimer une ressource");
        JMenuItem changerEtatRessource = new JMenuItem("Changer état d'une ressource");
        JMenuItem afficherRessources = new JMenuItem("Afficher toutes les ressources");
        menuRessource.add(ajouterRessource);
        menuRessource.add(supprimerRessource);
        menuRessource.add(changerEtatRessource);
        menuRessource.add(afficherRessources);
        

        menuBar.add(menuUtilisateur);
        menuBar.add(menuEmploye);
        menuBar.add(menuRessource);
        

        fenetre.setJMenuBar(menuBar);
        
        

        

        deco_boutton.addActionListener(e -> deconnexion());

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
        
        
        JTextField nomField = new JTextField(20);
        JTextField marqueField = new JTextField(20);
        JTextField prixField = new JTextField(20);
        JTextField dureeMaxField = new JTextField(20);
        

        String[] types = {"Ordinateur", "Telephone", "Tablette_graphique"};
        JComboBox<String> typeComboBox = new JComboBox<>(types);
        

        panel.add(new JLabel("Nom de la ressource :"));
        panel.add(nomField);
        panel.add(new JLabel("Marque :"));
        panel.add(marqueField);
        panel.add(new JLabel("Type :"));
        panel.add(typeComboBox);  
        panel.add(new JLabel("Prix :"));
        panel.add(prixField);
        panel.add(new JLabel("Durée max :"));
        panel.add(dureeMaxField);
        
      
        JButton validerButton = new JButton("Valider");
        

        validerButton.addActionListener(e -> {
            String nom = nomField.getText();
            String marque = marqueField.getText();
            String type = (String) typeComboBox.getSelectedItem();  // Récupérer le type sélectionné dans le JComboBox
            String prixStr = prixField.getText();
            String dureeMaxStr = dureeMaxField.getText();
            
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
        
        panel.add(validerButton);
        

        int option = JOptionPane.showConfirmDialog(fenetre, panel, "Ajouter une ressource", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;  
        }
    }



    
    public void supprimerRessource() {
        String emp = controleur.afficherRessources(); 

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Ressources disponibles :"));


        JTextArea resTextArea = new JTextArea(10, 30);
        resTextArea.setText(emp);
        resTextArea.setWrapStyleWord(true);
        resTextArea.setLineWrap(true);
        resTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resTextArea);
        panel.add(scrollPane);

        panel.add(new JLabel("ID de la ressource à supprimer :"));
        JTextField idField = new JTextField(20);
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Supprimer une ressource", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String idText = idField.getText().trim();

            if (!idText.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);
                    boolean success = controleur.supprimerRessource(id);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Ressource supprimée !");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ressource non trouvée !");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un ID valide (nombre entier) !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un ID !");
            }
        }
    }


    public void changerEtatRessource() {
        String[] etats = {"Neuf", "Abime", "Hors service", "En maintenance"};
        JComboBox<String> etat_box = new JComboBox<>(etats);
        

        String ressourcesAffichees = controleur.afficherRessources();


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Ressources disponibles :"));
        

        JTextArea ressourcesTextArea = new JTextArea(10, 30);
        ressourcesTextArea.setText(ressourcesAffichees);
        ressourcesTextArea.setWrapStyleWord(true); 
        ressourcesTextArea.setLineWrap(true); 
        ressourcesTextArea.setEditable(false); 
        JScrollPane scrollPane = new JScrollPane(ressourcesTextArea); 
        panel.add(scrollPane);
        

        JTextField idField = new JTextField(10);
        panel.add(Box.createVerticalStrut(10));  
        panel.add(new JLabel("Entrez l'ID de la ressource à modifier :"));
        panel.add(idField);

        panel.add(new JLabel("Sélectionnez le nouvel état :"));
        panel.add(etat_box);

        int selection = JOptionPane.showConfirmDialog(fenetre, panel, "Modifier l'état de la ressource", JOptionPane.OK_CANCEL_OPTION);


        if (selection == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idField.getText());
                String nouvelEtat = (String) etat_box.getSelectedItem();

                boolean success = controleur.changerEtatRessource(id, nouvelEtat);

                if (success) {
                    JOptionPane.showMessageDialog(fenetre, "État modifié avec succès !");
                } else {
                    JOptionPane.showMessageDialog(fenetre, "Ressource non trouvée !");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(fenetre, "ID invalide !");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(fenetre, "Erreur lors de la modification de l'état !");
            }
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

    	String emp = controleur.getListeUtilisateurs(); 


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Utilisateurs disponibles :"));


        JTextArea utiTextArea = new JTextArea(10, 30);
        utiTextArea.setText(emp);
        utiTextArea.setWrapStyleWord(true);
        utiTextArea.setLineWrap(true);
        utiTextArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(utiTextArea);
        panel.add(scrollPane);

        panel.add(new JLabel("Login de l'utilisateur à supprimer :"));
        JTextField loginField = new JTextField(20);
        panel.add(loginField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Supprimer un utilisateur", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


        if (result == JOptionPane.OK_OPTION) {
            String login = loginField.getText().trim();

            if (!login.isEmpty()) {
                boolean success = controleur.supprimerUtilisateur(login);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Utilisateur supprimé !");
                } else {
                    JOptionPane.showMessageDialog(null, "Utilisateur non trouvé !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un login !");
            }
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

    	JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  // Layout vertical
        
        JTextField nomField = new JTextField(20);
        JTextField dateNaissanceField = new JTextField(20);
        JTextField loginField = new JTextField(20);
        JPasswordField mdpField = new JPasswordField(20);
        JTextField salaireField = new JTextField(20);
        JTextField posteField = new JTextField(20);
        

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
        

        JButton validerButton = new JButton("Valider");
        

        validerButton.addActionListener(e -> {
            String nom = nomField.getText();
            String dateNaissanceStr = dateNaissanceField.getText();
            String login = loginField.getText();
            char[] mdpArray = mdpField.getPassword();
            String mdp = new String(mdpArray);
            String salaireStr = salaireField.getText();
            String poste = posteField.getText();
            

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
        

        panel.add(validerButton);
        

        int option = JOptionPane.showConfirmDialog(fenetre, panel, "Ajouter un employé", JOptionPane.PLAIN_MESSAGE);
        if (option != JOptionPane.OK_OPTION) {
            return;  
        }
    }


    public void supprimerEmploye() {

    	String emp = controleur.getListeEmployes(); 

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        panel.add(new JLabel("Employés disponibles :"));


        JTextArea employesTextArea = new JTextArea(10, 30);
        employesTextArea.setText(emp);
        employesTextArea.setWrapStyleWord(true);
        employesTextArea.setLineWrap(true);
        employesTextArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(employesTextArea);
        panel.add(scrollPane);


        panel.add(new JLabel("Login de l'employé à supprimer :"));
        JTextField loginField = new JTextField(20);
        panel.add(loginField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Supprimer un employé", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);


        if (result == JOptionPane.OK_OPTION) {
            String login = loginField.getText().trim();

            if (!login.isEmpty()) {
                boolean success = controleur.supprimerEmploye(login);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Employé supprimé !");
                } else {
                    JOptionPane.showMessageDialog(null, "Employé non trouvé !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un login !");
            }
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
