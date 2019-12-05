package lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamDemo {

	public static void main(String[] args) {
		Stream<String> emptyStream = Stream.empty();
		Stream<Character> charStream = Stream.iterate('0', ch -> (char)(ch + 1));
		charStream.limit(78).skip(10).forEach(System.out::print);
		IntStream intStream = IntStream.of(2, 3, 5, 7, 11, 13, 17, 19);
		System.out.println();
		intStream.filter(n -> n % 10 == 3).forEach(System.out::println);
		System.out.println();
		
		Product[] sampleProducts = { 
			new Product(1, "BK001", "Thinking in Java 4th ed.", 25.99),
			new Product(2, "BK002", "UML Distilled", 25.99),
			new Product(3, "BK003", "Effective Java", 32.5),
			new Product(4, "BK004", "Увод в програмирането с Java", 25.99),
			new Product(5, "BK003", "Functional Programming in Java", 25.2),
			new Product(6, "BK003", "Functional Programming for Java Developers", 22.99),
		};
		Stream<Product> productStream = Arrays.stream(sampleProducts);
		Stream<Product> booksStream = Stream.of(
				new Product(1, "BK001", "Thinking in Java 4th ed.", 25.99),
				new Product(2, "BK002", "UML Distilled", 25.99),
				new Product(3, "BK003", "Effective Java", 32.5),
				new Product(4, "BK004", "Увод в програмирането с Java", 25.99),
				new Product(5, "BK003", "Functional Programming in Java", 25.2),
				new Product(6, "BK003", "Functional Programming for Java Developers", 22.99)
		);
		List<Tuple2<Long, String>> titles = booksStream
			.filter(prod -> prod.getName().contains("Java"))
			.sorted(Comparator.comparing(Product::getName))
					//comparators by name
//					(p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
			
			//comaprators by id
//					Comparator.reverseOrder())
//					(p1, p2) -> -p1.compareTo(p2))
					
//				new Comparator<Product>() {
//					@Override
//					public int compare(Product p1, Product p2) {
//						return -p1.compareTo(p2);
//					}
//				})
			.map(p -> new Tuple2<>(p.getId(), p.getName()))
			.collect(Collectors.toList());
		
		titles.stream().forEach(System.out::println);
	}

}
