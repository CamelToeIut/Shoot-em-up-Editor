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

package MG2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import MG2D.geometrie.Point;

/**
 * Cette classe implémente les méthodes de MouseListener permettant la gestion de la souris.
 * <br /><br />
 * Elle permet de gérer la position et les clics de la souris dans une fenêtre créée avec la bibliothèque MG2D.
 * @author Rémi Synave
 * @version 2.2
 */

public class Souris implements MouseListener, MouseMotionListener {

    // Attributs //

    private boolean boutonGaucheEnfonce;
    private boolean boutonMilieuEnfonce;
    private boolean boutonDroitEnfonce;
    private boolean clicGauche;
    private boolean clicDroit;
    private boolean clicMilieu;
    private Point position;
    private int tailleY;

    // Constructeur //
    /**
     * Ce constructeur ne devrait pas être utilisé car la position de la position sera faussée.<br />
     * Crée une souris et initialise tous les attributs à faux pour clics relachés.
     */
    public Souris () {
	clicGauche=false;
	clicDroit=false;
	clicMilieu=false;
	boutonGaucheEnfonce=false;
	boutonDroitEnfonce=false;
	boutonMilieuEnfonce=false;
	position = new Point(-1,-1);
	tailleY=0;
    }

    /**
     * Crée une souris et initialise tous les attributs à faux pour clics relachés.<br />
     * Ce constructeur utilise la hauteur (passé en paramètre) de la fenêtre afin de calculer la position exacte de la souris. L'origine de la zone d'affichage se trouve en bas à gauche de la fenêtre.
     * @param taille Taille en hauteur de la fenêtre.
     */
    public Souris (int taille) {
	clicGauche=false;
	clicDroit=false;
	clicMilieu=false;
	boutonGaucheEnfonce=false;
	boutonDroitEnfonce=false;
	boutonMilieuEnfonce=false;
	position = new Point(-1,-1);
	tailleY=taille;
    }

    /**
     * Méthode permettant la réinitialisation de la souris. Réinitialisation de tous les événements.
     */
    public void reinitialisation(){
	clicGauche=false;
	clicDroit=false;
	clicMilieu=false;
	boutonGaucheEnfonce=false;
	boutonDroitEnfonce=false;
	boutonMilieuEnfonce=false;
    }

    /**
     * Méthode inutile - Implémentation de la méthode présente dans l'interface MouseListener.<br ∕>
     * Cette méthode est appelée lorsque la souris entre dans la zone d'affichage alors qu'elle était à l'extérieur précédemment.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Méthode inutile - Implémentation de la méthode présente dans l'interface MouseListener.<br ∕>
     * Cette méthode est appelée lorsque la souris sort de la zone d'affichage alors qu'elle était à l'intérieur précédemment.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Implémentation de la méthode de l'interface appelée quand on appuie sur un bouton de la souris.
     */
    @Override
    public void mousePressed(MouseEvent e) {
	switch ( e.getButton() ) {
	case MouseEvent.BUTTON1 :
	    boutonGaucheEnfonce=true;
	    break;

	case MouseEvent.BUTTON2 :
	    boutonMilieuEnfonce=true;
	    break;

	case MouseEvent.BUTTON3 :
	    boutonDroitEnfonce=true;
	    break;
	}
    }

    /**
     * Implémentation de la méthode de l'interface appelée quand on relâche sur un bouton de la souris.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
	switch ( e.getButton() ) {
	case MouseEvent.BUTTON1 :
	    boutonGaucheEnfonce=false;
	    break;

	case MouseEvent.BUTTON2 :
	    boutonMilieuEnfonce=false;
	    break;

	case MouseEvent.BUTTON3 :
	    boutonDroitEnfonce=false;
	    break;
	}
    }

    /**
     * Implémentation de la méthode de l'interface appelée quand on clic (appui puis relâchement) sur un bouton de la souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
	switch ( e.getButton() ) {
	case MouseEvent.BUTTON1 :
	    clicGauche=true;
	    break;

	case MouseEvent.BUTTON2 :
	    clicMilieu=true;
	    break;

	case MouseEvent.BUTTON3 :
	    clicDroit=true;
	    break;
	}
    }

    /**
     * Permet de savoir si l'utilisateur a fait un clic gauche.
     * @return vrai si l'utilisateur a fait un clic gauche, faux sinon.
     */
    public boolean getClicGauche(){
	boolean aretourner=clicGauche;
	clicGauche=false;
	return aretourner;
    }

    /**
     * Permet de savoir si l'utilisateur a fait un clic milieu.
     * @return vrai si l'utilisateur a fait un clic milieu, faux sinon.
     */
    public boolean getClicMilieu(){
	boolean aretourner=clicMilieu;
	clicMilieu=false;
	return aretourner;
    }

    /**
     * Permet de savoir si l'utilisateur a fait un clic droit.
     * @return vrai si l'utilisateur a fait un clic droit, faux sinon.
     */
    public boolean getClicDroit(){
	boolean aretourner=clicDroit;
	clicDroit=false;
	return aretourner;
    }

    /**
     * Permet de savoir si le clic gauche est enfoncé.
     * @return vrai si le clic gauche est enfoncé, faux sinon.
     */
    public boolean getBoutonGaucheEnfonce(){
	return boutonGaucheEnfonce;
    }

    /**
     * Permet de savoir si le clic milieu est enfoncé.
     * @return vrai si le clic milieu est enfoncé, faux sinon.
     */
    public boolean getBoutonMilieuEnfonce(){
	return boutonMilieuEnfonce;
    }

    /**
     * Permet de savoir si le clic droit est enfoncé.
     * @return vrai si le clic droit est enfoncé, faux sinon.
     */
    public boolean getBoutonDroitEnfonce(){
	return boutonDroitEnfonce;
    }

    /**
     * Permet de connaitre la position de la souris dans la fenêtre à tout moment.<br />
     * L'origine, le point (0,0), se trouve en bas à gauche de la fenêtre.
     * @return Un objet Point dont les coordoonées sont celles de la position de la souris dans la fenêtre.
     */
    public Point getPosition(){
	return new Point(position);
    }

    /**
     * Implémentation de la méthode de l'interface appelée lorsque la souris bouge alors qu'un clic est enfoncé.
     */
    @Override
    public void mouseDragged(MouseEvent e){
	//Mise à jour de la position de la souris.
	position.setX(e.getX());
	position.setY(tailleY-e.getY()-1);
    }

    /**
     * Implémentation de la méthode de l'interface appelée lorsque la souris bouge.
     */
    @Override
    public void mouseMoved(MouseEvent e){
	//Mise à jour de la position de la souris.
	position.setX(e.getX());
	position.setY(tailleY-e.getY()-1);
    }
}
