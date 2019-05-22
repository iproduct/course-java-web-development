package invoicing.view;

import static invoicing.view.MenuCommand.ADD_CONTRAGENT;
import static invoicing.view.MenuCommand.ADD_INVOICE;
import static invoicing.view.MenuCommand.ADD_PRODUCT;
import static invoicing.view.MenuCommand.EXIT;
import static invoicing.view.MenuCommand.GENERATE_INVOICING_REPORT_AS_CSV_FILE;
import static invoicing.view.MenuCommand.LIST_ALL_CONTRAGENTS;
import static invoicing.view.MenuCommand.LIST_ALL_PRODUCTS;
import static invoicing.view.MenuCommand.LOAD;
import static invoicing.view.MenuCommand.NONE;
import static invoicing.view.MenuCommand.PRINT_LAST_INVOICE;
import static invoicing.view.MenuCommand.PRINT_LAST_INVOICE_TO_FILE;
import static invoicing.view.MenuCommand.SAVE;
import static invoicing.view.MenuCommand.SET_ISSUER;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import invoicing.controller.InvoiceRegister;
import invoicing.exception.EntityAlreadyExistsException;
import invoicing.model.Company;
import invoicing.model.Contragent;
import invoicing.model.Product;
import invoicing.util.ContragentComparatorByName;
import invoicing.util.ProductComparatorByCode;

public class MainMenu {
	private static final String SESSION_FILENAME = "invoices.db";
	private static final String MENU_CONFIG_FILENAME = "menuconfig.txt";
	private static final String INVOICES_DIR = "invoices";
	private static final String INVOICES_FILENAME_PREFIX = "invoice_";
	private static final String INVOICES_FILENAME_SUFFIX = ".txt";
	
	private static final String[] menuItemStrings = { 
			"EXIT from this program = EXIT",
			"Add product = ADD_PRODUCT",
			"List all products = LIST_ALL_PRODUCTS", 
			"Add new contragent = ADD_CONTRAGENT",
			"List all contragents = LIST_ALL_CONTRAGENTS", 
			"Choose company issuing invoices = SET_ISSUER",
			"Issue new invoice = ADD_INVOICE",
			"Print last invoice = PRINT_LAST_INVOICE",
			"Print last invoice to file (invoices/invoice_XXXXXXXXXX.txt) = PRINT_LAST_INVOICE_TO_FILE",
			"List all invoices = LIST_ALL_INVOICES",
			"Generate invoicing report as CSV file = GENERATE_INVOICING_REPORT_AS_CSV_FILE" };

	private static final List<MenuItem> menuItems = new ArrayList<>();
	private static final Map<MenuCommand, Command> commands = new EnumMap<MenuCommand, Command>(MenuCommand.class);
	private static InvoiceRegister register = new InvoiceRegister(); 
	private final Scanner in;

