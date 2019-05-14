package invoicing.model;

import static invoicing.model.Measure.*;

public class Product {
	private long id;
	private String code;
	private String name;
	private double price;
	private Measure measure;
	
	public Product() {}

	public Product(String code, String name, double price, Measure measure) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.measure = measure;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Measure getMeasure() {
		return measure;
	}

	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getHeader() {
		return String.format("| %-9.9s | %-6.6s | %-30.30s | %8.8s | %-7.7s |", 
				"ID", "Code", "Name", "Price", "Measure");
	}
	
	@Override
	public String toString() {
		return String.format("| %09d | %-6.6s | %-30.30s | %8.2f | %-7.7s |",
				id, code, name, price, measure);
	}

	public static void main(String[] args) {
		Product p1 = new Product("BK0001", "Thinking in Java", 32.5, PCS);
		p1.setId(1);
		System.out.println(p1.getHeader());
		System.out.println(p1);

	}

}
