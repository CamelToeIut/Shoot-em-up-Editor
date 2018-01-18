package pkg;

import java.util.ArrayList;
import MG2D.Fenetre;
import MG2D.geometrie.Couleur;
import MG2D.geometrie.Point;
import MG2D.geometrie.Rectangle;
import MG2D.*;



public class Main{
	
	public static void main(String args[]){
		
		int maxH = 20, maxV =20, posxtest=1;
					

		Fenetre f = new Fenetre("Snake            Primitives:  ", 800, 700);

		Snake serpent = new Snake();
		Clavier clavier = new Clavier();
		clavier = f.getClavier();
		String direction = new String("B");
		int compteur=0;
		boolean pomme=false;
		int posx = 1;
		int posy = 1;
		int taille=3, score=0;
		int randomx=0,randomy=0;

		ArrayList<Rectangle> grilledejeu = new ArrayList<>();
		ArrayList<Position> serpentpos = new ArrayList<>();
		
		int matrice_grille[][]=new int[maxH][maxV];
	
		
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
				Thread.sleep(400);		
			}
				catch(Exception e){}
			
			//for(int i=0;i<serpent.getPos().size();i++)

					f.rafraichir();
			
			

		/*	for(int i=0; i<serpentpos.size(); i++){

				matrice_grille[posxtest][2]=1;
				
			}*/

			//posxtest++;

		if (clavier.getHautEnfoncee()){
			direction="H";
		}
		
		if(!direction.equals("H")){
		
			if (clavier.getBasEnfoncee()){
				direction="B";
			}
		}
		
		if (clavier.getDroiteEnfoncee()){		
			direction="D";	
		}

		if (clavier.getGaucheEnfoncee()){
			direction="G";	
		}
		
					
		
		System.out.println(direction);
		//System.out.println(posx);
		compteur++;
		try{
		
		if(direction.equals("B")){
			
			posy++;
			System.out.println(posy);
			matrice_grille[posx][posy]=1;
			//matrice_grille[posx][posy-3]=0;
			serpentpos.add(new Position(posx, posy));

		}
		System.out.println(serpentpos);
		
		if(direction.equals("H")){
			
			posy--;
			System.out.println(posx);
			//matrice_grille[posx][posy]=1;
			//matrice_grille[posx][posy+3]=0;
			serpentpos.add(new Position(posx, posy));
		}

		if(direction.equals("G")){
			
			posx--;
			System.out.println(posy);
			//matrice_grille[posx][posy]=1;
			//matrice_grille[posx+3][posy]=0;
			serpentpos.add(new Position(posx, posy));
		}
		if(direction.equals("D")){
			
			posx++;
			System.out.println(posy);
			//matrice_grille[posx][posy]=1;
			//matrice_grille[posx-3][posy]=0;
			serpentpos.add(new Position(posx, posy));

		}

		for(int i=0; i<serpentpos.size(); i++){
			try{
			matrice_grille[serpentpos.get(i).getPositionX()][serpentpos.get(i).getPositionY()]=1;
			//while(serpentpos.size()>3){
				for(int j=0; j<serpentpos.size()-taille; j++){
					matrice_grille[serpentpos.get(j).getPositionX()][serpentpos.get(j).getPositionY()]=0;
					serpentpos.remove(j);
					
					
				}
			//}
			}catch(IndexOutOfBoundsException e){}
				
		}		

		}catch(ArrayIndexOutOfBoundsException e){System.exit(1);}

		if (pomme==false){
			randomx = ((int)(Math.random() * (20)));
			randomy = ((int)(Math.random() * (20)));
			matrice_grille[randomx][randomy]=2;
			pomme=true;

		}
		if(matrice_grille[randomx][randomy]==1){
			pomme=false;
			score=score+100;
			taille++;
		}

		System.out.println(score);	
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

					if(matrice_grille[i][j]==2){
						grilledejeu.get((maxV*i)+j).setCouleur(Couleur.ROUGE);	
						grilledejeu.get((maxV*i)+j).setPlein(true);
				
						
	
					}
					
	
				
				}
			}
			
			

		
		}
	}
}

