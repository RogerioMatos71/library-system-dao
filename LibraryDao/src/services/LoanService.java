package services;

import java.time.LocalDate;

import application.InputUtils;
import copies.enums.CopyStatus;
import db.DB;
import db.DbException;
import model.dao.CopyDao;
import model.dao.LoanDao;
import model.dao.UserDao;
import model.dao.impl.CopyDaoJDBC;
import model.dao.impl.LoanDaoJDBC;
import model.dao.impl.UserDaoJDBC;
import model.entities.Copy;
import model.entities.Loan;
import model.entities.User;

public class LoanService {

	private UserDao userDao = new UserDaoJDBC(DB.getConnection());

	private CopyDao copyDao = new CopyDaoJDBC(DB.getConnection());

	public void borrowBook() {

		LocalDate loanDate = LocalDate.now();
		LocalDate dueDate = loanDate.plusDays(7);
		LocalDate returnDate = null;
		LoanDao loanDao = new LoanDaoJDBC(DB.getConnection());

		User user = userDao.findByCpf(InputUtils.readLine("Enter user cpf: "));
		Copy copy = copyDao.findById(InputUtils.readInt("Enter copy id: "));

		if (copy.getStatus() == CopyStatus.BORROWED) {
			throw new DbException("Book not available");

		}

		copy.borrow();

		copyDao.upDate(copy);

		Loan loan = new Loan(null, user, copy, loanDate, dueDate, returnDate);

		loanDao.insert(loan);

	}
	
	public void returnBook() {
		
	     LoanDao loanDao = new LoanDaoJDBC(DB.getConnection());
	     LocalDate dueDate = null;
	     LocalDate loanDate = null;
		 LocalDate returnDate = null;
		 User user = userDao.findByCpf(InputUtils.readLine("Enter user cpf: "));
		 Copy copy = copyDao.findById(InputUtils.readInt("Enter copy id to return: "));
		 
		 if (copy.getStatus() == CopyStatus.AVAILABLE) {
			 throw new DbException("Book not available to return!");
		 }
		 
		 copy.giveBack();
		 
		 copyDao.upDate(copy);
		 
		 Loan loan = new Loan(null, user, copy, loanDate, dueDate, returnDate);
		 
		 loanDao.update(loan);
		 
	}

}
