package application;

import java.util.Scanner;

import db.DB;
import model.dao.UserDao;
import model.dao.impl.UserDaoJDBC;
import model.entities.User;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		 UserDao userDao = new UserDaoJDBC(DB.getConnection());

	        User user = new User();

	        user.setId(15); // id que exista no banco
	        user.setName("Rogerio");
	        user.setCpf("137665008");
	        user.setEmail("rogerio@email.com");

	        userDao.update(user);

	        System.out.println("User updated successfully!");
	        
	        
	        sc.close();
	    }
	
		
		

		
		
		
		
		
		
	}
	
	
	

