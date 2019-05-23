package org.iproduct.eshop.entity;

import org.iproduct.eshop.repository.Identifiable;

/**
 * Item class models a stock item in eShop program.
 * Items can be interactively produced using 
 * {@link eshop.parser.Parser#inputItem() eshop.parser.Parser#inputItem() factory method}
 * @author Trayan Iliev
 * @author http://iproduct.org
 * @version 0.1
 * @since 0.1
 * @see eshop.parser.Parser
 */
public class Item implements Identifiable<Long>{
	private Long id;
	private String name;
	private String manufacturer;
	private String category;
	private String description;
	private double price;
	private int stockQuantity;

	/**
	 * No argument constructor
	 */
	public Item() {
		super();
	}

	/**
	 * Constructor with mandatory arguments
	 * @param id item it
	 * @param name item name
	 * @param manufacturer item manufacturer
	 */
	public Item(Long id, String name, String manufacturer) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
	}

	/**
	 * Full constructor
	 * @param @param id item it
	 * @param name item name
	 * @param manufacturer item manufacturer
	 * @param category category name from a predefined list of categories
	 * @param description optional item description
	 * @param price optional standard price for the item 
	 * @param stockQuantity optional available stock quantity for the item
	 */
	public Item(Long id, String name, String manufacturer, String category,
			String description, double price, int stockQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.category = category;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	/**
	 * Returns item id
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets item id
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns item name
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets item name
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return item manufacturer
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets item manufacturer
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * Return item category. 
	 * See {@link #setCategory(String) examples in #setCategory(String)}
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets item category from a predefined list of categories. Example:
	 * <ul>
	 * <li>Processors</li>
	 * <li>Moteherboards</li>
	 * <li>Accessories</li>
	 * <li>etc.</li>
	 * </ul>
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Return item description
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets item description
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Return item price in default currency
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets item price in default currency
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Returns stock quantity available for the item 
	 * @return the stockQuantity
	 */
	public int getStockQuantity() {
		return stockQuantity;
	}

	/**
	 * Modifies the stock quantity available for the item
	 * @param stockQuantity
	 *            the stockQuantity to set
	 */
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Item))
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("|%8d |%25s |%15s |%15s |%7.2f |%5d|",
						this.getId(), this.name, manufacturer, category, price, stockQuantity);
	}

	public static void main(String[] args) {
		Item i1 = new Item(1L, "Computer Mouse", "Logitech", "Accessoaries",
				"High quality optical mouse", 12.5, 20);
		Item i2 = new Item(2L, "Motherboard", "ASUS", "Motherboards",
				"AMD Athlon II x4 A03 motherboard", 125.7, 15);
		System.out.println(i1);
		System.out.println(i2);
	}

}
