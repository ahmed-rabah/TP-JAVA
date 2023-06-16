import java.util.UUID;

public class Person {
	private static int counter=0 ; 
    protected String id;
    protected String nom ; 
  
    //constructors
    public Person() {
        this.id = generateRandomId();
        this.nom= "Person"+counter ; 
    }
    public Person(String nom) {
        this.id = generateRandomId();
        this.nom= nom ; 
    }
    
    public Person(String id   , String nom) {
        this.id= id;
        this.nom= nom ; 
    }
    
    private String generateRandomId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString()+(++counter);
    }
    
    //getters & setters
    
    
    //afichage
    public void affiche() {
 	   System.out.println("id : "+this.id+" ||| nom  : "+this.nom);
    }
    
    public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getId() {
		return id;
	}
	public String ToString() {
 	   return this.id+","+this.nom; 
    }
}