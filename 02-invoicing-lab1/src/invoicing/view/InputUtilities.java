package invoicing.view;

import java.util.Scanner;

import invoicing.model.Product;
import invoicing.model.Unit;

public class InputUtilities {
	
	public static Product inputProduct(Scanner sc) {
		Product p = new Product();
		String input;
		do {
			System.out.println("Product code (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.matches("\\d{4,10}|[a-zA-Z]{2}\\d{2,8}")) {
				p.setCode(input);
			} else {
				System.err.println("Code must be 4-10 letters or digits (ex. BK025).");
			}
		} while(p.getCode() == null);
		
		do {
			System.out.println("Product name (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.length() >= 2 && input.length() <= 40) {
				p.setName(input);
			} else {
				System.err.println("Name must be between 2 and 40 characters long.");
			}
		} while(p.getName() == null);
		
		do {
			System.out.println("Product price (empty line for cancel):");
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			if(input.matches("\\d+(\\.\\d{1,2})?")) {
				try {
					p.setPrice(Double.parseDouble(input));
				} catch(NumberFormatException ex) {
					System.err.println("Invalid number. Try again (ex. 12.50).");
				}
			} else {
				System.err.println("Invalid number. Try again (ex. 12.50).");
			}
		} while(p.getPrice() < 0);
		
		do {
			StringBuilder sb = new StringBuilder("Product unit (empty line for cancel) [");
			for(Unit u: Unit.values()) {
				if(u.ordinal() > 0) {
					sb.append(", ");
				}
				sb.append(u.ordinal()).append(":").append(u);
			}
			sb.append("]:");
			System.out.println(sb); // print choices
			input = sc.nextLine().trim();
			if(input.isEmpty()) return null;
			try {
				int choice = Integer.parseInt(input);
				if(choice >= 0 && choice < Unit.values().length ) {
					p.setUnit(Unit.values()[choice]);
				} else {
					System.err.printf("The choice should be between %d and %d\n", 0, Unit.values().length - 1);
				}
			}catch(NumberFormatException ex) {
				System.err.println("Invalid number. Try again.");
			}
		} while(p.getUnit() == null);
		
		return p;
	}
	
	public static int paseInt(String input, int min, int max) {
		try {
			int choice = Integer.parseInt(input);
			if(choice >= min && choice <= max ) {
				return choice;
			} else {
				return -1;
			}
		}catch(NumberFormatException ex) {
			return -1;
		}
	}
}
