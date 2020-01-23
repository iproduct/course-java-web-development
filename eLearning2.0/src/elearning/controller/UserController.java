package elearning.controller;

import java.util.ArrayList;
import java.util.List;

import elearning.entity.User;
import elearning.exceptions.UserException;

public class UserController {
	protected List<User> users = new ArrayList<>();
	
	public User findByEmail(String email){
		for(User u: users){
			if(u.getEmail().equals(email))
				return u;
		}
		return null;
	}
	public User findById(long id){
		for(User u: users){
			if(u.getId() == id)
				return u;
		}
		return null;
	}
	public boolean isLoginValid(String email, String password){
		User user = findByEmail(email);
		if(user != null)
			return user.getPassword().equals(password);
		return false;
	}
	public void addUser(User user) throws UserException{
		if(users.contains(user))
			throw new UserException("User already exists.");
		users.add(user);
	}
	public void editUser(User user) throws UserException{
		if(!users.contains(user))
			throw new UserException("User does not exist.");
		users.remove(user);
		users.add(user);	
	}
	public void deleteUser(long userId){
		User user = findById(userId);
		if(user != null)
			users.remove(user);
	}
	
}
