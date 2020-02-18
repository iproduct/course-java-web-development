package org.iproduct.eshop.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.iproduct.eshop.entity.Item;
import org.iproduct.eshop.repository.ItemRepository;
import org.iproduct.eshop.repository.exception.NonExistingItemException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemRepositoryTest {
	public static final Item 
		ITEM1 = new Item(1L, "Computer Mouse", "Logitech", "Accessoaries",
			"High quality optical mouse", 12.5, 20),
		ITEM2 = new Item(2L, "Motherboard", "ASUS", "Motherboards",
			"AMD Athlon II x4 A03 motherboard", 125.7, 15);
	public static ItemRepository repository = ItemRepository.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp(){
//		repository.addItem(ITEM1);
//		repository.addItem(ITEM2);
	}

	@After
	public void cleanUp() throws NonExistingItemException{
//		repository.deleteItem(ITEM1.getId());
//		repository.deleteItem(ITEM2.getId());
	}

	@Test
	public final void testGetNextId() {
		// Test method
		Long actualNextId = repository.getNextId();
		// Assert
		assertEquals(new Long(1), actualNextId);
	}

	@Test
	public final void testUpdateKeyLongItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllItems() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddItem() {
		// Test method
		Item actualItem1 = repository.addItem(ITEM1);
		System.out.println(actualItem1);
		// Assert
		assertTrue(ITEM1.equals(actualItem1));
		
		Item actualItem2 = repository.getItem(actualItem1.getId());
		System.out.println(actualItem2);
		assertEquals(actualItem1, actualItem2);
	}

	@Test
	public final void testEditItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCount() {
		fail("Not yet implemented"); // TODO
	}

}
