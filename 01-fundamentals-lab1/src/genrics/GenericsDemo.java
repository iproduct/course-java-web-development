package genrics;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {
	
	public static void list(List<? extends Product> list) { //PCS - Producer - extends
		for(Product p: list) {
			System.out.println(p.getName() + ": " + p.getPrice());
		}
	}
	public static <T> void addElement(List<? super T> list, T elem) { //PCS - Consumer - super
		list.add(elem);
	}

	public static void main(String[] args) {
		List<Book> lb = new ArrayList<Book>(); // 1
		List<Product> initialp = new ArrayList<Product>(); // 1
		List<Object> lo = new ArrayList<Object>(); // 1
		Book b1= new Book("BK001", "Thinking in Java", 12.9, "Bruce Eckel", "Prentice Hall");
		Book b2= new Book("BK002", "UML Distilled", 12.9, "Bruce Eckel", "Prentice Hall");
		Product b3= new Book("BK002", "Effective Java", 25.9, "Bruce Eckel", "Prentice Hall");
		Service s1 = new Service("SV001", "Guaratee service", 125, "1 year");
		lb.add(b1);
		lb.add(b2);
		Product o = b1;
		
		addElement(initialp, b1);
		list(initialp);
//		List<? super Product> lp = lb; // 2 
//		// producer
//		for(Product p: lp) {
//			System.out.println(p.getName() + ": " + p.getPrice());
//		}
//		// consumer
//		lp.add(b3);
//		lp.add(s1);
	}

}
