import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Version2 {

	public static String[] unite = {"zéro" , "un" , "deux" , "trois" , "quatre" , "cinq" , "six" , "sept" , "huite" , "neuf"};
	public static String[] tenCounting = {"","onze","douze","treize","quatorze","quinze","seize"};
	public static String[] tens= {"", "dix", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingt", "quatre-vingt-dix"};
	public static String[] bigUnit  = {"","mille", "million", "milliard", "billion", "billiard", "trillion", "trilliard", "quadrillion", "quadrilliard", "quintillion", "quintilliard"};

	public static String[] multipliers = {"Double", "Triple", "Quadruple", "Quintuple", "Sextuple", "Septuple", "Octuple", "Nonuple", "Decuple"};


	public static  int extractNum(int x) {
		return x%1000 ; 
	}
	public static int extractNewNum(int x) {
		return  (x - ( x% 1000))/1000 ;
		
	}
	public static String fractionZero(String number) {
		if(number.charAt(0) != '0') return "";
		int  count =0;
		for(char c  : number.toCharArray()) {
			if(c =='0') count++ ; 
		}
		if(count ==1) return "zéro et";
		if(count >1 && count<=10) return multipliers[count-2]+ "-ZEROS et" ; 
		else return NumberToTextLessThanThousand(count)+"-ZEROS et";
	}
	public static String DoubleToText(double number) {
		int integerPart = (int) number; 
		String str = Double.toString(number)  ; 
		int fraction =Integer.parseInt(str.substring(str.indexOf(".")+1) );
		String fractionZeros = fractionZero(str.substring(str.indexOf(".")+1)) ; 
		if(integerPart == 0 && fraction ==0) return "zéro" ; 
		if(fraction== 0 && integerPart !=0) return IntToText(integerPart);
		return IntToText(integerPart)+"  VIRGULE  "+fractionZeros+""+IntToText(fraction); 
	}
	public static String  IntToText(int number) {
		if(number==0) return unite[0];
		String Text ="";
		int num =  String.valueOf(number).length();
		int size = num/3 ; 
		if(num % 3 != 0) size +=1 ; 
		List<Integer> numbers  = new ArrayList<Integer>();
		for(int i = 0  ; i < size ; i++) {
			numbers.add(extractNum(number)) ; 
			number = extractNewNum(number) ; 
		}
	
		for (int i = numbers.size()-1 ; i >= 0 ; i--) {
			String result =NumberToTextLessThanThousand( numbers.get(i));
			String element="" , and =" et ";
			if (i == numbers.size()-1) and =" " ; 
			if(result.equals("un") && numbers.size() !=1) result="";
			if( result.equals("zéro")) {
				Text+=" ";
			}
			else {	
		     element = and+result+" "+bigUnit[i].toUpperCase();
		     Text+=element;
			}
		}
		return Text ; 
	}
	
	public static String NumberToTextLessThanThousand(int number) {
		int num =  String.valueOf(number).length();
		String text ="" ; 
		if(number >=0 && number <=9) return unite[number] ; 
		if(num <= 3) {
			int centaine  = number / 100 ; 
			int dizaine = (number % 100) /10 ; 
			int unit = number % 10  ; 
			if(centaine > 0) {
			
				text += ((centaine==1)? ("") : (unite[centaine]+"-"))+"cent" ; 
			}
			if(dizaine == 1 &&  unitDixVerification(unit) ) {
				text+=" "+tenCounting[unit];
			}else if((dizaine == 7 || dizaine == 9) && unitDixVerification(unit) ) {
				text+=" "+tens[dizaine-1]+"-"+tenCounting[unit]  ; 
			}else {
				String dash = (dizaine == 0) ?("") :("-") ; 
				text += (unit==0) ? ( " "+tens[dizaine]) : (unit==1) ? (" "+tens[dizaine]+"-et-"+unite[unit]) :  ( " "+tens[dizaine]+dash+unite[unit]);
				
			}
			return text ;
		}
		
		return "probleme de conversion" ; 
	}
	public static void afficheConvertion(double number) {
		System.out.println(number+" ====> "+DoubleToText(number));
	}
	public static Boolean unitDixVerification(int x) {
		int[] numbers = {1,2,3,4,5,6} ; 
		 for (int i = 0; i < numbers.length; i++) {
	            if (numbers[i] == x) {
	                return true;
	             }
	        }
		 return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double Number ;
		Scanner sc =new  Scanner(System.in); 
		do{			
		System.out.println("entrez un nombre(tappez une lettre pour  terminer)");	
		try {			
		Number = sc.nextDouble();
		
		afficheConvertion(Number);
		} catch (InputMismatchException e) {
				System.out.println("Au revoir!!!");
				sc.close();
				break; 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(true); 
	
 
	}
}
