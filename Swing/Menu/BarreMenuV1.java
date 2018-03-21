/**
 * BarreMenu.java
 * 
 * Created on 8/12/2017
 * @author Bayart Valentin 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarreMenuV1 extends JFrame implements ActionListener{
    /**
     * Création de la barre de menu
     */
    private JMenuBar barre = new JMenuBar();
    /**
     * Création de l'onglet fichier de la barre de menu
     */
    private JMenu fichier = new JMenu("Fichier");
    /**
     * Création de l'onglet édition de la barre de menu
     */
    private JMenu edition = new JMenu("Édition");
    /**
     * Création de l'onglet aide de la barre de menu
     */
    private JMenu aide = new JMenu("Aide");
    /**
     * Création de l'onglet quitter de la barre de menu
     */
    private JMenuItem quitter = new JMenuItem("Quitter");

    /**
     * Création d'un item ouvrir
     */
    private JMenuItem ouvrir = new JMenuItem("Ouvrir...");
    /**
     * Création de la boite de dialogue
     */
    private JDialog dialogueQuit = new JDialog(this, "Fermer l'application", true);
    /**
     * Création des boutons nécessaires pour la boite de dialogue pour quitter
     */
    private JButton annulerQuit = new JButton("annuler");
    private JButton ok = new JButton("Ok");
    /** 
     * Constructeur du menu
     * Permet de créer une barre de menu
     */
    public BarreMenu(){
        /**
         * Création d'un panel
         */
        JPanel global = new JPanel(new BorderLayout());
        /**
         * Paramétrage de la taille de la fenêtre, de son opération de fermeture par défaut 
         * et autorisation de la redimension
         */
	    setBounds(275,0,800,600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        /**
         * Ajout des onglets au menu
         */
        barre.add(fichier);
        barre.add(edition);
        barre.add(aide);
        /**
         * Ajout de la barre au Panel global
         */
	    global.add(barre, BorderLayout.NORTH);
        /**
         * Ajout de l'action listener a quitter
         */
        quitter.addActionListener(this);
	    /**
	     * Ajout du sous menu
	     */
        fichier.add(ouvrir);
        fichier.add(quitter);	
        setContentPane(global);

        /**
         * Sépare la boite de dialogue en GridLayout
         */
        dialogueQuit.setLayout(new GridLayout(0,1));
        /** 
         * Création des panels 
         */
	    JPanel panelHaut = new JPanel(new FlowLayout());
        JPanel panelBas = new JPanel(new FlowLayout());
        /**
         * Ajout du texte au panel haut de la boite
         */
        panelHaut.add(new JLabel("Voulez-vous quitter ?"));
        /**
         * Ajout des boutons a la boite
         */
	    panelBas.add(ok);
        panelBas.add(annulerQuit);
        
	    dialogueQuit.add(panelHaut);
        dialogueQuit.add(panelBas);

        /**
         * Action Listener pour les boutons de la boite
         */
        ok.addActionListener(this);
	    annulerQuit.addActionListener(this);
        
	    /**
         * Rend la fenêtre visible
         */
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == quitter){
                      //Affichage de la boite de dialogue
            dialogueQuit.setSize(250,75); 
            dialogueQuit.setLocationRelativeTo(null);
            dialogueQuit.setVisible(true);
        }
        
        // Lorsqu'on clique sur le bouton ok de la boite de dialogue Quitter
        if (e.getSource() == ok){
            //Fermeture du programme
            System.exit(0);
        }
        
        // Lorsqu'on clique sur le bouton annuler de la boite de dialogue Quitter
        if (e.getSource() == annulerQuit){
            //On ferme la boite de dialogue
            dialogueQuit.dispose();
        }
        // if(e.getSource() == aide){

        // }
    }
}
