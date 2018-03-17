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

/**
 * Cette classe permet de créer des boites englobantes. Les boites englobantes sont utilisées par le moteur graphique pour gérer les collisions via le principe des AABB (Axis Aligned Bounding Box).
 * <br /><br />
 * Le principe est simple : on entoure notre forme par un rectangle dont les côtés sont alignés avec les axes x et y.<br />
 * L'objectif est de permettre de fournir une méthode de collision pour toutes les formes géréées par le moteur car les collisions rectangle - rectangle sont très facile à détecter.<br /><br />
 * Chaque forme géométrique héritant de Dessin possède une méthode getBoiteEnglobante.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Dessin
 * @see Dessin#getBoiteEnglobante
 */
public class BoiteEnglobante extends Rectangle {

    // Constructeur //

    /**
     * Construit une boite englobante sur le modèle du rectangle par défaut.
     */
    public BoiteEnglobante(){
	super();
    }

    /**
     * Construit une BoiteEnglobante à partir de deux points.<br />
     * @param a Coin bas gauche de la boite englobante.
     * @param b Coin haut droit de la boite englobante.
     * @see Point
     */
    public BoiteEnglobante ( Point a, Point b ) {

	super ( a, b );
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne le Point correspondant au coin bas gauche.
     * @return Coin bas gauche de la boite englobante.
     * @see Point
     */
    public Point getMin () {

	return getA();
    }

    /**
     * Retourne le Point correspondant au coin haut droit.
     * @return Coin haut deroit de la boite englobante.
     * @see Point
     */
    public Point getMax () {

	return getB();
    }

    // Setter //

    /**
     * Permet d'attribuer un point au coin bas gauche de la boite englobante.
     * @param min Point correspondant au coin bas gauche.
     * @see Point
     */
    public void setMin ( Point min ) {

	setA ( min );
    }

    /**
     * Permet d'attribuer un point au coin haut droit de la boite englobante.
     * @param max Point correspondant au coin haut droit.
     * @see Point
     */
    public void setMax ( Point max ) {

	setB ( max );
    }

    // Méthode //

    /**
     * Méthode de détection des collisions entre deux boites englobantes.
     * <br /><br />
     * Le calcul est assez simple :<br />
     * si les coordonnées x et y du point max de l'objet A sont inférieur aux coordonnées x et y du point min de l'objet B
     * ou si les coordonnées x et y du point min de l'objet A sont supérieur aux coordonnées x et y du point max de l'objet B
     * alors il n'y a pas collision.
     * @param b BoiteEnglobante.
     * @return collision Booléen déterminant s'il y a collision ou non.
     */
    public boolean intersection ( BoiteEnglobante b ) {

	boolean collision = true;

	if ( b.getMin().getX() > this.getMax().getX() ||
	     b.getMin().getY() > this.getMax().getY() ||
	     b.getMax().getX() < this.getMin().getX() ||
	     b.getMax().getY() < this.getMin().getY()
	     )
	    collision = false;

	return collision;
    }
}
