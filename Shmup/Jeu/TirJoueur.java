

import MG2D.*;
import MG2D.geometrie.*;

class TirJoueur extends Tir{

    public TirJoueur(Point oorigine, Point ddestination, long ttpsDepart, int ppuissance, long ddureeTir){
	super(oorigine,ddestination,ttpsDepart,ppuissance,ddureeTir);
    }
    protected void modifCouleurTir(){
	setCouleur(Couleur.ORANGE);
    }

    protected void modifTailleTir(){
	setRayon(5);
    }
}

