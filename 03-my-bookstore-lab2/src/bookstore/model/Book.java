package bookstore.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
	private long id;
	@NonNull
	private String title;
	@NonNull
	private String authors;
	private String format;
	private String isbn;
	private String publisher;
	private Date publishDate = new Date();
	private double price;
}
