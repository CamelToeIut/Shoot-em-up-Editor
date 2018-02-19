import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		BDeditor test = new BDeditor();
		boolean aoub = true;
		while(aoub){
			System.out.println("A ou laisse passer");
			Scanner sc = new Scanner(System.in);
			String va = sc.next();
			if(va=="a"){
				test.nouveauEnnemi();
			}else{
				aoub = false;
			}
			
		}
		test.EnregistrerFichier();
	
	}
}

