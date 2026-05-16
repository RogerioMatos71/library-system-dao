package application;

import java.util.Scanner;

import db.DB;
import model.dao.BookDao;
import model.dao.impl.BookDaoJDBC;
import model.entities.Book;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		 BookDao bookDao = new BookDaoJDBC(DB.getConnection());

	        Book book = new Book();
	        
	        book.setTitle("João e Maria");
	        book.setAuthor("Zé da Roça");
	        book.setIsbn("97801322568");
	        book.setPublisher("Editora azul");
	        book.setYearPublication(1971);
	        
	        bookDao.insert(book);
	        
	        System.out.println("Book successfully inserted!");
	        System.out.print("Book Id: " + book.getId());

	        
	       // userDao.update(user);

	       
	        
	        
	        sc.close();
	    }
	
		
		

		
		
		
	
		
		
	}
	
	
	

