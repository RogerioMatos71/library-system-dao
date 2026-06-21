package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import copies.enums.CopyStatus;
import db.DB;
import db.DbException;
import model.dao.LoanDao;
import model.entities.Book;
import model.entities.Copy;
import model.entities.Loan;
import model.entities.User;

public class LoanDaoJDBC implements LoanDao {

	private Connection conn;

	public LoanDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Loan loan) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO loans " + "(user_id, copy_id, loan_date, due_date, return_date) "
					+ "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, loan.getUser().getId());
			st.setInt(2, loan.getCopy().getId());
			st.setDate(3, java.sql.Date.valueOf(loan.getLoanDate()));
			st.setDate(4, java.sql.Date.valueOf(loan.getDueDate()));

			if (loan.getReturnDate() != null) {
				st.setDate(5, java.sql.Date.valueOf(loan.getReturnDate()));
			} else {
				st.setNull(5, java.sql.Types.DATE);
			}

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					loan.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Loan findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			if (id == null) {
				throw new DbException("Invalid user id!");
			}

			st = conn.prepareStatement("SELECT l.*, " + "u.id as user_id, u.name as user_name, u.cpf, u.email, "
					+ "b.id as book_id, b.title, b.author, b.isbn, b.publisher, b.year_publication, "
					+ "c.id as copy_id, c.status " + "FROM loans l " + "LEFT JOIN users u ON l.user_id = u.id "
					+ "LEFT JOIN copies c ON l.copy_id = c.id " + "LEFT JOIN books b ON c.book_id = b.id "
					+ "WHERE l.id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Loan loan = instantiateLoan(rs);

				return loan;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Loan> findByUser(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT l.*, " + "u.id as user_id, u.name as user_name, u.cpf, u.email, "
					+ "b.id as book_id, b.title, b.author, b.isbn, b.publisher, b.year_publication, "
					+ "c.id as copy_id, c.status " + "FROM loans l " + "LEFT JOIN users u ON l.user_id = u.id "
					+ "LEFT JOIN copies c ON l.copy_id = c.id " + "LEFT JOIN books b ON c.book_id = b.id "
					+ "WHERE u.name = ?");

			st.setString(1, name);
			rs = st.executeQuery();

			List<Loan> loans = new ArrayList<>();
			while (rs.next()) {
				Loan loan = instantiateLoan(rs);
				loans.add(loan);

			}
			return loans;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public void update(Loan loan) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("UPDATE loans "
					+ "SET user_id = ?, copy_id = ?, loan_date = ?, due_date = ?, return_date = ? " + "WHERE id = ?");

			st.setInt(1, loan.getUser().getId());
			st.setInt(2, loan.getCopy().getId());
			st.setDate(3, Date.valueOf(loan.getLoanDate()));
			st.setDate(4, Date.valueOf(loan.getDueDate()));

			if (loan.getReturnDate() != null) {
				st.setDate(5, Date.valueOf(loan.getReturnDate()));
			} else {
				st.setNull(5, Types.DATE);
			}
			st.setInt(6, loan.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	private Loan instantiateLoan(ResultSet rs) throws SQLException {

		Loan loan = new Loan();
		User user = new User();
		Copy copy = new Copy();
		Book book = new Book();

		// USER
		user.setId(rs.getInt("userId"));
		user.setName(rs.getString("userName"));
		//user.setCpf(rs.getString("cpf"));
		//user.setEmail(rs.getString("email"));

		// BOOK
		
		//book.setId(rs.getInt("bookId"));
		book.setTitle(rs.getString("bookTitle"));
		
		
		// COPY
		copy.setId(rs.getInt("copyId"));
		
		copy.setStatus(CopyStatus.valueOf(rs.getString("copyStatus")));
		

		// LOAN
		loan.setId(rs.getInt("loanId"));
		
		
		loan.setLoanDate(rs.getObject("loanDate", LocalDate.class));
		loan.setDueDate(rs.getObject("dueDate", LocalDate.class));
		loan.setReturnDate(rs.getObject("returnDate", LocalDate.class));

		loan.setUser(user);
		loan.setCopy(copy);
		copy.setBook(book);
		

		return loan;
	}

	@Override
	public Loan findByCpf(String cpf) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		if (cpf == null) {
			throw new DbException("CPF not found!");
		}
		
		Loan loan = new Loan();
		try {
			st = conn.prepareStatement("SELECT " +
				    "u.id AS userId, " +
				    "u.name AS userName, " +
				    "b.title AS bookTitle, " +
				    "c.id AS copyId, " +
				    "c.status AS copyStatus, " +
				    "l.id AS loanId, " +
				    "l.loan_date AS loanDate, " +
				    "l.due_date AS dueDate, " +
				    "l.return_date AS returnDate " +
				"FROM loans l " +
				"JOIN users u ON l.user_id = u.id " +
				"JOIN copies c ON l.copy_id = c.id " +
				"JOIN books b ON c.book_id = b.id " +
				"WHERE u.cpf = ?");
			
			st.setString(1, cpf);
			rs = st.executeQuery();
			
			while (rs.next()) {
				 loan = instantiateLoan(rs);
			}
			 return loan;
			
		}  catch (SQLException e) {
			throw new DbException (e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}