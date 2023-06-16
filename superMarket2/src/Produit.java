
public class Produit {
	private static int  idCount = 1 ; 
	private int idProduit; 
	private String nomProduit;
	private int Qte ; 
	private float prix ; 
	
	//constructors
	public Produit() {
		this.idProduit =  idCount++ ; 
		this.nomProduit ="product" ; 
		this.Qte = 10 ; 
		this.prix = 100 ; 
	}
	public Produit(String nom , int qte , float prix) {
		this.idProduit = idCount++ ; 
		this.nomProduit  = nom ; 
		this.Qte = qte ; 
		this.prix = prix ; 
	}
	public Produit(int id, String nom,int qte , float prix) {
		this.idProduit = id ; 
		this.nomProduit  = nom ; 
		this.Qte = qte ; 
		this.prix = prix ; 
	}

	//getters & setters
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	public int getQte() {
		return Qte;
	}
	public void setQte(int qte) {
		Qte = qte;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getIdProduit() {
		return idProduit;
	}
	
	//affichage
	public void affiche() {
		System.out.println("nom produit  : "+this.nomProduit+" || prix unitaire : "+this.prix+"dh || quantité en stock :"+this.Qte);
	}
	public String ToString() {
		return this.getIdProduit()+","+this.nomProduit+","+this.prix+","+this.Qte;
	}
}
