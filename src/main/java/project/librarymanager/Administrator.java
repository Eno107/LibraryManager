package project.librarymanager;

import java.util.ArrayList;

public class Administrator extends Manager {
	
	private static ArrayList<Manager> managers = new ArrayList<>();
    private static ArrayList<Administrator> admins = new ArrayList<>();
	
	private static String[] usernames = {
			"J0sh",
			"1"
	};

	private static String[] passwords = {
			"&zsX6QVZ",
			"3"
	};
	
	private String username;
	private String password;
	
	Administrator(String username,String password){
		super(username,password);
		this.username=username;
		this.password=password;
	}
	
	Administrator(String username,String password,String name,double salary,String phone,String email){
		super(username,password,name,salary,phone,email);
	}
	
	
	public static double getSalaries() {
		
		double ans = 0;
		
		for (int i = 0; i< Manager.getLibrarians().size(); i++) {
			ans += Manager.getLibrarians().get(i).getSalary();
		}
		
		for (int i=0;i<Administrator.getManagers().size();i++) {
			ans += Administrator.getManagers().get(i).getSalary();
		}
		
		for (int i=0;i<Administrator.getAdmins().size();i++) {
			ans += Administrator.getAdmins().get(i).getSalary();
		}
		return ans;
	}
	
	
    public static void InstantiateManagers() {
 		
		Manager mag = new Manager("Calv1n","PQ532Ayba","Calvin",900,"(912) 561-2628","calvl@manager.com") ;
		managers.add(mag);
		
	    mag = new Manager("Lui54","y@.3FYrn","Lui",900,"(912) 218-2594","lu@manager.com") ;
		managers.add(mag);
		
		mag = new Manager("1","2","TestManager",900,"(912) 623-5353","TestEmail@librarian.com");
		managers.add(mag);
	}
    
    
    
    public static void InstantiateAdmins() {
    	
    	Administrator admin = new Administrator("J0sh","&zsX6QVZ","Josh",1500,"(912) 561-2328","josh@administrator.com") ;
		admins.add(admin);
		
		admin = new Administrator("1","3","TestAdmin",1500,"(912) 626-5353","TestEmail@admin.com");
		admins.add(admin);
    	
    }
    
    public static ArrayList<Administrator> getAdmins(){
    	return admins;
    }
	
	
    public static boolean partOfManager(Manager mag) {
    	
    	for (int i=0;i<managers.size();i++) 
    		if (managers.get(i).getUsername().equals(mag.getUsername()))
    			return true;
    		
    	return false;
    	
    }
    
    public static Manager reEnter(Manager mag) {
    	
    	for (int i=0;i<managers.size();i++) {
    		if (managers.get(i).getUsername().equals(mag.getUsername()))
    			return managers.get(i);
    	}
    	
    	return null;
    	
    }
    
    public static void AddManager(Manager mag) {
    	managers.add(mag);
    }
    
    public static boolean checker(String username,String password) {
    	
    	for (int i=0;i<usernames.length;i++) {
    		if (usernames[i].equals(username))
    			if (passwords[i].equals(password))
    				return true;
    	}
    	
    	return false;
    }
    
    public static boolean ManagerChecker(Manager mag) {
    	for (int i=0;i<managers.size();i++) {
    		if (managers.get(i).getUsername().equals(mag.getUsername()) && managers.get(i).getPassword().equals(mag.getPassword()))
    			return true;
    	}
    	return false;
    	
    	
    }
    
    public static Manager getBackManager(Manager mag) {
    	
    	for (int i=0;i<managers.size();i++){
    		if (managers.get(i).getUsername().equals(mag.getUsername()))
    			return managers.get(i);
    	}
    	return null;
    }
    
    public static ArrayList<Manager> getManagers(){
    	return managers;
    }
    
    public static void deleteManager(Manager mag) {
    	for (int i=0;i<managers.size();i++) {
    		if (managers.get(i).getUsername().equals(mag.getUsername()) ) {
    			managers.remove(i);
    			return;
    		}
    	}
    }
    
   public static void updateManagers(Manager mag) {
    	
    	for (int i=0;i<managers.size();i++){
    		if (managers.get(i).getUsername().equals(mag.getUsername())) {
    			managers.get(i).setEmail( mag.getEmail() );
    			managers.get(i).setPhone( mag.getPhone() );
    			managers.get(i).setSalary( mag.getSalary() );
    			managers.get(i).setPassword( mag.getPassword() );
    			managers.get(i).setUsername( mag.getUsername() );
    			return;
    		}
    			
    	}
    
    	
    }


}
