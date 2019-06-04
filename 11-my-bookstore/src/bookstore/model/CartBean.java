package bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartBean {
	private Book book;
	private int quantity;
}
