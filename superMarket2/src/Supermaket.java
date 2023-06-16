import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Supermaket {
	private String nomMarket ; 
	private Admin  admin ; 
	private List<Produit> ListProduits ; 
	private List<Client>  ListClients ; 
	private List<Transaction> ListTransactions ; 
	
	//constructor
	public Supermaket( String nom , String nomAdmin , String password) {
		this.nomMarket = nom ; 
		admin  = new Admin(nomAdmin, password) ; 
		ListClients = getClientsFromFile();
		ListProduits = getProduitsFromFile();
		ListTransactions = getTransacrionsFromFile();
	}
	public Supermaket( String nom) {
		Scanner scan = new Scanner(System.in); 
		this.nomMarket = nom ; 
		System.out.println("entrez le nom de l'admin");
		String nomAdmin = scan.next();
		String password = scan.nextLine(); 
		admin  = new Admin(nomAdmin, password) ; 
		ListClients = getClientsFromFile();
		ListProduits = getProduitsFromFile();
		ListTransactions = getTransacrionsFromFile();
		scan.close(); 
	}
	public Supermaket( String nom  , Admin admin) {
		this.nomMarket = nom ; 
		this.admin  = admin ; 
		ListClients = getClientsFromFile();
		ListProduits = getProduitsFromFile();
		ListTransactions = getTransacrionsFromFile();
	}
	
	//addition unitaire 
	public void addProduct(String nom , int qte , float prix) {
		Produit prd = new Produit(nom , qte , prix);
		if(checkProduct(prd.getNomProduit())) {
			System.out.println("Impossible d'ajouter!!! un produit dans la liste porte le meme nom");
			return ; 
		}
		ListProduits.add(prd) ; 
		if(ListProduits.contains(prd)) {
			System.out.println("le produit est ajouté avec succés");
			updateFile();
		}
		else System.out.println("probleme d'ajout du produit");
	}
	public void addClient(String nom) {
		Client cl = new Client(nom);
		if(checkClient(cl)) {
			System.out.println("Client avec l'ID "+cl.getId()+" est déja dans la liste des clients");
			return ; 
		}
		ListClients.add(cl) ; 
		if(ListClients.contains(cl)) {
			System.out.println("le produit est ajouté avec succés");
			updateFile();
		}
		else System.out.println("probleme d'ajout du produit");
	}
	public void addClient() {
		Scanner sc = new Scanner(System.in) ; 
		System.out.println("entrez nom client");
		String nom = sc.next() ; 
		sc.close();
		Client cl = new Client(nom);
		if(checkClient(cl)) {
			System.out.println("Client avec l'ID "+cl.getId()+" est déja dans la liste des clients");
			return ; 
		}
		ListClients.add(cl) ; 
		if(ListClients.contains(cl)) {
			System.out.println("le produit est ajouté avec succés");
			updateFile();
		}
		else System.out.println("probleme d'ajout du produit");
	}
	public TransactionItem addItem(Produit p  , int Qte ) {
		TransactionItem item = new TransactionItem(p , Qte) ; 
		return  item ; 
		//if(Qte > p.getQte()) {
		//	System.out.println("quantité insuffisante , il reste "+p.getQte()+" dans le stock");
		//	return ;
		//}
	//	ListTransactions.add(tr) ; 
		//if(ListTransactions.contains(tr)) System.out.println("l'achat est complet avec succés");
	//	else System.out.println("echec de transaction!!!!!!");
	}
	// ajout multiple
	public void addProducts() {
		Scanner sc = new Scanner(System.in);  
		char encore ='o' ; 
		do {
			System.out.println("entrez le nom du produit : ");
			String nom  ; 
			do {
				nom = sc.next() ; 
				if(!checkProduct(nom)) break ; 
				else System.out.println("nom existe déja dans supermarket");
			}while(true) ;
			
			System.out.println("entrez la quantité");
			int qte = sc.nextInt() ; 
			System.out.println("entrez le prix unitaire du produit ");
			float prix  = sc.nextFloat();
			this.addProduct(nom , qte ,  prix) ;
			
			System.out.println("ajouter un nouveau produit (o/n) : ");
			
			encore = sc.next().charAt(0) ; 
		 
		}while(encore=='o') ; 
		sc.close(); 
		
	}
	public void addClients() {
		Scanner sc = new Scanner(System.in);  
		char encore ='o' ; 
		do {
			System.out.println("entrez le nom du client : ");
			String nom  ; 
			do {
				nom = sc.next() ; 
				if(!checkProduct(nom)) break ; 
				else System.out.println("nom existe déja dans supermarket");
			}while(true) ;
			this.addClient(nom); 
			System.out.println("ajouter un nouveau produit (o/n) : ");
			encore = sc.next().charAt(0) ;  
		}while(encore=='o') ; 
		sc.close(); 
					
	}
	public void addTransaction(Client client) {
		if(!checkClient(client)) {
			System.out.println(client.getNom()+" n'est pas un client");
			return ; 
		}
		Scanner sc = new Scanner(System.in) ; 
		char encore= 'o' ;
		ArrayList<TransactionItem> Items = new ArrayList<TransactionItem>();
		do {
			
			this.displayProduits();
			System.out.println("entrez le nom du produit : ");
			String nom ; 
			do{
				nom = sc.next() ; 
				if(!checkProduct(nom)) System.out.println("produit introuvable");
			}while(!checkProduct(nom)) ;
			Produit p = ListProduits.get(ProductIndex(nom)) ; 
			if(p.getQte() ==0) {
				System.out.println("le stock est vide");
				break ; 
			}
			int qte ; 
			do {
				qte = sc.nextInt();
				if(qte > p.getQte()) {
					System.out.println("quantité insuffisante , il reste "+p.getQte()+" dans le stock");
				}else {
					break ; 
				}
			}while(true) ; 
			TransactionItem el = new TransactionItem(p , qte) ; 
			Items.add(el) ; 
			if(Items.contains(el)) {
				System.out.println("l'element est ajouté au panier");
				p.setQte(p.getQte() - qte);
				ListProduits.set(ProductIndex(nom) , p) ; 
			}else System.out.println("echec d'ajout!!!!!!");
			System.out.println("acheter un autre produit(o) ou terminer la transaction (n)");
			encore = sc.next().charAt(0) ; 
			if(encore == 'o') {
				Transaction tr = new Transaction(client, Items) ; 
				ListTransactions.add(tr) ; 
				if(ListTransactions.contains(tr)) {
					System.out.println("l'achat est complet avec succés");
					updateFile();
				}
				else System.out.println("echec de transaction!!!!!!");
			}
			sc.close();
		}while(encore != 'o') ; 
	}

	//operation unitaire

	public void updateProduct() {
		Scanner sc = new Scanner(System.in) ; 
		System.out.println("entrez le nomù du produit");
		String name = sc.nextLine() ; 
			if(ProductIndex(name) == -1) {
				System.out.println("produit introuvable");
				sc.close();
				return ; 
			}
			int index = ProductIndex(name) ; 
		Produit	p = ListProduits.get(index) ; 
		System.out.println("les informations de ce produit : ");
		p.affiche();
		int i =0  ; 
		do {
			System.out.println("pour modifier:\n\t Nom  :: tapez 1\n\tQuantité :: tappez 2\n\tPrix :: tappez 3\n\tterminer la modification :: tappez 0");			
			i = sc.nextInt() ; 
			if(i ==0) {
				System.out.println("***** modification terminé ******");
				ListProduits.set(index,p) ; 
				updateFile();
				break ; 
			}
			switch (i) {
			case 1:{
				System.out.println("entrez nouveau nom pour le produit");
				String nom ; 
				do{
					nom = sc.nextLine() ;
					if(checkProduct(nom)) System.out.println("nom produit existe déja");
				}while(checkProduct(nom)) ; 
				p.setNomProduit(nom);
				System.out.println("le nom est modifié");
				updateFile();
				break ; 
			}
			case 2 : {
				System.out.println("entrez le nouveau prix du produit  : ");
				float prix ; 
				do{
					prix = sc.nextFloat() ; 
					if(prix <=0 ) System.out.println("veuillez entrez u prix > 0 ");
				}while(prix <= 0) ; 
				p.setPrix(prix);
				System.out.println("le prix est modifié");
				updateFile();
				break ; 
			}
			case 3 : {
				System.out.println("entrez le nouveau prix du produit  : ");
				int qte; 
				do{
					qte = sc.nextInt() ; 
					if(qte <=0 ) System.out.println("veuillez entrez une quantité > 0 ");
				}while(qte <= 0) ; 
				p.setQte(qte);
				System.out.println("la quantité  est modifié");
				updateFile();
				break ; 
			}
			default:				
				break;
			}
				
		}while(true) ; 
		
		sc.close();
	}
	public void deleteProduct() {
		Scanner sc = new Scanner(System.in) ; 	
		System.out.println("entrez le nomù du produit");
		String name = sc.nextLine() ; 
		int index = ProductIndex(name) ; 
		if(index == -1) {
			System.out.println("produit introuvable");
			sc.close();
			return ; 
		}
		ListProduits.remove(index) ; 
		System.out.println("le produit est supprimé");
		updateFile();
		sc.close();
	}
	
	
	// affichage
	public void displayClients() {
		System.out.println("Liste des clients du "+this.nomMarket.toUpperCase()+" superMarket  : ");
		for (Client client : ListClients) {
			client.affiche();
		}
	}
	public void displayProduits() {
		System.out.println("Liste des Produits du "+this.nomMarket.toUpperCase()+" superMarket  : ");
		for (Produit prd : ListProduits) {
			prd.affiche();
		}
	}
	public void displayTransactions() {
		System.out.println("Liste des transactions du  "+this.nomMarket.toUpperCase()+" superMarket  : ");
		for (Transaction Tr : ListTransactions) {
			Tr.affiche() ; 			
		}
	}
	
	public void AdminChoices() {
		int choix = 0  ; 
		do {
			Scanner scan  = new Scanner(System.in) ; 
			System.out.println("*****************************************************");
			System.out.println("tappez : ");
			System.out.println("1 : pour ajouter produit(s)");
			System.out.println("2 : pour ajouter  client(s)");
			System.out.println("3 : pour ajouter  transaction(s)");
			System.out.println("4 : pour modifier produit(s)");
			System.out.println("5 : pour supprimer produit(s)");
			System.out.println("6 : pour afficher la liste des produits");
			System.out.println("7 : pour afficher la liste des clients");
			System.out.println("8 : pour afficher la liste des transactions");
			System.out.println("0 : pour quitter le systeme");
			System.out.println("*****************************************************");

				try {
					choix = scan.nextInt() ; 					
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
					System.out.println(e.getMessage());
					System.out.println(e.getCause());
				}
				
			if(choix == 0)  {
				System.out.println("Au revoir Admin  : "+this.admin.getNom());
				break ; 			
			}
			this.Managechoices(choix) ; 
			scan.close();
		}while(choix != 0) ; 
	}
	public String ClientList() {
		String ls="" ; 
		for (Client client : ListClients) {
			ls += client.ToString()+"\n";
		}
		return ls ; 
	}
	public String ProductList() {
		String ls="" ; 
		for (Produit pr: ListProduits) {
			ls += pr.ToString()+"\n";
		}
		return ls ; 
	}
	public String TransactionList() {
		String ls="" ; 
		for (Transaction tr: ListTransactions) {
			ls += tr.ToString()+"\n";
		}
		return ls ; 
	}
	
	
	//fonctions utilitaires
	public Boolean checkAdmin(String nom , String password) {
		return this.admin.isAdmin(nom, password) ; 
	}
	public Boolean checkClient(Client x) {
		for (Client client : ListClients) {
			if(client.getId() == x.getId()) return true ; 
		}
		return false;
	}
	public Boolean checkProduct(String nom) {
			for (Produit prd : ListProduits) {
				if(prd.getNomProduit() == nom) return true ; 
		}
		
		return false ; 
	}
	public int ProductIndex(String name) {
		for (int i=0 ; i <ListProduits.size() ; i++) {
			if(ListProduits.get(i).getNomProduit().equals(name)) return i ;  
		}
		return -1 ; 
	}
	public int TransactionIndex(String id , ArrayList<Transaction> liste) {
		for (int i=0 ; i<liste.size() ; i++) {
			if(liste.get(i).getIdTransaction() == id) {
				return i ; 
			}
		}
		return -1 ; 
	}
	public int getClientIndexByName(String nom) {
		for (int i=0  ; i<ListClients.size() ; i++) {
			if(ListClients.get(i).getNom().equals(nom)) return i ; 
		}
		return -1 ; 
	}
	public void Managechoices(int choix) {
		switch(choix) {
		case 1 : {
			this.addProducts();
			break  ;
		}
		case 2 : {
			this.addClients();
			break  ;
		}
		case 3 : {
			char c  ;
			do  {
				this.displayClients();
				System.out.println("entrez le nom du client qui va faire la transaction");
				Scanner sc = new Scanner(System.in); 
				String nom  = sc.next() ; 
				int index = this.getClientIndexByName(nom) ;
				if(index != -1) {
					this.addTransaction(ListClients.get(index));									
				}else {
					System.out.println("client introuvable");; 
				}
				System.out.println("ajouter une autre transaction(o/n)");
				try {
					c  = sc.next().charAt(0) ; 
					if(c != 'o') break; 

					sc.nextLine() ; 
				}catch(InputMismatchException e) {
					   System.out.println("L'entrée n'est pas un entier valide. Veuillez entrer un entier.");
					
				}catch(Exception e) {
					System.out.println("manage choices") ; 
					System.out.println(e.getCause());
				}
				sc.close(); 
			}while(true); 
			break  ;
		}
		case 4 : {
			do {
				this.displayProduits() ; 
				this.updateProduct() ; 
				
				System.out.println("modidier un autre produit ??(o/n)");
				Scanner sc = new Scanner(System.in) ; 
				char c = sc.next().charAt(0) ;
				sc.close() ; 
				if( c!='o') break ; 
			}while(true) ; 
			break  ;
		}
		case 5 : {
			do{
			if(ListProduits.isEmpty()) {
				System.out.println("la liste des produit est vide");
				break ;
			}
				this.displayProduits() ; 
				this.deleteProduct() ; 
			System.out.println("supprimer un autre produit (o/n)");	
			Scanner sc = new Scanner(System.in) ; 
			char c = sc.next().charAt(0) ;
			sc.close() ; 
			if( c!='o') break ; 
			}while(true); 
			break ; 
		}
		case 6 :{
			this.displayProduits(); 
			break ; 
		}
		case 7 :{
			this.displayClients()  ; 
			break ; 
		}
		case 8 :{
			this.displayTransactions() ;  
			break ; 
		}
		default : 
			break ; 
		}
		return ; 
	}
	public void updateFile() {
		String clientFileName ="clients.txt" ,
			   produitFileName ="produits.txt" , 
			   transactionFileName ="transactions.txt" ;
		try {
			BufferedWriter ClientWriter = new BufferedWriter(new FileWriter(clientFileName)) ; 
			BufferedWriter TransactionWriter = new BufferedWriter(new FileWriter(transactionFileName)) ; 
			BufferedWriter ProduitWriter = new BufferedWriter(new FileWriter(produitFileName)) ; 
			ClientWriter.write(this.ClientList());
			TransactionWriter.write(this.TransactionList());
			ProduitWriter.write(this.ProductList());
			ClientWriter.close();
			ProduitWriter.close(); 
			TransactionWriter.close();
			System.out.println("les données sont sauvegardés avec succés dans les fichier textes");
		}catch (Exception e) {
				System.out.println("données n'est pas sauvegarder dans le fichier");
		}
		
	}
	public ArrayList<Client> getClientsFromFile(){
		ArrayList<Client> liste = new ArrayList<Client>() ; 
		try {
			BufferedReader reader = new BufferedReader(new FileReader("clients.txt")) ; 
			String line ; 
			while((line = reader.readLine()) != null) {
				if(line == "") {
					continue  ; 
				}
				String[] data= line.split(",") ;  
				liste.add(new Client(data[0] , data[1])) ; 
			}
			
			reader.close();
		}catch (Exception e) {
			System.out.println("probleme!!!!"); 
		}
		
		return  liste ; 
	}
	public ArrayList<Produit> getProduitsFromFile(){
		ArrayList<Produit> liste = new ArrayList<Produit>() ; 
		try {
			BufferedReader reader = new BufferedReader(new FileReader("produits.txt")) ; 
			String line ; 
			while((line = reader.readLine()) != null) {
				if(line == "") {
					continue  ; 
				}
				String[] data= line.split(",") ;  
				liste.add(new Produit(Integer.parseInt(data[0]) ,data[1] , Integer.parseInt(data[2]) ,Float.parseFloat(data[3]) ) ) ; 
			}
			
			reader.close();
		}catch (Exception e) {
			System.out.println("probleme!!!!"); 
		}
		
		return  liste ; 
	}
	public ArrayList<Transaction> getTransacrionsFromFile(){
		ArrayList<Transaction> liste = new ArrayList<Transaction>() ; 
		ArrayList<TransactionItem> subliste = new ArrayList<TransactionItem>() ; 
		try {
			BufferedReader reader = new BufferedReader(new FileReader("transactions.txt")) ; 
			String line ; 
			while((line = reader.readLine()) != null) {
				if(line == "") {
					continue  ; 
				}
				String[] data= line.split(",") ;  
				Produit pr  = new Produit(Integer.parseInt(data[3]) , data[4]  ,Integer.parseInt(data[5]) ,Float.parseFloat(data[6])) ;
				TransactionItem item = new TransactionItem(pr ,Integer.parseInt(data[7]) ); 
				if(TransactionIndex(data[0], liste) == -1) {
					subliste.add(item) ; 
					liste.add(new Transaction( data[0] , new Client(data[1] , data[2]), subliste)) ; 
				}else {
					subliste = liste.get(TransactionIndex(data[0], liste)).getItems() ; 
					subliste.add(item) ; 
					liste.set(TransactionIndex(data[0], liste), new Transaction( data[0] , new Client(data[1] , data[2]), subliste) ) ; 
				}
			}
			
			reader.close();
		}catch (Exception e) {
			System.out.println("probleme!!!!"); 
		}
		
		return  liste ; 
	}
	//getters & setters 
	public String getNomMarket() {
		return nomMarket;
	}


	public List<Produit> getListProduits() {
		return ListProduits;
	}
	
	
}
