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
 * Cette classe permet la création de lignes.<br />
 * Une ligne est définie par deux Points.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Point
 */
public class Ligne extends Dessin {

    // Attributs //

    private Point a, b;

    // Constructeurs //

    /**
     * Construit une ligne noir allant de (0,0) à (1,0).
     */
    public Ligne(){
	super();
	a = new Point();
	b = new Point(1,0);
    }

    /**
     * Construit une Ligne à partir d'un modèle de ligne.<br />
     * @param l La ligne à copier.
     */
    public Ligne ( Ligne l ) {

	super ( l.getCouleur() );

	a = new Point ( l.getA() );
	b = new Point ( l.getB() );
    }

    // Sans couleur //

    /**
     * Construit une Ligne à partir de deux Points.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Premier point.
     * @param b Second point.
     * @see Point
     */
    public Ligne ( Point a, Point b ) {

	super ( Couleur.NOIR );

        this.a = new Point ( a );
        this.b = new Point ( b );
    }

    // Avec couleur //

    /**
     * Construit une Ligne à partir de deux Points.
     * @param couleur Couleur de l'objet.
     * @param a Premier point.
     * @param b Second point.
     * @see Couleur
     * @see Point
     */
    public Ligne ( Couleur couleur, Point a, Point b ) {

	super ( couleur );

        this.a = new Point ( a );
        this.b = new Point ( b );
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne le premier point de la Ligne.
     * @return Premier point.
     */
    public Point getA () {

	return new Point(a);
    }

    /**
     * Retourne le second point de la Ligne.
     * @return b Second point.
     */
    public Point getB () {

	return new Point(b);
    }

    /**
     * Implémentation de la méthode getBoiteEnglobante() de la classe abstraite Dessin.<br />
     * Elle retourne une boite englobante entourant la Ligne.
     * @return Boite englobante entourant la Ligne.
     * @see BoiteEnglobante
     */
    public BoiteEnglobante getBoiteEnglobante () {

	return new BoiteEnglobante ( a, b );
    }

    /**
     * Implémentation de la méthode translater() de la classe abstraite Dessin.<br />
     * @param dx pas de translation suivant X
     * @param dy pas de translation suivant Y
     */
    public void translater ( int dx, int dy ){
	a.translater(dx,dy);
	b.translater(dx,dy);
    }

    // Setter //

    /**
     * Permet d'attribuer un Nouveau point au Point a.
     * @param a Nouveau point.
     * @see Point
     */
    public void setA ( Point a ) {

	this.a = new Point(a);
    }

    /**
     * Permet d'attribuer un Nouveau point au Point b.
     * @param b Nouveau Point.
     * @see Point
     */
    public void setB ( Point b ) {

	this.b = new Point(b);
    }

    // Méthodes //

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher une Ligne sur la zone d'affichage.
     * <br /><br />
     * On récupère d'abord la couleur de l'objet afin de le dessiner dans la bonne couleur. Puis on utilise l'affichage avec drawLine().
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {

	g.setColor ( getCouleur() );

	g.drawLine ( a.getX(), (int)g.getClipBounds().getHeight()-a.getY(), b.getX(), (int)g.getClipBounds().getHeight()-b.getY() );
    }

    // Intersections //

    // Ligne - Point //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Ligne - Ligne //

    /**
     * Méthode d'intersection précise entre une Ligne et une Ligne.<br />
     * Retourne vrai s'il y a collision.
     * @param l Ligne.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Ligne l ) {

	boolean collision = true;

	int Ax = a.getX();
	int Ay = a.getY();
	int Bx = b.getX();
	int By = b.getY();

	int Cx = l.getA().getX();
	int Cy = l.getA().getY();
	int Dx = l.getB().getX();
	int Dy = l.getB().getY();

	double Sx = 0, Sy = 0;

	if ( Ax == Bx ) {

	    if ( Cx == Dx )
		collision = false;

	    else {

		double pCD = ( Cy - Dy ) / ( Cx - Dx );

		Sx = Ax;
		Sy = pCD * ( Ax - Cx ) + Cy;
	    }
	}

	else {

	    if ( Cx == Dx ) {

		double pAB = ( Ay - By ) / ( Ax - Bx );

		Sx = Cx;
		Sy = pAB * ( Cx - Ax ) + Ay;
	    }

	    else {

		double pCD = ( Cy - Dy ) / ( Cx - Dx );
		double pAB = ( Ay - By ) / ( Ax - Bx );

		double oCD = Cy - pCD * Cx;
		double oAB = Ay - pAB * Ax;

		Sx = ( oAB - oCD ) / ( pCD - pAB );
		Sy = pCD * Sx + oCD;
	    }
	}

	if ( ( Sx < Ax && Sx < Bx ) || ( Sy < Ay && Sy < By ) ||
	     ( Sx > Ax && Sx > Bx ) || ( Sy > Ay && Sy > By ) ||
	     ( Sx < Cx && Sx < Dx ) || ( Sy < Cy && Sy < Dy ) ||
	     ( Sx > Cx && Sx > Dx ) || ( Sy > Cy && Sy > Dy )
	     )
	    collision = false;

	return collision;
    }

    // Ligne - Rectangle //

    /**
     * Méthode d'intersection précise entre une Ligne et un Rectangle.<br />
     * Retourne vrai s'il y a collision.
     * @param r Rectangle..
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Rectangle r ) {

	boolean collision = false;

	Point AxBy = new Point ( r.getA().getX(), r.getB().getY() );
	Point BxAy = new Point ( r.getB().getX(), r.getA().getY() );

	Ligne a_AxBy = new Ligne ( r.getA(), AxBy );
	Ligne AxBy_b = new Ligne ( AxBy, r.getB() );
	Ligne b_BxAy = new Ligne ( r.getB(), BxAy );
	Ligne BxAy_a = new Ligne ( BxAy, r.getA() );

	if ( this.intersection ( a_AxBy ) ||
	     this.intersection ( AxBy_b ) ||
	     this.intersection ( b_BxAy ) ||
	     this.intersection ( BxAy_a ) ||
	     ( a.getX() > r.getA().getX() &&
	       a.getY() > r.getA().getY() &&
	       b.getX() < r.getB().getX() &&
	       b.getY() < r.getB().getY()
	       )
	     )
	    collision = true;

	return collision;
    }

    // Ligne - Ovale //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Ligne - Cercle //

    /**
     * Méthode d'intersection précise entre une Ligne et un Cercle.<br />
     * Retourne vrai s'il y a collision.
     * @param c Cercle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Cercle c ) {

	boolean collision = false;

	int ux = b.getX() - a.getX();
	int uy = b.getY() - a.getY();
	int acx = c.getO().getX() - a.getX();
	int acy = c.getO().getY() - a.getY();

	int numerateur = ux * acy - uy * acx;

	if ( numerateur < 0 )
	    numerateur = -numerateur;

	int denominateur = ( int ) Math.sqrt ( ux * ux + uy * uy );
	int ci = numerateur / denominateur;

	if ( ci < c.getRayon() )
	    collision = true;

	return collision;
    }

    // Ligne - Triangle //

    /**
     * Méthode d'intersection précise entre une Ligne et un Triangle.<br />
     * Retourne vrai s'il y a collision.
     * @param t Triangle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Triangle t ) {

	boolean collision = false;

	Ligne AB = new Ligne ( t.getA(), t.getB() );
	Ligne BC = new Ligne ( t.getB(), t.getC() );
	Ligne CA = new Ligne ( t.getC(), t.getA() );

	if ( this.intersection ( AB ) ||
	     this.intersection ( BC ) ||
	     this.intersection ( CA )
	     )
	    collision = true;

	return collision;
    }

    /**
     * Méthode toString retournant un description de la ligne.<br />
     * La chaine de caractères retournée est de la forme "Ligne de (x1,y1) à (x2,y2)".
     * @return Une chaîne de caractères décrivant la ligne.
     */
    public String toString(){
	return new String("Ligne de "+getA()+" à "+getB());
    }

    /**
     * Méthode equals permettant de tester l'égalité entre une ligne et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est une ligne dont les caractéristiques sont les mêmes que la ligne sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Ligne) {
            // Vérification des valeurs des attributs
             Ligne other = (Ligne) obj;
	     return super.equals(other) && a.equals(other.a) && b.equals(other.b);
	}
	return false;
    }
}
