import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class TestNewIO {

	public static void main(String[] args) throws IOException {
		Path p5 = Paths.get(".").toRealPath();
		Path p6 = p5.resolve(".project");
		System.out.println(p6);
		System.out.printf("Exists: %s", Files.exists(p6));
		List<String> lines = Files.lines(p6).map(String::toUpperCase).collect(Collectors.toList());
		Files.write(Paths.get("./pyout.copy"), lines);
	}

}
