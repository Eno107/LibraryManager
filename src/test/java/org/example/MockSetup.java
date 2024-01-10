package org.example;

import org.mockito.Mockito;
import project.librarymanager.BillNumber;
import project.librarymanager.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;

public class MockSetup {

    static Date today = new Date();

    public static ArrayList<Book> getBooksWithDates(){

        ArrayList<Book> booksWithDates = new ArrayList<>();

        Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",6);
        book.addDate(today);
        book.addQuantity(2);
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(1);
        booksWithDates.add(book);

        // book is sold today
        book = new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10);
        book.addDate(today);
        book.addQuantity(3);
        book.addPurchasedDate(today);
        book.addQuantitiesPurchased(3);
        booksWithDates.add(book);

        // book is sold tomorrow
        book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",7);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        book.addDate(calendar.getTime());
        book.addQuantity(6);
        book.addPurchasedDate(calendar.getTime());
        book.addQuantitiesPurchased(6);
        booksWithDates.add(book);

        // book is sold yesterday
        book = new Book("9515267203718","Moby Dick","Adventure fiction","Cardinal Publishers Group",7.00,10.00,"Herman Melville",6);
        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        book.addDate(calendar.getTime());
        book.addQuantity(2);
        book.addPurchasedDate(calendar.getTime());
        book.addQuantitiesPurchased(2);
        booksWithDates.add(book);

        // book is sold a month ago
        book = new Book("3655736671389","One Hundred Years of Solitude","Magic realism","Ingram Content Group, Inc",13.00,16.99,"Gabriel Garcia Marquez",7);
        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        book.addDate(calendar.getTime());
        book.addQuantity(2);
        book.addPurchasedDate(calendar.getTime());
        book.addQuantitiesPurchased(2);
        booksWithDates.add(book);

        // book is sold after a year
        book = new Book("3115666367951","The Great Gatsby","Tragedy","Ingram Content Group, Inc",10.00,11.95,"F. Scott Fitzgerald",5);
        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, 1);
        book.addDate(calendar.getTime());
        book.addQuantity(5);
        book.addPurchasedDate(calendar.getTime());
        book.addQuantitiesPurchased(5);
        booksWithDates.add(book);

        return booksWithDates;
    }

    public static ArrayList<Book> getBooksWithoutDates(){

        ArrayList<Book> booksWithoutDates = new ArrayList<>();

        Book book = new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",4);
        booksWithoutDates.add(book);

        book = new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",3);
        booksWithoutDates.add(book);

        return booksWithoutDates;
    }

    public static BillNumber createMockBillNumberWithDates() {
        BillNumber mockBillNumber = Mockito.mock(BillNumber.class, CALLS_REAL_METHODS);
        when(mockBillNumber.getStockBooks(Mockito.anyString())).thenReturn(getBooksWithDates());
        return mockBillNumber;
    }

    public static BillNumber createMockBillNumberWithoutDates() {
        BillNumber mockBillNumber = Mockito.mock(BillNumber.class, CALLS_REAL_METHODS);
        when(mockBillNumber.getStockBooks(Mockito.anyString())).thenReturn(getBooksWithoutDates());
        return mockBillNumber;
    }

    public static Book createMockBook(String category, String soldDatesQuantitiesTotal) {
        Book mockBook = Mockito.mock(Book.class);
        when(mockBook.getCategory()).thenReturn(category);
        when(mockBook.getSoldDatesQuantitiesTotal()).thenReturn(soldDatesQuantitiesTotal);
        return mockBook;
    }
}
