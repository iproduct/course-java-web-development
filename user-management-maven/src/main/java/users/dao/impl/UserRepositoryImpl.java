package users.dao.impl;

import javax.xml.bind.JAXBException;

import users.dao.UserRepository;
import users.model.User;

// Problem 3.IV.2
public class UserRepositoryImpl extends GenericRepositoryImpl<User> implements UserRepository {

	@Override
	public User findByEmail(String email) {
		for (User user : entities.values()) {
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

}
