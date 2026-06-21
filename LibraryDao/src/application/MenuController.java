package application;

import java.util.Scanner;

import model.dao.BookDao;
import model.dao.DaoFactory;
import model.dao.LoanDao;
import model.dao.UserDao;
import model.entities.Book;
import model.entities.Loan;
import model.entities.User;
import services.LoanService;

public class MenuController {

	Scanner sc = new Scanner(System.in);

	public static void start() {

		int option;

		do {
			Menu.mainMenu();

			option = InputUtils.readInt("Enter the option: ");

			switch (option) {

			case 1: {

				UserDao userDao = DaoFactory.createUserDao();

				User user = new User();

				user.setName(InputUtils.readLine("Name: "));
				user.setCpf(InputUtils.readLine("Cpf: "));
				user.setEmail(InputUtils.readLine("Email: "));

				userDao.insert(user);

				System.out.println("Inserted! New Id = " + user.getId());

				InputUtils.pause();

				break;
			}

			case 2: {

				LoanDao loanDao = DaoFactory.createLoanDao();

				String cpf = InputUtils.readLine("Enter user cpf to consult: ");

				Loan loan = loanDao.findByCpf(cpf);

				System.out.println(loan);

				InputUtils.pause();

				break;
			}

			case 3: {

				UserDao userDao = DaoFactory.createUserDao();

				userDao.deleteById(InputUtils.readInt("Enter user id to delete: "));

				System.out.println("User deleted!");

				InputUtils.pause();

				break;

			}
			case 4: {

				BookDao bookDao = DaoFactory.createBookDao();

				Book book = new Book();

				book.setTitle(InputUtils.readLine("Title: "));
				book.setAuthor(InputUtils.readLine("Author: "));
				book.setIsbn(InputUtils.readLine("ISBN: "));
				book.setPublisher(InputUtils.readLine("Publisher: "));
				book.setYearPublication(InputUtils.readInt("Year Publication: "));

				bookDao.insert(book);

				System.out.println("Book inserted! New Id: " + book.getId());

				InputUtils.pause();

				break;
			}

			case 5: {

				Book book;

				BookDao bookDao = DaoFactory.createBookDao();

				book = bookDao.findById(InputUtils.readInt("Insert book id to consult: "));

				System.out.println(book);

				InputUtils.pause();

				break;
			}

			case 6: {

				BookDao bookDao = DaoFactory.createBookDao();

				Book book = new Book();

				book = bookDao.findById(InputUtils.readInt("Insert book id to delete: "));

				System.out.println(book);

				boolean confirmation = InputUtils.readConfirmation();
				
				if (confirmation) {

				bookDao.deleteById(book.getId());
				
				System.out.println("Book deleted!");
				
				} 
				else  {
					System.out.println("Canceled operation!");
				}

				InputUtils.pause();

				break;

			}

			case 10: {

				LoanService loanService = new LoanService();
				String cpf = InputUtils.readLine("Enter user cpf: ");
				int copyId = InputUtils.readInt("Enter copy id: ");

				Loan loan = loanService.borrowBook(cpf, copyId);

				System.out.println("Book borrowed! New Loan id: " + loan.getId());

				InputUtils.pause();

				break;
			}

			case 11: {

				LoanService loanService = new LoanService();

				String cpf = InputUtils.readLine("Enter user cpf: ");
				int loanId = InputUtils.readInt("Enter loan id to return: ");

				loanService.returnBook(cpf, loanId);

				System.out.println("Book returned!");

				InputUtils.pause();

				break;
			}

			}
		} while (option != 0);

	}
}
