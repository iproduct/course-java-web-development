package invoicing.dao;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.exceptions.FileIOException;
import invoicing.model.Measure;
import invoicing.model.Product;

public class BinaryFileProductRepositoryImpl extends ProductRepositoryImpl implements ProductRepository {

	public BinaryFileProductRepositoryImpl() {
	}

	@Override
	public Optional<Product> findById(String id) {
		if (entries.size() > 0) {
			try {
				loadProducts();
			} catch (IOException e) {
				throw new FileIOException(e);
			}
		}
		return super.findById(id);
	}

	@Override
	public Collection<Product> findAll() {
		if (entries.size() > 0) {
			try {
				loadProducts();
			} catch (IOException e) {
				throw new FileIOException(e);
			}
		}
		return super.findAll();
	}

	@Override
	public Product add(Product entity) throws EntityExistsException {
		Product result = super.add(entity);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	@Override
	public Product update(Product entity) throws EntityDoesNotExistException {
		Product result = super.update(entity);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	@Override
	public Optional<Product> delete(String id) {
		Optional<Product> result = super.delete(id);
		try {
			saveProducts();
		} catch (IOException e) {
			throw new FileIOException(e);
		}
		return result;
	}

	private void loadProducts() throws IOException {
		for(Product p : entries.values()) {
			p.getMeasure().ordinal();
		}
			
	}

	private void saveProducts() throws IOException {
		Product p = new Product();
		int ordinal = 1; // read from file
		p.setMeasure(Measure.values()[ordinal]);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
