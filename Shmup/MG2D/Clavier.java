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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Cette classe implémente les méthodes de KeyListener permettant la gestion du clavier.
 * <br /><br />
 * Elle permet de gérer le clavier dans des applications développées avec la bibliothèque MG2D.<br />
 * A la façon d'une borne d'arcade, elle gère uniquement les touches directionnelles, 6 lettres (a/z/e et q/s/d), la barre espace et le bouton entrée.<br />
 * Elle fonctionne sur le principe de booléen que l'on change quand on appuie ou relache les touches.<br />
 * Si l'utilisateur souhaite implémenter la gestion de davantage de touches, il peut reprendre ce code et le modifier selon ses besoins.
 * @author Equipe 2D, Rémi Synave
 * @version 2.2
 */
public class Clavier implements KeyListener {

    // Attributs //

    private boolean gauche;
    private boolean gaucheTape;
    private boolean droite;
    private boolean droiteTape;
    private boolean haut;
    private boolean hautTape;
    private boolean bas;
    private boolean basTape;

    private boolean espace;
    private boolean espaceTape;
    private boolean entree;
    private boolean entreeTape;

    private boolean a;
    private boolean aTape;
    private boolean z;
    private boolean zTape;
    private boolean e;
    private boolean eTape;
    private boolean q;
    private boolean qTape;
    private boolean s;
    private boolean sTape;
    private boolean d;
    private boolean dTape;

    // Constructeur //

    /**
     * Crée un clavier et initialise tous les attributs à faux pour touches relachés.
     */
    public Clavier () {

	gauche = gaucheTape = droite = droiteTape = false;
	haut = hautTape = bas = basTape = false;

	espace = espaceTape = entree = entreeTape = false;

	a = aTape = z = zTape = e = eTape = false;
	q = qTape = s = sTape = d = dTape = false;
    }

    // Accesseurs //

    // Getter //

    /**
     * Permet de savoir si la touche "flèche gauche" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche" : vrai pour appuyée, faux sinon.
     * @see #getGaucheEnfoncee() getGaucheEnfoncee()
     */
    @Deprecated
    public boolean getGauche () {

	return gauche;
    }

    /**
     * Permet de savoir si la touche "flèche gauche" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche" : vrai pour enfoncée, faux sinon.
     */
    public boolean getGaucheEnfoncee () {

	return gauche;
    }

    /**
     * Permet de savoir si la touche "flèche gauche" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getGaucheTape () {
	boolean aRetourner=gaucheTape;
	gaucheTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche droite" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite" : vrai pour appuyée, faux sinon.
     * @see #getDroiteEnfoncee() getDroiteEnfoncee()
     */
    @Deprecated
    public boolean getDroite () {

	return droite;
    }

    /**
     * Permet de savoir si la touche "flèche droite" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite" : vrai pour enfoncée, faux sinon.
     */
    public boolean getDroiteEnfoncee () {

	return droite;
    }

    /**
     * Permet de savoir si la touche "flèche droite" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getDroiteTape () {
	boolean aRetourner=droiteTape;
	droiteTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche haut" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut" : vrai pour appuyée, faux sinon.
     * @see #getHautEnfoncee() getHautEnfoncee()
     */
    @Deprecated
    public boolean getHaut () {

	return haut;
    }

    /**
     * Permet de savoir si la touche "flèche haut" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut" : vrai pour enfoncée, faux sinon.
     */
    public boolean getHautEnfoncee () {

	return haut;
    }

    /**
     * Permet de savoir si la touche "flèche haut" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getHautTape () {
	boolean aRetourner=hautTape;
	hautTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche bas" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas" : vrai pour appuyée, faux sinon.
     * @see #getBasEnfoncee() getBasEnfoncee()
     */
    @Deprecated
    public boolean getBas () {

	return bas;
    }

    /**
     * Permet de savoir si la touche "flèche bas" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas" : vrai pour enfoncée, faux sinon.
     */
    public boolean getBasEnfoncee () {

	return bas;
    }

    /**
     * Permet de savoir si la touche "flèche bas" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getBasTape () {
	boolean aRetourner=basTape;
	basTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "espace" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "espace" : vrai pour appuyée, faux sinon.
     * @see #getEspaceEnfoncee() getEspaceEnfoncee()
     */
    @Deprecated
    public boolean getEspace () {

	return espace;
    }

