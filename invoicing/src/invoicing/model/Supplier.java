package invoicing.model;

public class Supplier extends Contragent {
	private String iban;
	private String bic;
	private String issuer;

	public Supplier() {
	}
	
	public Supplier(String id, String name, String address, String iban, String bic, String issuer) {
		super(id, name, address);
		this.iban = iban;
		this.bic = bic;
		this.issuer = issuer;
	}

	public Supplier(String id, String name, String address, ContragentKind kind, String phone, 
			boolean vatRegisterd, String iban, String bic, String issuer) {
		super(id, name, address, kind, phone, vatRegisterd);
		this.iban = iban;
		this.bic = bic;
		this.issuer = issuer;
	}


	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public String toString() {
		return String.format("| S %s %15.15s | %8.8s | %12.12s |", 
				super.toString(), iban, bic, issuer);
	}
		
}
