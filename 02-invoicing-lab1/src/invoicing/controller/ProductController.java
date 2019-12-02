package invoicing.controller;

import java.util.Collection;

import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;
import invoicing.model.Product;

public interface ProductController {
	Collection<Product> findAll();
	Product findById(long id) throws NonexistingEntityException;
	long count();
	Product create(Product entity) throws InvalidEntityException;
	Product update(Product entity) throws NonexistingEntityException;
	Product delete(long id)  throws NonexistingEntityException;
}
