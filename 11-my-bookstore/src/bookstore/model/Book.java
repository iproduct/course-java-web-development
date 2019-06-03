package bookstore.model;

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
	private String name;
	@NonNull
	private String authors;
	@NonNull
	private String description;
	@NonNull
	private String publisher;
	private int year;
	private double price;
	private int qunatity;
	private boolean onSale;
}
