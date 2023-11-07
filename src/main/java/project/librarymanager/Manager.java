package project.librarymanager;
import java.util.ArrayList;

public class Manager extends Librarian {
	
	private static ArrayList<Librarian> librarians = new ArrayList<>();

	Manager(String username, String password) {
		super(username, password);
		
	}
	
	Manager(String username,String password,String name,double salary,String phone,String email){
		super(username,password,name,salary,phone,email);
	}
	
     public static void InstantiateLibrarians() {
		
		Librarian lib = new Librarian("Alfie123","SSU6umwt","Alfie",500,"(912) 921-2728","aflie@librarian.com") ;
		librarians.add(lib);
		
		lib = new Librarian("@Leo","TyFzN8we","Leo",500,"(912) 152-7493","leo@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("Julie?!","NDt8f6xL","Julie",500,"(912) 742-7832","julie@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("MargiE","vGtM6beC","Margie",500,"(912) 253-6939","margie@librarian.com");
		librarians.add(lib);
		
		lib = new Librarian("1","1","TestLibrarian",500,"(912) 632-6353","TestEmail@librarian.com");
		librarians.add(lib);
		
	}
	
    
    public static String checkStock() {
    	
    	ArrayList<Book> stockbooks = BillNumber.getStockBooks();
    	String ans = "Warning!\n";
    	int check=0;
    	
    	for (int i=0;i<stockbooks.size();i++) {
    		if (stockbooks.get(i).getStock()<5) {
    			check=1;
    			ans = ans.concat("Book: "+stockbooks.get(i).getTitle()+", With ISBN code: "+stockbooks.get(i).getISBN()+", Has Stock: "+stockbooks.get(i).getStock()+"\n");
    		}
    	}
    	
    	if (check==0)
    		return "Every book has 5 or more items in stock";
    	return ans;
    	
    }
    
    public static ArrayList<Book> getLowStock(){
    	
    	ArrayList<Book> stockbooks = BillNumber.getStockBooks();
    	ArrayList<Book> ans = new ArrayList<>();
    	
    	for (int i=0;i<stockbooks.size();i++) {
    		
    		if (stockbooks.get(i).getStock() < 5 ) {
    			ans.add(stockbooks.get(i));
    		}
    		
    	}
    	
    	return ans;
    	
    }
    
    public static void AddLibrarian(Librarian lib) {
    	librarians.add(lib);
    }
    
    public static ArrayList<Librarian> getLibrarians() {
    	return librarians;
    }
    
    public static boolean partOfLibrarian(Librarian lib) {
    	
    	for (int i=0;i<librarians.size();i++) 
    		if (librarians.get(i).getUsername().equals(lib.getUsername()))
    			return true;
    		
    	return false;
    	
    }
    
    public static Librarian reEnter(Librarian lib) {
    	
    	for (int i=0;i<librarians.size();i++) {
    		if (librarians.get(i).getUsername().equals(lib.getUsername()))
    			return librarians.get(i);
    	}
    	
    	return null;
    	
    }
    
    public static boolean LibrarianChecker(Librarian lib) {
    	for (int i=0;i<librarians.size();i++) {
    		if (librarians.get(i).getUsername().equals(lib.getUsername()) && librarians.get(i).getPassword().equals(lib.getPassword()))
    			return true;
    	}
    	return false;
    	
    	
    }
    
    public static Librarian getBackLibrarian(Librarian lib) {
    	
    	for (int i=0;i<librarians.size();i++){
    		if (librarians.get(i).getUsername().equals(lib.getUsername()))
    			return librarians.get(i);
    	}
    	return null;
    }
    
    public static void updateLibrarians(Librarian lib) {
    	
    	for (int i=0;i<librarians.size();i++){
    		if (librarians.get(i).getUsername().equals(lib.getUsername())) {
    			librarians.get(i).setEmail( lib.getEmail() );
    			librarians.get(i).setPhone( lib.getPhone() );
    			librarians.get(i).setSalary( lib.getSalary() );
    			librarians.get(i).setPassword( lib.getPassword() );
    			librarians.get(i).setUsername( lib.getUsername() );
    			return;
    		}
    			
    	}
    
    	
    }
    
    public static void deleteLibrarian(Librarian lib) {
    	for (int i=0;i<librarians.size();i++) {
    		if (librarians.get(i).getUsername().equals(lib.getUsername())) {
    			librarians.remove(i);
    			return;
    		}
    	}
    }
    
    public static ArrayList<String> getAllCategories(){
    	
    	ArrayList<String> ans = new ArrayList<>();
    	ans.add("Modernist");
    	ans.add("Fiction");
    	ans.add("Novel");
    	ans.add("Magic Realism");
    	ans.add("Tragedy");
    	ans.add("Adventure Fiction");
    	ans.add("Historical Novel");
    	ans.add("Epic");
    	ans.add("War");
    	ans.add("Autobiography and memoir");
    	ans.add("Biography");
    	ans.add("Non-fiction novel");
    	ans.add("Self-help");
    	ans.add("Short stories");
    	ans.add("Horror");
    	ans.add("Mystery");
    	ans.add("Romance");
    	ans.add("Thriller");
    	return ans;
    }
}