    /**
     * Permet de savoir si la touche "espace" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "espace" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEspaceEnfoncee () {

	return espace;
    }

    /**
     * Permet de savoir si la touche "espace" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "espace tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getEspaceTape () {
	boolean aRetourner=espaceTape;
	espaceTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "entrée" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "entrée" : vrai pour appuyée, faux sinon.
     * @see #getEntreeEnfoncee() getEntreeEnfoncee()
     */
    @Deprecated
    public boolean getEntree () {

	return entree;
    }

    /**
     * Permet de savoir si la touche "entrée" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "entrée" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEntreeEnfoncee () {

	return entree;
    }

    /**
     * Permet de savoir si la touche "entrée" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "entrée tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getEntreeTape () {
	boolean aRetourner=entreeTape;
	entreeTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "a" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "a" : vrai pour appuyée, faux sinon.
     * @see #getAEnfoncee() getAEnfoncee()
     */
    @Deprecated
    public boolean getA () {

	return a;
    }

    /**
     * Permet de savoir si la touche "a" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "a" : vrai pour enfoncée, faux sinon.
     */
    public boolean getAEnfoncee () {

	return a;
    }

    /**
     * Permet de savoir si la touche "a" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "a tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getATape () {
	boolean aRetourner=aTape;
	aTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "z" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "z" : vrai pour appuyée, faux sinon.
     * @see #getZEnfoncee() getZEnfoncee()
     */
    @Deprecated
    public boolean getZ () {

	return z;
    }

    /**
     * Permet de savoir si la touche "z" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "z" : vrai pour enfoncée, faux sinon.
     */
    public boolean getZEnfoncee () {

	return z;
    }

    /**
     * Permet de savoir si la touche "z" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "z tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getZTape () {
	boolean aRetourner=zTape;
	zTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "e" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "e" : vrai pour appuyée, faux sinon.
     * @see #getEEnfoncee() getEEnfoncee()
     */
    @Deprecated
    public boolean getE () {

	return e;
    }

    /**
     * Permet de savoir si la touche "e" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "e" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEEnfoncee () {

	return e;
    }

    /**
     * Permet de savoir si la touche "e" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "e tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getETape () {
	boolean aRetourner=eTape;
	eTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "q" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "q" : vrai pour appuyée, faux sinon.
     * @see #getQEnfoncee() getQEnfoncee()
     */
    @Deprecated
    public boolean getQ () {

	return q;
    }

    /**
     * Permet de savoir si la touche "q" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "q" : vrai pour enfoncée, faux sinon.
     */
    public boolean getQEnfoncee () {

	return q;
    }

    /**
     * Permet de savoir si la touche "q" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "q tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getQTape () {
	boolean aRetourner=qTape;
	qTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "s" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "s" : vrai pour appuyée, faux sinon.
     * @see #getSEnfoncee() getSEnfoncee()
     */
    @Deprecated
    public boolean getS () {

	return s;
    }

    /**
     * Permet de savoir si la touche "s" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "s" : vrai pour enfoncée, faux sinon.
     */
    public boolean getSEnfoncee () {

	return s;
    }

    /**
     * Permet de savoir si la touche "s" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "s tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getSTape () {
	boolean aRetourner=sTape;
	sTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "d" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "d" : vrai pour appuyée, faux sinon.
     * @see #getDEnfoncee() getDEnfoncee()
     */
    @Deprecated
    public boolean getD () {

	return d;
    }

    /**
     * Permet de savoir si la touche "d" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "d" : vrai pour enfoncée, faux sinon.
     */
    public boolean getDEnfoncee () {

	return d;
    }

    /**
     * Permet de savoir si la touche "d" a été appuyée puis relachée.
     * @return retourne la valeur du booléen correspondant à la touche "d tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getDTape () {
	boolean aRetourner=dTape;
	dTape=false;
	return aRetourner;
    }

    // Setter //

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche gauche".
     * @param gauche le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setGauche ( boolean gauche ) {

	this.gauche = gauche;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche droite".
     * @param droite le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setDroite ( boolean droite ) {

	this.droite = droite;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche haut".
     * @param haut le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setHaut( boolean haut ) {

	this.haut = haut;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche bas".
     * @param bas le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setBas ( boolean bas ) {

	this.bas = bas;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "espace".
     * @param espace le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setEspace ( boolean espace ) {

	this.espace = espace;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "entrée".
     * @param entree le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setEntree ( boolean entree ) {

	this.entree = entree;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "a".
     * @param a le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setA ( boolean a ) {

	this.a = a;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "z".
     * @param z le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setZ ( boolean z ) {

	this.z = z;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "e".
     * @param e le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setE ( boolean e ) {

	this.e = e;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "q".
     * @param q le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setQ ( boolean q ) {

	this.q = q;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "s".
     * @param s le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setS ( boolean s ) {

	this.s = s;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "d".
     * @param d le paramètre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setD ( boolean d ) {

	this.d = d;
    }

    // Méthodes //

    /**
     * Méthode permettant la réinitialisation du clavier. Réinitialisation de tous les événements.
     */
    public void reinitialisation(){

	gauche = gaucheTape = droite = droiteTape = false;
	haut = hautTape = bas = basTape = false;

	espace = espaceTape = entree = entreeTape = false;

	a = aTape = z = zTape = e = eTape = false;
	q = qTape = s = sTape = d = dTape = false;
    }

