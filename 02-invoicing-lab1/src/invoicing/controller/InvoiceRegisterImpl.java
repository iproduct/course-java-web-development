package invoicing.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import static java.util.logging.Level.*;
import java.util.logging.Logger;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;
import invoicing.model.Unit;

public class InvoiceRegisterImpl implements InvoiceRegister {
	public static final double VAT_RATE = 0.2;
	public static final String[] LABELS = {
		"I N V O I C E", "Number: ", "Date: ", "Issuer: ", "Customer: ", "Price: ", "VAT: ", "Total: ", "Event Date:",
	};
	public static final String[] COLUMNS = {
		"№", "Name", "Quantity", "Unit", "Price", "VAT Price", "Total"
	};
	
	private static long nextId = 0;
	private List<Product> products;
	private List<Contragent> issuers;
	private List<Contragent> customers;
	private List<Invoice> invoices;
	private int invoiceWidth = 40;
	private Logger logger = Logger.getLogger(InvoiceRegisterImpl.class.getSimpleName());
	
	public InvoiceRegisterImpl() {
		initialize();
	}
	
	@Override
	public void initialize() {
		products = new ArrayList<Product>();
		Product[] sampleProducts = {
			new Product("BK001", "Thinking in Java 4th ed.", 25.99),
			new Product("BK002", "UML Distilled", 25.99),
			new Product("BK003", "Увод в програмирането с Java", 25.99)
		};
		for(Product p : sampleProducts) {
			addProduct(p);
		}
		
		issuers = new ArrayList<Contragent>(Arrays.asList(
			new Contragent(1234567890, "Ivan Petrov EOOD", "Sofia 1000"),
			new Contragent(1234567890, "Dimitar Dimitrov EOOD", "Sofia 1000"),
			new Contragent(131234567, "ABC Ltd.", "Sofia 1000")));
		
		customers = new ArrayList<Contragent>(Arrays.asList(
			new Contragent(1234567890, "Ivan Petrov", "Sofia 1000", false),
			new Contragent(1234567890, "Dimitar Dimitrov", "Sofia 1000", false),
			new Contragent(131234567, "ABC Ltd.", "Sofia 1000")));
		
		invoices = new ArrayList<Invoice>(Arrays.asList(
			new Invoice(1, issuers.get(0), customers.get(2), 
				Arrays.asList(
					new Position(products.get(0), 5),
					new Position(products.get(2), 1)
				)
		)));
		
		InputStream propsInputStream = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		Properties defaultProps = new Properties();
		defaultProps.setProperty("invoice.print.width", "60");
		Properties appProps = new Properties(defaultProps);
		try {
			appProps.load(propsInputStream);
		} catch (NullPointerException | IOException e) {
			e.printStackTrace();
			logger.log(SEVERE, "Error loading application.properties file.", e);
		}
		try {
			invoiceWidth = Integer.parseInt(appProps.getProperty("invoice.print.width", "40"));
		} catch (NumberFormatException ex) {
			logger.log(SEVERE, "Error parsing invoice.print.width property.", ex);
		}
	}

	@Override
	public String formatInvoice(Invoice invoice) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		StringBuilder builder = new StringBuilder();
		builder.append(formatCentered(LABELS[0], "", invoiceWidth)).append("\n")
		.append(formatCentered(LABELS[1], String.format("%010d", invoice.getNumber()), invoiceWidth)).append("\n")
//		.append(formatCentered(LABELS[2], String.format("%1$te.%1$tm.%1$tY", date), NUM_COLUMNS)).append("\n");
		.append(formatCentered(LABELS[2], formatter.format(invoice.getDate()), invoiceWidth)).append("\n");
		// Event date
//		SimpleTimeZone eet = new SimpleTimeZone(2 * 60 * 60 * 1000, "Europe/Sofia");
//		eet.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
//		ZonedDateTime zdt = ZonedDateTime.of(invoice.getEventDate().atTime(LocalTime.of(0, 0)), eet.toZoneId());
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		String formattedDateTime = invoice.getEventDate().format(formatter2); // "1986-04-08 12:30"
		builder.append(formatCentered(LABELS[8], formattedDateTime, invoiceWidth)).append("\n");
		
		builder.append("\n").append(LABELS[3]).append("\n").append(invoice.getIssuer()).append("\n")
		.append("\n").append(LABELS[4]).append("\n").append(invoice.getCustomer()).append("\n");
		
		
		
		// format positions
		builder.append("\n").append(
			formatTableRow(new int[] {2, 33, 8, 5, 8, 9, 8}, new char[]{'c', 'c', 'c', 'c', 'c', 'c', 'c'}, COLUMNS));
		double sum = 0;
		
		List<Position> positions = invoice.getPositions();
		for(int i = 0; i < positions.size(); i++) {
			Position pos = positions.get(i);
			double price = getPositionPrice(pos);
			double vat = invoice.isVatInvoice() ? calculateVat(price, pos.getProduct()): 0;
			double vatPrice = price + vat;
			double total = price * pos.getQuantity();
			builder.append(
				formatTableRow(
					new int[] {2, 33, 8, 5, 8, 9, 8}, 
					new char[]{'r', 'l', 'r', 'c', 'r', 'r', 'r'}, 
					new String[] {
					i + "", pos.getProduct().getName() + "", 
					pos.getProduct().getUnit().equals(Unit.PCS) ? String.format("%8.0f", pos.getQuantity()) 
							: String.format("%8.2f", pos.getQuantity()),
					pos.getProduct().getUnit() + "", String.format("%8.2f", price), 
					String.format("%8.2f", vatPrice), String.format("%8.2f", total)
				}));
			sum += total; 
		}
		
		double totalVat = calculateVat(sum, null);
		
		builder.append(String.format("%" + invoiceWidth + "." + invoiceWidth + "s", LABELS[5] 
				+ String.format("%8.2f", sum))).append("\n");
		builder.append(String.format("%" + invoiceWidth + "." + invoiceWidth + "s", LABELS[6] 
				+ String.format("%8.2f", totalVat))).append("\n");
		builder.append(String.format("%" + invoiceWidth + "." + invoiceWidth + "s", LABELS[7] 
				+ String.format("%8.2f", sum + totalVat))).append("\n");
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
	
	@Override
	public double calculateVat(double price, Product product) {
		return VAT_RATE * price;
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
		StringBuilder sb = new StringBuilder("|");
		for(int i = 0; i < cellWidths.length; i++) {
			String data = "";
			switch(cellAlignment[i]) {
				case 'c' :
				case 'C' : data = formatCentered("", values[i].trim(), cellWidths[i]); break;
				case 'r' :
				case 'R' : data = String.format("%" + cellWidths[i] + "." + cellWidths[i] + "s", values[i].trim()); break;
				default: data = String.format("%-" + cellWidths[i] + "." + cellWidths[i] + "s", values[i].trim());
			}
			sb.append(data).append("|");
		}
		return sb.append("\n").toString();
	}
	
	protected double getPositionPrice(Position pos) {
		if(pos.getPrice() >= 0) {
			return pos.getPrice();
		} else {
			return pos.getProduct().getPrice();
		}
	}

	
	public static void main(String[] args) {
		InvoiceRegister reg = new InvoiceRegisterImpl();
		System.out.println(reg.findAllProducts());
		Invoice inv1 = reg.findAllInvoices().get(0);
		System.out.println(reg.formatInvoice(inv1));

	}



}
