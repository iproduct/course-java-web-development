package org.iproduct.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.iproduct.eshop.entity.Item;

public class HelloLambda {
	public static final Item[] items = {
			new Item(42L, "Computer Mouse", "Logitech", "Accessoaries",
					"High quality optical mouse", 12.5, 20),

			new Item(15L, "Motherboard", "ASUS", "Motherboards",
					"AMD Athlon II x4 A03 motherboard", 125.7, 15),

			new Item(3L, "Windows 8", "Microsoft", "Software",
					"Popular desktop OS", 125.7, 15),

			new Item(105L, "External Hard Disk", "Seagate", "Hard Disks",
					"Good archiving solution for small office", 125.7, 15),

			new Item(9L, "Motherboard", "ASUS", "Motherboards",
					"X99-SOC Force motherboard", 125.7, 15),

	};

	public static void main(String[] args) {
		List<Item> il = Arrays.asList(items);
		il.stream().sorted(Comparator.comparing(Item::getName).reversed())
			.forEachOrdered(System.out::println);	
		System.out.println(
			il.stream()
			.mapToDouble((Item i) -> i.getStockQuantity() * i.getPrice())
			.sum());

		System.out.println("\nWithout lambdas:");
		List<Integer> list = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return (int) Math.signum(i2 - i1);
			}
		});
		for (Integer n : list) {	
			int x = n * n;
			System.out.println(x);
		}

		System.out.println("\nWith lambdas:");
		List<Integer> list1 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		Consumer<Integer> p = System.out::println;	
		list1.stream().sorted((i1, i2) -> i2-i1 )
			.map(x -> x*x)
			.forEach(p);

		System.out.println("\nWithout lambdas:");
		List<Integer> list2 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		int sum2 = 0;
		for (Integer n : list2) {
			int x = n * n;
			sum2 = sum2 + x;
		}
		System.out.println(sum2);

		System.out.println("\nWith lambdas:");
		List<Integer> list3 = Arrays.asList(4, 3, 6, 7, 2, 5, 1);
		int sum3 = list3.stream().sorted().map(x -> x * x)
				.reduce((x, y) -> x + y).get();
		System.out.println(sum3);
		
		// Test find keyword
		System.out.println("============================>");
		
		String keywordsRegex = "motherboard";
		List<Item> itemsList = Arrays.asList(items);
		Pattern pat = Pattern.compile(keywordsRegex);
		itemsList.parallelStream().flatMap((Item i) -> {
			Map<Item, Integer> matches = new HashMap<>();
			Matcher m = pat.matcher(i.getDescription());
			while(m.find())
				matches.put(i, m.start());
			return matches.entrySet().stream();
		}).forEach(System.out::println);	
		
	}

}
