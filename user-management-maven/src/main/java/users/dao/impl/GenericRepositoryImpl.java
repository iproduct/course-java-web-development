package users.dao.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.CipherOutputStream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import users.dao.GenericRepository;
import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.model.Identifiable;

// Problem 3.IV.1
public class GenericRepositoryImpl<T extends Identifiable> implements GenericRepository<T> {
	private static long nextId = 0;
	protected Validator validator;

	protected Map<Long, T> entities = new ConcurrentHashMap<>();

	public GenericRepositoryImpl() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Override
	public Collection<T> findAll() {
		return entities.values();
	}

	@Override
	public T findById(long id) throws NonexistingEntityException {
		return entities.get(id);
	}

	@Override
	public long count() {
		return entities.size();
	}

	@Override
	public T create(T entity) throws InvalidEntityDataException {
		T exisitng = entities.get(entity.getId());
		if(exisitng != null) {
			throw new InvalidEntityDataException("Entity with ID='" + entity.getId() 
			+ " already exists!");
		}

		entity.setId(getNextId());
		
		// use HybernateValidator
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		if(!violations.isEmpty()) {
			throw new InvalidEntityDataException("Invalid entity data:" 
					+ violations.stream().map(cv -> String.format("%s %s: '%s' - %s.", 
					cv.getLeafBean().getClass().getSimpleName(), cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()))
						.reduce("", (str, cvStr) -> str + " " + cvStr));
		}
		
		entities.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public T update(T entity) throws NonexistingEntityException {
		long id = entity.getId();
		T exisitng = entities.get(id);
		if(exisitng == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		entities.put(id, entity);
		return entity;
	}

	@Override
	public T removeById(long id) throws NonexistingEntityException {
		T existing = entities.remove(id);
		if(existing == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		return existing;
	}
	
	protected long getNextId( ) {
		return ++nextId;
	}

}
