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

package MG2D.geometrie;

import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Cette classe permet la création de textures.<br />
 * Une texture est une image rectangulaire qui peut être affichée dans la zone d'affichage. La transparence est gérée. Les formats d'images supportées sont : gif, jpg, png et bmp.<br />
 * Contrairement aux rectangles, la modification des points A ou B (des coins bas gauche ou haut droit) ne changent pas la dimension de l'image. Pour modifier la taille de l'image, il vous faudra passer par les méthodes setHauteur et setLargeur.<br />
 * Si une couleur est spécifiée lors de la construction de l'objet (noir sinon), que l'image utilise la transparence et que l'on demande à afficher la forme pleine alors la couleur sera utilisée comme fond.<br />
 * Une Texture est définie par une image.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Rectangle
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html" target="_blank">BufferedImage</a>
 * @see Rectangle#setHauteur
 * @see Rectangle#setLargeur
 */
public class Texture extends Rectangle {

    // Attributs //

    private BufferedImage img;
    private ArrayList<Dessin> hitbox;

    // Constructeurs //

    /**
     * Constructeur par défaut qui ne fait que lancer une exception.
     * @exception java.lang.RuntimeException Ce constructeur ne prenant pas de parmètre ne fait que lancer l'exception.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Texture(){
	throw new java.lang.RuntimeException("Le constructeur par défaut de Texture ne peut être appelé. Il faut au moins spécifier une image.");
    }

    /**
     * Contruit une texture sur le modèle d'une autre texture.
     * @param t La texture à copier.
     */
    public Texture ( Texture t ){
	super(t);
	img = t.getImg().getSubimage(0,0,t.getLargeur(),t.getHauteur());
	// TODO - chercher comment copier une hitbox
	hitbox = new ArrayList<Dessin>(t.hitbox);
    }

    // Sans couleur de fond //

    /**
     * Construit une Texture à partir d'un chemin vers un fichier image et d'un Point.<br />
     * <br /><br />
     * La taille de l'image correspondra à la taille réelle de l'image. Elle ne sera pas déformée.<br />
     * Par défaut, la transparence est activée.
     * @param chemin Chaîne de caractères représente le chemin d'accès vers l'image.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @see Point
     */
    public Texture ( String chemin, Point a ) {

	super ( a, 0, 0 , false);

	try {

	    URL url = this.getClass().getResource ( "/"+chemin );
	    img = ImageIO.read ( url );
 
	}

	catch ( IOException e ) {

	    System.out.println ("[!] Erreur : L'image "+ chemin.substring(1,chemin.length()) +" est introuvable.\n" + e);
	}

	int largeur = img.getWidth ( null );
	int hauteur = img.getHeight ( null );
	setLargeur(largeur);
	setHauteur(hauteur);
	hitbox = new ArrayList<Dessin>();

	super.setB ( new Point ( a.getX() + largeur, a.getY() + hauteur ) );
    }

    /**
     * Construit une Texture à partir d'un chemin vers un fichier image, d'un Point, une largeur et une hauteur.<br />
     * Par défaut, la transparence est activée.
     * @param chemin Chaîne de caractères représente le chemin d'accès vers l'image.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @param larg Largeur souhaitée de l'image.
     * @param haut Lauteur souhaitée de l'image.
     * @see Point
     */
    public Texture ( String chemin, Point a, int larg, int haut ) {

	super ( a, 0, 0 , false);

	try {

	    URL url = getClass().getResource ( "/"+chemin );
	    img = ImageIO.read ( url );
	}

	catch ( IOException e ) {

	    System.out.println ("[!] Erreur : L'image "+ chemin.substring(1,chemin.length()) + " est introuvable.\n" + e);
	}

	setLargeur(larg);
	setHauteur(haut);
	hitbox = new ArrayList<Dessin>();

	super.setB ( new Point ( a.getX() + larg, a.getY() + haut ) );
    }

    // Avec couleur de fond //

