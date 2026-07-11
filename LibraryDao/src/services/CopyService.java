package services;

import java.util.ArrayList;
import java.util.List;

import copies.enums.CopyStatus;
import model.dao.BookDao;
import model.dao.CopyDao;
import model.dao.DaoFactory;
import model.entities.Book;
import model.entities.Copy;
import model.exceptions.BusinessException;

public class CopyService {
	
	private CopyDao copyDao;
	private BookDao bookDao;
	
	
	public CopyService() {
		copyDao = DaoFactory.createCopyDao();
		bookDao = DaoFactory.createBookDao();
	
	}
	
	public List<Copy> insert(int bookId, int quantity) { 
		Book book = bookDao.findById(bookId);
		List<Copy> copies = new ArrayList<>();
		if (book == null) {
			throw new BusinessException("Book not found!");
		}
		 for (int i = 0; i < quantity; i++) {
			 Copy copy = new Copy();
			 copy.setBook(book);
			 copy.setStatus(CopyStatus.AVAILABLE);
			 copyDao.insert(copy);
			 copies.add(copy);
			
		 }
		return copies;
		 
		 
	
	}
	
	public void delete(int copyId) {
		copyDao.deleteById(copyId);
	}
	
	public Copy findById(Integer copyId) {
		Copy copy = copyDao.findById(copyId);
		
		if (copy == null) {
			throw new BusinessException("Copy not found!");
		}
		return copyDao.findById(copyId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
