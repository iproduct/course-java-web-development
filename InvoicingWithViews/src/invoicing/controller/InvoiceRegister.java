/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* InvoiceRegister project is a simple invoicing demo.
* 
* Copyright (c) 2012 - 2016 IPT - Intellectual Products & Technologies Ltd.
* All rights reserved.
*
* E-mail: office@iproduct.org
* Web: http://iproduct.org/
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License (the "License")
* as published by the Free Software Foundation version 2 of the License.
* You may not use this file except in compliance with the License. You can
* obtain a copy of the License at root directory of this project in file
* LICENSE.txt.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License along
* with this program; if not, write to the Free Software Foundation, Inc.,
* 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*
* When distributing the software, include this COPYRIGHT & LICENSE HEADER
* in each file, and include the License file LICENSE.txt in the root directory
* of your distributable.
*
* GPL Classpath Exception:
* IPT - Intellectual Products & Technologies (IPT) designates this particular
* file as subject to the "Classpath" exception as provided by IPT in
* the GPL Version 2 License file that accompanies this code.
*
* In case you modify this file,
* please add the appropriate notice below the existing Copyright notices,
* with the fields enclosed in brackets {} replaced by your own identification:
* "Portions Copyright (c) {year} {name of copyright owner}"
*/

package invoicing.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import invoicing.exception.EntityAlreadyExistsException;
import invoicing.exception.NonExistingEntityException;
import invoicing.model.Company;
import invoicing.model.Contragent;
import invoicing.model.Invoice;
import invoicing.model.Position;
import invoicing.model.Product;
import invoicing.util.ProductComparatorByCode;
import invoicing.util.ProductComparatorByPrice;
import invoicing.view.OutputUtils;

/**
 * Top level invoicing application class.
 * @author Trayan Iliev
 * @version 1.0
 * @since 1.0
 * @see org.iproduct.invoicing.model.Invoice
 */
public class InvoiceRegister implements Serializable{
	public static final Product[] SAMPLE_PRODUCTS = {
			new Product("BK001", "Thinking in Java", 15.7),
			new Product("HD001", "Logitech Optical Mouse", 8.75),
			new Product("SF001", "Photoshop CC", 2000),
			new Product("HD002", "Raspberry Pi 2", 80),
			new Product("SV001", "Raspbian Installation", 20),
			new Product("HD003", "3dIMU - 3D Acceelerometr, Gyrospone & Comapss", 35)
	};
		
	public static final Position[] SAMPLE_POSITIONS = {
			new Position(SAMPLE_PRODUCTS[0]), 
			new Position(SAMPLE_PRODUCTS[1], 5), 
			new Position(SAMPLE_PRODUCTS[3], 10)
	};
		
	public static final Contragent[] SAMPLE_CONTRAGENTS = {			
			new Contragent(122222222, "ABC Ltd.", "Plovdiv"),
			new Contragent(1234567891, "Ivan Petrov", "Sofia 1000"),
			new Contragent(234234243, "D. Anatasov", "Sofia")
	};

	public static final Company[] SAMPLE_COMPANIES = {			
			new Company(1234567895L, "ABC Ltd.","Sofia 1000", "359885647345",
					true, true, "Ivan Petrov", "ASADAE", "ASADAE223234235556"),
			new Company(1234567892L, "Best Widgets Inc","Sofia 1240", "35902457698",
					true, true, "Dimitar Mladenov", "ERTDSF", "ERTDSF3452235556"),
			new Company(123456789, "Stocks Ulimited AD","Sofia 1790", "35902865734",
					true, true, "Sergei Eftimov", "ATDBTG", "ATDBTG2232343445")
	};

	
	
	private Map<String, Product> products = new HashMap<>();
	private Map<Long, Contragent> contragents = new HashMap<>();
	private Map<Company, List<Invoice>> invoices= new HashMap<>(); //invoice lists for each issuer
	private Company currentIssuer;
	private Invoice lastInvoice;
	
	//Invoice register initialization
	public void initialize(Collection<? extends Product> products, 
			Collection<? extends Contragent> contragents){
		setCurrentIssuer(SAMPLE_COMPANIES[0]);
		this.products.clear();
		for(Product p : products)
			this.products.put(p.getCode(), p);
		this.contragents.clear();
		for(Contragent p : contragents)
			this.contragents.put(p.getIdNumber(), p);
	}
	
	
	public Company getCurrentIssuer() {
		return currentIssuer;
	}


	public void setCurrentIssuer(Company currentIssuer) {
		this.currentIssuer = currentIssuer;
		if(invoices.get(getCurrentIssuer()) == null) {
			invoices.put(getCurrentIssuer(), new ArrayList<>());
		}
	}

