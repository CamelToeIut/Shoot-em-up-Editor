import MG2D.*;
import MG2D.geometrie.*;

import java.util.ArrayList;

abstract class Vaisseau extends Texture{

    public static final int TIR_UNIQUE=0;
    public static final int EVENTAIL=1;
    public static final int PARALLELE=2;

    private long dernierTir;
    private int vitesseTir;
    private long cadenceTir;
    private int pointDeVie, pointDeVieMax;
    protected int typeArme, typeArmeNbMunition, typeArmeEcartMunition;
    protected int tailleX, tailleY;

    public Vaisseau(String cheminTexture, Point pposition, int ppointDeVie, int vvitesseTir, long ccadenceTir, int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition, int ttailleX, int ttailleY){
	super(cheminTexture, pposition);
	cadenceTir=ccadenceTir;
	dernierTir=0;
	pointDeVie=ppointDeVie;
	pointDeVieMax=ppointDeVie;
	setTypeArme(ttypeArme, ttypeArmeNbMunition, ttypeArmeEcartMunition);
	vitesseTir=vvitesseTir;
	tailleX=ttailleX;
	tailleY=ttailleY;
    }

    public boolean getAutorisationTir(long temps){
	if((temps-dernierTir)>cadenceTir){
	    dernierTir=temps;
	    return true;
	}
	return false;
    }
    
    public void setTypeArme(int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition){
	if(ttypeArme==TIR_UNIQUE){
	    typeArme=TIR_UNIQUE;
	    typeArmeNbMunition=0;
	    typeArmeEcartMunition=0;
	    return;
	}
	if((ttypeArme==EVENTAIL || ttypeArme==PARALLELE) && ttypeArmeNbMunition>0 && ttypeArmeEcartMunition>0){
	    typeArme=ttypeArme;
	    typeArmeNbMunition=ttypeArmeNbMunition;
	    typeArmeEcartMunition=ttypeArmeEcartMunition;
	    return;
	}	    
    }

    public void touche(int dommage){
	pointDeVie-=dommage;
    }

    public boolean enVie(){
	return (pointDeVie>0);
    }

    public int getPointDeVie(){
	return pointDeVie;
    }

    public int getPointDeVieMax(){
	return pointDeVieMax;
    }

    public int getVitesseTir(){
	return vitesseTir;
    }

    public abstract int getPuissanceTir();

    public String toString(){
	String retour="\tVie : "+getPointDeVie()+"/"+getPointDeVieMax()+"\n\tVitesse du tir : "+vitesseTir+" avec une cadence d'un tir toutes les "+cadenceTir+" ms\n\tPuissance du tir : "+getPuissanceTir()+"\n\tArme : ";
	if(typeArme==Vaisseau.TIR_UNIQUE)
	    retour=retour.concat("tir unique");
	if(typeArme==Vaisseau.EVENTAIL)
	    retour=retour.concat("tir en éventail de "+typeArmeNbMunition+" munitions de "+typeArmeEcartMunition+" degrés d'écart");
	if(typeArme==Vaisseau.PARALLELE)
	    retour=retour.concat("tir parallèle de "+typeArmeNbMunition+" munitions de "+typeArmeEcartMunition+" pixels d'écart");

	return retour;
    }
    
}
