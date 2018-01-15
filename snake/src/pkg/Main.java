package pkg;

import java.util.ArrayList;

import MG2D.Fenetre;
import MG2D.geometrie.Couleur;
import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;

public class Main{
	
	public static void main(String args[]){
		
		int maxH = 20, maxV =20;
		MG2D test = new MG2D();
		
		Snake serpent = new Snake();
		
		ArrayList<Rectangle> grilledejeu = new ArrayList();
		ArrayList<Position> serpentpos = serpent.getPos();
		
		int matrice_grille[][]=new int[maxH][maxV];
		
		Fenetre f = new Fenetre("Snake            Primitives:  ", 800, 700);
		
		for (int i=0; i<maxH; i++){
			for (int j=0; j<maxV; j++){
				
				grilledejeu.add(new Rectangle(Couleur.NOIR,
						new Point(50+(30*i),600-(30*j)),
						new Point(80+(30*i),630-(30*j)), false));
				f.ajouter(grilledejeu.get(grilledejeu.size()-1));
				matrice_grille[i][j]=0;
			}
		}		
		
		f.rafraichir();
		
		while(true){				
			try{
				Thread.sleep(1000);		
			}
				catch(Exception e){}
			
			//for(int i=0;i<serpent.getPos().size();i++)

					f.rafraichir();
			
			for(int i=0; i<serpentpos.size(); i++){
				int posx = serpentpos.get(i).getPositionX();
				int posy = serpentpos.get(i).getPositionY();
				matrice_grille[posx][posy]=1;
				
			}
			System.out.println(serpent);
			serpent.nextMove();
			System.out.println(serpent);
			
			for(int i=0; i<20; i++){
				for(int j=0; j<20; j++){
					
					if(matrice_grille[i][j]==1){
					
						grilledejeu.get((maxV*i)+j).setCouleur(Couleur.VERT);	
						grilledejeu.get((maxV*i)+j).setPlein(true);
					}
					
					if(matrice_grille[i][j]==0){
						grilledejeu.get((maxV*i)+j).setCouleur(Couleur.NOIR);	
						grilledejeu.get((maxV*i)+j).setPlein(false);
	
					}
				
				}
			}
		
			}
	}
}

