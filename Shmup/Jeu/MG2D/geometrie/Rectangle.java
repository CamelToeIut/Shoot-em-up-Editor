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
 * Cette classe permet la création de rectangles.<br />
 * Un rectangle est défini par deux points (le point A en bas à gauche et le point B en haut à droite), deux entiers permettant de régler les arrondis ainsi qu'un booléen pour le remplissage ou non de la forme géométrique.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Point
 */
public class Rectangle extends Dessin {

    // Attributs //

    private Point a;	// Point ( x min, y min ) //
    private Point b;	// Point ( x max, y max ) //

    private int arcLargeur = 0, arcHauteur = 0;

    private boolean plein = false;		// Détermine si le rectangle est plein ou non //

    // Constructeurs //

    /**
     * Construit un rectangle noir allant de (0,0) en (2,1).
     */
    public Rectangle(){
	super();
	a = new Point();
	b = new Point(2,1);
    }

    /**
     * Construit un rectangle à partir d'un modèle de rectangle.
     * @param r Le rectangle à copier.
     */
    public Rectangle ( Rectangle r ) {

	super ( r.getCouleur() );

	a = new Point ( r.getA().getX(), r.getA().getY() );
	b = new Point ( r.getB().getX(), r.getB().getY() );

	arcLargeur = r.getArcLargeur();
	arcHauteur = r.getArcHauteur();

	plein = r.getPlein();
    }

    // Sans couleur //

    /**
     * Construit un rectangle à partir de deux Points.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, Point b ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);
    }

    /**
     * Construit un Rectangle plein à partir de deux Points.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, Point b, boolean plein ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.plein = plein;
    }

    /**
     * Construit un Rectangle dont les bords sont arrondis à partir de deux Points et de deux entiers.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, Point b, int arcLargeur, int arcHauteur ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);
    }

    /**
     * Construit un Rectangle plein dont les bords sont arrondis à partir de deux Points et de deux entiers.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, Point b, int arcLargeur, int arcHauteur, boolean plein ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);

	this.plein = plein;
    }

    /**
     * Construit un Rectangle à partir d'un Point, de sa largeur et de sa hauteur.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param largeur Largeur du rectangle.
     * @param hauteur Hauteur du rectangle.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, int largeur, int hauteur ) {

	super ( Couleur.NOIR );

        this.a = new Point ( a );
        b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);
    }

    /**
     * Construit un Rectangle plein à partir d'un Point, de sa largeur et de sa hauteur.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param largeur Largeur du rectangle.
     * @param hauteur Hauteur du rectangle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, int largeur, int hauteur, boolean plein ) {

	super ( Couleur.NOIR );

        this.a = new Point ( a );
        b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

        this.plein = plein;
    }

    /**
     * Construit un Rectangle dont les bords sont arrondis à partir d'un Point, de sa largeur, de sa hauteur et des arrondis.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param largeur Largeur du rectangle.
     * @param hauteur Hauteur du rectangler.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);
    }

    /**
     * Construit un Rectangle plein dont les bords sont arrondis à partir d'un Point, de sa largeur, de sa hauteur et des arrondis.<br />
     * Il s'agit du constructeur sans couleur, ainsi l'objet sera noir.
     * @param a Coin bas gauche.
     * @param largeur Largeur du Rectangle.
     * @param hauteur Hauteur du Rectangle.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur, boolean plein ) {

	super ( Couleur.NOIR );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);

        this.plein = plein;
    }

    // Avec couleur //

    /**
     * Construit un rectangle à partir de deux Points.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, Point b )
    {
	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);
    }

    /**
     * Construit un rectangle à partir de deux Points.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, Point b, boolean plein ) {

	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.plein = plein;
    }

    /**
     * Construit un rectangle dont les bords sont arrondis à partir de deux Points et de deux entiers.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, Point b, int arcLargeur, int arcHauteur ) {

	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);
    }

    /**
     * Construit un rectangle plein dont les bords sont arrondis à partir de deux Points et de deux entiers.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param b Coin haut droit.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, Point b, int arcLargeur, int arcHauteur, boolean plein ) {

	super ( couleur );

	this.a = new Point ( a );
	this.b = new Point ( b );
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);

	this.plein = plein;

    }

    /**
     * Construit un rectangle à partir d'un Point, de sa largeur et de sa hauteur.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param largeur Largeur du Rectangle.
     * @param hauteur Hauteur du Rectangle.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, int largeur, int hauteur ) {

	super ( couleur );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);
    }

    /**
     * Construit un Rectangle plein à partir d'un Point, de sa largeur et de sa hauteur.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param largeur Largeur du Rectangle.
     * @param hauteur Hauteur du Rectangle.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, int largeur, int hauteur, boolean plein ) {

	super ( couleur );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

        this.plein = plein;
    }

    /**
     * Construit un Rectangle dont les bords sont arrondis à partir d'un Point, de sa largeur, de sa hauteur et de ses arrondis.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param largeur Largeur du Rectangle.
     * @param hauteur Hauteur du Rectangler.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur ) {

	super ( couleur );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);
    }

    /**
     * Construit un Rectangle plein dont les bords sont arrondis à partir d'un Point, de sa largeur, de sa hauteur et de ses arrondis.
     * @param couleur Couleur de l'objet.
     * @param a Coin bas gauche.
     * @param largeur Largeur du Rectangle.
     * @param hauteur Hauteur du Rectangle.
     * @param arcLargeur Arrondis en largeur.
     * @param arcHauteur Arrondis en hauteur.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Rectangle ( Couleur couleur, Point a, int largeur, int hauteur, int arcLargeur, int arcHauteur, boolean plein ) {

	super ( couleur );

	this.a = new Point ( a );
	b = new Point ( ( a.getX() + largeur ), ( a.getY() + hauteur ) );
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);

	this.arcLargeur = arcLargeur;
	this.arcHauteur = arcHauteur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);

        this.plein = plein;
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne le coin bas gauche du rectangle.
     * @return Coin bas gauche.
     */
    public Point getA () {

	return new Point(a);
    }

