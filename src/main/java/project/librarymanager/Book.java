package project.librarymanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Book implements Serializable{

	private String ISBN;
	private String title;
	private String category;
	private String supplier;
	private ArrayList<Date> dates;
	private ArrayList<Integer> purchasedAmount;
	
	private ArrayList<Date> purchasedDates; 
	private ArrayList<Integer> quantitiesPurchased;
	private double sellingPrice;
	private double originalPrice;
	private String author;
	private int stock=0;
	
	Book(String ISBN, String title, String category, String supplier, double originalPrice, double sellingPrice,String author, int stock){
		this.setISBN(ISBN);
		this.setTitle(title);
		this.setCategory(category);
		this.setSupplier(supplier);
		this.setOriginalPrice(originalPrice);
		this.setSellingPrice(sellingPrice);
		this.setAuthor(author);
		this.AddStock(stock);
		dates = new ArrayList<>();
		purchasedDates = new ArrayList<>();
		quantitiesPurchased = new ArrayList<>();
		purchasedAmount = new ArrayList<>();
	}
	
	Book(String ISBN){
		quantitiesPurchased = new ArrayList<>();
		dates = new ArrayList<>();
		purchasedDates = new ArrayList<>();
		purchasedAmount = new ArrayList<>();
		this.setISBN(ISBN);
	}
	
	
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public double getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getStock() {
		return stock;
	}
	public void AddStock(int stock) {
		this.stock += stock;
	}
	public void RemoveStock(int stock) {
		this.stock -= stock;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void addDate(Date date) {
		this.dates.add(date);
	}
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}
	public ArrayList<Date> getDates(){
		return dates;
	}
	public void addPurchasedDate(Date date) {
		this.purchasedDates.add(date);
	}
	
	public void addQuantity(int quan) {
		this.purchasedAmount.add( quan );
	    
	}
	public void addQuantitiesPurchased(int quan) {
		this.quantitiesPurchased.add(quan);
	}
	
	
	public int getPurchasedAmount(){
		int sum=0;
	    for (int i=0;i<purchasedAmount.size();i++) {
	    	sum+=purchasedAmount.get(i);
	    }
	    return sum;
	}
	public int getQuantitiesPurchased(){
		int sum=0;
	    for (int i=0;i<quantitiesPurchased.size();i++) {
	    	sum+=quantitiesPurchased.get(i);
	    }
	    return sum;
	}

	

	


	public String getSoldDatesQuantitiesTotal() {
		
        String ans = "For \"" + this.title +"\" We have sold:\n";
		
		if (dates.isEmpty()) {
			return this.getTitle()+" has had no purchases\n";
		}
		
		Date today = new Date();
		
		for (int i=0;i<dates.size();i++) {
			ans = ans.concat(purchasedAmount.get(i) +" at "+dates.get(i)+"\n");
		}
		return ans;
		
	}
	
	
	public String getSoldDatesQuantitiesDay(){
		
		String ans = "For \"" + this.title +"\" We have sold in a day:\n";
		
		if (dates.isEmpty()) {
			return this.getTitle()+" has had no purchases\n";
		}
		
		Date today = new Date();
		
		for (int i=0;i<dates.size();i++) {
			
			if (dates.get(i).getYear() == today.getYear() && dates.get(i).getMonth() == today.getMonth() && dates.get(i).getDay() == today.getDay()) {
				ans = ans.concat(purchasedAmount.get(i) +" at "+dates.get(i)+"\n");
			}
		}
		return ans;
		
		
	}
	
     public String getSoldDatesQuantitiesMonth(){
		
		String ans = "For \"" + this.title +"\" We have sold in a month:\n";
		
		if (dates.isEmpty()) {
			return this.getTitle()+" has had no purchases\n";
		}
		
		Date today = new Date();
			
		for (int i=0;i<dates.size();i++) {
			if (dates.get(i).getYear() == today.getYear() && dates.get(i).getMonth() == today.getMonth()) {
				ans = ans.concat(purchasedAmount.get(i) +" at "+dates.get(i)+"\n");
			}
		}
		return ans;
	}
     
     public String getSoldDatesQuantitiesYear(){
 		
 		String ans = "For \"" + this.title +"\" We have sold in a year:\n";
 		
 		if (dates.isEmpty()) {
 			return this.getTitle()+" has had no purchases\n";
 		}
 		
 		Date today = new Date();
 		
 		for (int i=0;i<dates.size();i++) {
 			if (dates.get(i).getYear() == today.getYear()) {
 				ans = ans.concat(purchasedAmount.get(i) +" at "+dates.get(i)+"\n");
 			}
 		}
 		return ans;
		
 		
 	}
     
     
     public String getBoughtDatesQuantitiesTotal() {
    	 
    	 String ans = "For \"" + this.title +"\" We have bought in a day:\n";
  		
  		if (purchasedDates.isEmpty()) {
  			return "We have made no purchases on \""+this.getTitle()+"\"\n";
  		}
  		
  		Date today = new Date();
  		
  		for (int i=0;i<purchasedDates.size();i++) {
  			ans = ans.concat(quantitiesPurchased.get(i) +" at "+purchasedDates.get(i)+"\n");
  		}
  		
  		return ans;
    	 
     }
     
     public String getBoughtDatesQuantitiesDay(){
 		
 		String ans = "For \"" + this.title +"\" We have bought in a day:\n";
 		
 		if (purchasedDates.isEmpty()) {
 			return "We have made no purchases on \""+this.getTitle()+"\"\n";
 		}
 		
 		Date today = new Date();
 		
 		for (int i=0;i<purchasedDates.size();i++) {
 			if (purchasedDates.get(i).getYear() == today.getYear() && purchasedDates.get(i).getMonth() == today.getMonth() && purchasedDates.get(i).getDay() == today.getDay()) {
 				ans = ans.concat(quantitiesPurchased.get(i) +" at "+purchasedDates.get(i)+"\n");
 			}
 		}
 		return ans;
 	}
     
     public String getBoughtDatesQuantitiesMonth(){
  		
  		String ans = "For \"" + this.title +"\" We have bought in a month:\n";
  		
  		if (purchasedDates.isEmpty()) {
  			return "We have made no purchases on \""+this.getTitle()+"\"\n";
  		}
  		
  		Date today = new Date();
  		
  		for (int i=0;i<purchasedDates.size();i++) {
  			if (purchasedDates.get(i).getYear() == today.getYear() && purchasedDates.get(i).getMonth() == today.getMonth()) {
  				ans = ans.concat(quantitiesPurchased.get(i) +" at "+purchasedDates.get(i)+"\n");
  			}
  		}
  		return ans;
  	}
     
     public String getBoughtDatesQuantitiesYear(){
  		
  		String ans = "For \"" + this.title +"\" We have bought in a year:\n";
  		
  		if (purchasedDates.isEmpty()) {
  			return "We have made no purchases on \""+this.getTitle()+"\"\n";
  		}
  		
  		Date today = new Date();
  		
  		for (int i=0;i<purchasedDates.size();i++) {
  			if (purchasedDates.get(i).getYear() == today.getYear()) {
  				ans = ans.concat(quantitiesPurchased.get(i) +" at "+purchasedDates.get(i)+"\n");
  			}
  		}
  		return ans;
  	}
     
     public int getTotalBooksSoldDay() {
    	 
    	 if (dates.isEmpty()) {
  			return 0;
  		}
    	 
    	 int ans = 0;
 		
 		Date today = new Date();
 		
 		for (int i=0;i<dates.size();i++) {
 			
 			if (dates.get(i).getYear() == today.getYear() && dates.get(i).getMonth() == today.getMonth() && dates.get(i).getDay() == today.getDay()) {
 				ans+=purchasedAmount.get(i);
 			}
 		}
 		return ans;
    	 
     }
     
 public int getTotalBooksSoldMonth() {
    	 
    	 if (dates.isEmpty()) {
  			return 0;
  		}
    	 
    	 int ans = 0;
 		
 		Date today = new Date();
 		
 		for (int i=0;i<dates.size();i++) {
 			
 			if (dates.get(i).getYear() == today.getYear() && dates.get(i).getMonth() == today.getMonth()) {
 				ans+=purchasedAmount.get(i);
 			}
 		}
 		return ans;
    	 
     }
 
   public int getTotalBooksSoldYear() {
	 
	 if (dates.isEmpty()) {
			return 0;
		}
	 
	 int ans = 0;
		
		Date today = new Date();
		
		for (int i=0;i<dates.size();i++) {
			
			if (dates.get(i).getYear() == today.getYear()) {
				ans+=purchasedAmount.get(i);
			}
		}
		return ans;
	 
 }
   
   public int getTotalBooksBoughtDay() {
	   
	   if (purchasedDates.isEmpty()) {
			return 0;
		}
	 
	 int ans = 0;
		
		Date today = new Date();
		
		for (int i=0;i<purchasedDates.size();i++) {
			
			if (purchasedDates.get(i).getYear() == today.getYear() && purchasedDates.get(i).getMonth() == today.getMonth() && purchasedDates.get(i).getDay() == today.getDay()) {
				ans+=quantitiesPurchased.get(i);
			}
		}
		return ans;
   }
   
public int getTotalBooksBoughtMonth() {
	   
	   if (purchasedDates.isEmpty()) {
			return 0;
		}
	 
	 int ans = 0;
		
		Date today = new Date();
		
		for (int i=0;i<purchasedDates.size();i++) {
			
			if (purchasedDates.get(i).getYear() == today.getYear() && purchasedDates.get(i).getMonth() == today.getMonth()) {
				ans+=quantitiesPurchased.get(i);
			}
		}
		return ans;
   }

public int getTotalBooksBoughtYear() {
	
	if (purchasedDates.isEmpty()) {
		return 0;
	}
 
 int ans = 0;
	
	Date today = new Date();
	
	for (int i=0;i<purchasedDates.size();i++) {
		
		if (purchasedDates.get(i).getYear() == today.getYear()) {
			ans+=quantitiesPurchased.get(i);
		}
	}
	return ans;
	
}

public int getTotalBooksSold() {
	
	
	if (dates.isEmpty()) {
		return 0;
	}
	
	int ans=0;
	
	for (int i=0;i<dates.size();i++) {
		ans+=purchasedAmount.get(i);
	}
	
	return ans;
}

public int getTotalBooksBought() {
	
	
	if (purchasedDates.isEmpty()) {
		return 0;
	}
	
	int ans=0;
	
	for (int i=0;i<purchasedDates.size();i++) {
		ans+=quantitiesPurchased.get(i);
	}
	
	return ans;
}

	@Override
	public String toString() {
		return "Book [ISBN=" + ISBN + ", title=" + title + ", category=" + category + ", supplier=" + supplier
				+ ", sellingPrice=" + sellingPrice
				+ ", originalPrice=" + originalPrice + ", author=" + author + ", stock=" + stock + "]";
	}
	
}