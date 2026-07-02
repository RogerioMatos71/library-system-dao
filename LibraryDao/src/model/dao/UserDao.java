package model.dao;

import java.util.List;

import model.entities.User;

public interface UserDao {
	
	void insert(User user);
	void update(User user);
	void deleteById(Integer id);
	void deleteByCpf(String cpf);
	User findById(Integer id);
	User findByCpf(String cpf);
	
	List<User> findByName(String name);
	List<User> findAll();
	
	

}
