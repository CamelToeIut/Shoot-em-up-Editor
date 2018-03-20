

import MG2D.geometrie.*;
import java.util.ArrayList;

public class Maine{

	public static void main(String[] args){
		BDeditor test = new BDeditor();
		ArrayList<Point> ppositions = new ArrayList<>();
		ArrayList<Long> ttempsIntermediaires = new ArrayList<>();

		test.parametreJeu(30, "BONDOUR", 2, 800, 600);
		test.ajoutJoueurXML("BONDOUR", "BONDOUR.hitbox", 2000, 10, 20, 20, 50, 30, 100);
		test.modifierJoueurXML("BONDOUR2", "BONDOUR2.hitbox", 2000, 10, 20, 20, 50, 30, 100);
		ppositions.add(new Point(5,5));

		ppositions.add(new Point(5,5));
		ppositions.add(new Point(5,5));
		
		ttempsIntermediaires.add(new Long(9000));
		ttempsIntermediaires.add(new Long(9000));
		ttempsIntermediaires.add(new Long(9000));
		
		test.ajouterEnnemi("img/avion1.png",3,4,8,12,0,0,1,1,ppositions,ttempsIntermediaires,1,1,1);
		test.ajouterEnnemi("img/avion2.png",22,11,6,4,1,1,1,1,ppositions,ttempsIntermediaires,2,1,1);
		test.ajouterEnnemi("img/avion3.png",83,4,7,21,2,2,1,1,ppositions,ttempsIntermediaires,3,1,1);
		
		test.ajoutEnnemiXML();
		
		test.enregistrerFichier("test");
	}
	
}
