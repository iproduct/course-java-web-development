package invoicing.view;

public enum MenuItem {
	ADD_PRODUCT("Add Product"), 
	PRINT_PRUCTS("Print All Products"), 
	WRITE_TO_FILE("Write to File"),
	READ_FROM_FILE("Read from File"),
	EXIT("Exit");
	
	private String text;
	private MenuItem(String itemText) {
		text = itemText;
	}
	public String getText() {
		return text;
	}
	
}
