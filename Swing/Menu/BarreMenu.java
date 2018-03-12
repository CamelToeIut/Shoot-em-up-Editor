/**
 * BarreMenu.java
 * 
 * Created on 8/12/2017
 * @author Bayart Valentin 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarreMenu extends JFrame implements ActionListener{

    /**
     * Création des barres de menu
     */
    private JMenuBar barre = new JMenuBar();
    //private JMenuBar choixOnglet = new JMenuBar();

    /**
     * Création de l'onglet fichier de la barre de menu
     */
    private JMenu fichier = new JMenu("Fichier");
    /**
     * Sous-menus de Fichier
     */
    /**
     * Création d'un item quitter du menu fichier
     */
    private JMenuItem quitter = new JMenuItem("Quitter      ");
    /**
     * Création d'un item ouvrir du menu fichier
     */
    private JMenuItem ouvrir = new JMenuItem("Ouvrir...     ");
    /**
     * Création d'un item nouveau du menu fichier
     */
    private JMenuItem nouveau = new JMenuItem("Nouveau      ");
    /**
     * Création d'un item enregistrer du menu fichier
     */
    private JMenuItem enregistrer = new JMenuItem("Enregistrer     ");
    /**
     * Création d'un item enregistrer sous du menu fichier
     */
    private JMenuItem enregistrerSous = new JMenuItem("Enregistrer Sous     ");


    /**
     * Création de l'onglet édition de la barre de menu
     */
    private JMenu edition = new JMenu("Édition");
    /**
     * Sous-menus de Edition
     */


    /**
     * Création de l'onglet aide de la barre de menu
     */
    private JMenu aide = new JMenu("Aide");
    /**
     * Sous-menus de Aide
     */
    /**
     * Création de l'item aide du sous-menu d'aide
     */
    private JMenuItem aideItem = new JMenuItem("Aide    ");
    /**
     * Création de l'item à propos de Aide
     */
    private JMenuItem propos = new JMenuItem("À propos  ");


    /**
     * Création des onglets de choix ennemi / vaisseau
     */
    private JTabbedPane onglets = new JTabbedPane();

    /**
     * Boite de dialogue pour quitter via fichier
     */
    /**
     * Création de la boite de dialogue
     */
    private JDialog dialogueQuit = new JDialog(this, "Fermer l'application", true);
    /**
     * Création des boutons nécessaires pour la boite de dialogue pour quitter
     */
    private JButton annulerQuit = new JButton("annuler");
    private JButton ok = new JButton("Oui");

    private JButton okProp = new JButton("Ok");

    /**
     * Boite de dialogue pour aide et à propos via Aide
     */
    /**
     * Création de la boite de dialogue d'aide
     */
    private JDialog dialogueAide = new JDialog(this, "Aide", true);
    /**
     * Création de la boite de dialogue à propos
     */
    private JDialog dialoguePropos = new JDialog(this, "À propos", true);

    /**
     * Création des panel
     */
    JPanel global = new JPanel(new BorderLayout());
    JPanel contenu = new JPanel(new GridLayout(1,1));
    JPanel code = new JPanel(new GridLayout(2,1));
	JPanel panelHaut = new JPanel(new FlowLayout());
    JPanel panelBas = new JPanel(new FlowLayout());
    JPanel curseur = new JPanel(new GridLayout(1,1));

    /**
     * Création des panel pour les onglets
     */
    JPanel panelOng1 = new JPanel();
    JPanel panelOng2 = new JPanel();
    JButton validerJoueur = new JButton("Valider");
    JButton validerEnnemi = new JButton("Valider");
    JButton ajoutJoueur = new JButton("Choix du joueur");
    JButton ajoutEnnemi = new JButton("Ajouter un ennemi");

    /**
     * Listes déroulantes
     */
    // JComboBox listeJoueur = new JComboBox();

    /**
     * Variables provisoires
     */
    /**
     * Bouton provisoire pour la partie curseur
     */
    JButton cursor = new JButton("Curseur");

    /** 
     * Constructeur du menu
     * Permet de créer une barre de menu
     */
    public BarreMenu(){
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
         * Ajout des action listener
         */
        quitter.addActionListener(this);
        aideItem.addActionListener(this);
        propos.addActionListener(this);

	    /**
	     * Ajout des sous-menu de fichier
	     */
        fichier.add(nouveau);
        fichier.add(ouvrir);
        fichier.add(enregistrer);
        fichier.add(enregistrerSous);
        fichier.add(quitter);	

        /**
         * Ajout des sous-menu de édition
         */

        /**
         * Ajout des sous-menus de aide
         */
        aide.add(aideItem);
        aide.add(propos);
        

        setContentPane(global);

        /**
         * Sépare la boite de dialogue en GridLayout
         */
        dialogueQuit.setLayout(new GridLayout(0,1));
        dialogueAide.setLayout(new GridLayout(0,1));
        dialoguePropos.setLayout(new GridLayout(0,1));

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
         * On ajoute le contenu au centre d'un panel global
         */
        global.add(contenu, BorderLayout.CENTER);

        /**
         * On ajoute le curseur au panel south
         */
        global.add(curseur, BorderLayout.SOUTH);
        curseur.add(cursor);

        /**
         * Ajout du texte dans la boite de dialogue à propos
         */
        dialoguePropos.add(new Label("  Cet éditeur a été créé par un groupe de l'IUT de Calais."));
        dialoguePropos.add(new Label(" "));
        dialoguePropos.add(new Label("  Ce groupe est composé de :"));
        dialoguePropos.add(new Label("    Chombart Félix (Chef de projet)"));
        dialoguePropos.add(new Label("    Bayart Valentin"));
        dialoguePropos.add(new Label("    Grislain Damien"));
        dialoguePropos.add(new Label("    et Comello Baptiste."));
        dialoguePropos.add(new Label(" "));
        dialoguePropos.add(new Label(" Version 0.3.1 - Ajout des Panels de contenu"));

        /**
         * Ajout du texte dans la boîte de dialogue aide
         */
        dialogueAide.add(new Label(" Aide "));
        dialogueAide.add(new Label(" Cet éditeur de Shoot'em Up vous permet de créer vos propres niveaux "));
        dialogueAide.add(new Label(" Pour cela, il vous suffit d'utiliser les outils mis à votre disposition. "));
        dialogueAide.add(new Label(" Par exemple, depuis la partie en haut à droite, vous pouvez ajouter,"));
        dialogueAide.add(new Label(" modifier ou supprimer des ennemis."));

        /**
         * Ajout du fond sur le premier panel
         */   
        contenu.add(new ImagePanel(new ImageIcon("fond.png").getImage()));
        contenu.add(code);

        /**
         * Ajout des onglets au panel haut-droit (panel 2)
         */
        onglets.addTab("Joueur", null, panelOng1, null); 
        onglets.addTab("Ennemi", null, panelOng2, null); 
        code.add(onglets);

        /**
         * Ajout du contenu des onglets du panel 2
         */
        /**
         * Onglet joueur
         */
        panelOng1.setLayout(new BorderLayout());

        panelOng1.add(ajoutJoueur, BorderLayout.NORTH);

        JPanel panelCentreOng1 = new JPanel(new GridLayout(4,1));
        panelOng1.add(panelCentreOng1, BorderLayout.CENTER);

        panelCentreOng1.add(new JLabel("Puissance de Tir : "));
        TextField powerJ = new TextField("", 10);
        panelCentreOng1.add(powerJ);

        panelCentreOng1.add(new JLabel("Points de vie : "));
        TextField lifeJ = new TextField("", 10);
        panelCentreOng1.add(lifeJ);
        
        panelCentreOng1.add(new JLabel("Mode de tir : "));
        JComboBox<String> comboBox  = new JComboBox<String>();
        comboBox.addItem("Tir Simple");
        comboBox.addItem("Tir Multiple");
        panelCentreOng1.add(comboBox);

        panelCentreOng1.add(new JLabel("Cadence de tir : "));
        TextField cadenceJ = new TextField("", 10);
        panelCentreOng1.add(cadenceJ);

        panelOng1.add(validerJoueur, BorderLayout.SOUTH);

        /**
         * Onglet ennemi
         */
        panelOng2.setLayout(new BorderLayout());

        panelOng2.add(ajoutEnnemi, BorderLayout.NORTH);

        JPanel panelCentreOng2 = new JPanel(new GridLayout(4,1));
        panelOng2.add(panelCentreOng2, BorderLayout.CENTER);

        panelCentreOng2.add(new JLabel("Puissance de Tir : "));
        TextField powerE = new TextField("", 10);
        panelCentreOng2.add(powerE);

        panelCentreOng2.add(new JLabel("Points de vie : "));
        TextField lifeE = new TextField("", 10);
        panelCentreOng2.add(lifeE);
        
        panelCentreOng2.add(new JLabel("Mode de tir : "));
        JComboBox<String> comboBoxE  = new JComboBox<String>();
        comboBoxE.addItem("Tir Simple");
        comboBoxE.addItem("Tir Multiple");
        panelCentreOng2.add(comboBoxE);

        panelCentreOng2.add(new JLabel("Cadence de tir : "));
        TextField cadenceE = new TextField("", 10);
        panelCentreOng2.add(cadenceE);

        panelOng2.add(validerEnnemi, BorderLayout.SOUTH);

        /**
         * Panel code XML
         */
        code.add(new JButton("XML"));

	    /** 
         * Rend la fenêtre visible
         */
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == quitter){
            /**
             * Affichage de la boite de dialogue
             */
            dialogueQuit.setSize(250,75); 
            dialogueQuit.setLocationRelativeTo(null);
            dialogueQuit.setVisible(true);
        }
        
        /**
         *  Lorsqu'on clique sur le bouton ok de la boite de dialogue Quitter
         */
        if (e.getSource() == ok){
            /**
             * Fermeture du programme
             */
            System.exit(0);
        }
        
        /**
         *  Lorsqu'on clique sur le bouton annuler de la boite de dialogue Quitter
         */
        if (e.getSource() == annulerQuit){
            /**
             * On ferme la boite de dialogue
             */
            dialogueQuit.dispose();
        }

        /**
         * Lorsque l'on clique sur le bouton aide, une boite de dialogue s'affiche
         */
        if (e.getSource() == aideItem){
            dialogueAide.setSize(450,150); 
            dialogueAide.setLocationRelativeTo(null);
            dialogueAide.setVisible(true);
        }

        /**
         * Lorsque l'on clique sur le bouton à propos, une boite de dialogue s'affiche et donne les informations du logiciel
         */
        if(e.getSource() == propos){
            dialoguePropos.setSize(600,150); 
            dialoguePropos.setLocationRelativeTo(null);
            dialoguePropos.setVisible(true);
        }
    }
}
