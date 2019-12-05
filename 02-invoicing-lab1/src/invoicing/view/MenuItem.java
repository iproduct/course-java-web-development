package invoicing.view;

public enum MenuItem {
	ADD_PRODUCT("Add Product"), PRINT_PRUCTS("Print All Products"), EXIT("Exit");
	
	private String text;
	private MenuItem(String itemText) {
		text = itemText;
	}
	public String getText() {
		return text;
	}
	
}
