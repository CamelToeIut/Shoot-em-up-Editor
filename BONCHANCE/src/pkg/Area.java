package pkg;

public class Area{
	/** size de type int */
	private int size;
	/** tableau de type postion */
	private Position[][] tab = new Position[size][size];
	/** class qui represente Area.
		Les attributs sont size de type int et un tableau de type Position */
	public Area(){

		size = 0;
		for(int i=0; i<size; i++){
			for(int j = 0; j<size; j++){
				tab[i][j]=new Position(i,j,Contenu.Vide);
			}
		}
	}
	/** Constructeur par copie.
		@param size de type int */
	public Area(int size){
		this.size = size;
		tab = new Position[size][size];
		for(int i=0; i<size; i++){
			for(int j = 0; j<size; j++){
				tab[i][j]=new Position(i,j,Contenu.Vide);
			}
		}
	}
	/** Setter setSize
		@param size de type int */
	public void setSize(int size){
		this.size = size;
	}
	/** Setter SetTab. */
	public void setTab(){
		for(int i=0; i<size; i++){
			for(int j = 0; j<size; j++){
				tab[i][j]=new Position(i,j,Contenu.Vide);
			}
		}
	}
	/** Getter getSize.
		@return la taille de l'area */
	public int getSize(){
		return size;
	}
	/** Méthode toString.
		@return la taille de l'areaa */
	public String toString(){
		return "[Size : " + size +"]";
	}
}
