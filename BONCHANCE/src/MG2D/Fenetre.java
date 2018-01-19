/*********************************************************************/
/*                                                                   */
/* Copyright 2012-2017 Rémi Synave, Anthony Desitter,                */
/*                     Nicolas Dubrunfaut, Maxime Langa,             */
/*                     Guillaume Langa                               */
/*                                                                   */
/* This file is part of MG2D.                                        */
/* This library uses javazoom library piece of code                  */
/* http://www.javazoom.net                                           */
/* and can be found with this library (file jlayer1.0.1.tar.gz)      */
/*                                                                   */
/* MG2D is free software: you can redistribute it and/or modify      */
/* it under the terms of the GNU General Public License as published */
/* by the Free Software Foundation, either version 3 of the License, */
/* or (at your option) any later version.                            */
/*                                                                   */
/* MG2D is distributed in the hope that it will be useful,           */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of    */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the      */
/* GNU General Public License for more details.                      */
/*                                                                   */
/* You should have received a copy of the GNU General Public License */
/* along with MG2D. If not, see <http://www.gnu.org/licenses/>.      */
/*                                                                   */
/*********************************************************************/

package MG2D;

import MG2D.geometrie.*;

import java.awt.Dimension;

import javax.swing.JFrame;

import java.awt.Robot;
import java.util.Calendar;
import java.util.Date;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Cette classe crée une fenêtre comportant une zone d'affichage aux dimensions précisées.
 * <br /><br />
 * L'origine, le point (0,0) se trouve en bas à gauche de la zone d'affichage.<br />
 * L'utilisateur peut définir un titre, une largeur et une hauteur à cette fenêtre.<br />
 * Par la suite, il pourra, au travers de cette classe, gérer un ensemble de formes affichés.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 */
public class Fenetre extends JFrame {

    // Attributs //

    private Dimension d;	// Permet de gérer correctement la dimension du Panneau avec la méthode p.setPreferredSize ( Dimension ) //
    private Panneau p;

    //Pour la gestion d'un clavier et d'une souris classique. Attributs qui ne seront pas forcément instanciés.
    private Clavier c;
    private Souris s;

    //Pour le calcul des fps
    private long dernierEvt;
    private long dernierAffichage;
    private boolean affichageFPS;

    //Pour l'affichage du nombre de primitives
    private boolean affichageNbPrimitives;

    String titre;

    // Constructeur //

    /**
     * Construit une Fenetre ayant une zone d'affichage de 800x600 pixels et de titre "Mon appli MG2D".
     * <br /><br />
     * Par défaut, la fenêtre est centrée et ne peut pas être redimensionnée.
     */
    public Fenetre () {

	//Non instanciation du clavier et de la souris
	c=null;
	s=null;

	d = new Dimension ( 800, 600 );
	p = new Panneau ();

	p.setPreferredSize ( d ); // On indique que l'on souhaite avoir un Panneau de la Dimension (largeur, hauteur) //

	this.setContentPane ( p );

	this.pack(); // Permet d'attribuer automatiquement la dimension de la Fenetre grâce à ce qui la compose (ici en l'occurence, le Panneau) //

	this.setTitle ( new String("Mon appli MG2D") );
	titre=new String("Mon appli MG2D");

	this.setLocationRelativeTo ( null );
	this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
	this.setResizable ( false ); // On empêche le redimensionnement pour éviter les problèmes de centrages des objets //

	this.setVisible ( true );

	if ( p.getWidth() != d.getWidth() || p.getHeight() != d.getHeight() ) { // Si le Panneau ne fait toujours pas la taille voulue, on force le changement //

	    p.setSize ( d );
	    p.setMinimumSize( d );
	    p.setMaximumSize( d );
	    this.pack();
	    // Je ne comprends pas pourquoi ces 2 lignes sont nécessaires mais il ne faut pas les enlever !
	    revalidate();
	    pack();
	}
	dernierEvt = new Date().getTime();
	affichageFPS=false;
	dernierAffichage=dernierEvt;
	affichageNbPrimitives=false;
    }