    /**
     * Construit une Texture à partir d'un chemin vers un fichier image et d'un Point.
     * <br /><br />
     * La taille de l'image correspondra à la taille réelle de l'image. Elle ne sera pas déformée.<br />
     * Par défaut, la transparence est désactivée.
     * @param couleur Couleur de fond
     * @param chemin Chaîne de caractères représente le chemin d'accès vers l'image.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @see Couleur
     * @see Point
     */
    public Texture ( Couleur couleur, String chemin, Point a ) {

	super ( couleur, a, 0, 0 , true);

	try {

	    URL url = getClass().getResource ( "/"+chemin );
	    img = ImageIO.read ( url );
	}

	catch ( IOException e ) {

	    System.out.println ("[!] Erreur : L'image "+ chemin.substring(1,chemin.length())  +" est introuvable.\n" + e);
	}

	int largeur = img.getWidth ( null );
	int hauteur = img.getHeight ( null );

	setLargeur(largeur);
	setHauteur(hauteur);
	hitbox = new ArrayList<Dessin>();

	setB ( new Point ( a.getX() + largeur, a.getY() + hauteur ) );
    }

    /**
     * Construit une Texture à partir d'un chemin vers un fichier image, d'un Point, d'une largeur et d'une hauteur.<br />
     * Par défaut, la transparence est désactivée.
     * @param couleur Couleur de fond
     * @param chemin Chaîne de caractères représente le chemin d'accès vers l'image.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @param larg Largeur souhaitée de l'image.
     * @param haut Hauteur souhaitée de l'image.
     * @see Couleur
     * @see Point
     */
    public Texture ( Couleur couleur, String chemin, Point a, int larg, int haut ) {

	super ( couleur, a, 0, 0 , true);

	try {

	    URL url = getClass().getResource ( "/"+chemin );
	    img = ImageIO.read ( url );
	}

	catch ( IOException e ) {

	    System.out.println ("[!] Erreur : L'image "+chemin.substring(1,chemin.length()) +" est introuvable.\n" + e);
	}

	setLargeur(larg);
	setHauteur(haut);
	hitbox = new ArrayList<Dessin>();

	setB ( new Point ( a.getX() + larg, a.getY() + haut ) );
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne l'image.
     * @return L'image
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html" target="_blank">BufferedImage</a>
     */
    public BufferedImage getImg () {

	return img;
    }

    /**
     * Retourne la transparence de l'image.<br />
     * Cette méthode est l'inverse de getPlein.
     * @return Vrai si la transparence est activée, faux sinon.
     * @see Rectangle#getPlein
     */
    public boolean getTransparent () {
	return !getPlein();
    }

    /**
     * Retourne la hitbox - ensemble de primitives géométriques formant la hitbox de la texture.
     * @return Une liste contenant l'ensemble des primitives gégométriques formanant la hitbox.
     */
    public ArrayList<Dessin> getHitbox(){
	return hitbox;
    }

    // Setter //

    /**
     * Permet d'attribuer une nouvelle image à la texture.<br />
     * @param img La nouvelle image à attribuer à la texture.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferedImage.html" target="_blank">BufferedImage</a>
     */
    public void setImg ( BufferedImage img ) {

	this.img = img;
	int largeur = img.getWidth ( null );
	int hauteur = img.getHeight ( null );
	setLargeur(largeur);
	setHauteur(hauteur);

	setB ( new Point ( getA().getX() + largeur, getA().getY() + hauteur ) );
    }

    /**
     * Permet d'attribuer une nouvelle image à la texture.<br />
     * Créé une nouvelle image grâce au chemin indiqué en paramètre.
     * @param chemin Chemin d'accès vers une image.
     */
    public void setImg ( String chemin ) {

	try {

	    URL url = getClass().getResource ( "/"+chemin );
	    img = ImageIO.read ( url );
	}

	catch ( IOException e ) {

	    System.out.println ("[!] Erreur : L'image "+chemin.substring(1,chemin.length())+" est introuvable.\n" + e);
	}

	int largeur = img.getWidth ( null );
	int hauteur = img.getHeight ( null );
	setLargeur(largeur);
	setHauteur(hauteur);

	super.setB ( new Point ( getA().getX() + largeur, getA().getY() + hauteur ) );

    }


    /**
     * Permet de rendre ou non le fond de l'image transparent.<br />
     * Cette méthode est l'inverse de setPlein.
     * @param transparent Vrai si l'on veut avoir de la transparence, faux sinon.
     * @see Rectangle#setPlein
     */
    public void setTransparent ( boolean transparent ) {
	setPlein(!transparent);
    }

    /**
     * Permet de modifier la position du point A. La position du point B est également modifiée afin que la texture ne soit pas étirée.
     * @param aa Nouvelle position du coin bas gauche.
     * @see Point
     */
    public void setA(Point aa){
	/*int largeur = getLargeur();
	int hauteur = getHauteur();
	super.setB(new Point(aa.getX()+largeur,aa.getY()+hauteur));
	super.setA(aa);*/
	translater(aa.getX()-getA().getX(),aa.getY()-getA().getY());
    }

    /**
     * Permet de modifier la position du point B. La position du point A est également modifiée afin que la texture ne soit pas étirée.
     * @param bb Nouvelle position du coin haut droit.
     * @see Point
     */
    public void setB(Point bb){
	/*int largeur = getLargeur();
	int hauteur = getHauteur();
	super.setA(new Point(bb.getX()-largeur,bb.getY()-hauteur));
	super.setB(bb);*/
	translater(bb.getX()-getB().getX(),bb.getY()-getB().getY());
    }

    // Méthode //

    /**
     * Ajoute une primitive géométrique à la hitbox.
     * @param d une primitive géométrique, un objet de type Dessin.
     */
    public void ajouterALaHitbox(Dessin d){
	d.translater(getA().getX(),getA().getY());
	hitbox.add(d);
    }

    /**
     * Supprime l'ensemble des objets contenus dans la hitbox.
     */
    public void supprimeHitbox(){
	hitbox.clear();
    }

    /**
     * Translate une texture et la hitbox qui va avec.
     * @param dx translation en x
     * @param dy translation en y
     */
    public void translater(int dx, int dy){
	super.translater(dx,dy);
	for(Dessin d:hitbox)
	    d.translater(dx,dy);
    }

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher une Texture sur la zone d'affichage.
     * <br /><br />
     * On vérifie d'abord si l'image est transparente ou non, puis on utilise la méthode drawImage adéquate.
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {

	if ( getTransparent() )
	    g.drawImage ( img, this.getA().getX(), (int)g.getClipBounds().getHeight()-this.getA().getY()-getHauteur(), getLargeur(), getHauteur(), null );

	else
	    g.drawImage ( img, this.getA().getX(), (int)g.getClipBounds().getHeight()-this.getA().getY()-getHauteur(), getLargeur(), getHauteur(), getCouleur(), null );
    }

    /**
     * Méthode equals permettant de tester l'égalité entre une texture et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est une texture dont les caractéristiques sont les mêmes que la texture sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Texture) {
            // Vérification des valeurs des attributs
	    Texture other = (Texture) obj;
	    return super.equals(other) && img.equals(other.img);
	}
	return false;
    }

    /**
     * Teste l'intersection entre une primitive géométrique et la hitbox de la texture (si elle existe).
     * @param d une primitive géométrique
     * @return vrai s'il y a intersection entre la primitive géométrique passé en paramètre et la hitbox de la texture, faux sinon.
     */
    public boolean intersection(Dessin d){
	if(hitbox.size()==0)
	    return super.intersection(d);
	
	for(Dessin dh:hitbox)
	    if(dh.intersection(d))
		return true;
	return false;
    }

    /**
     * Teste l'intersection entre les hitbox de deux textures (si elles existent).
     * @param t une texture
     * @return vrai s'il y a intersection entre la hitbox des deux textures, faux sinon.
     */
    public boolean intersection(Texture t){
	if(hitbox.size()==0 && t.hitbox.size()==0)
	    return super.intersection(t);

	if(hitbox.size()==0 && t.hitbox.size()>0){
	    for(Dessin dh: t.hitbox)
		if(dh.intersection(this))
		    return true;
	}

	if(hitbox.size()>0 && t.hitbox.size()==0){
	    for(Dessin dh:hitbox)
		if(dh.intersection(t))
		    return true;
	}
	
	if(hitbox.size()>0 && t.hitbox.size()>0){
	    for(Dessin dh:hitbox)
		for(Dessin dh2:t.hitbox)
		    if(dh.intersection(dh2))
			return true;
	}
	
	return false;
    }
}
