package invoicing;
import java.util.Scanner;

import invoicing.controller.InvoiceRegister;
import invoicing.controller.InvoiceRegisterImpl;
import invoicing.model.Product;
import invoicing.model.Unit;
import invoicing.view.InputUtilities;

public class MainApp {
	private static Scanner sc = new Scanner(System.in);
	
	private InvoiceRegister invoiceRegister = new InvoiceRegisterImpl();
	
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
				case 1: 
					Product product = InputUtilities.inputProduct(sc);
					if(product != null) {
						Product created = invoiceRegister.addProduct(product);
						System.out.printf("Successfully added product: %d:%s-%8.2f\n", 
								created.getId(), created.getName(), created.getPrice());
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
