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
 * Cette classe permet la création de triangles.<br />
 * Un Triangle est défini par trois points.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Point
 */
public class Triangle extends Dessin {

    // Attributs //

    private Point a, b, c;

    private boolean plein = false;	// Détermine si le Triangle est plein ou non //

    // Constructeur //

    /**
     * Construit un triangle noir (0,0) - (1,0) - (0,1).
     */
    public Triangle(){
	super();
	a = new Point();
	b = new Point(1,0);
	c = new Point(0,1);
    }

    /**
     * Construit un triangle sur un modèle de triangle.
     * @param t Le triangle modèle
     */
    public Triangle( Triangle t ){
	super( t.getCouleur() );
	a = new Point ( t.a );
	b = new Point( t.b );
	c = new Point( t.c );
	plein=t.plein;
    }

    // Sans couleur //

    /**
     * Construit un triangle à partir de trois Points.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Premier point du triangle
     * @param b Second point du triangle
     * @param c Troisième point du triangle
     * @see Point
     */
    public Triangle ( Point a, Point b, Point c ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	this.c = new Point ( c );
    }

    /**
     * Construit un triangle à partir de trois Points.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Premier point du triangle
     * @param b Second point du triangle
     * @param c Troisième point du triangle
     * @param plein Défini si la forme doit être dessinée pleine ou non
     * @see Point
     */
    public Triangle ( Point a, Point b, Point c, boolean plein ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	this.c = new Point ( c );

	this.plein = plein;
    }

    // Avec couleur //

    /**
     * Construit un triangle à partir de trois Points.
     * @param couleur Couleur de l'objet
     * @param a Premier point du triangle
     * @param b Second point du triangle
     * @param c Troisième point du triangle
     * @see Couleur
     * @see Point
     */
    public Triangle ( Couleur couleur, Point a, Point b, Point c ) {

	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	this.c = new Point ( c );
    }

