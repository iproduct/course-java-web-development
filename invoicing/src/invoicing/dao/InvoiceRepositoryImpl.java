package invoicing.dao;

import static invoicing.model.Measure.M;
import static invoicing.model.Measure.PCS;

import invoicing.exceptions.EntityExistsException;
import invoicing.model.Contragent;
import invoicing.model.Customer;
import invoicing.model.Invoice;
import invoicing.model.OrderLine;
import invoicing.model.Product;
import invoicing.model.Supplier;

public class InvoiceRepositoryImpl extends RepositoryImpl<Long, Invoice> implements InvoiceRepository {
	private static int sequence = 0;
	
	public InvoiceRepositoryImpl() {
		super(() -> new Long(++sequence));
	}

	public static void main(String[] args) throws EntityExistsException {
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
		
		Invoice inv2 = new Invoice(s1, c1);
		inv2.addLine(new OrderLine(p1, 10, 22.30));
		inv2.addLine(new OrderLine(p2, 10));
		inv2.addLine(new OrderLine(p3, 1, 22.30));

		Invoice inv3 = new Invoice(s1, c1);
		inv3.addLine(new OrderLine(p4, 7, 22.30));

		InvoiceRepository invoiceRepo = new InvoiceRepositoryImpl();
		invoiceRepo.add(inv1);
		invoiceRepo.add(inv2);
		invoiceRepo.add(inv3);
		
		for(Invoice i: invoiceRepo.findAll()) {
			System.out.println(i);
		}

		
	}

}
