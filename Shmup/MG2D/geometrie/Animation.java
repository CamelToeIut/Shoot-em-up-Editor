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
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Cette classe l'affichage d'images animées dans vos applications. Elle ne permet pas d'intégrer des gifs animés directement. Les images que vous voulez afficher doivent être dans un format supporté par MG2D (jpg, png, etc.). L'animation doit être un ensemble d'images dont le nom est composé d'une racine commune à toutes les images suivi d'un numéro. Par exemple, une nimation pourra être composée des images : "img/perso1.png", "img/perso2.png", ..., "img/perso15.png". Ces informations (racine commune du nom de fichier, numéro de la première image, de la dernière, extension du fichier) vous eront nécessaires pour la création d'une animation lors de l'appel au contructeur. <br />
 * Les numéros de première et dernière image peuvent être écrit de manière classique ou formater de manière à ce que le nombre de caractère utilisé soit toujours constant. Exemple : "img/perso001.png", "img/perso002.png", ..., "img/perso015.png". Dans ce cas, bien définir le numéro de première image à "001" et le numéro de dernière image à "015".<br />
 * Le passage d'une image à la suivante se fait à chaque rafraichissement de la fenêtre dans laquelle l'animation est affichée. Par défaut, la lecture est activée.<br />
 * Il est également possible de savoir à tout moment le nombre de fois que l'animation a été jouée.<br />
 * Cette classe n'a pas vraiment été testée... Merci de faire remonter les bugs :-)<br />
 * @author Rémi Synave
 * @version 2.7
 * @see Texture
 */
public class Animation extends Texture {

    private int nbTourAnimation;
    private String racineCheminImage;
    private String numeroPremiereImage;
    private String numeroDerniereImage;
    private String extensionImage;
    private String numeroImageCourante;
    private boolean lecture;

    // Constructeurs //

    /**
     * Constructeur par défaut qui ne fait que lancer une exception.
     * @exception java.lang.RuntimeException Ce constructeur ne prenant pas de parmètre ne fait que lancer l'exception.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html" target="_blank">RuntimeException</a>
     */
    public Animation(){
	throw new java.lang.RuntimeException("Le constructeur par défaut d'animation ne peut être appelé. Il faut au moins spécifier une image.");
    }

    /**
     * Contruit une animation sur le modèle d'une autre animation.
     * @param a L'animation à copier.
     */
    public Animation ( Animation a ){
	super(a);
	nbTourAnimation=a.nbTourAnimation;
	racineCheminImage=new String(a.racineCheminImage);
	numeroPremiereImage=new String(a.numeroPremiereImage);
	numeroDerniereImage=new String(a.numeroDerniereImage);
	extensionImage=new String(a.extensionImage);
	numeroImageCourante=new String(a.numeroImageCourante);
	lecture=true;

    }

    // Sans couleur de fond //

    /**
     * Construit une Animation à partir d'un chemin vers un fichier image, des différentes informations nécessaires et d'un Point.<br />
     * <br /><br />
     * La taille de l'image correspondra à la taille réelle de l'image. Elle ne sera pas déformée.<br />
     * Par défaut, la transparence est activée.
     * @param racineCheminImage Racine du chemin d'accès vers l'image.
     * @param numeroPremiereImage numero sous forme de chaine de caracteres de la première image.
     * @param numeroDerniereImage numero sous forme de chaine de caracteres de la dernière image.
     * @param extensionImage extension de l'image (sans le point), format de fichier.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @see Point
     */
    public Animation ( String racineCheminImage, String numeroPremiereImage, String numeroDerniereImage, String extensionImage, Point a ) {
	super ( racineCheminImage+numeroPremiereImage+"."+extensionImage, a);

	nbTourAnimation=0;
	this.racineCheminImage=new String(racineCheminImage);
	this.numeroPremiereImage=new String(numeroPremiereImage);
	this.numeroDerniereImage=new String(numeroDerniereImage);
	this.extensionImage=new String(extensionImage);
	numeroImageCourante=new String(numeroPremiereImage);
	lecture=true;

    }

