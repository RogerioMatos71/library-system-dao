package services;

import java.time.LocalDate;
import java.util.List;

import copies.enums.CopyStatus;
import model.dao.CopyDao;
import model.dao.LoanDao;
import model.dao.UserDao;
import model.entities.Copy;
import model.entities.Loan;
import model.entities.User;
import model.exceptions.BusinessException;

public class LoanService {

	private UserDao userDao;
	private CopyDao copyDao;
	private LoanDao loanDao;
	

	public LoanService(UserDao userDao, CopyDao copyDao, LoanDao loanDao) {
		this.userDao = userDao;
		this.copyDao = copyDao;
		this.loanDao = loanDao;
	}

	public Loan borrowBook(String cpf, Integer copyId) {

		User user = userDao.findByCpf(cpf);
		Copy copy = copyDao.findById(copyId);

		if (user == null) {
			throw new BusinessException("User not found!");
		}

		if (copy == null) {
			throw new BusinessException("Copy not found!");
		}

		if (copy.getStatus() == CopyStatus.BORROWED) {
			throw new BusinessException("Book not available");

		}

		LocalDate loanDate = LocalDate.now();
		LocalDate dueDate = loanDate.plusDays(7);

		Loan loan = new Loan(null, user, copy, loanDate, dueDate, null);

		copy.borrow();

		copyDao.upDate(copy);
		loanDao.insert(loan);

		return loan;

	}

	public void returnBook(String cpf, Integer loanId) {

		User user = userDao.findByCpf(cpf);
		Loan loan = loanDao.findById(loanId);

		if (user == null) {
			throw new BusinessException("User not found!");
		}

		if (loan == null) {
			throw new BusinessException("Loan not found!");
		}

		LocalDate returnDate = LocalDate.now();

		loan.setReturnDate(returnDate);

		loanDao.update(loan);

		CopyStatus status = CopyStatus.AVAILABLE;

		loan.getCopy().setStatus(status);

		copyDao.upDate(loan.getCopy());

	}

	public List<Loan> findLoansByCpf(String cpf) {
		User user = userDao.findByCpf(cpf);
		if (user == null) {
			throw new BusinessException("User cpf not found!");
		}
		return loanDao.findByCpf(cpf);
		
	}
	
	
	}
	
	


