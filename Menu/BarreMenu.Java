/**
 * BarreMenu.java
 * 
 * Created on 8/12/2017
 * @author Bayart Valentin 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BarreMenu extends JFrame{
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
     * Création d'un item ouvrir
     */
    private JMenuItem ouvrir = new JMenuItem("Ouvrir");

    /** 
     * Constructeur du menu
     * Permet de créer une barre de menu
     */
    public BarreMenu(){
        CardLayout card = new CardLayout();
        /**
         * Création de la fenêtre et définition de son titre
         */
        JFrame fen = new JFrame("Éditeur de niveau Shmup - CamelToe");
        /**
         * Création d'un panel
         */
        JPanel menu = new JPanel();
        /**
         * Paramétrage de la taille de la fenêtre, de son opération de fermeture par défaut 
         * et autorisation de la redimension
         */
        fen.setSize(800,600);
        fen.setResizable(true);
        fen.setDefaultCloseOperation(EXIT_ON_CLOSE);
        menu.setSize(100,100);

        /**
         * Ajout des onglets au menu
         */
        barre.add(fichier);
        barre.add(edition);
        barre.add(aide);

        /**
         * On ajoute la sous-menu ouvrir lorsque l'on clique sur fichier
         */
        ouvrir.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                card.next(menu);
            }
        });
        /**
         * Ajout du sous menu "ouvrir" à l'onglet fichier
         */
        fichier.add(menu);
        /**
         * Ajout de la barre à la fenêtre
         */
        fen.add(barre);
        /**
         * Rend la fenêtre visible
         */
        fen.setVisible(true);
    }
}