    /**
     * Retourne le coin haut droit du rectangle.
     * @return Coin haut droit.
     */
    public Point getB () {

	return new Point(b);
    }

    /**
     * Retourne la largeur du rectangle.
     * @return Largeur du rectangle.
     */
    public int getLargeur () {

	return ( b.getX() - a.getX() );
    }

    /**
     * Retourne la hauteur du rectangle.
     * @return Hauteur du rectangle.
     */
    public int getHauteur () {

	return ( b.getY() - a.getY() );
    }

    /**
     * Retourne la valeur de l'arrondi en largeur.
     * @return Arrondis en largeur.
     */
    public int getArcLargeur () {

	return arcLargeur;
    }

    /**
     * Retourne la valeur de l'arrondi en hauteur.
     * @return Arrondis en hauteur.
     */
    public int getArcHauteur () {

	return arcHauteur;
    }

    /**
     * Retoure le booléen indiquant si le rectangle est arrondi ou non.
     * @return Vrai si le rectangle est arrondi, faux sinon.
     */
    public boolean getArrondi () {

	return (arcLargeur>0 || arcHauteur>0);
    }

    /**
     * Retourne le booléen indiquant si le Rectangle est plein ou non.
     * @return Vrai si la forme est dessinée plein, faux sinon.
     */
    public boolean getPlein () {

	return plein;
    }

    /**
     * Implémentation de la méthode getBoiteEnglobante() de la classe abstraite Dessin.<br />
     * Elle retourne une BoiteEnglobante entourant le Rectangle.
     * @return Boite englobante entourant le Rectangle.
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
     * Permet d'attribuer un Point au coin bas gauche.
     * @param a Nouveau coin bas gauche.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setA ( Point a ) {

	this.a = new Point(a);
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);
    }

    /**
     * Permet d'attribuer un Point au coin haut droit.
     * @param b Nouveau coin haut droit.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setB ( Point b ) {

	this.b = new Point(b);
	if(a.getX()>b.getX() || a.getY()>b.getY())
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle dont le coin bas gauche se trouve au dessus ou à droite du coin haut droite. Coin bas gauche : "+a+". Coin haut droit : "+b);
    }

    /**
     * Permet d'attribuer une valeur à arcLargeur.
     * @param arcLargeur Valeur de l'arrondi en largeur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setArcLargeur ( int arcLargeur ) {

	this.arcLargeur = arcLargeur;
	if(arcLargeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en largeur de "+arcLargeur);
    }

    /**
     * Permet d'attribuer une valeur à arcHauteur
     * @param arcHauteur Valeur de l'arrondi en hauteur.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setArcHauteur ( int arcHauteur ) {

	this.arcHauteur = arcHauteur;
	if(arcHauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un arrondi en hauteur de "+arcHauteur);
    }

    /**
     * Permet d'indiquer si le rectangle doit être dessiné plein ou non.
     * @param plein Défini si la forme doit être dessinée pleine ou non.
     */
    public void setPlein ( boolean plein ) {

	this.plein = plein;
    }

