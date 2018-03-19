

import MG2D.geometrie.Rectangle;
import MG2D.geometrie.Couleur;
import MG2D.geometrie.Point;

class BarreDeVie extends Rectangle{

    private int maxPDV;
    private double coeffBarreDeVie;
    
    public BarreDeVie(int max, int valeurCourante, double coeff, int ttailleY){
	super(Couleur.VERT,new Point(10,ttailleY-15), (max/50) ,10,true);
	coeffBarreDeVie=coeff;
	maxPDV=max;
	maj(valeurCourante);
    }

    public void maj(int valeurCourante){
	double coeff=(valeurCourante*1.0)/maxPDV;
	double coeffInv=1-coeff;
	double r=0,v=0;
	if(coeffInv==0){r=0;v=1;}
	if(coeffInv>0 && coeffInv<=0.33){r=(coeffInv/0.33);v=1;}
	if(coeffInv>0.33 && coeffInv<=0.66){r=1;v=1-((coeffInv-0.33)/0.66);}
	if(coeffInv>0.66){r=1;v=coeff/0.66;}
	if(r<0) r=0;
	if(v<0) v=0;
	setLargeur((int)(((coeff)*maxPDV)/coeffBarreDeVie));
	setCouleur(new Couleur((int)(255*r),(int)(255*v),0));
    }
}
