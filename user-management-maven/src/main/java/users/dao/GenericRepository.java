package users.dao;

import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;

import users.exceptions.InvalidEntityDataException;
import users.exceptions.NonexistingEntityException;
import users.model.Identifiable;

// Problem 3.III.1
public interface GenericRepository<T extends Identifiable> {
	Collection<T> findAll();
	T findById(Long id);
	long count();
	T create(T entity) throws InvalidEntityDataException;
	T update(T entity) throws NonexistingEntityException, InvalidEntityDataException;
	T removeById(Long id);
	
	static <T> void handleConstarintViolations(Set<ConstraintViolation<T>> violations) throws InvalidEntityDataException {
		if (!violations.isEmpty()) {
			throw new InvalidEntityDataException("Invalid entity data:" + violations.stream()
					.map(cv -> String.format("%s %s: '%s' - %s.", cv.getLeafBean().getClass().getSimpleName(),
							cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()))
					.reduce("", (str, cvStr) -> str + " " + cvStr));
		}
	}
}
