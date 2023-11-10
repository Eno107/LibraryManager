package project.librarymanager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Librarian extends BillNumber {
	
	private int numberOfBills=0;
	private int booksSold=0;
	private double moneyMade=0;
	private final ArrayList<Date> datesSold;
	private final ArrayList<Double> moneyMadeDates;
	private String username;
	private String password;
	private String name;
	private double salary;
	private String phone;
	private String email;

	
	Librarian(String username,String password,String name,double salary,String phone,String email){
		this.username = username;
		this.password = password;
		this.name = name;
		this.salary = salary;
		this.phone = phone;
		this.email=email;
		datesSold = new ArrayList<>();
		moneyMadeDates = new ArrayList<>();
	}
	
	Librarian(String username,String password){
		this.username = username;
		this.password = password;
		datesSold = new ArrayList<>();
		moneyMadeDates = new ArrayList<>();
	}
	
	
	public void checkOutBooks(ArrayList<Book> books,ArrayList<Integer> quantities) throws IOException {

		PrintWriter writer = new PrintWriter("Bill"+(++BillNumber.billNumber)+".txt");
		ArrayList<Book> storybooks = BillNumber.getStockBooks();
		double totalPrice = 0;
		
		removeDuplicatesSoldBooks(books,quantities);
		
		for (int i=0;i<books.size();i++) {
            for (Book stockbook : storybooks) {

                if (books.get(i).getISBN().equals(stockbook.getISBN())) {
                    writer.write("Title: \"" + stockbook.getTitle() + "\", Quantities: " + quantities.get(i) + ", OriginalPrice " +
                            stockbook.getSellingPrice() + ", Price: " + stockbook.getSellingPrice() * quantities.get(i) + "\n");

                    stockbook.addDate(new Date());
                    stockbook.addQuantity(quantities.get(i));

                    totalPrice += stockbook.getSellingPrice() * quantities.get(i);

                    BillNumber.totalBooksSold += quantities.get(i);
                    BillNumber.totalIncome += totalPrice;
                    booksSold += quantities.get(i);

                }

            }
		}
		
		
		BillNumber.updateBooks(storybooks);
		moneyMade+=totalPrice;
		numberOfBills+=1;
		datesSold.add(new Date());
		moneyMadeDates.add(moneyMade);
		
		writer.write("\nTotal price: "+totalPrice+" Date: "+ (new Date()));
		writer.close();
		
	}
	
	
	
	
	public void removeDuplicatesSoldBooks(ArrayList<Book> books, ArrayList<Integer> quantities) {
		
		if (books.isEmpty() || quantities.isEmpty())
			return;
		
		for (int k=0;k<2;k++) {
			
			for (int i=0;i<books.size();i++) {
				for (int j=i+1;j<books.size();j++) {
					if (books.get(i).getISBN().equals(books.get(j).getISBN())){
						quantities.set(i, quantities.get(i) + quantities.get(j));
						books.remove(j);
						quantities.remove(j);
					}
				}
		    }
			
		}
		
		int n=books.size()-1;
		try {
			if (  books.get(n).getISBN().equals( books.get(n-1).getISBN() ) ) {
				
				quantities.set(n-1,  quantities.get(n)+quantities.get(n-1) );
				quantities.remove(n);
				books.remove(n);
			}
		}
		catch(IndexOutOfBoundsException ignored) {}
		
	}
	
	
	
	public static boolean BookPresent(String ISBN) {
		
		ArrayList<Book> storybooks = BillNumber.getStockBooks();

        for (Book stockbook : storybooks) {
            if (stockbook.getISBN().equals(ISBN))
                return true;
        }
		return false;
	}
	
	public static boolean EnoughStock(String ISBN, int quantity) {
		
		ArrayList<Book> storybooks = BillNumber.getStockBooks();

        for (Book stockbook : storybooks) {
            if (stockbook.getISBN().equals(ISBN))
                if (stockbook.getStock() - quantity >= 0)
                    return true;
        }
		
		return false;
		
		
	}
	
	
	public static ArrayList<Date> addBookDates(Book book,ArrayList<Date> dates) {
		ArrayList<Date> ans = new ArrayList<>();

        for (Date date : dates) {
            ans.add(date);
        }
		
		return ans;
	}
	
	public double moneyMadeInDay() {

        double ans=0;
		Date today = new Date();
		
		for (int i=0;i<this.datesSold.size();i++) {
			
			if ( datesSold.get(i).getYear()==today.getYear() && datesSold.get(i).getMonth()==today.getMonth() && datesSold.get(i).getDay() == today.getDay()) {
				ans+=moneyMadeDates.get(i);
			}
				
		}
		
		return ans;
		
	}
	
	public double moneyMadeInMonth() {

        double ans=0;
		Date today = new Date();
		
		
		for (int i=0;i<datesSold.size();i++) {
			
			if (datesSold.get(i).getYear() == today.getYear() && datesSold.get(i).getMonth()==today.getMonth()) {
				ans+=moneyMadeDates.get(i);
			}
		}
		
		return ans;
		
	}
	
	public double moneyMadeInYear() {

        double ans=0;
		Date today = new Date();
		
		for (int i=0;i<datesSold.size();i++) {
			
			if (datesSold.get(i).getYear() == today.getYear()) {
				ans+=moneyMadeDates.get(i);
			}
		}

		return ans;
		
	}
	
	public static boolean checkPassword(String password) {
		return password.matches(".{8,}");
	}
	
	public static boolean checkPhone(String phone) {
		return phone.matches("\\(912\\)\\s\\d{3}-\\d{4}");
	}
	
	public static boolean checkEmail(String email) {
		return email.matches("[a-zA-Z]+@[a-zA-z]+\\.com");
	}
	
	public static boolean checkSalary(String salary) {
		return salary.matches("^[0-9]+\\.?[0-9]*$");
	}
	
	public static boolean checkName(String name) {
		return name.matches("[a-zA-Z]+");
	}
	
	public static boolean checkUsername(String username) {
		return username.matches(".{5,}");
	}

	public int getNumberOfBills() {
		return numberOfBills;
	}


	public int getBooksSold() {
		return booksSold;
	}


	public double getMoneyMade() {
		return moneyMade;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Librarian [username=" + username + ", password=" + password + ", name=" + name + ", salary=" + salary
				+ ", phone=" + phone + ", email=" + email + "]";
	}
	
	
	
}
