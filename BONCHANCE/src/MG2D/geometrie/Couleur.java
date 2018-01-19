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

import java.awt.Color;

/**
 * Classe permettant de gérer les couleurs. Cette opération était laissée à la classe java.awt.Color mais dans un soucis de francisation du code, cette classe n'est qu'une passerelle vers java.awt.Color.<br />
 * Cette classe contient des constantes représentant les couleurs les plus utiliées.<br />
 * Vous pourrez toujours les éclaircir ou assombrir si elles en lui conviennent pas ou utiliser le constructeur prenant 3 entiers r, v et b en paramètre pour créer ses propres couleurs.
 * @author Rémi Synave
 * @version 2.2
 * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html" target="_blank">java.awt.Color</a>
 */

public class Couleur extends Color{

    /**
     * Constantes à utiliser pour le noir.
     */
    public static final Couleur NOIR = new Couleur (0,0,0);
    /**
     * Constantes à utiliser pour le blanc.
     */
    public static final Couleur BLANC = new Couleur(255,255,255);
    /**
     * Constantes à utiliser pour le rouge.
     */
    public static final Couleur ROUGE = new Couleur(255,0,0);
    /**
     * Constantes à utiliser pour le vert.
     */
    public static final Couleur VERT = new Couleur(0,255,0);
    /**
     * Constantes à utiliser pour le bleu.
     */
    public static final Couleur BLEU = new Couleur(0,0,255);
    /**
     * Constantes à utiliser pour le cyan.
     */
    public static final Couleur CYAN = new Couleur(0,255,255);
    /**
     * Constantes à utiliser pour le gris.
     */
    public static final Couleur GRIS = new Couleur(127,127,127);
    /**
     * Constantes à utiliser pour le gris foncé.
     */
    public static final Couleur GRIS_FONCE = new Couleur(96,96,96);
    /**
     * Constantes à utiliser pour le gris clair.
     */
    public static final Couleur GRIS_CLAIR = new Couleur(158,158,158);
    /**
     * Constantes à utiliser pour le magenta.
     */
    public static final Couleur MAGENTA = new Couleur(255,0,255);
    /**
     * Constantes à utiliser pour le orange.
     */
    public static final Couleur ORANGE = new Couleur(255,127,0);
    /**
     * Constantes à utiliser pour le rose.
     */
    public static final Couleur ROSE = new Couleur(249,66,158);
    /**
     * Constantes à utiliser pour le jaune.
     */
    public static final Couleur JAUNE = new Couleur(255,255,0);

    /**
     * Création de la couleur noire.
     */
    public Couleur(){
	super(0,0,0);
    }

    /**
     * Création d'une couleur personnalisée.<br />
     * Vous pouvez donner les valeurs de rouge, vert et bleu que vous souhaitez. Attention, les valeurs sont comprises entre 0 et 255.
     * @param r Valeur de rouge.
     * @param v Valeur de vert.
     * @param b Valeur de bleu.
     */
    public Couleur(int r, int v, int b){
	super(r,v,b);
    }

    /**
     * Crée une couleur sur le modèle d'une couleur.<br />
     * Remarque : Le paramètre peut être un objet de type java.awt.color.
     * @param c Couleur à copier.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html" target="_blank">java.awt.Color</a>
     */
    public Couleur(Color c){
	super(c.getRed(),c.getGreen(),c.getBlue());
    }

    /**
     * Création d'une nouvelle couleur qui assombrit la couleur courante.<br />
     * Cette méthode est une simple passerelle vers la méthode darker de la classe java.awt.color.
     * @return Nouvelle couleur plus sombre.
     */
    public Couleur assombrir(){
	return new Couleur(super.darker());
    }

    /**
     * Création d'une nouvelle couleur qui éclaircit la couleur courante.<br />
     * Cette méthode est une simple passerelle vers la méthode brighter de la classe java.awt.color.
     * @return Nouvelle couleur plus claire.
     */
    public Couleur eclaircir(){
	return new Couleur(super.brighter());
    }

    /**
     * Test de la couleur afin de savoir si elle est blanche.
     * @return Vrai si (r,v,b)=(255,255,255), faux sinon.
     */
    public boolean estBlanc(){
	return (equals(Couleur.BLANC));
    }

    /**
     * Test de la couleur afin de savoir si elle est noire.
     * @return Vrai si (r,v,b)=(0,0,0), faux sinon.
     */
    public boolean estNoir(){
	return (equals(Couleur.NOIR));
    }

    /**
     * Méthode toString retournant un description de la couleur.<br />
     * La chaine de caractères retournée est de la forme "(r,v,b) = (x,x,x)".
     * @return Une chaîne de caractères décrivant la couleur.
     */
    public String toString(){
	return new String("(r,v,b)=("+getRed()+","+getGreen()+","+getBlue()+")");
    }

    /**
     * Méthode equals permettant de tester l'égalité entre une couleur et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est une couleur dont les caractéristiques sont les mêmes que la couleur sur lequel la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Couleur) {
            // Vérification des valeurs des attributs
             Couleur other = (Couleur) obj;
	     return getRed()==other.getRed() && getGreen()==other.getGreen() && getBlue()==other.getBlue();
	}
	return false;
    }

}
