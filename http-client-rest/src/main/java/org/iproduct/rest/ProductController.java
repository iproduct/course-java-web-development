package org.iproduct.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class ProductController {
	private static AtomicLong sequence= new AtomicLong();
	private static ProductController theInstance = new ProductController();
	private List<Product> products = new CopyOnWriteArrayList<>();
	public ProductController() { 
		Product product = new Product();
		product.setId(getNextId());
		product.setName("iPad 3");
		product.setQty(999);
		products.add(product);
		Product product2 = new Product();
		product.setId(getNextId());
		product2.setName("Lenovo Laptop");
		product2.setQty(125);
		products.add(product2);
	}
	static ProductController getInstance() {
		return theInstance;
	}
	
	protected long getNextId() {
		return sequence.incrementAndGet();
	}
	
	public Product addProduct(Product p) {
		p.setId(getNextId());
		products.add(p);
		return p;
	}
	
	public List<Product> findAll() {
		return products;
	}
	
	public Product findById(String id) {
		return products.get(0);
	}
}
