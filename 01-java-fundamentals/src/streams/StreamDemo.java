package streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import invoicing.utils.Tuple;

public class StreamDemo {

	public static void main(String[] args) throws IOException {
		// Creating streams
		Stream<String> streamEmpty = Stream.empty();
//		streamEmpty.forEach(System.out::println);
		
		Collection<String> collection = Arrays.asList("a", "b", "c");
		Stream<String> streamOfCollection = collection.parallelStream().sequential();
//		streamOfCollection.forEach(System.out::println);
		
		Stream<String> streamOfArray = Stream.of("a", "b", "c", "d");
//		streamOfArray.forEach(System.out::println);
		
		Stream<String> streamBuilder =
				  Stream.<String>builder().add("a").add("b").add("c").build();
//		streamBuilder.forEach(System.out::println);
		
		IntStream intStream = IntStream.range(1, 10);
		LongStream longStream = LongStream.rangeClosed(1, 15).limit(5);
//		intStream.forEach(System.out::println);
		System.out.println();
//		longStream.forEach(System.out::println);
		
		Stream<Integer> streamIterated = Stream.iterate(40, n -> n + 2).limit(10);
//		streamIterated.forEach(System.out::println);
		
		Random rand = new Random();
		
		Stream<String> streamGenerated = Stream.generate(
				() -> String.format("Element %d", rand.nextInt(100))).limit(10);
//		streamGenerated.forEach(System.out::println);
		
		IntStream streamOfChars = "IntStream is used".chars();
		String result = streamOfChars.mapToObj(c -> String.valueOf((char)c))
				.collect(Collectors.joining(", "));
//		System.out.println(result);
		
		Stream<String> streamOfString = Pattern.compile(", ").splitAsStream(result);
//		System.out.println( streamOfString.collect(Collectors.joining()) );
		
		Path path = Paths.get("src/streams/StreamDemo.java");
		String fResult = Files.lines(path)
//				.map(l -> new Tuple<String, Integer>(l, 0))
				.reduce(new Tuple<>("", 1), 
					(acc, line) -> // accumulator
						new Tuple<>(acc.getProp1() + acc.getProp2() + ": " + line + "\n", acc.getProp2() + 1),
					(acc1, acc2) -> // combimner
						new Tuple<>(acc1.getProp1() + "\n" + acc2.getProp2(), 0)
				).getProp1();
		
//		System.out.println(fResult);
		
		StringBuilder resBuilder = Files.lines(path, Charset.forName("UTF-8"))
//	   		.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			.map(String::toUpperCase)
   			.collect(StringBuilder::new, 
   					(sb, line) -> sb.append(line).append(System.lineSeparator()), 
   					(sb1, sb2) -> sb1.append(sb2));
		
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < lines.size(); i++) {
//			sb.append(i + 1).append(": ").append(lines.get(i)).append(System.lineSeparator());
//		}
		
//		System.out.println(resBuilder);
		
		List<String> listWithDuplicates = Arrays.asList("a", "bb", "ccccc", "d", "bb");
		Map<String, Integer> mapResult = 
			listWithDuplicates.stream().collect(Collectors.toMap(Function.identity(), String::length,
				(e1, e2) -> e2));
//		System.out.println(mapResult);
		
		IntSummaryStatistics stat = listWithDuplicates.stream().collect(
				Collectors.summarizingInt(s -> s.length()));
		System.out.println(stat);
		
		
	}

}
