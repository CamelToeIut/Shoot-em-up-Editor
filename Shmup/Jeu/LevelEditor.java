/**
 * LevelEditor.java
 * 
 * Created on 8/12/2017
 * Renamed on 20/03/2018
 *
 * @author Bayart Valentin 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import MG2D.geometrie.*;
import java.util.ArrayList;
public class LevelEditor extends JFrame implements ActionListener{

	BDeditor xml = new BDeditor();
	int ori,type,nbmun,ecmun;
	ArrayList<MG2D.geometrie.Point> aP = new ArrayList();
	ArrayList<Long> aL = new ArrayList();

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
     * Création de la boite de déplacement spatio temporel
     */
    private JDialog spatio = new JDialog(this, "Déplacement Spatio-Temporel", true);
    private JPanel contentSpatio = new JPanel(new GridLayout(6,1));
    JButton dpctSpatio = new JButton("Deplacements");
    JButton ajouterDpct = new JButton("Ajouter");
    JButton finSpatio = new JButton("Fin");

    /**
     * Textfields des dpcy spatiaux
     */
    JTextField dpctXAvant = new JTextField("",10);
    JTextField dpctXApres = new JTextField("",10);
    
    JTextField dpctYAvant = new JTextField("",10);
    JTextField dpctYApres = new JTextField("",10);

    JTextField tempsAvant = new JTextField("",10);
    JTextField tempsApres = new JTextField("",10);

    String xSpatio, ySpatio, tSpatio;
    boolean erreurSpatio = false;
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
    JTextField nomFic = new JTextField("",30);
    JButton enreg = new JButton("Enregistrer");

    /**
     * Variables pour l'onglet ennemi
     */
    JComboBox<String> comboBoxArme  = new JComboBox<String>();
    JTextField nbAmmo = new JTextField("",10);
    JTextField ecAmmo = new JTextField("",10);
    JTextField lifeE = new JTextField("", 10);
    JTextField rewardE = new JTextField("", 10);
    JComboBox<String> comboBoxE  = new JComboBox<String>();
    JTextField powerE = new JTextField("", 10);
    JTextField cadenceE = new JTextField("", 10);
    JTextField speedSE = new JTextField("", 10);

    /**
     * TextField pour l'onglet Joueur
     */
    JTextField lifeJ = new JTextField("", 10);
    JTextField speedJ = new JTextField("", 10);
    JTextField speedshotJ = new JTextField("", 10);
    JTextField cadenceJ = new JTextField("", 10);
    JTextField powerSJ = new JTextField("", 10);
    JTextField powerPJ = new JTextField("", 10);
    JTextField powerEJ = new JTextField("", 10);

    /**
     * Boutons boite dialogue joueur
     */
    JButton boutonJ1, boutonJ2, boutonJ3, boutonJ4, boutonJ5, boutonJ6;
    /**
     * Boutons boite dialogue ennemi
     */
    JButton boutonE1, boutonE2, boutonE3, boutonE4, boutonE5, boutonE6;
    
    /**
     * Variable a envoyer au XML
     */
    String pathImage = new String("");
    String vie, vitesse, vitesseTir, cadence, puissanceSimple, puissancePara, puissanceEve, nbMun, ecartMun, recompense, arme, modeTir;
    boolean choixJoueur = false, choixEnnemi = false;
    /** 
     * Constructeur du menu
     * Permet de créer une barre de menu
     */
    public LevelEditor(){
        /**
         * Paramétrage de la taille de la fenêtre, de son opération de fermeture par défaut 
         * et autorisation de la redimension
         */
        xml.parametreJeu(30,"logo.png",5000,400,700);
	    setBounds(275,0,800,600);
        setResizable(false);
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
        JPanel curseurP = new JPanel(new GridLayout(1,2));
        curseurP.add(nomFic);
        curseurP.add(enreg);
        enreg.addActionListener(this);
        global.add(curseurP, BorderLayout.SOUTH);

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

        ongletJoueur();

        panelOng1.add(validerJoueur, BorderLayout.SOUTH);
        validerJoueur.addActionListener(this);

        /**
         * Onglet ennemi
         */
        panelOng2.setLayout(new BorderLayout());

        panelOng2.add(ajoutEnnemi, BorderLayout.NORTH);
        ajoutEnnemi.addActionListener(this);

        ongletEnnemi();

        panelOng2.add(validerEnnemi, BorderLayout.SOUTH);
        validerEnnemi.addActionListener(this);

        /**
         * 
         * Ajout des texture a la boite de dialogue de choix de texture joueur
         */
        JPanel textureJ = new JPanel(new GridLayout(3,2));
        dialogueJoueur.add(textureJ);

        ajoutTexture(textureJ);

        JPanel textureE = new JPanel(new GridLayout(3,2));
        dialogueEnnemi.add(textureE);

        ajoutTextureE(textureE);

        /**
         * Panel code XML
         */
        code.add(new JButton("XML"));


        nbAmmo.setEditable(false);
        ecAmmo.setEditable(false);

        /**
         * Ajout a la boite de déplacement spatiaux
         */
        JPanel panelSouth = new JPanel(new GridLayout(1,1));
        /**
         * Anciennes valeur
         */
        /**
         * Position en X
         */
        contentSpatio.add(new JLabel(" Position X précédente"));
        contentSpatio.add(dpctXAvant);
        dpctXAvant.setEditable(false);
        /**
         * Position Y
         */
        contentSpatio.add(new JLabel(" Position Y précédente"));
        contentSpatio.add(dpctYAvant);
        dpctYAvant.setEditable(false);
        /**
         * Temps
         */
        contentSpatio.add(new JLabel(" Temps précédent"));
        contentSpatio.add(tempsAvant);
        tempsAvant.setEditable(false);
        /**
         * Valeurs actuelles
         */
        /**
         * Position X
         */
        contentSpatio.add(new JLabel(" Position X"));
        contentSpatio.add(dpctXApres);
        dpctXApres.addActionListener(this);
        /**
         * Position Y
         */
        contentSpatio.add(new JLabel(" Position Y"));
        contentSpatio.add(dpctYApres);
        dpctYApres.addActionListener(this);
        /**
         * Temps
         */
        contentSpatio.add(new JLabel(" Temps"));
        contentSpatio.add(tempsApres);
        tempsApres.addActionListener(this);
        
        panelSouth.add(ajouterDpct);
        ajouterDpct.addActionListener(this);
        panelSouth.add(finSpatio);
        finSpatio.addActionListener(this);
        spatio.add(panelSouth, BorderLayout.SOUTH);
        /**
         * Contenu de la boite
         */
        spatio.add(contentSpatio, BorderLayout.CENTER);

	    /** 
         * Rend la fenêtre visible
         */
        setVisible(true);
    }

    /**
     * Méthode permettant d'ajouter des boutons de texture
     * .getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
     */
    public void ajoutTexture(JPanel textureJ){
        ImageIcon textureJ1 = new ImageIcon("img/avion1.png");
        boutonJ1 = new JButton(textureJ1);
        textureJ.add(boutonJ1);
        boutonJ1.addActionListener(this);
        /**
         * Texture 2
         */
        ImageIcon textureJ2 = new ImageIcon("img/avion2.png");
        boutonJ2 = new JButton(textureJ2);
        textureJ.add(boutonJ2);
        boutonJ2.addActionListener(this);
        /**
         * Texture 3
         */
        ImageIcon textureJ3 = new ImageIcon("img/avion3.png");
        boutonJ3 = new JButton(textureJ3);
        textureJ.add(boutonJ3);
        boutonJ3.addActionListener(this);
        /**
         * Texture 4
         */
        ImageIcon textureJ4 = new ImageIcon("img/avion4.png");
        boutonJ4 = new JButton(textureJ4);
        textureJ.add(boutonJ4);
        boutonJ4.addActionListener(this);
        /**
         * Texture 5
         */
        ImageIcon textureJ5 = new ImageIcon("img/avion5.png");
        boutonJ5 = new JButton(textureJ5);
        textureJ.add(boutonJ5);
        boutonJ5.addActionListener(this);
        /**
         * Texture 6
         */
        ImageIcon textureJ6 = new ImageIcon("img/avion6.png");
        boutonJ6 = new JButton(textureJ6);
        textureJ.add(boutonJ6);
        boutonJ6.addActionListener(this);
    }

    /**
     * Méthode permettant d'ajouter des boutons de texture pour les ennemis
     */
    public void ajoutTextureE(JPanel textureE){
        ImageIcon textureE1 = new ImageIcon(new ImageIcon("img/ennemi_1_1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE1 = new JButton(textureE1);
        textureE.add(boutonE1);
        boutonE1.addActionListener(this);
        /**
         * Texture 2
         */
        ImageIcon textureE2 = new ImageIcon(new ImageIcon("img/ennemi_1_2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE2 = new JButton(textureE2);
        textureE.add(boutonE2);
        boutonE2.addActionListener(this);
        /**
         * Texture 3
         */
        ImageIcon textureE3 = new ImageIcon(new ImageIcon("img/ennemi_1_3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE3 = new JButton(textureE3);
        textureE.add(boutonE3);
        boutonE3.addActionListener(this);
        /**
         * Texture 4
         */
        ImageIcon textureE4 = new ImageIcon(new ImageIcon("img/ennemi_2_1.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE4 = new JButton(textureE4);
        textureE.add(boutonE4);
        boutonE4.addActionListener(this);
        /**
         * Texture 5
         */
        ImageIcon textureE5 = new ImageIcon(new ImageIcon("img/ennemi_2_2.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE5 = new JButton(textureE5);
        textureE.add(boutonE5);
        boutonE5.addActionListener(this);
        /**
         * Texture 6
         */
        ImageIcon textureE6 = new ImageIcon(new ImageIcon("img/ennemi_2_3.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        boutonE6 = new JButton(textureE6);
        textureE.add(boutonE6);
        boutonE6.addActionListener(this);
    }

    /**
     * Méthode permettant de créer un onglet joueur
     */
    public void ongletJoueur(){
        
        JPanel panelCentreOng1 = new JPanel(new GridLayout(7,1));
        panelOng1.add(panelCentreOng1, BorderLayout.CENTER);
        /**
         * Vie du joueur
         */
        panelCentreOng1.add(new JLabel("Points de vie : "));
        panelCentreOng1.add(lifeJ);
        /**
         * Vitesse de déplacement du joueur
         */
        panelCentreOng1.add(new JLabel("Vitesse de déplacement : "));
        panelCentreOng1.add(speedJ);
        /**
         * Vitesse de tir du joueur
         */
        panelCentreOng1.add(new JLabel("Vitesse de tir : "));
        panelCentreOng1.add(speedshotJ);
        /**
         * Cadence de tir du joueur
         */
        panelCentreOng1.add(new JLabel("Cadence de tir : "));
        panelCentreOng1.add(cadenceJ);
        /**
         * Puissance de chaque mode de tir
         */
        /**
         * Simple
         */
        panelCentreOng1.add(new JLabel("Puissance de Tir Unique : "));
        panelCentreOng1.add(powerSJ);
        /**
         * Parallèles
         */
        panelCentreOng1.add(new JLabel("Puissance de Tir Parallèle : "));
        panelCentreOng1.add(powerPJ);
        /**
         * Tir en éventail
         */
        panelCentreOng1.add(new JLabel("Puissance de Tir Éventail : "));
        panelCentreOng1.add(powerEJ);

    }

    /**
     * Méthode permettant de créer l'onglet ennemi
     */
    public void ongletEnnemi(){
    
        /**
         * Panel de l'onglet = gridLayout
         */
        JPanel panelCentreOng2 = new JPanel(new GridLayout(10,1));
        panelOng2.add(panelCentreOng2, BorderLayout.CENTER);
        /**
         * Zone de texte pour points de vie
         */
        panelCentreOng2.add(new JLabel("Points de vie : "));
        panelCentreOng2.add(lifeE);
        /**
         * Point de récompense a chaque ennemi tué
         */
        panelCentreOng2.add(new JLabel("Points de récompense : "));
        panelCentreOng2.add(rewardE);
        /**
         * Orientation des tirs ennemis
         */
        panelCentreOng2.add(new JLabel("Orientation de tir : "));
        comboBoxE.addItem("Tir Droit");
        comboBoxE.addItem("Tir Visé");
        comboBoxE.addItem("Tir Aléatoire");
        panelCentreOng2.add(comboBoxE);
        comboBoxE.addActionListener(this);
        /**
         * Type de tir ennemi
         */
        panelCentreOng2.add(new JLabel("Type arme : "));
        comboBoxArme.addItem("Tir Unique");
        comboBoxArme.addItem("Tir Parallèle");
        comboBoxArme.addItem("Tir Éventail");
        panelCentreOng2.add(comboBoxArme);
        comboBoxArme.addActionListener(this);
        /**
         * Nombre de munitions de l'arme
         */
        panelCentreOng2.add(new JLabel("Nombre munitions : "));
        panelCentreOng2.add(nbAmmo);
        /**
         * Ecart entre les tirs
         */
        panelCentreOng2.add(new JLabel("Ecart munitions : "));
        panelCentreOng2.add(ecAmmo);
        
        /**
         * Puissance de tir
         */        
        panelCentreOng2.add(new JLabel("Puissance de Tir : "));
        panelCentreOng2.add(powerE);
        /**
         * Cadence de tir
         */
        panelCentreOng2.add(new JLabel("Cadence de tir : "));
        panelCentreOng2.add(cadenceE);
        /**
         * Vitesse de déplacement des tirs
         */
        panelCentreOng2.add(new JLabel("Vitesse de tir : "));
        panelCentreOng2.add(speedSE);
        /**
         * Bouton de déplacement spatio
         */
        panelCentreOng2.add(new JLabel("Déplacement :"));
        panelCentreOng2.add(dpctSpatio);
        dpctSpatio.addActionListener(this);
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
         * Appui sur le bouton de déplacement spatio
         */
        if(e.getSource() == dpctSpatio){
            spatio.setSize(400,250);
            spatio.setLocationRelativeTo(null);
            spatio.setVisible(true);
        }
        /**
         * Lorsque l'on clique sur le bouton ajout ennemi, une boite de dialogue s'affiche et permet le choix de la texture
         */
        if(e.getSource() == ajoutEnnemi){
            dialogueEnnemi.setSize(250,400);
            dialogueEnnemi.setLocationRelativeTo(null);
            dialogueEnnemi.setVisible(true);
        }

        if(e.getSource() == comboBoxArme){
            /**
             * Listener
             */
            if(comboBoxArme.getSelectedItem().toString().equals("Tir Unique")){
                nbAmmo.setEditable(false);
                ecAmmo.setEditable(false);
                nbAmmo.setText("");
                ecAmmo.setText("");
                this.repaint();
            }else{
                nbAmmo.setEditable(true);
                ecAmmo.setEditable(true);
                this.repaint();
            }
        }

        /**
         * Action Listener des boutons
         */
        /**
         * Avion 1
         */
        if(e.getSource() == boutonJ1){
            dialogueJoueur.dispose();
            pathImage = "img/avion1";
            choixJoueur = true;
        }
        /**
         * Avion 2
         */
        if(e.getSource() == boutonJ2){
            dialogueJoueur.dispose();
            pathImage = "img/avion2";
            choixJoueur = true;
        }
        /**
         * Avion 3
         */
        if(e.getSource() == boutonJ3){
            dialogueJoueur.dispose();
            pathImage = "img/avion3";
            choixJoueur = true;
        }
        /**
         * Avion 4
         */
        if(e.getSource() == boutonJ4){
            dialogueJoueur.dispose();
            pathImage = "img/avion4";
            choixJoueur = true;
        }
        /**
         * Avion 5
         */
        if(e.getSource() == boutonJ5){
            dialogueJoueur.dispose();
            pathImage = "img/avion5";
            choixJoueur = true;
        }
        /**
         * Avion 6
         */
        if(e.getSource() == boutonJ6){
            dialogueJoueur.dispose();
            pathImage = "img/avion6";
            choixJoueur = true;
        }

        /**
         * Action Listener des boutons ennemis afin de selectionner une texture
         */
        /**
         * Avion 1
         */
        if(e.getSource() == boutonE1){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_1_1";
            choixEnnemi = true;
        }
        /**
         * Avion 2
         */
        if(e.getSource() == boutonE2){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_1_2";
            choixEnnemi = true;
        }
        /**
         * Avion 3
         */
        if(e.getSource() == boutonE3){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_1_3";
            choixEnnemi = true;
        }
        /**
         * Avion 4
         */
        if(e.getSource() == boutonE4){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_2_1";
            choixEnnemi = true;
        }
        /**
         * Avion 5
         */
        if(e.getSource() == boutonE5){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_2_2";
            choixEnnemi = true;
        }
        /**
         * Avion 6
         */
        if(e.getSource() == boutonE6){
            dialogueEnnemi.dispose();
            pathImage = "img/ennemi_2_3";
            choixEnnemi = true;
        } 

        /**
         * ActionListener du clique sur valider et envoie des donnés récupérées au xml
         */
        if(e.getSource() == validerJoueur){
            boolean erreur = false;
            /**
             * On teste si une valeur est entrée dans chacun des champs de texte
             * Si une valeur manque dans un champs de texte, on affiche une erreur
             * Sinon on envoie au XML
             */
            if((vie = lifeJ.getText()).equals("")){
                System.out.println("Veuillez entrer une valeur dans le champs vie du joueur");
                erreur = true;
            }
            if((vitesse = speedJ.getText()).equals("")){
                System.out.println("Veuillez entrer une vitesse au joueur");
                erreur = true;
            }
            if((vitesseTir = speedshotJ.getText()).equals("")){
                System.out.println("Veuillez entrer une vitesse de tir au joueur");
                erreur = true;
            }
            if((cadence = cadenceJ.getText()).equals("")){
                System.out.println("Veuillez entrer une cadence de tir au joueur");
                erreur = true;
            }
            if((puissanceSimple = powerSJ.getText()).equals("")){
                System.out.println("Veuillez entrer une puissance pour le mode de tir simple");
                erreur = true;
            }
            if((puissancePara = powerPJ.getText()).equals("")){
                System.out.println("Veuillez entrer une puissance pour le mode de tir parallèle");
                erreur = true;
            }
            if((puissanceEve = powerEJ.getText()).equals("")){
                System.out.println("Veuillez entrer une puissance pour le mode de tir éventail");
                erreur = true;
            }
            if(choixJoueur == false){
                System.out.println("Veuillez choisir une texture au joueur");
                erreur = true;
            }
            System.out.println("\n");

            if(erreur == false){
                xml.ajoutJoueurXML(pathImage.concat(".png"),pathImage.concat(".hitbox"),Integer.parseInt(vie),Integer.parseInt(vitesse),
                Integer.parseInt(cadence),Integer.parseInt(puissanceSimple),Integer.parseInt(puissancePara),
                Integer.parseInt(puissanceEve),Integer.parseInt(vitesseTir));
            }
            this.repaint();
        }

        if(e.getSource() == ajouterDpct){
            if(dpctXApres.getText().equals("")){
                System.out.println("Manque x");
                erreurSpatio = true;
            }
            if(dpctYApres.getText().equals("")){
                erreurSpatio = true;
                System.out.println("Manque y");
            }
            if(tempsApres.getText().equals("")){
                erreurSpatio = true;
                System.out.println("Manque temps");
            }
            if(erreurSpatio == false){
                /**
                 * Conservation des valeurs
                 */
                xSpatio = dpctXApres.getText();
                ySpatio = dpctYApres.getText();
                tSpatio = tempsApres.getText();

                /**
                 * Vider les champs du bas
                 */
                dpctXApres.setText("");
                dpctYApres.setText("");
                tempsApres.setText("");

                /**
                 * Changement des valeurs des champs du haut
                 */
                dpctXAvant.setText(xSpatio);
                dpctYAvant.setText(ySpatio);
                tempsAvant.setText(tSpatio);
                /**
                 * Ajout à l'Array de xSpatio ySpatio et tSpatio
                 */
            }

        }

        if(e.getSource() == finSpatio){
            spatio.dispose();
        }

        /**
         * ActionListener du bouton valider ennemi pour l'envoie au xml
         */
        if(e.getSource() == validerEnnemi){
            boolean erreurE = false;

            /**
             * On teste chacune de valeur entrées dans les combo box et les text fields 
             * Si une valeur est manquante, le contenu n'est pas envoyé au xml et une erreur est envoyée
             * Sinon suite au clic sur envoyer, les données sont transmises au xml
             */

            if((vie = lifeE.getText()).equals("")){
                System.out.println("Veuillez entrer une valeur dans le champs vie de l'ennemi");
                erreurE = true;
            }
            if((recompense = rewardE.getText()).equals("")){
                System.out.println("Veuillez entrer une valeur de récompense à l'ennemi");
                erreurE = true;
            }
            /**
             * Condition supplémentaire dûe au mode de tir choisi
             */
            if( (nbMun = nbAmmo.getText()).equals("") && 
            !(comboBoxArme.getSelectedItem().toString().equals("Tir Unique")) ){
                System.out.println("Veuillez entrer unnombre de munition pour l'ennemi");
                erreurE = true;
            }
            if( (ecartMun = ecAmmo.getText()).equals("") &&
            !(comboBoxArme.getSelectedItem().toString().equals("Tir Unique")) ){
                System.out.println("Veuillez indiquer l'écart entre les munition de l'ennemi");
                erreurE = true;
            }
            if((puissanceSimple = powerE.getText()).equals("")){
                System.out.println("Veuillez entrer une puissance pour l'ennemi'");
                erreurE = true;
            }
            if((cadence = cadenceE.getText()).equals("")){
                System.out.println("Veuillez entrer une cadence à l'ennemi");
                erreurE = true;
            }
            if((vitesseTir = speedSE.getText()).equals("")){
                System.out.println("Veuillez entrer une vitesse de tir à l'ennemi");
                erreurE = true;
            }
            arme = comboBoxArme.getSelectedItem().toString();
            modeTir = comboBoxE.getSelectedItem().toString();
            /**
             * Condition de choix de texture
             */
            if(choixEnnemi == false){
                System.out.println("Veuillez choisir une texture a l'ennemi");
                erreurE = true;
            }
            System.out.println("\n");

            if(erreurE == false){
               if(arme.equals("Tir Unique")){
               		nbmun=0;
               		ecmun=0;
               		type=0;
               		if(modeTir.equals("Tir Visé")){
               			ori=0;
               		}else if(modeTir.equals("Tir Droit")){
               			ori=1;
               		}else{
               			ori=2;
               		}
               }else if(arme.equals("Tir Éventail")){
              		nbmun=Integer.parseInt(nbMun);
               		ecmun=Integer.parseInt(ecartMun);
               		type=1;
               		if(modeTir.equals("Tir Visé")){
               			ori=0;
               		}else if(modeTir.equals("Tir Droit")){
               			ori=1;
               		}else{
               			ori=2;
               		}
               }else{
               		nbmun=Integer.parseInt(nbMun);
               		ecmun=Integer.parseInt(ecartMun);
               		type=2;
               		if(modeTir.equals("Tir Visé")){
               			ori=0;
               		}else if(modeTir.equals("Tir Droit")){
               			ori=1;
               		}else{
               			ori=2;
               		}
               }
               xml.ajouterEnnemi(pathImage.concat(".png"),Integer.parseInt(vie),Integer.parseInt(vitesseTir),
               			Long.parseLong(cadence),Integer.parseInt(puissanceSimple),ori,type,nbmun,
               			ecmun,aP,aL,Integer.parseInt(recompense),50,80);
               			aP.clear();
               			aL.clear();
            }
            this.repaint();
        }

        if(e.getSource() == enreg){
            String lien = nomFic.getText();

            if(!nomFic.getText().equals("")){
            	xml.ajoutEnnemiXML();
                xml.enregistrerFichier(lien);
            }
        }
    }
}

//1022, 1013 et 947
