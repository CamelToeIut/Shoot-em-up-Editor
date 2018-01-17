package pkg;
/** Class qui représente un effet.
	Les attributs sont le type de type char et la valeur de type int */
public class Effect{
	/** Type de type char. */
    private char type;
    /** Value de type int. */
    private int value;
    	public Effect(){
		type = 't';
	    value  = 2; 
	}
	/** Constructeur par copie.
		@param type de type char
		@param value de type int */
	public Effect(char type, int value){
		this.type = type;
		this.value = value;
	}
	/** Setter Effectvalue.
		@param value de type int*/
	public void setEffectvalue(int value){
		this.value = value;
	}
	/** Setter Effecttype.
		@param type de type char*/
	public void setEffecttype(char type){
		this.type = type;
	}
	/** Getter getEffecttype.
		@return le type */
	public int getEffecttype(){
		return type;
	}
	/** Getter getEffectvalue.
		@return la valeur */
	public int getEffectvalue(){
		return value;
	}
	/** Méthode toString.
		@return le type et la valeur */
	public String toString(){
		return "[Type : " + type +"|  Value: " + value + "]";
	} 
}