    /**
     * Permet la modification de la hauteur du rectangle.<br />
     * Le coin bas gache ne bouge pas. C'est le coin aut droit qui est déplacé pour prendre en compte la nouvelle taille du rectangle.
     * @param hauteur Nouvelle hauteur du rectangle.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setHauteur(int hauteur){
	b = new Point ( getB().getX(), getA().getY() + hauteur ) ;
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);
    }

    /**
     * Permet la modification de la largeur du rectangle.<br />
     * Le coin bas gache ne bouge pas. C'est le coin aut droit qui est déplacé pour prendre en compte la nouvelle taille du rectangle.
     * @param largeur Nouvelle largeur du rectangle.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setLargeur(int largeur){
	b = new Point ( getA().getX() + largeur, getB().getY() ) ;
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
    }

    /**
     * Permet la modification de la taille du rectangle.<br />
     * Le coin bas gache ne bouge pas. C'est le coin aut droit qui est déplacé pour prendre en compte la nouvelle taille du rectangle.
     * @param largeur Nouvelle largeur du rectangle.
     * @param hauteur Nouvelle hauteur du rectangle.
     * @exception java.lang.RuntimeException Cette exception est lancée si :<ul><li>les arrondis ont des dimensions négatives</li><li>si on tente de construire un rectangle avec des dimensions négatives</li><li>si le coin bas gauche et au dessus ou à droite du coin haut droit.</li></ul>.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public void setTaille(int largeur, int hauteur){
	b = new Point ( getA().getX() + largeur, getA().getY() + hauteur ) ;
	if(largeur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de largeur négative : "+largeur);
	if(hauteur<0)
	    throw new java.lang.RuntimeException("Impossible de créer un rectangle de hauteur négative : "+hauteur);
    }

    // Méthodes //

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher le rectangle dans la zone d'affichage.
     * <br /><br />
     * On récupère d'abord la couleur de l'objet afin de le dessiner dans la bonne couleur. Ensuite, on vérifie si l'objet est arrondis et plein, arrondis ou plein pour appeler les méthodes adéquates. Sinon on utilise l'affichage de base avec drawRect().
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {

	g.setColor ( getCouleur() );

	if ( getArrondi() && plein )
	    g.fillRoundRect ( a.getX(), (int)g.getClipBounds().getHeight()-a.getY()-getHauteur(), getLargeur(), getHauteur(), arcLargeur, arcHauteur );

	else if ( getArrondi() )
	    g.drawRoundRect ( a.getX(), (int)g.getClipBounds().getHeight()-a.getY()-getHauteur(), getLargeur(), getHauteur(), arcLargeur, arcHauteur );

	else if ( plein )
	    g.fillRect ( a.getX(), (int)g.getClipBounds().getHeight()-a.getY()-getHauteur(), getLargeur(), getHauteur() );

	else
	    g.drawRect ( a.getX(), (int)g.getClipBounds().getHeight()-a.getY()-getHauteur(), getLargeur(), getHauteur() );
    }

    // Intersections //

    // Rectangle - Point : BoiteEnglobante //

    // Rectangle - Ligne //

    /**
     * Méthode d'intersection précise entre un Rectangle et une Ligne.<br />
     * Retourne vrai s'il y a collision.
     * @param l Ligne.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Ligne l ) {

	boolean collision = false;

	Point AxBy = new Point ( a.getX(), b.getY() );
	Point BxAy = new Point ( b.getX(), a.getY() );

	Ligne a_AxBy = new Ligne ( a, AxBy );
	Ligne AxBy_b = new Ligne ( AxBy, b );
	Ligne b_BxAy = new Ligne ( b, BxAy );
	Ligne BxAy_a = new Ligne ( BxAy, a );

	if ( l.intersection ( a_AxBy ) ||
	     l.intersection ( AxBy_b ) ||
	     l.intersection ( b_BxAy ) ||
	     l.intersection ( BxAy_a ) ||
	     ( l.getA().getX() > a.getX() &&
	       l.getA().getY() > a.getY() &&
	       l.getB().getX() < b.getX() &&
	       l.getB().getY() < b.getY()
	       )
	     )
	    collision = true;

	return collision;
    }

    // Rectangle - Rectangle : Boite Englobante //

    // Rectangle - Ovale //

    // TODO : travail à faire, si l'envie vous prend :) //

    // Rectangle - Cercle //

    /**
     * Méthode d'intersection précise entre un Rectangle et un Cercle.<br />
     * Retourne vrai s'il y a collision.
     * @param c Cercle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Cercle c ) {

	boolean collision = false;

	int x = c.getO().getX();
	int y = c.getO().getY();

	if ( x < a.getX() )
	    x = a.getX();

	if ( x > ( a.getX() + getLargeur() ) )
	    x = ( a.getX() + getLargeur() );

	if ( y <  a.getY() )
	    y = a.getY();

	if ( y > ( a.getY() + getHauteur() ) )
	    y = ( a.getY() + getHauteur() );

	if ( ( c.getO().getX() - x ) * ( c.getO().getX() - x ) + ( c.getO().getY() - y ) * ( c.getO().getY() - y ) < c.getRayon() * c.getRayon() )
	    collision = true;

	return collision;
    }

    // Rectangle - Triangle //

    /**
     * Méthode d'intersection précise entre un Rectangle et un Triangle.<br />
     * Retourne vrai s'il y a collision.
     * @param t Triangle.
     * @return boolean Résultat du test.
     */
    public boolean intersection ( Triangle t ) {

	boolean collision = false;

	Point AxBy = new Point ( a.getX(), b.getY() );
	Point BxAy = new Point ( b.getX(), a.getY() );

	Ligne a_AxBy = new Ligne ( a, AxBy );
	Ligne AxBy_b = new Ligne ( AxBy, b );
	Ligne b_BxAy = new Ligne ( b, BxAy );
	Ligne BxAy_a = new Ligne ( BxAy, a );

	Ligne tAB = new Ligne ( t.getA(), t.getB() );
	Ligne tBC = new Ligne ( t.getB(), t.getC() );
	Ligne tCA = new Ligne ( t.getC(), t.getA() );

	if ( ( a_AxBy.intersection ( tAB ) || a_AxBy.intersection ( tBC ) || a_AxBy.intersection ( tCA ) ) ||
	     ( AxBy_b.intersection ( tAB ) || AxBy_b.intersection ( tBC ) || AxBy_b.intersection ( tCA ) ) ||
	     ( b_BxAy.intersection ( tAB ) || b_BxAy.intersection ( tBC ) || b_BxAy.intersection ( tCA ) ) ||
	     ( BxAy_a.intersection ( tAB ) || BxAy_a.intersection ( tBC ) || BxAy_a.intersection ( tCA ) ) ||
	     ( ( t.getA().getX() > a.getX() && t.getA().getY() > a.getY() ) &&
	       ( t.getA().getX() < b.getX() && t.getA().getY() < b.getY() ) &&
	       ( t.getB().getX() > a.getX() && t.getB().getY() > a.getY() ) &&
	       ( t.getB().getX() < b.getX() && t.getB().getY() < b.getY() ) &&
	       ( t.getC().getX() > a.getX() && t.getC().getY() > a.getY() ) &&
	       ( t.getC().getX() < b.getX() && t.getC().getY() < b.getY() )
	       )
	     )
	    collision = true;

	return collision;
    }

    /**
     * Méthode toString retournant un description du rectangle.<br />
     * La chaine de caractères retournée est de la forme "Rectangle de (x1,y1) en (x2,y2)".
     * @return Une chaîne de caractères décrivant le rectangle.
     */
    public String toString(){
	String classe=getClass().toString();
	String classeSplit[]=classe.split(" ");
	int index=classeSplit[1].lastIndexOf('.');
	return new String(classeSplit[1].substring(index+1,classeSplit[1].length())+" de "+getA()+" en "+getB());
    }

    /**
     * Méthode equals permettant de tester l'égalité entre un rectangle et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est un rectangle dont les caractéristiques sont les mêmes que le rectangle sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Rectangle) {
            // Vérification des valeurs des attributs
	    Rectangle other = (Rectangle) obj;
	    return super.equals(other) && a.equals(other.a) && b.equals(other.b) && arcLargeur==other.arcLargeur && arcHauteur==other.arcHauteur && plein==other.plein;
	}
	return false;
    }
}