    /**
     * Construit une Animation à partir d'un chemin vers un fichier image, des différentes informations nécessaires, d'un Point, une largeur et une hauteur.<br />
     * Par défaut, la transparence est activée.
     * @param racineCheminImage Racine du chemin d'accès vers l'image.
     * @param numeroPremiereImage numero sous forme de chaine de caracteres de la première image.
     * @param numeroDerniereImage numero sous forme de chaine de caracteres de la dernière image.
     * @param extensionImage extension de l'image (sans le point), format de fichier.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @param larg Largeur souhaitée de l'image.
     * @param haut Lauteur souhaitée de l'image.
     * @see Point
     */
    public Animation ( String racineCheminImage, String numeroPremiereImage, String numeroDerniereImage, String extensionImage, Point a, int larg, int haut ) {
	super ( racineCheminImage+numeroPremiereImage+"."+extensionImage, a, larg, haut);

	nbTourAnimation=0;
	this.racineCheminImage=new String(racineCheminImage);
	this.numeroPremiereImage=new String(numeroPremiereImage);
	this.numeroDerniereImage=new String(numeroDerniereImage);
	this.extensionImage=new String(extensionImage);
	numeroImageCourante=new String(numeroPremiereImage);
	lecture=true;
    }

    // Avec couleur de fond //

    /**
     * Construit une Animation à partir d'un chemin vers un fichier image, des différentes informations nécessaires et d'un Point.<br />
     * <br /><br />
     * La taille de l'image correspondra à la taille réelle de l'image. Elle ne sera pas déformée.<br />
     * Par défaut, la transparence est désactivée.
     * @param couleur Couleur de fond
     * @param racineCheminImage Racine du chemin d'accès vers l'image.
     * @param numeroPremiereImage numero sous forme de chaine de caracteres de la première image.
     * @param numeroDerniereImage numero sous forme de chaine de caracteres de la dernière image.
     * @param extensionImage extension de l'image (sans le point), format de fichier.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @see Couleur
     * @see Point
     */
    public Animation ( Couleur couleur, String racineCheminImage, String numeroPremiereImage, String numeroDerniereImage, String extensionImage, Point a ) {
	super ( couleur, racineCheminImage+numeroPremiereImage+"."+extensionImage, a);

	nbTourAnimation=0;
	this.racineCheminImage=new String(racineCheminImage);
	this.numeroPremiereImage=new String(numeroPremiereImage);
	this.numeroDerniereImage=new String(numeroDerniereImage);
	this.extensionImage=new String(extensionImage);
	numeroImageCourante=new String(numeroPremiereImage);
	lecture=true;
    }

    /**
     * Construit une Animation à partir d'un chemin vers un fichier image, des différentes informations nécessaires, d'un Point, une largeur et une hauteur.<br />
     * Par défaut, la transparence est désactivée.
     * @param couleur Couleur de fond
     * @param racineCheminImage Racine du chemin d'accès vers l'image.
     * @param numeroPremiereImage numero sous forme de chaine de caracteres de la première image.
     * @param numeroDerniereImage numero sous forme de chaine de caracteres de la dernière image.
     * @param extensionImage extension de l'image (sans le point), format de fichier.
     * @param a	Position du coin bas gauche de l'image dans la zone d'affichage.
     * @param larg Largeur souhaitée de l'image.
     * @param haut Hauteur souhaitée de l'image.
     * @see Couleur
     * @see Point
     */
    public Animation ( Couleur couleur, String racineCheminImage, String numeroPremiereImage, String numeroDerniereImage, String extensionImage, Point a, int larg, int haut ) {
	super (couleur, racineCheminImage+numeroPremiereImage+"."+extensionImage, a, larg, haut);

	nbTourAnimation=0;
	this.racineCheminImage=new String(racineCheminImage);
	this.numeroPremiereImage=new String(numeroPremiereImage);
	this.numeroDerniereImage=new String(numeroDerniereImage);
	this.extensionImage=new String(extensionImage);
	numeroImageCourante=new String(numeroPremiereImage);
	lecture=true;
    }

    // getter
    /**
     * Permet de connaitre le nombre de tours que l'animation a déjà faite.
     * @return le nombre de fois que l'animation a été jouée.
     */
    public int getNbTourAnimation(){
	return nbTourAnimation;
    }

