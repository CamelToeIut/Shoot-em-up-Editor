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


import java.awt.Graphics;

/**
 * Cette classe permet la création de cercles.<br />
 * Cette classe est un cas particulier de la classe Ovale. Un ovale est décrit grâce à un point central, une largeur et une hauteur. Le cercle est décrit grâce à un point central et un diamètre (largeur=hauteur).<br />
 * <br /><br />
 * Elle hérite de la classe Ovale afin que toutes les méthodes de la classe Ovale s'applique également à celle-ci.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 */
public class Cercle extends Ovale {

    // Attribut //

    // Constructeur //

    /**
     * Construit un cercle noir centré en (1,1) et de rayon 1.
     */
    public Cercle(){
	super(new Point(1,1),2,2);
    }

    /**
     * Construit un cercle à partir d'un modèle de cercle.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param c Cercle.
     */
    public Cercle ( Cercle c ) {

	super ( c.getCouleur(),
		c.getO(),
		c.getDiametre(),
		c.getDiametre(),
		c.getPlein()
		);
    }

    // Sans couleur //

    /**
     * Construit un Cercle à partir d'un Point et d'un rayon.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param o Point correspondant au centre du Cercle.
     * @param rayon int correspondant au rayon du Cercle.
     * @see Point
     */
    public Cercle ( Point o, int rayon ) {

	super ( o, rayon*2, rayon*2 );
    }

    /**
     * Construit un Cercle à partir d'un Point et d'un rayon.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param o Point correspondant au centre du Cercle.
     * @param rayon int correspondant au rayon du Cercle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @see Point
     */
    public Cercle ( Point o, int rayon, boolean plein ) {

	super ( o, rayon*2, rayon*2, plein );

    }

    /**
     * Construit un cercle à partir d'un carré.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * <br /><br />
     * Le centre du cercle sera obtenue via les coordonnées du Point min du Carre auxquelles on ajoute la moitié de la dimension des côtés.
     * @param c Carre qui défini le Cercle.
     * @see Carre
     */
    public Cercle ( Carre c ) {

	super ( new Point ( ( c.getA().getX() + c.getTaille() / 2 ), ( c.getA().getY() + c.getTaille() / 2 ) ),
		c.getTaille(),
		c.getTaille()
		);
    }

    /**
     * Construit un cercle à partir d'un carré.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * <br /><br />
     * Le centre du cercle sera obtenue via les coordonnées du Point min du Carre auxquelles on ajoute la moitié de la dimension des côtés.
     * @param c Carre qui défini le Cercle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @see Carre
     */
    public Cercle ( Carre c, boolean plein ) {

	super ( new Point ( ( c.getA().getX() + c.getTaille() / 2 ), ( c.getA().getY() + c.getTaille() / 2 ) ),
		c.getTaille(),
		c.getTaille(),
		plein
		);
    }

    // Avec couleur //

    /**
     * Construit un cercle à partir d'un Point, d'un rayon et d'une couleur.<br />
     * @param couleur Couleur de l'objet.
     * @param o Point correspondant au centre du Cercle.
     * @param rayon Rayon du Cercle.
     * @see Couleur
     * @see Point
     */
    public Cercle ( Couleur couleur, Point o, int rayon ) {

	super ( couleur, o, rayon*2, rayon*2 );
    }

    /**
     * Construit un cercle à partir d'un Point, d'un rayon et d'une couleur.<br />
     * @param couleur Couleur de l'objet.
     * @param o Point correspondant au centre du Cercle.
     * @param rayon Rayon du Cercle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @see Couleur
     * @see Point
     */
    public Cercle ( Couleur couleur, Point o, int rayon, boolean plein ) {

	super ( couleur, o, rayon*2, rayon*2, plein );
    }

    /**
     * Construit un cercle à partir d'un carré et d'une couleur.<br />
     * Le centre du cercle sera obtenu via les coordonnées du Point min du Carre auxquelles on ajoute la moitié de la dimension des côtés.
     * @param couleur Couleur de l'objet.
     * @param c Carré qui défini le cercle.
     * @see Couleur
     * @see Carre
     */
    public Cercle ( Couleur couleur, Carre c ) {

	super ( couleur,
		new Point ( ( c.getA().getX() + c.getTaille()/2 ), ( c.getA().getY() + c.getTaille()/2 ) ),
		c.getTaille(),
		c.getTaille()
		);
    }

