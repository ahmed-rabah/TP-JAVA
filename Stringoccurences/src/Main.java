import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {

//	Exercice 1 :
//		Écrivez un programme Java qui demande à l'utilisateur de saisir une chaîne de caractères et qui affiche
//		le nombre d'occurrences de chaque lettre dans cette chaîne, en ignorant les espaces et la casse.

	public static int LettreInString(String x , char c) {
		int  somme = 0 ; 
		for (char ch : x.toCharArray()) {
			if(c == ch) {
				somme++; 
			}
		}
		return somme; 
	}
	public static void occurence(String str){
		HashMap<Character, Integer>  map = new HashMap<Character, Integer>();
		str  = str.toLowerCase().replace(" ",""); 
		for (char c : str.toCharArray()) {
			map.put(c,LettreInString(str,c)) ;
		}
		for (Entry<Character, Integer> entry : map.entrySet()) {
		    System.out.println("character: " + entry.getKey() + " =====>  " + entry.getValue());
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =  new Scanner(System.in) ; 
		char c ; 
		do {
			System.out.println("*****entrez une phrase*****");
			String str = sc.nextLine() ; 
			occurence(str) ; 		
			System.out.println("pour continuer tappez 'o' ");
			System.out.println("pour arreter tappez 'n' ");
			c = sc.nextLine().charAt(0) ;
		}while(c == 'o') ;

		System.out.println("*****Au revoir*****");
		sc.close();
	}

}
