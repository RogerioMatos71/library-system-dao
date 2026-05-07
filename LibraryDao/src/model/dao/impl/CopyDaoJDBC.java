package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.CopyDao;
import model.entities.Copy;

public class CopyDaoJDBC implements CopyDao {

	private Connection conn;
	
	public CopyDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Copy obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Copy obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Copy findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Copy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
