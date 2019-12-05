package invoicing.view;

import java.util.Scanner;

import invoicing.controller.InvoiceRegister;
import invoicing.exceptions.ActionUnsuccessfulException;
import invoicing.exceptions.InvalidEntityException;
import invoicing.model.Product;

public class AddProductCommand implements Command {
	private InvoiceRegister register;
	private Scanner sc;

	public AddProductCommand(InvoiceRegister register, Scanner sc) {
		this.register = register;
		this.sc = sc;
	}

	@Override
	public void action() throws ActionUnsuccessfulException {
		Product product = InputUtilities.inputProduct(sc);
		if(product != null) {
			Product created;
			try {
				created = register.addProduct(product);
				System.out.printf("Successfully added product: %d:%s-%8.2f\n", 
						created.getId(), created.getName(), created.getPrice());
			} catch (InvalidEntityException e) {
//				logger.log(SEVERE, "Error creating product: " + product, e);
				throw new ActionUnsuccessfulException("Error creating product: " + product, e);
			}
			
		}
	}

}
