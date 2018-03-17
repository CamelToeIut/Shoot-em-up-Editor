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
 * Cette classe permet la création de points.<br />
 * Un Point est défini par deux entiers (abscisse et ordonnée).
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 */
public class Point extends Dessin {

    // Attributs //

    private int x;	// Abscisse //
    private int y;	// Ordonnée //

    // Constructeurs //

    /**
     * Construit un point noir en (0,0).
     */
    public Point(){
	super();
	x=0;
	y=0;
    }

    /**
     * Construit un Point à partir d'un modèle de point.<br />
     * @param p Le point à copier.
     */
    public Point ( Point p ) {

	super ( p.getCouleur() );

	x = p.getX();
	y = p.getY();
    }

    // Sans couleur //

    /**
     * Construit un Point à partir de deux entiers.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param x Abscisse.
     * @param y Ordonnée.
     */
    public Point ( int x, int y ) {

	super ( Couleur.NOIR );

        this.x = x;
        this.y = y;
    }

    // Avec couleur //

    /**
     * Construit un Point à partir de deux entiers.
     * @param couleur Couleur de l'objet.
     * @param x Abscisse.
     * @param y Ordonnée.
     * @see Couleur
     */
    public Point ( Couleur couleur, int x, int y ) {

	super ( couleur );

        this.x = x;
        this.y = y;
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne la valeur de x.
     * @return Abscisse du point.
     */
    public int getX () {

	return x;
    }

    /**
     * Retourne la valeur de y.
     * @return Ordonnée du point.
     */
    public int getY () {

	return y;
    }

    /**
     * Implémentation de la méthode getBoiteEnglobante() de la classe abstraite Dessin.<br />
     * Elle retourne une BoiteEnglobante qui est le Point lui-même.
     * @return BoiteEnglobante BoiteEnglobante qui est le Point lui-même.
     */
    public BoiteEnglobante getBoiteEnglobante () {

	return new BoiteEnglobante ( new Point ( x, y ),
				     new Point ( x, y ) );
    }

    /**
     * Implémentation de la méthode translater() de la classe abstraite Dessin.<br />
     * @param dx pas de translation suivant X
     * @param dy pas de translation suivant Y
     */
    public void translater ( int dx, int dy ){
	this.x=this.x+dx;
	this.y=this.y+dy;
    }

    // Setter //

    /**
     * Permet d'attribuer une nouvelle valeur à x.
     * @param x Nouvelle abscisse.
     */
    public void setX ( int x ) {

	this.x = x;
    }

    /**
     * Permet d'attribuer une nouvelle valeur à y.
     * @param y Nouvelle ordonnée.
     */
    public void setY ( int y ) {

	this.y = y;
    }

    // Méthodes //

    /**
     * Calcul de la longueur entre deux points.
     * @param p le second point.
     * @return la longueur entre les deux points
     */
    public double longueur(Point p){
	return Math.sqrt((getX()-p.getX())*(getX()-p.getX())+(getY()-p.getY())*(getY()-p.getY()));
    }

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher un Point sur la zone d'affichage.
     * <br /><br />
     * On récupère d'abord la couleur de l'objet afin de le dessiner dans la bonne couleur. Puis on utilise l'affichage avec drawLine().
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {

	g.setColor ( getCouleur() );

	g.drawLine ( x, (int)g.getClipBounds().getHeight()-y, x, (int)g.getClipBounds().getHeight()-y );
    }

    // Intersections //

    // Point - Point //

    /**
     * Méthode d'intersection précise entre un Point et un Point.<br />
     * Retourne vrai s'il y a collision.
     * @param p Point.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Point p ) {

	return (x == p.getX() && y == p.getY() );

    }

    // Point - Ligne //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Point - Rectangle : BoiteEnglobante //

    // Point - Ovale //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Point - Cercle //

    /**
     * Méthode d'intersection précise entre un Point et un Cercle.<br />
     * Retourne vrai s'il y a collision.
     * @param c Cercle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Cercle c ) {

	boolean collision = false;

	int dx = x - c.getO().getX();
	int dy = y - c.getO().getY();

	if ( ( dx * dx ) + ( dy * dy ) < ( c.getRayon() * c.getRayon() ) )
	    collision = true;

	return collision;
    }

    // Point - Triangle //

    // TODO : travail à faire, si l'envie vous prend :) //


    /**
     * Méthode toString retournant un description du point.<br />
     * La chaine de caractères retournée est de la forme "(x,y)".
     * @return Une chaîne de caractères décrivant le point.
     */
    public String toString(){
	return new String("("+x+","+y+")");
    }

    /**
     * Méthode equals permettant de tester l'égalité entre un point et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est un point dont les caractéristiques sont les mêmes que le point sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Point) {
            // Vérification des valeurs des attributs
	    Point other = (Point) obj;
	    return super.equals(other) && x==other.x && y==other.y;
	}
	return false;
    }
}