	public MainMenu(InputStream inStream) {
		this.in = new Scanner(inStream);
		List<Contragent> contragents = new ArrayList<>(Arrays.asList(InvoiceRegister.SAMPLE_CONTRAGENTS));
		contragents.addAll(Arrays.asList(InvoiceRegister.SAMPLE_COMPANIES));

		// Initialize the InvoiceRegister singleton
		register.initialize(Arrays.asList(InvoiceRegister.SAMPLE_PRODUCTS), contragents);

		// Parse menu items
		try {
			BufferedReader configuration = new BufferedReader(
					new FileReader(MENU_CONFIG_FILENAME));
			String line;
			while((line = configuration.readLine())!= null) {
				menuItems.add(parseMenuItemString(line));
			}
		} catch (FileNotFoundException e1) {
			System.err.println("Menu cofiguration file not found: " + MENU_CONFIG_FILENAME);
			loadMenuConfigDefaults();
		} catch (IOException e) {
			System.err.println("Error reding menu cofiguration file: " + MENU_CONFIG_FILENAME);
			loadMenuConfigDefaults();
		}
		
		
		// Add commands to map.
		commands.put(NONE, () -> true);
		commands.put(EXIT, () -> {
				System.out.println("You are goiung to exit. All unsaved data will be lost. Are you sure? [y/n]:");
				if(in.nextLine().toLowerCase().startsWith("y")){
					System.out.println("Exiting from invoicing program ...");
					System.exit(0);
				}
				return true;
		});
		commands.put(ADD_PRODUCT, new Command() {
			@Override
			public boolean action() {
				Product newProduct = InputUtils.inputProduct(in);
				if(newProduct == null) {  //canceled
					System.out.println("Input product canceled. Product NOT added.");
					return false;
				}
				try {
					register.addProduct(newProduct);
					return true;
				} catch (EntityAlreadyExistsException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
		});
		commands.put(LIST_ALL_PRODUCTS, new Command() {
			@Override
			public boolean action() {
				System.out.println("LIST OF ALL PRODUCTS:");
				System.out.println("======================");
				register.printAllProductsSorted(new ProductComparatorByCode());
				return true;
			}
		});
		commands.put(ADD_CONTRAGENT, new Command() {
			@Override
			public boolean action() {
				Contragent newContragent = InputUtils.inputContragent(in);
				if(newContragent == null) {  //canceled
					System.out.println("Input contragent canceled. Contragent NOT added.");
					return false;
				}
				if(newContragent.isOrganization()) {
					newContragent = InputUtils.inputCompanyDeltas(in, newContragent);
				}
				if(newContragent == null) {  //canceled
					System.out.println("Input comapny canceled. Company NOT added.");
					return false;
				}
				try {
					register.addContragent(newContragent);
					System.out.println("New contragent was successfully added: " + newContragent);
					return true;
				} catch (EntityAlreadyExistsException e) {
					System.err.println(e.getMessage());
					return false;
				}
			}
		});
		commands.put(LIST_ALL_CONTRAGENTS, new Command() {
			@Override
			public boolean action() {
				System.out.println("LIST OF ALL CONTRAGENTS:");
				System.out.println("======================");
				register.printAllContragentsSorted(new ContragentComparatorByName());
				return true;
			}
		});
		commands.put(SET_ISSUER, new Command() {
			@Override
			public boolean action() {
				boolean issuerSelected = false;
				do {
					System.out.println("Please enter the current invoice ISSUER Id Number (h for help, empty line for cancel):");
					String input = in.nextLine().trim();
					if (input.isEmpty()) {
						System.out.println("Current issuer selection canceled. Issuer NOT changed: " + register.getCurrentIssuer());
						return false; //cancel
					}
					if(input.toLowerCase().equals("h")){ //help
						register.printAllOrganizationsSorted(new ContragentComparatorByName());
						continue;
					}
					try{
						long idNumber = Long.parseLong(input);
						Contragent issuer = register.findContragentByIdNumber(idNumber);
						if(issuer == null || !(issuer instanceof Company) ) {
							System.err.println("Invalid company Id Number. Please try again.");
							continue;
						}
						register.setCurrentIssuer((Company) issuer);
						System.out.println("Current issuer selected: " + register.getCurrentIssuer());
						issuerSelected = true;
					} catch (NumberFormatException e){
						System.err.println("Invalid company Id Number - should be 9-12 digits. Please try again.");
					}
				} while (!issuerSelected);
				return true;
			}
		});
		commands.put(ADD_INVOICE, new Command() {
			@Override
			public boolean action() {
				return InputUtils.inputInvoice(in, register);
			}
		});
		commands.put(PRINT_LAST_INVOICE, new Command() {
			@Override
			public boolean action() {
				if(register.getLastInvoice() != null) {
					System.out.println( OutputUtils.formatInvoice(register.getLastInvoice()) );
					System.out.println("\n\n");
					return true;
				} else {
					System.out.println("No invoice issued during last session.");
					return false;
				}
			}
		});
		commands.put(PRINT_LAST_INVOICE_TO_FILE, new Command() {
			@Override
			public boolean action() {
				if(register.getLastInvoice() != null) {
					String fileName = INVOICES_DIR + "/" + INVOICES_FILENAME_PREFIX 
							+ String.format("%010d", register.getLastInvoice().getNumber())
							+ INVOICES_FILENAME_SUFFIX;
					File dir = new File(INVOICES_DIR);
					if(dir.exists() || dir.mkdirs()) { // check if invoices directory exists or create it if not
						try (PrintWriter out = new PrintWriter(fileName)){
							out.println( OutputUtils.formatInvoice(register.getLastInvoice()) );
							return true;
						} catch (IOException e) {
							System.err.println("Error writing to file: " + fileName );
							System.err.println(e.getMessage());
						}
					}
				} else {
					System.out.println("No invoice issued during last session.");
				}
				return false;
			}
		});
		commands.put(MenuCommand.LIST_ALL_INVOICES, new Command() {
			@Override
			public boolean action() {
				System.out.println(OutputUtils.getInvoicesReportForCurrentIssuer(register));
				return true;
			}

		});
		commands.put(GENERATE_INVOICING_REPORT_AS_CSV_FILE, new Command() {
			@Override
			public boolean action() {
				System.out.println("Please enter report file name (empty line for cancel):");
				String fileName = in.nextLine().trim();
				if (fileName.isEmpty()) {
					System.out.println("Report generation canceled for current issuer: " + register.getCurrentIssuer());
					return false; //cancel
				}
				try (PrintWriter out = new PrintWriter(fileName)){
					System.out.println( OutputUtils.getInvoicesReportForCurrentIssuerCSV(register) );
					out.println( OutputUtils.getInvoicesReportForCurrentIssuerCSV(register) );
					return true;
				} catch (IOException e) {
					System.err.println("Error writing to file: " + fileName );
					System.err.println(e.getMessage());
				}
				return false;
			}
		});
		commands.put(LOAD, () -> {
			try (ObjectInputStream inSession = new ObjectInputStream(
						new FileInputStream(SESSION_FILENAME))) {
				register = (InvoiceRegister) inSession.readObject();
				return true;
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error loading data from file: " + SESSION_FILENAME);
				e.printStackTrace();
			}
			return false;
		});
		commands.put(SAVE, new Command() {
			@Override
			public boolean action() {
				try (ObjectOutputStream outSession = new ObjectOutputStream(
							new FileOutputStream(SESSION_FILENAME))) {
					outSession.writeObject(register);
					return true;
				} catch (IOException e) {
					System.err.println("Error saving data to file: " + SESSION_FILENAME);
					e.printStackTrace();
				}
				return false;
			}
		});

	}

	private void loadMenuConfigDefaults() {
		for (String s : menuItemStrings) {
			menuItems.add(parseMenuItemString(s));
		}
	}

	public void start() {
		boolean finish = false;
		do {
			System.out.println("           M A I N    M E N U");
			System.out.println("=========================================");
			for (int i = 0; i < menuItems.size(); i++)
				System.out.println("<" + i + "> " + menuItems.get(i).getLabel());
			String answer;
			int chosenOption = 0;
			do {
				System.out.println("Please select operation [0 to " + (menuItems.size()-1) + "]:");
				answer = in.nextLine();
				try {
					chosenOption = Integer.parseInt(answer);
				} catch (NumberFormatException e) {
					System.err.println("Invalid option - should be [0 to " + (menuItems.size()-1) + "].");
				}
			} while (chosenOption < 0 || chosenOption > menuItems.size());

			// execute command
			Command commandToExecute = commands.get(menuItems.get(chosenOption).getCommand());
			if (commandToExecute != null) {
				commandToExecute.action();
			}
			System.out.println("\n");
		} while (!finish);
	}

	public static MenuItem parseMenuItemString(String menuItemString) {
		Pattern p = Pattern.compile("\\s*(.+)\\s*=\\s*(.+)\\s*");
		Matcher m = p.matcher(menuItemString);
		if (m.matches()) {
			try {
				return new MenuItem(m.group(1), Enum.valueOf(MenuCommand.class, m.group(2)));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			}
		}
		System.err.println("Incorrect menu item string: " + menuItemString);
		return new MenuItem();
	}

	public static void main(String[] args) {
		MainMenu menu = new MainMenu(System.in);
		menu.start();
	}
}
