package project.librarymanager;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class MainFx extends Application implements EventHandler<ActionEvent> {

	// main file path
	String  FILE_PATH = "Books.txt";

	//shared
	static BillNumber billNumber = new BillNumber();
	String usernamePage;
	TextField username = new TextField();
	Text textUsername = new Text("Username");
	PasswordField password = new PasswordField();
	Text textPassword = new Text("Password");
	Button bttSubmit = new Button("Submit");
	Button bttBack = new Button("Back");
	Text textSystem = new Text("System");
	TextField mainLoginWarning = new TextField();
	ArrayList<String> titlesSold = new ArrayList<>();
	ArrayList<Integer> quantitiesSold = new ArrayList<>();
	ArrayList<String> titlesBought = new ArrayList<>();
	ArrayList<Integer> quantitiesBought = new ArrayList<>();
    
	//librarian variables
	Librarian librarian;
	Date date;
	ComboBox comboBoxLibrarian;
	TextField bookISBN = new TextField();
	Text textBookISBN = new Text("Book ISBN");
	TextField quantity = new TextField();
	Text textQuantity = new Text("Quantity");
	Button bttAdd = new Button("Add");
	Button bttBill = new Button("Bill");
	TextField warningsLibrarian = new TextField();
	BorderPane borderLibrarianMain = new BorderPane();
	ArrayList<Book> books = new ArrayList<>();
	ArrayList<Integer> bookQuantities = new ArrayList<>();
	ArrayList<String> booksSoldTitles = new ArrayList<>();
	
	//manager variables
	Manager manager;
	TextField magLoginWarning = new TextField();
	Button bttSupply = new Button("Supply");
	Button bttCheckLibrarians = new Button("Check Librarians");
	Button bttCheckStock = new Button("Check Stock");
	Button bttAddStock = new Button("Add Stock");
	Button bttNewCategory = new Button("New Category");
	Button bttNewBook = new Button("New Book");
	Button bttAddBookToStock = new Button("Add to Stock");
	TextField addedOrNot = new TextField();
	Text textTitle = new Text("Title");
	TextField title = new TextField();
	Text textSupplier = new Text("Supplier");
	TextField supplier = new TextField();
	Text textOriginalPrice = new Text("Original Price");
	TextField originalPrice = new TextField();
	Text textSellingPrice = new Text("Selling Price");
	TextField sellingPrice = new TextField();
	Text textAuthor = new Text("Author");
	TextField author = new TextField();
	Button stockCategoryAddBook = new Button("Add");
	TextField addedOrNotStockCategory = new TextField();
	Button bttBackManager = new Button("Back");
	TextField category = new TextField();
	Text textCategory = new Text("Category");
	ArrayList<String> categ = billNumber.getCategories(FILE_PATH);
	TextField totalNumberBillsLibrarian = new TextField();
	Text textTotalNumberBillsLibrarian = new Text("Total Number of Bills");
	TextField booksSold = new TextField();
	Text textBooksSold = new Text("Books Sold");
	TextField totalAmountOfMoneyMadeInDay = new TextField();
	Text textTotalMoneyDay = new Text("Money made today");
	TextField totalAmountOfMoneyMadeInMonth = new TextField();
	Text textTotalMoneyMonth = new Text("Money made in a month");
	TextField totalAmountOfMoneyMadeInYear = new TextField();
	Text textTotalMoneyYear = new Text("Money made in a year");

	//administrator variables
	Button bttManageLibrarians = new Button("Manage Librarians");


	public static void main(String[] args) throws IOException  {
		
		billNumber.setInitialStock("Books.txt");
		Manager.InstantiateLibrarians();
		Administrator.InstantiateManagers();
		Administrator.InstantiateAdmins();
		
		
		Application.launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Library System");
        
		
		Scene scene = new Scene(mainPage());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		
	}
	
public BorderPane mainPage() {
		
	BorderPane border = new BorderPane();
	border.setMinSize(500,300);
	
	StackPane stackText = new StackPane();
	Text text = new Text("Welcome");
	text.setFont(new Font(30));
	stackText.getChildren().add(text);
	stackText.setPadding(new Insets(20));
	border.setTop(stackText);
	
	mainLoginWarning.setEditable(false);
	GridPane grid = new GridPane();
	grid.setAlignment(Pos.CENTER);
	grid.setHgap(5);
    grid.setVgap(5);
    grid.add(textUsername,0,1);
    grid.add(username,1,1);
    grid.add(textPassword,0,2);
    grid.add(password,1,2);
    grid.add(textSystem, 0, 5);
    grid.add(mainLoginWarning, 1, 5);
    grid.add(bttSubmit, 6, 6);
    border.setCenter(grid);
    
    bttSubmit.setOnAction(this);
    
   
	return border;
	}
	
	
	public BorderPane librarianMainPage() {
		
	   
		comboBoxLibrarian = new ComboBox(FXCollections.observableArrayList(billNumber.getISBNName(FILE_PATH)));
		
		Text textHeaderLibrarian = new Text("Welcome "+usernamePage);
		StackPane stackHeader = new StackPane();
		textHeaderLibrarian.setFont(new Font(30));
		stackHeader.getChildren().add(textHeaderLibrarian);
		stackHeader.setPadding(new Insets(20));
		borderLibrarianMain.setTop(stackHeader);
		
		GridPane gridLibrarianMain = new GridPane();
		gridLibrarianMain.setAlignment(Pos.CENTER);
		gridLibrarianMain.setHgap(5);
	    gridLibrarianMain.setVgap(5);
	    gridLibrarianMain.add(comboBoxLibrarian,1,0);
	    gridLibrarianMain.add(textQuantity, 0, 2);
	    gridLibrarianMain.add(quantity, 1, 2);
	    gridLibrarianMain.add(textSystem, 0, 5);
	    gridLibrarianMain.add(warningsLibrarian, 1, 5);
	    warningsLibrarian.setEditable(false);
	    borderLibrarianMain.setCenter(gridLibrarianMain);
	    
	    HBox hbox = new HBox();
	    hbox.setAlignment(Pos.CENTER);
	    hbox.getChildren().addAll(bttBack,bttAdd,bttBill);
	    hbox.setPadding(new Insets(40));
	    hbox.setSpacing(30);
	    borderLibrarianMain.setBottom(hbox);
	    
	    
	    bttAdd.setOnAction(this);
	    bttBill.setOnAction(this);
        bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
            	quantity.clear();
            	warningsLibrarian.clear();
            	bttBack.getScene().setRoot(mainPage());
            }
            	
        });
	    
		
		return borderLibrarianMain;
	}
	
    
    
    public BorderPane mangaerMainPage() {
    	
    	BorderPane borderManagerMain = new BorderPane();	
    	Text textHeaderManager = new Text("Welcome "+usernamePage);
		StackPane stackHeader = new StackPane();
		textHeaderManager.setFont(new Font(30));
		stackHeader.getChildren().add(textHeaderManager);
		stackHeader.setPadding(new Insets(20));
		borderManagerMain.setTop(stackHeader);
		
		Button bttbookStatistics = new Button("Book Statistics");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
	    grid.add(bttSupply,0,0);
	    grid.add(bttCheckLibrarians, 1, 0);
	    grid.add(bttBack, 4, 0);
	    grid.add(bttCheckStock, 2, 0);
	    grid.add(bttbookStatistics, 3, 0);
	    grid.setPadding(new Insets(30));
	    borderManagerMain.setCenter(grid);
	    
	    //--------------------
	    StackPane pane = new StackPane();
	    TableView table = new TableView();
    	TableColumn<Book,String> column1 = new TableColumn<>("ISBN");
    	TableColumn<Book,String> column2 = new TableColumn<>("Title");
    	TableColumn<Book,String> column3 = new TableColumn<>("Category");
    	TableColumn<Book,String> column4 = new TableColumn<>("Author");
    	TableColumn<Book,Double> column5 = new TableColumn<>("Original Price");
    	TableColumn<Book,Double> column6 = new TableColumn<>("Selling Price");
    	TableColumn<Book,String> column7 = new TableColumn<>("Supplier");
    	TableColumn<Book,Integer> column8 = new TableColumn<>("Stock");
    	
    	column1.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
    	column2.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
    	column3.setCellValueFactory(new PropertyValueFactory<Book,String>("category"));
    	column4.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
    	column5.setCellValueFactory(new PropertyValueFactory<Book,Double>("originalPrice"));
    	column6.setCellValueFactory(new PropertyValueFactory<Book,Double>("sellingPrice"));
    	column7.setCellValueFactory(new PropertyValueFactory<Book,String>("supplier"));
    	column8.setCellValueFactory(new PropertyValueFactory<Book,Integer>("stock"));
    	
    	table.getColumns().add(column1);
    	table.getColumns().add(column2);
    	table.getColumns().add(column3);
    	table.getColumns().add(column4);
    	table.getColumns().add(column5);
    	table.getColumns().add(column6);
    	table.getColumns().add(column7);
    	table.getColumns().add(column8);
    	
    	table.setItems(FXCollections.observableArrayList(Manager.getLowStock(FILE_PATH)));
        table.setMaxHeight(150);
        pane.getChildren().add(table);
    	
    	borderManagerMain.setBottom(pane);
	    
	    
	    
	    //---------------------
	    
	    
	    
	    bttSupply.setOnAction(this);
	    bttCheckStock.setOnAction(this);
	    bttCheckLibrarians.setOnAction(event ->{
	    	if (event.getSource()==bttCheckLibrarians) {
	    		bttCheckLibrarians.getScene().setRoot(librariansAllPage());
	    	}
	    	
	    });
	    bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot(mainPage());	
            }
	    });
    	
	    bttbookStatistics.setOnAction(event ->{
	    	bttbookStatistics.getScene().setRoot(managerStatisticsPage());
	    });
	    
    	return borderManagerMain;    	
    	
    }
    
    public BorderPane managerStatisticsPage() {
    	
    	BorderPane border = new BorderPane();

    	
    	Text text = new Text("Book Statistics");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		Button bttSold = new Button("Sold");
		Button bttBought = new Button("Bought");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(bttSold, 0, 0);
		grid.add(bttBought, 1, 0);
		border.setCenter(grid);
		
		
		bttSold.setOnAction(event ->{
			bttSold.getScene().setRoot(managerSoldPage());
		});
		
		bttBought.setOnAction(event -> {
			bttBought.getScene().setRoot(managerBoughtPage());
		});
		
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( mangaerMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		return border;
    	
    }
    
    public BorderPane managerBoughtPage() {
    	
    	 BorderPane border = new BorderPane();
     	 
         ArrayList<Book> stockBooks = billNumber.getStockBooks(FILE_PATH);
        for (Book stockBook : stockBooks) {
            if (stockBook.getQuantitiesPurchased() > 0) {
                titlesBought.add(stockBook.getTitle());
                quantitiesBought.add(stockBook.getQuantitiesPurchased());
            }
        }
        
    	 
    	 PieChart pieChart = new PieChart();
         billNumber.removeDuplicatesSoldTitles(titlesBought,quantitiesBought);
         
     	for (int i=0;i<titlesBought.size();i++) {
     		Data test = new Data(titlesBought.get(i), quantitiesBought.get(i));
     		pieChart.getData().add(test);
     	}
     	
     	Text text = new Text("Bought books throughout day/month/year/total");
 		StackPane stack = new StackPane();
 		text.setFont(new Font(30));
 		stack.getChildren().add(text);
 		stack.setPadding(new Insets(20));
 		border.setTop(stack);
 		
 	
 		Text text1 = new Text(billNumber.getBooksBoughtDay(FILE_PATH));
 		Text text2 = new Text(billNumber.getBooksBoughtMonth(FILE_PATH));
 		Text text3 = new Text(billNumber.getBooksBoughtYear(FILE_PATH));
 		Text text4 = new Text(billNumber.getBooksBoughtTotal(FILE_PATH));
 		
 		GridPane grid = new GridPane();
 		grid.add(text1, 0, 0);
		grid.add(text2, 1, 0);
		grid.add(text3, 2, 0);
		grid.add(pieChart, 1, 1);
		grid.setHgap(30);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
		border.setCenter(grid);
 		
 		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( managerStatisticsPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
 		
 		return border;
    	
    }
    
    
    public BorderPane managerSoldPage() {
    	
        BorderPane border = new BorderPane();
               
        PieChart pieChart = new PieChart();
        ArrayList<Book> stockBooks = billNumber.getStockBooks(FILE_PATH);
        for (Book stockBook : stockBooks) {
            if (stockBook.getPurchasedAmount() > 0) {
                titlesSold.add(stockBook.getTitle());
                quantitiesSold.add(stockBook.getPurchasedAmount());
            }
        }
        billNumber.removeDuplicatesSoldTitles(titlesSold,quantitiesSold);
        
    	for (int i=0;i<titlesSold.size();i++) {
    		Data test = new Data(titlesSold.get(i), quantitiesSold.get(i));
    		pieChart.getData().add(test);
    	}
    	
    	Text text = new Text("Sold books throughout day/month/year/total");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		Text text1 = new Text(billNumber.getBooksSoldDay(FILE_PATH));
		Text text2 = new Text(billNumber.getBooksSoldMonth(FILE_PATH));
		Text text3 = new Text(billNumber.getBooksSoldYear(FILE_PATH));
		Text text4 = new Text(billNumber.getBooksSoldTotal(FILE_PATH));
		
		GridPane grid = new GridPane();
		
		grid.add(text1, 0, 0);
		grid.add(text2, 1, 0);
		grid.add(text3, 2, 0);
		grid.add(pieChart, 1, 1);
		grid.setHgap(30);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
		border.setCenter(grid);
		
			
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( managerStatisticsPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		return border;
    	
    }
    
    
    public BorderPane librariansAllPage() {
    	
    	BorderPane border = new BorderPane();
    	
    	Text textHeaderManager = new Text("Select Librarians");
		StackPane stackHeader = new StackPane();
		textHeaderManager.setFont(new Font(30));
		stackHeader.getChildren().add(textHeaderManager);
		stackHeader.setPadding(new Insets(20));
		border.setTop(stackHeader);
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBack);
		bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot( mangaerMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		
		ArrayList<Librarian> librarians = Manager.getLibrarians();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	int k=0;
     	int j=0;
     	
		for (int i=0;i<librarians.size();i++) {
    		if (i%5==0) {
    			k=0;
    			j++;
    		}
    			
    		
    		Button button = createButton3(librarians.get(i));
    		
			grid.add(button,k++,j);
			
    	}
    	border.setCenter(grid);
    	
    	
    	return border;
    	
    	
    }
    
    //------------------------------------------------------------------
    
    
    private Button createButton3(Librarian lib) {
    	Button button;
  
    	if (lib.getName()==null) 
    		button = new Button(lib.getUsername());
    	
    	else 
    		button = new Button(lib.getName());
    	
        
        button.setOnAction(new ButtonHandler3(lib));
        return button ;
    }
    
    class ButtonHandler3 implements EventHandler<ActionEvent> {
    	
    	private final Librarian lib;
    	
        ButtonHandler3(Librarian lib) {
            this.lib = lib;
        }
        
        @Override
        public void handle(ActionEvent event) {
        	Stage stage = new Stage();
        	Scene scene = new Scene(selectLibrarianPage(lib));
        	stage.setWidth(465);
        	stage.setHeight(465);
        	stage.setScene(scene);
        	stage.show();
        	
        }

    }
    
    
    //------------------------------------------------------------------
    
    
    public BorderPane selectLibrarianPage(Librarian lib) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text;
    	if (lib.getName() == null) 
    		text = new Text("Performance of: "+lib.getUsername());
    	else
    		text = new Text("Performance of: "+lib.getName());
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textTotalNumberBillsLibrarian, 0, 0);
     	grid.add(totalNumberBillsLibrarian, 1, 0);
     	
     	grid.add(textBooksSold, 0, 1);
     	grid.add(booksSold, 1, 1);
     	
     	grid.add(textTotalMoneyDay, 0, 2);
     	grid.add(totalAmountOfMoneyMadeInDay, 1, 2);

     	grid.add(textTotalMoneyMonth, 0, 3);
     	grid.add(totalAmountOfMoneyMadeInMonth, 1, 3);
     	
     	grid.add(textTotalMoneyYear, 0, 4);
     	grid.add(totalAmountOfMoneyMadeInYear, 1, 4);
     	
     	totalNumberBillsLibrarian.setEditable(false);
     	booksSold.setEditable(false);
     	totalAmountOfMoneyMadeInDay.setEditable(false);
     	totalAmountOfMoneyMadeInMonth.setEditable(false);
     	totalAmountOfMoneyMadeInYear.setEditable(false);
     	
     	totalNumberBillsLibrarian.setText(Integer.toString(lib.getNumberOfBills()));
     	booksSold.setText(Integer.toString(lib.getBooksSold()));
     	totalAmountOfMoneyMadeInDay.setText( Double.toString(lib.moneyMadeInDay()) );
     	totalAmountOfMoneyMadeInMonth.setText( Double.toString(lib.moneyMadeInMonth()) );
     	totalAmountOfMoneyMadeInYear.setText( Double.toString(lib.moneyMadeInYear()) );
    
     	border.setCenter(grid);
    	
    	return border;
    }
    

    
    
    public BorderPane checkStoragePage() {
    	
    	BorderPane border = new BorderPane();
    	TableView table = new TableView();
    	TableColumn<Book,String> column1 = new TableColumn<>("ISBN");
    	TableColumn<Book,String> column2 = new TableColumn<>("Title");
    	TableColumn<Book,String> column3 = new TableColumn<>("Category");
    	TableColumn<Book,String> column4 = new TableColumn<>("Author");
    	TableColumn<Book,Double> column5 = new TableColumn<>("Original Price");
    	TableColumn<Book,Double> column6 = new TableColumn<>("Selling Price");
    	TableColumn<Book,String> column7 = new TableColumn<>("Supplier");
    	TableColumn<Book,Integer> column8 = new TableColumn<>("Stock");
    	
    	column1.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
    	column2.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
    	column3.setCellValueFactory(new PropertyValueFactory<Book,String>("category"));
    	column4.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
    	column5.setCellValueFactory(new PropertyValueFactory<Book,Double>("originalPrice"));
    	column6.setCellValueFactory(new PropertyValueFactory<Book,Double>("sellingPrice"));
    	column7.setCellValueFactory(new PropertyValueFactory<Book,String>("supplier"));
    	column8.setCellValueFactory(new PropertyValueFactory<Book,Integer>("stock"));
    	
    	table.getColumns().add(column1);
    	table.getColumns().add(column2);
    	table.getColumns().add(column3);
    	table.getColumns().add(column4);
    	table.getColumns().add(column5);
    	table.getColumns().add(column6);
    	table.getColumns().add(column7);
    	table.getColumns().add(column8);
    	
    	table.setItems(FXCollections.observableArrayList(billNumber.getStockBooks(FILE_PATH)));
    	
    	border.setCenter(table);
    	    	   	
    	Text text = new Text("Stock");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBack);
		bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot( mangaerMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(40, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		border.setTop(stack);
    
    	return border;
    	
    }
    
    
    public BorderPane ManagerSupplyCickPage() {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add Stock or New Books");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
    	
		GridPane supplyPageGrid = new GridPane();
		supplyPageGrid.setHgap(5);
		supplyPageGrid.setVgap(5);
		supplyPageGrid.add(bttAddStock, 0, 0);
		supplyPageGrid.add(bttNewCategory, 1, 0);
		supplyPageGrid.add(bttBack, 2, 0);
		
		bttAddStock.setOnAction(this);
		bttNewCategory.setOnAction(this);
		bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot(mangaerMainPage());	
            }
	    });
		
		supplyPageGrid.setAlignment(Pos.CENTER);
        border.setCenter(supplyPageGrid);
    	
		
    	return border;
    }
    
    public BorderPane chooseNewCategoryAddStock() {
    	
    	ChoiceBox menuNewCategory = new ChoiceBox(FXCollections.observableArrayList(Manager.getAllCategories()));
    	ArrayList<Book> stockbooks = billNumber.getStockBooks(FILE_PATH);
    
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add new Book Category");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		TextField textAddCategoryWarning = new TextField();
		textAddCategoryWarning.setEditable(false);
		
		Button bttAddCategory = new Button("Add");
		bttBack = new Button("Back");
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textCategory, 0, 0);     	
     	grid.add(menuNewCategory, 1, 0);
     	grid.add(bttBack, 0, 5);
     	grid.add(textSystem, 0, 4);
     	grid.add(textAddCategoryWarning, 1, 4);
     	grid.add(bttAddCategory, 2, 5);
     	border.setCenter(grid);
     	
     	bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
            	bttBack.getScene().setRoot( ManagerSupplyCickPage()  );	
            }
	    });
     	
     	bttAddCategory.setOnAction(event -> {
     		
     		if (event.getSource()==bttAddCategory) {
     			
                 
     			
     			if (menuNewCategory.getSelectionModel().getSelectedItem().toString().isEmpty()) {
     				textAddCategoryWarning.setText("Failed, Empty field");
     				return;
     			}
     			
     			if (category.getCharacters().toString().matches("\\d+")) {
     				textAddCategoryWarning.setText("Failed, Invalid Category");
     				return;
     			}
     			if (billNumber.partOfCateogriesChecker(categ,menuNewCategory.getSelectionModel().getSelectedItem().toString())) {
     				textAddCategoryWarning.setText("Failed, Not New");
     				return;
     			}
     			
     			
     			
     			String category = menuNewCategory.getSelectionModel().getSelectedItem().toString();
     			categ.add(category);
     			textAddCategoryWarning.setText("Added!");
     			
     			
     		}
     		
     		
     	});
     	
     	
     	
		return border;
    	
    	
    }
    
    public BorderPane chooseAddCurretStock() {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Choose category to add");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( ManagerSupplyCickPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		
		categ = billNumber.removeDuplicates(categ);
		
        
        GridPane grid = new GridPane();
     	grid.setHgap(5);
     	grid.setVgap(5);
     	int j=0;
     	int k=0;
    	
    	for (int i=0;i<categ.size();i++) {
    		if (i%5==0) {
    			k=0;
    			j++;
    		}
    			
    		
    		Button button = createButton(categ.get(i));
    		
			grid.add(button,k++,j);
			
    	}
    	
    	
    	
    	grid.setAlignment(Pos.CENTER);
    	border.setCenter(grid);
    	
    	
    	return border;
    }
    
    
    //----------------------------------------------------------------------------
    
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setOnAction(new ButtonHandler(button.getText()));
        return button ;
    }
    
    class ButtonHandler implements EventHandler<ActionEvent> {
    	
        private final String text;
        
        ButtonHandler(String text) {
            this.text = text ;
        }
        
        @Override
        public void handle(ActionEvent event) {
        	Stage stage = new Stage();
        	Scene scene = new Scene(categoryStock(text));
        	stage.setWidth(465);
        	stage.setHeight(465);
        	stage.setScene(scene);
        	stage.show();
        	
        }

    }
    //-----------------------------------------------------------------------
    
    
    public BorderPane categoryStock(String category) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Choose Book to add");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		
		ArrayList<Book> books = billNumber.getBookFromCategory(FILE_PATH, category);
		GridPane grid = new GridPane();
     	grid.setHgap(5);
     	grid.setVgap(5);
     	int j=0;
     	int k=-1;
		
     	if (!books.isEmpty()) {
     		for (int i=0;i<books.size();i++) {
        		if (i%5==0) {
        			k=-1;
        			j++;
        		}
        		Button button = createButton2(books.get(i));
        		
    			grid.add(button,++k,j);
    			
        	}
     	}
		
		grid.add(bttNewBook, ++k, j);
        grid.setAlignment(Pos.CENTER);
    	border.setCenter(grid);
    	
    	bttNewBook.setOnAction(event -> {
			
            if(event.getSource()==bttNewBook) {
            	bookISBN.clear();
     		    title.clear();
     		    supplier.clear();
     		    originalPrice.clear();
     		    sellingPrice.clear();
     		    author.clear();
     		    quantity.clear();
            	
            	
            	bttNewBook.getScene().setRoot( stockCategoryNewBookPage(category) );
            }
   
            
 	    });
    	
    	
    	return border;
    	
    	
    }
    
    //-------------------------------------------------------------------
    
    private Button createButton2(Book book) {
        Button button = new Button(book.getTitle());
        button.setOnAction(new ButtonHandler2(book));
        return button ;
    }
    
    class ButtonHandler2 implements EventHandler<ActionEvent> {
    	
    	private final Book book;
    	
        ButtonHandler2(Book book) {
            this.book = book;
        }
        
        @Override
        public void handle(ActionEvent event) {
        	Stage stage = new Stage();
        	Scene scene = new Scene(stockBookPage(book));
        	stage.setWidth(465);
        	stage.setHeight(465);
        	stage.setScene(scene);
        	stage.show();
        	
        }

    }
    
    //-------------------------------------------------------------------
    
    
    public BorderPane stockBookPage(Book book) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add \""+book.getTitle()+"\" To stock");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		
		
		GridPane grid = new GridPane();
     	grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textQuantity, 0, 0);
     	grid.add(quantity, 1, 0);
     	grid.add(bttAddBookToStock,2, 4);
     	grid.add(addedOrNot, 1, 3);
     	grid.add(textSystem, 0, 3);
    	border.setCenter(grid);
    	
		
    	addedOrNot.setEditable(false);
		bttAddBookToStock.setOnAction(event -> {
			
            if(event.getSource()==bttAddBookToStock) {
            	
            	if(!quantity.getCharacters().toString().matches("\\d+")){
    				addedOrNot.setText("Failed, Invalid Quantity");
    				return;
    			}
    			
    			if (quantity.getCharacters().toString().isEmpty()) {
    				addedOrNot.setText("Failed, Empty Quantity");
    				return;
    			}
    			
    		    ArrayList<Book> stockbooks = billNumber.getStockBooks(FILE_PATH);
    		    
    		    for (int i=0;i<stockbooks.size();i++) {
    		    	if (stockbooks.get(i).getISBN().equals(book.getISBN())) {
    		    		
    		    		stockbooks.get(i).AddStock(Integer.parseInt(quantity.getCharacters().toString()));
    		    		stockbooks.get(i).addPurchasedDate(new Date());
    		    		stockbooks.get(i).addQuantitiesPurchased(Integer.parseInt(quantity.getCharacters().toString()));
    		    	}    		    	   
    		    }
    		    
    		    try {
					billNumber.updateBooks(FILE_PATH, stockbooks);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		    
    		    quantity.clear();
    		    addedOrNot.setText("Added");
             }
            
 	    });
    	
    	
    	
    	return border;
    	
    	
    }
    
    
    public BorderPane stockCategoryNewBookPage(String category) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add book to "+category+" category");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		GridPane gridSupplier = new GridPane();
		ToggleGroup Supplier = new ToggleGroup();
		RadioButton r1 = new RadioButton("Ingram Content Group, Inc");
	    RadioButton r2 = new RadioButton("Baker & Taylor");
        RadioButton r3 = new RadioButton("BCH Fulfillment & Distribution");
        RadioButton r4 = new RadioButton("Cardinal Publishers Group");
        RadioButton r5 = new RadioButton("Bella Distribution");
        RadioButton r6 = new RadioButton("Publishers Group West");
	    r1.setToggleGroup(Supplier);
	    r2.setToggleGroup(Supplier);
	    r3.setToggleGroup(Supplier);
	    r4.setToggleGroup(Supplier);
	    r5.setToggleGroup(Supplier);
	    r6.setToggleGroup(Supplier);
	    gridSupplier.add(r1, 0, 0);
	    gridSupplier.add(r2, 1, 0);
	    gridSupplier.add(r3, 1, 2);
	    gridSupplier.add(r4, 0, 1);
	    gridSupplier.add(r5, 0, 2);
	    gridSupplier.add(r6, 1, 1);
	    gridSupplier.setHgap(5);
	    gridSupplier.setVgap(5);
	  
	       
	 
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textBookISBN, 0, 0);
     	grid.add(bookISBN, 1, 0);
     	grid.add(textTitle,0, 1);
     	grid.add(title, 1, 1);
     	grid.add(textSupplier, 0, 2);
     	grid.add(gridSupplier, 1, 2);
     	grid.add(textOriginalPrice, 0, 3);
     	grid.add(originalPrice, 1, 3);
     	grid.add(textSellingPrice,0,4);
     	grid.add(sellingPrice, 1, 4);
     	grid.add(textAuthor, 0, 5);
     	grid.add(author, 1, 5);
     	grid.add(textQuantity, 0, 6);
     	grid.add(quantity, 1, 6);
     	grid.add(textSystem, 0, 9);
     	grid.add(addedOrNotStockCategory, 1, 9);
     	grid.add(stockCategoryAddBook, 2, 10);
     	grid.add(bttBack, 0, 10);
     	
     	
     	addedOrNotStockCategory.setEditable(false);
     	bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
            	bttBack.getScene().setRoot( categoryStock(category) );
            }
            
 	    });
     	stockCategoryAddBook.setOnAction(event -> {
            if(event.getSource()==stockCategoryAddBook) {
            	
            	RadioButton chk = (RadioButton)Supplier.getSelectedToggle(); 

    			if (bookISBN.getCharacters().toString().isEmpty() || title.getCharacters().toString().isEmpty() || Supplier.getSelectedToggle() == null
    					|| originalPrice.getCharacters().toString().isEmpty() || sellingPrice.getCharacters().toString().isEmpty() || author.getCharacters().toString().isEmpty()
    					|| quantity.getCharacters().toString().isEmpty()) {
    				addedOrNotStockCategory.setText("Failed, Empty Fields");
    				return;
    			}
    			
    			if (!bookISBN.getCharacters().toString().matches("\\d{13}")) {
    				addedOrNotStockCategory.setText("Failed, Invalid ISBN");
    				return;
    			}
    			
    			if ( !(quantity.getCharacters().toString().matches("\\d+")) || !(originalPrice.getCharacters().toString().matches("\\d+"))
    					|| !(sellingPrice.getCharacters().toString().matches("\\d+")) ||  Integer.parseInt(sellingPrice.getCharacters().toString())==0
    					|| Integer.parseInt(originalPrice.getCharacters().toString()) == 0 || Integer.parseInt(quantity.getCharacters().toString()) == 0) {
    				
    				addedOrNotStockCategory.setText("Failed, Invalid Numbers");
    				return;
    			}
    			
    			if (billNumber.isPartOfBooks(FILE_PATH, bookISBN.getCharacters().toString())){
    				addedOrNotStockCategory.setText("Failed, Already In Stock");
    				return;
    				
    			}
    			
    			String bIsbn = bookISBN.getCharacters().toString();
    			String bTitle = title.getCharacters().toString();
    			String bSupplier = chk.getText();
    			int bSellingPrice = Integer.parseInt(sellingPrice.getCharacters().toString());
    			int bOriginalPrice = Integer.parseInt(originalPrice.getCharacters().toString());
    			String bAuthor = author.getCharacters().toString();
    			int bStock = Integer.parseInt(quantity.getCharacters().toString());
    			
    		    Book book = new Book(bIsbn,bTitle,category,bSupplier,bOriginalPrice,bSellingPrice,bAuthor,bStock);
    		    book.addQuantitiesPurchased(bStock);
    		    date = new Date();
    		    book.addPurchasedDate(date);
            	
    		    ArrayList<Book> stockbooks = billNumber.getStockBooks(FILE_PATH);
    		    stockbooks.add(book);
    		    try {
					billNumber.updateBooks(FILE_PATH, stockbooks);
				} catch (IOException e) {
					System.err.println("An error occurred while updating books: " + e.getMessage());
				}
    		    
    		    bookISBN.clear();
    		    title.clear();
    		    Supplier.getSelectedToggle().setSelected(false);
    		    originalPrice.clear();
    		    sellingPrice.clear();
    		    author.clear();
    		    quantity.clear();
    		    
    		    
    		    addedOrNotStockCategory.setText("Added");
    		    
            }
            
 	    });
     	
     	border.setCenter(grid);
		
		
    	
    	return border;
    }
    
    //------------------------------------------------------------------------Administrator--------------------------------------------------------------------------
    
    
    
    public BorderPane administratorMainPage() {
    	BorderPane border = new BorderPane();
    	
    	
    	Text text = new Text("Manage Employees");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		Button bttManageManager = new Button("Manage Managers");
		Button bttManageLibrarians = new Button("Manage Librarians");
		Button bttStats = new Button("Stats");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
	    grid.setVgap(5);
	    grid.add(bttManageLibrarians, 0, 0);
	    grid.add(bttManageManager, 1, 0);
	    grid.add(bttStats, 2, 0);
	    grid.add(bttBack, 3, 0);
	    border.setCenter(grid);
	    
	    
	    bttManageLibrarians.setOnAction(event ->{
	    	if (event.getSource() == bttManageLibrarians) {
	    		bttManageLibrarians.getScene().setRoot(administratorManageLibrariansPage());
	    	}
	    });
	    
	    bttManageManager.setOnAction(event ->{
	    	bttManageManager.getScene().setRoot(administratorManagerPage());
	    });
	    
	    bttStats.setOnAction(event ->{
	    	bttStats.getScene().setRoot(administratorStatPage());
	    });
	    
	    bttBack.setOnAction(event-> {
	    	if (event.getSource() == bttBack) {
	    		username.clear();
	    		password.clear();
	    		bttBack.getScene().setRoot(mainPage());
	    	}
	    });
		
    	
    	return border;
    }
    
    public BorderPane administratorManageLibrariansPage(){
    	
        BorderPane border = new BorderPane();
    	
    	Text textHeaderManager = new Text("Select Librarians");
		StackPane stackHeader = new StackPane();
		textHeaderManager.setFont(new Font(30));
		stackHeader.getChildren().add(textHeaderManager);
		stackHeader.setPadding(new Insets(20));
		border.setTop(stackHeader);
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBack);
		bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot( administratorMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		Button bttAddNew = new Button("Add New");
		ArrayList<Librarian> librarians = Manager.getLibrarians();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	int k=0;
     	int j=0;
     	
		for (int i=0;i<librarians.size();i++) {
    		if (i%5==0) {
    			k=0;
    			j++;
    		}
    			
    		
    		Button button = createButton4(librarians.get(i));
    		
			grid.add(button,k++,j);
			
    	}
		grid.add(bttAddNew, k, j);
    	border.setCenter(grid);
    	
    	bttAddNew.setOnAction(event-> {
    		bttAddNew.getScene().setRoot(addLibrarian());
    	});
    	
    	
    	return border;
    }
    
    //-----------------------------------------------
    
    private Button createButton4(Librarian lib) {
    	Button button;
  
    	if (lib.getName()==null) 
    		button = new Button(lib.getUsername());
    	
    	else 
    		button = new Button(lib.getName());
    	
        
        button.setOnAction(new ButtonHandler4(lib));
        return button ;
    }
    
    class ButtonHandler4 implements EventHandler<ActionEvent> {
    	
    	private final Librarian lib;
    	
        ButtonHandler4(Librarian lib) {
            this.lib = lib;
        }
        
        @Override
        public void handle(ActionEvent event) {
        	Stage stage = new Stage();
        	Scene scene = new Scene(editLibrarianPage(lib));
        	stage.setWidth(465);
        	stage.setHeight(465);
        	stage.setScene(scene);
        	stage.show();
        	
        }

    }
    
    //-------------------------------------------------
    
    
    public BorderPane addLibrarian() {
    	
        BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add new Librarian");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		TextField name = new TextField();
		Text textName = new Text("Name");
		TextField salary = new TextField();
		Text textSalary = new Text("Salary");
		TextField phone = new TextField();
		Text textPhone = new Text("Phone");
		TextField email = new TextField();
		Text textEmail = new Text("Email");
		TextField username = new TextField();
		Text textUsername = new Text("Username");
		TextField password = new TextField();
		Text textPassword = new Text("Password");
		Button bttAdd = new Button("Submit");
		Button bttBack = new Button("Back");
		TextField libWarningNew = new TextField();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textName,0,0);
	    grid.add(name,1,0);
	    grid.add(textPassword,0,1);
	    grid.add(password,1,1);
	    grid.add(textUsername,0,2);
	    grid.add(username, 1, 2);
	    grid.add(textSalary, 0, 3);
	    grid.add(salary, 1, 3);
	    grid.add(textPhone, 0, 4);
	    grid.add(phone, 1, 4);
	    grid.add(textEmail, 0, 5);
	    grid.add(email, 1, 5);
	    grid.add(textSystem, 0, 8);
	    grid.add(libWarningNew, 1, 8);
	    grid.add(bttAdd, 3, 9);
	    grid.add(bttBack, 0, 9);
	    border.setCenter(grid);
	    
	    bttBack.setOnAction(event -> {
	    	bttBack.getScene().setRoot(administratorManageLibrariansPage());
	    });
	    
	    bttAdd.setOnAction(event -> {
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty() || name.getCharacters().isEmpty()) {
	    		libWarningNew.setText("Failed, Empty Fields!");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkName(name.getCharacters().toString()))) {
	    		libWarningNew.setText("Invalid Name");
	    		name.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		libWarningNew.setText("Invalid email");
	    		email.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		libWarningNew.setText("Invalid password");
	    		password.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		libWarningNew.setText("Invalid salary");
	    		salary.clear();
	    		return;
	    	}
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		libWarningNew.setText("Invalid phone");
	    		phone.clear();
	    		return;
	    	}
	    	
	    	
	    	librarian = new Librarian( username.getCharacters().toString(), password.getCharacters().toString(), name.getCharacters().toString(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	Manager.AddLibrarian(librarian);
	    	libWarningNew.setText("Success!");
	    	
	    	
	    	username.clear();
	    	name.clear();
	    	password.clear();
	    	salary.clear();
	    	phone.clear();
	    	email.clear();
	    	
	    	
	    
	    });
		

	    return border;
		
		
    	
    }
    
    public BorderPane editLibrarianPage(Librarian lib) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Edit Librarian");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		TextField libLoginWarning = new TextField();
		
		TextField name = new TextField();
		Text textName = new Text("Name");
		TextField salary = new TextField();
		Text textSalary = new Text("Salary");
		TextField phone = new TextField();
		Text textPhone = new Text("Phone");
		TextField email = new TextField();
		Text textEmail = new Text("Email");
		TextField username = new TextField();
		Text textUsername = new Text("Username");
		TextField password = new TextField();
		Text textPassword = new Text("Password");
		Button bttSubmit = new Button("Submit");
		Button bttDelete = new Button("Delete");
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textName,0,0);
	    grid.add(name,1,0);
	    grid.add(textPassword,0,1);
	    grid.add(password,1,1);
	    grid.add(textUsername,0,2);
	    grid.add(username, 1, 2);
	    grid.add(textSalary, 0, 3);
	    grid.add(salary, 1, 3);
	    grid.add(textPhone, 0, 4);
	    grid.add(phone, 1, 4);
	    grid.add(textEmail, 0, 5);
	    grid.add(email, 1, 5);
	    grid.add(textTotalNumberBillsLibrarian, 2, 0);
	    grid.add(totalNumberBillsLibrarian, 3, 0);
	    grid.add(textBooksSold, 2, 1);
	    grid.add(booksSold, 3, 1);
	    grid.add(textTotalMoneyDay, 2, 2);
	    grid.add(totalAmountOfMoneyMadeInDay, 3, 2);
	    grid.add(textTotalMoneyMonth, 2, 3);
	    grid.add(totalAmountOfMoneyMadeInMonth, 3, 3);	    
     	grid.add(textTotalMoneyYear, 2, 4);
     	grid.add(totalAmountOfMoneyMadeInYear, 3, 4);
     	grid.add(textSystem, 0, 8);
     	grid.add(libLoginWarning, 1, 8);
     	
     	HBox hbox = new HBox();
     	hbox.getChildren().addAll(bttSubmit,bttDelete);
     	hbox.setAlignment(Pos.CENTER);
     	hbox.setSpacing(5);
     	hbox.setPadding(new Insets(0,0,20,0));
     	border.setBottom(hbox);
     	
     	totalNumberBillsLibrarian.setEditable(false);
     	booksSold.setEditable(false);
     	totalAmountOfMoneyMadeInDay.setEditable(false);
     	totalAmountOfMoneyMadeInMonth.setEditable(false);
     	totalAmountOfMoneyMadeInYear.setEditable(false);
     	libLoginWarning.setEditable(false);
  	
     	totalNumberBillsLibrarian.setText(Integer.toString(lib.getNumberOfBills()));
     	booksSold.setText(Integer.toString(lib.getBooksSold()));
     	totalAmountOfMoneyMadeInDay.setText( Double.toString(lib.moneyMadeInDay()) );
     	totalAmountOfMoneyMadeInMonth.setText( Double.toString(lib.moneyMadeInMonth()) );
     	totalAmountOfMoneyMadeInYear.setText( Double.toString(lib.moneyMadeInYear()) );
	    
	    name.setEditable(false);
	    name.setText(lib.getName());
	    username.setText(lib.getUsername());
	    password.setText(lib.getPassword());
	    salary.setText(Double.toString(lib.getSalary()));
	    email.setText(lib.getEmail());
	    phone.setText(lib.getPhone());
	    
	    bttDelete.setOnAction(event ->{
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty()) {
	    		libLoginWarning.setText("Empty Fields");
	    		return;
	    	}
	    	
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		email.clear();
	    		libLoginWarning.setText("Invalid Email");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		password.clear();
	    		libLoginWarning.setText("Invalid Password");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		phone.clear();
	    		libLoginWarning.setText("Invalid Phone Number");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		salary.clear();
	    		libLoginWarning.setText("Invalid Salary");
	    		return;
	    	}
	    	
	    	librarian = new Librarian( username.getCharacters().toString(), password.getCharacters().toString(), lib.getName(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	Manager.deleteLibrarian(librarian);
	    	username.clear();
	    	name.clear();
	    	password.clear();
	    	salary.clear();
	    	phone.clear();
	    	email.clear();
	    	totalNumberBillsLibrarian.clear();
	    	booksSold.clear();
	    	totalAmountOfMoneyMadeInDay.clear();
	    	totalAmountOfMoneyMadeInMonth.clear();
	    	totalAmountOfMoneyMadeInYear.clear();
	    	libLoginWarning.setText("Deleted Successfully!");
	    	
	    	
	    	
	    });
	    
	    bttSubmit.setOnAction(event ->{
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty()) {
	    		libLoginWarning.setText("Empty Fields");
	    		return;
	    	}
	    	
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		email.clear();
	    		libLoginWarning.setText("Invalid Email");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		password.clear();
	    		libLoginWarning.setText("Invalid Password");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		phone.clear();
	    		libLoginWarning.setText("Invalid Phone Number");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		salary.clear();
	    		libLoginWarning.setText("Invalid Salary");
	    		return;
	    	}
	    	
	    	librarian = new Librarian( username.getCharacters().toString(), password.getCharacters().toString(), lib.getName(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	
	    	Manager.updateLibrarians(librarian);
	    	libLoginWarning.setText("Success!");    	
	    	
	    });
	    
	    
	    
	    border.setCenter(grid);
    	
    	return border;
    }
    
    
    public BorderPane administratorManagerPage() {
    	
        BorderPane border = new BorderPane();
    	
    	Text text = new Text("Select Manager");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBack);
		bttBack.setOnAction(event -> {
            if(event.getSource()==bttBack) {
               bttBack.getScene().setRoot( administratorMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		Button bttAddNew = new Button("Add New");
		ArrayList<Manager> managers = Administrator.getManagers();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	int k=0;
     	int j=0;
     	
		for (int i=0;i<managers.size();i++) {
    		if (i%5==0) {
    			k=0;
    			j++;
    		}
    			
    		
    		Button button = createButton5(managers.get(i));
    		
			grid.add(button,k++,j);
			
    	}
		grid.add(bttAddNew, k, j);
    	border.setCenter(grid);
    	
    	bttAddNew.setOnAction(event-> {
    		bttAddNew.getScene().setRoot(addManager());
    	});
		
		
		return border;
    	
    }
    
    
    //----------------------------------------------------------------------
    
    
    private Button createButton5(Manager mag) {
    	Button button;
  
 
   		button = new Button(mag.getName());
    	
        
        button.setOnAction(new ButtonHandler5(mag));
        return button ;
    }
    
    class ButtonHandler5 implements EventHandler<ActionEvent> {
    	
    	private final Manager mag;
    	
        ButtonHandler5(Manager mag) {
            this.mag = mag;
        }
        
        @Override
        public void handle(ActionEvent event) {
        	Stage stage = new Stage();
        	Scene scene = new Scene( editManagerPage(mag) );
        	stage.setWidth(465);
        	stage.setHeight(465);
        	stage.setScene(scene);
        	stage.show();
        	
        }

    }
    
    //---------------------------------------------------------------------
    
    
    public BorderPane addManager() {
    	
    	
        BorderPane border = new BorderPane();
    	
    	Text text = new Text("Add new Manager");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		TextField name = new TextField();
		Text textName = new Text("Name");
		TextField salary = new TextField();
		Text textSalary = new Text("Salary");
		TextField phone = new TextField();
		Text textPhone = new Text("Phone");
		TextField email = new TextField();
		Text textEmail = new Text("Email");
		TextField username = new TextField();
		Text textUsername = new Text("Username");
		TextField password = new TextField();
		Text textPassword = new Text("Password");
		Button bttAdd = new Button("Submit");
		Button bttBack = new Button("Back");
		TextField magWarningNew = new TextField();
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textName,0,0);
	    grid.add(name,1,0);
	    grid.add(textUsername,0,1);
	    grid.add(username,1,1);
	    grid.add(textPassword,0,2);
	    grid.add(password, 1, 2);
	    grid.add(textSalary, 0, 3);
	    grid.add(salary, 1, 3);
	    grid.add(textPhone, 0, 4);
	    grid.add(phone, 1, 4);
	    grid.add(textEmail, 0, 5);
	    grid.add(email, 1, 5);
	    grid.add(textSystem, 0, 8);
	    grid.add(magWarningNew, 1, 8);
	    grid.add(bttAdd, 3, 9);
	    grid.add(bttBack, 0, 9);
	    border.setCenter(grid);
	    
	    bttBack.setOnAction(event -> {
	    	bttBack.getScene().setRoot( administratorManagerPage());
	    });
	    
	    bttAdd.setOnAction(event -> {
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty() || name.getCharacters().isEmpty()) {
	    		magWarningNew.setText("Failed, Empty Fields!");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkName(name.getCharacters().toString()))) {
	    		magWarningNew.setText("Invalid Name");
	    		name.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		magWarningNew.setText("Invalid email");
	    		email.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		magWarningNew.setText("Invalid password");
	    		password.clear();
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		magWarningNew.setText("Invalid salary");
	    		salary.clear();
	    		return;
	    	}
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		magWarningNew.setText("Invalid phone");
	    		phone.clear();
	    		return;
	    	}
	    	
	    	
	    	manager = new Manager( username.getCharacters().toString(), password.getCharacters().toString(), name.getCharacters().toString(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	Administrator.AddManager(manager);
	    	magWarningNew.setText("Success!");
	    	
	    	username.clear();
	    	name.clear();
	    	password.clear();
	    	salary.clear();
	    	phone.clear();
	    	email.clear();
	    	
	    });
		

	    return border;
		
    	
    	
    }
    
    
    public BorderPane editManagerPage(Manager mag) {
    	
    	BorderPane border = new BorderPane();
    	
    	Text text = new Text("Edit Manager");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		magLoginWarning.clear();
		
		TextField name = new TextField();
		Text textName = new Text("Name");
		TextField salary = new TextField();
		Text textSalary = new Text("Salary");
		TextField phone = new TextField();
		Text textPhone = new Text("Phone");
		TextField email = new TextField();
		Text textEmail = new Text("Email");
		TextField username = new TextField();
		Text textUsername = new Text("Username");
		TextField password = new TextField();
		Text textPassword = new Text("Password");
		Button bttSubmit = new Button("Submit");
		Button bttDelete = new Button("Delete");
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
     	grid.setVgap(5);
     	grid.setAlignment(Pos.CENTER);
     	grid.add(textName,0,0);
	    grid.add(name,1,0);
	    grid.add(textPassword,0,1);
	    grid.add(password,1,1);
	    grid.add(textUsername,0,2);
	    grid.add(username, 1, 2);
	    grid.add(textSalary, 0, 3);
	    grid.add(salary, 1, 3);
	    grid.add(textPhone, 0, 4);
	    grid.add(phone, 1, 4);
	    grid.add(textEmail, 0, 5);
	    grid.add(email, 1, 5);
	    grid.add(textSystem, 0, 8);
	    grid.add(magLoginWarning, 1, 8);
     	
     	HBox hbox = new HBox();
     	hbox.getChildren().addAll(bttSubmit,bttDelete);
     	hbox.setAlignment(Pos.CENTER);
     	hbox.setSpacing(5);
     	hbox.setPadding(new Insets(0,0,20,0));
     	border.setBottom(hbox);
     	
     	
     	magLoginWarning.setEditable(false);	    
	    name.setEditable(false);
	    
	    name.setText(mag.getName());
	    username.setText(mag.getUsername());
	    password.setText(mag.getPassword());
	    salary.setText(Double.toString(mag.getSalary()));
	    email.setText(mag.getEmail());
	    phone.setText(mag.getPhone());
	    
	    bttDelete.setOnAction(event ->{
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty()) {
	    		magLoginWarning.setText("Empty Fields");
	    		return;
	    	}
	    	
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		email.clear();
	    		magLoginWarning.setText("Invalid Email");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		password.clear();
	    		magLoginWarning.setText("Invalid Password");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		phone.clear();
	    		magLoginWarning.setText("Invalid Phone Number");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		salary.clear();
	    		magLoginWarning.setText("Invalid Salary");
	    		return;
	    	}
	    	
	    	manager = new Manager( username.getCharacters().toString(), password.getCharacters().toString(), mag.getName(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	Administrator.deleteManager(manager);
	    	username.clear();
	    	name.clear();
	    	password.clear();
	    	salary.clear();
	    	phone.clear();
	    	email.clear();
	    	magLoginWarning.setText("Deleted Successfully!");
	    });
	    
	    bttSubmit.setOnAction(event ->{
	    	
	    	if (password.getCharacters().isEmpty() || username.getCharacters().isEmpty() || salary.getCharacters().isEmpty() || phone.getCharacters().isEmpty() || email.getCharacters().isEmpty()) {
	    		magLoginWarning.setText("Empty Fields");
	    		return;
	    	}
	    	
	    	
	    	if (!(Librarian.checkEmail(email.getCharacters().toString()))) {
	    		email.clear();
	    		magLoginWarning.setText("Invalid Email");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPassword(password.getCharacters().toString()))) {
	    		password.clear();
	    		magLoginWarning.setText("Invalid Password");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkPhone(phone.getCharacters().toString()))) {
	    		phone.clear();
	    		magLoginWarning.setText("Invalid Phone Number");
	    		return;
	    	}
	    	
	    	if (!(Librarian.checkSalary(salary.getCharacters().toString()))) {
	    		salary.clear();
	    		magLoginWarning.setText("Invalid Salary");
	    		return;
	    	}
	    	
	    	manager = new Manager( username.getCharacters().toString(), password.getCharacters().toString(), mag.getName(), Double.parseDouble(salary.getCharacters().toString()), phone.getCharacters().toString(),
	    			email.getCharacters().toString());
	    	
	    	
	    	Administrator.updateManagers(manager);
	    	magLoginWarning.setText("Success!");    	
	    	
	    });
	    
	    border.setCenter(grid);
    	
    	return border;
    	
    }
    
    
    
    
    public BorderPane administratorStatPage() {
    	
        BorderPane border = new BorderPane();
    	
    	Text text = new Text("Book Statistics");
		StackPane stack = new StackPane();
		text.setFont(new Font(30));
		stack.getChildren().add(text);
		stack.setPadding(new Insets(20));
		border.setTop(stack);
		
		Button bttSold = new Button("Sold");
		Button bttBought = new Button("Bought");
        Button bttIncome = new Button("Income");
        Button bttCost = new Button("Cost");

		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(bttSold, 0, 0);
		grid.add(bttBought, 1, 0);
		grid.add(bttIncome, 2, 0);
		grid.add(bttCost, 3, 0);
		border.setCenter(grid);
		
		
		bttSold.setOnAction(event ->{
			bttSold.getScene().setRoot(administratorSoldPage());
		});
		
		bttBought.setOnAction(event -> {
			bttBought.getScene().setRoot(administratorBoughtPage());
		});
		
		bttIncome.setOnAction(event -> {
			bttIncome.getScene().setRoot( administratorIncomePage() );
		});
		
		bttCost.setOnAction(event -> {
			bttCost.getScene().setRoot( administratorCostPage() );
		});
		
		
		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( administratorMainPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
		
		return border;
    }
    
    public BorderPane administratorIncomePage() {
    	
        BorderPane border = new BorderPane();
     	
     	Text text = new Text("Income throughout day/month/year");
 		StackPane stack = new StackPane();
 		text.setFont(new Font(30));
 		stack.getChildren().add(text);
 		stack.setPadding(new Insets(20));
 		border.setTop(stack);
 		
 		TextField totalBooksDay = new TextField();
 		Text textTotalBooksDay = new Text("Total Books Today");
 		TextField totalIncomeDay = new TextField();
 		Text textIncomeDay = new Text("Total Income Today");
 		
 		TextField totalBooksMonth = new TextField();
 		Text textTotalBooksMonth = new Text("Total Books in a Month");
 		TextField totalIncomeMonth = new TextField();
 		Text textIncomeMonth = new Text("Total Income in a Month");
 		
 		TextField totalBooksYearly = new TextField();
 		Text textTotalBooksYearly = new Text("Total Books in a Year");
 		TextField totalIncomeYearly = new TextField();
 		Text textIncomeYearly = new Text("Total Income in a Year");
 		
 		
  		GridPane grid = new GridPane();
 		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(textTotalBooksDay, 0, 0);
		grid.add(totalBooksDay, 1, 0);
		grid.add(textIncomeDay, 0, 1);
		grid.add(totalIncomeDay,1,1);
		grid.add(textTotalBooksMonth, 2, 0);
		grid.add(totalBooksMonth,3,0);
		grid.add(textIncomeMonth, 2, 1);
		grid.add(totalIncomeMonth, 3, 1);
		grid.add(textTotalBooksYearly, 4, 0);
		grid.add(totalBooksYearly, 5, 0);
		grid.add(textIncomeYearly, 4, 1);
		grid.add(totalIncomeYearly, 5, 1);
		
		border.setCenter(grid);
		
		totalBooksDay.setEditable(false);
		totalIncomeDay.setEditable(false);
		totalBooksMonth.setEditable(false);
		totalIncomeMonth.setEditable(false);
		totalBooksYearly.setEditable(false);
		totalIncomeYearly.setEditable(false);
		
		totalBooksDay.setText( Integer.toString( billNumber.getIntBooksSoldDay(FILE_PATH) ) );
		totalIncomeDay.setText( Double.toString( billNumber.getIncomeDay(FILE_PATH)) );
		totalBooksMonth.setText( Integer.toString( billNumber.getIntBooksSoldMonth(FILE_PATH) )  );
	    totalIncomeMonth.setText( Double.toString( billNumber.getIncomeMonth(FILE_PATH))  );
	    totalBooksYearly.setText( Integer.toString( billNumber.getIntBooksSoldYear(FILE_PATH) ));
	    totalIncomeYearly.setText( Double.toString( billNumber.getIncomeYear(FILE_PATH) ));
 		
 		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( administratorStatPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
 		
 		return border;
    	
    }
    
    public BorderPane administratorCostPage() {
    	
        BorderPane border = new BorderPane();
     	
     	Text text = new Text("Bought books throughout day/month/year");
 		StackPane stack = new StackPane();
 		text.setFont(new Font(30));
 		stack.getChildren().add(text);
 		stack.setPadding(new Insets(20));
 		border.setTop(stack);
 		
 		
 		TextField totalBooksDay = new TextField();
 		Text textTotalBooksDay = new Text("Total Books Today");
 		TextField totalIncomeDay = new TextField();
 		Text textIncomeDay = new Text("Total Cost Today");
 		
 		TextField totalBooksMonth = new TextField();
 		Text textTotalBooksMonth = new Text("Total Books in a Month");
 		TextField totalIncomeMonth = new TextField();
 		TextField salaryMonth = new TextField();
 		Text textSalaryMonth = new Text("Salary Total This Month");
 		Text textIncomeMonth = new Text("Total Cost in a Month");
 		
 		TextField totalBooksYearly = new TextField();
 		Text textTotalBooksYearly = new Text("Total Books in a Year");
 		TextField totalIncomeYearly = new TextField();
 		TextField salaryYear = new TextField();
 		Text textSalaryYear = new Text("Salary Total In a  Year");
 		Text textIncomeYearly = new Text("Total Cost in a Year");
 		
 		
  		GridPane grid = new GridPane();
 		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);
		grid.add(textTotalBooksDay, 0, 0);
		grid.add(totalBooksDay, 1, 0);
		grid.add(textIncomeDay, 0, 1);
		grid.add(totalIncomeDay,1,1);
		
		grid.add(textTotalBooksMonth, 2, 0);
		grid.add(totalBooksMonth,3,0);
		grid.add(textIncomeMonth, 2, 1);
		grid.add(totalIncomeMonth, 3, 1);
		grid.add(textSalaryMonth,2,2);
		grid.add(salaryMonth, 3, 2);
		
		grid.add(textTotalBooksYearly, 4, 0);
		grid.add(totalBooksYearly, 5, 0);
		grid.add(textIncomeYearly, 4, 1);
		grid.add(totalIncomeYearly, 5, 1);
		grid.add(textSalaryYear, 4, 2);
		grid.add(salaryYear, 5, 2);
		
		border.setCenter(grid);
		
		totalBooksDay.setEditable(false);
		totalIncomeDay.setEditable(false);
		totalBooksMonth.setEditable(false);
		totalIncomeMonth.setEditable(false);
		totalBooksYearly.setEditable(false);
		totalIncomeYearly.setEditable(false);
		salaryMonth.setEditable(false);
		salaryYear.setEditable(false);
		
		
		
		totalBooksDay.setText( Integer.toString( billNumber.getTotalBoughtBooksDay(FILE_PATH) ) );
		totalIncomeDay.setText( Double.toString( billNumber.getCostDay(FILE_PATH)) );
		
		totalBooksMonth.setText( Integer.toString( billNumber.getTotalBoughtBooksMonth(FILE_PATH) )  );
	    totalIncomeMonth.setText( Double.toString( billNumber.getCostMonth(FILE_PATH))  );
	    salaryMonth.setText( Double.toString(Administrator.getSalaries()) );
	    
	    totalBooksYearly.setText( Integer.toString( billNumber.getTotalBoughtBooksYear(FILE_PATH) ));
	    totalIncomeYearly.setText( Double.toString( billNumber.getCostYear(FILE_PATH) ));
	    salaryYear.setText( Double.toString(Administrator.getSalaries()*12) );
 		
 		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( administratorStatPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
 		
 		return border;
    	
    }
    
    public BorderPane administratorSoldPage() {
    
        BorderPane border = new BorderPane();
        
        PieChart pieChart = new PieChart();
        ArrayList<Book> stockBooks = billNumber.getStockBooks(FILE_PATH);
        for (Book stockBook : stockBooks) {
            if (stockBook.getPurchasedAmount() > 0) {
                titlesSold.add(stockBook.getTitle());
                quantitiesSold.add(stockBook.getPurchasedAmount());
            }
        }
		billNumber.removeDuplicatesSoldTitles(titlesSold,quantitiesSold);
        
    	for (int i=0;i<titlesSold.size();i++) {
    		Data test = new Data(titlesSold.get(i), quantitiesSold.get(i));
    		pieChart.getData().add(test);
    	}
     	
     	Text text = new Text("Bought books throughout day/month/year/total");
 		StackPane stack = new StackPane();
 		text.setFont(new Font(30));
 		stack.getChildren().add(text);
 		stack.setPadding(new Insets(20));
 		border.setTop(stack);
 		
 		Text text1 = new Text(billNumber.getBooksSoldDay(FILE_PATH));
 		Text text2 = new Text(billNumber.getBooksSoldMonth(FILE_PATH));
 		Text text3 = new Text(billNumber.getBooksSoldYear(FILE_PATH));
 		Text text4 = new Text( billNumber.getBooksSoldTotal(FILE_PATH));
 		
 		GridPane grid = new GridPane();
 		grid.add(text1, 0, 0);
		grid.add(text2, 1, 0);
		grid.add(text3, 2, 0);
		grid.add(pieChart, 1, 1);
		grid.setHgap(30);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
		border.setCenter(grid);
 		
 		
 		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( administratorStatPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
 		
 		return border;
    }
    
    public BorderPane administratorBoughtPage() {
    	
    	
        BorderPane border = new BorderPane();
        
        ArrayList<Book> stockBooks = billNumber.getStockBooks(FILE_PATH);
        for (Book stockBook : stockBooks) {
            if (stockBook.getQuantitiesPurchased() > 0) {
                titlesBought.add(stockBook.getTitle());
                quantitiesBought.add(stockBook.getQuantitiesPurchased());
            }
        }
       
   	 
   	    PieChart pieChart = new PieChart();
		billNumber.removeDuplicatesSoldTitles(titlesBought,quantitiesBought);
        
    	for (int i=0;i<titlesBought.size();i++) {
    		Data test = new Data(titlesBought.get(i), quantitiesBought.get(i));
    		pieChart.getData().add(test);
    	}
     	
     	Text text = new Text("Bought books throughout day/month/year/total");
 		StackPane stack = new StackPane();
 		text.setFont(new Font(30));
 		stack.getChildren().add(text);
 		stack.setPadding(new Insets(20));
 		border.setTop(stack);
 		
 		Text text1 = new Text(billNumber.getBooksBoughtDay(FILE_PATH));
 		Text text2 = new Text(billNumber.getBooksBoughtMonth(FILE_PATH));
 		Text text3 = new Text(billNumber.getBooksBoughtYear(FILE_PATH));
 		Text text4 = new Text(billNumber.getBooksBoughtTotal(FILE_PATH));
 		
 		GridPane grid = new GridPane();
 		grid.add(text1, 0, 0);
		grid.add(text2, 1, 0);
		grid.add(text3, 2, 0);
		grid.add(pieChart, 1, 1);
		grid.setHgap(30);
		grid.setVgap(30);
		grid.setAlignment(Pos.CENTER);
		border.setCenter(grid);
 		
 		
 		StackPane stackBackButton = new StackPane();
		stackBackButton.getChildren().add(bttBackManager);
		bttBackManager.setOnAction(event -> {
            if(event.getSource()==bttBackManager) {
            	bttBackManager.getScene().setRoot( administratorStatPage() );	
            }
	    });
		stackBackButton.setPadding(new Insets(0, 0, 40, 0));
		border.setBottom(stackBackButton);
 		
 		return border;
    	
    	
    }
    
    
    
	
	@Override
	public void handle(ActionEvent e) {
		
		if (e.getSource()==bttSubmit) {
			
			if (username.getCharacters().toString().isEmpty() || password.getCharacters().toString().isEmpty()) {
				
				mainLoginWarning.setText("Empty Fields");
				return;
				
			}
			
			String user = username.getCharacters().toString();
			String pass = password.getCharacters().toString();
			librarian = new Librarian(user,pass);
			manager = new Manager(user,pass);
			
			if (Manager.LibrarianChecker(librarian)) {
				
				username.clear();
				password.clear();
				librarian = Manager.getBackLibrarian(librarian);
                assert librarian != null;
                usernamePage = librarian.getName();
				bttSubmit.getScene().setRoot(librarianMainPage());
				
			}
			else if (Administrator.ManagerChecker(manager)) {
				
				username.clear();
				password.clear();
				manager = Administrator.getBackManager(manager);
                assert manager != null;
                usernamePage = manager.getName();
				bttSubmit.getScene().setRoot(mangaerMainPage());
				
			}
			else if ((Administrator.checker(user, pass))) {
				username.clear();
				password.clear();
				bttSubmit.getScene().setRoot(administratorMainPage());
 			}
			else {
				mainLoginWarning.setText("Wrong Information");
				username.clear();
				password.clear();
			}
		}

		if (e.getSource()==bttBack) {
			username.clear();
			password.clear();
			bttBack.getScene().setRoot(mainPage());
			mainLoginWarning.clear();
		}
		if (e.getSource()==bttAdd) {
			 
			if (comboBoxLibrarian.getValue() == null && quantity.getCharacters().toString().isEmpty()) {
				warningsLibrarian.setText("Failed, Empty fields");
				return;
			}
			else if (comboBoxLibrarian.getValue() == null) {
				warningsLibrarian.setText("Failed to add,Empty ISBN");
				return;
			}
			else if (quantity.getCharacters().toString().isEmpty()) {
				warningsLibrarian.setText("Failed, Empty Quantity");
				return;
			}
			
			else if(!quantity.getCharacters().toString().matches("\\d+") || Integer.parseInt(quantity.getCharacters().toString()) == 0) {
				warningsLibrarian.setText("Failed, Invalid Quantity");
				return;
			}
			
			
			if (!Librarian.EnoughStock(FILE_PATH, comboBoxLibrarian.getValue().toString().substring(0,13), Integer.parseInt(quantity.getCharacters().toString())) ) {
				warningsLibrarian.setText("Failed,not enough stock");
				return;
			}
			
			String isbn = comboBoxLibrarian.getValue().toString().substring(0,13);
			int quan = Integer.parseInt(quantity.getCharacters().toString());
			String Title = comboBoxLibrarian.getValue().toString().substring(16);
			date = new Date();
			
			ArrayList<Book> stockbooks = billNumber.getStockBooks(FILE_PATH);
			for (int i=0;i<stockbooks.size();i++) {
				if (stockbooks.get(i).getISBN().equals(isbn)) {
					
					stockbooks.get(i).RemoveStock(quan);
				}
			}
			try {
				billNumber.updateBooks(FILE_PATH, stockbooks);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			Book book = new Book(isbn);
			
			books.add(book);
			bookQuantities.add(quan);
		    booksSoldTitles.add(Title);
			bookISBN.clear();
			quantity.clear();
			warningsLibrarian.setText("Added");
		
		}
		
		if (e.getSource()==bttBill)  {
			
			
			if (books.isEmpty() || bookQuantities.isEmpty()) {
				warningsLibrarian.setText("Failed, No Books to add");
				return;
			}
			
			try {
				librarian.checkOutBooks(FILE_PATH, books, bookQuantities);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			bookISBN.clear();
			booksSoldTitles.clear();
			quantity.clear();
			books.clear();
			bookQuantities.clear();
			warningsLibrarian.setText("Bill File Created!");
		}
		
		
		if (e.getSource()==bttSupply) {
			bttSupply.getScene().setRoot(ManagerSupplyCickPage());
			
		}
		
		if (e.getSource()==bttAddStock) {
			bttAddStock.getScene().setRoot(chooseAddCurretStock());
		}
		
		if (e.getSource()==bttCheckStock) {
			bttCheckStock.getScene().setRoot(checkStoragePage());
		}
		if (e.getSource()==bttNewCategory) {
			bttNewCategory.getScene().setRoot(chooseNewCategoryAddStock());
		}
		
		if (e.getSource()==bttManageLibrarians) {
			bttManageLibrarians.getScene().setRoot( administratorManageLibrariansPage() );
		}
	}
}