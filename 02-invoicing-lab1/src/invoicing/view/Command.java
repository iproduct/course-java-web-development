package invoicing.view;

import invoicing.exceptions.ActionUnsuccessfulException;

@FunctionalInterface
public interface Command {
	void action() throws ActionUnsuccessfulException;
}
