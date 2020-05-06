package users.dao.impl;

import javax.xml.bind.JAXBException;

import users.dao.UserRepository;
import users.model.User;

//Problem 4.II
public class JaxbUserRepositoryImpl extends JAXBEntityRepository<User> implements UserRepository {

	public JaxbUserRepositoryImpl(String xmlFileName) {
		super(xmlFileName); 
	}

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
