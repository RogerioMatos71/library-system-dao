package services;

import java.util.List;

import copies.enums.CopyDeletionStatus;
import db.DB;
import model.dao.BookDao;
import model.dao.CopyDao;
import model.dao.DaoFactory;
import model.dao.impl.BookDaoJDBC;
import model.dao.impl.CopyDaoJDBC;
import model.entities.Book;
import model.entities.Copy;

public class BookService {

	private CopyDao copyDao;
	private BookDao bookDao;
	private List<Copy> copies;
	CopyService copyService = new CopyService();

	public BookService() {
		copyDao = DaoFactory.createCopyDao();
		bookDao = DaoFactory.createBookDao();

	}

	public List<Copy> insert(Book book, int quantity) {
		bookDao.insert(book);
		List<Copy> copies = copyService.insert(book.getId(), quantity);
		
		return copies;

	}

	public CopyDeletionStatus checkDeletionBook(int bookId) {
		CopyDeletionStatus result = copyDao.getStatusCopiesDeletion(bookId);
		if (result == CopyDeletionStatus.NONE) {
			return CopyDeletionStatus.NONE;
		}
		if (result == CopyDeletionStatus.AVAILABLE) {
			return CopyDeletionStatus.AVAILABLE;
		}
		return CopyDeletionStatus.BORROWED;

	}

	public void deleteBook(int bookId) {
		bookDao.deleteById(bookId);

	}

	public void deleteBookAndCopies(int bookId) {
		copyDao.deleteByBookId(bookId);
		bookDao.deleteById(bookId);

	}

	public Book findById(int bookId) {
		Book book = bookDao.findById(bookId);
		return book;
	}

}
