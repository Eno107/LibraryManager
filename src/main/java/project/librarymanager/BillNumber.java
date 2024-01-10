package project.librarymanager;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class BillNumber {

	public static int billNumber=0;
	public double  totalIncome=0;
	public int totalBooksSold=0;


	public void updateBooks(String path, ArrayList<Book> arr) throws IOException {

		FileOutputStream out = new FileOutputStream(path);
		ObjectOutputStream objout = new ObjectOutputStream(out);

		for (Book book : arr) {
			objout.writeObject(book);
		}

		out.close();
		objout.close();

	}


	public ArrayList<Book> getStockBooks(String path){

		ArrayList<Book> stockBooks = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream objis = new ObjectInputStream(fis);

			while (true) {
				try {
					stockBooks.add((Book) objis.readObject());
				} catch (EOFException e) {
					break;
				}
			}

			fis.close();
			objis.close();
		}
		catch(IOException | ClassNotFoundException ignored) {}


		return stockBooks;
	}

	public ArrayList<Book> getInitialStock(){

		ArrayList<Book> bookArrayList = new ArrayList<>();

		Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);
		bookArrayList.add(book);

		book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
		bookArrayList.add(book);

		book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
		bookArrayList.add(book);

		book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",3);
		bookArrayList.add(book);

		book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",7);
		bookArrayList.add(book);

		book = new Book("9515267203718","Moby Dick","Adventure fiction","Cardinal Publishers Group",7.00,10.00,"Herman Melville",5);
		bookArrayList.add(book);

		book = new Book("0725587872636","War and Peace","Historical novel","Bella Distribution",17.00,19.99,"Leo Tolstoy",2);
		bookArrayList.add(book);

		book = new Book("0664687443145","Hamlet","Tragedy","Publishers Group West",12.00,14.99,"William Shakespeare",12);
		bookArrayList.add(book);

		book = new Book("8047862766153","The Odyssey","Epic","Publishers Group West",15.00,22.99,"Homer",4);
		bookArrayList.add(book);

		book = new Book("4535777140780","Lolita","Novel","Ingram Content Group, Inc",10.00,14.40,"Vladimir Nabokov",9);
		bookArrayList.add(book);

		return bookArrayList;
	}

	public void setInitialStock(String path) throws IOException {
		FileOutputStream out = new FileOutputStream(path);
		ObjectOutputStream objout = new ObjectOutputStream(out);

		for (Book book : this.getInitialStock()){
			objout.writeObject(book);
		}

		out.close();
		objout.close();
	}

	public ArrayList<String> getCategories(String path) {

		ArrayList<String> ans = new ArrayList<>();

		ArrayList<Book> books = this.getStockBooks(path);

		for (Book book : books) {
			ans.add(book.getCategory());
		}


		return removeDuplicates(ans);

	}

	//    UNIT 1
	public <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
	{

		ArrayList<T> newList = new ArrayList<T>();

		for (T element : list) {

			if (!newList.contains(element)) {

				newList.add(element);
			}
		}

		return newList;
	}

	public ArrayList<Book> getBookFromCategory(String path, String category){

		ArrayList<Book> ans = new ArrayList<>();
		ArrayList<Book> stockbooks = this.getStockBooks(path);

		for (Book stockbook : stockbooks) {
			if (stockbook.getCategory().equals(category)) {
				ans.add(stockbook);
			}
		}

		return ans;

	}

	public void addBookToStock(String path, Book book) throws IOException {

		ArrayList<Book> stockbooks = this.getStockBooks(path);
		for (Book stockbook: stockbooks){
			if (stockbook.getISBN().equals(book.getISBN())){
				stockbook.AddStock(book.getStock());
				this.updateBooks(path, stockbooks);
				return;
			}
		}

		stockbooks.add(book);
		this.updateBooks(path, stockbooks);
	}

	public String showStringStock(String path) {

		String ans="Currently in Stock:\n";
		ArrayList<Book> stockbooks = this.getStockBooks(path);

		for (Book stockbook : stockbooks) {
			ans = ans.concat(stockbook + "\n");
		}

		return ans;
	}

	// UNIT 2
	public boolean partOfCateogriesChecker(ArrayList<String> categoriesStock,String category) {

		for (String s : categoriesStock) {
			if (s.equals(category))
				return true;
		}
		return false;
	}

	public String getBooksSoldTotal(String path) {

		String ans = "For Books Sold in Total We Have\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getSoldDatesQuantitiesTotal());
		}
		return ans;
	}

	public String getBooksBoughtTotal(String path) {

		String ans = "For Books Bought in Total We Have\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getBoughtDatesQuantitiesTotal());
		}
		return ans;

	}

	public String getBooksSoldDay(String path) {

		String ans = "For Books Sold Today We Have:\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getSoldDatesQuantitiesDay());
		}
		return ans;
	}

	public String getBooksSoldMonth(String path) {

		String ans = "For Books Sold In A Month We Have\n\n";

		ArrayList<Book> arr = this.getStockBooks(path);
		for (Book book : arr) {
			ans = ans.concat(book.getSoldDatesQuantitiesMonth());
		}
		return ans;
	}

	public String getBooksSoldYear(String path) {

		String ans = "For Books Sold In A Year We Have\n\n";

		ArrayList<Book> arr = this.getStockBooks(path);
		for (Book book : arr) {
			ans = ans.concat(book.getSoldDatesQuantitiesYear());
		}
		return ans;
	}


	public String getBooksBoughtDay(String path) {

		String ans = "For Books Bought Today We Have\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getBoughtDatesQuantitiesDay());
		}
		return ans;

	}

	public String getBooksBoughtMonth(String path) {

		String ans = "For Books Bought In A Month We Have\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getBoughtDatesQuantitiesMonth());
		}
		return ans;

	}

	public String getBooksBoughtYear(String path) {

		String ans = "For Books Bought In A Year We Have\n\n";

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			ans = ans.concat(book.getBoughtDatesQuantitiesYear());
		}
		return ans;

	}

	public  int getIntBooksSoldDay(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldDay();
		}
		return ans;

	}

	public  int getIntBooksSoldMonth(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldMonth();
		}
		return ans;
	}

	public  int getIntBooksSoldYear(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldYear();
		}
		return ans;

	}

	public  double getIncomeDay(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldDay() * book.getSellingPrice();
		}

		return ans;

	}

	public  double getIncomeMonth(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldMonth() * book.getSellingPrice();
		}

		return ans;

	}

	public  double getIncomeYear(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksSoldYear() * book.getSellingPrice();
		}

		return ans;

	}

	public  int getTotalBoughtBooksDay(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtDay();
		}
		return ans;

	}

	public  int getTotalBoughtBooksMonth(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtMonth();
		}
		return ans;
	}

	public  int getTotalBoughtBooksYear(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		int ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtYear();
		}
		return ans;

	}

	public  double getCostDay(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtDay() * book.getOriginalPrice();
		}

		return ans;

	}

	public  double getCostMonth(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtMonth() * book.getOriginalPrice();
		}

		return ans;

	}
	public  double getCostYear(String path) {

		ArrayList<Book> array = this.getStockBooks(path);
		double ans = 0;

		for (Book book : array) {
			ans += book.getTotalBooksBoughtYear() * book.getOriginalPrice();
		}

		return ans;

	}

	public  boolean isPartOfBooks(String path, String ISBN) {

		ArrayList<Book> array = this.getStockBooks(path);

		for (Book book : array) {
			if (book.getISBN().equals(ISBN))
				return true;
		}
		return false;
	}

	public  ArrayList<String> getISBNName(String path){

		ArrayList<Book> array = this.getStockBooks(path);
		ArrayList<String> ans = new ArrayList<>();

		for (Book book : array) {
			ans.add(book.getISBN() + " - " + book.getTitle());
		}

		return ans;
	}

	public  ArrayList<String> getAllTitles(String path){

		ArrayList<Book> array = this.getStockBooks(path);
		ArrayList<String> ans = new ArrayList<>();

		for (Book book : array) {
			ans.add(book.getTitle());
		}

		return ans;

	}

	public  ArrayList<Integer> getAllStock(String path){

		ArrayList<Book> array = this.getStockBooks(path);
		ArrayList<Integer> ans = new ArrayList<>();

		for (Book book : array) {
			ans.add(book.getStock());
		}

		return ans;

	}

	// UNIT 4
	public  void removeDuplicatesSoldTitles(ArrayList<String> titles, ArrayList<Integer> quantities) {
		Map<String, Integer> titleQuantities = new LinkedHashMap<>();

		for (int i = 0; i < titles.size(); i++) {
			String currentTitle = titles.get(i);
			int currentQuantity = quantities.get(i);

			if (titleQuantities.containsKey(currentTitle)) {
				int updatedQuantity = titleQuantities.get(currentTitle) + currentQuantity;
				titleQuantities.put(currentTitle, updatedQuantity);
			} else {
				titleQuantities.put(currentTitle, currentQuantity);
			}
		}

		titles.clear();
		quantities.clear();

		for (Map.Entry<String, Integer> entry : titleQuantities.entrySet()) {
			titles.add(entry.getKey());
			quantities.add(entry.getValue());
		}
	}


}
