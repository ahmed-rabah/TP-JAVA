import java.util.ArrayList;
import java.util.UUID;

public class Transaction {
	
	private String idTransaction ; 
	private Client client ; 
	private ArrayList<TransactionItem> Items ; 
	
	public Transaction(Client cl, ArrayList<TransactionItem> liste) {
		this.idTransaction = generateRandomId() ; 
		this.client = cl ; 
		this.Items = liste ;
	}
	public Transaction(String id , Client cl, ArrayList<TransactionItem> liste) {
		this.idTransaction = id; 
		this.client = cl ; 
		this.Items = liste ;
	}
	
	public float TotalPrice() {
		float total =0  ; 
		for (TransactionItem Item : Items) {
			total+= Item.getPrix() ; 
		}
		return total ; 
	}
	public void affiche() {
		System.out.println(("Transaction N° :"+this.idTransaction+" du "+client.getNom()).toUpperCase());
		System.out.println("les elements du transaction ::");
		for (TransactionItem Item : Items) {
			Item.affiche();
		}
		System.out.println("PRIX TOTAL:: DU TRANSACTION : "+this.TotalPrice()+" DH");
	}
	public String ToString() {
		String ls  =""; 
		for (TransactionItem Item : Items) {
		ls+= this.idTransaction+","+this.client.ToString()+","+Item.ToString()+"\n"; 
		}
		return ls ; 
	}
	  private String generateRandomId() {
	        UUID uuid = UUID.randomUUID();
	        return uuid.toString();
	    }

	public ArrayList<TransactionItem> getItems() {
		return Items;
	}

	public void setItems(ArrayList<TransactionItem> items) {
		Items = items;
	}

	public String getIdTransaction() {
		return idTransaction;
	}
	  
}
