package invoicing.control;

import static invoicing.model.Measure.M;
import static invoicing.model.Measure.PCS;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import invoicing.exceptions.EntityDoesNotExistException;
import invoicing.exceptions.EntityExistsException;
import invoicing.model.Measure;
import invoicing.model.Product;

public class ProductController {
	private static Scanner sc = new Scanner(System.in);
	private static Pattern codePattern = Pattern.compile("[A-Z]{2}\\d{4}");
	private static Pattern namePattern = Pattern.compile(".{2,40}");
	
	private Map<String, Product> products = new HashMap<>();
	
	public static Product inputProduct() {
		Product p = new Product();
		do {
			System.out.print("Code: ");
			String str = sc.nextLine();
			if(codePattern.matcher(str).matches()) {
				p.setId(str);
			} else {
				System.out.println("Please, input valid code, ex. BK0123");
			}
		} while(p.getId() == null);
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
	
	public Product add(Product p) throws EntityExistsException {
		if( products.containsKey(p.getId()) ) {
			throw new EntityExistsException(String.format("Product with code=%s already exists.", p.getId()));
		}
		products.put(p.getId(), p);
		return p;
	}
	
	public Product update(Product p) throws EntityDoesNotExistException {
		if(!products.containsKey(p.getId()) ) {
			throw new EntityDoesNotExistException(String.format("Product with code=%s does not exist.", p.getId()));
		}
		products.put(p.getId(), p);
		return p;
	}
	
	//TreeSet impl
//	public Optional<Product> findByCode(String code) {
//		Product found = products.floor(new Product(code));
//		return Optional.ofNullable(
//				found != null && found.getCode().equals(code) ? found : null
//		);
//	}
	
	public Optional<Product> findByCode(String code) {
		return Optional.ofNullable(products.get(code));
	}
	
	public Collection<Product> findAll(){
		return products.values();
	}
	
	public static void main(String[] args) throws EntityExistsException, EntityDoesNotExistException {
		ProductController ctrl = new ProductController();
		ctrl.add(new Product("BK1125", "Thinking in Java", 25.70, PCS));
		ctrl.add(new Product("CA4218", "Computer Mouse", 12.99, PCS));
		ctrl.add(new Product("HA0019", "Network cable", 2.17, M));
		ctrl.add(new Product("AX9972", "Copier paper", 12.30, PCS));
		for(Product p : ctrl.findAll()) {
			System.out.println(p);
		}
		
		System.out.println();
		ctrl.update(new Product("BK1125", "Effective Java", 35.70, PCS));
		for(Product p : ctrl.findAll()) {
			System.out.println(p);
		}
		
	}

}
