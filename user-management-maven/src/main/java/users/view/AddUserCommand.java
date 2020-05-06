package users.view;

import java.util.Scanner;
import users.controller.ApplicationController;
import users.exceptions.ActionUnsuccessfulException;
import users.exceptions.InvalidEntityDataException;
import users.model.User;

public class AddUserCommand implements Command {
	private ApplicationController controller;
	private Scanner sc;

	public AddUserCommand(ApplicationController mainController, Scanner sc) { 
		this.controller = mainController;
		this.sc = sc;
	}

	@Override
	public void action() throws ActionUnsuccessfulException {
		User user = InputUtilities.inputUser(sc);
		if(user != null) {
			User created;
			try {
				created = controller.getUserService().createUser(user);
				System.out.printf("Successfully created user: %d:%s %s - %s%n", 
						created.getId(), created.getFirstName(), created.getLastName(), 
						created.getRoles().stream().map(role -> role.getName()).reduce("", (acc, role) -> acc + " " + role));
			} catch (InvalidEntityDataException e) {
//				logger.log(SEVERE, "Error creating user: " + user, e);
				throw new ActionUnsuccessfulException("Error creating user: " + user, e);
			}
			
		}
	}

}
