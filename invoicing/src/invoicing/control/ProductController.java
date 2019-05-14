package invoicing.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import invoicing.model.Measure;
import invoicing.model.Product;

import static invoicing.model.Measure.*;

public class ProductController {
	private static Scanner sc = new Scanner(System.in);
	private static Pattern codePattern = Pattern.compile("[A-Z]{2}\\d{4}");
	private static Pattern namePattern = Pattern.compile(".{2,40}");
	
	private List<Product> products = new ArrayList<>();
	
	public static Product inputProduct() {
		Product p = new Product();
		do {
			System.out.print("Code: ");
			String str = sc.nextLine();
			if(codePattern.matcher(str).matches()) {
				p.setCode(str);
			} else {
				System.out.println("Please, input valid code, ex. BK0123");
			}
		} while(p.getCode() == null);
		do {
			System.out.print("Name: ");
			String str = sc.nextLine().trim();
			if(namePattern.matcher(str).matches()) {
				p.setName(str);
			} else {
				System.out.println("Please, input valid name (2-40 characters).");
			}
		} while(p.getName() == null);
		do {
			System.out.print("Price: ");
			String str = sc.nextLine().trim();
			try {
				p.setPrice(Double.parseDouble(str));
			} catch(NumberFormatException ex) {
				System.out.println("Please, input valid price (ex. 25.40).");
			}
		} while(p.getPrice() <= 0);
		do {
			System.out.print("Measure " + Arrays.toString(Measure.values()) + ", PCS by default: ");
			String str = sc.nextLine().trim();
			if(str.length() == 0) {
				p.setMeasure(PCS);
			} else {
				try {
					p.setMeasure(Measure.valueOf(Measure.class, str.toUpperCase()));
				} catch (IllegalArgumentException e){
					System.err.println("Error: Please enter a valid measure.");
				}	
			}
		} while(p.getMeasure() == null);
		
		return p;
	}
	
	public static void main(String[] args) {
		System.out.println(inputProduct());

	}

}
