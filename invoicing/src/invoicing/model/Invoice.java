package invoicing.model;

import static invoicing.model.Measure.M;
import static invoicing.model.Measure.PCS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import invoicing.dao.Identifiable;
import invoicing.utils.Tuple;

public class Invoice implements Identifiable<Long>{
	private Long id;
	private LocalDate date = LocalDate.now();
	private Supplier supplier;
	private Customer customer;
	private List<OrderLine> lines = new ArrayList<OrderLine>();

	public Invoice() {
	}

	public Invoice(Long id) {
		this.id = id;
	}

	public Invoice(Supplier supplier, Customer customer) {
		this.supplier = supplier;
		this.customer = customer;
	}

	public Invoice(Supplier supplier, Customer customer, List<OrderLine> lines) {
		super();
		this.supplier = supplier;
		this.customer = customer;
		this.lines = lines;
	}
	
	public Invoice(Long id, LocalDate date, Supplier supplier, Customer customer, List<OrderLine> lines) {
		super();
		this.id = id;
		this.date = date;
		this.supplier = supplier;
		this.customer = customer;
		this.lines = lines;
	}
	
	public void addLine(OrderLine line) {
		lines.add(line);
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
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderLine> getLines() {
		return lines;
	}

	public void setLines(List<OrderLine> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return String.format("Invoice No.: %1$010d"
				+ "\nDate: %2$te.%2$tm.%2$tY"
				+ "\nSupplier:\n%3$s"
				+ "\nCustomer:\n%4$s"
				+ "\n\n%5$s",
				id, date, supplier.toString(),	customer.toString(), 
				lines.stream()
					.map(line -> String.format("%s %7.2f |\n",	line.toString(), line.getLinePrice()) )
					.map(line -> new Tuple(line, 1))
					.reduce(new Tuple<String, Integer>("", 1), 
						(accPos, linePos) -> { 
							return new Tuple<>(
								accPos.getProp1() + "| " + accPos.getProp2() + " " + linePos.getProp1(), 
								(int)accPos.getProp2() + 1);
						}
					).getProp1()
//					.reduce(new Tuple<String, Integer>("", 1), 
//							(accPos, line) -> { 
//								return new Tuple<String, Integer>(
//									accPos.getProp1() + "| " + accPos.getProp2() + " " + line, 
//									accPos.getProp2() + 1);
//							},
//							(accPos, pos) -> { 
//								return new Tuple<>(
//									accPos.getProp1() + pos.getProp1(), 
//									accPos.getProp2() + pos.getProp2());
//							}
//						).getProp1() 
				);
	}

	public static void main(String[] args) {
		Supplier s1 = new Supplier("123456789", "ABC Ltd.", "Sofia 1000", "RBBABZ1234566778878", 
				"RBBABZ123", "Ivan Petrov");
		Customer c1 = new Customer("82122423412", "Dimitar Jekov", "Plovdiv, Ciclama 15");
		Invoice inv1 = new Invoice(s1, c1);
		
		Product p1 = new Product("BK1125", "Thinking in Java", 25.70, PCS);
		Product p2 = new Product("CA4218", "Computer Mouse", 12.99, PCS);
		Product p3 = new Product("HA0019", "Network cable", 2.17, M);
		Product p4 = new Product("AX9972", "Copier paper", 12.30, PCS);
		
		inv1.addLine(new OrderLine(p1, 10, 22.30));
		inv1.addLine(new OrderLine(p2, 10));
		inv1.addLine(new OrderLine(p3, 1, 22.30));
		inv1.addLine(new OrderLine(p1, 3));
		
		System.out.println(inv1);

	}

	


}
