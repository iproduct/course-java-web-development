package invoicing.dao.infrastructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import invoicing.dao.IdGenerator;
import invoicing.dao.Identifiable;
import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;

public class RepositoryImpl <K,V extends Identifiable<K>> implements Repository<K, V> {
	
	protected Map<K,V> entries = new HashMap<>();
	protected IdGenerator<K> idGenerator;

	public RepositoryImpl() {
	}
	
	public RepositoryImpl(IdGenerator<K> idGenerator) {
		this.idGenerator = idGenerator;
	}
	
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

		if(idGenerator != null) {
			entity.setId(idGenerator.getNextId());
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
