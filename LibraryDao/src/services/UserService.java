package services;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class UserService {

	private UserDao userDao;

	public UserService() {
	
		userDao = DaoFactory.createUserDao();
	}

	public void insert(User user) {
		userDao.insert(user);
	}
	
	public void delete(String cpf) {
		userDao.deleteByCpf(cpf);
	}
	
	public User findByCpf(String cpf) {
	   User user = userDao.findByCpf(cpf);
	   return user;
	}
}
