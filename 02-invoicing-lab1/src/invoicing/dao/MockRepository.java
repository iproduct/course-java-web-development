package invoicing.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;

public class MockRepository<T extends Identifiable<K>, K> implements Repository<T, K> {
	private Map<K, T> entitiesMap = new HashMap<>();
	private IdGenerator<K> idGen;
	
	public MockRepository(IdGenerator<K> idGen) {
		this.idGen = idGen;
	}
	
	@Override
	public Collection<T> findAll() {
		return entitiesMap.values();
	}

	@Override
	public T findById(K id) throws NonexistingEntityException {
		T result = entitiesMap.get(id);
		if(result == null) { //entity does not exist
			throw new NonexistingEntityException("Entity with ID='" + id + "' does not exist!");
		}
		return result;
	}

	@Override
	public long count() {
		return entitiesMap.size();
	}

	@Override
	public T create(T entity) throws InvalidEntityException {
		T exisitng = entitiesMap.get(entity.getId());
		if(exisitng != null) {
			throw new InvalidEntityException("Entity with ID='" + entity.getId() 
			+ " already exists!");
		}
		K id = idGen.getNextId();
		entity.setId(id);
		entitiesMap.put(id, entity);
		return entity;
	}

	@Override
	public T update(T entity) throws NonexistingEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T delete(K id) throws NonexistingEntityException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
