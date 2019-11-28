package invoicing.model;

public class Position {
	private Product product;
	private double quantity;
	private double price = -1;

	public Position() {
	}

	public Position(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Position(Product product, double quantity, double price) {
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		temp = Double.doubleToLongBits(quantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Double.doubleToLongBits(quantity) != Double.doubleToLongBits(other.quantity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("Position [product=");
		builder.append(product).append(", quantity=")
			.append(quantity).append(", price=").append(price).append("]");
		return builder.toString();
	}

	public static void main(String[] args) {
		Product p1 = new Product("BK001", "Thinking in Java 4th ed.", 25.99);
		Product p2 = new Product("BK002", "UML Distilled", 25.99);
		Position pos1 = new Position(p1, 5, 23.2);
		Position pos2 = new Position(p2, 1);
		System.out.println(pos1);
		System.out.println(pos2);
		
	}

}