    /**
     * Crée une fenêtre possédant un titre, une largeur et une hauteur.
     * <br /><br />
     * Attention, la taille précisée en paramètre correspond à la taille de la zone d'affichage. La taille de la fenêtre sera légérement plus grande.<br />
     * <br /><br />
     * Par défaut, la fenêtre est centrée et ne peut pas être redimensionnée.
     * @param ttitre Titre de la Fenetre.
     * @param largeur Largeur de l'espace de travail.
     * @param hauteur Hauteur de l'espace de travail.
     */
    public Fenetre ( String ttitre, int largeur, int hauteur ) {

	//Non instanciation du clavier et de la souris
	c=null;
	s=null;

	d = new Dimension ( largeur, hauteur );
	p = new Panneau ();

	p.setPreferredSize ( d ); // On indique que l'on souhaite avoir un Panneau de la Dimension (largeur, hauteur) //

	this.setContentPane ( p );

	this.pack(); // Permet d'attribuer automatiquement la dimension de la Fenetre grâce à ce qui la compose (ici en l'occurence, le Panneau) //

	this.setTitle ( new String(ttitre) );
	titre=new String(ttitre);

	this.setLocationRelativeTo ( null );
	this.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
	this.setResizable ( false ); // On empêche le redimensionnement pour éviter les problèmes de centrages des objets //

	this.setVisible ( true );

	if ( p.getWidth() != d.getWidth() || p.getHeight() != d.getHeight() ) { // Si le Panneau ne fait toujours pas la taille voulue, on force le changement //

	    p.setSize ( d );
	    p.setMinimumSize( d );
	    p.setMaximumSize( d );
	    this.pack();
	    // Je ne comprends pas pourquoi ces 2 lignes sont nécessaires mais il ne faut pas les enlever !
	    revalidate();
	    pack();
	}
	dernierEvt = new Date().getTime();
	affichageFPS=false;
	dernierAffichage=dernierEvt;
	affichageNbPrimitives=false;
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne une copie du Panneau lié à la fenêtre.
     * @return copie du Panneau lié à la Fenetre.
     */
    public Panneau getP () {

	return new Panneau(p);
    }

    /**
     * Retourne un clavier par défaut permettant une interaction entre le programme et l'utilisateur.<br ∕>
     * Permet une gestion des touches par le clavier prédéfini dans la bibliothèque.<br />
     * Cette gestion se limite à la gestion des touches a/z/e/q/s/d, espace, entrée et les flèches directionnelles.
     * @return Un clavier classique.
     * @see Clavier
     */
    public Clavier getClavier () {
	c = new Clavier();
	addKeyListener(c);
	return c;
    }

    /**
     * Retourne un clavier par défaut permettant une interaction entre le programme et l'utilisateur.<br ∕>
     * Permet une gestion des clics de la souris prédéfini dans la bibliothèque.<br />
     * Cette gestion permet de détecter les clics gauche, droit, milieu et de connaitre la position du pointeur dans la fenêtre.
     * @return Une souris classique.
     * @see Souris
     */
    public Souris getSouris () {
	s = new Souris((int)(d.getHeight()));
	addMouseListener(s);addMouseMotionListener(s);
	p.addMouseListener(s);p.addMouseMotionListener(s);
	return s;
    }

    /**
     * Permet de faire appel à la méthode getMilieu() d'un Panneau.
     * <br /><br />
     * Il s'agit simplement d'un relais entre le programme principal et la classe Panneau.<br />
     * On évite ainsi à l'utilisateur d'écrire f.getP().getMilieu();
     * @return Objet de type Point ayant pour coordonnées le milieu de la zone d'affichage.
     * @see Panneau
     */
    public Point getMilieu () {
	// p.getMilieu retourne déjà un "new Point"
	return p.getMilieu();
    }

    /**
     * Permet l'affichage ou non du nombre d'image par seconde dans la barre de titre.
     * @param b paramètre à "true" si l'affichage doit se faire, "false" sinon.
     */
    public void setAffichageFPS(boolean b){
	affichageFPS=b;
	if(b==false && !affichageNbPrimitives)
	    setTitle(titre);
    }

    /**
     * Permet l'affichage ou non du nombre de primitives dans la fenêtre. Cet affichage se fera dans la barre de titre.
     * @param b paramètre à "true" si l'affichage doit se faire, "false" sinon.
     */
    public void setAffichageNbPrimitives(boolean b){
	affichageNbPrimitives=b;
	if(b==false && !affichageFPS)
	    setTitle(titre);
    }

    // Méthodes //
    /**
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Component.html#repaint()" target="_blank">Repaint de Component</a>
     */
    @Override
    public void repaint(){
	super.repaint();
	getToolkit().sync();
    }

    /**
     * Applique un rafraichissement de la fenêtre.<br ∕>
     * Cette méthode va mettre à jour l'affichage.<br /><br />
     * Permet d'appliquer la méthode repaint() à la fenêtre. Méthode présente dans un soucis de "Francisation" du code.
     */
    public void rafraichir () {
	long temp=new Date().getTime();
	double fps = 1000.0/(temp-dernierEvt);
	String affichageSup="";

	if(affichageNbPrimitives && ((temp-dernierAffichage)>1000))
	    affichageSup=" - "+getP().getA().size()+" primitives";

	if(affichageFPS && ((temp-dernierAffichage)>1000))
	    affichageSup=affichageSup+" - "+fps;

	if((affichageFPS || affichageNbPrimitives) && ((temp-dernierAffichage)>1000)){
	    setTitle(titre+affichageSup);
	    dernierAffichage=temp;
	}

	dernierEvt=temp;
	repaint();
    }

    /**
     * Permet d'effacer totalement la zone d'affichage.
     * <br /><br />
     * Pour que l'effacement total ait lieu, ne pas oublier d'appeler la méthode rafraichir().
     * Il s'agit simplement d'un relais entre le programme principal et la classe Panneau.<br />
     * On évite ainsi à l'utilisateur d'écrire f.getP().effacer();
     * @see Panneau
     * @see #rafraichir rafraichir()
     */
    public void effacer () {
	p.effacer();
    }

    /**
     * Permet d'ajouter une forme géométrique à la zone d'affichage.
     * <br /><br />
     * Pour que la forme apparaisse dans la zone d'affichage, ne pas oublier d'appeler la méthode rafraichir().
     * Il s'agit simplement d'un relais entre le programme principal et la classe Panneau.<br />
     * On évite ainsi à l'utilisateur d'écrire f.getP().ajouter();
     * @see Panneau
     * @see Dessin
     * @see #rafraichir rafraichir()
     */
    public void ajouter ( Dessin d ) {

	p.ajouter ( d );
    }

    /**
     * Permet de supprimer une forme géométrique de la zone d'affichage.
     * <br /><br />
     * Pour que la suppression de la forme ait lieu, ne pas oublier d'appeler la méthode rafraichir().
     * Il s'agit simplement d'un relais entre le programme principal et la classe Panneau.<br />
     * On évite ainsi à l'utilisateur d'écrire f.getP().supprimer();
     * @see Panneau
     * @see Dessin
     * @see #rafraichir rafraichir()
     */
    public void supprimer ( Dessin d ) {

	p.supprimer ( d );
    }

    /**
     * Permet de faire un screenshot de la zone d'affichage.
     * <br /><br />
     * L'image sera créé dans le répertoire courant et aura la date du jour pour nom.
     */
    public void snapShot(){
	try{
	    Robot rb = new Robot();
	    Calendar cal=Calendar.getInstance();
	    String mois=""+(cal.get(cal.MONTH)+1);
	    if(mois.length()==1)
		mois="0"+mois;
	    String jour=""+cal.get(cal.DAY_OF_MONTH);
	    if(jour.length()==1)
		jour="0"+jour;
	    String heure=""+cal.get(cal.HOUR_OF_DAY);
	    if(heure.length()==1)
		heure="0"+heure;
	    String minute=""+cal.get(cal.MINUTE);
	    if(minute.length()==1)
		minute="0"+minute;
	    String seconde=""+cal.get(cal.SECOND);
	    if(seconde.length()==1)
		seconde="0"+seconde;
	    String milliseconde=""+cal.get(cal.MILLISECOND);
	    if(milliseconde.length()==1)
		milliseconde="0"+milliseconde;
	    if(milliseconde.length()==2)
		milliseconde="0"+milliseconde;
	    String chemin=new String(cal.get(cal.YEAR)+"_"+mois+"_"+jour+"__"+heure+"_"+minute+"_"+seconde+"_"+milliseconde+".jpg");
	    BufferedImage bufImage = rb.createScreenCapture(new java.awt.Rectangle(getX(),getY(),getWidth(),getHeight()));
	    File imageFile = new File(chemin);
	    imageFile.createNewFile();
	    ImageIO.write(bufImage, "jpeg", imageFile);
	}
	catch(Exception e){
	    System.out.println(e);
	}
    }

    /**
     * Permet de faire un screenshot de la zone d'affichage.
     * @param chemin Nom du fichier où doit être sauvegarder le screenshot.
     */
    public void snapShot(String chemin){
	try{
	    BufferedImage bufImage = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
	    // call the Component's paint method, using
	    // the Graphics object of the image.
	    paint( bufImage.getGraphics() ); // alternately use .printAll(..)
	    File imageFile = new File(chemin);
	    imageFile.createNewFile();
	    ImageIO.write(bufImage, "jpeg", imageFile);
	}
	catch(Exception e){
	    System.out.println(e);
	}
    }

    /**
     * Permet la fermeture de la fenêtre.
     */
    public void fermer(){
	dispose();
    }

    /**
     * Retourne une description de la fenêtre.
     * @return Chaîne de caractères contenant la description de la fenêtre.
     */
    public String toString(){
	return new String("Fenêtre de taille "+d.getWidth()+"x"+d.getHeight());
    }
}
