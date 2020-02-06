package org.iproduct.rest;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.iproduct.rest.exception.NonExistngEntityException;

public class ProductController {
	private static AtomicLong sequence = new AtomicLong();
	private static ProductController theInstance = new ProductController();
	private Map<Long, Product> products = new ConcurrentHashMap<>();

	public ProductController() {
		Product product = new Product();
		product.setId(getNextId());
		product.setName("iPad 3");
		product.setQty(999);
		products.put(product.getId(), product);
		Product product2 = new Product();
		product.setId(getNextId());
		product2.setName("Lenovo Laptop");
		product2.setQty(125);
		products.put(product2.getId(), product2);
	}

	static ProductController getInstance() {
		return theInstance;
	}

	protected long getNextId() {
		return sequence.incrementAndGet();
	}

	public Product addProduct(Product p) {
		p.setId(getNextId());
		products.put(p.getId(), p);
		return p;
	}

	public Collection<Product> findAll() {
		return products.values();
	}

	public Product findById(long id) throws NonExistngEntityException {
		Product product = products.get(id);
		if (product != null) {
			return product;
		} else {
			throw new NonExistngEntityException("Product with ID='" + id + "' does not exist.");
		}
	}
}
