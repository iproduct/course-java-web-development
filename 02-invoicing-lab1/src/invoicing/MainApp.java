package invoicing;
import static java.util.logging.Level.SEVERE;

import java.util.Scanner;
import java.util.logging.Logger;

import invoicing.controller.InvoiceRegister;
import invoicing.controller.InvoiceRegisterImpl;
import invoicing.controller.ProductController;
import invoicing.controller.ProductControllerImpl;
import invoicing.dao.IdGenerator;
import invoicing.dao.LongIdGenerator;
import invoicing.dao.MockRepository;
import invoicing.dao.Repository;
import invoicing.exceptions.InvalidEntityException;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.view.InputUtilities;

public class MainApp {
	private static Scanner sc = new Scanner(System.in);
	private Logger logger = Logger.getLogger(MainApp.class.getSimpleName());
	
	private InvoiceRegister invoiceRegister;
	
	public MainApp() {
		IdGenerator<Long> longGen = new LongIdGenerator();
		Repository<Product, Long> productRepo = new MockRepository<>(longGen, Product.class);
		ProductController pController = new ProductControllerImpl(productRepo);
		// fill with sample products
		Product[] sampleProducts = { new Product("BK001", "Thinking in Java 4th ed.", 25.99),
				new Product("BK002", "UML Distilled", 25.99),
				new Product("BK003", "Увод в програмирането с Java", 25.99) };
		for (Product p : sampleProducts) {
			try {
				pController.create(p);
			} catch (InvalidEntityException e) {
				logger.log(SEVERE, "Error initializing ProductController", e);
			}
		}
		invoiceRegister = new InvoiceRegisterImpl(pController);
		invoiceRegister.initialize();
	}
	
	public void showMainMenu() {
		String input;
		int choice;
		do {
			System.out.println();
			System.out.println("[1]. Input Product");
			System.out.println("[2]. Exit");
			input = sc.nextLine();
			choice = InputUtilities.paseInt(input, 1, 2);
			if(choice < 0) {
				System.out.printf("Invalid choice - should be between %d and %d.\n", 1, 2);
			} else {
				switch (choice) {
				case 1: // input product
					Product product = InputUtilities.inputProduct(sc);
					if(product != null) {
						Product created;
						try {
							created = invoiceRegister.addProduct(product);
							System.out.printf("Successfully added product: %d:%s-%8.2f\n", 
									created.getId(), created.getName(), created.getPrice());
						} catch (InvalidEntityException e) {
							logger.log(SEVERE, "Error creating product: " + product, e);
							System.err.println("Error creating product: " + product);
						}
						
					}
					break;
				}
				
			}
		} while (choice < 2);
		System.out.println("Goodbye from Invoicing app!");
	}

	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.showMainMenu();
//		Product product = InputUtilities.inputProduct(sc);
//		System.out.println(product);

	}

}
