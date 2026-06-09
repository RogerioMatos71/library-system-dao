package model.dao;

import java.util.List;

import model.entities.Loan;
import services.LoanService;

public interface LoanDao {

	void insert(Loan loan);
    Loan findById(Integer id);
    List<Loan> findByUser(String name);
    //List<Loan> findActiveLoansByUserId();
    void update(Loan loan);
	Loan findByCpf(String cpf);
}