    /**
     * Implémentation de la méthode KeyReleased - méthode appelée automatiquement lors d'un événement clavier.
     * <br /><br />
     * Elle permet de mettre à jour les valeurs des attributs en fonction des interactions au clavier.<br />
     * Ici, si une touche est relachée, l'attribut gérant cette touche est mis à jour.<br />
     * Pour la gestion sur l'appui des touches, voir la méthode keyPressed ( KeyEvent k ).
     * @param k un événement clavier.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html" target="_blank">KeyEvent</a>
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html" target="_blank">KeyListener</a>
     * @see #keyPressed keyPressed ( KeyEvent k )
     */
    @Override
    public void keyReleased ( KeyEvent k ) {

	switch ( k.getKeyCode() ) {

	case KeyEvent.VK_LEFT :
	    gauche = false;
	    gaucheTape = true;
	    break;

	case KeyEvent.VK_RIGHT :
	    droite = false;
	    droiteTape = true;
	    break;

	case KeyEvent.VK_UP :
	    haut = false;
	    hautTape = true;
	    break;

	case KeyEvent.VK_DOWN :
	    bas = false;
	    basTape = true;
	    break;

	case KeyEvent.VK_SPACE :
	    espace = false;
	    espaceTape = true;
	    break;

	case KeyEvent.VK_ENTER :
	    entree = false;
	    entreeTape = true;
	    break;

	case KeyEvent.VK_A :
	    a = false;
	    aTape = true;
	    break;

	case KeyEvent.VK_Z :
	    z = false;
	    zTape = true;
	    break;

	case KeyEvent.VK_E :
	    e = false;
	    eTape = true;
	    break;

	case KeyEvent.VK_Q :
	    q = false;
	    qTape = true;
	    break;

	case KeyEvent.VK_S :
	    s = false;
	    sTape = true;
	    break;

	case KeyEvent.VK_D :
	    d = false;
	    dTape = true;
	    break;
	}
    }

    /**
     * Implémentation de la méthode KeyTyped - méthode appelée automatiquement lors d'un événement clavier.
     * <br /><br />
     * Cette méthode doit être implémentée mais est inutile dans ce moteur.
     */
    @Override
    public void keyTyped ( KeyEvent k ) {
    }

    /**
     * Implémentation de la méthode KeyPressed - méthode appelée automatiquement lors d'un événement clavier.
     * <br /><br />
     * Elle permet de mettre à jour les valeurs des attributs en fonction des interactions au clavier.<br />
     * Ici, si une touche est pressée, l'attribut gérant cette touche est mis à jour.<br />
     * Pour la gestion du relachement des touches, voir la méthode keyReleased ( KeyEvent k ).
     * @param k un événement clavier.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html" target="_blank">KeyEvent</a>
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html" target="_blank">KeyListener</a>
     * @see #keyReleased keyReleased ( KeyEvent k )
     */
    @Override
    public void keyPressed ( KeyEvent k ) {

	switch ( k.getKeyCode() ) {

	case KeyEvent.VK_LEFT :
	    gauche = true;
	    break;

	case KeyEvent.VK_RIGHT :
	    droite = true;
	    break;

	case KeyEvent.VK_UP :
	    haut = true;
	    break;

	case KeyEvent.VK_DOWN :
	    bas = true;
	    break;

	case KeyEvent.VK_SPACE :
	    espace = true;
	    break;

	case KeyEvent.VK_ENTER :
	    entree = true;
	    break;

	case KeyEvent.VK_A :
	    a = true;
	    break;

	case KeyEvent.VK_Z :
	    z = true;
	    break;

	case KeyEvent.VK_E :
	    e = true;
	    break;

	case KeyEvent.VK_Q :
	    q = true;
	    break;

	case KeyEvent.VK_S :
	    s = true;
	    break;

	case KeyEvent.VK_D :
	    d = true;
	    break;
	}
    }
}
