import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		BDeditor test = new BDeditor();
		boolean aoub = true;
		while(aoub){
			System.out.println("A ou laisse passer");
			Scanner sc = new Scanner(System.in);
			int va = sc.nextInt();
			if(va==1){
				test.ajouterEnnemi();
			}else{
				aoub = false;
			}
			
		}
		test.supprimerEnnemi();
		test.EnregistrerFichier();
	
	}
}

