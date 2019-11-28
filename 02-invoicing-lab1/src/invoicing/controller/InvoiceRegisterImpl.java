package invoicing.controller;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;

public class InvoiceRegisterImpl implements InvoiceRegister {
	public static final int WIDTH = 80;
	public static final String[] LABELS = {
		"I N V O I C E", "Number: ", "Date: ", "Issuer: ", "Customer: ", "Price: ", "VAT: ", "Total: ",
	};
	public static final String[] COLUMNS = {
		"№", "Name", "Quantity", "Unit", "Price", "VAT Price"
	};
	
	private static long nextId = 0;
	private List<Product> products;
	private List<Contragent> issuers;
	private List<Contragent> customers;
	private List<Invoice> invoices;
	
	@Override
	public void initialize() {
		products = Arrays.asList(
			new Product("BK001", "Thinking in Java 4th ed.", 25.99),
			new Product("BK002", "UML Distilled", 25.99),
			new Product("BK003", "Увод в програмирането с Java", 25.99)
		);
		issuers = Arrays.asList(
			new Contragent(1234567890, "Ivan Petrov EOOD", "Sofia 1000"),
			new Contragent(1234567890, "Dimitar Dimitrov EOOD", "Sofia 1000"),
			new Contragent(131234567, "ABC Ltd.", "Sofia 1000"));
		customers = Arrays.asList(
			new Contragent(1234567890, "Ivan Petrov", "Sofia 1000", false),
			new Contragent(1234567890, "Dimitar Dimitrov", "Sofia 1000", false),
			new Contragent(131234567, "ABC Ltd.", "Sofia 1000"));
		invoices = Arrays.asList(
			new Invoice(1, issuers.get(0), customers.get(2), 
				Arrays.asList(
					new Position(products.get(0), 5),
					new Position(products.get(2), 1)
				)
		));
		
		
	}

	@Override
	public String formatInvoice(Invoice invoice) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append(formatCentered(LABELS[0], "", WIDTH)).append("\n")
		.append(formatCentered(LABELS[1], String.format("%010d", invoice.getNumber()), WIDTH)).append("\n")
//		.append(formatCentered(LABELS[2], String.format("%1$te.%1$tm.%1$tY", date), NUM_COLUMNS)).append("\n");
		.append(formatCentered(LABELS[2], formatter.format(invoice.getDate()), WIDTH)).append("\n")
		.append("\n").append(LABELS[3]).append("\n").append(invoice.getIssuer()).append("\n")
		.append("\n").append(LABELS[4]).append("\n").append(invoice.getCustomer()).append("\n");
		
		// format positions
		builder.append("\n").append(
			formatTableRow(new int[] {2, 30, 8, 5, 8, 10}, new char[]{'c', 'c', 'c', 'c', 'c', 'c'}, COLUMNS));
		double sum = 0;
		
		List<Position> positions = invoice.getPositions();
		for(int i = 0; i < positions.size(); i++) {
			Position p = positions.get(i);
			builder.append(
				formatTableRow(
					new int[] {2, 30, 8, 5, 8, 10}, 
					new char[]{'r', 'l', 'r', 'c', 'r', 'r'}, 
					new String[] {
					i + "", p.getProduct().getName() + "", p.getQuantity() + "", 
					p.getProduct().getUnit() + "", p.getPrice() + "", 
					1.2 * p.getPrice() + ""
				}));
			sum += p.getPrice() * p.getQuantity(); 
		}
		
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[5] + sum)).append("\n");
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[6] + 0.2 * sum)).append("\n");
		builder.append(String.format("%" + WIDTH + "." + WIDTH + "s", LABELS[7] + 1.2 * sum)).append("\n");
		return builder.toString();
	}
	

	@Override
	public Invoice addInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product addProduct(Product product) {
		product.setId(++nextId);
		if(products.add(product)) {
			return product;
		}
		return null;
	}

	@Override
	public Contragent addIssuers(Contragent issuer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contragent addCustomer(Contragent custer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllProducts() {
		return products;
	}

	@Override
	public List<Invoice> findAllInvoices() {
		return invoices;
	}

	@Override
	public List<Contragent> findAllIssuers() {
		return issuers;
	}

	@Override
	public List<Contragent> findAllCustomers() {
		return customers;
	}
	
	// protected methods
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
		InvoiceRegister reg = new InvoiceRegisterImpl();
		reg.initialize();
		System.out.println(reg.findAllProducts());
		Invoice inv1 = reg.findAllInvoices().get(0);
		System.out.println(reg.formatInvoice(inv1));

	}


}
