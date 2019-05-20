package invoicing.dao;

import java.util.Collection;
import java.util.Optional;

import invoicing.dao.infrastructure.RepositoryImpl;
import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.model.Product;

public class BinaryFileProductRepositoryImpl extends ProductRepositoryImpl 
		implements ProductRepository {

	public BinaryFileProductRepositoryImpl() {
	}

	@Override
	public Optional<Product> findById(String id) {
		if(entries.size() > 0) {
			loadProducts();
		}
		return super.findById(id);
	}



	@Override
	public Collection<Product> findAll() {
		if(entries.size() > 0) {
			loadProducts();
		}
		return super.findAll();
	}



	@Override
	public Product add(Product entity) throws EntityExistsException {
		Product result = super.add(entity);
		saveProducts();
		return result;
	}



	@Override
	public Product update(Product entity) throws EntityDoesNotExistException {
		Product result = super.update(entity);
		saveProducts();
		return result;
	}



	@Override
	public Optional<Product> delete(String id) {
		Optional<Product> result = super.delete(id);
		saveProducts();
		return result;
	}

	private void loadProducts() {
		
		
	}
	
	private void saveProducts() {
		
		
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
