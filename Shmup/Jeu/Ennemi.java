package Jeu;

import MG2D.*;
import MG2D.geometrie.*;

import java.util.ArrayList;

class Ennemi extends Vaisseau{

    public static final int VISE=0;
    public static final int DROIT=1;
    public static final int ALEATOIRE=2;

    private ArrayList<Long> tempsIntermediaires;
    private ArrayList<Point> positions;
    private int orientationTir;
    private int pointsRecompense;
    private int puissanceTir;

    public Ennemi(String str, int ppointDeVie, int vvitesseTir, long ccadenceTir, int ppuissanceTir, int oorientationTir, int ttypeArme, int ttypeArmeNbMunition, int ttypeArmeEcartMunition, ArrayList<Point> ppositions, ArrayList<Long> ttempsIntermediaires, int ppointsRecompense, int ttailleX, int ttailleY){
	//Le paramètre de vitesse peut être mis à n'importe quoi car c'est le scénario qui donnera la vitesse des ennemis. On met 1 pour remplir.
	super(str, new Point(-100000,0), ppointDeVie, vvitesseTir, ccadenceTir, ttypeArme, ttypeArmeNbMunition, ttypeArmeEcartMunition, ttailleX, ttailleY);
	positions = new ArrayList<Point>(ppositions);
	tempsIntermediaires= new ArrayList<Long>(ttempsIntermediaires);
	orientationTir=oorientationTir;
	pointsRecompense=ppointsRecompense;
	puissanceTir=ppuissanceTir;
    }

    public void majPosition(long tps){
	if(tps>tempsIntermediaires.get(0) && tps<tempsIntermediaires.get(tempsIntermediaires.size()-1)){
	    int index=1;
	    while(tps>tempsIntermediaires.get(index))
		index++;
	    index--;
	    long delta=tempsIntermediaires.get(index+1)-tempsIntermediaires.get(index);
	    long petitDelta=tps-tempsIntermediaires.get(index);
	    double coeff=(petitDelta*1.0)/(delta*1.0);
	    int deltaX=positions.get(index+1).getX()-positions.get(index).getX();
	    int deltaY=positions.get(index+1).getY()-positions.get(index).getY();
	    setA(new Point((int)(positions.get(index).getX()+coeff*deltaX),(int)(positions.get(index).getY()+coeff*deltaY)));
	}
    }

    public boolean actif(long tps){
	if(tps>tempsIntermediaires.get(0) && tps<tempsIntermediaires.get(tempsIntermediaires.size()-1))
	    return true;
	return false;
    }

    //Retourne vrai si l'ennemi a été actif et a fini son travail
    public boolean enRetraite(long tps){
	return(tps>tempsIntermediaires.get(tempsIntermediaires.size()-1));
    }

    public boolean visible(){
	//On s'assure que l'ennemi est visible au moins au quart
	Rectangle r = new Rectangle(new Point(-getLargeur()/2,-getHauteur()/2),new Point(tailleX+getLargeur()/2,tailleY-20+getHauteur()/2));
	return (getA().getX()-r.getA().getX()>=0 && getA().getY()-r.getA().getY()>=0 && r.getB().getX()-getB().getX()>=0 && r.getB().getY()-getB().getY()>=0);
    }

