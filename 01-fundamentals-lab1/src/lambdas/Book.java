package lambdas;

public class Book extends Product {
	private String author;
	private String publisher;
	public Book() {
//		super();
	}
	public Book(long id, String code, String name, double price, Unit unit, String author, String publisher) {
		super(id, code, name, price, unit);
		this.author = author;
		this.publisher = publisher;
	}
	public Book(long id, String code, String name, double price, String author, String publisher) {
		super(id, code, name, price);
		this.author = author;
		this.publisher = publisher;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [author=").append(author)
		.append(", publisher=").append(publisher)
		.append(", toString()=").append(super.toString()).append("]");
		return builder.toString();
	}
}
