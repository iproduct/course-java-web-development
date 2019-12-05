package genrics;

public class Service extends Product {
	private String period;
	public Service() {
		super();
	}
	public Service(long id, String code, String name, double price, Unit unit, String period) {
		super(id, code, name, price, unit);
		this.period = period;
	}
	public Service(String code, String name, double price, Unit measure, String period) {
		super(code, name, price, measure);
		this.period = period;
	}
	public Service(String code, String name, double price, String period) {
		super(code, name, price);
		this.period = period;
	}
	public String getAuthor() {
		return period;
	}
	public void setAuthor(String period) {
		this.period = period;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [period=").append(period)
		.append(", toString()=").append(super.toString()).append("]");
		return builder.toString();
	}
}
