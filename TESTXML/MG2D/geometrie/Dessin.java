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
 * Classe abstraite destinée à représenter une forme géométrique.
 * <br /><br />
 * Elle contient le minimum commun à toutes les formes qu'il sera possible d'afficher : la couleur.<br />. De plus, elle contient les méthodes abstaites qui devront être redéfinies par ses sous-classes. Chaque forme devra être capable de s'afficher et de se translater. Afin de gérer les collisions, chaque forme devra également être capable de retourner sa boite englobante. Duex méthodes existent dans cette classe afin de gérer les collisions : intersection et intersectionRapide prenant toutes les deux une forme en paramètre. La première peut être redéfinie afin de gérer les collisions de manière précise alors que la seconde ne peut être redéfinie et sera utilisée pour faire des tests de collision rapidement.<br />
 * Elle permet de gérer la totalité des formes géométriques du moteur.<br />
 * Chaque classe de MG2D gérant une forme géométrique doit hériter de cette classe.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 * @see Couleur
 * @see Dessin#intersection
 * @see Dessin#intersectionRapide
 */
public abstract class Dessin {

    // Attribut //

    private Couleur couleur;

    // Constructeur //

    /**
     * Création d'un dessin noir.
     */
    public Dessin(){
	couleur = Couleur.NOIR;
    }

    /**
     * Création d'un dessin en initialisant sa couleur.
     * @param couleur Couleur du dessin.
     * @see Couleur
     */
    public Dessin ( Couleur couleur ) {

	this.couleur = new Couleur(couleur);
    }

    // Accesseurs //

    // Getter //

    /**
     * Retourne la couleur du dessin.
     * @return Couleur du dessin.
     * @see Couleur
     */
    public Couleur getCouleur () {

	return new Couleur(couleur);
    }

    /**
     * Méthode abstraite destinée à retourner la boite englobante d'une forme géométrique.<br />
     * La boite englobante est un rectangle englobant totalement la forme géométrique. C'est cette méthode qui sera utilisée par défaut pour les tests d'intersection. La boite englobante étant liée à la future forme géométrique, cette méthode ne peut être écrite dans cette classe mais devra forcément exister.
     * @return La boiteEnglobante du dessin
     * @see BoiteEnglobante
     */
    public abstract BoiteEnglobante getBoiteEnglobante ();

    // Setter //

    /**
     * Attribut une couleur au dessin.
     * @param couleur Couleur du dessin.
     * @see Couleur
     */
    public void setCouleur ( Couleur couleur ) {

	this.couleur = new Couleur(couleur);
    }

    // Méthodes //

    /**
     * Méthode abstraite destinée à faire translater la forme géométrique.<br />
     * Cette méthode doit faire translater la future forme géométrique dans une direction définie par les paramètres. Le fonctionnement de la translation étant fortement liée à la forme, cette méthode ne peut être écrite dans cette classe mais devra forcément exister.
     * @param dx pas de translation en X
     * @param dy pas de translation en Y
     */
    public abstract void translater ( int dx, int dy );

    /**
     * Méthode abstraite destinée à afficher une forme géométrique.<br />
     * La forme géométrique étant définie dans les sous-classes, cette méthode ne peut être écrite ici mais devra forcément exister.
     * @param g Graphics.
     */
    public abstract void afficher ( Graphics g );

    /**
     * Méthode de permettant d'effectuer un test d'intersection.<br />
     * Par défaut, ce test est fait grâce aux boites englobantes ce qui donne un test rapide d'exécution mais très imprécis. Il est conseillé de redéfinir cette méthode d'intersection entre la forme décrite dans la sous-classe et toutes formes des sous-classes déjà existantes.<br />
     * @param d Dessin - permet le polymorphisme et d'appeler la bonne méthode s'il y a redéfinition dans les sous-classes.
     * @return Vrai si la méthode détecte une intersection, faux sinon.
     */
    public boolean intersection ( Dessin d ) {

	return this.getBoiteEnglobante().intersection( d.getBoiteEnglobante() );
    }

    /**
     * Méthode de permettant d'effectuer un test d'intersection rapide, basé sur les boites englobantes.<br />
     * En appelant cette méthode, l'utilisateur n'appelle pas les méthodes d'intersection précises des sous-classes mais celle faisant appel aux boites englobantes.<br />
     * Cette méthode est <em>final</em> et ne peut donc pas être redéfinie.
     * @param d Dessin.
     * @return Vrai si la méthode détecte une intersection, faux sinon.
     */
    public final boolean intersectionRapide ( Dessin d ) {

	return this.getBoiteEnglobante().intersection( d.getBoiteEnglobante() );
    }

    /**
     * Méthode abstraite obligeant chaque sous-classe à redéfinir la méthode permettant d'obtenir une description de la forme géométrique.
     * @return Une chaîne de caractères décrivant la forme géométrique.
     */
    public abstract String toString();

    /**
     * Permet de tester l'égalité entre deux dessins.
     * @param obj L'objet à comparer.
     * @return Vrai si l'objet passé en paramètre est un dessin et que leurs caractéristiques sont les mêmes, faux sinon.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Dessin) {
            // Vérification des valeurs des attributs
	    Dessin other = (Dessin) obj;
	    return couleur.equals(other.couleur);
	}
	return false;
    }
}
