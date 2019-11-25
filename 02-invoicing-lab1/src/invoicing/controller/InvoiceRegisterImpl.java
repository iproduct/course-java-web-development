package invoicing.controller;

import java.util.Arrays;
import java.util.List;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Product;

public class InvoiceRegisterImpl implements InvoiceRegister {
	private static long nextId = 0;
	private List<Product> products;
	private List<Contragent> contragents;
	private List<Invoice> invoices;
	

	@Override
	public void initialize() {
		products = Arrays.asList(
			new Product("BK001", "Thinking in Java 4th ed.", 25.99),
			new Product("BK002", "UML Distilled", 25.99),
			new Product("BK003", "Увод в програмирането с Java", 25.99)
		);
		
	}

	@Override
	public String formatInvoice(Invoice invoice) {
		// TODO Auto-generated method stub
		return null;
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
