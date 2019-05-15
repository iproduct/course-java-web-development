package invoicing.model;

public class Customer extends Contragent {
	public String email;

	public Customer() {
		super();
	}

	public Customer(String id, String name, String address, ContragentKind kind, String phone, 
			boolean vatRegisterd, String email) {
		super(id, name, address, kind, phone, vatRegisterd);
		this.email = email;
	}

	public Customer(String id, String name, String address) {
		super(id, name, address);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("| C %s | %s |", 
				super.toString(), email);
	}

	
	
}
