package org.iproduct.eshop.parser;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.iproduct.eshop.entity.Item;

public class Parser {
	protected Scanner sc;
	
	public Parser(InputStream in) {
		sc = new Scanner(in);
	}
	
	public Item inputItemFactory() {
		Item it = new Item();
		inputItem(it);
		return it;
	}
	
	protected void inputItem(Item it) {
		String inStr;
		//input id
		do{
			System.out.print("Item ID: ");
			inStr = sc.nextLine().trim();
			try {
				it.setId(Long.parseLong(inStr));
			} catch (NumberFormatException e) {}
			if(it.getId() <= 0) {
				System.err.println("Invalid id - should be a positive number.");
			}
		} while (it.getId() <= 0);
		
		//input name
		do{
			System.out.print("Name: ");
			inStr = sc.nextLine().trim();
			Pattern p = Pattern.compile("[a-zA-Z]+");
			if(p.matcher(inStr).matches())
				it.setName(inStr);
			else
				System.err.println("Invalid name - should contain at least one word.");
		} while (it.getName() == null);
		
		//input manufacturer
		do{
			System.out.print("Manufacturer: ");
			inStr = sc.nextLine().trim();
			Pattern p = Pattern.compile("[a-zA-Z]+");
			if(p.matcher(inStr).find())
				it.setManufacturer(inStr);
			else
				System.err.println("Invalid manufacuter - should contain at least one word.");
		} while (it.getManufacturer() == null);
				
		//input category
		do{
			System.out.print("Category: ");
			inStr = sc.nextLine().trim();
			Pattern p = Pattern.compile("[a-zA-Z]+");
			if(p.matcher(inStr).find())
				it.setCategory(inStr);
			else
				System.err.println("Invalid manufacuter - should contain at least one word.");
		} while (it.getCategory() == null);
		
		//input description
		System.out.print("Description: ");
		inStr = sc.nextLine().trim();
		it.setDescription(inStr);
		
		//input price
		do{
			System.out.print("Price: ");
			inStr = sc.nextLine().trim();
			try {
				it.setPrice(Double.parseDouble(inStr));
			} catch (NumberFormatException e) {}
			if(it.getPrice() <= 0) {
				System.err.println("Invalid price - should be a positive number.");
			}
		} while (it.getPrice() <= 0);
		
		//input quantity
		do{
			System.out.print("Available stock quantity: ");
			inStr = sc.nextLine().trim();
			try {
				it.setStockQuantity(Integer.parseInt(inStr));
			} catch (NumberFormatException e) {}
			if(it.getStockQuantity() <= 0) {
				System.err.println("Invalid stock quantity - should be a positive number.");
			}
		} while (it.getStockQuantity() <= 0);

	}
	
	public static void main(String[] args) {
		Parser p  = new Parser(System.in);
		Item it = p.inputItemFactory();
		System.out.println(it);
	}

}
