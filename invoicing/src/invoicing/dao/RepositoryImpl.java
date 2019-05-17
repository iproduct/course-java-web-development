package invoicing.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;

public class RepositoryImpl <K,V extends Identifiable<K>> implements Repository<K, V> {
	
	private Map<K,V> entries = new HashMap<>();
	
	@Override
	public Optional<V> findById(K id) {
		return Optional.ofNullable(entries.get(id));
	}

	@Override
	public Collection<V> findAll() {
		return entries.values();
	}

	@Override
	public V add(V entity) throws EntityExistsException {
		if( entries.containsKey(entity.getId()) ) {
			throw new EntityExistsException(String.format("Entity with ID=%s already exists.", 
					entity.getId()));
		}
		entries.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public V update(V entity) throws EntityDoesNotExistException{
		if( !entries.containsKey(entity.getId()) ) {
			throw new EntityDoesNotExistException(String.format("Entity with ID=%s does not exist.", 
					entity.getId()));
		}
		entries.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public Optional<V> delete(K id) {
		return Optional.ofNullable(entries.remove(id));
	}

}
