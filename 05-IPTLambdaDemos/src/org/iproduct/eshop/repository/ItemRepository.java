package org.iproduct.eshop.repository;

import org.iproduct.eshop.entity.Item;

public class ItemRepository extends AbstractRepository<Long, Item> {
	private static long sequence = 0;
	private static ItemRepository theRepository = new ItemRepository();
	
	private ItemRepository(){
	}
	
	public static ItemRepository getInstance(){
		return theRepository;
	}
	
	@Override
	protected Long getNextId() {
		return ++sequence;
	}

	@Override
	protected Item updateKey(Long newKey, Item oldValue) {
		oldValue.setId(newKey);
		return oldValue;
	}


}
