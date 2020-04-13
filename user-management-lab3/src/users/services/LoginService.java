package users.services;

import users.model.User;

public interface LoginService {
	User getLoggedUser(); //returns the currently logged User in the (single user) system;
	boolean login(String email, String password); //logs-in the User by provided email and password as method arguments, and returns boolean status if login is successful;
	void logout(); //logs-out the currently logged user, always successful.
}
