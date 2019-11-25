package invoicing.controller;

import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Product;

public interface InvoiceRegister {
	void initialize();
	Invoice addInvoice(Invoice invoice);	
	Product addProduct(Product product);	
	Contragent addIssuers(Contragent issuer);
	Contragent addCustomer(Contragent custer);
	String formatInvoice(Invoice invoice);
	
}	
