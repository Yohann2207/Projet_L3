package Vue;

import Controleur.Controleur;
import Personne.Employe;

import javax.swing.*;
import java.awt.*;

public class MenuEmploye_IHM {

    private JFrame fenetre;
    private Controleur controleur;
    private Employe employe;

    public MenuEmploye_IHM(Controleur controleur, Employe employe) {
        this.controleur = controleur;
        this.employe = employe;

        fenetre = new JFrame("Menu Employé - " + employe.getNom());
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
    }

    public void ajouterRessource() {
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

    public void ajouterUtilisateur() {
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

    public void supprimerUtilisateur() {
        String login = JOptionPane.showInputDialog(fenetre, "Login de l'utilisateur à supprimer :");

        boolean success = controleur.supprimerUtilisateur(login);

        if (success) {
            JOptionPane.showMessageDialog(fenetre, "Utilisateur supprimé !");
        } else {
            JOptionPane.showMessageDialog(fenetre, "Utilisateur non trouvé !");
        }
    }

    public void ajouterEmploye() {
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
