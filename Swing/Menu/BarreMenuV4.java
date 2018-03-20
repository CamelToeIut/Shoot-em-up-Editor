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
     * Création de la boite de choix de texture joueur
     */
    private JDialog dialogueJoueur = new JDialog(this, "Choix de la texture du Joueur", true);
    private JScrollPane panelJScroll = new JScrollPane();
    /**
     * Création de la boite de choix de texture ennemi
     */
    private JDialog dialogueEnnemi = new JDialog(this, "Choix de la texture du Joueur", true);

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
        contenu.add(new ImagePanel(new ImageIcon("img/map.png").getImage()));
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
        ajoutJoueur.addActionListener(this);

        JPanel panelCentreOng1 = new JPanel(new GridLayout(7,1));
        panelOng1.add(panelCentreOng1, BorderLayout.CENTER);

        panelCentreOng1.add(new JLabel("Points de vie : "));
        TextField lifeJ = new TextField("", 10);
        panelCentreOng1.add(lifeJ);

        panelCentreOng1.add(new JLabel("Vitesse de déplacement : "));
        TextField speedJ = new TextField("", 10);
        panelCentreOng1.add(speedJ);

        panelCentreOng1.add(new JLabel("Vitesse de tir : "));
        TextField speedshotJ = new TextField("", 10);
        panelCentreOng1.add(speedshotJ);

        panelCentreOng1.add(new JLabel("Cadence de tir : "));
        TextField cadenceJ = new TextField("", 10);
        panelCentreOng1.add(cadenceJ);

        panelCentreOng1.add(new JLabel("Puissance de Tir Unique : "));
        TextField powerSJ = new TextField("", 10);
        panelCentreOng1.add(powerSJ);

        panelCentreOng1.add(new JLabel("Puissance de Tir Parallèle : "));
        TextField powerPJ = new TextField("", 10);
        panelCentreOng1.add(powerPJ);

        panelCentreOng1.add(new JLabel("Puissance de Tir Éventail : "));
        TextField powerEJ = new TextField("", 10);
        panelCentreOng1.add(powerEJ);

        panelOng1.add(validerJoueur, BorderLayout.SOUTH);

        /**
         * Onglet ennemi
         */
        panelOng2.setLayout(new BorderLayout());

        panelOng2.add(ajoutEnnemi, BorderLayout.NORTH);
        ajoutEnnemi.addActionListener(this);

        JPanel panelCentreOng2 = new JPanel(new GridLayout(10,1));
        panelOng2.add(panelCentreOng2, BorderLayout.CENTER);

        panelCentreOng2.add(new JLabel("Points de vie : "));
        TextField lifeE = new TextField("", 10);
        panelCentreOng2.add(lifeE);

        panelCentreOng2.add(new JLabel("Points de récompense : "));
        TextField rewardE = new TextField("", 10);
        panelCentreOng2.add(rewardE);

        panelCentreOng2.add(new JLabel("Orientation de tir : "));
        JComboBox<String> comboBoxE  = new JComboBox<String>();
        comboBoxE.addItem("Tir Droit");
        comboBoxE.addItem("Tir Visé");
        comboBoxE.addItem("Tir Aléatoire");
        panelCentreOng2.add(comboBoxE);

        /**
         * Type arme
         */
        panelCentreOng2.add(new JLabel("Type arme : "));
        JComboBox<String> comboBoxArme  = new JComboBox<String>();
        comboBoxArme.addItem("Tir Unique");
        comboBoxArme.addItem("Tir Parallèle");
        comboBoxArme.addItem("Tir Éventail");
        panelCentreOng2.add(comboBoxArme);

        panelCentreOng2.add(new JLabel("Nombre munitions : "));
        TextField nbAmmo = new TextField("",10);
        panelCentreOng2.add(nbAmmo);

        panelCentreOng2.add(new JLabel("Ecart munitions : "));
        TextField ecAmmo = new TextField("",10);
        panelCentreOng2.add(ecAmmo);

        if(comboBoxArme.getSelectedItem().toString().equals("Tir Unique")){
            nbAmmo.setEditable(false);
            ecAmmo.setEditable(false);
        }else{
            nbAmmo.setEditable(true);
            ecAmmo.setEditable(true);
        }
        
        /**
         * Puissance de tir
         */        
        panelCentreOng2.add(new JLabel("Puissance de Tir : "));
        TextField powerE = new TextField("", 10);
        panelCentreOng2.add(powerE);

        panelCentreOng2.add(new JLabel("Cadence de tir : "));
        TextField cadenceE = new TextField("", 10);
        panelCentreOng2.add(cadenceE);

        panelCentreOng2.add(new JLabel("Vitesse de tir : "));
        TextField speedSE = new TextField("", 10);
        panelCentreOng2.add(speedSE);

        panelOng2.add(validerEnnemi, BorderLayout.SOUTH);

        /**
         * Ajout des texture a la boite de dialogue de choix de texture joueur
         */
        JPanel textureJ = new JPanel(new GridLayout(3,2));
        dialogueJoueur.add(textureJ);
        /**
         * Texture 1
         */
        ImageIcon textureJ1 = new ImageIcon("img/avion1.png");
        JButton boutonJ1 = new JButton(textureJ1);
        textureJ.add(boutonJ1);
        boutonJ1.addActionListener(this);
        /**
         * Texture 2
         */
        ImageIcon textureJ2 = new ImageIcon("img/avion2.png");
        JButton boutonJ2 = new JButton(textureJ2);
        textureJ.add(boutonJ2);
        boutonJ2.addActionListener(this);
        /**
         * Texture 3
         */
        ImageIcon textureJ3 = new ImageIcon("img/avion3.png");
        JButton boutonJ3 = new JButton(textureJ3);
        textureJ.add(boutonJ3);
        boutonJ3.addActionListener(this);
        /**
         * Texture 4
         */
        ImageIcon textureJ4 = new ImageIcon("img/avion4.png");
        JButton boutonJ4 = new JButton(textureJ4);
        textureJ.add(boutonJ4);
        boutonJ4.addActionListener(this);
        /**
         * Texture 5
         */
        ImageIcon textureJ5 = new ImageIcon("img/avion5.png");
        JButton boutonJ5 = new JButton(textureJ5);
        textureJ.add(boutonJ5);
        boutonJ5.addActionListener(this);
        /**
         * Texture 6
         */
        ImageIcon textureJ6 = new ImageIcon("img/avion6.png");
        JButton boutonJ6 = new JButton(textureJ6);
        textureJ.add(boutonJ6);
        boutonJ6.addActionListener(this);

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

        /**
         * Lorsque l'on clique sur le bouton choix du joueur, une boite de dialogue s'affiche et permet le choix de la texture
         */
        if(e.getSource() == ajoutJoueur){
            dialogueJoueur.setSize(250,400);
            dialogueJoueur.setLocationRelativeTo(null);
            dialogueJoueur.setVisible(true);
        }

        /**
         * Lorsque l'on clique sur le bouton ajout ennemi, une boite de dialogue s'affiche et permet le choix de la texture
         */
        if(e.getSource() == ajoutEnnemi){
            dialogueEnnemi.setSize(250,400);
            dialogueEnnemi.setLocationRelativeTo(null);
            dialogueEnnemi.setVisible(true);
        }
    }
}