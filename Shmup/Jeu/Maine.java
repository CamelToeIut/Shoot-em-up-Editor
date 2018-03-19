

import MG2D.geometrie.*;
import java.util.ArrayList;

public class Maine{

	public static void main(String[] args){
		BDeditor test = new BDeditor();
		ArrayList<Point> ppositions = new ArrayList<>();
		ArrayList<Long> ttempsIntermediaires = new ArrayList<>();
		ppositions.add(new Point(5,5));
		ppositions.add(new Point(5,5));
		ppositions.add(new Point(5,5));
		
		ttempsIntermediaires.add(new Long(9000));
		ttempsIntermediaires.add(new Long(9000));
		ttempsIntermediaires.add(new Long(9000));
		
		test.ajouterEnnemi("img/avion1.png",1,1,1,1,1,1,1,1,ppositions,ttempsIntermediaires,1,1,1);
		test.ajouterEnnemi("img/avion1.png",1,1,1,1,1,1,1,1,ppositions,ttempsIntermediaires,1,1,1);
		test.ajouterEnnemi("img/avion1.png",1,1,1,1,1,1,1,1,ppositions,ttempsIntermediaires,1,1,1);
		
		test.initialiserJoueur("img/avion1.png",new Point(10,10),1,1,1,1,1,1,1,1,1);
		
		test.ajoutJoueurXML("img/avion1.png");
		test.ajoutEnnemiXML();
		
		test.enregistrerFichier("test");
	}
	
}
