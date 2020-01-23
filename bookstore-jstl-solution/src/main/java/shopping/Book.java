package shopping;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String authors;
	private String format;
	private String isbn;
	private String publisher;
	private Date publishedDate = new Date();
	private double price;
	private boolean onSale;
	public Book() {
	}
	public Book(long id, String title, String authors, String format, String isbn, String publisher, Date publishedDate,
			double price, boolean onSale) {
		this.id = id;
		this.title = title;
		this.authors = authors;
		this.format = format;
		this.isbn = isbn;
		this.publisher = publisher;
		this.publishedDate = publishedDate;
		this.price = price;
		this.onSale = onSale;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isOnSale() {
		return onSale;
	}
	public void setOnSale(boolean onSale) {
		this.onSale = onSale;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=").append(id).append(", title=").append(title).append(", authors=").append(authors)
				.append(", format=").append(format).append(", isbn=").append(isbn).append(", publisher=")
				.append(publisher).append(", publishedDate=").append(publishedDate).append(", price=").append(price)
				.append(", onSale=").append(onSale).append("]");
		return builder.toString();
	}
	

}
