package invoicing.controller;

import java.util.List;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Product;

public interface InvoiceRegister {
	void initialize();
	Invoice addInvoice(Invoice invoice);	
	Product addProduct(Product product);	
	Contragent addIssuers(Contragent issuer);
	Contragent addCustomer(Contragent custer);
	List<Product> findAllProducts();
	List<Invoice> findAllInvoices();
	List<Contragent> findAllIssuers();
	List<Contragent> findAllCustomers();
	String formatInvoice(Invoice invoice);
	double calculateVat(double price, Product product);
}	
