import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supermaket market = new Supermaket("hemza","ahmed","rabah") ; 
		Scanner sc = new Scanner(System.in);
		System.out.println("****************\nbonjour\n****************" );
		String nomAdmin = ""  , password ="";  
		char c ='o' ; 
		do {
			System.out.println("entrez nom d'Admin :");
			nomAdmin =sc.nextLine();
			System.out.println("entrez le mot de passe :");
			password = sc.nextLine() ; 
			if(!market.checkAdmin(nomAdmin,password)) {
				
				System.out.println("inforamtions invalide\nressayer à nouveau tappez : (o)\npour sortir du systeme tappez (n)");
				c = sc.nextLine().charAt(0) ; 
				if(c != 'o') {
					System.out.println("au revoir");
					sc.close();
					return  ; 
				}
			}
			
		}while(!market.checkAdmin(nomAdmin,password)) ; 
		System.out.println("bonjour ADMIN : "+nomAdmin);
		
		market.AdminChoices();
		
		sc.close();		
	}

}
