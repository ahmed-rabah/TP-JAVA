

public class Client extends Person{

  
    //constructors
    public Client() {
    	super(); 
    }
    public Client(String nom) {
    	super(nom) ; 
    }
    
    public Client(String id   , String nom) {
        super(id,nom) ;
    }


  //affichage()
   public void affiche() {
	   System.out.println("id : "+this.id+" ||| nom client : "+this.nom);
   }
   

    
}