    /**
     * Construit un cercle à partir d'un carré et d'une couleur.<br />
     * Le centre du cercle sera obtenu via les coordonnées du Point min du Carre auxquelles on ajoute la moitié de la dimension des côtés.
     * @param couleur Couleur de l'objet.
     * @param c Carre qui défini le Cercle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @see Couleur
     * @see Carre
     */
    public Cercle ( Couleur couleur, Carre c, boolean plein ) {

	super ( couleur,
		new Point ( ( c.getA().getX() + c.getTaille()/2 ), ( c.getA().getY() + c.getTaille()/2 ) ),
		c.getTaille(),
		c.getTaille(),
		plein
		);
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne la valeur du rayon.
     * @return Rayon du cercle.
     */
    public int getRayon () {

	return getLargeur()/2;
    }

    /**
     * Retourne la valeur du diamètre.
     * @return Diamètre du cercle.
     */
    public int getDiametre () {

	return getLargeur();
    }

    // Setter //

    /**
     * Permet d'attribuer une nouvelle valeur au rayon.<br />
     * Le centre du cercle ne bouge pas.
     * @param rayon Nouveau rayon du cercle.
     */
    public void setRayon ( int rayon ) {

	setLargeur(rayon*2);
    }

    /**
     * Permet d'attribuer une nouvelle valeur au diametre.<br />
     * Le centre du cercle ne bouge pas.
     * @param diametre Nouveau diamètre du cercle.
     */
    public void setDiametre ( int diametre ) {

	setLargeur(diametre);
    }

    /**
     * <strong>Redéfinition !</strong> Permet d'attribuer une nouvelle valeur au diamètre.
     * @param diametre Nouveau diamètre du cercle.
     */
    public void setLargeur ( int diametre ) {

	super.setLargeur(diametre);
	super.setHauteur(diametre);
    }

    /**
     * <strong>Redéfinition !</strong> Permet d'attribuer une nouvelle valeur au diamètre.
     * @param diametre Nouveau diamètre du Cercle.
     */
    public void setHauteur ( int diametre ) {

	super.setLargeur(diametre);
	super.setHauteur(diametre);
    }

    // Méthodes //


    // Intersections //

    // Cercle - Point //

    /**
     * Méthode d'intersection précise entre un Cercle et un Point.<br />
     * Retourne vrai s'il y a collision.
     * @param p Point.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Point p ) {

	boolean collision = false;

	int dx = p.getX() - this.getO().getX();
	int dy = p.getY() - this.getO().getY();

	if ( ( dx * dx ) + ( dy * dy ) < ( getRayon() * getRayon() ) )
	    collision = true;

	return collision;
    }

    // Cercle - Ligne //

    /**
     * Méthode d'intersection précise entre un Cercle et une Ligne.<br />
     * Retourne vrai s'il y a collision.
     * @param l Ligne.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Ligne l ) {

	boolean collision = false;

	int ux = l.getB().getX() - l.getA().getX();
	int uy = l.getB().getY() - l.getA().getY();
	int acx = this.getO().getX() - l.getA().getX();
	int acy = this.getO().getY() - l.getA().getY();

	int numerateur = ux * acy - uy * acx;

	if ( numerateur < 0 )
	    numerateur = -numerateur;

	int denominateur = ( int ) ( Math.sqrt ( ux * ux + uy * uy ) );
	int ci = numerateur / denominateur;

	if ( ci < getRayon() )
	    collision = true;

	return collision;
    }

    // Cercle - Rectangle //

    /**
     * Méthode d'intersection précise entre un Cercle et un Rectangle.<br />
     * Retourne vrai s'il y a collision.
     * @param r Rectangle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Rectangle r ) {

	boolean collision = false;

	int x = this.getO().getX();
	int y = this.getO().getY();

	if ( x < r.getA().getX() )
	    x = r.getA().getX();

	if ( x > ( r.getA().getX() + r.getLargeur() ) )
	    x = ( r.getA().getX() + r.getLargeur() );

	if ( y <  r.getA().getY() )
	    y = r.getA().getY();

	if ( y > ( r.getA().getY() + r.getHauteur() ) )
	    y = ( r.getA().getY() + r.getHauteur() );

	if ( ( this.getO().getX() - x ) * ( this.getO().getX() - x ) + ( this.getO().getY() - y ) * ( this.getO().getY() - y ) < getRayon() * getRayon() )
	    collision = true;

	return collision;
    }

    // Cercle - Ovale //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Cercle - Cercle //

    /**
     * Méthode d'intersection précise entre un Cercle et un Cercle.<br />
     * Retourne vrai s'il y a collision.
     * @param c Cercle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Cercle c ) {

	boolean collision = false;

	int dx = c.getO().getX() - this.getO().getX();
	int dy = c.getO().getY() - this.getO().getY();
	int ra = c.getRayon() + getRayon();

	if ( ( dx * dx ) + ( dy * dy ) <= ( ra * ra ) )
	    collision = true;

	return collision;
    }

    // Cercle - Triangle //

    // TODO : travail à faire, si l'envie vous prend :) //

    /**
     * Méthode toString retournant une descirption du cercle.<br />
     * La chaine de caractères retournée est de la forme "Cercle de centre (x1,y1) et de rayon r".
     * @return Une chaîne de caractères décrivant le cercle.
     */
    public String toString(){
	return new String("Cercle de centre "+getO()+" et de rayon "+getRayon());
    }

    /**
     * Méthode equals permettant de tester l'égalité entre un cercle et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est un cercle dont les caractéristiques sont les mêmes que le cercle sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Cercle) {
            // Vérification des valeurs des attributs
             Cercle other = (Cercle) obj;
	     return super.equals(other) && getRayon()==other.getRayon();
	}
	return false;
    }
}
