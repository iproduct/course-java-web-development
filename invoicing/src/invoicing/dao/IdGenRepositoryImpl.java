package invoicing.dao;

import invoicing.exceptions.EntityExistsException;

public class IdGenRepositoryImpl<K, V extends Identifiable<K>> extends RepositoryImpl<K,V> 
		implements Repository<K, V>{
	private IdAutoIncrementable<K> idGenerator;

	public IdGenRepositoryImpl(IdAutoIncrementable<K> idGenerator) {
		this.idGenerator = idGenerator;
	}

	@Override
	public void setIdGenerator(IdAutoIncrementable<K> idGen) {
		idGenerator = idGen;
	}

	@Override
	public IdAutoIncrementable<K> getIdGenerator() {
		return idGenerator;
	}

	@Override
	public V add(V entity) throws EntityExistsException {
		entity.setId(idGenerator.getNextId());
		return super.add(entity);
	}
	
	

}
