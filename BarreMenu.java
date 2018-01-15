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
    private JMenu quitter = new JMenu("Quitter");

    /**
     * Création d'un item ouvrir
     */
    private JMenuItem ouvrir = new JMenuItem("Ouvrir...");
    
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
        barre.add(quitter);
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
	    setContentPane(global);
        
	    /**
         * Rend la fenêtre visible
         */
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == quitter){
            dispose();
        }
    }
}
