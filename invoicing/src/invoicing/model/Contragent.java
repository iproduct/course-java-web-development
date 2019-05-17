package invoicing.model;

import invoicing.dao.Identifiable;

public class Contragent implements Identifiable<String>{
	private String id;
	private String name;
	private String address;
	private ContragentKind kind = ContragentKind.COMPANY;
	private String phone;
	private boolean vatRegisterd = true;;
	
	public Contragent() {}

	public Contragent(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Contragent(String id, String name, String address, ContragentKind kind, String phone, boolean vatRegisterd) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.kind = kind;
		this.phone = phone;
		this.vatRegisterd = vatRegisterd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("| %12.12s | %20.20s | %20.20s | %5.5s | %15.15s | %4.4s |",
				id, name, address, kind, phone, vatRegisterd);
	}
	
}
