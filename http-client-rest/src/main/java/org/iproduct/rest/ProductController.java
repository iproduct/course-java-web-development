package org.iproduct.rest;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProductController {
	private static ProductController theInstance = new ProductController();
	private List<Product> products = new CopyOnWriteArrayList<>();
	public ProductController() { 
		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		products.add(product);
		Product product2 = new Product();
		product2.setName("Lenovo Laptop");
		product2.setQty(125);
		products.add(product2);
	}
	static ProductController getInstance() {
		return theInstance;
	}
	
	public Product addProduct(Product p) {
		products.add(p);
		return p;
	}
	
	public List<Product> findAll() {
		return products;
	}
}
