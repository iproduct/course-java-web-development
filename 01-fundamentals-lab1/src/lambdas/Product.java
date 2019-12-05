package lambdas;

public class Product implements Identifiable<Long>, Comparable<Product>{

	private long id;
	private String code;
	private String name;
	private double price = -1;
	private Unit unit;
	
	// 1. Constructors
	public Product() {}
	
	public Product(long id, String code, String name, double price, Unit unit) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.unit = unit;
	}	
	public Product(long id, String code, String name, double price) {
		this(id, code, name, price, Unit.PCS);
	}

	// 2. getters and setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
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
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name 
				+ ", price=" + price + ", unit=" + unit + "]";
	}
	
	@Override
	public int compareTo(Product other) {
		return (int) Math.signum(id - other.getId());
	}

	public static void main(String[] args) {
		Product p1 = new Product(1, "BK001", "Thinking in Java 4th ed.", 25.99);
		Product p2 = new Product(2, "BK002", "UML Distilled", 25.99);
		Product p3 = new Product(3, "BK003", "Увод в програмирането с Java", 25.99);
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);

	}


}
