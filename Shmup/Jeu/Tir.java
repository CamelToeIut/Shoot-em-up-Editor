package Jeu;

import MG2D.*;
import MG2D.geometrie.*;

abstract class Tir extends Cercle{
    private Point origine;
    private Point destination;
    private long tpsDepart;
    private long dureeTir;
    private int puissance;

    public Tir(Point oorigine, Point ddestination, long ttpsDepart, int ppuissance, long ddureeTir){
	super(Couleur.NOIR,new Point(oorigine),1,true);
	origine=new Point(oorigine);
	destination=new Point(ddestination);
	tpsDepart=ttpsDepart;
	dureeTir=ddureeTir;
	puissance=ppuissance;
	modifCouleurTir();
	modifTailleTir();
    }

    protected abstract void modifCouleurTir();

    protected abstract void modifTailleTir();

    public void majPosition(long tps){
	long petitDelta=tps-tpsDepart;
	double coeff=(petitDelta*1.0)/(dureeTir*1.0);
	int deltaX=destination.getX()-origine.getX();
	int deltaY=destination.getY()-origine.getY();
	setO(new Point((int)(origine.getX()+coeff*deltaX),(int)(origine.getY()+coeff*deltaY)));
    }

    public Point getOrigine(){
	return new Point(origine);
    }

    public Point getDestination(){
	return new Point(destination);
    }

    public long getTpsDepart(){
	return tpsDepart;
    }

    public long getDureeTir(){
	return dureeTir;
    }

    public int getPuissance(){
	return puissance;
    }

    //TODO
    /*public boolean equals(){
      }*/

    public String toString(){
	return new String("de "+origine+" à "+destination+" en "+dureeTir+"ms à partir de "+tpsDepart+" avec une puissance de "+puissance);
    }

}