	public Invoice getLastInvoice() {
		return lastInvoice;
	}
	

	//Product methods
	public Product findProductByProductCode(String pCode){
		return products.get(pCode);
	}

	public void addProduct(Product product) throws EntityAlreadyExistsException {
		if ( products.containsKey(product.getCode()) )
			throw new EntityAlreadyExistsException("Product with code = " + product.getCode() + " already exists.");
		
		int maxProductId = 0;
		if(!products.isEmpty()) {
			Product maxProduct = Collections.max(products.values());
			maxProductId = maxProduct.getId();
		}
		product.setId(maxProductId + 1);
		products.put(product.getCode(), product);
	}
	
	public void printAllProductsSorted(Comparator<Product> pc){
		List<Product> plist = new ArrayList<>(products.values());
		Collections.sort(plist, pc);
		for(Product p : plist)
			System.out.println(p);
	}
	
	
	//Contragent methods
	public Contragent findContragentByIdNumber(long idNumber){
		return contragents.get(idNumber);
	}
	
	public void addContragent(Contragent contragent) throws EntityAlreadyExistsException {
		if ( contragents.containsKey(contragent.getIdNumber()) )
			throw new EntityAlreadyExistsException("Contragent with ID Number = " + contragent.getIdNumber() + " already exists.");
		contragents.put(contragent.getIdNumber(), contragent);
	}
	
	public void printAllContragentsSorted(Comparator<Contragent> cc){
		List<Contragent> clist = new ArrayList<>(contragents.values());
		Collections.sort(clist, cc);
		for(Contragent c : clist)
			System.out.println(c);
	}
	
	public void printAllOrganizationsSorted(Comparator<Contragent> cc){
		List<Contragent> clist = new ArrayList<>(contragents.values());
		Collections.sort(clist, cc);
		for(Contragent c : clist)
			if(c.isOrganization())
				System.out.println(c);
	}
	
	
	//Invoice building methods
	public Invoice newInvoice(Contragent receiver){
		long maxNumber = 0;
		if(!invoices.get(getCurrentIssuer()).isEmpty()) {
			Invoice maxInvoice = Collections.max(invoices.get(currentIssuer));
			maxNumber = maxInvoice.getNumber();
		}
		Invoice newInvoice = new Invoice(maxNumber+1, getCurrentIssuer(), receiver, new Date(),
				new ArrayList<Position>());
		return newInvoice;
	}
	
	public void addPositionToInvoice(Invoice invoice, String productCode,
			double quantity) throws NonExistingEntityException {
		Product product = findProductByProductCode(productCode);
		if (product == null)
			throw new NonExistingEntityException("Invalid product code: " + productCode);
		invoice.addPosition(product, quantity);
	}
	
	public void addInvoice(Invoice invoice){
		invoices.get(getCurrentIssuer()).add(invoice);
		lastInvoice = invoice;
	}
	
	public List<Invoice> getInvoicesForCurrentIssuer() {
		return invoices.get(getCurrentIssuer());
	}



	public static void main(String[] args) {
		
		InvoiceRegister register = new InvoiceRegister();
		register.initialize(Arrays.asList(SAMPLE_PRODUCTS), Arrays.asList(SAMPLE_CONTRAGENTS));
		
		register.printAllProductsSorted(new ProductComparatorByCode());
		System.out.println();
		register.printAllProductsSorted(new ProductComparatorByPrice());
		
		System.out.println("\n" + register.findProductByProductCode("HD001"));
		
		//Create invoice
		
//		Company issuer = SAMPLE_COMPANIES[0];
//		issuer.input(System.in);
		
		Contragent receiver = new Contragent(234234243, "D. Anatasov", "Sofia");
//		receiver.input(System.in);
		
		Invoice invoice = register.newInvoice(receiver);
		try {
			register.addPositionToInvoice(invoice, "SF001", 7);
			register.addPositionToInvoice(invoice, "HD001", 14);
			register.addPositionToInvoice(invoice, "HD003", 2);
			register.addInvoice(invoice);
		} catch (NonExistingEntityException e) {
			e.printStackTrace();
		}
		
		System.out.println(OutputUtils.formatInvoice(invoice));
		
		invoice = register.newInvoice(receiver);
		try {
			register.addPositionToInvoice(invoice, "SF001", 7);
			register.addPositionToInvoice(invoice, "HD001", 14);
			register.addPositionToInvoice(invoice, "HD003", 2);
			register.addInvoice(invoice);
		} catch (NonExistingEntityException e) {
			e.printStackTrace();
		}
		
		System.out.println(OutputUtils.formatInvoice(invoice));
	}


}
