package genrics;

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
	public Book(String code, String name, double price, Unit measure, String author, String publisher) {
		super(code, name, price, measure);
		this.author = author;
		this.publisher = publisher;
	}
	public Book(String code, String name, double price, String author, String publisher) {
		super(code, name, price);
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Book))
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
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
