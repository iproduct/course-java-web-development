package invoicing.dao;

import java.util.Collection;

import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;

public interface Repository<T extends Identifiable<K>, K> {
	Collection<T> findAll();
	T findById(K id) throws NonexistingEntityException;
	long count();
	T create(T entity) throws InvalidEntityException;
	T update(T entity) throws NonexistingEntityException;
	T delete(K id)  throws NonexistingEntityException;
}
