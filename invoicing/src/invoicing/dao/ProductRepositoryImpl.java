package invoicing.dao;

import static invoicing.model.Measure.M;
import static invoicing.model.Measure.PCS;

import invoicing.control.ProductController;
import invoicing.dao.infrastructure.RepositoryImpl;
import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.model.Product;

public class ProductRepositoryImpl extends RepositoryImpl<String, Product> {

	public static void main(String[] args) throws EntityExistsException, EntityDoesNotExistException {
		ProductRepositoryImpl repo = new ProductRepositoryImpl();
		repo.add(new Product("BK1125", "Thinking in Java", 25.70, PCS));
		repo.add(new Product("CA4218", "Computer Mouse", 12.99, PCS));
		repo.add(new Product("HA0019", "Network cable", 2.17, M));
		repo.add(new Product("AX9972", "Copier paper", 12.30, PCS));
		for(Product p : repo.findAll()) {
			System.out.println(p);
		}
		
		System.out.println();
		repo.update(new Product("BK1125", "Effective Java", 35.70, PCS));
		for(Product p : repo.findAll()) {
			System.out.println(p);
		}


	}

}
