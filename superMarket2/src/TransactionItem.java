

public class TransactionItem {
	
		private Produit produit ; 
		private int Qte  ; 
		private float prix ; 
		
		public TransactionItem(Produit p  , int qte) {
			this.produit = p ; 
			this.Qte = qte ; 
			this.prix = p.getPrix()*qte ; 
		}
		
		public void affiche() {
			System.out.println(" produit : "+produit.getNomProduit()+" || quantité  : "+this.Qte+"  || prix total   :"+prix);
		}
		public String ToString() {
			return produit.ToString()+","+this.Qte+","+prix;
		}
		

		public Produit getProduit() {
			return produit;
		}

		public void setProduit(Produit produit) {
			this.produit = produit;
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
	
		
}
