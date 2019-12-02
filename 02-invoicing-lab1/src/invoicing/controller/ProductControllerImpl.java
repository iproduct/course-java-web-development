package invoicing.controller;

import java.util.Collection;
import java.util.logging.Logger;

import invoicing.dao.IdGenerator;
import invoicing.dao.LongIdGenerator;
import invoicing.dao.MockRepository;
import invoicing.dao.Repository;
import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;
import invoicing.model.Product;
import static java.util.logging.Level.*;

public class ProductControllerImpl implements ProductController{
	private Repository<Product, Long> repo;
	private Logger logger = Logger.getLogger(ProductControllerImpl.class.getSimpleName());

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
	
	public static void main(String[] args) throws InvalidEntityException, NonexistingEntityException {
		IdGenerator<Long> longGen = new LongIdGenerator();
		Repository<Product, Long> productRepo = new MockRepository<Product, Long>(longGen);
		ProductController pc = new ProductControllerImpl(productRepo);
		// fill with sample products
		Product[] sampleProducts = { new Product("BK001", "Thinking in Java 4th ed.", 25.99),
				new Product("BK002", "UML Distilled", 25.99),
				new Product("BK003", "Увод в програмирането с Java", 25.99) };
		for (Product p : sampleProducts) {
			pc.create(p);
		}
		// test CRUD operations
		Product newProd = pc.create(new Product("BK07", "The Unified Process", 27.9));
		newProd.setPrice(22.4);
		newProd.setCode("BK005");
		pc.update(newProd);
		for(Product p: pc.findAll()) {
			System.out.println(p);
		}
		pc.delete(newProd.getId());
		System.out.println("\nAfter delete:");
		for(Product p: pc.findAll()) {
			System.out.println(p);
		}
	}

}
