package replacing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.*;

public class ReplaceLambda {

	public void replaceOccurencesFromInfileToOutfile(
		String inFileName, String outFileName, String toBeFound, String replacement)
		throws IOException {
		List<String> resultLines = 
			Files.lines(Paths.get(inFileName))
			.map(s -> s.replaceAll(toBeFound, replacement) )
			.collect(Collectors.toList());
		Files.write(Paths.get(outFileName), resultLines);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length != 4) {
			System.out.println("Usage:\njava Replace <infilename> <outfilename> <word-to-be-replaced> <replacement>");
			return;
		}
		ReplaceLambda instance = new ReplaceLambda();
		instance.replaceOccurencesFromInfileToOutfile(args[0], args[1], args[2], args[3]);
	    System.out.println("Replacement is successful to file: " + args[1]);	  
	}

}
