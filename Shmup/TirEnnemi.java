import MG2D.*;
import MG2D.geometrie.*;

class TirEnnemi extends Tir{

    public TirEnnemi(Point oorigine, Point ddestination, long ttpsDepart, int ppuissance, long ddureeTir){
	super(oorigine,ddestination,ttpsDepart,ppuissance,ddureeTir);
    }
    protected void modifCouleurTir(){
	setCouleur(Couleur.ROUGE);
    }

    protected void modifTailleTir(){
	setRayon(3);
    }
}