    /**
     * Construit un triangle à partir de trois Points.
     * @param couleur Couleur de l'objet
     * @param a Premier point du triangle
     * @param b Second point du triangle
     * @param c Troisième point du triangle
     * @param plein Défini si la forme doit être dessinée pleine ou non
     * @see Couleur
     * @see Point
     */
    public Triangle ( Couleur couleur, Point a, Point b, Point c, boolean plein ) {

	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	this.c = new Point ( c );

	this.plein = plein;
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne le premier point du triangle.
     * @return Premier point du triangle
     * @see Point
     */
    public Point getA () {

	return new Point(a);
    }

    /**
     * Retourne le second point du triangle.
     * @return Second point du triangle.
     * @see Point
     */
    public Point getB () {

	return new Point(b);
    }

    /**
     * Retourne le troisième point du triangle.
     * @return Troisième point du triangle.
     * @see Point
     */
    public Point getC () {

	return new Point(c);
    }

    /**
     * Retourne le booléen indiquant si le triangle est plein ou non.
     * @return plein Défini si la forme doit être dessinée pleine ou non
     */
    public boolean getPlein () {

	return plein;
    }

    /**
     * Implémentation de la méthode getBoiteEnglobante() de la classe abstraite Dessin.<br />
     * Elle retourne une boite englobante entourant le Triangle.
     * @return Boite englobante entourant le Triangle.
     */
    public BoiteEnglobante getBoiteEnglobante () {

	int x[] = { a.getX(), b.getX(), c.getX() };
	int y[] = { a.getY(), b.getY(), c.getY() };

	int minX = x[0], minY = y[0];
	int maxX = x[0], maxY = y[0];

	for ( int i = 1; i < x.length; i++ ) {

	    if ( x[i] < minX )
		minX = x[i];

	    if ( x[i] > maxX )
		maxX = x[i];

	    if ( y[i] < minY )
		minY = y[i];

	    if ( y[i] > maxY )
		maxY = y[i];
	}

	return new BoiteEnglobante ( new Point ( minX, minY ), new Point ( maxX, maxY ) );
    }

    /**
     * Implémentation de la méthode translater() de la classe abstraite Dessin.<br />
     * @param dx pas de translation suivant X
     * @param dy pas de translation suivant Y
     */
    public void translater ( int dx, int dy ){
	a.translater(dx,dy);
	b.translater(dx,dy);
	c.translater(dx,dy);
    }

    // Setter //

    /**
     * Permet d'attribuer un nouveau Point au Point a.
     * @param a Nouveau Point
     * @see Point
     */
    public void setA ( Point a ) {

	this.a = new Point(a);
    }

    /**
     * Permet d'attribuer un nouveau Point au Point b.
     * @param b Nouveau Point
     * @see Point
     */
    public void setB ( Point b ) {

	this.b = new Point(b);
    }

    /**
     * Permet d'attribuer un nouveau Point au Point c.
     * @param c Nouveau Point
     * @see Point
     */
    public void setC ( Point c ) {

	this.c = new Point(c);
    }

    /**
     * Permet de rendre le Triangle plein ou non.
     * @param plein Défini si la forme doit être dessinée pleine ou non
     */
    public void setPlein ( boolean plein ) {

	this.plein = plein;
    }

    // Méthodes //

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher un Triangle sur la zone d'affichage.
     * <br /><br />
     * Un Triangle étant un polygon, et la méthode drawPolygon n'acceptant que des tableaux d'entiers, nous avons créés deux tableaux x[] et y[] pour pouvoir afficher le Triangle.
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {

	int x[] = { a.getX(), b.getX(), c.getX() };
	int y[] = { (int)g.getClipBounds().getHeight()-a.getY(), (int)g.getClipBounds().getHeight()-b.getY(), (int)g.getClipBounds().getHeight()-c.getY() };

	g.setColor ( getCouleur() );

	if ( plein )
	    g.fillPolygon ( x, y, 3 );

	else
	    g.drawPolygon ( x, y, 3 );
    }

    // Intersections //

    // Triangle - Point //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Triangle - Ligne //

    /**
     * Méthode d'intersection précise entre un Triangle et une Ligne.<br />
     * Retourne vrai s'il y a collision.
     * @param l Ligne.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Ligne l ) {

	boolean collision = false;

	Ligne AB = new Ligne ( a, b );
	Ligne BC = new Ligne ( b, c );
	Ligne CA = new Ligne ( c, a );

	if ( l.intersection ( AB ) || l.intersection ( BC ) || l.intersection ( CA ) )
	    collision = true;

	return collision;
    }

    // Triangle - Rectangle //

    /**
     * Méthode d'intersection précise entre un Triangle et un Rectangle.<br />
     * Retourne vrai s'il y a collision.
     * @param r Rectangle.
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

	Ligne tAB = new Ligne ( a, b );
	Ligne tBC = new Ligne ( b, c );
	Ligne tCA = new Ligne ( c, a );

	if ( ( a_AxBy.intersection ( tAB ) || a_AxBy.intersection ( tBC ) || a_AxBy.intersection ( tCA ) ) ||
	     ( AxBy_b.intersection ( tAB ) || AxBy_b.intersection ( tBC ) || AxBy_b.intersection ( tCA ) ) ||
	     ( b_BxAy.intersection ( tAB ) || b_BxAy.intersection ( tBC ) || b_BxAy.intersection ( tCA ) ) ||
	     ( BxAy_a.intersection ( tAB ) || BxAy_a.intersection ( tBC ) || BxAy_a.intersection ( tCA ) ) ||
	     ( ( a.getX() > r.getA().getX() && a.getY() > r.getA().getY() ) &&
	       ( a.getX() < r.getB().getX() && a.getY() < r.getB().getY() ) &&
	       ( b.getX() > r.getA().getX() && b.getY() > r.getA().getY() ) &&
	       ( b.getX() < r.getB().getX() && b.getY() < r.getB().getY() ) &&
	       ( c.getX() > r.getA().getX() && c.getY() > r.getA().getY() ) &&
	       ( c.getX() < r.getB().getX() && c.getY() < r.getB().getY() )
	       )
	     )
	    collision = true;

	return collision;
    }

    // Triangle - Ovale //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Triangle - Cercle //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Triangle - Triangle //

    /**
     * Méthode d'intersection précise entre un Triangle et un Triangle.<br />
     * Retourne vrai s'il y a collision.
     * @param t Triangle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Triangle t ) {

	boolean collision = false;

	Ligne AB = new Ligne ( a, b );
	Ligne BC = new Ligne ( b, c );
	Ligne CA = new Ligne ( c, a );

	Ligne tAB = new Ligne ( t.getA(), t.getB() );
	Ligne tBC = new Ligne ( t.getB(), t.getC() );
	Ligne tCA = new Ligne ( t.getC(), t.getA() );

	if ( AB.intersection ( tAB ) || BC.intersection ( tBC ) || CA.intersection ( tCA ) )
	    collision = true;

	return collision;
    }

    /**
     * Méthode toString retournant un description du triangle.<br />
     * La chaine de caractères retournée est de la forme "(x1,y1) - (x2,y2) - (x3,y3)".
     * @return Une chaîne de caractères décrivant le triangle.
     */
    public String toString(){
	return new String(getA()+" - "+getB()+" - "+getC());
    }

    /**
     * Méthode equals permettant de tester l'égalité entre un triangle et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est un triangle dont les caractéristiques sont les mêmes que le triangle sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Triangle) {
            // Vérification des valeurs des attributs
             Triangle other = (Triangle) obj;
	     return super.equals(other) && a.equals(other.a) && b.equals(other.b) && c.equals(other.c) && plein==other.plein;
	}
	return false;
    }
}
