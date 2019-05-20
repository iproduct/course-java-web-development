package invoicing.view;

import java.util.Scanner;

import invoicing.controller.InvoiceRegister;
import invoicing.exception.NonExistingEntityException;
import invoicing.model.Company;
import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;
import invoicing.util.ContragentComparatorByName;
import invoicing.util.ProductComparatorByCode;

public class InputUtils {
	
	public static Contragent inputContragent(Scanner in) {
		Contragent contragent = new Contragent(); 
		String input;

		//input code
		System.out.println("Id Number (empty line for cancel): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) return null;
			if (input.matches("\\d{9,12}") )
				contragent.setIdNumber(Long.parseLong(input));
			else
				System.err.println("Id Number should be 9 to 12 digits.");
		} while (contragent.getIdNumber() <= 0);
		
		//input name
		System.out.println("Name (empty line for cancel): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) return null;
			if ( !input.isEmpty() )
				contragent.setName(input);
			else
				System.err.println("Name should not be empty.");
		} while (contragent.getName() == null);
		
		//input address
		System.out.println("Adress (empty line for cancel): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) return null;
			if ( !input.isEmpty() )
				contragent.setAddress(input);
			else
				System.err.println("Address should not be empty.");
		} while (contragent.getAddress() == null);
		
		//input phone
		System.out.println("Phone (empty line to skip): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) break;
			if ( input.matches("[0-9\\+\\(\\)\\s]{5,20}") )
				contragent.setPhone(input);
			else
				System.err.println("Invalid phone number. Ex.: (+3592) 9762540");
		} while (contragent.getPhone() == null);
		
		//input price
		System.out.println("Is company [yes OR no, empty line for cancel]: ");
		boolean valid = false;
		do {
			input = in.nextLine().trim().toLowerCase();	
			if (input.isEmpty()) return null;
			if ( input.matches("[yn]\\w{0,2}") ) {
				contragent.setOrganization(input.startsWith("y"));
				valid = true;
			} else
				System.err.println("yes or no");
		} while (!valid);		
		return contragent;
	}
	
	public static Company inputCompanyDeltas(Scanner in, Contragent baseContragent ) {
		Company company = new Company(baseContragent.getIdNumber(), baseContragent.getName(), 
				baseContragent.getAddress(), baseContragent.getPhone(), true);
		String input;
		
		//is VAT registered
		System.out.println("Is VAT registered [yes OR no, empty line for cancel]: ");
		boolean valid = false;
		do {
			input = in.nextLine().trim().toLowerCase();		
			if (input.isEmpty()) return null; //cancel
			if ( input.matches("[yn]\\w{0,2}") ) {
				company.setVatRegistered(input.startsWith("y"));
				valid = true;
			} else
				System.err.println("yes or no");
		} while (!valid);
		
		//input accountable person name
		System.out.println("Accountable Person Name (empty line to skip): ");
		do {
			input = in.nextLine();
			if ( !input.isEmpty() )
				company.setAccountablePerson(input);
			else
				break; //skip
		} while (company.getAccountablePerson() == null);
		
		//input BIC
		System.out.println("BIC (empty line to skip): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) break; //skip
			if (input.matches("\\w{6}") )
				company.setBic(input);
			else
				System.err.println("BIC should be 6 characters.");
		} while (company.getBic() == null);
		
		//input IBAN
		System.out.println("IBAN (empty line to skip): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) break; //skip
			if (input.matches("\\w{10,16}") )
				company.setIban(input);
			else
				System.err.println("IBAN should be 10 to 16 characters.");
		} while (company.getIban() == null);	
		
		return company;
	}

	
	public static Product inputProduct(Scanner in) {
		Product product = new Product();
		String input;

		//input code
		System.out.println("Product code (empty line for cancel):");
		do {
			input = in.nextLine();
			if (input.isEmpty()) return null; //cancel
			if (input.matches("(\\d|[a-zA-Z]){5,10}") )
				product.setCode(input);
			else
				System.err.println("Code should be 5 to 10 letters or digits.");
		} while (product.getCode() == null);
		
		//input name
		System.out.println("Product name (empty line for cancel): ");
		do {
			input = in.nextLine();
			if (input.isEmpty()) return null; //cancel
			product.setName(input);
		} while (product.getName() == null);
		
		//input price
		System.out.println("Product price  (empty line for cancel): ");
		do {
			input = in.nextLine().trim();
			if (input.isEmpty()) return null; //cancel
			if ( input.matches("\\d+(\\.\\d+)?") )
				product.setPrice(Double.parseDouble(input));
			else
				System.err.println("Invalid price. Ex: 9.57");
		} while (product.getPrice() < 0);
		return product;
	}
	
	public static boolean inputInvoice(Scanner in, InvoiceRegister register) {
		boolean invoiceCompleted = false;
		do { //while not valid invoice or canceled 
			System.out.println("Please enter invoice RECEIVER Id Number (h for help, empty line for cancel):");
			String input = in.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("Current receiver selection canceled.");
				return false; //cancel
			}
			if(input.toLowerCase().equals("h")){ //help
				register.printAllContragentsSorted(new ContragentComparatorByName());
				continue;
			}
			try{
				long idNumber = Long.parseLong(input);
				Contragent receiver = register.findContragentByIdNumber(idNumber);
				if(receiver == null || receiver.equals(register.getCurrentIssuer()) ){ //receiver should exist and should be different from issuer
					System.err.println("Invalid receiver company Id Number. Please try again.");
					continue;
				}
				//create invoice
				Invoice newInvoice = register.newInvoice(receiver);
				//add positions
				positions_loop:
				do { //while more positions
					System.out.println("Enter next product code (h for help, empty line to finish)");
					String productCode = in.nextLine().trim();
					if (productCode.isEmpty()) { //last position
						System.out.println(newInvoice.getPositions().size() + " positions added to incoice.");
						break;
					}
					if(productCode.toLowerCase().equals("h")){ //help
						register.printAllProductsSorted(new ProductComparatorByCode());
						continue positions_loop;
					}
					double quantity = -1;
					do { //while quantity not valid and not canceled
						System.out.println("Enter product quantity (empty line to cancel position)");
						input = in.nextLine();
						if (input.isEmpty()) { //cancel position
							System.out.println("Position canceled for product code: " + productCode);
							continue positions_loop; 
						}
						try{
							quantity = Double.parseDouble(input.trim());
							register.addPositionToInvoice(newInvoice, productCode, quantity); // add position to invoice
							Position positionAdded = newInvoice.getPositions().get(newInvoice.getPositions().size()-1);
							System.out.println("New position successfully added - product: " 
									+ positionAdded.getProduct().getName() // last product
									+ ", quantity: " + positionAdded.getQuantity());
						} catch (NumberFormatException e){
							System.err.println("Invalid quantity - should be real number. Please try again.");
						} catch (NonExistingEntityException e) {
							System.err.println("Invalid product code: " + productCode);
						}
					} while (quantity < 0);
				} while (true); // add positions loop
				register.addInvoice(newInvoice); //adding new invoice to Invoiceregister
				System.out.println("New invoice successfully added - number: "
						+ newInvoice.getNumber() 
						+ ", receiver: " + newInvoice.getReceiver().getName()
						+ ", total VAT included: " + newInvoice.getTotalPlusVAT() );
				invoiceCompleted = true;
			} catch (NumberFormatException e){
				System.err.println("Invalid company Id Number - should be 9-12 digits. Please try again.");
			}
		} while (!invoiceCompleted);
		return true;
	}



}
