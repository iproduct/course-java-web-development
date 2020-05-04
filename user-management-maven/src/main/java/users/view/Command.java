package users.view;

import users.exceptions.ActionUnsuccessfulException;

@FunctionalInterface
public interface Command {
	void action() throws ActionUnsuccessfulException;
}
