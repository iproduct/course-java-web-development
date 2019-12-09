package invoicing.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import invoicing.exceptions.InvalidEntityException;
import invoicing.exceptions.NonexistingEntityException;
import invoicing.model.Product;

public class MockRepository<T extends Identifiable<K>, K> implements Repository<T, K> {
	private Map<K, T> entitiesMap = new HashMap<>();
	private IdGenerator<K> idGen;
	
	public <C>MockRepository(IdGenerator<K> idGen, Class<C> type) {
		this.idGen = idGen;
		Product p = new Product();
//		if(p instance K) { //...}
//		T newElem = new T();
//		T[] array = new T[5];
		try {
			C instance = type.newInstance(); //new C()
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
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
	public T create(T entity, boolean generateId) throws InvalidEntityException {
		T exisitng = entitiesMap.get(entity.getId());
		if(exisitng != null) {
			throw new InvalidEntityException("Entity with ID='" + entity.getId() 
			+ " already exists!");
		}
		if(generateId) {
			K id = idGen.getNextId();
			entity.setId(id);
		}
		
		entitiesMap.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public T update(T entity) throws NonexistingEntityException {
		K id = entity.getId();
		T exisitng = entitiesMap.get(id);
		if(exisitng == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		entitiesMap.put(id, entity);
		return entity;
	}

	@Override
	public T delete(K id) throws NonexistingEntityException {
		T existing = entitiesMap.remove(id);
		if(existing == null) {
			throw new NonexistingEntityException("Entity with ID='" + id + " does not exists!");
		}
		return existing;
	}
	
	@Override
	public void deleteAll() {
		entitiesMap.clear();		
	}
	
	public static void main(String[] args) {
		List<?>[] lsa = new List<?>[10];
	}



}
