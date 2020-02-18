package replacing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReplaceDir {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		if (args.length != 5) {
			System.out
					.println("Usage:\njava Replace <base-drirectory> <glob> <out-filename> <word-to-be-replaced> <replacement>");
			return;
		}
		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new FileWriter(args[2])))) {
			DirectoryStream<Path> files = 
					Files.newDirectoryStream(Paths.get(args[0]), args[1]);
			for (Path file : files) {
				try (BufferedReader in = new BufferedReader(
						new FileReader(file.toFile()))) {
					String s = new String();
					while ((s = in.readLine()) != null) {
						s = s.replaceAll(args[3], args[4]);
						System.out.println(s);
						out.println(s);
					}
					System.out.println("Replacement is successful to file: "
							+ args[1]);
				}
			}
		}
		;

	}
}
