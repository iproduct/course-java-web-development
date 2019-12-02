package invoicing.controller;

import java.util.Collection;

import invoicing.dao.MockRepository;
import invoicing.dao.Repository;
import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;
import invoicing.model.Product;

public class ProductControllerImpl implements ProductController{
	private Repository<Product, Long> repo;

	public ProductControllerImpl(Repository<Product, Long> repo) {
		this.repo = repo;
	}

	@Override
	public Collection<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Product findById(long id) throws NonexistingEntityException {
		return repo.findById(id);
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public Product create(Product entity) throws InvalidEntityException {
		return repo.create(entity);
	}

	@Override
	public Product update(Product entity) throws NonexistingEntityException {
		return repo.update(entity);
	}

	@Override
	public Product delete(long id) throws NonexistingEntityException {
		return repo.delete(id);
	}
	
	public static void main(String[] args) {
		
		Repository<Product, Long> productRepo = new MockRepository<>(idGen);
		ProductController pc = new ProductControllerImpl(productRepo);

	}

}
