package bookstore.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {
	private long id;
	@NonNull
	private String title;
	@NonNull
	private String authors;
	@NonNull
	private String format;
	@NonNull
	private String isbn;
	@NonNull
	private String publisher;
	private Date publishedDate = new Date();
	private double price;
}
