package invoicing.dao;

import invoicing.control.ProductController;
import invoicing.dao.infrastructure.RepositoryImpl;
import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.model.Contragent;
import static invoicing.model.ContragentKind.*;
import invoicing.model.Customer;
import invoicing.model.Product;
import invoicing.model.Supplier;

public class ContragentRepositoryImpl extends RepositoryImpl<String, Contragent> 
	implements ContragentRepository{

	public static void main(String[] args) throws EntityExistsException, EntityDoesNotExistException {
		ContragentRepository repo = new ContragentRepositoryImpl();
		repo.add(new Supplier("123456789", "ABC Ltd.", "Sofia 1000", "RBBABZ1234566778878", 
				"RBBABZ123", "Ivan Petrov"));
		repo.add(new Customer("82122423412", "Dimitar Jekov", "Plovdiv, Ciclama 15"));
		repo.add(new Customer("234234234234", "Best Widgets Ltd.", "Plovdiv, Ciclama 15", 
				COMPANY, "359 2 342234", true, "office@best.com"));
		for(Contragent p : repo.findAll()) {
			System.out.println(p);
		}
		
		System.out.println();
		repo.update(new Customer("82122423412", "Dimitar Jekov", "Sofia, Cherni Vrah 25"));
		for(Contragent p : repo.findAll()) {
			System.out.println(p);
		}


	}

}
