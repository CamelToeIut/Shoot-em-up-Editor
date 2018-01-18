package pkg;
import MG2D.*;
import MG2D.audio.Bruitage;
import MG2D.geometrie.Couleur;
import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.geometrie.*;

public class MG2D{

	public MG2D(){
		
	}
	
	public void initPlateau(){
		
		Fenetre f = new Fenetre("Snake            Primitives:  ", 973, 700);
		
		
		
		for (int i=0; i<70; i++){
			for (int j=0; j<70; j++){
				
				
				
				Rectangle casevide = new Rectangle(Couleur.NOIR,
						new Point(9+(10*i),692-16-(10*j)),
						new Point(17+(10*i),700-16-(10*j)),
						true);
	
			}
		}
	}
	
}
