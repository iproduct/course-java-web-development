package invoicing;
import static java.util.logging.Level.SEVERE;

import java.util.HashMap;
import java.util.Map;
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
import invoicing.exceptions.ActionUnsuccessfulException;
import invoicing.exceptions.InvalidEntityException;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.view.AddProductCommand;
import invoicing.view.Command;
import invoicing.view.InputUtilities;
import invoicing.view.MenuItem;
import static invoicing.view.MenuItem.*;

public class MainApp {
	private static Scanner sc = new Scanner(System.in);
	private Logger logger = Logger.getLogger(MainApp.class.getSimpleName());
	
	private InvoiceRegister invoiceRegister;
	private Map<MenuItem, Command> commands = new HashMap<>();
	
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
		
		//Map menu items to commands
		commands.put(ADD_PRODUCT, new AddProductCommand(invoiceRegister, sc));
		commands.put(PRINT_PRUCTS, () -> {
			invoiceRegister.findAllProducts().stream().forEach(System.out::println);
		});
		commands.put(EXIT, MainApp.this::finish);
//		commands.put(EXIT, () -> { finish(); });
//		commands.put(EXIT, new Command() {
//			@Override
//			public void action() throws ActionUnsuccessfulException {
//				finish();
//			}
//		});
	}
	
	public void showMainMenu() {
		String input;
		int choice;
		do {
			System.out.println();
			printMainMenu();
			input = sc.nextLine();
			choice = InputUtilities.paseInt(input, 1, MenuItem.values().length);
			if(choice < 0) {
				System.out.printf("Invalid choice - should be between %d and %d.\n", 1, MenuItem.values().length);
			} else {
				MenuItem selectedItem = MenuItem.values()[choice - 1];
				Command command = commands.get(selectedItem);
				try {
					command.action();
				} catch (ActionUnsuccessfulException e) {
					System.err.println(e.getMessage());
				}
			}
		} while (true);
	}

	private void printMainMenu() {
		for(MenuItem it: MenuItem.values()) {
			System.out.println(String.format("[%d] %s", it.ordinal() + 1, it.getText()));
		}
	}
	
	private void finish() {
		System.out.println("Goodbye from Invoicing app!");
		System.exit(0);
	}

	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.showMainMenu();
//		Product product = InputUtilities.inputProduct(sc);
//		System.out.println(product);

	}

}
