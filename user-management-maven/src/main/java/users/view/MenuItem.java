package users.view;

public enum MenuItem {
	ADD_USER("Add User"), 
	PRINT_USERS("Print All Users"), 
	EXIT("Exit");
	
	private String text;
	private MenuItem(String itemText) {
		text = itemText;
	}
	public String getText() {
		return text;
	}
	
}
