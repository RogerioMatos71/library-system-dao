package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.BookDao;
import model.entities.Book;

public class BookDaoJDBC implements BookDao {
	
private Connection conn;
	
	public BookDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Book obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Book obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
