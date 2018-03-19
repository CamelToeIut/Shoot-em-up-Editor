

class Main{
    public static void main(String[] args){
	
	if(args.length!=1){
	    System.out.println("Le jeu se lance avec un paramètre : le fichier xml du niveau");
	    System.exit(0);
	}
	
	Shmup sh = new Shmup(args[0]);
	
	while(!sh.getPerdu() && !sh.getGagne()){
	    sh.tempo();
	    sh.maj();
	}
	
	sh.afficheStats();
    }
}
