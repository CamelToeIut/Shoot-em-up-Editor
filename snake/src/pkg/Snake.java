package pkg;
import java.util.*;
/** Class qui représente Snake.
	L attributs est score de type int */
public class Snake{
	/** Score de type score. */
	private int score;
	/** déclaration de diréction de type direction. */
	private Direction direction;
	/** ArrayList de type position.*/
	private LinkedList<Position> pos = new LinkedList<>();
	/** Constructeur Snake mettant score a 0, direction a nord, et ajoute les différents positions. */
	public Snake(){
		score = 0;
		direction = Direction.S;
		pos.add(new Position(1,1,Contenu.Serpent));
		pos.add(new Position(1,2,Contenu.Serpent));
		pos.add(new Position(1,3,Contenu.Serpent));
	}
	/** Constructeur par copie.
		@param score de type int
		@param direction de type Direction */
	public Snake(int size, int score, Direction direction){
		this.direction = direction;
		this.score = score;
		pos.add(new Position(1,1,Contenu.Serpent));
		pos.add(new Position(1,2,Contenu.Serpent));
		pos.add(new Position(1,3,Contenu.Serpent));
	}
	/** Setter setDirection
		@param direction de type Direction */
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	/** Setter setScore.
		@param score de type Score */
	public void setScore(int score){
		this.score = score;
	}
	/** Getter getDirection.
		@return la direction */
	public Direction getDirection(){
		return direction;
	}
	/** Getter getScore.
		@return le score */
	public int getScore(){
		return score;
	}
	/** Getter getPos.
		@return la position dans l'arryList */
	public ArrayList<Position> getPos(){
		ArrayList<Position> res = new ArrayList<>(pos);

		return res;
	}
	/** Méthode eat.
		Donne la derière position du serpent pour la rajouter  */
	public void eat(){
		int x = pos.get(pos.size()-1).getPositionX();
		int y = pos.get(pos.size()-1).getPositionY();
		pos.add(new Position(x,y+1,Contenu.Serpent));
	}
	/** Méthode nextMove.
		Modifie la position du serpent au prochaine move */
	public void nextMove(){
		int x = pos.get(0).getPositionX();
		int y = pos.get(0).getPositionY();
		if (direction.equals("N")){
			pos.addFirst(new Position(x,y-1,Contenu.Serpent));	
		}
		else if (direction.equals("S")){
			pos.addFirst(new Position(x,y+1,Contenu.Serpent));	
		}
		else if (direction.equals("O")){
			pos.addFirst(new Position(x-1,y,Contenu.Serpent));	
		}
		else if (direction.equals("E")){
			pos.addFirst(new Position(x+1,y,Contenu.Serpent));	
		}
		pos.remove(pos.size()-1);
	}
	/** Méthode gauche.
		Modifie la position du serpent */
	public void gauche(){
		if (direction.equals("N")){
			direction = Direction.valueOf("O");	
		}
		else if (direction.equals("S")){
			direction = Direction.valueOf("E");	
		}
		else if (direction.equals("O")){
			direction = Direction.valueOf("S");		
		}
		else if (direction.equals("E")){
			direction = Direction.valueOf("N");	
		}
	}
	/** Méthode droite.
		Modifie la position du serpent */
	public void droite(){
		if (direction.equals("N")){
			direction = Direction.valueOf("E");	
		}
		else if (direction.equals("S")){
			direction = Direction.valueOf("O");	
		}
		else if (direction.equals("O")){
			direction = Direction.valueOf("N");		
		}
		else if (direction.equals("E")){
			direction = Direction.valueOf("S");	
		}
	}
	/** Méthode toString.
		@return la taille, la direction, le score*/
	public String toString(){
		return "[Direction : " + direction + "Score : " + score + "Pos:" + pos +  "]";
	}
}
