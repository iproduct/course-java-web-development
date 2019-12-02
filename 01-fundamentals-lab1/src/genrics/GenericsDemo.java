package genrics;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

	public static void main(String[] args) {
		List<Book> lb = new ArrayList<Book>(); // 1
		Book b1= new Book("BK001", "Thinking in Java", 12.9, "Bruce Eckel", "Prentice Hall");
		Book b2= new Book("BK002", "UML Distilled", 12.9, "Bruce Eckel", "Prentice Hall");
		Product b3= new Book("BK002", "Effective Java", 25.9, "Bruce Eckel", "Prentice Hall");
		lb.add(b1);
		lb.add(b2);
		Product o = b1;
		List<? extends Product> lp = lb; // 2 
		// consuming
		for(Product p: lp) {
			System.out.println(p);
		}
		// producing
		lp.add(b3);
	}

}
