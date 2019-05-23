package invoicing.controller;

import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;

import invoicing.exception.EntityAlreadyExistsException;
import invoicing.model.Product;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class InvoiceRegisterTest {

	@Test
	public void givenNonexistngProduct_whenAddProduct_thenProductAddedSuccessfully() throws EntityAlreadyExistsException {
		// Setup
		InvoiceRegister register = new InvoiceRegister();
		
		// Action
		register.addProduct(SAMPLE_PRODUCTS[0]);
		
		// Check
		assertThat(register.getProducts(), contains(SAMPLE_PRODUCTS[0]));
	}

	@Test
	public void givenExistngProduct_whenfindProductByName_thenProductFound() {
		fail("Not yet implemented");
	}
	
	public static final Product[] SAMPLE_PRODUCTS = {
			new Product("BK001", "Thinking in Java", 15.7),
			new Product("HD001", "Logitech Optical Mouse", 8.75),
			new Product("SF001", "Photoshop CC", 2000),
			new Product("HD002", "Raspberry Pi 2", 80),
			new Product("SV001", "Raspbian Installation", 20),
			new Product("HD003", "3dIMU - 3D Acceelerometr, Gyrospone & Comapss", 35)
	};


}
