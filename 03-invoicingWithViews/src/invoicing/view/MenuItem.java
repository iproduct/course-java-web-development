package invoicing.view;

import static invoicing.view.MenuCommand.*;

public class MenuItem {
	private String label = "";
	private MenuCommand command = NONE;
	
	public MenuItem() {
	}

	public MenuItem(String label, MenuCommand command) {
		this.label = label;
		this.command = command;
	}

	public String getLabel() {
		return label;
	}

	public MenuCommand getCommand() {
		return command;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((command == null) ? 0 : command.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuItem other = (MenuItem) obj;
		if (command != other.command)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "label=" + label + " (" + command +")";
	}
	
	
	
}
