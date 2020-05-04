package users.dao;

import java.util.Collection;

import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.model.Identifiable;

// Problem 3.III.1
public interface GenericRepository<T extends Identifiable> {
	Collection<T> findAll();
	T findById(long id) throws NonexistingEntityException;
	long count();
	T create(T entity) throws InvalidEntityDataException;
	T update(T entity) throws NonexistingEntityException;
	T removeById(long id) throws NonexistingEntityException;
}
