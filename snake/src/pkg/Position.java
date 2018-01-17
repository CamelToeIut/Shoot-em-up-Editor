package pkg;
/** Class qui représente une position.
	les attributs sont deux entiers qui représentent l'abscisse et l'ordonnée */
public class Position{
	/** X de type entier. */
	private int x;
	/** Y de type entier. */
	private int y;
	private Contenu contenu;
	/* Constructeur Position mettant X et Y a 0. */
	public Position(){
		x = 0;
		y = 0;
		contenu = Contenu.valueOf("Vide");
	}
	/** Constructeur par copie.
		@param x de type entier
		@param y de type entier  */ 
	public Position(int x, int y, Contenu contenu){
		this.x = x;
		this.y = y;
		this.contenu = contenu;
	}

	public Position(int x, int y){
		this.x = x;
		this.y = y;
	
	}

	/**  Setter de position X.
		@param x de type entier  */
	public void setPositionX(int x){
		this.x = x;
	}
	/** Setter de Y.
		@param y de type entier */
	public void setPositionY(int y){
		this.y = y;
	}
	public void setContenu(Contenu contenu){
		this.contenu = contenu;
	}
	/** Getter de la position de X.
		@return la position de X */
	public int getPositionX(){
		return x;
	}
	/** Getter de la position de Y.
		@return la position de Y */
	public int getPositionY(){
		return y;
	}
	public Contenu getContenu(){
		return contenu;
	}
	/** Méthode toString.
		@return La position de X et la position de Y */
	public String toString(){
		return "[Position X : " + x +"| Position Y : " + y + "| Contenu : " + contenu +"]";
	} 
}
