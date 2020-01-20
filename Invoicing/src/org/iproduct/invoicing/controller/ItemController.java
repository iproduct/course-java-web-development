/* COPYRIGHT & LICENSE HEADER
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
*
* IPTInvoiceServer is part of multi-tier client-server invoicing application 
* using JPA, Swing and RMI technologies.
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

package org.iproduct.invoicing.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.iproduct.invoicing.entity.old.Book;
import org.iproduct.invoicing.entity.old.Item;

public class ItemController<T extends Item> {
	public static final double VAT_RATE = .2;
	public static final String PRODUCTS_FILENAME = "products.db";
	private List<T> items = new ArrayList<>();
	private File productsDBFile = new File(PRODUCTS_FILENAME);
	
	public ItemController() {
		readProductsFromFile();
	}

	public ItemController(File productsDBFile) {
		this();
		this.productsDBFile = productsDBFile;
	}

	public List<T> getItems() {
		return items;
	}
	
	public File getProductsDBFile() {
		return productsDBFile;
	}


	public void setProductsDBFile(File productsDBFile) {
		this.productsDBFile = productsDBFile;
	}



	public double calcualteTotalPrice(Item[] products) {
		double total = 0;
		for (Item p : products)
			total += p.getPrice();
		return total;
	}

	public double calculateVat(double price) {
		return price * VAT_RATE;
	}

	public void writeProductsToFile() {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(productsDBFile))) {
			out.writeObject(items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readProductsFromFile() {
		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(productsDBFile))) {
			Object obj = in.readObject();
			if (obj instanceof List) {
				items = (List<T>) obj;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public boolean addItem(T item) {
		item.setId(maxId() + 1);
		items.add(item);
		writeProductsToFile();
		System.out.println(items);
		return true;
	}

	private long maxId() {
		Optional<Long> maxId = items.stream().map(Item::getId)
				.max(Comparator.comparing(Long::longValue));
		return (maxId.isPresent())? maxId.get() : 0;
	}

	public static void main(String[] args) {
		ItemController<Book> pc = new ItemController<>();
		// Item[] hardwareItems = new Item[] {
		// new Item(1, "Whiteboard Marker", "", .89, "Hardware"),
		// new Item(2, "USB Flash 16GB", "AData", 8.5, "Hardware"),
		// new Item(3, "Computer Mouse", "Logitech", 12.33, "Hardware")
		// };
		Book[] books = new Book[] {
				new Book(1001, "Thinking in Java", 24.5, "Bruce Eckel", "Prenctice Hall", 2011, "007-6092039389"),
				new Book(1002, "UML Distilled", 18.5, "Martin Fowler", "Prenctice Hall", 2010, "010-643589389") };

		// Serialize to file
		Arrays.asList(books).stream().forEach(book -> pc.getItems().add(book));
		System.out.println(pc.getItems());
//		pc.writeProductsToFile(PRODUCTS_FILENAME);
		pc.getItems().clear();
//		pc.readProductsfromFile(PRODUCTS_FILENAME);

		// Print products
		pc.getItems().stream().forEach(System.out::println);

		// Invoice<Book> bookstoreInvoice =
		// new Invoice<>("BG21332123121, Best Books Ltd., Sofia", "Ivan
		// Petrov");
		// for(Book b : books)
		// bookstoreInvoice.addPosition(b, 3);
		// System.out.println(bookstoreInvoice);

		// double total = pc.calcualteTotalPrice(hardwareItems);
		// System.out.println(total);
	}

}
