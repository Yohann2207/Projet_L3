package Vue;

import Controleur.Controleur;
import Personne.Utilisateur;
import Ressource.Ressource;
import Transaction.Emprunt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;
import java.util.List;



public class MenuUtilisateur_IHM {

    private JFrame fenetre;
    private Controleur controleur;
    private Utilisateur utilisateur;
    private BufferedImage image; 

    public MenuUtilisateur_IHM(Controleur controleur, Utilisateur utilisateur) {
        this.controleur = controleur;
        this.utilisateur = utilisateur;
        

        fenetre = new JFrame("Menu Utilisateur - " + utilisateur.getNom());
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
        

        JMenu menuUtilisateur = new JMenu("Emprunter/Rendre");
        JMenuItem emprunt = new JMenuItem("Emprunter");
        JMenuItem rendu = new JMenuItem("Rendre");
        JMenuItem emprunt_encours = new JMenuItem("Consulter ses emprunts");
  
        menuUtilisateur.add(emprunt);
        menuUtilisateur.add(rendu);
        menuUtilisateur.add(emprunt_encours);

        
        JMenu menuEmploye = new JMenu("Gestion Dette");
        JMenuItem v_dette = new JMenuItem("Voir sa dette");
        JMenuItem p_dette = new JMenuItem("Payer sa dette");
     
        menuEmploye.add(v_dette);
        menuEmploye.add(p_dette);
        

        JMenu menuRessource = new JMenu("Ressources");
        JMenuItem res = new JMenuItem("Voir les ressources disponibles");
        
        menuRessource.add(res);

        
        menuBar.add(menuUtilisateur);
        menuBar.add(menuEmploye);
        menuBar.add(menuRessource);
        

        fenetre.setJMenuBar(menuBar);
        
        

        
        deco_boutton.addActionListener(e -> deconnexion());

        v_dette.addActionListener(e -> voir_dette());
        p_dette.addActionListener(e -> paiement_dette());
        
        
        
        rendu.addActionListener(e -> ihm_rendu());
        
        
        
        res.addActionListener(e -> afficherRessources());
        emprunt.addActionListener(e -> afficherFenetreEmprunt());
        emprunt_encours.addActionListener(e -> voir_emprunt());
        

                
        fenetre.setVisible(true);
    }

    public void afficherRessources() {
        String ressourcesAffichees = controleur.afficherRessourcesLibres();
        JOptionPane.showMessageDialog(fenetre, ressourcesAffichees);
    }
  
    public void ihm_rendu() {
        controleur.synchroniserRessources();
        ArrayList<Emprunt> empruntsUtilisateur = controleur.recup_emp_en_cours();

        if (empruntsUtilisateur.isEmpty()) {
            JOptionPane.showMessageDialog(fenetre, "Vous n'avez aucun emprunt à rendre.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(fenetre, "Rendre une ressource", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(fenetre);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[empruntsUtilisateur.size()];

        for (int i = 0; i < empruntsUtilisateur.size(); i++) {
            Emprunt emprunt = empruntsUtilisateur.get(i);
            String texteBouton = emprunt.getRessource().getNom();
            radioButtons[i] = new JRadioButton(texteBouton);
            group.add(radioButtons[i]);
            panel.add(radioButtons[i]);
        }

        JButton valider = new JButton("Valider");
        valider.addActionListener(e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    Emprunt empruntSelectionne = empruntsUtilisateur.get(i);
                    boolean renduReussi = controleur.rendreEmprunt(empruntSelectionne);
                    if (renduReussi) {
                        JOptionPane.showMessageDialog(dialog, "Ressource rendue avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Erreur lors du rendu.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                    dialog.dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner une ressource à rendre.");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(valider);
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(e -> dialog.dispose());
        buttonPanel.add(annuler);

        JLabel titleLabel = new JLabel("Sélectionnez une ressource à rendre :");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dialog.add(titleLabel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
   
   
    public void voir_emprunt() {
        controleur.synchroniserRessources();
        String empruntsAffichees = controleur.afficherEmpruntsUtilisateur();
        JOptionPane.showMessageDialog(fenetre, empruntsAffichees);
    }
    
    public void voir_dette() {
    	double dette=controleur.recup_dette();
    	String message = "Votre dette est de : " + dette + " €";
        JOptionPane.showMessageDialog(fenetre, message);
    }
    
    
    
    public void paiement_dette() {
        double dette = controleur.recup_dette();
        int confirmation = JOptionPane.showConfirmDialog(fenetre,
                "Votre dette est de " + dette + " €. Voulez-vous la payer ?",
                "Confirmation du paiement", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            controleur.pay_dette();

            double nouvelleDette = controleur.recup_dette();
            
            if (nouvelleDette == 0) {
                JOptionPane.showMessageDialog(fenetre, "Paiement effectué avec succès ! Votre dette est maintenant de 0 €.");
            } else {
                JOptionPane.showMessageDialog(fenetre, "Erreur : La dette n'a pas été entièrement effacée.");
            }
        }
    }
    
    public void afficherFenetreEmprunt() {
        ArrayList<Ressource> ressourcesDisponibles = controleur.afficherRessourcesAL(); 
       
        if (ressourcesDisponibles == null || ressourcesDisponibles.isEmpty()) {
            JOptionPane.showMessageDialog(fenetre, "Aucune ressource disponible à emprunter.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(fenetre, "Emprunter une ressource", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(fenetre);
        dialog.setLayout(new BorderLayout());


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] radioButtons = new JRadioButton[ressourcesDisponibles.size()];

        for (int i = 0; i < ressourcesDisponibles.size(); i++) {
            Ressource ressource = ressourcesDisponibles.get(i);
            String texteBouton = ressource.getId() + " - " + ressource.getNom() + " " + ressource.getMarque(); 
            radioButtons[i] = new JRadioButton(texteBouton);
            group.add(radioButtons[i]);
            panel.add(radioButtons[i]);
        }

        JButton valider = new JButton("Valider");
        valider.addActionListener(e -> {
            for (int i = 0; i < radioButtons.length; i++) {
                if (radioButtons[i].isSelected()) {
                    Ressource ressourceSelectionnee = ressourcesDisponibles.get(i); 
                    int idRessource = ressourceSelectionnee.getId();

                    boolean empruntReussi = controleur.ajouterNouvelEmprunt(idRessource);
                    if (empruntReussi) {
                        JOptionPane.showMessageDialog(dialog, "Emprunt réussi !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Échec de l'emprunt.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }

                    dialog.dispose(); 
                    return;
                }
            }
            JOptionPane.showMessageDialog(dialog, "Veuillez sélectionner une ressource.");
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(valider);
        JButton annuler = new JButton("Annuler");
        annuler.addActionListener(e -> dialog.dispose());
        buttonPanel.add(annuler);

        JLabel titleLabel = new JLabel("Sélectionnez une ressource à emprunter :");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        dialog.add(titleLabel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(panel), BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }
    
    public void deconnexion() {
        fenetre.dispose();
        controleur.deconnecter();
        new Authentification_IHM(controleur);
    }
    
   
}
