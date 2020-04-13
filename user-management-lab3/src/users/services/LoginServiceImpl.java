package users.services;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import users.exceptions.NonexistingEntityException;
import users.model.User;

public class LoginServiceImpl implements LoginService {
	private User loggedIn;
	private UserService userService;

	public LoginServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getLoggedUser() {
		return loggedIn;
	}

	@Override
	public boolean login(String email, String password) {
		User user;
		try {
			user = userService.getUserByEmail(email);
		} catch (NonexistingEntityException e) {
			e.printStackTrace();
			return false;
		}
		if(user.getPassword().equals(password)) {
			loggedIn = user;
			return true;
		} else {
			loggedIn = null;
			return false;
		}
	}

	@Override
	public void logout() {
		loggedIn = null;
	}

	

}
