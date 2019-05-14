package invoicing.model;

public class Contragent {
	private String idNumber;
	private String name;
	private String address;
	private ContragentKind kind = ContragentKind.COMPANY;
	private String phone;
	private boolean vatRegisterd = true;;
	
	public Contragent() {}

	public Contragent(String id, String name, String address) {
		idNumber = id;
		this.name = name;
		this.address = address;
	}

	public Contragent(String id, String name, String address, ContragentKind kind, String phone, boolean vatRegisterd) {
		idNumber = id;
		this.name = name;
		this.address = address;
		this.kind = kind;
		this.phone = phone;
		this.vatRegisterd = vatRegisterd;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ContragentKind getKind() {
		return kind;
	}

	public void setKind(ContragentKind kind) {
		this.kind = kind;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isVatRegisterd() {
		return vatRegisterd;
	}

	public void setVatRegisterd(boolean vatRegisterd) {
		this.vatRegisterd = vatRegisterd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
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
		Contragent other = (Contragent) obj;
		if (idNumber == null) {
			if (other.idNumber != null)
				return false;
		} else if (!idNumber.equals(other.idNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Contragent [idNumber=%s, name=%s, address=%s, kind=%s, phone=%s, vatRegisterd=%s]",
				idNumber, name, address, kind, phone, vatRegisterd);
	}
	
}
