package invoicing.controller;

import java.util.Collection;
import java.util.List;

import invoicing.exceptions.InvalidEntityException;
import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Product;

public interface InvoiceRegister {
	void initialize();
	Invoice addInvoice(Invoice invoice);	
	Product addProduct(Product product) throws InvalidEntityException;	
	Contragent addIssuers(Contragent issuer);
	Contragent addCustomer(Contragent custer);
	Collection<Product> findAllProducts();
	Collection<Invoice> findAllInvoices();
	Invoice getLatestInvoice();
	Collection<Contragent> findAllIssuers();
	Collection<Contragent> findAllCustomers();
	String formatInvoice(Invoice invoice);
	double calculateVat(double price, Product product);
}	
