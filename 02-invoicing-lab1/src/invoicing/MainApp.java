package invoicing;
import static java.util.logging.Level.SEVERE;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
	public static final String CONFIG_FILE_NAME = "application.properties";
	private static Scanner sc = new Scanner(System.in);
	private Logger logger = Logger.getLogger(MainApp.class.getSimpleName());
	
	private InvoiceRegister invoiceRegister;
	private Map<MenuItem, Command> commands = new HashMap<>();
	
	private static Properties appProps;
	
	public MainApp() {
		//read configuration properties first
		readConfig(CONFIG_FILE_NAME);
		
		IdGenerator<Long> longGen = new LongIdGenerator();
		Repository<Product, Long> productRepo = new MockRepository<>(longGen, Product.class);
		ProductController pController = new ProductControllerImpl(productRepo);
//		// fill with sample products
//		Product[] sampleProducts = { new Product("BK001", "Thinking in Java 4th ed.", 25.99),
//				new Product("BK002", "UML Distilled", 25.99),
//				new Product("BK003", "Увод в програмирането с Java", 25.99) };
//		for (Product p : sampleProducts) {
//			try {
//				pController.create(p, true);
//			} catch (InvalidEntityException e) {
//				logger.log(SEVERE, "Error initializing ProductController", e);
//			}
//		}
		invoiceRegister = new InvoiceRegisterImpl(pController);
//		invoiceRegister.initialize();
		
		//Map menu items to commands
		commands.put(ADD_PRODUCT, new AddProductCommand(invoiceRegister, sc));
		commands.put(PRINT_PRUCTS, () -> {
			invoiceRegister.findAllProducts().stream().forEach(System.out::println);
		});
		commands.put(WRITE_TO_FILE, () -> {
			String dbFileName = appProps.getProperty("invoice.db.filename", "default.db");
			try(DataOutputStream db = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(dbFileName)))) {
				Collection<Product> products = invoiceRegister.findAllProducts();
				db.writeInt(products.size());
				for(Product p : products) {
					db.writeLong(p.getId());
					db.writeUTF(p.getCode());
					db.writeUTF(p.getName());
					db.writeDouble(p.getPrice());
					db.writeInt(p.getUnit().ordinal());
				}
			} catch (IOException e) {
				logger.log(SEVERE, "Error reding database file: " + dbFileName, e);
			} 
		});
		commands.put(READ_FROM_FILE, () -> {
			String dbFileName = appProps.getProperty("invoice.db.filename", "default.db");
			try(DataInputStream db = new DataInputStream(
					new BufferedInputStream(new FileInputStream(dbFileName)))) {
				int size = db.readInt();
				invoiceRegister.deleteAllProducts();
				for(int i = 0; i < size; i++) {
					Product p = new Product();
					p.setId(db.readLong());
					p.setCode(db.readUTF());
					p.setName(db.readUTF());
					p.setPrice(db.readDouble());
					p.setUnit(Unit.values()[db.readInt()]);	
					try {
						invoiceRegister.addProduct(p, false);
					} catch (InvalidEntityException e) {
						System.err.println(e.getMessage());
					}
				}
			} catch (FileNotFoundException e) {
				logger.log(SEVERE, "Database file '" + dbFileName + "' not found.", e);
			} catch (IOException e) {
				logger.log(SEVERE, "Error reding database file: " + dbFileName, e);
			} 
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
	
	public static Properties getAppProps() {
		return appProps;
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
	
	private void readConfig(String configFileName) {
		InputStream propsInputStream = 
				Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName);
		Properties defaultProps = new Properties();
		defaultProps.setProperty("invoice.print.width", "60");
		appProps = new Properties(defaultProps);
		try {
			appProps.load(propsInputStream);
		} catch (NullPointerException | IOException e) {
			logger.log(SEVERE, "Error loading application.properties file.", e);
		}
	}

	public static void main(String[] args) {
		MainApp app = new MainApp();
		app.showMainMenu();
//		Product product = InputUtilities.inputProduct(sc);
//		System.out.println(product);

	}

}