    public ArrayList<TirEnnemi> getTir(long tps, Point d){
	ArrayList<TirEnnemi> retour = new ArrayList<TirEnnemi>();
	Point origine=new Point(getA().getX()+getLargeur()/2,getA().getY());
	if(getAutorisationTir(tps) && actif(tps) && visible()){
	    if(typeArme==TIR_UNIQUE){
		if(orientationTir==VISE){
		    double longueur=(origine.longueur(d))/(tailleY);
		    retour.add(new TirEnnemi(origine, d, tps, puissanceTir, (int)(longueur*getVitesseTir())));
		}
		if(orientationTir==DROIT){
		    Point destination =new Point(origine.getX(),origine.getY()-tailleY);
		    retour.add(new TirEnnemi(origine, destination , tps, puissanceTir, getVitesseTir()));
		}
		if(orientationTir==ALEATOIRE){
		    double angleAlea=Math.random()*3.1415-(3.1415);
		    double cos=Math.cos(angleAlea)*tailleY;
		    double sin=Math.sin(angleAlea)*tailleY;
		    retour.add(new TirEnnemi(origine, new Point((int)(origine.getX()+cos),(int)(origine.getY()+sin)), tps, puissanceTir, getVitesseTir()));
		}
	    }
	    if(typeArme==PARALLELE){	
		if(orientationTir==VISE){
		    double longueur=(origine.longueur(d))/(tailleY);
		    int dx=origine.getX()-d.getX();
		    int dy=origine.getY()-d.getY();
		    int echang=-dy;
		    dy=dx;
		    dx=echang;
		    double norme=Math.sqrt(dx*dx+dy*dy);
		    double ddx=dx/norme;
		    double ddy=dy/norme;
		    ddx=ddx*typeArmeEcartMunition;
		    ddy=ddy*typeArmeEcartMunition;
		    if(typeArmeNbMunition%2==0){
			for(int i=0;i<typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)((i+0.5)*ddx),origine.getY()+(int)((i+0.5)*ddy)), new Point(d.getX()+(int)((i+0.5)*ddx),d.getY()+(int)((i+0.5)*ddy)), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(-(i+0.5)*ddx),origine.getY()+(int)(-(i+0.5)*ddy)), new Point(d.getX()+(int)(-(i+0.5)*ddx),d.getY()+(int)(-(i+0.5)*ddy)), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			}
		    }
		    else{
			retour.add(new TirEnnemi(origine, d, tps, puissanceTir, (int)(longueur*getVitesseTir())));
			for(int i=1;i<=typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(i*ddx),origine.getY()+(int)(i*ddy)), new Point(d.getX()+(int)(i*ddx),d.getY()+(int)(i*ddy)), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(-i*ddx),origine.getY()+(int)(-i*ddy)), new Point(d.getX()+(int)(-i*ddx),d.getY()+(int)(-i*ddy)), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			}
		    }
		}
		if(orientationTir==DROIT){
		    double longueur=(origine.longueur(new Point(origine.getX(),0)))/(tailleY);
		    Point destination = new Point(origine.getX(),origine.getY()-tailleY);
		    if(typeArmeNbMunition%2==0){
			for(int i=0;i<typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point((int)(origine.getX()+(i+0.5)*typeArmeEcartMunition),origine.getY()), new Point((int)(origine.getX()+(i+0.5)*typeArmeEcartMunition), destination.getY()), tps, puissanceTir, getVitesseTir()));
			    retour.add(new TirEnnemi(new Point((int)(origine.getX()-(i+0.5)*typeArmeEcartMunition),origine.getY()), new Point((int)(origine.getX()-(i+0.5)*typeArmeEcartMunition), destination.getY()), tps, puissanceTir, getVitesseTir()));
			}
		    }
		    else{
			retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			for(int i=1;i<=typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point(origine.getX()+i*typeArmeEcartMunition,origine.getY()), new Point(origine.getX()+i*typeArmeEcartMunition,destination.getY()), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			    retour.add(new TirEnnemi(new Point(origine.getX()-i*typeArmeEcartMunition,origine.getY()), new Point(origine.getX()-i*typeArmeEcartMunition,destination.getY()), tps, puissanceTir, (int)(longueur*getVitesseTir())));
			}
		    }
		}
		if(orientationTir==ALEATOIRE){
		    double angleAlea=Math.random()*3.1415-(3.1415);
		    double cos=Math.cos(angleAlea)*tailleY;
		    double sin=Math.sin(angleAlea)*tailleY;
		    d = new Point((int)(origine.getX()+cos),(int)(origine.getY()+sin));
		    int dx=origine.getX()-d.getX();
		    int dy=origine.getY()-d.getY();
		    int echang=-dy;
		    dy=dx;
		    dx=echang;
		    double norme=Math.sqrt(dx*dx+dy*dy);
		    double ddx=dx/norme;
		    double ddy=dy/norme;
		    ddx=ddx*typeArmeEcartMunition;
		    ddy=ddy*typeArmeEcartMunition;
		    if(typeArmeNbMunition%2==0){
			for(int i=1;i<=typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)((i+0.5)*ddx),origine.getY()+(int)((i+0.5)*ddy)), new Point(d.getX()+(int)((i+0.5)*ddx),d.getY()+(int)((i+0.5)*ddy)), tps, puissanceTir, getVitesseTir()));
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(-(i+0.5)*ddx),origine.getY()+(int)(-(i+0.5)*ddy)), new Point(d.getX()+(int)(-(i+0.5)*ddx),d.getY()+(int)(-(i+0.5)*ddy)), tps, puissanceTir, getVitesseTir()));
			}
		    }
		    else{
			retour.add(new TirEnnemi(origine, d, tps, puissanceTir, getVitesseTir()));
			for(int i=0;i<=typeArmeNbMunition/2;i++){
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(i*ddx),origine.getY()+(int)(i*ddy)), new Point(d.getX()+(int)(i*ddx),d.getY()+(int)(i*ddy)), tps, puissanceTir, getVitesseTir()));
			    retour.add(new TirEnnemi(new Point(origine.getX()+(int)(-i*ddx),origine.getY()+(int)(-i*ddy)), new Point(d.getX()+(int)(-i*ddx),d.getY()+(int)(-i*ddy)), tps, puissanceTir, getVitesseTir()));
			}
		    }
		}
	    }
	    if(typeArme==EVENTAIL){
		if(orientationTir==VISE){
		    //double longueur=(origine.longueur(d))/(tailleY);
		    double deltax=origine.getX()-d.getX();
		    double deltay=origine.getY()-d.getY();
		    //Si les deux vaisseaux sont alignés verticalement - OK
		    if(deltax==0){
			//vaisseau ennemi au dessus
			if(deltay>0){
			    Point destination =new Point(origine.getX(),origine.getY()-tailleY);
			    if(typeArmeNbMunition%2==0){
				for(int i=0;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir,getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir,getVitesseTir()));
				}
			    }
			    else{
				retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				for(int i=1;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
			//vaisseau ennemi en dessous
			else{
			    Point destination =new Point(origine.getX(),origine.getY()+tailleY);
			    if(typeArmeNbMunition%2==0){
				for(int i=0;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			    else{
				retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				for(int i=1;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
		    }
		    else{
			//si les deux vaisseaux sont alignés horizontalement - OK
			if(deltay==0){
			    //vaisseau ennemi à droite
			    if(deltax>0){
				Point destination =new Point(origine.getX()-tailleY,origine.getY());
				if(typeArmeNbMunition%2==0){
				    for(int i=0;i<typeArmeNbMunition/2;i++){
					double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir,getVitesseTir()));
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
				else{
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    for(int i=1;i<=typeArmeNbMunition/2;i++){
					double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
			    }
			    //vaisseau ennemi à gauche
			    else{
				Point destination =new Point(origine.getX()+tailleY,origine.getY());
				if(typeArmeNbMunition%2==0){
				    for(int i=0;i<typeArmeNbMunition/2;i++){
					double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
				else{
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    for(int i=1;i<=typeArmeNbMunition/2;i++){
					double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
			    }
			}
			//vaisseaux non alignés - OK
			else{
			    double angle=Math.atan2(-deltay, -deltax);
			    if(typeArmeNbMunition%2==0){
				for(int i=-typeArmeNbMunition/2;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin(((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    double dy=Math.cos(((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    Point destination=new Point((int)(dy+origine.getX()),(int)(origine.getY()+dx));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			    else{
				for(int i=-typeArmeNbMunition/2;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    double dy=Math.cos((i*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    Point destination=new Point((int)(dy+origine.getX()),(int)(origine.getY()+dx));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
		    }
		}
		if(orientationTir==DROIT){
		    Point destination =new Point(origine.getX(),origine.getY()-tailleY);
		    if(typeArmeNbMunition%2==0){
			for(int i=0;i<typeArmeNbMunition/2;i++){
			    double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			    double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
			    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
			    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			}
		    }
		    else{
			retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			for(int i=1;i<=typeArmeNbMunition/2;i++){
			    double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			    double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
			    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
			    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
			    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
			}
		    }
		}
		if(orientationTir==ALEATOIRE){
		    double angleAlea=Math.random()*3.1415*2;
		    double cos=Math.cos(angleAlea)*tailleY;
		    double sin=Math.sin(angleAlea)*tailleY;
		    d=new Point((int)(origine.getX()+cos),(int)(origine.getY()+sin));
		    //retour.add(new TirEnnemi(origine, d, tps, puissanceTir, getVitesseTir()));
		    //double longueur=(origine.longueur(d))/(tailleY);
		    double deltax=origine.getX()-d.getX();
		    double deltay=origine.getY()-d.getY();
		    //Si les deux vaisseaux sont alignés verticalement - OK
		    if(deltax==0){
			//vaisseau ennemi au dessus
			if(deltay>0){
			    Point destination =new Point(origine.getX(),origine.getY()-tailleY);
			    if(typeArmeNbMunition%2==0){
				for(int i=0;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			    else{
				retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				for(int i=1;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()-dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
			//vaisseau ennemi en dessous
			else{
			    Point destination =new Point(origine.getX(),origine.getY()+tailleY);
			    if(typeArmeNbMunition%2==0){
				for(int i=0;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			    else{
				retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				for(int i=1;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
				    destination=new Point((int)(dx+origine.getX()),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    destination=new Point((int)(origine.getX()-dx),(int)(origine.getY()+dy));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
		    }
		    else{
			//si les deux vaisseaux sont alignés horizontalement - OK
			if(deltay==0){
			    //vaisseau ennemi à droite
			    if(deltax>0){
				Point destination =new Point(origine.getX()-tailleY,origine.getY());
				if(typeArmeNbMunition%2==0){
				    for(int i=0;i<typeArmeNbMunition/2;i++){
					double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
				else{
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    for(int i=1;i<=typeArmeNbMunition/2;i++){
					double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()-dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
			    }
			    //vaisseau ennemi à gauche
			    else{
				Point destination =new Point(origine.getX()+tailleY,origine.getY());
				if(typeArmeNbMunition%2==0){
				    for(int i=0;i<typeArmeNbMunition/2;i++){
					double dx=Math.sin((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
				else{
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    for(int i=1;i<=typeArmeNbMunition/2;i++){
					double dx=Math.sin(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					double dy=Math.cos(i*typeArmeEcartMunition/360.0*3.1415926*2)*tailleY;
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()+dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
					destination=new Point((int)(origine.getX()+dy),(int)(origine.getY()-dx));
					retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				    }
				}
			    }
			}
			//vaisseaux non alignés - OK
			else{
			    double angle=Math.atan2(-deltay, -deltax);
			    if(typeArmeNbMunition%2==0){
				for(int i=-typeArmeNbMunition/2;i<typeArmeNbMunition/2;i++){
				    double dx=Math.sin(((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    double dy=Math.cos(((i+0.5)*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    Point destination=new Point((int)(dy+origine.getX()),(int)(origine.getY()+dx));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			    else{
				for(int i=-typeArmeNbMunition/2;i<=typeArmeNbMunition/2;i++){
				    double dx=Math.sin((i*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    double dy=Math.cos((i*typeArmeEcartMunition/360.0*3.1415926*2)+angle)*tailleY;
				    Point destination=new Point((int)(dy+origine.getX()),(int)(origine.getY()+dx));
				    retour.add(new TirEnnemi(origine, destination, tps, puissanceTir, getVitesseTir()));
				}
			    }
			}
		    }
		}
	    }
	}
	return retour;
    }

    public ArrayList<Long> getTpsIntervalle(){
	return tempsIntermediaires;
    }

    public ArrayList<Point> getPositions(){
	return positions;
    }

    public int getPointsRecompense(){
	return pointsRecompense;
    }

    public int getPuissanceTir(){
	return puissanceTir;
    }

    public String toString(){
	String retour="Ennemi\n"+super.toString()+"\n\tOrientation du tir : ";
	if(orientationTir==VISE)
	    retour=retour.concat("vise");
	if(orientationTir==DROIT)
	    retour=retour.concat("tir droit");
	if(orientationTir==ALEATOIRE)
	    retour=retour.concat("tir dans une direction aléatoire");
	retour=retour.concat("\n\tTemps/position :");
	for(int i=0;i<positions.size();i++)
	    retour=retour.concat("\n\t\tà "+tempsIntermediaires.get(i)+"ms en ("+(positions.get(i).getX()+getLargeur()/2)+","+(positions.get(i).getY()+getHauteur()/2)+")");
	
	return retour;
    }
}
