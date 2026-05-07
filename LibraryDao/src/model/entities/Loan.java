package model.entities;

import java.time.LocalDate;

public class Loan {

	private Integer id;
	private User user;
	private Copy copy;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnDate;

	public Loan() {

	}

	public Loan(Integer id, User user, Copy copy, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
		super();
		this.id = id;
		this.user = user;
		this.copy = copy;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	
	

}
