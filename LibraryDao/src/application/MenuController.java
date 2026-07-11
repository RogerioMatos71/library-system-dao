package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import copies.enums.CopyDeletionStatus;
import copies.enums.CopyStatus;
import model.dao.BookDao;
import model.dao.CopyDao;
import model.dao.DaoFactory;
import model.dao.LoanDao;
import model.dao.UserDao;
import model.entities.Book;
import model.entities.Copy;
import model.entities.Loan;
import model.entities.User;
import services.BookService;
import services.CopyService;
import services.LoanService;
import services.UserService;

public class MenuController {

	private LoanService loanService;
	private UserService userService;
	private BookService bookService;
	private CopyService copyService;

	Scanner sc = new Scanner(System.in);

	public MenuController() {

		UserDao userDao = DaoFactory.createUserDao();
		CopyDao copyDao = DaoFactory.createCopyDao();
		LoanDao loanDao = DaoFactory.createLoanDao();

		loanService = new LoanService(userDao, copyDao, loanDao);
		userService = new UserService();
		bookService = new BookService();
		copyService = new CopyService();

	}

	public void start() {

		int option;

		do {
			Menu.mainMenu();

			option = InputUtils.readInt("Enter the option: ");

			switch (option) {

			case 1: {

				User user = new User();

				user.setName(InputUtils.readLine("Name: "));
				user.setCpf(InputUtils.readLine("Cpf: "));
				user.setEmail(InputUtils.readLine("Email: "));

				userService.insert(user);

				System.out.println(user);

				System.out.println("User registered successfully! New ID: " + user.getId());

				InputUtils.pause();

				break;
			}

			case 2: {

				String cpf = InputUtils.readLine("Enter user cpf to consult loans: ");

				List<Loan> loans = loanService.findLoansByCpf(cpf);

				if (loans.isEmpty()) {
					System.out.println("Loans not found!");
					InputUtils.pause();
					break;
				}
				System.out.println("User: " + loans.get(0).getUser().getName());

				for (Loan loan : loans) {

					System.out.println("Loan ID: " + loan.getId());
					System.out.println("Book title: " + loan.getCopy().getBook().getTitle());
					System.out.println("Loan date: " + loan.getLoanDate());
					System.out.println("Due date: " + loan.getDueDate());
					System.out.println("Return date: " + loan.getReturnDate());
					System.out.println();
				}

				InputUtils.pause();

				break;
			}

			case 3: {

				String cpf = InputUtils.readLine("Enter user cpf to delete: ");

				User user = userService.findByCpf(cpf);

				System.out.println(user);

				if (InputUtils.readConfirmation()) {
					userService.delete(cpf);
					System.out.println("User deleted!");
					InputUtils.pause();
					break;
				}
				System.out.println("Canceled operation!");

				InputUtils.pause();

				break;

			}
			case 4: {

				Book book = new Book();

				book.setTitle(InputUtils.readLine("Title: "));
				book.setAuthor(InputUtils.readLine("Author: "));
				book.setIsbn(InputUtils.readLine("ISBN: "));
				book.setPublisher(InputUtils.readLine("Publisher: "));
				book.setYearPublication(InputUtils.readInt("Year Publication: "));

				int quantity = InputUtils.readInt("Enter copies quantity to added: ");

				List<Copy> copies = bookService.insert(book, quantity);

				System.out.println("Book registered successfully! New ID: " + book.getId());

				System.out.println("New ID copies");

				for (Copy copy : copies) {
					System.out.println("Copy ID: " + copy.getId());
				}

				InputUtils.pause();

				break;
			}

			case 5: {

				Book book = bookService.findById(InputUtils.readInt("Insert book id to consult: "));

				System.out.println("Title: " + book.getTitle());
				System.out.println("Author: " + book.getAuthor());
				System.out.println("Publisher: " + book.getPublisher());

				InputUtils.pause();

				break;
			}

			case 6: {

				int bookId = InputUtils.readInt("Insert book id to delete: ");

				Book book = bookService.findById(bookId);

				System.out.println(book);

				CopyDeletionStatus status = bookService.checkDeletionBook(bookId);

				if (status == CopyDeletionStatus.BORROWED) {
					System.out.println("Cannot delete book: borrowed copies exist!");
					InputUtils.pause();
					break;

				} else if (status == CopyDeletionStatus.AVAILABLE) {
					System.out.println(
							"There are copies of this book that will also be deleted; do you wish to continue? (Y) (N)");
					boolean confirm = InputUtils.readConfirmation();
					if (confirm == true) {
						bookService.deleteBookAndCopies(bookId);
						System.out.println("Deleted book and copies!");
						InputUtils.pause();
						break;
					}
					System.out.println("Canceled operation");
					InputUtils.pause();
					break;

				} else if (status == CopyDeletionStatus.NONE) {
					System.out.println("The book will be deleted. Are you sure you want to continue? (Y) (N)");
					if (InputUtils.readConfirmation() == true) {
						bookService.deleteBook(bookId);
						System.out.println("Deleted book!");
						InputUtils.pause();
						break;
					}
					System.out.println("Canceled operation");
					InputUtils.pause();
					break;
				}

			}

			case 7: {

				int bookId = InputUtils.readInt("Insert book ID to add copy: ");
				Book book = bookService.findById(bookId);

				int quantity = InputUtils.readInt("Enter the copies quantity: ");

				System.out.println("Title: " + book.getTitle());
				System.out.println("Check the book title to add the copy");
				boolean confirm = InputUtils.readConfirmation();
				if (confirm == false) {
					System.out.println("Canceled operation!");
					InputUtils.pause();
					break;
				} else {

					List<Copy> copies = copyService.insert(bookId, quantity);

					System.out.println("Copy successfully inserted!");

					for (Copy copy : copies) {
						System.out.println("Copy ID: " + copy.getId());
					}

					InputUtils.pause();

					break;

				}
			}

			case 8: {

				int copyId = InputUtils.readInt("Enter copy ID to consult: ");

				Copy copy = copyService.findById(copyId);

				System.out.println(copy);

				InputUtils.pause();

				break;

			}

			case 9: {

				int copyId = InputUtils.readInt("Enter copy Id to delete: ");
				Copy copy = copyService.findById(copyId);

				CopyStatus status = copy.getStatus();

				if (status == CopyStatus.BORROWED) {
					System.out.println("Cannot delete copy, this is borrowed!");
					InputUtils.pause();
					break;

				} else if (status == CopyStatus.AVAILABLE) {

					System.out.println("Title: " + copy.getBook().getTitle());
					System.out.println("Confirm the title to delete copy");

					if (InputUtils.readConfirmation() == true) {
						copyService.delete(copyId);
						System.out.println("Deleted copy!");
						InputUtils.pause();
						break;
					}
					System.out.println("Canceled operation!");
					InputUtils.pause();
					break;
				}
			}

			case 10: {

				String cpf = InputUtils.readLine("Enter user cpf: ");
				int copyId = InputUtils.readInt("Enter copy id: ");

				User user = userService.findByCpf(cpf);
				Copy copy = copyService.findById(copyId);

				System.out.println(user);
				System.out.println("Title: " + copy.getBook().getTitle());
				System.out.println();
				System.out.println("Confirm the borrow? ");
				boolean confirm = InputUtils.readConfirmation();

				if (confirm == true) {
					Loan loan = loanService.borrowBook(cpf, copyId);

					System.out.println("Book borrowed! New Loan id: " + loan.getId());

					InputUtils.pause();

					break;

				}
				System.out.println("Canceled operation!");

				InputUtils.pause();

				break;

			}

			case 11: {

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
