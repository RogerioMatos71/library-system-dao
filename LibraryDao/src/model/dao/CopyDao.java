package model.dao;

import java.util.List;


import model.book.Copy;

public interface CopyDao {
	
	void insert(Copy obj);
	void update(Copy obj);
	void deleteById(Integer id);
	Copy findById(Integer id);
	List<Copy> findAll();
	

}
