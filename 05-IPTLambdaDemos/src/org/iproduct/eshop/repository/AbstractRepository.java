package org.iproduct.eshop.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.iproduct.eshop.repository.exception.NonExistingItemException;

public abstract class AbstractRepository<K,V extends Identifiable<K>> implements Repository<K,V>{
	protected Map<K,V> store = new HashMap<>();
	
	@Override
	public V getItem(K key) {
		return store.get(key);
	}

	@Override
	public Collection<V> getAllItems() {
		return store.values();
	}

	@Override
	public V addItem(V item) {
		K newKey = getNextId();
		V newItem = updateKey(newKey, item);
		store.put(newKey, newItem);
		return newItem;
	}

	@Override
	public V editItem(V item) throws NonExistingItemException {
		K key = item.getId();
		if(!store.containsKey(key))
			throw new NonExistingItemException("Item " + item + " does not exist");
		store.put(key, item);
		return item;
	}

	@Override
	public V deleteItem(K itemId) throws NonExistingItemException {
		return store.remove(itemId);
	}

	@Override
	public int count() {
		return store.size();
	}

	protected abstract K getNextId();
	protected abstract V updateKey(K newKey, V oldValue);
}
