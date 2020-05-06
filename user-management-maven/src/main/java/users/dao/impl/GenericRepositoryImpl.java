package users.dao.impl;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
	public T findById(Long id) {
		return entities.get(id);
	}

	@Override
	public long count() {
		return entities.size();
	}

	@Override
	public T create(T entity) throws InvalidEntityDataException {
		if( entity.getId() != null) {
			T exisitng = entities.get(entity.getId());
			if (exisitng != null) {
				throw new InvalidEntityDataException("Entity with ID='" + entity.getId() + " already exists!");
			}
		}
 
		entity.setId(getNextId());

		// use HybernateValidator
		GenericRepository.handleConstarintViolations(validator.validate(entity));

		entities.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public T update(T entity) throws NonexistingEntityException, InvalidEntityDataException {
		Long id = entity.getId();
		if( id == null) {
			throw new InvalidEntityDataException("Null entity ID.");
		}
		T exisitng = entities.get(id);
		if (exisitng == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		// use HybernateValidator
		GenericRepository.handleConstarintViolations(validator.validate(entity));
		entities.put(id, entity);
		return entity;
	}

	@Override
	public T removeById(Long id) {
		return entities.remove(id);
	}

	protected long getNextId() {
		return ++nextId;
	}

}
