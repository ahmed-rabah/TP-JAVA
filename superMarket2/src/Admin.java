public class Admin extends Person{
	private String password ; 
	
	  public Admin(String password) {
	    	super(); 
	    	 this.password = password ; 
	    }
	    public Admin(String nom,String password) {
	    	super(nom) ; 
	    	this.password = password ; 
	    }
	    public Admin(String id   , String nom , String password) {
	        super(id,nom) ;
	    	this.password = password ; 
	    }
	    
	    public Boolean isAdmin(String nom , String password) {
	    	return (this.getNom().equals(nom) && this.password.equals(password) )  ; 
	
	    }
}
