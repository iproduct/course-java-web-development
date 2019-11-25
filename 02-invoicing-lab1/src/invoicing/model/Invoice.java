package invoicing.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Invoice {
	public static final int WIDTH = 80;
	public static final String[] LABELS = {
		"I N V O I C E", "Number: ", "Date: ", "Issuer: ", "Customer: ", "Price: ", "VAT: ", "Total: ",
	};
	public static final String[] COLUMNS = {
		"№", "Name", "Quantity", "Unit", "Price", "VAT Price"
	};
	private long number;
	private LocalDate date = LocalDate.now();
	private Contragent issuer;
	private Contragent customer;
	private LocalDate eventDate = LocalDate.now();
	private boolean vatInvoice = true;
	private List<Position> positions = new ArrayList<>();

	public Invoice() {
	}

	public Invoice(long number, Contragent issuer, Contragent customer, List<Position> positions) {
		this.number = number;
		this.issuer = issuer;
		this.customer = customer;
		this.positions = positions;
	}

	public Invoice(long number, Contragent issuer, Contragent customer, boolean vatInvoice, List<Position> positions) {
		this.number = number;
		this.issuer = issuer;
		this.customer = customer;
		this.vatInvoice = vatInvoice;
		this.positions = positions;
	}
	
	public Invoice(long number, Contragent issuer, Contragent customer, LocalDate eventDate, boolean vatInvoice,
			List<Position> positions) {
		this.number = number;
		this.issuer = issuer;
		this.customer = customer;
		this.eventDate = eventDate;
		this.vatInvoice = vatInvoice;
		this.positions = positions;
	}

	public Invoice(long number, LocalDate date, Contragent issuer, Contragent customer, LocalDate eventDate,
			boolean vatInvoice, List<Position> positions) {
		this.number = number;
		this.date = date;
		this.issuer = issuer;
		this.customer = customer;
		this.eventDate = eventDate;
		this.vatInvoice = vatInvoice;
		this.positions = positions;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Contragent getIssuer() {
		return issuer;
	}

	public void setIssuer(Contragent issuer) {
		this.issuer = issuer;
	}

	public Contragent getCustomer() {
		return customer;
	}

	public void setCustomer(Contragent customer) {
		this.customer = customer;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public boolean isVatInvoice() {
		return vatInvoice;
	}

	public void setVatInvoice(boolean vatInvoice) {
		this.vatInvoice = vatInvoice;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (number ^ (number >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Invoice))
			return false;
		Invoice other = (Invoice) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append(formatCentered(LABELS[0], "", WIDTH)).append("\n")
		.append(formatCentered(LABELS[1], String.format("%010d", number), WIDTH)).append("\n")
//		.append(formatCentered(LABELS[2], String.format("%1$te.%1$tm.%1$tY", date), NUM_COLUMNS)).append("\n");
		.append(formatCentered(LABELS[2], formatter.format(date), WIDTH)).append("\n")
		.append("\n").append(LABELS[3]).append("\n").append(issuer).append("\n")
		.append("\n").append(LABELS[4]).append("\n").append(customer).append("\n");
		
		// format positions
		builder.append("\n").append(
			formatTableRow(new int[] {2, 30, 8, 5, 8, 10}, new char[]{'c', 'c', 'c', 'c', 'c', 'c'}, COLUMNS));
		double sum = 0;
		
		for(int i = 0; i < positions.size(); i++) {
			Position p = positions.get(i);
			builder.append(
				formatTableRow(new int[] {2, 30, 8, 5, 8, 10}, new char[]{'r', 'l', 'r', 'c', 'r', 'r'}, new String[] {
					i + "", p.getProductId() + "", p.getQuantity() + "", Unit.PCS + "", p.getPrice() + "", 
					1.2 * p.getPrice() + ""
				}));
			sum += p.getPrice() * p.getQuantity(); 
		}
		
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[5] + sum)).append("\n");
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[6] + 0.2 * sum)).append("\n");
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[7] + 1.2 * sum)).append("\n");
		return builder.toString();
	}
	
	protected String formatCentered(String label, String value, int fieldLength) {
		int txtLenght = (label.length() + value.length());
		int pos = (fieldLength - txtLenght) / 2;
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < pos; i++) {
			result.append(" ");
		}
		result.append(label).append(value);
		for(int i = 0; i < fieldLength - txtLenght - pos; i++) {
			result.append(" ");
		}
		return result.toString();
	}
	
	protected String formatTableRow(int[] cellWidths, char[] cellAlignment, String[] values) {
		StringBuilder sb = new StringBuilder("| ");
		for(int i = 0; i < cellWidths.length; i++) {
			String data = "";
			switch(cellAlignment[i]) {
				case 'c' :
				case 'C' : data = formatCentered("", values[i].trim(), cellWidths[i]); break;
				case 'r' :
				case 'R' : data = String.format("%" + cellWidths[i] + "." + cellWidths[i] + "s", values[i].trim()); break;
				default: data = String.format("%-" + cellWidths[i] + "." + cellWidths[i] + "s", values[i].trim());
			}
			sb.append(data).append(" |");
		}
		return sb.append("\n").toString();
	}

	public static void main(String[] args) {
		Contragent c1 = new Contragent(1234567890, "Ivan Petrov", "Sofia 1000", false);
		Contragent c2 = new Contragent(1234567890, "Dimitar Dimitrov", "Sofia 1000", false);
		Contragent c3 = new Contragent(131234567, "ABC Ltd.", "Sofia 1000");
		Product p1 = new Product("BK001", "Thinking in Java 4th ed.", 25.99);
		Product p2 = new Product("BK002", "UML Distilled", 25.99);
		Position pos1 = new Position(p1.getId(), 5, 23.2);
		Position pos2 = new Position(p2.getId(), 1);
		List<Position> invPositions = new ArrayList<>();
		invPositions.add(pos1);
		invPositions.add(pos2);
		Invoice inv1 = new Invoice(1, c3, c1, invPositions);
		System.out.println(inv1);
	}

}
