package listing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DirList {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.out
				.println("Usage:\njava DirList <base-drirectory>");
			return;
		}
//		Files.walk(Paths.get(args[0])).filter(path -> Files.isDirectory(path))
//		.forEach(path -> System.out.println(path));
		Files.walk(Paths.get(args[0])).forEach(path -> {
			if(Files.isDirectory(path)){
				System.out.println("+ " + path.normalize());
			} else {
				System.out.println("    " + path.getFileName());
			}
		});
	}

}
