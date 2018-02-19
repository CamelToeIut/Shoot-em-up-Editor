import MG2D.*;
import MG2D.geometrie.*;

import java.util.ArrayList;

class Joueur extends Vaisseau{

    private int vitesseDeplacement;
    private int puissanceTirUnique, puissanceTirParallele, puissanceTirEventail;

    public Joueur(String str, Point p, int ppointDeVie, int vvitesseDeplacement, int vvitesseTir, long ccadenceTir, int ppuissanceTirUnique, int ppuissanceTirParallele, int ppuissanceTirEventail, int ttailleX, int ttailleY){
	super(str, p, ppointDeVie, vvitesseTir, ccadenceTir, Vaisseau.TIR_UNIQUE, 0, 0, ttailleX, ttailleY);

	vitesseDeplacement=vvitesseDeplacement;
	puissanceTirUnique=ppuissanceTirUnique;
	puissanceTirParallele=ppuissanceTirParallele;
	puissanceTirEventail=ppuissanceTirEventail;
    }

    public void majPosition(boolean haut, boolean bas, boolean gauche, boolean droite){
	if(haut){
	    if(getB().getY()<tailleY-vitesseDeplacement)
		translater(0,vitesseDeplacement);
	}
	if(bas){
	    if(getA().getY()>vitesseDeplacement)
		translater(0,-vitesseDeplacement);
	}
	if(gauche){ 
	    if(getA().getX()>vitesseDeplacement)
		translater(-vitesseDeplacement,0);
	}
	if(droite){
	    if(getB().getX()<tailleX-vitesseDeplacement)
		translater(vitesseDeplacement,0);
	}
    }

    public ArrayList<TirJoueur> getTir(long tps){
	ArrayList<TirJoueur> retour = new ArrayList<TirJoueur>();
	// Origine du tir au centre du vaisseau au dessus
	Point origine=new Point(getA().getX()+getLargeur()/2,getB().getY());
	// Ne peut que tirer tout droit
	Point destination = new Point(origine.getX(),origine.getY()+tailleY);
	
	// Si le tir est autorisé, en fonction de l'arme, on crée un ensemble de tirs
	if(getAutorisationTir(tps)){
	    // Un seul tir vers la destination
	    if(typeArme==Vaisseau.TIR_UNIQUE)
		retour.add(new TirJoueur(origine, destination, tps, puissanceTirUnique, getVitesseTir()));		
	    // Si tir en éventail, en fonction du nombre de munitions (pair ou impair)
	    if(typeArme==Vaisseau.EVENTAIL){
		if(typeArmeNbMunition%2==0){
		    for(int i=0;i<typeArmeNbMunition/2;i++){
			double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			destination=new Point((int)(dx+origine.getX()),(int)(dy+origine.getY()));
			retour.add(new TirJoueur(origine, destination, tps, puissanceTirEventail, getVitesseTir()));
			destination=new Point((int)(origine.getX()-dx),(int)(dy+origine.getY()));
			retour.add(new TirJoueur(origine, destination, tps, puissanceTirEventail, getVitesseTir()));
		    }
		}
		else{
		    retour.add(new TirJoueur(origine, destination, tps, puissanceTirEventail, getVitesseTir()));
		    for(int i=1;i<=typeArmeNbMunition/2;i++){
			double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			destination=new Point((int)(dx+origine.getX()),(int)(dy+origine.getY()));
			retour.add(new TirJoueur(origine, destination, tps, puissanceTirEventail, getVitesseTir()));
			destination=new Point((int)(origine.getX()-dx),(int)(dy+origine.getY()));
			retour.add(new TirJoueur(origine, destination, tps, puissanceTirEventail, getVitesseTir()));
		    }
		}
	    }
	    // Si tir en parallele, en fonction du nombre de munitions (pair ou impair)
	    if(typeArme==Vaisseau.PARALLELE){
		if(typeArmeNbMunition%2==0){
		    for(int i=0;i<typeArmeNbMunition/2;i++){
			Point origine2=new Point(origine.getX()+(int)((i+0.5)*typeArmeEcartMunition),origine.getY());
			destination=new Point(origine2.getX(),origine2.getY()+tailleY);
			retour.add(new TirJoueur(origine2, destination, tps, puissanceTirParallele, getVitesseTir()));
			origine2=new Point(origine.getX()+(int)((-i-0.5)*typeArmeEcartMunition),origine.getY());
			destination=new Point(origine2.getX(),origine2.getY()+tailleY);
			retour.add(new TirJoueur(origine2, destination, tps, puissanceTirParallele, getVitesseTir()));
		    }
		}
		else{
		    retour.add(new TirJoueur(origine, destination, tps, puissanceTirParallele, getVitesseTir()));
		    for(int i=1;i<=typeArmeNbMunition/2;i++){
			Point origine2 = new Point(origine.getX()+i*typeArmeEcartMunition, origine.getY());
			destination = new Point(origine2.getX(),origine2.getY()+tailleY);
			retour.add(new TirJoueur(origine2, destination, tps, puissanceTirParallele, getVitesseTir()));
			origine2 = new Point(origine.getX()-i*typeArmeEcartMunition, origine.getY());
			destination = new Point(origine2.getX(),origine2.getY()+tailleY);
			retour.add(new TirJoueur(origine2, destination, tps, puissanceTirParallele, getVitesseTir()));
		    }
		}
	    }
	}
	return retour;
    }
    
    public int getPuissanceTir(){
	if(typeArme==Vaisseau.TIR_UNIQUE)
	    return puissanceTirUnique;
	if(typeArme==Vaisseau.PARALLELE)
	    return puissanceTirParallele;
	return puissanceTirEventail;
    }
    
    // Pour l'avion avion1.png, la surface de contact est défini par les 3 boites englobantes suivantes (voir code)
    public boolean intersection(TirEnnemi t){
	if(t.intersection(new BoiteEnglobante(new Point(getA().getX()+2,getA().getY()+5),new Point(getB().getX()-2,getA().getY()+26))))
	    return true;
	if(t.intersection(new BoiteEnglobante(new Point(getA().getX()+13,getA().getY()+31),new Point(getB().getX()-13,getA().getY()+43))))
	    return true;
	if(t.intersection(new BoiteEnglobante(new Point(getA().getX()+26,getA().getY()+31),new Point(getB().getX()-23,getB().getY()))))
	    return true;
	return false;
    }

     public String toString(){
	String retour="Joueur\n"+super.toString();
	return retour;
    }
    
}