    /**
     * Permet de connaître l'état de l'animation, de savoir si l'animation est en cours de lecture ou non.
     * @return vrai si l'animation est en cours de lecture, faux sinon.
     */
    public boolean getLecture(){
	return lecture;
    }

    /**
     * Retourne le numéro de l'image affichée.
     * @return le numéro de l'image courante sous forme de chaîne de caractères.
     */
    public String getNumeroImageCourante(){
	return numeroImageCourante;
    }

    /**
     * Permet de savoir si l'image courante affichée est la première de l'animation
     * @return vrai si l'image affichée est la première de la série, faux sinon.
     */
    public boolean surPremiereImage(){
	return numeroImageCourante.equals(numeroPremiereImage);
    }

    /**
     * Permet de savoir si l'image courante affichée est la dernière de l'animation
     * @return vrai si l'image affichée est la dernière de la série, faux sinon.
     */
    public boolean surDerniereImage(){
	return numeroImageCourante.equals(numeroDerniereImage);
    }

    /**
     * Fixe l'image courante à la première image de la série. Permet donc de relancer l'animation depuis le début.
     */
    public void affichePremiereImage(){
	numeroImageCourante=new String(numeroPremiereImage);
    }

    /**
     * Fixe l'image courante à la dernière image de la série.
     */
    public void afficheDerniereImage(){
	numeroImageCourante=new String(numeroDerniereImage);
    }


    private void imageSuivante(){
	int numCourant=Integer.parseInt(numeroImageCourante);
	int derImg=Integer.parseInt(numeroDerniereImage);
	numCourant++;
	if(numCourant>derImg){
	    numCourant=Integer.parseInt(numeroPremiereImage);
	    nbTourAnimation++;
	}
	numeroImageCourante=""+numCourant;
	if(numeroPremiereImage.length()==numeroDerniereImage.length()){
	    while(numeroPremiereImage.length()>numeroImageCourante.length())
		numeroImageCourante="0"+numeroImageCourante;
	}
    }

    // setter
    /**
     * Permet de (re)lancer l'animation ou la mettre en pause.
     * @param b passer true si on veut (re)lancer l'animation, false si on veut la mettre en pause.
     */
    public void setLecture(boolean b){
	lecture=b;
    }

    /**
     * Permet de (re)lancer l'animation.
     */
    public void lecture(){
	lecture=true;
    }

    /**
     * Permet de stopper l'animation.
     */
    public void stop(){
	lecture=false;
    }

    /**
     * Permet de contrôler l'animation et d'afficher une image précise. L'animation continuera à partir de cette image.
     * @param str une chaine de caractères spécifiant le numéro de l'image à afficher.
     */
    public void setNumeroImage(String str){
	/*TODO - Vérifier que le numéro appartient bien à l'ensemble d'images*/
	numeroImageCourante=new String(str);
	setImg(racineCheminImage+numeroImageCourante+"."+extensionImage);
    }


    // Méthode //

    /**
     * Implémentation de la méthode afficher() de la classe abstraite Dessin.<br />
     * Elle permet d'afficher une Animation sur la zone d'affichage. En fonction de l'image en cours, la suivante est choisie puis affichée.
     * @param g Graphics.
     */
    public void afficher ( Graphics g ) {
	if(lecture){
	    imageSuivante();
	    setImg(racineCheminImage+numeroImageCourante+"."+extensionImage);
	}

	super.afficher(g);
    }

    /**
     * Méthode equals permettant de tester l'égalité entre une animation et un objet passé en paramètre.
     * @return Vrai si l'objet passé en paramètre est une animation dont les caractéristiques sont les mêmes que l'animation sur laquelle la méthode est appelée.
     */
    public boolean equals(Object obj){
	if (obj==this) {
            return true;
        }

        // Vérification du type du paramètre
        if (obj instanceof Animation) {
            // Vérification des valeurs des attributs
	    Animation other = (Animation) obj;
	    return super.equals(other) && racineCheminImage.equals(other.racineCheminImage) && numeroPremiereImage.equals(other.numeroPremiereImage) && numeroDerniereImage.equals(other.numeroDerniereImage) && extensionImage.equals(other.extensionImage);
	}
	return false;
    }
}
