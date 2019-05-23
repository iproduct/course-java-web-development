package invoicing.model;

import static invoicing.model.Measure.PCS;

import invoicing.dao.Identifiable;

public class Product implements Identifiable<String>, Comparable<Product> {
	private String id;
	private String name;
	private double price;
	private Measure measure;
	
	public Product() {}

	public Product(String code) {
		this.id = code;
	}

	public Product(String code, String name, double price, Measure measure) {
		super();
		this.id = code;
		this.name = name;
		this.price = price;
		this.measure = measure;
	}

	public String getId() {
		return id;
	}

	public void setId(String code) {
		this.id = code;
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

	public String getHeader() {
		return String.format("| %-6.6s | %-30.30s | %8.8s | %-7.7s |", 
				"Code", "Name", "Price", "Measure");
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("| %-6.6s | %-30.30s | %8.2f | %-7.7s |",
				id, name, price, measure);
	}

	public static void main(String[] args) {
		Product p1 = new Product("BK0001", "Thinking in Java", 32.5, PCS);
		Product p2 = new Product("BK0002", "UML Distilled", 15.99, PCS);
		System.out.println(p1.getHeader());
		System.out.println(p1);
		System.out.println(p2);

	}

	@Override
	public int compareTo(Product other) {
		return getId().compareToIgnoreCase(other.getId());
	}

}
